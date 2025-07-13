package com.ravelinluth;
import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    private CardLayout cardLayout;
    private JPanel mainPanel;

    public MainFrame() {
        // Konfigurasi frame utama
        setTitle("Adventure of Titoti");
        setSize(800, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Atur CardLayout untuk mengelola panel
        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        // Tambahkan semua scene ke mainPanel
        MainMenu mainMenu = new MainMenu(this);
        SceneManager sceneManager = new SceneManager(this);
        Ending1 ending1 = new Ending1(this);
        SceneLorongKiri sceneLorongKiri = new SceneLorongKiri(this);
        Ending2 ending2 = new Ending2(this);
        Ending3 ending3 = new Ending3(this);
        SceneLorongKanan sceneLorongKanan = new SceneLorongKanan(this);
        SceneMonster sceneMonster = new SceneMonster(this);
        EndingMati endingMati = new EndingMati(this);
        MemberiMakanMonster makanMonster = new MemberiMakanMonster(this);
        ScenePintu scenePintu = new ScenePintu(this);
        ScenePintuBulan scenePintuBulan = new ScenePintuBulan(this);
        ScenePintuMatahari scenePintuMatahari = new ScenePintuMatahari(this);
        SceneJembatan sceneJembatan = new SceneJembatan(this);
        SceneUlar sceneUlar = new SceneUlar(this);
        SceneSeruling sceneSeruling = new SceneSeruling(this);
        Ending4 lastEnding = new Ending4(this);
        GoodEnding goodEnding = new GoodEnding(this);

        mainPanel.add(mainMenu, "MainMenu");
        mainPanel.add(sceneManager, "SceneManager");
        mainPanel.add(ending1, "EndingKabur");
        mainPanel.add(sceneLorongKiri, "SceneLorongKiri");
        mainPanel.add(ending2, "EndingTerjebak");
        mainPanel.add(ending3, "EndingCheat");
        mainPanel.add(sceneLorongKanan, "SceneLorongKanan");
        mainPanel.add(sceneMonster, "SceneMonster");
        mainPanel.add(endingMati, "EndingMati");
        mainPanel.add(makanMonster, "MakanMonster");
        mainPanel.add(scenePintu, "ScenePintu");
        mainPanel.add(scenePintuBulan, "ScenePintuBulan");
        mainPanel.add(scenePintuMatahari, "ScenePintuMatahari");
        mainPanel.add(sceneJembatan, "SceneJembatan");
        mainPanel.add(sceneUlar, "SceneUlar");
        mainPanel.add(sceneSeruling, "SceneSeruling");
        mainPanel.add(lastEnding, "Ending4");
        mainPanel.add(goodEnding, "GoodEnding");

        // Tambahkan mainPanel ke frame
        add(mainPanel);

        // Tampilkan MainMenu saat pertama kali
        cardLayout.show(mainPanel, "MainMenu");
    }

    // Metode untuk berpindah scene
    public void showScene(String sceneName) {
        cardLayout.show(mainPanel, sceneName);
    }

    public static void main(String[] args) {
        MainFrame frame = new MainFrame();
        frame.setVisible(true);
    }
}

