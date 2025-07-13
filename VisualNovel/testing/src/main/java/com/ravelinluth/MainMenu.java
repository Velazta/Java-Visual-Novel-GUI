package com.ravelinluth;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenu extends JPanel {
    private JLabel backgroundLabel;
    private JLabel overlayLabel;
    private float alpha = 0.0f; // Transparency for fade effect
    private Timer fadeTimer; // Timer for fade effect

    public MainMenu(MainFrame mainFrame) {
        setLayout(null);

        // Tambahkan label judul
        JLabel titleLabel = new JLabel("Adventure of", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Anton", Font.BOLD, 50));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setBounds(100, 100, 600, 50);
        add(titleLabel);

        JLabel titleLabel2 = new JLabel("Titoti", SwingConstants.CENTER);
        titleLabel2.setFont(new Font("Anton", Font.BOLD, 80));
        titleLabel2.setForeground(new Color(255, 223, 0)); // Warna kuning
        titleLabel2.setBounds(100, 160, 600, 80);
        add(titleLabel2);

        // Tambahkan tombol Start
        JButton startButton = new JButton("Start Game");
        startButton.setBounds(300, 300, 200, 50);
        startButton.setBackground(Color.BLACK);
        startButton.setForeground(Color.WHITE);
        startButton.setFont(new Font("Arial", Font.BOLD, 20));
        add(startButton);

        // Listener tombol Start untuk memulai fade-out dan berpindah ke SceneManager
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startFadeOut(mainFrame);
            }
        });

        // Tambahkan tombol Exit
        JButton exitButton = new JButton("Exit");
        exitButton.setBounds(300, 370, 200, 50);
        exitButton.setBackground(new Color(139, 69, 19)); // Warna coklat
        exitButton.setForeground(Color.WHITE);
        exitButton.setFont(new Font("Arial", Font.BOLD, 20));
        add(exitButton);

        // Listener tombol Exit untuk keluar dari aplikasi
        exitButton.addActionListener(e -> System.exit(0));

        // Tambahkan latar belakang gambar
        backgroundLabel = new JLabel(new ImageIcon("src/main/java/Bg/BackgroundMenu.png")); // Ganti dengan path gambar
        backgroundLabel.setBounds(0, 0, 800, 600);
        add(backgroundLabel);

        // Tambahkan overlay untuk efek transparansi
        overlayLabel = new JLabel();
        overlayLabel.setOpaque(true);
        overlayLabel.setBackground(new Color(0, 0, 0, 0)); // Transparansi penuh di awal
        overlayLabel.setBounds(0, 0, 800, 600);
        add(overlayLabel);

        // Pastikan background berada di belakang semua komponen
        setComponentZOrder(backgroundLabel, getComponentCount() - 1);
        setComponentZOrder(overlayLabel, getComponentCount() - 2);
    }

    private void startFadeOut(MainFrame mainFrame) {
        fadeTimer = new Timer(30, null); // Timer untuk fade-out dengan interval 30ms
        fadeTimer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                alpha += 0.02f; // Tingkatkan alpha secara bertahap untuk efek fade-out
                if (alpha >= 1.0f) {
                    alpha = 1.0f;
                    fadeTimer.stop();
                    mainFrame.showScene("SceneManager"); // Berpindah ke SceneManager setelah fade selesai
                }
                overlayLabel.setBackground(new Color(0, 0, 0, (int) (alpha * 255)));
                repaint();
            }
        });
        fadeTimer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
    }
}