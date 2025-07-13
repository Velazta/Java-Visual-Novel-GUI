package com.ravelinluth;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ScenePintu extends JPanel {
    private int currentTextIndex = 0;
    private String[] storyTexts = {
            "Titoti tiba di sebuah ruangan besar dengan dua pintu batu besar di depannya. Di atas masing-masing pintu, terdapat simbol kuno yang berkilauan samar. Salah satu pintu adalah jalan keluar, sementara yang lain membawa kematian.",
            "Deskripsi Pintu 1:Simbol matahari dengan sinar memancar. \nDi bawahnya ada tulisan kuno yang diartikan Titoti sebagai: “Terang mengungkap kebenaran. \n Deskripsi Pintu 2: Simbol bulan sabit di bawah awan gelap. \nDi bawahnya tertulis: “Kegelapan menyembunyikan keselamatan.\nPilih pintu yang tepat demi keselamatan Titoti"
    };

    private JButton leftButton;
    private JButton rightButton;

    public ScenePintu(MainFrame mainFrame) {
        // Gunakan JLayeredPane untuk mengelola lapisan
        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setPreferredSize(new Dimension(800, 600));
        setLayout(new BorderLayout());
        add(layeredPane, BorderLayout.CENTER);

        // Latar Belakang
        ImageIcon backgroundImage = new ImageIcon("src/main/java/Bg/pintu.png");
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
        leftButton = new JButton("Pintu Bulan");
        leftButton.setBounds(200, 520, 150, 50);
        leftButton.setVisible(false);
        layeredPane.add(leftButton, Integer.valueOf(2));

        // Tombol Kanan
        rightButton = new JButton("Pintu Matahari");
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
        leftButton.addActionListener((ActionEvent e) -> mainFrame.showScene("ScenePintuBulan"));

        // Event untuk Tombol Kanan
        rightButton.addActionListener((ActionEvent e) -> mainFrame.showScene("ScenePintuMatahari"));

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

