package org.ADDU.TestJavaClasses;

import static org.junit.Assert.*;
import org.ADDU.JavaClasses.*;
import org.apache.commons.io.FileUtils;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;


import java.io.File;
public class OnlineBankingApplicationTest {

    OnlineBankingApplication OBA;
    OnlineBankingApplication OBATest;

    private static final String PATH_CLNT="src/test/resources/clientFileTest.xml";
    private static final String PATH_MNG="src/test/resources/managerTest.xml";

    private static LoginController CLIENT_TEST;

    private static final String USR_CLNT="sebi99";
    private static final String PSS_CLNT="esteban";
    private static final String CRD_CLNT="1234";

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
    public void testPasswordEncoding() {
        assertNotEquals("sebi99", LoginController.encodePassword("sebi99", "esteban"));
    }

}
