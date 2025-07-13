package com.ravelinluth;

import javax.swing.*;
import java.awt.*;

public class SceneUlar extends JPanel {
    private int currentTextIndex = 0;
    private String[] storyTexts = {
            "Dengan sekuat tenaga, Titoti menghadang ular-ular tersebut hanya dengan pisau kecil yang dibawanya. Walaupun serangan ular terus-menerus menerjang Titoti tetap melawan mereka dan berhasil selamat.\n",
            "Titoti segera mencari jalan keluar dari jurang dengan tubuh yang penuh luka dan racun. Setelah terlihat suatu jalan, sayang sekali perjalanan Titoti harus terhenti karena kesadarannya hilang akibat bisa ular sudah menyebar di tubuhnya.\n."
    };


    public SceneUlar(MainFrame mainFrame) {
        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setPreferredSize(new Dimension(800, 600));
        setLayout(new BorderLayout());
        add(layeredPane, BorderLayout.CENTER);

        // Latar Belakang
        ImageIcon backgroundImage = new ImageIcon("src/main/java/Bg/");
        JLabel backgroundLabel = new JLabel(backgroundImage);
        backgroundLabel.setBounds(0, 0, 800, 600);
        layeredPane.add(backgroundLabel, Integer.valueOf(0)); // Lapisan bawah

        // Panel Teks Justified
        JustifiedTextPanel storyTextPanel = new JustifiedTextPanel(storyTexts[currentTextIndex]);
        storyTextPanel.setBounds(50, 400, 700, 100);
        layeredPane.add(storyTextPanel, Integer.valueOf(1)); // Lapisan atas

        // Karakter (di atas background)
        ImageIcon characterImage = new ImageIcon("miniCreature.png");
        JLabel characterLabel = new JLabel();
        characterLabel.setBounds(200, 20, 600, 400); // Posisi lebih tinggi dan ukuran hampir penuh
        characterLabel.setOpaque(false);
        characterLabel.setIcon(characterImage);
        layeredPane.add(characterLabel, Integer.valueOf(1));

        // Nama Karakter
        JLabel nameLabel = new JLabel("Narator");
        nameLabel.setFont(new Font("Arial", Font.BOLD, 24));
        nameLabel.setForeground(Color.WHITE);
        nameLabel.setBounds(50, 350, 200, 30);
        layeredPane.add(nameLabel, Integer.valueOf(1)); // Lapisan atas

        // Tombol menuju SceneJembatan
        JButton nextButton = new JButton("Next");
        nextButton.setBounds(300, 520, 200, 50);
        nextButton.setVisible(false);
        layeredPane.add(nextButton, Integer.valueOf(2)); // Lapisan atas

        // Efek Pengetikan
        TypeWritterEffect effect = new TypeWritterEffect(storyTextPanel, storyTexts[currentTextIndex], 50);
        effect.setOnCompleteListener(() -> {
            // Ketika teks selesai diketik, tombol muncul
            nextButton.setVisible(true);
        });

        effect.start();

        nextButton.addActionListener(e -> mainFrame.showScene("Ending4"));

        storyTextPanel.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                if (effect.isRunning()) {
                    effect.skip();
                } else {
                    currentTextIndex++;
                    if (currentTextIndex < storyTexts.length) {
                        nextButton.setVisible(false);
                        storyTextPanel.setText(storyTexts[currentTextIndex]);
                        effect.setText(storyTexts[currentTextIndex]);
                        effect.start();
                    }
                }
            }
        });
    }
}

