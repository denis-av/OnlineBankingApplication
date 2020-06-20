package org.ADDU.TestJavaClasses;

import org.ADDU.Exceptions.CouldNotFindLoan;
import org.ADDU.JavaClasses.*;
import org.ADDU.Model.Client;
import org.ADDU.Model.Manager;
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

public class ScriereCitireManagerTest {

    private static final String USR_MAN="denisav";
    private static final String PSS_MAN="cevrei";

    private static final String USR_CLNT="sebi99";
    private static final String PSS_CLNT="esteban";
    private static final String CRD_CLNT="1234";

    private static LoginController MANAGER_TEST;
    private static LoginController CLIENT_TEST;

    private static final String PATH_CLNT="src/test/resources/clientFileTest.xml";
    private static final String PATH_MNG="src/test/resources/managerTest.xml";

    ScriereCitireManager SCM;

    @BeforeClass
    public static void setupClass() throws Exception {
        FileSystemService.APPLICATION_FOLDER = ".test-example";

        FileUtils.copyURLToFile(OnlineBankingApplication.class.getClassLoader().getResource("managerTest.xml"), new File(PATH_MNG));
    }

    @Before
    public void setUp() throws Exception {
        FileUtils.copyURLToFile(OnlineBankingApplication.class.getClassLoader().getResource("managerTest.xml"), new File(PATH_MNG));

        MANAGER_TEST=new LoginController(USR_MAN,LoginController.encodePassword(USR_MAN,PSS_MAN),PATH_MNG);
        SCM=new ScriereCitireManager(PATH_MNG);
    }


    @Test
    public void returnManagerTest(){
        Manager mng=MANAGER_TEST.returnManager();
        Manager test=SCM.returnManager(String.valueOf(mng.getId()));
        assertNotEquals(mng,test);
    }

    @Test
    public void scriereManagerTest(){
        CLIENT_TEST=new LoginController(USR_CLNT,LoginController.encodePassword(USR_CLNT,PSS_CLNT),CRD_CLNT,"src/test/resources/clientFileTest.xml");
        Client CLNT=CLIENT_TEST.returnClient();
        Manager mng=MANAGER_TEST.returnManager();
        Loan ln=new Loan();
        ln.setDesiredAmount("test");
        ln.setMessage("test");
        ln.setStatus("test");
        ln.setUsernameClient(CLNT.getUsername());
        int InitialSizeLoan=mng.getLoans().size();
        SCM.scriereManager("theAmount",CLNT,mng);
        mng=SCM.returnManager(String.valueOf(CLNT.getManagerId()));
        assertNotEquals(InitialSizeLoan,mng.getLoans().size());
    }

    @Test(expected = CouldNotFindLoan.class)
    public void stergeLoanManagerTest(){
        CLIENT_TEST=new LoginController(USR_CLNT,LoginController.encodePassword(USR_CLNT,PSS_CLNT),CRD_CLNT,"src/test/resources/clientFileTest.xml");
        Client CLNT=CLIENT_TEST.returnClient();
        Manager mng=MANAGER_TEST.returnManager();
        Loan ln=new Loan();
        Loan ln1=new Loan();
        ln.setDesiredAmount("test");
        ln.setMessage("test");
        ln.setStatus("test");
        ln.setUsernameClient("test");
        if(mng.getLoans().size()>0) ln=mng.getLoans().get(0);
        int initialLoanSize=mng.getLoans().size();
        SCM.stergereLoanManager(mng,ln.getUsernameClient(),ln.getDesiredAmount());
        SCM.stergereLoanManager(mng,ln1.getUsernameClient(),ln1.getDesiredAmount());
        mng=SCM.returnManager(String.valueOf(CLNT.getManagerId()));
        assertEquals(initialLoanSize-1,mng.getLoans().size());
        SCM.scriereManager(ln.getDesiredAmount(),CLNT,mng);
    }

}
