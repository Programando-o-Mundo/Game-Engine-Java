package com.gustavolr.engine.sound;

import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class SoundPlayer {
    
    Clip soundClip;
    public String fileName;

    boolean soundLoaded;

    public SoundPlayer() {
        soundLoaded = false;
    }

    public SoundPlayer(String fileName) {
        this.fileName = fileName;
        this.loadSFXfromFile(fileName);
    }

    public void loadSFXfromFile(String fileName) {

        File sfxFile = new File(fileName.strip());

        try {

            AudioInputStream audio = AudioSystem.getAudioInputStream(sfxFile);
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
