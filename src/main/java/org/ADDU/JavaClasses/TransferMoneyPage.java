package org.ADDU.JavaClasses;

import org.ADDU.Exceptions.CouldNotFindBeneficiar;
import org.ADDU.Exceptions.CouldNotFindClient;
import org.ADDU.Model.Client;
import org.ADDU.Model.Manager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.text.DecimalFormat;
import java.math.RoundingMode;
import java.util.*;

public class TransferMoneyPage extends JFrame{

    private JTextField desiredAmount;
    private JButton send;
    private JLabel l1;
    private JTextField t1;
    private Client client1 = new Client();
    private Client beneficiar = new Client();
    private ArrayList<Client> client2 = new ArrayList<Client>();
    private JLabel background;
    private static String file;
    JPanel principal;
    JPanel contPanel = new JPanel();
    CardLayout cardLOUT=new CardLayout();

    private ScriereCitireClient scriereCitireClient;
    private Font fondText=new Font("Calibri Light (Headings)", Font.BOLD,13);
    private JButton buttonBack;

    public TransferMoneyPage(Client client1,ArrayList<Client> client2,String file){
        this.client1 = client1;
        this.client2 = client2;
        this.file=file;
        scriereCitireClient = new ScriereCitireClient(file);
    }

    public JButton getButtonBack() {
        return buttonBack;
    }

    public JButton getButtonSend(){
        return send;
    }

    public Client theBeneficiar(String x){
        for(int i=0; i<client2.size(); i++){
            if(x.equals(client2.get(i).getIban())) return client2.get(i);
        }
        throw new CouldNotFindBeneficiar();
    }

    public static boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            int d = Integer.parseInt(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    public JPanel returnPanel() {

        principal = new JPanel();
        principal.setSize(350, 300);
        principal.setBounds(0, 0, 750, 450);
        principal.setLayout(null);

        JLabel textAmount = new JLabel("Introduce the amount:");
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

        JLabel l1 = new JLabel("Introduce the beneficiary's IBAN:");
        l1.setFont(fondText);
        l1.setSize(200,28);
        l1.setBounds(360,185,220,28);
        principal.add(l1);

        t1 = new JTextField();
        t1.setFont(fondText);
        t1.setDisabledTextColor(Color.WHITE);
        t1.setSize(200,28);
        t1.setBounds(360,215,200,28);
        principal.add(t1);

        send=new JButton("Send");
        send.setFont(fondText);
        send.setBounds(370,255,80,28);
        principal.add(send);

        buttonBack=new JButton("Back");
        buttonBack.setFont(fondText);
        buttonBack.setBounds(470,255,80,28);
        principal.add(buttonBack);


        send.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                        int sumaClient, sumaBeneficiar;
                        beneficiar=theBeneficiar(t1.getText());
                        if(isNumeric(desiredAmount.getText()))
                        {
                            sumaClient = (Integer.parseInt(client1.getSold()) - Integer.parseInt(desiredAmount.getText()));
                            sumaBeneficiar = (Integer.parseInt(beneficiar.getSold()) + Integer.parseInt(desiredAmount.getText()));
                            scriereCitireClient.scriereClient(String.valueOf(sumaClient),client1);
                            scriereCitireClient.scriereClient(String.valueOf(sumaBeneficiar),beneficiar);
                            JOptionPane.showMessageDialog(principal, "Transfer made successfully!");
                        }
                        else JOptionPane.showMessageDialog(principal, "You must introduce a number!");
                        t1.setText("");
                        desiredAmount.setText("");
                }catch (RuntimeException exp){
                    throw new CouldNotFindBeneficiar();
                }
            }
        });

        ImageIcon backgroundImage = new ImageIcon("src\\main\\resources\\background.jpg");
        background = new JLabel("", backgroundImage,JLabel.CENTER);
        background.setBounds(0, 0, 665, 403);

        principal.add(background);

        return principal;
    }
}
