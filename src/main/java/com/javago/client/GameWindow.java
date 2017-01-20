package com.javago.client;

import com.javago.client.util.Theme;

import javax.swing.*;
import java.awt.*;

/**
 * @author Jakub Przywara
 * @version 1.1
 * @since 2016-12-08
 */
public class GameWindow extends Thread {
    /**
     * Pole przechowujące rozmiar planszy do gry.
     */
    private final int boardSize;
    /**
     * Pole przechowujące rozmiar jednego kafelka (kwadratu).
     * Wymagane do stworzenia okna w odpowiednim rozmiarze.
     */
    private final int tileSize = 35;

    /**
     *
     * @param boardSize Rozmiar planszy.
     */
    public GameWindow(int boardSize) {
        this.boardSize = boardSize;
    }

    public void run() {
        Theme.setTheme();
        initialize();
    }

    /**
     * Zawartość okna gry.
     */
    private void initialize() {
        JFrame frmGameWindow = new JFrame();
        frmGameWindow.setTitle("JavaGo");
        frmGameWindow.setBounds(100, 100, (boardSize+1) * tileSize + 5, (boardSize + 1) * tileSize + 100);
        frmGameWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frmGameWindow.getContentPane().setLayout(null);
        frmGameWindow.setResizable(false);

        JPanel boardPanel = new Board(this.boardSize, this.tileSize);
        boardPanel.setBounds(0, 0, (boardSize + 1) * tileSize + 5, (boardSize + 1) * tileSize);
        frmGameWindow.getContentPane().add(boardPanel);

        JPanel GamePanel = new JPanel();
        GamePanel.setBounds(0, (boardSize + 1) * tileSize, (boardSize + 1) * tileSize, frmGameWindow.getHeight() - boardPanel.getHeight() - 35);
        frmGameWindow.getContentPane().add(GamePanel);
        GamePanel.setLayout(new GridLayout(1, 0, 0, 0));

        JLabel lblBlack = new JLabel("Czarne: 0");
        lblBlack.setHorizontalAlignment(SwingConstants.CENTER);
        lblBlack.setFont(new Font("SansSerif", Font.BOLD, 25));
        GamePanel.add(lblBlack);

        JButton btnPass = new JButton("PASUJ");
        btnPass.setFont(new Font("SansSerif", Font.BOLD, 18));
        GamePanel.add(btnPass);

        JButton btnEnd = new JButton("ZAKOŃCZ");
        btnEnd.setFont(new Font("SansSerif", Font.BOLD, 18));
        GamePanel.add(btnEnd);

        JLabel lblWhite = new JLabel("Białe: 0");
        lblWhite.setHorizontalAlignment(SwingConstants.CENTER);
        lblWhite.setFont(new Font("SansSerif", Font.BOLD, 25));
        GamePanel.add(lblWhite);

        frmGameWindow.setVisible(true);
    }
}
