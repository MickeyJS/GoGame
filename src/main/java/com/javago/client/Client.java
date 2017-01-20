package com.javago.client;


import com.javago.client.exceptions.PopupInfo;

import javax.swing.*;
import java.io.*;
import java.net.InetAddress;
import java.net.Socket;

/**
 * @author Jakub Przywara
 * @version 1.0
 * @since 2016-12-08
 */
public class Client extends Thread {
    private static Socket socket;
    private static PrintWriter out;
    private static BufferedReader in;

    public static NewGame newGameWindow;
    //protected static WaitWindow waitWindow;
    public static GameWindow gameWindow;

    public static void main(String[] args) {
        Client client = new Client();
        client.start();
    }

    public void run() {
        try {
            socket = new Socket(InetAddress.getByName("localhost"), 5000);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);
            newGameWindow = new NewGame();
            newGameWindow.start();
        } catch (IOException e) {
            new PopupInfo(null, "Błąd", "Błąd połączenia z serwerem. Spróbuj ponownie za chwilę.", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void sendMessage(String message) {
        out.println(message);
        System.out.println("Message sent to the client is "+message);
    }

    public static String receiveMessage() {
        String msg = null;
        try {
            msg = in.readLine();
            System.out.println("Message received from the server : " +msg);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return msg;
    }
}
