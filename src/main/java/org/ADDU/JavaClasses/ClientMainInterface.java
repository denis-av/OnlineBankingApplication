package org.ADDU.JavaClasses;


import org.ADDU.Model.Client;
import org.ADDU.Model.Manager;

import javax.swing.*;
import javax.swing.plaf.basic.BasicBorders;
import javax.swing.plaf.metal.MetalBorders;
import java.awt.*;
import java.awt.event.*;


public class ClientMainInterface extends JFrame{
    private JButton buttonSold;
    private JButton buttonTransfer;
    private JFrame frame2;
    private Client client;
    private JLabel background;
    private JButton buttonRequest;
    private JButton buttonLoans;
    private JButton logoutButton;
    private ScriereCitireManager theManager=new ScriereCitireManager();
    private ScriereCitireClient theClient = new ScriereCitireClient();
    private JPanel principal;
    private JPanel contPanel = new JPanel();
    private CardLayout cardLOUT=new CardLayout();
    private SeeSoldPage soldPage;
    private NewLoanPage newLoanPage;
    private TransferMoneyPage transferPage;
    private SeeRequestsPage rqPage;

    public ClientMainInterface(Client client){
        this.client=client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public JButton getButtonSold() {
        return buttonSold;
    }

    public JButton getButtonTransfer() {
        return buttonTransfer;
    }

    public JButton getButtonRequest() {
        return buttonRequest;
    }

    public JButton getButtonLoans() {
        return buttonLoans;
    }

    public JButton getLogoutButton() {
        return logoutButton;
    }

    public JPanel returnPanelMainClinet(){

        soldPage = new SeeSoldPage(client);
        newLoanPage=new NewLoanPage(client,theManager.returnManager(client.getManagerId()));
        transferPage = new TransferMoneyPage(client,theClient.returnClient());
        rqPage = new SeeRequestsPage(client);
        contPanel.setLayout(cardLOUT);

        principal = new JPanel();
        principal.setSize(666,403);
        principal.setBounds(0,0,666,403);
        principal.setLayout(null);


        Font fondText=new Font("Calibri Light (Headings)", Font.BOLD,13);

        buttonSold = new JButton("See sold");
        buttonSold.setSize(235,28);
        buttonSold.setBounds(360,75,205,28);
        buttonSold.setFont(fondText);
        buttonSold.setBackground(Color.WHITE);
        principal.add(buttonSold);

        buttonTransfer = new JButton("Transfer Money");
        buttonTransfer.setSize(235,28);
        buttonTransfer.setBounds(360,130,205,28);
        buttonTransfer.setFont(fondText);
        buttonTransfer.setBackground(Color.WHITE);
        principal.add(buttonTransfer);

        buttonRequest = new JButton("Request Loan");
        buttonRequest.setSize(235,28);
        buttonRequest.setBounds(360,183,205,28);
        buttonRequest.setFont(fondText);
        buttonRequest.setBackground(Color.WHITE);
        principal.add(buttonRequest);

        buttonLoans = new JButton("See Requests");
        buttonLoans.setSize(235,28);
        buttonLoans.setBounds(360,235,205,28);
        buttonLoans.setFont(fondText);
        buttonLoans.setBackground(Color.WHITE);
        principal.add(buttonLoans);

        logoutButton=new JButton("Logout");
        logoutButton.setBounds(360,287,205,28);
        logoutButton.setFont(fondText);
        logoutButton.setBackground(Color.WHITE);
        principal.add(logoutButton);

        ImageIcon backgroundImage = new ImageIcon("src\\main\\resources\\background.jpg");
        background = new JLabel("", backgroundImage,JLabel.CENTER);
        background.setBounds(0, 0, 665, 403);

        principal.add(background);

        contPanel.setBounds(0,0,665,403);
        contPanel.add(principal,"2");
        contPanel.add(soldPage.returnPanel(),"soldPage");
        contPanel.add(newLoanPage.returnPanel(),"newLoanPage");
        contPanel.add(transferPage.returnPanel(),"transferMoney");
        contPanel.add(rqPage.returnPanel(),"seeRequests");

        buttonSold.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLOUT.show(contPanel,"soldPage");
            }
        });

        soldPage.getButtonBack().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLOUT.show(contPanel,"2");
            }
        });

        buttonRequest.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLOUT.show(contPanel,"newLoanPage");
            }
        });

        newLoanPage.getButtonBack().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLOUT.show(contPanel,"2");
            }
        });

        buttonTransfer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLOUT.show(contPanel,"transferMoney");
            }
        });

        transferPage.getButtonBack().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLOUT.show(contPanel,"2");
            }
        });

        buttonLoans.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLOUT.show(contPanel,"seeRequests");
            }
        });

        rqPage.getButtonBack().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLOUT.show(contPanel,"2");
            }
        });

        return contPanel;
    }
}