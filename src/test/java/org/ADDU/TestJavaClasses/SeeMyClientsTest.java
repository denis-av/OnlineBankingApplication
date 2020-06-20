package org.ADDU.TestJavaClasses;

import org.ADDU.JavaClasses.*;
import org.apache.commons.io.FileUtils;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.File;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;


public class SeeMyClientsTest {

    private static final String USR_MAN="denisav";
    private static final String PSS_MAN="cevrei";

    private static LoginController MANAGER_TEST;
    private static LoginController MANAGER_TEST1;

    private static final String PATH_CLNT="src/test/resources/clientFileTest.xml";
    private static final String PATH_MNG="src/test/resources/managerTest.xml";

    private static ManagerMainInterface mang;
    private static SeeMyClients SMC;

    @BeforeClass
    public static void setupClass() throws Exception {
        FileSystemService.APPLICATION_FOLDER = ".test-example";

        FileUtils.copyURLToFile(OnlineBankingApplication.class.getClassLoader().getResource("managerTest.xml"), new File(PATH_MNG));
    }

    @Before
    public void setUp() throws Exception {
        FileUtils.copyURLToFile(OnlineBankingApplication.class.getClassLoader().getResource("managerTest.xml"), new File(PATH_MNG));


        MANAGER_TEST=new LoginController(USR_MAN,LoginController.encodePassword(USR_MAN,PSS_MAN),PATH_MNG);
    }

    @Test
    public void theManagerTest(){
        mang = new ManagerMainInterface(MANAGER_TEST.returnManager(),PATH_MNG,PATH_CLNT);
        mang.addMyClients();
        SMC=new SeeMyClients(null,mang.myClient);
        SMC.setManager(MANAGER_TEST.returnManager());
        assertEquals(SMC.getManager(),MANAGER_TEST.returnManager());
    }

    @Test
    public void returnPanelTest(){
        assertNotNull(SMC.returnPanel());
    }
}
