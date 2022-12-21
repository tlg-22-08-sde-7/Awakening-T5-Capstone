package com.awakening.ui.framework.gui;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.net.URL;

public class Sound {

    Clip clip;
    URL soundURL[] = new URL[30];

    public Sound() {
        soundURL[0] = getClass().getResource("resources/sound/titlescreen.wav");
        soundURL[1] = getClass().getResource("resources/sound/attack.wav");
        soundURL[2] = getClass().getResource("resources/sound/badcommand.wav");
        soundURL[3] = getClass().getResource("resources/sound/camera.wav");
        soundURL[4] = getClass().getResource("resources/sound/file.wav");
        soundURL[5] = getClass().getResource("resources/sound/ghost.wav");
        soundURL[6] = getClass().getResource("resources/sound/item.wav");
        soundURL[7] = getClass().getResource("resources/sound/losegame.wav");
        soundURL[8] = getClass().getResource("resources/sound/opendoor.wav");
        soundURL[9] = getClass().getResource("resources/sound/room.wav");
        soundURL[10] = getClass().getResource("resources/sound/room2.wav");
        soundURL[11] = getClass().getResource("resources/sound/room3.wav");
        soundURL[12] = getClass().getResource("resources/sound/useitem.wav");
        soundURL[13] = getClass().getResource("resources/sound/wingame.wav");
        soundURL[14] = getClass().getResource("resources/sound/wrongway.wav");

    }
    public void setFile(int i) {
        try {

            AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL[i]);
            clip = AudioSystem.getClip();
            clip.open(ais);

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

    /*public void playMusic(int i) {

        sound.setFile(i);
        sound.play();
        sound.loop();
    }

    public void stopMusic() {
        sound.stop();
    }

    public void playSE(int i) {
        sound.setFile(i);
        sound.play();
    }*/
}
