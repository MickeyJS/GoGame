package com.javago.client.listeners;

import com.javago.client.Client;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class JoinBtnListener implements ActionListener {
    public void actionPerformed(ActionEvent e) {
        Client.sendMessage("JOIN_TO_GAME");
        Client.sendMessage("1");
    }
}
