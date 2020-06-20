package org.ADDU.JavaClasses;


import javax.xml.namespace.QName;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.ADDU.Exceptions.CouldNotFindClient;
import org.ADDU.Exceptions.CouldNotFindManager;
import org.ADDU.Model.Client;
import org.ADDU.Model.Manager;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Iterator;

public class LoginController {
    private static String file;
    private String username;
    private String password;
    private String cardNumber;
    private Manager test=new Manager();
    private Client testClient=new Client();

    public LoginController(String username, String password,String file) {
        this.username = username;
        this.password = password;
        this.file=file;
        test =this.parseManagerXML(this.username,this.password);
    }


    public Manager returnManager() throws CouldNotFindManager{
        if(test.getUsername()!=null) return test;
        else throw new CouldNotFindManager();
    }

    public LoginController(String username, String password, String cardNumber,String file){
        this.username = username;
        this.password = password;
        this.cardNumber = cardNumber;
        this.file=file;
        //testClient=this.readConfig(fileClient,this.cardNumber,this.username,this.password);
        testClient=this.parseClientXML(this.username,this.password,this.cardNumber);
    }


    public Client returnClient() throws CouldNotFindClient {
        if(testClient.getUsername()!=null) return testClient;
        else throw new CouldNotFindClient();
    }

    public static Client parseClientXML(String username, String password, String cardNumber){
        Client items = new Client();
        Client nullItem=new Client();
        Client client = null;
        String loanId;
        String desiredAmount;
        String status;
        String message;
        String usernameClient;
        Loan loan=new Loan();
        try {
            client=new Client();

            File inputFile = new File(file);

            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputFile);
            doc.getDocumentElement().normalize();

            Node Client = doc.getFirstChild();
            for(int j=0;j<doc.getElementsByTagName("client").getLength();j++) {
                Node eachClient = doc.getElementsByTagName("client").item(j);

                NamedNodeMap attr = eachClient.getAttributes();
                Node nodeAttr = attr.getNamedItem("cardNumber");
                client.setCardNumber(nodeAttr.getTextContent());

                NodeList list = eachClient.getChildNodes();

                for (int i = 0; i < list.getLength(); i++) {

                    Node node = list.item(i);

                    if ("username".equals(node.getNodeName())) {
                        client.setUsername(node.getTextContent());
                    }
                    if ("password".equals(node.getNodeName())) {
                        client.setPassword(node.getTextContent());
                    }
                    if ("firstName".equals(node.getNodeName())) {
                        client.setFirstName(node.getTextContent());
                    }
                    if ("lastName".equals(node.getNodeName())) {
                        client.setLastName(node.getTextContent());
                    }
                    if ("iban".equals(node.getNodeName())) {
                        client.setIban(node.getTextContent());
                    }
                    if ("sold".equals(node.getNodeName())) {
                        client.setSold(node.getTextContent());
                    }
                    if ("managerID".equals(node.getNodeName())) {
                        client.setManagerId(node.getTextContent());
                    }
                    if ("loans".equals(node.getNodeName())) {
                        NodeList loans = node.getChildNodes();
                        for (int k = 0; k < loans.getLength(); k++) {
                            NodeList loans2 = loans.item(k).getChildNodes();
                            for (int p = 0; p < loans2.getLength(); p++) {
                                Node test=loans2.item(p);

                                if ("desiredAmount".equals(test.getNodeName())) {
                                    loan.setDesiredAmount(test.getTextContent());
                                }
                                if ("status".equals(test.getNodeName())) {
                                    loan.setStatus(test.getTextContent());
                                }
                                if ("message".equals(test.getNodeName())) {
                                    loan.setMessage(test.getTextContent());
                                }
                                if ("usernameClient".equals(test.getNodeName())) {
                                    loan.setUsernameClient(test.getTextContent());
                                    if(client.getUsername().equals(username))client.addLoan(loan);
                                    loan = new Loan();
                                }
                            }
                        }
                    }
                }
                if (client.getUsername().equals(username) && client.getPassword().equals(password) && client.getCardNumber().equals(cardNumber)) {
                    items = client;client=new Client();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return items;
    }

    public static Manager parseManagerXML(String username, String password){
        Manager items = new Manager();
        Manager manager = null;
        Loan loan=new Loan();
        try {
            manager=new Manager();
            File inputFile = new File(file);

            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputFile);
            doc.getDocumentElement().normalize();

            Node Manager = doc.getFirstChild();
            for(int j=0;j<doc.getElementsByTagName("Manager").getLength();j++) {
                Node eachClient = doc.getElementsByTagName("Manager").item(j);

                NamedNodeMap attr = eachClient.getAttributes();
                Node nodeAttr = attr.getNamedItem("id");
                manager.setId(Integer.parseInt(nodeAttr.getTextContent()));

                NodeList list = eachClient.getChildNodes();

                for (int i = 0; i < list.getLength(); i++) {

                    Node node = list.item(i);

                    if ("username".equals(node.getNodeName())) {
                        manager.setUsername(node.getTextContent());
                    }

                    if ("password".equals(node.getNodeName())) {
                        manager.setPassword(node.getTextContent());
                    }
                    if ("firstName".equals(node.getNodeName())) {
                        manager.setFirstName(node.getTextContent());
                    }
                    if ("lastName".equals(node.getNodeName())) {
                        manager.setLastName(node.getTextContent());
                    }
                    if("cards".equals(node.getNodeName())){
                        NodeList cards = node.getChildNodes();
                        for (int p = 0; p < cards.getLength(); p++){
                            Node test=cards.item(p);
                            if("cardNumber".equals(test.getNodeName())){
                                if(manager.getUsername().equals(username))manager.addCard(test.getTextContent());
                            }
                        }
                    }
                    if ("loans".equals(node.getNodeName())) {
                        NodeList loans = node.getChildNodes();
                        for (int k = 0; k < loans.getLength(); k++) {
                            NodeList loans2 = loans.item(k).getChildNodes();
                            for (int p = 0; p < loans2.getLength(); p++) {
                                Node test=loans2.item(p);

                                if ("desiredAmount".equals(test.getNodeName())) {
                                    loan.setDesiredAmount(test.getTextContent());
                                }
                                if ("status".equals(test.getNodeName())) {
                                    loan.setStatus(test.getTextContent());
                                }
                                if ("message".equals(test.getNodeName())) {
                                    loan.setMessage(test.getTextContent());
                                }
                                if ("usernameClient".equals(test.getNodeName())) {
                                    loan.setUsernameClient(test.getTextContent());
                                    if(manager.getUsername().equals(username))manager.addLoan(loan);
                                    loan = new Loan();
                                }
                            }
                        }
                    }
                }
                if (manager.getUsername().equals(username) && manager.getPassword().equals(password)) {
                    items = manager;manager=new Manager();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return items;
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

}
