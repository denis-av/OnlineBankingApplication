package org.ADDU.TestJavaClasses;

import org.ADDU.Exceptions.CouldNotFindClient;
import org.ADDU.JavaClasses.FileSystemService;
import org.ADDU.JavaClasses.LoginController;
import org.ADDU.JavaClasses.ManagerMainInterface;
import org.ADDU.JavaClasses.OnlineBankingApplication;
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

public class ManagerMainInterfaceTest {
    private static final String USR_MAN="denisav";
    private static final String PSS_MAN="cevrei";

    private static LoginController MANAGER_TEST;
    private static LoginController MANAGER_TEST1;

    private static final String PATH_CLNT="src/test/resources/clientFileTest.xml";
    private static final String PATH_MNG="src/test/resources/managerTest.xml";

    private static ManagerMainInterface mang;

    @BeforeClass
    public static void setupClass() throws Exception {
        FileSystemService.APPLICATION_FOLDER = ".test-example";

        FileUtils.copyURLToFile(OnlineBankingApplication.class.getClassLoader().getResource("managerTest.xml"), new File(PATH_MNG));
    }

    @Before
    public void setUp() throws Exception {
        FileUtils.copyURLToFile(OnlineBankingApplication.class.getClassLoader().getResource("managerTest.xml"), new File(PATH_MNG));


        MANAGER_TEST=new LoginController(USR_MAN,LoginController.encodePassword(USR_MAN,PSS_MAN),PATH_MNG);
        MANAGER_TEST1=new LoginController("test",LoginController.encodePassword("test","testParola"),PATH_MNG);
    }

    @Test(expected = CouldNotFindClient.class)
    public void addClientTest(){
        mang = new ManagerMainInterface(MANAGER_TEST.returnManager(),PATH_MNG,PATH_CLNT);
        mang.addAClient("1234");
        mang.addAClient("test");
        assertEquals(1,mang.myClient.size());
    }

    @Test(expected = CouldNotFindClient.class)
    public void addTwoClientsTest(){
        mang = new ManagerMainInterface(MANAGER_TEST.returnManager(),PATH_MNG,PATH_CLNT);
        mang.addAClient("1234");
        mang.addAClient("5678");
        mang.addAClient("test");
        assertEquals(2,mang.myClient.size());
    }


    @Test
    public void addAllClientsTest(){
        mang = new ManagerMainInterface(MANAGER_TEST.returnManager(),PATH_MNG,PATH_CLNT);
        mang.addMyClients();
        assertEquals(mang.myClient.size(),MANAGER_TEST.returnManager().getCards().size());
    }

    @Test
    public  void returnPanelMainManagerTest(){
        mang = new ManagerMainInterface(MANAGER_TEST.returnManager(),PATH_MNG,PATH_CLNT);
        assertNotNull(mang.returnPanelMainManager());
    }
}
