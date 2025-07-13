package com.ravelinluth;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class ScenePintuMatahari extends JPanel {
    private int currentTextIndex = 0;
    private String[] storyTexts = {
            "Titoti memilih pintu dengan simbol matahari, meyakini bahwa terang adalah jalan yang benar. Saat pintu terbuka, ia mendapati lorong terang benderang. Namun, ketika ia melangkah masuk, sinar terang berubah menjadi panas yang menyengat. Lorong mulai runtuh, mengarah ke jebakan mematikan. Titoti tidak memiliki waktu untuk mundur, dan akhirnya, jalan itu menelan dirinya."

    };

    private JButton leftButton;

    public ScenePintuMatahari(MainFrame mainFrame) {
        // Gunakan JLayeredPane untuk mengelola lapisan
        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setPreferredSize(new Dimension(800, 600));
        setLayout(new BorderLayout());
        add(layeredPane, BorderLayout.CENTER);

        // Latar Belakang
        JPanel backgroundPanel = new JPanel();
        backgroundPanel.setBackground(Color.BLACK);
        backgroundPanel.setBounds(0, 0, 800, 600);
        layeredPane.add(backgroundPanel, Integer.valueOf(0)); // Lapisan bawah

        // Panel Teks Justified
        JustifiedTextPanel storyTextPanel = new JustifiedTextPanel(storyTexts[currentTextIndex]);
        storyTextPanel.setBounds(50, 400, 700, 100);
        layeredPane.add(storyTextPanel, Integer.valueOf(1)); // Lapisan atas

        // Nama Karakter
        JLabel nameLabel = new JLabel("Narator");
        nameLabel.setFont(new Font("Arial", Font.BOLD, 24));
        nameLabel.setForeground(Color.WHITE);
        nameLabel.setBounds(50, 350, 200, 30);
        layeredPane.add(nameLabel, Integer.valueOf(1)); // Lapisan atas

        // Tombol Kiri
        leftButton = new JButton("Next");
        leftButton.setBounds(300, 520, 200, 50);
        leftButton.setVisible(false);
        layeredPane.add(leftButton, Integer.valueOf(2));

        // Efek Pengetikan
        TypeWritterEffect effect = new TypeWritterEffect(storyTextPanel, storyTexts[currentTextIndex], 50);
        effect.setOnCompleteListener(() -> {
            if (currentTextIndex == storyTexts.length - 1) { // Hanya munculkan tombol jika teks terakhir selesai
                leftButton.setVisible(true);
            }
        });

        effect.start();

        // Event untuk Tombol Kiri
        leftButton.addActionListener((ActionEvent e) -> mainFrame.showScene("EndingMati"));
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
                        storyTextPanel.setText(storyTexts[currentTextIndex]);
                        effect.setText(storyTexts[currentTextIndex]);
                        effect.start();
                    }
                }
            }
        });
    }
}
