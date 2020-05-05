package org.ADDU.JavaClasses;

import org.ADDU.Model.Client;
import org.ADDU.Model.Manager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.text.DecimalFormat;
import java.math.RoundingMode;

public class NewLoanPage extends JFrame{
    private JTextField desiredAmount;
    private JButton sendAmount;
    private Client client=new Client();
    private Manager manager=new Manager();
    private JLabel background;
    JPanel principal;
    JPanel contPanel = new JPanel();
    CardLayout cardLOUT=new CardLayout();
    private ScriereCitireManager scriereCitireManager=new ScriereCitireManager();
    private Font fondText=new Font("Calibri Light (Headings)", Font.BOLD,13);
    private JButton buttonBack;

    public NewLoanPage(Client client,Manager manager){
        this.client=client;
        this.manager=manager;
    }

    public JButton getButtonBack() {
        return buttonBack;
    }

    public JButton getButtonSend(){
        return sendAmount;
    }

    public JPanel returnPanel() {

        principal = new JPanel();
        principal.setSize(350, 300);
        principal.setBounds(0, 0, 750, 450);
        principal.setLayout(null);

        JLabel textAmount = new JLabel("Your desired amount:");
        textAmount.setFont(fondText);
        textAmount.setSize(200,28);
        textAmount.setBounds(360,125,200,28);
        principal.add(textAmount);

        desiredAmount = new JTextField();
        desiredAmount.setFont(fondText);
        desiredAmount.setDisabledTextColor(Color.WHITE);
        desiredAmount.setSize(200,28);
        desiredAmount.setBounds(360,155,200,28);
        principal.add(desiredAmount);

        sendAmount=new JButton("Send");
        sendAmount.setFont(fondText);
        sendAmount.setBounds(370,195,80,28);
        principal.add(sendAmount);

        buttonBack=new JButton("Back");
        buttonBack.setFont(fondText);
        buttonBack.setBounds(470,195,80,28);
        principal.add(buttonBack);

        sendAmount.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                scriereCitireManager.scriereManager(desiredAmount.getText(),client,manager);
                desiredAmount.setText("");
            }
        });

        ImageIcon backgroundImage = new ImageIcon("background.jpg");
        background = new JLabel("", backgroundImage,JLabel.CENTER);
        background.setBounds(0, 0, 665, 403);

        principal.add(background);

        return principal;
    }
}
