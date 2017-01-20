package com.javago.client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;


/**
 * @author Jakub Przywara
 * @version 1.3
 * @since 2016-12-08
 */
public class Board extends JPanel {
    /**
     * Zmienna przechowująca rozmiar planszy.
     */
    private final int boardSize;
    /**
     * Zmienna przechowująca rozmiar pola (kwadratu)
     */
    private final int tileSize;

    /**
     * Konstruktor planszy do gry.
     * @param boardSize Rozmiar planszy.
     */
    protected Board(final int boardSize, final int tileSize) {
        this.boardSize = boardSize;
        this.tileSize = tileSize;
        this.setBackground(new Color(220, 178, 92));

        //Client.receiveMessage();

        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                int x = Math.round((float) (e.getX() - tileSize) / tileSize);
                int y = Math.round((float) (e.getY() - tileSize) / tileSize);
                if (x >= boardSize) x = boardSize - 1;
                if (x < 0) x = 0;
                if (y >= boardSize) x = boardSize - 1;
                if (y < 0) y = 0;
                System.out.println(String.format("y: %d, x: %d", y, x));

                repaint();
            }
        });
    }

    /**
     * Metoda rysująca.
     * @param g Obiekt typu Graphics.
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Ustawienie koloru do rysowania na CZARNY
        g2.setColor(Color.BLACK);
        // Ustawienie grubszej linii.
        g2.setStroke(new BasicStroke(2));

        // Rysowanie wierszy i kolumn.
        for (int i = 0; i < this.boardSize; i++) {
            g2.drawLine(this.getCoordinate(0), this.getCoordinate(i), this.getCoordinate(this.boardSize - 1), this.getCoordinate(i));
            g2.drawLine(this.getCoordinate(i), this.getCoordinate(0), this.getCoordinate(i), this.getCoordinate(this.boardSize - 1));
        }

        // BLOK TESTOWY //
        //rysowanie kamieni
        /*for (Stone stone : this.stones) {
            g2.setColor(stone.color);
            g2.fillOval(getCoordinate(stone.x) - tileSize / 2, getCoordinate(stone.y) - tileSize / 2, tileSize, tileSize);
        }*/
        // BLOK TESTOWY //
    }

    private int getCoordinate(int x) {
        return x * this.tileSize + tileSize;
    }
}
