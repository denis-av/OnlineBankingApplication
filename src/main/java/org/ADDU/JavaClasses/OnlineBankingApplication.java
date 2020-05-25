package org.ADDU.JavaClasses;

import org.apache.commons.io.FileUtils;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.awt.*;
import java.awt.event.*;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class OnlineBankingApplication extends JFrame{
    //panel for login
    private JPanel login;

    //username
    private JLabel screenUsername;
    private JTextField username;

    //password
    private JLabel screenPassword;
    private JPasswordField password;

    //card number
    private JLabel screenCard;
    private JTextField cardNumber;

    //login button
    private JButton loginButton;


    private JLabel screenResult;
    //the user - which will be
    //A manager (if he introduce username & password)
    //A client (if he introduce username & password & card number
    public LoginController user;

    //the background - which will be an image
    private JLabel background;

    //panel for CardLayout
    JPanel contPanel = new JPanel();

    //2 fonts
    private Font fondText = new Font("Calibri Light (Headings)", Font.BOLD, 13);
    private Font fondText2 = new Font("Calibri Light (Headings)", Font.BOLD, 15);

    //The CardLayout which will let us to swich the screen
    private CardLayout cardLOUT = new CardLayout();

    ClientMainInterface clientMainInterface;
    ManagerMainInterface managerMainInterface;

    JDialog messText=new JDialog();
    JLabel message=new JLabel();

    //The frame for application
    JFrame mainFrame = new JFrame("Online Banking Application");

    //Constructor
    public OnlineBankingApplication() throws IOException {
        copyData();
        initComponents();
    }

    public void copyData() throws IOException {
        FileUtils.copyURLToFile(OnlineBankingApplication.class.getClassLoader().getResource("clientFile.xml"), new File("src/main/resources/clientFile.xml"));
        FileUtils.copyURLToFile(OnlineBankingApplication.class.getClassLoader().getResource("background.jpg"), new File("src/main/resources/background.jpg"));
        FileUtils.copyURLToFile(OnlineBankingApplication.class.getClassLoader().getResource("backf.jpg"), new File("src/main/resources/backf.jpg"));
        FileUtils.copyURLToFile(OnlineBankingApplication.class.getClassLoader().getResource("manager.xml"), new File("src/main/resources/manager.xml"));
        FileUtils.copyURLToFile(OnlineBankingApplication.class.getClassLoader().getResource("myclientsback.jpg"), new File("src/main/resources/myclientsback.jpg"));
        FileUtils.copyURLToFile(OnlineBankingApplication.class.getClassLoader().getResource("myloansback.jpg"), new File("src/main/resources/myloansback.jpg"));
        FileUtils.copyURLToFile(OnlineBankingApplication.class.getClassLoader().getResource("recivedloanback.jpg"), new File("src/main/resources/recivedloanback.jpg"));
    }

    public static String encodePassword(String salt, String password) {
        MessageDigest md = getMessageDigest();
        md.update(salt.getBytes(StandardCharsets.UTF_8));

        byte[] hashedPassword = md.digest(password.getBytes(StandardCharsets.UTF_8));

        // This is the way a password should be encoded when checking the credentials
        return new String(hashedPassword, StandardCharsets.UTF_8)
                .replace("\"", ""); //to be able to save in JSON format
    }

    private static MessageDigest getMessageDigest() {
        MessageDigest md;
        try {
            md = MessageDigest.getInstance("SHA-512");
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalStateException("SHA-512 does not exist!");
        }
        return md;
    }


    //A method which will create the screen
    public void initComponents() {

        contPanel.setLayout(cardLOUT);

        login = new JPanel();
        login.setSize(350, 450);
        login.setLayout(null);
        login.setBackground(new Color(0, 0, 0, 0));
        login.setBounds(0, 0, 665, 403);

        screenUsername = new JLabel("Enter your username!*");
        screenUsername.setBounds(380, 73, 200, 30);
        screenUsername.setFont(fondText);
        login.add(screenUsername);

        username = new JTextField();
        username.setSize(200, 50);
        username.setBounds(380, 100, 200, 30);
        login.add(username);

        screenPassword = new JLabel("Enter your password!*");
        screenPassword.setBounds(380, 138, 200, 30);
        screenPassword.setFont(fondText);
        login.add(screenPassword);

        password = new JPasswordField();
        password.setSize(200, 50);
        password.setBounds(380, 165, 200, 30);
        login.add(password);

        screenCard = new JLabel("Enter your card number!");
        screenCard.setBounds(380, 213, 200, 30);
        screenCard.setFont(fondText);
        login.add(screenCard);

        cardNumber = new JTextField();
        cardNumber.setSize(200, 50);
        cardNumber.setBounds(380, 240, 200, 30);
        login.add(cardNumber);

        loginButton = new JButton("Login");
        loginButton.setSize(100, 20);
        loginButton.setBounds(430, 290, 100, 25);
        loginButton.setFont(fondText);
        login.add(loginButton);

        contPanel.setBounds(0,0,665,403);
        contPanel.add(login,"1");//main screen
        cardLOUT.show(contPanel,"1");//main screen will be show

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(username.getText().equals("") || password.getText().equals("")){
                    JOptionPane.showMessageDialog(mainFrame,
                            "Non-existing credentials",
                            "Failed login",
                            JOptionPane.WARNING_MESSAGE);
                }else if (!cardNumber.getText().equals("")) {
                    try{
                            user = new LoginController(username.getText(),encodePassword(username.getText(),password.getText()), cardNumber.getText());

                            clientMainInterface=new ClientMainInterface(user.returnClient());

                            contPanel.add(clientMainInterface.returnPanelMainClinet(),"2");//the main screen for client operations

                            cardLOUT.show(contPanel,"2");

                            clientMainInterface.getLogoutButton().addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    login.add(background);
                                    username.setText("");
                                    password.setText("");
                                    cardNumber.setText("");
                                    cardLOUT.show(contPanel,"1");
                                }
                            });
                    }catch (RuntimeException exp){
                        JOptionPane.showMessageDialog(mainFrame,
                                "Invalid username/password/card number!");
                    }
                }
                else if (cardNumber.getText().equals("")){
                    try{
                            user = new LoginController(username.getText(), encodePassword(username.getText(),password.getText()));
                                managerMainInterface=new ManagerMainInterface(user.returnManager());

                                contPanel.add(managerMainInterface.returnPanelMainManager(),"Main interface for managers");

                                cardLOUT.show(contPanel,"Main interface for managers");

                                managerMainInterface.getLogoutButton().addActionListener(new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent e) {
                                        login.add(background);
                                        username.setText("");
                                        password.setText("");
                                        cardNumber.setText("");
                                        cardLOUT.show(contPanel,"1");
                                    }
                                });
                        }catch (RuntimeException e1){
                            JOptionPane.showMessageDialog(mainFrame,
                                    "Invalid username or password");
                    }
                }
            }
        });

        //creating the background
        ImageIcon backgroundImage = new ImageIcon("src\\main\\resources\\background.jpg");
        background = new JLabel("", backgroundImage, JLabel.CENTER);
        background.setBounds(0, 0, 665, 403);

        //adding all to the frame
        mainFrame.setSize(665, 403);
        mainFrame.add(contPanel);
        mainFrame.add(background);
        mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        mainFrame.setLocationRelativeTo(null);
        mainFrame.pack();
        mainFrame.setVisible(true);

    }

    public LoginController getUser() {
        return user;
    }

    public static void main(String[] args){
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    new OnlineBankingApplication();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
