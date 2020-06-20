package org.ADDU.TestJavaClasses;

import org.ADDU.Exceptions.CouldNotFindClient;
import org.ADDU.Exceptions.CouldNotFindManager;
import org.ADDU.JavaClasses.FileSystemService;
import org.ADDU.JavaClasses.Loan;
import org.ADDU.JavaClasses.LoginController;
import org.ADDU.JavaClasses.OnlineBankingApplication;
import org.ADDU.Model.Client;
import org.ADDU.Model.Manager;
import org.apache.commons.io.FileUtils;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import static org.junit.Assert.*;


public class LoginControllerTest {
    private static OnlineBankingApplication OBA;
    private static LoginController CLIENT_TEST;
    private static LoginController MANAGER_TEST;
    private static LoginController CLIENT_TEST1;
    private static LoginController MANAGER_TEST1;

    private static final String USR_CLNT="sebi99";
    private static final String PSS_CLNT="esteban";
    private static final String CRD_CLNT="1234";

    private static final String USR_MAN="denisav";
    private static final String PSS_MAN="cevrei";


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

        CLIENT_TEST1=new LoginController("test",LoginController.encodePassword("test","testParola"),"1234","src/test/resources/clientFileTest.xml");
        CLIENT_TEST=new LoginController(USR_CLNT,LoginController.encodePassword(USR_CLNT,PSS_CLNT),CRD_CLNT,"src/test/resources/clientFileTest.xml");
        MANAGER_TEST=new LoginController(USR_MAN,LoginController.encodePassword(USR_MAN,PSS_MAN),"src/test/resources/managerTest.xml");
        MANAGER_TEST1=new LoginController("test",LoginController.encodePassword("test","testParola"),"src/test/resources/managerTest.xml");
    }


    @Test(expected = CouldNotFindClient.class)
    public void returnClientTest(){
        assertNotNull(CLIENT_TEST.returnClient());
        CLIENT_TEST1.returnClient();
    }

    @Test(expected = CouldNotFindManager.class)
    public void returnManagerTest(){
        assertNotNull(MANAGER_TEST.returnManager());
        MANAGER_TEST1.returnManager();
    }

    @Test
    public void parseClientXMLTest(){
        assertNotNull(LoginController.parseClientXML(USR_CLNT,LoginController.encodePassword(USR_CLNT,PSS_MAN),CRD_CLNT));
        assertNull(LoginController.parseClientXML("test",LoginController.encodePassword("test","testParola"),"1234").getUsername());
    }

    @Test
    public void parseManagerXMLTest(){
        assertNotNull(LoginController.parseManagerXML(USR_MAN,LoginController.encodePassword(USR_MAN,PSS_MAN)).getUsername());
        assertNull(LoginController.parseManagerXML("test",LoginController.encodePassword("test","testParola")).getUsername());
    }

    @Test
    public void testPasswordEncoding() {
        assertNotEquals("sebi99", LoginController.encodePassword("sebi99", "esteban"));
    }

}
