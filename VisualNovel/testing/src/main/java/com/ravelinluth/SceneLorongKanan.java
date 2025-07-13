package com.ravelinluth;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class SceneLorongKanan extends JPanel {
    private int currentTextIndex = 0;
    private String[] storyTexts = {
            "Titoti tetap mengambil artefak tersebut dan menghadapi kekacauan. Lorong-lorong berubah bentuk, membuat peta yang dibawanya tidak lagi berguna.",
            "Di tengah kekacauan, sebuah suara misterius terdengar, memperingatkannya untuk berhati-hati. Kini, Titoti dihadapkan pada dua pilihan jalan di depan: Lorong Kiri atau Lorong Kanan."
    };

    private JButton leftButton;
    private JButton rightButton;

    public SceneLorongKanan(MainFrame mainFrame) {
        // Gunakan JLayeredPane untuk mengelola lapisan
        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setPreferredSize(new Dimension(800, 600));
        setLayout(new BorderLayout());
        add(layeredPane, BorderLayout.CENTER);

        // Latar Belakang
        ImageIcon backgroundImage = new ImageIcon("src/main/java/Bg/2path.png");
        JLabel backgroundLabel = new JLabel(backgroundImage);
        backgroundLabel.setBounds(0, 0, 800, 600);
        layeredPane.add(backgroundLabel, Integer.valueOf(0)); // Lapisan bawah

        // Panel Teks Justified
        JustifiedTextPanel storyTextPanel = new JustifiedTextPanel(storyTexts[currentTextIndex]);
        storyTextPanel.setBounds(50, 400, 700, 100);
        layeredPane.add(storyTextPanel, Integer.valueOf(1)); // Lapisan atas

        // Tombol Kiri
        leftButton = new JButton("Lorong Kiri");
        leftButton.setBounds(200, 520, 150, 50);
        leftButton.setVisible(false);
        layeredPane.add(leftButton, Integer.valueOf(2));

        // Tombol Kanan
        rightButton = new JButton("Lorong Kanan");
        rightButton.setBounds(450, 520, 150, 50);
        rightButton.setVisible(false);
        layeredPane.add(rightButton, Integer.valueOf(2));

        // Efek Pengetikan
        TypeWritterEffect effect = new TypeWritterEffect(storyTextPanel, storyTexts[currentTextIndex], 50);
        effect.setOnCompleteListener(() -> {
            if (currentTextIndex == storyTexts.length - 1) { // Hanya munculkan tombol jika teks terakhir selesai
                leftButton.setVisible(true);
                rightButton.setVisible(true);
            }
        });

        effect.start();

        // Event untuk Tombol Kiri
        leftButton.addActionListener((ActionEvent e) -> mainFrame.showScene("SceneLorongKiri"));

        // Event untuk Tombol Kanan
        rightButton.addActionListener((ActionEvent e) -> mainFrame.showScene("SceneMonster"));

        // Event untuk Klik pada Teks Cerita
        storyTextPanel.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                if (effect.isRunning()) {
                    effect.skip();
                } else {
                    currentTextIndex++;
                    if (currentTextIndex < storyTexts.length) {
                        leftButton.setVisible(false);
                        rightButton.setVisible(false);
                        storyTextPanel.setText(storyTexts[currentTextIndex]);
                        effect.setText(storyTexts[currentTextIndex]);
                        effect.start();
                    }
                }
            }
        });
    }
}