package org.ADDU.TestJavaClasses;

import org.ADDU.JavaClasses.*;
import org.apache.commons.io.FileUtils;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.File;

import static org.junit.Assert.*;
import static org.junit.Assert.assertFalse;

public class SEERequestTest {

    private static final String USR_MAN="denisav";
    private static final String PSS_MAN="cevrei";

    private static LoginController MANAGER_TEST;

    private static final String PATH_CLNT="src/test/resources/clientFileTest.xml";
    private static final String PATH_MNG="src/test/resources/managerTest.xml";

    private static ManagerMainInterface mang;
    private static SEERequests SR;

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
    public void theManager(){
        SR=new SEERequests(null,PATH_MNG,PATH_CLNT);
        SR.setManager(MANAGER_TEST.returnManager());
        assertEquals(SR.getManager(),MANAGER_TEST.returnManager());
    }

    @Test
    public void returnPanelTest(){
        SR=new SEERequests(MANAGER_TEST.returnManager(),PATH_MNG,PATH_CLNT);
        assertNotNull(SR.returnPanel());
    }

    public void isNumericTest(){
        assertTrue(SR.isNumeric("123"));
        assertFalse(SR.isNumeric("test"));
    }

}
