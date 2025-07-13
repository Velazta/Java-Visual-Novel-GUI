package com.ravelinluth;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class MemberiMakanMonster extends JPanel {
    private int currentTextIndex = 0;
    private String[] storyTexts = {
            "Titoti menjulurkan bekal makan siangnya ke arah monster, berharap makanan dapat meredakan kemarahan monster.",
            "Monster itu terhenti, matanya yang menyala merah menatap makanan dengan rasa ingin tahu. Suasana tegang di antara mereka berdua seolah melambat.",
            "Titoti: \"Tolong jangan makan aku, makan saja sandwich buatan makku ini, dagingku tidak lezat.\"",
            "Monster: \"Apa ini? Aku tidak pernah memakannya.\"",
            "[Monster tersebut dengan lahap memakan sandwich itu tanpa pikir panjang.]",
            "Monster: \"Anjir enak banget ini, hey manusia apa kau masih memiliki makanan itu?\"",
            "Titoti: \"Tidak wahai tuan monster, tapi aku berjanji akan membawakan makanan ini lagi jika kau membiarkanku pergi.\"",
            "Monster: \"Baiklah aku akan membiarkanmu pergi, tapi berjanjilah membawakanku makanan lezat itu lagi.\"",
            "Titoti: \"Oke, terima kasih tuan monster.\"",
            "Monster: \"Karena kau telah memberikan aku sandwich yang sangat lezat ini, terimalah seruling ini. Siapa tahu ini bisa membantumu.\"",
            "[Dengan wajah penuh tanda tanya, Titoti menerima seruling itu dan melangkah pergi. Monster itu, dengan tatapan penuh misteri, akhirnya membiarkan Titoti pergi dengan selamat.]"
    };

    private JButton nextButton;
    private JLabel CharLabel;

    public MemberiMakanMonster(MainFrame mainFrame) {
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

        CharLabel = new JLabel(new ImageIcon("src/main/java/Characters/MonsterArya.png"));
        CharLabel.setBounds(550, 250, 200, 300); // Posisi dan ukuran gambar karakter
        CharLabel.setVisible(false); // Sembunyikan di awal
        layeredPane.add(CharLabel, Integer.valueOf(1));

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
                mainFrame.showScene("ScenePintu");
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