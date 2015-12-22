/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package applicationanalyzer.Misc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.collections.ObservableList;
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
public class SQLExecutorTest {
    
    public SQLExecutorTest() {
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
     * Test of executeProcedure method, of class SQLExecutor.
     */
    @Test
    public void testExecuteProcedure() {
        System.out.println("executeProcedure");
        String procedureName = "";
        boolean expResult = false;
        boolean result = SQLExecutor.executeProcedure(procedureName);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of executeQuery method, of class SQLExecutor.
     */
    @Test
    public void testExecuteQuery() {
        System.out.println("executeQuery");
        String querySQL = "";
        ResultSet expResult = null;
        ResultSet result = SQLExecutor.executeQuery(querySQL);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getTablePage method, of class SQLExecutor.
     */
    @Test
    public void testGetTablePage_3args() {
        System.out.println("getTablePage");
        String packageName = "";
        Integer pageNr = null;
        Integer pageResultsNr = null;
        CallableStatementResults expResult = null;
        CallableStatementResults result = SQLExecutor.getTablePage(packageName, pageNr, pageResultsNr);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getTablePage method, of class SQLExecutor.
     */
    @Test
    public void testGetTablePage_5args() {
        System.out.println("getTablePage");
        String packageName = "";
        Integer pageNr = null;
        Integer pageResultsNr = null;
        String findbyField = "";
        Integer findbyKey = null;
        CallableStatementResults expResult = null;
        CallableStatementResults result = SQLExecutor.getTablePage(packageName, pageNr, pageResultsNr, findbyField, findbyKey);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of updateRowNvalue method, of class SQLExecutor.
     */
    @Test
    public void testUpdateRowNvalue() {
        System.out.println("updateRowNvalue");
        String packageName = "";
        Integer rowCode = null;
        String fieldName = "";
        Number newValue = null;
        boolean expResult = false;
        boolean result = SQLExecutor.updateRowNvalue(packageName, rowCode, fieldName, newValue);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getTableRow method, of class SQLExecutor.
     */
    @Test
    public void testGetTableRow() {
        System.out.println("getTableRow");
        String packageName = "";
        String columnName = "";
        Integer key = null;
        CallableStatementResults expResult = null;
        CallableStatementResults result = SQLExecutor.getTableRow(packageName, columnName, key);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getPrettyName method, of class SQLExecutor.
     */
    @Test
    public void testGetPrettyName() {
        System.out.println("getPrettyName");
        String tableName = "";
        String columnName = "";
        String expResult = "";
        String result = SQLExecutor.getPrettyName(tableName, columnName);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getLov method, of class SQLExecutor.
     */
    @Test
    public void testGetLov() {
        System.out.println("getLov");
        String tableName = "";
        String fieldName = "";
        ObservableList<String> expResult = null;
        ObservableList<String> result = SQLExecutor.getLov(tableName, fieldName);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getColumnData method, of class SQLExecutor.
     */
    @Test
    public void testGetColumnData() {
        System.out.println("getColumnData");
        String tableName = "B_MINMAX_CHECK_PARS";
        
        CallableStatementResults result = SQLExecutor.getColumnData(tableName);
        ResultSet res = result.getResultSet();
        try {
            while (res.next()) {
                System.out.println(res.getString(1));
            }
        } catch (SQLException sqle) {
            Alerts.AlertSQL(sqle);
        }
        //assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
    }
    
}
