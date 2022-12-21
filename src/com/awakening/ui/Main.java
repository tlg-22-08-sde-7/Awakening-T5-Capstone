package com.awakening.ui;

import com.awakening.ui.framework.Engine;
import com.awakening.ui.framework.resources.Loader;

import javax.swing.*;

class Main {
    public static void main(String[] args) {
        //creating game thread
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                // load images, data from json files
                Loader.load();
                Engine.init();
                Engine.start();
            }
        });
    }
}
