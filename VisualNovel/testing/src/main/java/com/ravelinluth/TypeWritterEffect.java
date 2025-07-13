package com.ravelinluth;

import javax.swing.*;
import java.util.Timer;
import java.util.TimerTask;

public class TypeWritterEffect {
    @FunctionalInterface
    public interface TypingCompleteListener {
        void onTypingComplete();
    }

    private JustifiedTextPanel textPanel;
    private String fullText;
    private int delay;
    private int currentIndex = 0;
    private Timer timer;
    private boolean running = false;
    private TypingCompleteListener onCompleteListener;

    public TypeWritterEffect(JustifiedTextPanel textPanel, String fullText, int delay) {
        this.textPanel = textPanel;
        this.fullText = fullText; // JustifiedTextPanel tidak memerlukan pemformatan tambahan
        this.delay = delay;
    }

    public void start() {
        textPanel.setText(""); // Clear text area
        currentIndex = 0;
        running = true;
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                if (currentIndex < fullText.length()) {
                    SwingUtilities.invokeLater(() -> {
                        // Tambahkan satu karakter ke teks
                        String currentText = textPanel.getText();
                        textPanel.setText(currentText + fullText.charAt(currentIndex++));
                    });
                } else {
                    stop();
                    if (onCompleteListener != null) {
                        SwingUtilities.invokeLater(onCompleteListener::onTypingComplete);
                    }
                }
            }
        }, 0, delay);
    }

    public void stop() {
        running = false;
        if (timer != null) {
            timer.cancel();
        }
    }

    public void skip() {
        stop();
        textPanel.setText(fullText);
        if (onCompleteListener != null) {
            SwingUtilities.invokeLater(onCompleteListener::onTypingComplete);
        }
    }

    public void setText(String newText) {
        this.fullText = newText;
        textPanel.setText(""); // Clear the panel for new text
    }

    public boolean isRunning() {
        return running;
    }

    public void setOnCompleteListener(TypingCompleteListener listener) {
        this.onCompleteListener = listener;
    }
}
