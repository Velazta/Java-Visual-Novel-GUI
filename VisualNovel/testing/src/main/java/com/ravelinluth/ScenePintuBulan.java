package com.ravelinluth;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class ScenePintuBulan extends JPanel {
    private int currentTextIndex = 0;
    private String[] storyTexts = {
            "Titoti memilih pintu dengan simbol bulan, memasuki lorong gelap yang dingin. Saat ia melangkah lebih jauh, kabut mulai menebal, dan kegelapan menjadi hampir mustahil ditembus, bahkan dengan senter kecil yang ia bawa.\nTiba-tiba, sebuah cahaya kecil muncul di kejauhan. Cahaya itu mendekat, memperlihatkan sosok makhluk kecil bersinar seperti kunang-kunang, tetapi dengan tubuh kecil dan berbulu.",
            "Pelem: “Hei! Kamu manusia, ya? Nama aku Pelem. Mau ke mana?\n Titoti: “Aku mencari jalan keluar. Tempat ini terlalu gelap.\n Pelem: “Hah, gampang! Aku tahu jalannya. Ayo ikut aku, tapi hati-hati, ada jebakan!",
            "Pelem melayang di depan, cahayanya menerangi jalan.\nPelem: “Stop! Jangan injak batu itu, jebakan! Lewat sini aja.\nTitoti: “Terima kasih, Pelem. Kamu hebat sekali.\nPelem: “Hehehe, biasa aja. Aku kan pintar!",
            "Saat mereka mendekati pintu keluar, gua mulai runtuh.\nPelem: “Itu pintunya! Cepat, aku jagain cahayanya!",
            "Setelah keluar, Pelem tersenyum.\nPelem: “Sampai sini aja ya. Gua ini rumahku. Jaga dirimu, manusia!\nTitoti: “Terima kasih, Pelem. Aku tidak akan melupakanmu!\nPelem melambai kecil sebelum menghilang ke dalam kegelapan."
    };

    private JButton nextButton;

    public ScenePintuBulan(MainFrame mainFrame) {
        // Gunakan JLayeredPane untuk mengelola lapisan
        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setPreferredSize(new Dimension(800, 600));
        setLayout(new BorderLayout());
        add(layeredPane, BorderLayout.CENTER);

        // Latar Belakang
        ImageIcon backgroundImage = new ImageIcon("src/main/java/Bg/PintuBulan.png");
        JLabel backgroundLabel = new JLabel(backgroundImage);
        backgroundLabel.setBounds(0, 0, 800, 600);
        layeredPane.add(backgroundLabel, Integer.valueOf(0)); // Lapisan bawah

        // Karakter (di atas background)
        ImageIcon characterImage = new ImageIcon("src/main/java/Bg/miniCreature.png");
        JLabel characterLabel = new JLabel();
        characterLabel.setBounds(200, 20, 600, 400); // Posisi lebih tinggi dan ukuran hampir penuh
        characterLabel.setOpaque(false);
        characterLabel.setIcon(characterImage);
        layeredPane.add(characterLabel, Integer.valueOf(1));

        // Panel Teks Justified
        JustifiedTextPanel storyTextPanel = new JustifiedTextPanel(storyTexts[currentTextIndex]);
        storyTextPanel.setBounds(50, 400, 700, 100);
        storyTextPanel.setOpaque(true);
        storyTextPanel.setForeground(Color.BLACK);
        storyTextPanel.setFont(new Font("Arial", Font.PLAIN, 18));
        layeredPane.add(storyTextPanel, Integer.valueOf(1)); // Lapisan atas

        // Tombol Next
        nextButton = new JButton("Next");
        nextButton.setBounds(300, 520, 200, 50);
        nextButton.setVisible(false);
        layeredPane.add(nextButton, Integer.valueOf(2));

        // Efek Pengetikan
        TypeWritterEffect effect = new TypeWritterEffect(storyTextPanel, storyTexts[currentTextIndex], 50);
        effect.setOnCompleteListener(() -> {
            if (currentTextIndex == storyTexts.length - 1) {
                nextButton.setVisible(true);
            }
        });
        effect.start();

        // Aksi Tombol Next
        nextButton.addActionListener((ActionEvent e) -> {
            currentTextIndex++;
            if (currentTextIndex < storyTexts.length) {
                nextButton.setVisible(false);
                storyTextPanel.setText(storyTexts[currentTextIndex]);
                effect.setText(storyTexts[currentTextIndex]);
                effect.start();
            } else {
                mainFrame.showScene("SceneJembatan");
            }
        });

        // Klik pada Panel untuk Melanjutkan
        storyTextPanel.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                if (effect.isRunning()) {
                    effect.skip();
                } else {
                    nextButton.doClick();
                }
            }
        });
    }
}
