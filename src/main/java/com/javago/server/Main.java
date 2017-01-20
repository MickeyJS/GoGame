package com.javago.server;

/**
 * @author Jakub Przywara
 * @version 1.0
 * @since 2017-01-19
 */
public class Main {
    public static void main(String[] args) {
        Server server = Server.getInstance();
        server.start();
    }
}
