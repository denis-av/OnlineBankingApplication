package org.ADDU.JavaClasses;


import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.ADDU.Exceptions.CouldNotFindClient;
import org.ADDU.Model.Client;
import org.ADDU.Model.Manager;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import java.util.*;

public class ScriereCitireClient {


    public ArrayList<Client> returnClient(){

        ArrayList<Client>  array = new ArrayList<Client>();
        Client items = new Client();
        Client client = null;
        Loan loan=new Loan();
        try {
            client = new Client();
            File inputFile = new File("src\\main\\resources\\clientFile.xml");

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
                                    client.addLoan(loan);
                                    loan = new Loan();
                                }
                            }
                        }
                    }
                }
                array.add(client);
                client = new Client();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return array;
    }


    public void scriereClient(String amount, Client client1){
        try{
            String filepath = "src\\main\\resources\\clientFile.xml";
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            Document doc = docBuilder.parse(filepath);

            Node Client = doc.getFirstChild();

            for(int j=0; j<doc.getElementsByTagName("client").getLength() ;j++) {
                Node client = doc.getElementsByTagName("client").item(j);
                NodeList list = client.getChildNodes();

                for (int i = 0; i < list.getLength(); i++) {

                    Node node = list.item(i);
                    Node nume = list.item(1);

                    if ("sold".equals(node.getNodeName()) && client1.getUsername().equals(nume.getTextContent())) {
                        node.setTextContent(amount);
                    }
                }

                TransformerFactory transformerFactory = TransformerFactory.newInstance();
                Transformer transformer = transformerFactory.newTransformer();
                DOMSource source = new DOMSource(doc);
                StreamResult result = new StreamResult(new File(filepath));
                transformer.transform(source, result);

                }

        }catch (ParserConfigurationException pce) {
            pce.printStackTrace();
        } catch (TransformerException tfe) {
            tfe.printStackTrace();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        } catch (SAXException sae) {
            sae.printStackTrace();
        }
    }

    public void scriereClient(String amount, String username,String statuss,String Message){
        int ok=0;
        try{
            String filepath = "src\\main\\resources\\clientFile.xml";
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            Document doc = docBuilder.parse(filepath);

            Node Client = doc.getFirstChild();

            for(int j=0; j<doc.getElementsByTagName("client").getLength() ;j++) {
                Node client = doc.getElementsByTagName("client").item(j);
                NodeList list = client.getChildNodes();
                Node usr=list.item(1);

                for (int i = 0; i < list.getLength(); i++) {

                    Node node = list.item(i);

                    if ("loans".equals(node.getNodeName()) && username.equals(usr.getTextContent())) {
                        Element loan=doc.createElement("loan");
                        Element desiredAmount=doc.createElement("desiredAmount");
                        desiredAmount.appendChild(doc.createTextNode(amount));
                        Element status=doc.createElement("status");
                        status.appendChild(doc.createTextNode(statuss));
                        Element message=doc.createElement("message");
                        message.appendChild(doc.createTextNode(Message));
                        Element usernameClient=doc.createElement("usernameClient");
                        usernameClient.appendChild(doc.createTextNode(username));
                        loan.appendChild(desiredAmount);
                        loan.appendChild(status);
                        loan.appendChild(message);
                        loan.appendChild(usernameClient);
                        node.appendChild(loan);ok=1;
                    }

                }

                TransformerFactory transformerFactory = TransformerFactory.newInstance();
                Transformer transformer = transformerFactory.newTransformer();
                DOMSource source = new DOMSource(doc);
                StreamResult result = new StreamResult(new File(filepath));
                transformer.transform(source, result);

            }
            if(ok==0) throw new CouldNotFindClient();
        }catch (ParserConfigurationException pce) {
            pce.printStackTrace();
        } catch (TransformerException tfe) {
            tfe.printStackTrace();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        } catch (SAXException sae) {
            sae.printStackTrace();
        }
    }
}

