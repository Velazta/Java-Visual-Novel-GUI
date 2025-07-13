package com.ravelinluth;

import javax.swing.*;
import java.awt.*;
import java.awt.Graphics;

public class JustifiedTextPanel extends JPanel {
    private String text; // Menyimpan teks yang akan ditampilkan

    public JustifiedTextPanel(String text) {
        this.text = text;
    }

    public void setText(String text) {
        this.text = text;
        repaint(); // Meminta panel untuk menggambar ulang dengan teks baru
    }

    public String getText() {
        return text; // Mengembalikan teks saat ini
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        // Atur anti-aliasing untuk kualitas teks lebih baik
        g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

        // Atur font
        Font font = new Font("Arial", Font.PLAIN, 16);
        g2d.setFont(font);
        FontMetrics metrics = g2d.getFontMetrics(font);

        int lineHeight = metrics.getHeight();
        int x = 10; // Margin kiri
        int y = lineHeight; // Posisi awal vertikal
        int maxWidth = getWidth() - 20; // Lebar maksimum teks dengan margin kanan

        String[] words = text.split(" ");
        StringBuilder line = new StringBuilder();

        for (String word : words) {
            if (metrics.stringWidth(line + word) < maxWidth) {
                line.append(word).append(" ");
            } else {
                drawJustifiedLine(g2d, line.toString().trim(), x, y, maxWidth, metrics);
                y += lineHeight;
                line = new StringBuilder(word).append(" ");
            }
        }

        // Gambar baris terakhir
        if (!line.isEmpty()) {
            g2d.drawString(line.toString().trim(), x, y);
        }
    }

    private void drawJustifiedLine(Graphics2D g2d, String line, int x, int y, int maxWidth, FontMetrics metrics) {
        String[] words = line.split(" ");
        int totalWidth = 0;

        for (String word : words) {
            totalWidth += metrics.stringWidth(word);
        }

        int spaceWidth = (maxWidth - totalWidth) / Math.max(1, words.length - 1);
        int curX = x;

        for (int i = 0; i < words.length; i++) {
            g2d.drawString(words[i], curX, y);
            curX += metrics.stringWidth(words[i]) + (i < words.length - 1 ? spaceWidth : 0);
        }
    }
}
