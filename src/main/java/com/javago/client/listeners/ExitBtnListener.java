package com.javago.client.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author Jakub Przywara
 * @version 1.0
 * @since 2016-12-09
 */
public class ExitBtnListener implements ActionListener{
    public void actionPerformed(ActionEvent e) {
        //TODO wyslij do serwera informacje o rozlaczeniu
        System.exit(0);
    }
}
