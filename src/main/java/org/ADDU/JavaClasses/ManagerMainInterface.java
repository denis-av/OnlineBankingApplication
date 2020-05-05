package org.ADDU.JavaClasses;


import org.ADDU.Model.Manager;

import javax.swing.*;
import javax.swing.plaf.basic.BasicBorders;
import javax.swing.plaf.metal.MetalBorders;
import java.awt.*;
import java.awt.event.*;

public class ManagerMainInterface extends JFrame {
    private JButton buttonClients;
    private JButton buttonLoans;
    private Manager manager;
    private JLabel background;
    private JButton buttonRequest;
    private JButton logoutButton;
    JPanel principal;
    JPanel contPanel = new JPanel();
    CardLayout cardLOUT = new CardLayout();

    public ManagerMainInterface(Manager manager) {
        this.manager = manager;
    }

    public void setManager(Manager manager) {
        this.manager = manager;
    }

    public JButton getButtonClients() {
        return buttonClients;
    }

    public JButton getButtonLoans() {
        return buttonLoans;
    }

    public JButton getLogoutButton() {
        return logoutButton;
    }

    public JPanel returnPanelMainManager() {
        contPanel.setLayout(cardLOUT);

        principal = new JPanel();
        principal.setSize(350, 300);
        principal.setBounds(0, 0, 750, 450);
        principal.setLayout(null);


        Font fondText = new Font("Calibri Light (Headings)", Font.BOLD, 13);

        buttonClients = new JButton("See clients");
        buttonClients.setSize(235, 28);
        buttonClients.setBounds(360, 125, 205, 28);
        buttonClients.setFont(fondText);
        buttonClients.setBackground(Color.WHITE);
        principal.add(buttonClients);

        buttonLoans = new JButton("Received Loans");
        buttonLoans.setSize(235, 28);
        buttonLoans.setBounds(360, 180, 205, 28);
        buttonLoans.setFont(fondText);
        buttonLoans.setBackground(Color.WHITE);
        principal.add(buttonLoans);

        logoutButton = new JButton("Logout");
        logoutButton.setBounds(360, 235, 205, 28);
        logoutButton.setFont(fondText);
        logoutButton.setBackground(Color.WHITE);
        principal.add(logoutButton);

        ImageIcon backgroundImage = new ImageIcon("backf.jpg");
        background = new JLabel("", backgroundImage, JLabel.CENTER);
        background.setBounds(0, 0, 665, 403);

        principal.add(background);

        contPanel.setBounds(0, 0, 665, 403);
        contPanel.add(principal, "Main interface for managers");

        return contPanel;
    }

}