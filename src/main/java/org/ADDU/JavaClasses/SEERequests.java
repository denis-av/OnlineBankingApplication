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
    private static String file2;
    private static String file;
    private ScriereCitireManager scr=new ScriereCitireManager(file);
    private  ScriereCitireClient scrc=new ScriereCitireClient(file2);
    private Font fondText=new Font("Calibri Light (Headings)", Font.BOLD,13);
    private Font fondText2=new Font("Calibri Light (Headings)", Font.BOLD,23);

    public SEERequests(Manager manager,String file,String file2){
        this.manager=manager;
        this.file=file;
        this.file2=file2;
        scr=new ScriereCitireManager(file);
        scrc=new ScriereCitireClient(file2);
    }

    public Manager getManager() {
        return manager;
    }

    public void setManager(Manager manager) {
        this.manager = manager;
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

    public JButton getButtonBack() {
        return buttonBack;
    }

    public JPanel returnPanel() {

        principal = new JPanel();

        principal = new JPanel();
        //principal.setSize(350, 300);
        principal.setBounds(0, 0, 665, 403);
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

        manageLoans.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JPanel pan=new JPanel();
                pan.setLayout(null);

                JLabel t1=new JLabel("Username");
                t1.setBounds(75,10,190,28);
                t11=new JTextField();
                t11.setBounds(75,38,190,28);

                JLabel t2=new JLabel("Desired Amount");
                t2.setBounds(75,66,190,28);
                t22=new JTextField();
                t22.setBounds(75,94,190,28);

                JLabel t3=new JLabel("Status");
                t3.setBounds(75,122,190,28);
                t33=new JTextField();
                t33.setBounds(75,150,190,28);

                JLabel t4=new JLabel("Message");
                t4.setBounds(75,178,190,28);
                t44=new JTextField();
                t44.setBounds(75,206,190,28);

                send.setBounds(85,255,75,28);
                send.setFont(fondText);
                send.setBackground(Color.WHITE);
                pan.add(send);

                cancel.setBounds(170,255,85,28);
                cancel.setFont(fondText);
                cancel.setBackground(Color.WHITE);
                pan.add(cancel);

                pan.add(t1);
                pan.add(t11);

                pan.add(t2);
                pan.add(t22);

                pan.add(t3);
                pan.add(t33);

                pan.add(t4);
                pan.add(t44);

                dialog.add(pan);

                dialog.setSize(350, 370);
                dialog.setLocationRelativeTo(null);
                dialog.setVisible(true);

            }
        });


        send.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(isNumeric(t22.getText()))
                    {
                        for(int indexRow=0;indexRow<table.getRowCount();indexRow++)
                            if(t11.getText().equals(model.getValueAt(indexRow,0)) && t22.getText().equals(model.getValueAt(indexRow,1)))
                                model.setValueAt(t33.getText(),indexRow,2);
                        scr.stergereLoanManager(manager,t11.getText(),t22.getText());
                        scrc.scriereClient(t22.getText(),t11.getText(),t33.getText(),t44.getText());
                        JOptionPane.showMessageDialog(dialog, "Your decision was sent!");
                    }
                else JOptionPane.showMessageDialog(principal, "You must introduce a number!");
                /*
                t11.setText("");
                t22.setText("");
                t33.setText("");
                t44.setText("");*/
            }
        });

        cancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dialog.setVisible(false);
                dialog.getContentPane().removeAll();
            }
        });

        return principal;
    }
}