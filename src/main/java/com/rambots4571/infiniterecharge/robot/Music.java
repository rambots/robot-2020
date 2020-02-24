package com.rambots4571.infiniterecharge.robot;

import com.ctre.phoenix.music.Orchestra;
import com.rambots4571.infiniterecharge.robot.subsystem.Shooter;

public class Music {
    private Orchestra orchestra;
    private static Music instance;
    private int songSelection = 0;
    private int loops = 0;

    private String[] songs = new String[] {
            "darude.chrp"
    };

    private Music() {
        orchestra = new Orchestra(Shooter.getInstance().getFalcons());
        orchestra.loadMusic(songs[songSelection]);
    }

    public static synchronized Music getInstance() {
        if (instance == null) instance = new Music();
        return instance;
    }

    public void changeSong(int offset) {
        songSelection += offset;

        if (songSelection > songs.length) songSelection = 0;

        if (songSelection < 0) songSelection = songs.length - 1;

        orchestra.loadMusic(songs[songSelection]);
    }

    public void toggle() {
        if (orchestra.isPlaying()) {
            orchestra.pause();
        } else {
            orchestra.play();
        }
    }

    public void skip() {
        changeSong(1);
    }

    public void prev() {
        changeSong(-1);
    }

    public void play() {
        orchestra.play();
    }

    public void pause() {
        orchestra.pause();
    }
}
