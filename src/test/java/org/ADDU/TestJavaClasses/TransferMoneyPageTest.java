package org.ADDU.TestJavaClasses;

import org.ADDU.Exceptions.CouldNotFindBeneficiar;
import org.ADDU.JavaClasses.*;
import org.apache.commons.io.FileUtils;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.File;

import static org.junit.Assert.*;
import static org.junit.Assert.assertFalse;

public class TransferMoneyPageTest {

    private static LoginController CLIENT_TEST;

    private static final String USR_CLNT="sebi99";
    private static final String PSS_CLNT="esteban";
    private static final String CRD_CLNT="1234";

    private static final String PATH_CLNT="src/test/resources/clientFileTest.xml";
    private static final String PATH_MNG="src/test/resources/managerTest.xml";

    private static TransferMoneyPage TMP;
    private ClientMainInterface thisClient;

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
        thisClient=new ClientMainInterface(CLIENT_TEST.returnClient(),PATH_CLNT,PATH_MNG);
        TMP=new TransferMoneyPage(CLIENT_TEST.returnClient(),thisClient.theClient.returnClient(),PATH_CLNT);
    }

    @Test(expected = CouldNotFindBeneficiar.class)
    public void theBeneficiarTest(){
        assertNotNull(TMP.theBeneficiar(CLIENT_TEST.returnClient().getIban()));
        TMP.theBeneficiar("test");
    }

    @Test
    public void returnPanel(){
        assertNotNull(TMP.returnPanel());
    }

    @Test
    public void isNumericTest(){
        assertTrue(TMP.isNumeric("123"));
        assertFalse(TMP.isNumeric("test"));
    }

}
