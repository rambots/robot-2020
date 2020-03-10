package com.rambots4571.infiniterecharge.robot.command;

import com.rambots4571.infiniterecharge.robot.subsystem.Indexer;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class IndexCommand extends CommandBase {
    private Indexer indexer = Indexer.getInstance();
    private boolean runConveyor;

    public IndexCommand() {
        addRequirements(indexer);
    }

    @Override
    public void initialize() {
    }

    @Override
    public void execute() {
        if (!indexer.isCellInTop()) {
            if (indexer.isCellInIntake()) runConveyor = true;
            if (indexer.didCellReachChamber()) runConveyor = false;
            if (runConveyor) indexer.setConveyor(0.5);
        }
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}
