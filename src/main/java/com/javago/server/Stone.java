package com.javago.server;

import java.awt.*;

/**
 * @author Jakub Przywara
 * @version 1.0
 * @since 2017-01-17
 */
public class Stone {
    /**
     * Pozycja x kamienia.
     */
    private int x;
    /**
     * Pozycja y kamienia.
     */
    private int y;
    /**
     * Kolor kamienia.
     */
    private Color color;

    /**
     *
     * @param point
     * @param
     */
    public Stone(Point point) {
        this.x = (int) point.getX();
        this.y = (int) point.getY();
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Color getColor() {
        return this.color;
    }

    public Point getPosition() {
        return new Point(this.x, this.y);
    }
}
