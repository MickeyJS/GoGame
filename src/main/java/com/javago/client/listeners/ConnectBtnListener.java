package com.javago.client.listeners;

import com.javago.client.Client;
import com.javago.client.exceptions.EmptyUserNameException;
import com.javago.client.exceptions.IncorrectUserNameException;
import com.javago.client.exceptions.PopupInfo;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author Jakub Przywara
 * @version 1.0
 * @since 2016-12-09
 */
public class ConnectBtnListener implements ActionListener {
    private JButton connectBtn;
    private JButton addBtn;
    private JButton joinBtn;
    private JTextField name;

    public ConnectBtnListener(JButton connectBtn, JTextField textField, JButton addBtn, JButton joinBtn) {
        this.connectBtn = connectBtn;
        this.addBtn = addBtn;
        this.joinBtn = joinBtn;
        this.name = textField;
    }

    /**
     * Metoda wykonywana po naciśnięciu przycisku "Połącz".
     * @param e
     */
    public void actionPerformed(ActionEvent e) {
        try {
            validateName();
            connectBtn.setEnabled(false);
            addBtn.setEnabled(true);
            joinBtn.setEnabled(true);
            name.setEnabled(false);

        } catch (EmptyUserNameException exception) {
            new PopupInfo(null, "Błąd", "Musisz podać nazwę użytkownika", JOptionPane.ERROR_MESSAGE);
        } catch (IncorrectUserNameException exception) {
            new PopupInfo(null, "Błąd", "Użytkownik z taką nazwą jest już w grze", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Metoda sprawdzająca poprawność podanej nazwy użytkownika.
     * @throws EmptyUserNameException Wyjątek wywoływany gdy podana nazwa użytkownika jest pusta.
     * @throws IncorrectUserNameException Wyjątek wywoływany gdy osoba z taka samą nazwą użytkownika
     * jak podana jest już połączona.
     */
    private void validateName() throws EmptyUserNameException, IncorrectUserNameException {
        if (this.name.getText().equals("")) throw new EmptyUserNameException();
        else {
            Client.sendMessage("LOGIN");
            Client.sendMessage(name.getText());
            String serverResponse = Client.receiveMessage();
            if (serverResponse.equals("NOT_AVAILABLE")) {
                throw new IncorrectUserNameException();
            } else if (serverResponse.equals("LOGIN_SUCCED")){
                new PopupInfo(null, "Witaj", "Witaj w grze: " + name.getText().toUpperCase() + " !", JOptionPane.INFORMATION_MESSAGE);
            }

        }
    }
}
