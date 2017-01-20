package com.javago.client;

import com.javago.client.listeners.AddBtnListener;
import com.javago.client.listeners.ConnectBtnListener;
import com.javago.client.listeners.ExitBtnListener;
import com.javago.client.listeners.JoinBtnListener;
import com.javago.client.util.Theme;

import javax.swing.*;
import java.awt.*;
import java.io.*;

/**
 * @author Jakub Przywara
 * @version 2.2
 * @since 2016-12-08
 */
public class NewGame extends Thread {
    /**
     * Główna ramka, w której znajdują się wszystkie elementy.
     */
    private JFrame frmNewGame;
    /**
     * Pole tekstowe przeznaczone do wprowadzania nicku gracza.
     */
    private JTextField tfName;

    /**
     * Konstruktor okna Nowej Gry
     */

    public void run() {
        Theme.setTheme();
        initialize();
    }

    /**
     * Zawartość okna Nowej Gry
     */
    private void initialize() {
        // Główna ramka
        frmNewGame = new JFrame();
        frmNewGame.setTitle("Nowa gra");
        frmNewGame.setBounds(100, 100, 600, 500);
        frmNewGame.setMinimumSize(new Dimension(500, 400));
        frmNewGame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frmNewGame.getContentPane().setLayout(new BorderLayout(0, 0));

        // Panel po lewej
        JPanel westPanel = new JPanel();
        frmNewGame.getContentPane().add(westPanel, BorderLayout.WEST);
        westPanel.setLayout(new GridLayout(10, 2, 0, 5));

        // Przycisk: "Połącz"
        JButton btnConnect = new JButton("Połącz");
        btnConnect.setFont(new Font("SansSerif", Font.PLAIN, 18));
        westPanel.add(btnConnect);

        // Przycisk: "Dodaj grę"
        JButton btnAdd = new JButton("Dodaj grę");
        btnAdd.setEnabled(false);
        btnAdd.setFont(new Font("SansSerif", Font.PLAIN, 18));
        westPanel.add(btnAdd);

        // Przycisk: "Usuń grę"
        JButton btnDelete = new JButton("Usuń grę");
        btnDelete.setEnabled(false);
        btnDelete.setFont(new Font("SansSerif", Font.PLAIN, 18));
        westPanel.add(btnDelete);

        // Przycisk: "Dołącz do gry"
        JButton btnJoin = new JButton("Dołącz do gry");
        btnJoin.setEnabled(false);
        btnJoin.setFont(new Font("SansSerif", Font.PLAIN, 18));
        westPanel.add(btnJoin);

        // Przycisk: "Zakończ"
        JButton btnExit = new JButton("Zakończ");
        btnExit.setFont(new Font("SansSerif", Font.PLAIN, 18));
        westPanel.add(btnExit);

        // Panel po prawej
        JPanel centerPanel = new JPanel();
        frmNewGame.getContentPane().add(centerPanel, BorderLayout.CENTER);
        centerPanel.setLayout(new BorderLayout(0, 0));

        // Lista aktywnych gier
        JScrollPane scrollPane = new JScrollPane();
        centerPanel.add(scrollPane, BorderLayout.CENTER);
        //TODO dodać liste gier z serwera jako parametr JList(<lista z servera>)
        JList gamesList = new JList();
        gamesList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        gamesList.setSelectedIndex(0);
        gamesList.setFont(new Font("SansSerif", Font.PLAIN, 21));
        scrollPane.setViewportView(gamesList);

        // Etykieta: "Lista aktywnych gier"
        JLabel lblGamesList = new JLabel("LISTA AKTYWNYCH GIER");
        lblGamesList.setHorizontalAlignment(SwingConstants.CENTER);
        lblGamesList.setLabelFor(gamesList);
        lblGamesList.setFont(new Font("SansSerif", Font.BOLD, 25));
        scrollPane.setColumnHeaderView(lblGamesList);

        // Górny panel
        JPanel northPanel = new JPanel();
        centerPanel.add(northPanel, BorderLayout.NORTH);
        northPanel.setLayout(new BoxLayout(northPanel, BoxLayout.X_AXIS));

        // Etykieta: "Podaj imię:"
        JLabel lblName = new JLabel(" Podaj imię: ");
        lblName.setFont(new Font("SansSerif", Font.PLAIN, 24));
        northPanel.add(lblName);

        // Odstęp między etykietą a polem tekstowym
        Component horizontalStrut = Box.createHorizontalStrut(20);
        northPanel.add(horizontalStrut);

        // Pole tekstowe do wpisywania imienia
        tfName = new JTextField();
        tfName.setFont(new Font("SansSerif", Font.PLAIN, 24));
        northPanel.add(tfName);

        // Ustawienie ramki na środku ekranu
        final Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.frmNewGame.setLocation(dim.width/2 - frmNewGame.getSize().width/2 , dim.height/2 - frmNewGame.getSize().height/2);
        // Ustawienie widoczności ramki
        this.frmNewGame.setVisible(true);

        // Listeners
        btnConnect.addActionListener(new ConnectBtnListener(btnConnect, tfName, btnAdd, btnJoin));
        btnAdd.addActionListener(new AddBtnListener(frmNewGame));
        btnJoin.addActionListener(new JoinBtnListener());
        btnExit.addActionListener(new ExitBtnListener());
    }
}
