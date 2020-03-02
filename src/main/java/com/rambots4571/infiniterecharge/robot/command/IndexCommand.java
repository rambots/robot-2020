package com.rambots4571.infiniterecharge.robot.command;

import com.rambots4571.infiniterecharge.robot.subsystem.Indexer;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class IndexCommand extends CommandBase {
    private Indexer indexer = Indexer.getInstance();
    private boolean isCellAlreadyIn;
    private boolean didCellMoveChamber;

    public IndexCommand() {
        addRequirements(indexer);
    }

    @Override
    public void initialize() {
        isCellAlreadyIn = indexer.isCellInChamber();
    }

    @Override
    public void execute() {
        boolean runConveyor = false;
        // if there's no ball at the top run the command.
        if (!indexer.isCellInTop()) {
            // run the conveyor when it detects a ball entering.
            if (indexer.didBallEnterIntake()) runConveyor = true;
            // checks if there's cell already in the system.
            if (isCellAlreadyIn && indexer.didCellLeaveChamber())
                isCellAlreadyIn = false;
            // when there's no cell in the chamber and a new one enters, stop
            // the conveyor belt.
            if (!isCellAlreadyIn && indexer.didBallReachChamber()) {
                isCellAlreadyIn = true;
                runConveyor = false;
            }
            if (runConveyor) indexer.setConveyor(0.4);
            else indexer.stopConveyor();
        }
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}
