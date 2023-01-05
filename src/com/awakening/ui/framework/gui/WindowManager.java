package com.awakening.ui.framework.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyListener;

public class WindowManager {

    private JFrame frame;
    private JPanel panel;

    public static int WIDTH = 1200;
    public static int HEIGHT = 675;

    public WindowManager() {
        frame = new JFrame("Awakening");
        frame.setBounds(70, 70, 0, 0);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        ImageIcon img = new ImageIcon("resources/pictures.ghost/ghost4.png");
        frame.setIconImage(img.getImage());
    }

    public void addPanel(JPanel panel) {
        this.panel = panel;
        this.panel.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        this.panel.setFocusable(true);
        this.panel.requestFocusInWindow();
    }

    public void addKeyListener(KeyListener listener) {
        try {
            this.panel.addKeyListener(listener);
        } catch (NullPointerException e) {
            System.err.println("[WindowManager]: Error! Tried to add KeyListener before JPanel");
            System.exit(-1);
        }
    }

    public void createWindow() {
        this.frame.setContentPane(panel);
        this.frame.pack();
        this.frame.setVisible(true);
    }
}
