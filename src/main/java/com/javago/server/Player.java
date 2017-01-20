package com.javago.server;

import java.awt.*;
import java.io.*;
import java.net.Socket;

public class Player extends Thread {
    private String userName = null;
    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;

    private Game game = null;
    private Color stoneColor = null;
    private int totalScore = 0;

    public Player(Socket socket) throws IOException{
        this.socket = socket;
        System.out.println("Player logged...");
    }

    public void run() {
        System.out.println("Player started...");
        try {
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);

            while (true) {
                String command = in.readLine();

                if (command.equals("LOGIN")) {
                    String recv = in.readLine();
                    if (this.checkNameAvailability(recv)) {
                        userName = recv;
                        out.println("LOGIN_SUCCED");
                        Server.addPlayer(this);
                        Server.usedNicks.add(this.getUserName());
                    } else {
                        out.println("NOT_AVAILABLE");
                    }
                }
                else if (command.equals("CREATE_GAME")) {
                    String recv = in.readLine();
                    try {
                        int boardSize = Integer.parseInt(recv);
                        Game newGame = new Game(Server.getNextGameID(), this, boardSize);
                        Server.addGame(newGame);
                        this.game = newGame;
                        this.stoneColor = Color.BLACK;
                        out.println("WAIT_FOR_OPPONENT");
                    } catch (NumberFormatException e) {}
                }
                else if (command.equals("JOIN_TO_GAME")) {
                    String recv = in.readLine();
                    try {
                        int index = Integer.parseInt(recv);
                        Game toJoin = Server.findGame(index);
                        toJoin.addPlayer(this);
                        this.game = toJoin;
                        this.stoneColor = game.getHost().getOpponentStoneColor();
                        toJoin.start();
                    } catch (NumberFormatException e) {}
                }
                else if (command.equals("DELETE_GAME")) {
                    if (this.equals(this.game.getHost())) {
                        Server.deleteGame(game);
                        this.resetPlayer();
                    }
                }
                else if (command.equals("PASS")) {
                    this.game.changePlayer();
                }


            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addPoints(int points) {
        this.totalScore += points;
    }

    public int getTotalScore() {
        return totalScore;
    }

    public void resetPlayer() {
        game = null;
        stoneColor = null;
        totalScore = 0;
    }

    public Color getStoneColor() {
        return stoneColor;
    }
    public void setStoneColor(Color color) { this.stoneColor = color; }

    public Color getOpponentStoneColor() {
        if (this.stoneColor == Color.BLACK)
            return Color.WHITE;
        else
            return Color.BLACK;
    }

    public String getUserName() {
        return userName;
    }

    public static boolean checkNameAvailability(String name) {
        boolean available = true;
        synchronized (Server.usedNicks) {
            if (Server.usedNicks.contains(name)) {
                available = false;
            }
        }
        return available;
    }
}
