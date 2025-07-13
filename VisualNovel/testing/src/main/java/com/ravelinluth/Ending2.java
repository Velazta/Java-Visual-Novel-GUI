package com.ravelinluth;

import javax.swing.*;
import java.awt.*;

public class Ending2 extends JPanel {
    private MainFrame mainFrame;
    private int currentTextIndex = 0;
    private String[] storyTexts = {
            "Pria tua\t: “Tak apa jika tak ingin memberikanku makanan, aku punya sesuatu yang membantumu agar bisa keluar dari gua ini dengan selamat.”\n",
            "Titoti pun melanjutkan perjalanan. Akan tetapi, peta yang diberikan tidak menunjukkan jalan keluar dan Titoti berputar-putar di gua selama seminggu hingga berakhir meninggal karena kelaparan.\n"
    };

    public Ending2(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
        initializeUI();
    }

    private void initializeUI() {
        removeAll();
        setLayout(new BorderLayout());

        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setPreferredSize(new Dimension(800, 600));
        add(layeredPane, BorderLayout.CENTER);

        JPanel backgroundPanel = new JPanel();
        backgroundPanel.setBackground(Color.BLACK);
        backgroundPanel.setBounds(0, 0, 800, 600);
        layeredPane.add(backgroundPanel, Integer.valueOf(0));

        JustifiedTextPanel storyTextPanel = new JustifiedTextPanel(storyTexts[currentTextIndex]);
        storyTextPanel.setBounds(50, 400, 700, 100);
        storyTextPanel.setOpaque(false);
        storyTextPanel.setForeground(Color.WHITE);
        storyTextPanel.setFont(new Font("Arial", Font.PLAIN, 18));
        layeredPane.add(storyTextPanel, Integer.valueOf(1));

        TypeWritterEffect effect = new TypeWritterEffect(storyTextPanel, storyTexts[currentTextIndex], 50);
        effect.setOnCompleteListener(() -> {
            if (currentTextIndex == storyTexts.length - 1) {
                // Tampilkan "ENDING TERJEBAK" dan tombol EXIT setelah narasi selesai
                JLabel endingLabel = new JLabel("ENDING TERJEBAK", SwingConstants.CENTER);
                endingLabel.setFont(new Font("Arial", Font.BOLD, 36));
                endingLabel.setForeground(Color.RED);
                endingLabel.setBounds(200, 200, 400, 50);
                layeredPane.add(endingLabel, Integer.valueOf(1));

                JButton exitButton = new JButton("EXIT");
                exitButton.setBounds(300, 520, 200, 50);
                layeredPane.add(exitButton, Integer.valueOf(1));

                exitButton.addActionListener(e -> System.exit(0));
                layeredPane.repaint();
            }
        });

        effect.start();

        storyTextPanel.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                if (effect.isRunning()) {
                    effect.skip();
                } else {
                    currentTextIndex++;
                    if (currentTextIndex < storyTexts.length) {
                        effect.setText(storyTexts[currentTextIndex]);
                        effect.start();
                    }
                }
            }
        });
    }
}
