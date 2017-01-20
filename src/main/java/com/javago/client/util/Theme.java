package com.javago.client.util;

import javax.swing.*;

/**
 * Klasa ustawiająca motyw aplikacji.
 * @author Jakub Przywara
 * @version 1.0
 * @since 2016-12-09
 */
public class Theme {
    public static void setTheme() {
        // Ustawienie motywu Windowsa jeśli aplikacja jest uruchamiana na Windowsie
        if (System.getProperty("os.name").startsWith("Windows")) {
            try {
                UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
            }
            catch (ClassNotFoundException e) { e.printStackTrace(); }
            catch (InstantiationException e) { e.printStackTrace(); }
            catch (IllegalAccessException e) { e.printStackTrace(); }
            catch (UnsupportedLookAndFeelException e) { e.printStackTrace(); }
        }
    }
}
