package com.gustavolr.engine.sound;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.InputStream;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class SoundPlayer {
    
    Clip soundClip;

    boolean soundLoaded;

    public SoundPlayer() {
        soundLoaded = false;
    }

    public SoundPlayer(InputStream fileName) {
        this.loadSFXfromFile(fileName);
    }

    public void loadSFXfromFile(InputStream file) {

        InputStream bufferedInputStream = new BufferedInputStream(file);

        try {

            AudioInputStream audio = AudioSystem.getAudioInputStream(bufferedInputStream);
            this.soundClip = AudioSystem.getClip();
            soundClip.open(audio);
        
            this.soundLoaded = true;
        } catch (Exception e) {

            e.printStackTrace();
            soundLoaded = false;
        } 
    }
    
    public void setLoopMode(int loopMode) {
        soundClip.loop(loopMode);
    }

    public void play() {

        if (soundLoaded) {

            soundClip.setFramePosition(0);
            soundClip.start();
        } else {

            System.err.println("Error! No sound/music was loaded!");
        }
    }

}
