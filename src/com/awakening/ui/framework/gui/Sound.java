package com.awakening.ui.framework.gui;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

public class Sound {

    Clip clip;
    URL[] soundURL = new URL[30];
    float previousVolume = 0;
    float currentVolume = 0;
    FloatControl fc;
    boolean mute = false;
    public int volumeScale = 2;
    float volume;

    public Sound() {
        soundURL[0] = Sound.class.getResource("/sound/titlescreen.wav");
        soundURL[1] = Sound.class.getResource("/sound/attack.wav");
        soundURL[2] = Sound.class.getResource("/sound/badcommand.wav");
        soundURL[3] = Sound.class.getResource("/sound/camera.wav");
        soundURL[4] = Sound.class.getResource("/sound/file.wav");
        soundURL[5] = Sound.class.getResource("/sound/ghost.wav");
        soundURL[6] = Sound.class.getResource("/sound/item.wav");
        soundURL[7] = Sound.class.getResource("/sound/losegame.wav");
        soundURL[8] = Sound.class.getResource("/sound/opendoor.wav");
        soundURL[9] = Sound.class.getResource("/sound/room.wav");
        soundURL[10] = Sound.class.getResource("/sound/room2.wav");
        soundURL[11] = Sound.class.getResource("/sound/room3.wav");
        soundURL[12] = Sound.class.getResource("/sound/wingame.wav");
        soundURL[13] = Sound.class.getResource("/sound/bat.wav");
    }

    public void setFile(int i) {
        try {

            AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL[i]);
            clip = AudioSystem.getClip();
            clip.open(ais);
            fc = (FloatControl)clip.getControl(FloatControl.Type.MASTER_GAIN);
            checkVolume();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void play() {
        clip.start();
    }

    public void loop() {
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }

    public void stop() {
        clip.stop();
    }

    public void playMusic(int i) {

        setFile(i);
        play();
        loop();
    }

    public void stopMusic() {
        stop();
    }

    public void playSE(int i) {
        setFile(i);
        play();
    }
    public void checkVolume() {
        switch (volumeScale) {
            case 0: volume = -80f; break;
            case 1: volume = -20f; break;
            case 2: volume = -12f; break;
            case 3: volume = -5f; break;
            case 4: volume = 1f; break;
            case 5: volume = 6f; break;
        }
        fc.setValue(volume);
    }
}
