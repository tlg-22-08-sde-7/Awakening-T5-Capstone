package com.awakening.app;

import javax.swing.*;
import javax.swing.plaf.metal.MetalBorders;
import java.awt.*;

class GameGUI {
    int winX = 1200;
    int winY = 800;
    JFrame window;
    Container container;
    JLabel backgroundPicture;
    ImageIcon titleBackgroundImg;
    JButton startButton, instructionButton, quitButton;
    // customized font
    Font titleFont = new Font("Times new Roman", Font.PLAIN, 90);

    public static void main(String[] args) {
        new GameGUI();
    }

    public GameGUI() {
        // Title Background Image
        titleBackgroundImg = new ImageIcon("resources\\pictures\\titlescreen.jpg");
        backgroundPicture = new JLabel(titleBackgroundImg);
        backgroundPicture.setSize(winX, winY);

        // Window
        window = new JFrame();
        window.setSize(winX, winY);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.getContentPane().setBackground(Color.black);
        window.setTitle("Awakening");
        window.setLayout(null);
        window.setVisible(true);
        window.setResizable(false);

        container = window.getContentPane();
        container.add(backgroundPicture);

        //Start Game Button
        startButton = new JButton("New Game");
        startButton.setBounds(winX / 2 - 200, 550, 150, 50);
        startButton.setBorder(new MetalBorders.MenuItemBorder());
        startButton.setFont(new Font("Times new Roman", Font.BOLD, 20));

        // Instruction Button
        instructionButton = new JButton("Instructions");
        instructionButton.setBounds(winX / 2, 550, 150, 50);
        instructionButton.setBorder(new MetalBorders.MenuItemBorder());
        instructionButton.setFont(new Font("Times new Roman", Font.BOLD, 20));

        // Quit Game Button
        quitButton = new JButton("Quit");
        quitButton.setBounds(winX - 100, 25, 50, 25);
        quitButton.setBorder(new MetalBorders.MenuItemBorder());
        quitButton.setFont(new Font("Times new Roman", Font.BOLD, 20));

        backgroundPicture.add(startButton);
        backgroundPicture.add(instructionButton);
        backgroundPicture.add(quitButton);

    }

    private void itemsSizing(int x, int y, String file) {
        ImageIcon icon = new ImageIcon(new ImageIcon(file)
                .getImage().getScaledInstance(winY / 15, winX / 15, Image.SCALE_DEFAULT));
        JLabel items = new JLabel(icon);
        items.setBounds(x, y, 75, 75);
        items.setIcon(icon);
        backgroundPicture.add(items);
    }
}
