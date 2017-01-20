package com.javago.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

/**
 * @author Jakub Przywara
 * @version 1.0
 * @since 2016-12-09
 */

public class Server extends Thread {
    private static Server instance = null;
    private final int PORT = 5000;

    private ArrayList<Player> connectedPlayers;
    protected static HashSet<String> usedNicks;
    private ArrayList<Game> startedGames;

    private static int gameID = 1;

    public void run() {
        System.out.println("Server is running...");
        connectedPlayers = new ArrayList<Player>();
        startedGames = new ArrayList<Game>();
        usedNicks = new HashSet<String>();

        ServerSocket listener = null;
        try {
            listener = new ServerSocket(PORT);
            while (true) {
                Player newPlayer = new Player(listener.accept());
                newPlayer.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                listener.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void addPlayer(Player player) {
        instance.connectedPlayers.add(player);
    }

    public static void addGame(Game game) {
        instance.startedGames.add(game);
    }

    public static void deleteGame(Game game) {
        instance.startedGames.remove(game);
    }

    public static Game findGame(int gameID) {
        int index = -1;
        for (Game game: instance.startedGames) {
            if (game.getGameID() == gameID)
                index = instance.startedGames.indexOf(game);
        }

        return instance.startedGames.get(index);
    }

    public static Server getInstance() {
        if (instance == null) {
            synchronized (Server.class) {
                if (instance == null) {
                    instance = new Server();
                }
            }
        }

        return instance;
    }

    public static void resetInstance() {
        instance = null;
    }

    public static int getNextGameID() {
        gameID++;

        return gameID - 1;
    }
}
