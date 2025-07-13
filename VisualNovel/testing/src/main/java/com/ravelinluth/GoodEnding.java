package com.ravelinluth;

import javax.swing.*;
import java.awt.*;

public class GoodEnding extends JPanel {
    private MainFrame mainFrame;
    private int currentTextIndex = 0;
    private String[] storyTexts = {
            "Titoti segera mengambil barang apa saja yang ada dalam tasnya, tanpa disadari ia mengambil sebuah seruling yang tadi diberikan oleh tuan monster. Dengan cepat, Titoti mengangkat seruling dan mulai memainkan melodi lembut.",
            "Suara seruling itu mengalun merdu, ular tersebut mulai berhenti bergerak mendengar melodi seruling yang dimainkan Titoti.",
            "Akhirnya Titoti berhasil mengalihkan perhatian ular tersebut. Titoti segera keluar dari goa tersebut dengan selamat sentosa."
    };

    public GoodEnding(MainFrame mainFrame) {
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
                // Tampilkan GOOD ENDING dan tombol EXIT setelah narasi selesai
                JLabel endingLabel = new JLabel("GOOD ENDING", SwingConstants.CENTER);
                endingLabel.setFont(new Font("Arial", Font.BOLD, 36));
                endingLabel.setForeground(Color.GREEN);
                endingLabel.setBounds(200, 200, 400, 50);
                layeredPane.add(endingLabel, Integer.valueOf(1));

                JButton mainMenuButton = new JButton("EXIT");
                mainMenuButton.setBounds(300, 520, 200, 50);
                layeredPane.add(mainMenuButton, Integer.valueOf(1));

                mainMenuButton.addActionListener(e -> System.exit(0));
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
