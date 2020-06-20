package org.ADDU.JavaClasses;

import org.ADDU.Model.Client;
import org.ADDU.Model.Manager;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.util.ArrayList;

public class SeeMyClients extends JFrame {
    private JButton buttonBack;
    private Manager manager=new Manager();
    private JLabel background;
    private JPanel principal;
    private ArrayList<Client> myClients=new ArrayList<Client>();
    private JPanel contPanel = new JPanel();
    private CardLayout cardLOUT=new CardLayout();
    private Font fondText=new Font("Calibri Light (Headings)", Font.BOLD,13);
    private Font fondText2=new Font("Calibri Light (Headings)", Font.BOLD,23);

    public SeeMyClients(Manager manager, ArrayList<Client> myClients){
        this.manager=manager;
        this.myClients=myClients;
    }

    public Manager getManager() {
        return manager;
    }

    public void setManager(Manager manager) {
        this.manager = manager;
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


        String[] row = new String[4];

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment( JLabel.CENTER );



        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("First name");
        model.addColumn("Last Name");
        model.addColumn("Username");
        model.addColumn("IBAN");
        JTable table = new JTable(model);
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

        JScrollPane sp=new JScrollPane(table);
        sp.setBounds(82,85,500,150);
        for(int i=0;i<myClients.size();i++)
        {
            Client client=myClients.get(i);
            row[0]=client.getFirstName();
            row[1]=client.getLastName();
            row[2]=client.getUsername();
            row[3]=client.getIban();
            model.addRow(row);
            row=new String[4];
        }


        principal.add(sp);

        ImageIcon backgroundImage = new ImageIcon("src\\main\\resources\\myclientsback.jpg");
        background = new JLabel("", backgroundImage,JLabel.CENTER);
        background.setBounds(0, 0, 665, 403);

        principal.add(background);

        return principal;
    }
}
