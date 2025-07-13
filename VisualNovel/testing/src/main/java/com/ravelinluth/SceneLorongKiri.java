package com.ravelinluth;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class SceneLorongKiri extends JPanel {
    private int currentTextIndex = 0;
    private String[] storyTexts = {
            "Titoti berjalan di lorong kiri. Saat berada di lorong kiri, Titoti mendengar suara gemerisik di balik semak-semak. Titoti berhenti dan memeriksa sumber suara tersebut",
            "Titoti menemukan seorang pria tua dengan pakaian compang-camping, Titoti kebingungan dengan kakek-kakek tersebut.\n",
            "Pria tua: “Tunggu anak muda! Aku dangat kelaparan, bolehkah kamu memberikanku sedikit makanan milikmu?\n"
    };

    private JButton leftButton;
    private JButton rightButton;
    private JLabel characterLabel;

    public SceneLorongKiri(MainFrame mainFrame) {
        // Gunakan JLayeredPane untuk mengelola lapisan
        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setPreferredSize(new Dimension(800, 600));
        setLayout(new BorderLayout());
        add(layeredPane, BorderLayout.CENTER);

        // Latar Belakang
        ImageIcon backgroundImage = new ImageIcon("src/main/java/Bg/LorongKiri.png");
        JLabel backgroundLabel = new JLabel(backgroundImage);
        backgroundLabel.setBounds(0, 0, 800, 600);
        layeredPane.add(backgroundLabel, Integer.valueOf(0)); // Lapisan bawah

        // Panel Teks Justified
        JustifiedTextPanel storyTextPanel = new JustifiedTextPanel(storyTexts[currentTextIndex]);
        storyTextPanel.setBounds(50, 400, 700, 100);
        layeredPane.add(storyTextPanel, Integer.valueOf(1)); // Lapisan atas

        // Nama Karakter
        JLabel nameLabel = new JLabel("");
        nameLabel.setFont(new Font("Arial", Font.BOLD, 24));
        nameLabel.setForeground(Color.WHITE);
        nameLabel.setBounds(50, 350, 200, 30);
        layeredPane.add(nameLabel, Integer.valueOf(1)); // Lapisan atas

        // Label untuk karakter (gambar "Kakek Tua")
        characterLabel = new JLabel(new ImageIcon("src/main/java/Character/PriaTua.png"));
        characterLabel.setBounds(100, 50, 800, 600); // Posisi dan ukuran gambar karakter
        characterLabel.setVisible(false); // Sembunyikan di awal
        layeredPane.add(characterLabel, Integer.valueOf(1));

        // Tombol Kiri
        leftButton = new JButton("Beri Bekal");
        leftButton.setBounds(200, 520, 150, 50);
        leftButton.setVisible(false);
        layeredPane.add(leftButton, Integer.valueOf(2));

        // Tombol Kanan
        rightButton = new JButton("Hiraukan");
        rightButton.setBounds(450, 520, 150, 50);
        rightButton.setVisible(false);
        layeredPane.add(rightButton, Integer.valueOf(2));

        // Efek Pengetikan
        TypeWritterEffect effect = new TypeWritterEffect(storyTextPanel, storyTexts[currentTextIndex], 50);
        effect.setOnCompleteListener(() -> {
            if (currentTextIndex == storyTexts.length - 1) { // Hanya munculkan tombol jika teks terakhir selesai
                leftButton.setVisible(true);
                rightButton.setVisible(true);
                characterLabel.setVisible(true);
                nameLabel.setText("Kakek Tua");

            }
        });

        effect.start();

        // Event untuk Tombol Kiri
        leftButton.addActionListener((ActionEvent e) -> mainFrame.showScene("EndingCheat"));

        // Event untuk Tombol Kanan
        rightButton.addActionListener((ActionEvent e) -> mainFrame.showScene("EndingTerjebak"));

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