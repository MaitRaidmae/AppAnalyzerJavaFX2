/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package applicationanalyzer.DataClasses;

import applicationanalyzer.Misc.Alerts;
import applicationanalyzer.Misc.CallableStatementResults;
import applicationanalyzer.Misc.ConnectionManager;
import java.sql.Connection;
import java.sql.SQLException;
import javafx.scene.layout.GridPane;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Hundisilm
 */
public class MinmaxCheckParsTest {
    
    public MinmaxCheckParsTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        ConnectionManager login = new ConnectionManager();
        try {
            Connection db_con = login.getDBConnection("Hundisilm", "dummyPassword");
        } catch (SQLException sqle) {
            Alerts.AlertSQL(sqle);
        }    
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getMcpCode method, of class MinmaxCheckPars.
     */
    @Test
    public void testGetMcpCode() {
        System.out.println("getMcpCode");
        MinmaxCheckPars instance = null;
        Integer expResult = null;
        Integer result = instance.getMcpCode();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setMcpCode method, of class MinmaxCheckPars.
     */
    @Test
    public void testSetMcpCode() {
        System.out.println("setMcpCode");
        Integer code = null;
        MinmaxCheckPars instance = null;
        instance.setMcpCode(code);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getMcpChkCode method, of class MinmaxCheckPars.
     */
    @Test
    public void testGetMcpChkCode() {
        System.out.println("getMcpChkCode");
        MinmaxCheckPars instance = null;
        Integer expResult = null;
        Integer result = instance.getMcpChkCode();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setMcpChkCode method, of class MinmaxCheckPars.
     */
    @Test
    public void testSetMcpChkCode() {
        System.out.println("setMcpChkCode");
        Integer chk_code = null;
        MinmaxCheckPars instance = null;
        instance.setMcpChkCode(chk_code);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getMcpThreshold method, of class MinmaxCheckPars.
     */
    @Test
    public void testGetMcpThreshold() {
        System.out.println("getMcpThreshold");
        MinmaxCheckPars instance = null;
        Double expResult = null;
        Double result = instance.getMcpThreshold();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setMcpThreshold method, of class MinmaxCheckPars.
     */
    @Test
    public void testSetMcpThreshold() {
        System.out.println("setMcpThreshold");
        Double threshold = null;
        MinmaxCheckPars instance = null;
        instance.setMcpThreshold(threshold);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getMcpCheckField method, of class MinmaxCheckPars.
     */
    @Test
    public void testGetMcpCheckField() {
        System.out.println("getMcpCheckField");
        MinmaxCheckPars instance = null;
        String expResult = "";
        String result = instance.getMcpCheckField();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setMcpCheckField method, of class MinmaxCheckPars.
     */
    @Test
    public void testSetMcpCheckField() {
        System.out.println("setMcpCheckField");
        String check_field = "";
        MinmaxCheckPars instance = null;
        instance.setMcpCheckField(check_field);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getGrid method, of class MinmaxCheckPars.
     */
    @Test
    public void testGetGrid() {
        System.out.println("getGrid");
        Boolean editable = null;
        MinmaxCheckPars instance = null;
        GridPane expResult = null;
        GridPane result = instance.getGrid(editable);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getEmptyGrid method, of class MinmaxCheckPars.
     */
    @Test
    public void testGetEmptyGrid() {
        System.out.println("getEmptyGrid");
        GridPane expResult = null;
        GridPane result = MinmaxCheckPars.getEmptyGrid();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of commit method, of class MinmaxCheckPars.
     */
    @Test
    public void testCommit() {
        System.out.println("commit");
        MinmaxCheckPars instance = null;
        instance.commit();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setFromGrid method, of class MinmaxCheckPars.
     */
    @Test
    public void testSetFromGrid() {
        System.out.println("setFromGrid");
        GridPane grid = null;
        MinmaxCheckPars instance = null;
        instance.setFromGrid(grid);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of create method, of class MinmaxCheckPars.
     */
    @Test
    public void testCreate() {
        System.out.println("create");
        Integer code = null;
        MinmaxCheckPars.create(code);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getResultSet method, of class MinmaxCheckPars.
     */
    @Test
    public void testGetResultSet() {
        System.out.println("getResultSet");
        Integer MCP_CHK_CODE = null;
        CallableStatementResults expResult = null;
        CallableStatementResults result = MinmaxCheckPars.getResultSet(MCP_CHK_CODE);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
