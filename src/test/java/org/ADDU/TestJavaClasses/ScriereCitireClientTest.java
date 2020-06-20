package org.ADDU.TestJavaClasses;

import org.ADDU.JavaClasses.*;
import org.ADDU.Model.Client;
import org.apache.commons.io.FileUtils;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import static org.junit.Assert.*;


public class ScriereCitireClientTest {

    private static LoginController CLIENT_TEST;

    private static final String USR_CLNT="sebi99";
    private static final String PSS_CLNT="esteban";
    private static final String CRD_CLNT="1234";

    private static final String PATH_CLNT="src/test/resources/clientFileTest.xml";
    private static final String PATH_MNG="src/test/resources/managerTest.xml";

    ScriereCitireClient SCC=new ScriereCitireClient(PATH_CLNT);

    @BeforeClass
    public static void setupClass() throws Exception {
        FileSystemService.APPLICATION_FOLDER = ".test-example";

        FileUtils.copyURLToFile(OnlineBankingApplication.class.getClassLoader().getResource("clientFileTest.xml"), new File("src/test/resources/clientFileTest.xml"));
        FileUtils.copyURLToFile(OnlineBankingApplication.class.getClassLoader().getResource("managerTest.xml"), new File("src/test/resources/managerTest.xml"));
    }

    @Before
    public void setUp() throws Exception {
        FileUtils.copyURLToFile(OnlineBankingApplication.class.getClassLoader().getResource("clientFileTest.xml"), new File("src/test/resources/clientFileTest.xml"));
        FileUtils.copyURLToFile(OnlineBankingApplication.class.getClassLoader().getResource("managerTest.xml"), new File("src/test/resources/managerTest.xml"));

        CLIENT_TEST=new LoginController(USR_CLNT,LoginController.encodePassword(USR_CLNT,PSS_CLNT),CRD_CLNT,"src/test/resources/clientFileTest.xml");
    }

    @Test
    public void returnClientsTest() throws IOException, SAXException, ParserConfigurationException {
        int NumberClients=-1;
        try {
            File inputFile = new File(PATH_CLNT);

            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputFile);
            doc.getDocumentElement().normalize();

            Node Client = doc.getFirstChild();
            NumberClients=doc.getElementsByTagName("client").getLength();
        } catch (Exception e) {
            e.printStackTrace();
        }
        assertEquals(NumberClients,SCC.returnClient().size());
    }

    @Test
    public void scrieClientTest1(){
        Client CLNT=CLIENT_TEST.returnClient();
        String InitialAmount=CLNT.getSold();
        SCC.scriereClient("theNewSold",CLNT);
        ArrayList<Client> CLNT2=SCC.returnClient();
        for(int i=0;i<CLNT2.size();i++)
            if(CLNT2.get(i).getUsername().equals(CLNT.getUsername())) CLNT=CLNT2.get(i);
        assertNotEquals(InitialAmount,CLNT.getSold());
        SCC.scriereClient(InitialAmount,CLNT);
    }

    @Test
    public void scrieClientTest2(){
        Client CLNT=CLIENT_TEST.returnClient();
        int initialLoan=CLNT.getLoans().size();
        Loan ln=new Loan();
        ln.setDesiredAmount("test");
        ln.setMessage("test");
        ln.setStatus("test");
        ln.setUsernameClient(CLNT.getUsername());
        SCC.scriereClient(ln.getDesiredAmount(),ln.getUsernameClient(),ln.getStatus(),ln.getMessage());
        ArrayList<Client> CLNT2=SCC.returnClient();
        for(int i=0;i<CLNT2.size();i++)
            if(CLNT2.get(i).getUsername().equals(CLNT.getUsername())) CLNT=CLNT2.get(i);
        assertEquals(initialLoan+1,CLNT.getLoans().size());
        SCC.stergereLoanClient(CLNT.getUsername(),ln.getDesiredAmount());
    }

}
