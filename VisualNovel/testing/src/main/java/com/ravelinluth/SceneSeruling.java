package com.ravelinluth;

import javax.swing.*;
import java.awt.*;

public class SceneSeruling extends JPanel {
    private int currentTextIndex = 0;
    private String[] storyTexts = {
            "Titoti segera mengambil barang apa saja yang ada dalam tasnya, tanpa disadari ia mengambil sebuah seruling yang tadi diberikan oleh tuan monster.\n",
            "Dengan cepat, Titoti mengangkat seruling dan mulai memainkan melodi lembut. Suara seruling itu mengalun merdu, ular tersebut mulai berhenti bergerak mendengar melodi seruling yang dimainkan titoti. \n",
            "Akhirnya titoti berhasil mengalihkan perhatian ular tersebut titoti segera keluar dari goa tersebut dengan selamat sentosa\n"
    };


    public SceneSeruling(MainFrame mainFrame) {
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

        nextButton.addActionListener(e -> mainFrame.showScene("GoodEnding"));

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

