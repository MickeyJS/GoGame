package com.javago.client.exceptions;

import java.awt.Font;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

/**
 * Klasa wystwietlajaca okno z ostrzezeniem.
 * @author Jakub Przywara
 * @version 1.0
 * @since 2016-12-09
 */
public class PopupInfo {
    /**
     * Tworzy nowa okno Popup z informacja.
     * @param frame Okno, w ktorym zostanie wyswitlone ostrzezenie.
     * @param title Tytul wyswietlonego okna.
     * @param msg Tresc wiadomosci.
     * @param type Typ wiadomosci.
     */
    public PopupInfo(JFrame frame, String title, String msg, int type) {
        UIManager.put("OptionPane.messageFont", new Font("SansSerif", Font.PLAIN, 16));
        UIManager.put("OptionPane.buttonFont", new Font("SansSerif", Font.PLAIN, 14));
        JOptionPane.showMessageDialog(frame, msg, title, type);
    }
}
