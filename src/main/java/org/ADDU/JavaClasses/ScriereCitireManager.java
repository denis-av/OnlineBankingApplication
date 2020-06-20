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

import org.ADDU.Exceptions.CouldNotFindLoan;
import org.ADDU.Model.Client;
import org.ADDU.Model.Manager;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class ScriereCitireManager {
    private int idManager;
    private String file;

    public ScriereCitireManager(String file){
        this.file=file;
    }

    public Manager returnManager(String MANAGERID){
        Manager items = new Manager();
        Manager manager = null;
        String id="1";
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
                                manager.addCard(test.getTextContent());
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
                                    manager.addLoan(loan);
                                    loan = new Loan();
                                }
                            }
                        }
                    }
                }
                if (manager.getId()==(Integer.parseInt(MANAGERID))) {
                    items=manager;manager=new Manager();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return items;
    }

    public void scriereManager(String amount, Client client, Manager managerClient){
        try{
            String filepath = file;
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            Document doc = docBuilder.parse(filepath);

            Node Client = doc.getFirstChild();


            Node manager = doc.getElementsByTagName("Manager").item(managerClient.getId()-1);
            Node loans = doc.getElementsByTagName("loans").item(managerClient.getId()-1);


            NodeList list = manager.getChildNodes();

            for (int i = 0; i < list.getLength(); i++) {

                Node node = list.item(i);

                if ("loans".equals(node.getNodeName())) {
                    Element loan=doc.createElement("loan");
                    Element desiredAmount=doc.createElement("desiredAmount");
                    desiredAmount.appendChild(doc.createTextNode(amount));
                    Element usernameClient=doc.createElement("usernameClient");
                    usernameClient.appendChild(doc.createTextNode(client.getUsername()));
                    loan.appendChild(desiredAmount);
                    loan.appendChild(usernameClient);
                    loans.appendChild(loan);
                }


            }

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File(filepath));
            transformer.transform(source, result);

            //System.out.println("Done");

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

    public void stergereLoanManager(Manager managerClient,String username,String dsrdAmount){
        Loan loan=new Loan();
        int ok = 0;

        try{
            String filepath = file;
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            Document doc = docBuilder.parse(filepath);

            Node Client = doc.getFirstChild();


            for(int j=0;j<doc.getElementsByTagName("loan").getLength();j++) {
                Node eachLoan = doc.getElementsByTagName("loan").item(j);

                NodeList list = eachLoan.getChildNodes();

                for (int i = 0; i < list.getLength(); i++) {

                    Node node = list.item(i);

                    if ("desiredAmount".equals(node.getNodeName())) {
                        loan.setDesiredAmount(node.getTextContent());
                    }

                    if ("usernameClient".equals(node.getNodeName())) {
                        loan.setUsernameClient(node.getTextContent());
                        if(loan.getUsernameClient().equals(username) && loan.getDesiredAmount().equals(dsrdAmount))
                        {
                            eachLoan.getParentNode().removeChild(eachLoan);
                            ok = 1;
                        }
                        loan = new Loan();
                    }
                }
            }

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File(filepath));
            transformer.transform(source, result);
            if (ok==0) throw new CouldNotFindLoan();
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
