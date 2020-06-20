package org.ADDU.JavaClasses;

import org.ADDU.Model.Client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.text.DecimalFormat;
import java.math.RoundingMode;

public class SeeSoldPage extends JFrame {

    private JTextField soldLei;
    private JTextField soldEuro;
    private Client client = new Client();
    private DecimalFormat df2 = new DecimalFormat("#.##");
    private JLabel background;
    JPanel principal;
    JPanel contPanel = new JPanel();
    CardLayout cardLOUT=new CardLayout();
    private Font fondText=new Font("Calibri Light (Headings)", Font.BOLD,13);
    private JPanel select;
    private JButton buttonBack;

    public SeeSoldPage(Client client){
        this.client=client;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public JButton getButtonBack() {
        return buttonBack;
    }

    public JPanel returnPanel(){

        principal = new JPanel();
        principal.setSize(350,300);
        principal.setBounds(0,0,750,450);
        principal.setLayout(null);

        JFrame frame1 = new JFrame();
        frame1.setTitle("Online Banking Application");
        frame1.setSize(750,550);
        frame1.setLocationRelativeTo(null);
        frame1.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame1.setLayout(new BorderLayout());

        Font fondText=new Font("Calibri Light (Headings)", Font.BOLD,13);


        JLabel textLei = new JLabel("The sold in LEI:");
        textLei.setFont(fondText);
        textLei.setSize(200,28);
        textLei.setBounds(360,120,200,28);
        principal.add(textLei);


        JTextField soldLei = new JTextField(client.getSold());
        soldLei.setFont(fondText);
        soldLei.setDisabledTextColor(Color.WHITE);
        soldLei.setSize(200,28);
        soldLei.setBounds(360,150,200,28);
        soldLei.setEditable(false); //read-only
        principal.add(soldLei);

        JLabel textEuro = new JLabel("The sold in EURO:");
        textEuro.setFont(fondText);
        textEuro.setSize(200,28);
        textEuro.setBounds(360,190,200,28);
        principal.add(textEuro);

        JTextField soldEuro = new JTextField(String.valueOf(df2.format(Double.parseDouble(client.getSold()) / 4.7)));
        soldEuro.setFont(fondText);
        soldEuro.setSize(200,28);
        soldEuro.setBounds(360,220,200,28);
        soldEuro.setEditable(false); //read-only
        principal.add(soldEuro);

        buttonBack=new JButton("Back");
        buttonBack.setFont(fondText);
        buttonBack.setBounds(410,260,100,28);
        principal.add(buttonBack);


        ImageIcon backgroundImage = new ImageIcon("src\\main\\resources\\background.jpg");
        background = new JLabel("", backgroundImage,JLabel.CENTER);
        background.setBounds(0, 0, 665, 403);

        principal.add(background);
        //contPanel.setBounds(0,0,666,404);
        //contPanel.add(principal,"soldPage");

        return principal;
    }
}
