package org.ADDU.TestJavaClasses;

import org.ADDU.Exceptions.CouldNotFindClient;
import org.ADDU.Exceptions.CouldNotFindManager;
import org.ADDU.Exceptions.CouldNotFindYourManager;
import org.ADDU.JavaClasses.*;
import org.ADDU.Model.Manager;
import org.apache.commons.io.FileUtils;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;


import java.io.File;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import static org.junit.Assert.*;

public class NewLoanPageTest {
    private static LoginController CLIENT_TEST;
    private static LoginController MANAGER_TEST;
    private static LoginController MANAGER_TEST1;


    private static final String USR_CLNT="sebi99";
    private static final String PSS_CLNT="esteban";
    private static final String CRD_CLNT="1234";

    private static final String USR_MAN="denisav";
    private static final String PSS_MAN="cevrei";

    private static final String PATH_CLNT="src/test/resources/clientFileTest.xml";
    private static final String PATH_MNG="src/test/resources/managerTest.xml";

    private static NewLoanPage NLP;
    private static NewLoanPage NLPtest;


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
        MANAGER_TEST=new LoginController(USR_MAN,LoginController.encodePassword(USR_MAN,PSS_MAN),"src/test/resources/managerTest.xml");
        MANAGER_TEST1=new LoginController("dianaudi",LoginController.encodePassword("dianaudi","cevreitu"),PATH_MNG);

        NLP=new NewLoanPage(CLIENT_TEST.returnClient(),MANAGER_TEST.returnManager(),PATH_MNG);
    }

    @Test(expected = CouldNotFindYourManager.class)
    public void theManagerTest(){
        assertEquals(NLP.theManager(),MANAGER_TEST.returnManager());
        assertNotNull(NLP.theManager());
        Manager mng=new Manager();
        mng.setUsername(null);
        NLPtest=new NewLoanPage(CLIENT_TEST.returnClient(),mng,PATH_MNG);
        NLPtest.theManager();
        ;    }

    @Test
    public void returnPanel(){
        assertNotNull(NLP.returnPanel());
    }

    @Test
    public void isNumericTest(){
        assertTrue(NLP.isNumeric("123"));
        assertFalse(NLP.isNumeric("test"));
    }

}
