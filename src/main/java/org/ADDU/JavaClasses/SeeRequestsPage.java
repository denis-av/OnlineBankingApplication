package org.ADDU.JavaClasses;

import org.ADDU.Model.Client;
import org.ADDU.Model.Manager;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.text.DecimalFormat;
import java.math.RoundingMode;

public class SeeRequestsPage extends JFrame{
    private JTextField desiredAmount;
    private JButton sendAmount;
    private Client client=new Client();
    private Manager manager=new Manager();
    private JLabel background;
    JPanel principal;
    JPanel contPanel = new JPanel();
    CardLayout cardLOUT=new CardLayout();
    private Font fondText=new Font("Calibri Light (Headings)", Font.BOLD,13);
    private JButton buttonBack;
    //private JButton logoutButton;

    public SeeRequestsPage(Client client){
        this.client=client;
    }

    public JButton getButtonBack() {
        return buttonBack;
    }

    public JButton getButtonSend(){
        return sendAmount;
    }

    public JPanel returnPanel() {

        principal = new JPanel();

        principal = new JPanel();
        principal.setSize(350, 300);
        principal.setBounds(0, 0, 750, 450);
        principal.setLayout(null);

        String[] row = new String[3];

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment( JLabel.CENTER );

        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Desired Amount");
        model.addColumn("Status");
        model.addColumn("Message");
        JTable table = new JTable(model);
        table.setRowHeight(25);
        table.setBackground(Color.WHITE);
        table.setForeground(Color.BLACK);

        for (int columnIndex = 0; columnIndex < table.getColumnCount(); columnIndex++)
        {
            table.getColumnModel().getColumn(columnIndex).setCellRenderer(centerRenderer);
        }

        buttonBack = new JButton("Back");
        buttonBack.setBounds(457,265,125,28);
        buttonBack.setFont(fondText);
        buttonBack.setBackground(Color.WHITE);
        principal.add(buttonBack);

        JScrollPane sp=new JScrollPane(table);
        sp.setBounds(82,85,500,150);
        for(int i=0;i<client.getLoans().size();i++)
        {
            Loan l=client.getLoans().get(i);
            row[0]=l.getDesiredAmount();
            row[1]=l.getStatus();
            row[2]=l.getMessage();
            model.addRow(row);
            row=new String[3];
        }


        principal.add(sp,CENTER_ALIGNMENT);

        ImageIcon backgroundImage = new ImageIcon("C:\\Users\\Diana Udisteanu\\Desktop\\ProiectFis\\OnlineBankingApplication\\src\\main\\resources\\myloansback.jpg");
        background = new JLabel("", backgroundImage,JLabel.CENTER);
        background.setBounds(0, 0, 665, 403);

        principal.add(background);

        return principal;
    }
}