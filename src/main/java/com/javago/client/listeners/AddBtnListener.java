package com.javago.client.listeners;


import com.javago.client.Client;
import com.javago.client.GameWindow;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddBtnListener implements ActionListener {
    private JFrame frame;

    public AddBtnListener(JFrame newGameFrame) {
        this.frame = newGameFrame;
    }

    public void actionPerformed(ActionEvent e) {
        Client.sendMessage("CREATE_GAME");
        Client.sendMessage("19");
        String serverResponse = Client.receiveMessage();
        if (serverResponse.equals("WAIT_FOR_OPPONENT")) {
            frame.setVisible(false);
            Client.gameWindow = new GameWindow(19);
            Client.gameWindow.start();
        }

    }
}
