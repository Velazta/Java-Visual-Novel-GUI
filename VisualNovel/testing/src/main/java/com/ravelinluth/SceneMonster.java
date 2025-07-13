package com.ravelinluth;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class SceneMonster extends JPanel {
    private int currentTextIndex = 0;
    private String[] storyTexts = {
            "Titoti akhirnya memilih berjalan ke lorong kanan.",
            "Titoti berlari sekencang mungkin mencari jalan keluar dari goa terkutuk tersebut. Gelap gulita menyelimuti lorong gua tersebut.",
            "Tiba-tiba Titoti dihentikan oleh monster arya. Tanpa disadari, ia sudah mengusik monster yang sedang tidur dan tidak sengaja diinjak Titoti.",
            "Monster : \"Hey pemuda sialan, berani sekali kau mengganggu daku yang sedang tidur\"",
            "Titoti  : \"Tunggu! Aku tidak bermaksud mengganggumu! Aku hanya tersesat!\"",
            "Monster : \"Bodo amat, kau kira aku peduli, hellow~. Aku lapar sekali, keliatannya dagingmu enak\"",
            "Titoti  : \"Tu, tunggu jangan membunuhku, sumpah dagingku tidak enak\"",
            "Monster : \"Masa bodo! rawr\"",
            "Dengan sangat cepat monster tersebut langsung ingin menerkam Titoti dengan sangat ganas"
    };

    private JButton leftButton;
    private JButton rightButton;
    private JLabel monsterLabel;

    public SceneMonster(MainFrame mainFrame) {
        // Gunakan JLayeredPane untuk mengelola lapisan
        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setPreferredSize(new Dimension(800, 600));
        setLayout(new BorderLayout());
        add(layeredPane, BorderLayout.CENTER);

        // Latar Belakang
        ImageIcon backgroundImage = new ImageIcon("src/main/java/Bg/LorongKanan.png");
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

        // Label untuk karakter monster
        monsterLabel = new JLabel(new ImageIcon("src/main/java/Characters/MonsterArya.png"));
        monsterLabel.setBounds(550, 250, 200, 300); // Posisi dan ukuran gambar karakter
        monsterLabel.setVisible(false); // Sembunyikan di awal
        layeredPane.add(monsterLabel, Integer.valueOf(1));

        // Tombol Kiri
        leftButton = new JButton("Berlari");
        leftButton.setBounds(200, 520, 150, 50);
        leftButton.setVisible(false);
        layeredPane.add(leftButton, Integer.valueOf(2));

        // Tombol Kanan
        rightButton = new JButton("Kasih Makanan");
        rightButton.setBounds(450, 520, 150, 50);
        rightButton.setVisible(false);
        layeredPane.add(rightButton, Integer.valueOf(2));

        // Efek Pengetikan
        TypeWritterEffect effect = new TypeWritterEffect(storyTextPanel, storyTexts[currentTextIndex], 50);
        effect.setOnCompleteListener(() -> {
            if (currentTextIndex == storyTexts.length - 1) { // Hanya munculkan tombol jika teks terakhir selesai
                leftButton.setVisible(true);
                rightButton.setVisible(true);
            } else if (currentTextIndex == 2) { // Munculkan monster setelah teks ketiga selesai
                monsterLabel.setVisible(true);
                nameLabel.setText("");
            } else {
                nameLabel.setText("");
            }
        });

        effect.start();

        // Event untuk Tombol Kiri
        leftButton.addActionListener((ActionEvent e) -> mainFrame.showScene("EndingMati"));

        // Event untuk Tombol Kanan
        rightButton.addActionListener((ActionEvent e) -> mainFrame.showScene("MakanMonster"));

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
