package com.ravelinluth;

import javax.swing.*;
import java.awt.*;

public class Ending1 extends JPanel {
    private MainFrame mainFrame;

    public Ending1(MainFrame mainFrame) {
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

        JustifiedTextPanel storyTextPanel = new JustifiedTextPanel("Titoti memilih untuk mengembalikan artefak karena tidak ingin mengganggu keseimbangan alam yang sudah ada. Ia pun kembali pulang tanpa mendapatkan hasil apapun demi keselamatan nyawanya.");
        storyTextPanel.setBounds(50, 400, 700, 100);
        storyTextPanel.setOpaque(false);
        storyTextPanel.setForeground(Color.WHITE);
        storyTextPanel.setFont(new Font("Arial", Font.PLAIN, 18));
        layeredPane.add(storyTextPanel, Integer.valueOf(1));

        TypeWritterEffect effect = new TypeWritterEffect(storyTextPanel, "Titoti memilih untuk mengembalikan artefak karena tidak ingin mengganggu keseimbangan alam yang sudah ada. Ia pun kembali pulang tanpa mendapatkan hasil apapun demi keselamatan nyawanya.", 50);
        effect.start();

        JLabel endingLabel = new JLabel("BAD ENDING", SwingConstants.CENTER);
        endingLabel.setFont(new Font("Arial", Font.BOLD, 36));
        endingLabel.setForeground(Color.RED);
        endingLabel.setBounds(200, 200, 400, 50);
        layeredPane.add(endingLabel, Integer.valueOf(1));

        JButton mainMenuButton = new JButton("EXIT");
        mainMenuButton.setBounds(300, 520, 200, 50);
        layeredPane.add(mainMenuButton, Integer.valueOf(1));

        mainMenuButton.addActionListener(e -> System.exit(0));
    }
}
