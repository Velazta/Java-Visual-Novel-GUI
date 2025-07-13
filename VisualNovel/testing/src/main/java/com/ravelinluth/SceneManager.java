package com.ravelinluth;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SceneManager extends JPanel {
    private int currentTextIndex = 0;
    private String[] storyTexts = {
            "Titoti adalah seorang penjelajah yang terkenal dengan keberanian dan rasa ingin tahunya. Suatu hari, Titoti menerima surat misterius dari seorang profesor tua bernama Dr. Amelia. Dia meminta bantuan Titoti untuk menemukan sebuah artefak kuno bernama 'Mata Kegelapan', yang tersembunyi di dalam Gua Larangan, tempat yang terkenal dengan legenda mengerikan.",
            "\"Hati-hati, Titoti,\" tulis Dr. Amelia. \"Mata Kegelapan tidak hanya berharga, tapi juga berbahaya. Banyak yang mencoba mencarinya, tapi tak pernah kembali.\" Dengan tekad kuat, Titoti memutuskan untuk memulai perjalanan. Dia membawa peta usang, kompas, senter, dan pisau kecil.",
            "Titoti akhirnya menemukan artefak legendaris 'Mata Kegelapan' di dalam gua kuno. Namun, begitu ia menyentuhnya, gua itu mulai bergetar hebat. Lorong-lorong berubah bentuk, membuat peta yang dibawanya tidak lagi berguna. Dengan suara gemuruh dan dinding yang bergerak seperti hidup, Titoti berjuang mencari jalan keluar.",
            "Di tengah kekacauan, sebuah bisikan misterius terdengar, memperingatkannya: \"Kembalikan... atau terima nasibmu.\" Kini, Titoti dihadapkan pada pilihan sulit: mempertahankan artefak berharga itu atau menyelamatkan nyawanya."
    };

    private JButton leftButton;
    private JButton rightButton;
    private JLabel characterLabel; // Label untuk gambar karakter

    public SceneManager(MainFrame mainFrame) {
        // Gunakan JLayeredPane untuk mengelola lapisan
        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setPreferredSize(new Dimension(800, 600));
        setLayout(new BorderLayout());
        add(layeredPane, BorderLayout.CENTER);

        // Latar Belakang
        ImageIcon backgroundImage = new ImageIcon("src/main/java/Bg/Background1Scene.png");
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

        // Tambahkan karakter gambar tapi sembunyikan di awal
        ImageIcon characterImage = new ImageIcon("src/main/java/Bg/artefak-removebg-preview.png");
        Image scaledCharacterImage = characterImage.getImage().getScaledInstance(150, 225, Image.SCALE_SMOOTH); // Sesuaikan ukuran
        characterLabel = new JLabel(new ImageIcon(scaledCharacterImage));
        characterLabel.setBounds(325, 100, 150, 225); // Posisikan di tengah
        characterLabel.setVisible(false);
        layeredPane.add(characterLabel, Integer.valueOf(1));

        // Tombol Kiri
        leftButton = new JButton("Tinggalkan Artefak");
        leftButton.setBounds(200, 520, 150, 50);
        leftButton.setVisible(false); // Tidak terlihat saat awal
        layeredPane.add(leftButton, Integer.valueOf(2)); // Lapisan atas

        // Tombol Kanan
        rightButton = new JButton("Pertahankan Artefak");
        rightButton.setBounds(450, 520, 160, 50);
        rightButton.setVisible(false); // Tidak terlihat saat awal
        layeredPane.add(rightButton, Integer.valueOf(2)); // Lapisan atas

        // Efek Pengetikan
        TypeWritterEffect effect = new TypeWritterEffect(storyTextPanel, storyTexts[currentTextIndex], 50);
        effect.setOnCompleteListener(() -> {
            if (currentTextIndex == 2) { // Jika mencapai teks dengan index 2, tampilkan karakter
                characterLabel.setVisible(true);
            }
            if (currentTextIndex == storyTexts.length - 1) { // Hanya munculkan tombol jika teks terakhir selesai
                leftButton.setVisible(true);
                rightButton.setVisible(true);
            }
        });

        effect.start();

        // Event untuk Tombol Kiri
        leftButton.addActionListener((ActionEvent e) -> mainFrame.showScene("EndingKabur"));

        // Event untuk Tombol Kanan
        rightButton.addActionListener((ActionEvent e) -> mainFrame.showScene("SceneLorongKanan"));

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