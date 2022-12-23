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

    static Clip clip;
    static URL[] soundURL = new URL[30];
    static float previousVolume = 0;
    static float currentVolume = 0;
    static FloatControl fc;
    static boolean mute = false;

    static{
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
    }

    public static void setFile(int i) {
        try {

            AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL[i]);
            clip = AudioSystem.getClip();
            clip.open(ais);
            fc = (FloatControl)clip.getControl(FloatControl.Type.MASTER_GAIN);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void play() {
        clip.start();
    }

    public static void loop() {
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }

    public static void stop() {
        clip.stop();
    }

    public static void playMusic(int i) {

        setFile(i);
        play();
        loop();
    }

    public static void stopMusic() {
        stop();
    }

    public static void playSE(int i) {
        setFile(i);
        play();
    }

    // Volume Control
    public static JPanel volumeControl() {
        JPanel adjustSound = new JPanel();
        adjustSound.setLayout(new GridLayout(1, 3));

        JButton volumeUpB = new JButton("Volume Up");
        volumeUpB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                volumeUp();
            }
        });
        adjustSound.add(volumeUpB);

        JButton volumeDownB = new JButton("Volume Down");
        volumeDownB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                volumeDown();
            }
        });
        adjustSound.add(volumeDownB);

        JButton muteB = new JButton("Mute");
        muteB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                volumeMute();
            }
        });

        adjustSound.add(muteB);
        adjustSound.setVisible(true);
        return adjustSound;
    }
    public static void volumeUp() {
        currentVolume += 1.0f;
        if (currentVolume > 6.0f) {
            currentVolume = 6.0f;
        }
        fc.setValue(currentVolume);
    }
    public static void volumeDown() {
        currentVolume -= 1.0f;
        if (currentVolume < -80.0f) {
            currentVolume = -80.0f;
        }
        fc.setValue(currentVolume);
    }
    public static void volumeMute() {
        if (!mute) {
            previousVolume = currentVolume;
            currentVolume = -80.0f;
            fc.setValue(currentVolume);
            mute = true;
        }
        else {
            currentVolume = previousVolume;
            fc.setValue(currentVolume);
            mute = false;
        }
    }
}
