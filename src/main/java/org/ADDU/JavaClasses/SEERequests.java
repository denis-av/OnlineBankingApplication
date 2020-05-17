package org.ADDU.JavaClasses;

import org.ADDU.Model.Manager;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class SEERequests extends JFrame {
    private JButton buttonBack;
    private JButton manageLoans;
    private Manager manager=new Manager();
    private JLabel background;
    private JPanel principal;
    private JTextField t11;
    private JTextField t22;
    private JTextField t33;
    private JTextField t44;
    private JTable table;
    private DefaultTableModel model;
    private JButton send=new JButton("Send");
    private JButton cancel=new JButton("Cancel");
    private JDialog dialog=new JDialog();
    private JPanel contPanel = new JPanel();
    private CardLayout cardLOUT=new CardLayout();
    private ScriereCitireManager scr=new ScriereCitireManager();
    private  ScriereCitireClient scrc=new ScriereCitireClient();
    private Font fondText=new Font("Calibri Light (Headings)", Font.BOLD,13);
    private Font fondText2=new Font("Calibri Light (Headings)", Font.BOLD,23);

    public SEERequests(Manager manager){
        this.manager=manager;
    }

    public JButton getButtonBack() {
        return buttonBack;
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



        model = new DefaultTableModel();
        model.addColumn("Username");
        model.addColumn("DesiredAmount");
        model.addColumn("Status");
        table = new JTable(model);
        table.setRowHeight(25);
        table.setBackground(Color.WHITE);
        table.setForeground(Color.BLACK);
        table.setFont(fondText);
        JTableHeader tab=table.getTableHeader();
        tab.setFont(fondText);
        for (int columnIndex = 0; columnIndex < table.getColumnCount(); columnIndex++)
        {
            table.getColumnModel().getColumn(columnIndex).setCellRenderer(centerRenderer);
        }

        buttonBack=new JButton("Back");
        buttonBack.setBounds(457,265,125,28);
        buttonBack.setFont(fondText);
        buttonBack.setBackground(Color.WHITE);
        principal.add(buttonBack);

        manageLoans=new JButton("Manage Loans");
        manageLoans.setBounds(280,265,145,28);
        manageLoans.setFont(fondText);
        manageLoans.setBackground(Color.WHITE);
        principal.add(manageLoans);

        JScrollPane sp=new JScrollPane(table);
        sp.setBounds(82,85,500,150);
        for(int i=0;i<manager.getLoans().size();i++)
        {
            row[0]=manager.getLoans().get(i).getUsernameClient();
            row[1]=manager.getLoans().get(i).getDesiredAmount();
            row[2]=manager.getLoans().get(i).getStatus();
            model.addRow(row);
            row=new String[3];
        }


        principal.add(sp);

        ImageIcon backgroundImage = new ImageIcon("src\\main\\resources\\recivedloanback.jpg");
        background = new JLabel("", backgroundImage,JLabel.CENTER);
        background.setBounds(0, 0, 665, 403);

        principal.add(background);
        return principal;
    }
}