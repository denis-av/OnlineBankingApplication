package org.ADDU.JavaClasses;


import org.ADDU.Exceptions.CouldNotFindClient;
import org.ADDU.Model.Client;
import org.ADDU.Model.Manager;

import javax.swing.*;
import javax.swing.plaf.basic.BasicBorders;
import javax.swing.plaf.metal.MetalBorders;
import java.awt.*;
import java.awt.event.*;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

public class ManagerMainInterface extends JFrame {
    private JButton buttonClients;
    private JButton buttonLoans;
    private Manager manager;
    private static String file;
    private JLabel background;
    private JButton buttonRequest;
    private JButton logoutButton;
    private JPanel principal;
    private JPanel contPanel = new JPanel();
    private CardLayout cardLOUT = new CardLayout();
    private SeeMyClients MYCLIENTS;
    private static String file2;
    public ArrayList<Client> myClient;
    private ScriereCitireClient theClient;
    private ArrayList<Client> allClients;
    private SEERequests myRequests;

    public ManagerMainInterface(Manager manager,String file,String file2) {
        this.manager = manager;
        this.file=file;
        this.file2=file2;
        theClient = new ScriereCitireClient(file2);
        allClients=theClient.returnClient();
        addMyClients();
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

   public void addAClient(String cardNumber){
        int find=0;
       for(int i=0;i<allClients.size();i++){
            if (allClients.get(i).getCardNumber().equals(cardNumber)) {
                myClient.add(allClients.get(i));
                find = 1;
            }
        }
        if(find==0) throw new CouldNotFindClient();
   }


    public void addMyClients(){
        myClient=new ArrayList<Client>();
        for(int i=0;i<manager.getCards().size();i++)
        {
            int find=0;
            for(int j=0;j<allClients.size() && find==0;j++)
                if(allClients.get(j).getCardNumber().equals(manager.getCards().get(i))){
                    myClient.add(allClients.get(j));
                    find=1;
                }
            if(find==0) throw new CouldNotFindClient();
        }
    }

    public JPanel returnPanelMainManager() {
        contPanel.setLayout(cardLOUT);
        MYCLIENTS=new SeeMyClients(manager,myClient);
        myRequests=new SEERequests(manager,file,file2);

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

        ImageIcon backgroundImage = new ImageIcon("src\\main\\resources\\background.jpg");
        background = new JLabel("", backgroundImage, JLabel.CENTER);
        background.setBounds(0, 0, 665, 403);

        principal.add(background);

        contPanel.setBounds(0, 0, 665, 403);
        contPanel.add(principal, "Main interface for managers");
        contPanel.add(MYCLIENTS.returnPanel(),"SeeMyClients");
        contPanel.add(myRequests.returnPanel(),"SeeRequests");

        buttonClients.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLOUT.show(contPanel,"SeeMyClients");
            }
        });

        MYCLIENTS.getButtonBack().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLOUT.show(contPanel,"Main interface for managers");
            }
        });

        buttonLoans.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLOUT.show(contPanel,"SeeRequests");
            }
        });

        myRequests.getButtonBack().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLOUT.show(contPanel,"Main interface for managers");
            }
        });

        return contPanel;
    }

}