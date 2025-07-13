package com.ravelinluth;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class SceneJembatan extends JPanel {
    private int currentTextIndex = 0;
    private String[] storyTexts = {
            "Dari kejauhan terlihat secerca cahaya yang berkilau. Cahaya itu bergetar, seakan-akan memancarkan energi positif yang mengusir kegelapan di sekitarnya. Titoti merasa dorongan untuk bergerak menuju cahaya tersebut, seolah cahaya itu menawarkan harapan dan jalan keluar dari goa yang menakutkan.",
            "\"Cahaya apa itu? Apakah itu jalan keluarnya? Aku harus cepat keluar dari goa ini,\" kata Titoti sembari bergegas.",
            "Perlahan-lahan Titoti mulai mengikuti cahaya tersebut, berharap itu adalah harapan Titoti untuk keluar dari goa tersebut. Namun … ternyata tantangan semakin berlanjut karena Titoti tiba di sebuah jembatan rapuh yang membentang di atas jurang yang dalam. Dan saat melihat ke bawah, dia melihat sekumpulan ular yang sangat banyak memenuhi jurang tersebut.",
            "\"Akhh sialan, kenapa harus ada tantangan lagi sih!\" Titoti berseru. \"Aku harus segera menyebrangi jembatan ini sebelum goa ini runtuh.\"",
            "Jembatan bergetar saat Titoti melangkah perlahan, dan suara desisan ular semakin mendekat. Tiba-tiba jembatan itu runtuh dan membuat Titoti jatuh ke jurang tersebut.",
            "\"Uwaaah!!! Bruk!\" Titoti berteriak saat jatuh.",
            "Titoti berusaha berdiri. Dia melihat ular-ular merayap mendekat. Titoti pun panik karena seekor ular mulai melingkar di sekelilingnya."
    };

    private JButton leftButton;
    private JButton rightButton;

    public SceneJembatan(MainFrame mainFrame) {
        // Gunakan JLayeredPane untuk mengelola lapisan
        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setPreferredSize(new Dimension(800, 600));
        setLayout(new BorderLayout());
        add(layeredPane, BorderLayout.CENTER);

        // Latar Belakang
        ImageIcon backgroundImage = new ImageIcon("src/main/java/Bg/Jembatan.png");
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

        // Tombol Kiri
        leftButton = new JButton("Main Seruling");
        leftButton.setBounds(200, 520, 150, 50);
        leftButton.setVisible(false);
        layeredPane.add(leftButton, Integer.valueOf(2));

        // Tombol Kanan
        rightButton = new JButton("Melawan Ular");
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
        leftButton.addActionListener((ActionEvent e) -> mainFrame.showScene("GoodEnding"));

        // Event untuk Tombol Kanan
        rightButton.addActionListener((ActionEvent e) -> mainFrame.showScene("Ending4"));

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