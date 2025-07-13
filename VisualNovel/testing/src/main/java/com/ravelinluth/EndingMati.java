package com.ravelinluth;

import javax.swing.*;
import java.awt.*;

public class EndingMati extends JPanel {
    private MainFrame mainFrame;

    public EndingMati(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
        initializeUI();
    }

    private void initializeUI() {
        removeAll();
        setLayout(new BorderLayout());

        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setPreferredSize(new Dimension(800, 600));
        add(layeredPane, BorderLayout.CENTER);

        JPanel backgroundPanel = new JPanel();
        backgroundPanel.setBackground(Color.BLACK);
        backgroundPanel.setBounds(0, 0, 800, 600);
        layeredPane.add(backgroundPanel, Integer.valueOf(0));

        JustifiedTextPanel storyTextPanel = new JustifiedTextPanel("Titoti segera melarikan diri dengan sangat kencang, menghindari amukan monster itu, monster itu pun dengan sigap mengejar titoti dan akhirnya titoti tertangkap dan meninggal dimakan monster.\n");
        storyTextPanel.setBounds(50, 400, 700, 100);
        storyTextPanel.setOpaque(false);
        storyTextPanel.setForeground(Color.WHITE);
        storyTextPanel.setFont(new Font("Arial", Font.PLAIN, 18));
        layeredPane.add(storyTextPanel, Integer.valueOf(1));

        TypeWritterEffect effect = new TypeWritterEffect(storyTextPanel, "Titoti segera melarikan diri dengan sangat kencang, menghindari amukan monster itu, monster itu pun dengan sigap mengejar titoti dan akhirnya titoti tertangkap dan meninggal dimakan monster.\n", 50);
        effect.start();

        JLabel endingLabel = new JLabel("EAT ENDING", SwingConstants.CENTER);
        endingLabel.setFont(new Font("Arial", Font.BOLD, 36));
        endingLabel.setForeground(Color.RED);
        endingLabel.setBounds(200, 200, 400, 50);
        layeredPane.add(endingLabel, Integer.valueOf(1));

        JButton mainMenuButton = new JButton("EXIT");
        mainMenuButton.setBounds(300, 520, 200, 50);
        layeredPane.add(mainMenuButton, Integer.valueOf(1));

        mainMenuButton.addActionListener(e -> System.exit(0));
    }
}
