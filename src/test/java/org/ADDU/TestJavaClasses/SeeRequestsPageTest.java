package org.ADDU.TestJavaClasses;

import org.ADDU.JavaClasses.LoginController;

import org.ADDU.JavaClasses.*;
import org.apache.commons.io.FileUtils;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.File;
import static org.junit.Assert.*;

public class SeeRequestsPageTest {

    private static LoginController CLIENT_TEST;

    private static final String USR_CLNT="sebi99";
    private static final String PSS_CLNT="esteban";
    private static final String CRD_CLNT="1234";

    private static final String PATH_CLNT="src/test/resources/clientFileTest.xml";
    private static final String PATH_MNG="src/test/resources/managerTest.xml";
    SeeRequestsPage SRP;

    @BeforeClass
    public static void setupClass() throws Exception {
        FileSystemService.APPLICATION_FOLDER = ".test-example";

        FileUtils.copyURLToFile(OnlineBankingApplication.class.getClassLoader().getResource("clientFileTest.xml"), new File(PATH_CLNT));
    }

    @Before
    public void setUp() throws Exception {
        FileUtils.copyURLToFile(OnlineBankingApplication.class.getClassLoader().getResource("clientFileTest.xml"), new File(PATH_CLNT));

        CLIENT_TEST=new LoginController(USR_CLNT,LoginController.encodePassword(USR_CLNT,PSS_CLNT),CRD_CLNT,PATH_CLNT);
    }

    @Test
    public void theClientTest(){
        SRP=new SeeRequestsPage(CLIENT_TEST.returnClient());
        assertNotNull(SRP.getClient());
        SRP.setClient(null);
        assertNull(SRP.getClient());
    }

    @Test
    public void returnPanelTest(){
        SRP=new SeeRequestsPage(CLIENT_TEST.returnClient());
        assertNotNull(SRP.returnPanel());
    }

}
