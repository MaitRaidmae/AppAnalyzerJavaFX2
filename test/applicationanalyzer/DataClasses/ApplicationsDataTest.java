/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package applicationanalyzer.DataClasses;

import java.util.LinkedHashMap;
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
public class ApplicationsDataTest {
    
    public ApplicationsDataTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
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

//    /**
//     * Test of getAplCode method, of class Applications.
//     */
//    @Test
//    public void testGetAplCode() {
//        System.out.println("getAplCode");
//        Applications instance = new Applications(448,"Name",45.3,45.3,56.2,0.4,59.0,"Secondary Education",0,0);;
//        Integer expResult = null;
//        Integer result = instance.getAplCode();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of setAplCode method, of class Applications.
//     */
//    @Test
//    public void testSetAplCode() {
//        System.out.println("setAplCode");
//        Integer code = 1;
//        Applications instance = new Applications(448,"Name",45.3,45.3,56.2,0.4,59.0,"Secondary Education",0,0);;
//        instance.setAplCode(code);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getAplName method, of class Applications.
//     */
//    @Test
//    public void testGetAplName() {
//        System.out.println("getAplName");
//        Applications instance = new Applications(448,"Name",45.3,45.3,56.2,0.4,59.0,"Secondary Education",0,0);;
//        String expResult = "";
//        String result = instance.getAplName();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of setAplName method, of class Applications.
//     */
//    @Test
//    public void testSetAplName() {
//        System.out.println("setAplName");
//        String name = "";
//        Applications instance = new Applications(448,"Name",45.3,45.3,56.2,0.4,59.0,"Secondary Education",0,0);;
//        instance.setAplName(name);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getAplIncome method, of class Applications.
//     */
//    @Test
//    public void testGetAplIncome() {
//        System.out.println("getAplIncome");
//        Applications instance = new Applications(448,"Name",45.3,45.3,56.2,0.4,59.0,"Secondary Education",0,0);;
//        Double expResult = null;
//        Double result = instance.getAplIncome();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of setAplIncome method, of class Applications.
//     */
//    @Test
//    public void testSetAplIncome() {
//        System.out.println("setAplIncome");
//        Double income = 23.4;
//        Applications instance = new Applications(448,"Name",45.3,45.3,56.2,0.4,59.0,"Secondary Education",0,0);;
//        instance.setAplIncome(income);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getAplObligations method, of class Applications.
//     */
//    @Test
//    public void testGetAplObligations() {
//        System.out.println("getAplObligations");
//        Applications instance = new Applications(448,"Name",45.3,45.3,56.2,0.4,59.0,"Secondary Education",0,0);;
//        Double expResult = null;
//        Double result = instance.getAplObligations();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of setAplObligations method, of class Applications.
//     */
//    @Test
//    public void testSetAplObligations() {
//        System.out.println("setAplObligations");
//        Double obligations = null;
//        Applications instance = new Applications(448,"Name",45.3,45.3,56.2,0.4,59.0,"Secondary Education",0,0);;
//        instance.setAplObligations(obligations);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getAplReserve method, of class Applications.
//     */
//    @Test
//    public void testGetAplReserve() {
//        System.out.println("getAplReserve");
//        Applications instance = new Applications(448,"Name",45.3,45.3,56.2,0.4,59.0,"Secondary Education",0,0);;
//        Double expResult = null;
//        Double result = instance.getAplReserve();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of setAplReserve method, of class Applications.
//     */
//    @Test
//    public void testSetAplReserve() {
//        System.out.println("setAplReserve");
//        Double reserve = null;
//        Applications instance = new Applications(448,"Name",45.3,45.3,56.2,0.4,59.0,"Secondary Education",0,0);;
//        instance.setAplReserve(reserve);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getAplDebtToIncome method, of class Applications.
//     */
//    @Test
//    public void testGetAplDebtToIncome() {
//        System.out.println("getAplDebtToIncome");
//        Applications instance = new Applications(448,"Name",45.3,45.3,56.2,0.4,59.0,"Secondary Education",0,0);;
//        Double expResult = null;
//        Double result = instance.getAplDebtToIncome();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of setAplDebtToIncome method, of class Applications.
//     */
//    @Test
//    public void testSetAplDebtToIncome() {
//        System.out.println("setAplDebtToIncome");
//        Double debt_to_income = null;
//        Applications instance = new Applications(448,"Name",45.3,45.3,56.2,0.4,59.0,"Secondary Education",0,0);;
//        instance.setAplDebtToIncome(debt_to_income);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getAplAge method, of class Applications.
//     */
//    @Test
//    public void testGetAplAge() {
//        System.out.println("getAplAge");
//        Applications instance = new Applications(448,"Name",45.3,45.3,56.2,0.4,59.0,"Secondary Education",0,0);;
//        Double expResult = null;
//        Double result = instance.getAplAge();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of setAplAge method, of class Applications.
//     */
//    @Test
//    public void testSetAplAge() {
//        System.out.println("setAplAge");
//        Double age = null;
//        Applications instance = new Applications(448,"Name",45.3,45.3,56.2,0.4,59.0,"Secondary Education",0,0);;
//        instance.setAplAge(age);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getAplEducation method, of class Applications.
//     */
//    @Test
//    public void testGetAplEducation() {
//        System.out.println("getAplEducation");
//        Applications instance = new Applications(448,"Name",45.3,45.3,56.2,0.4,59.0,"Secondary Education",0,0);;
//        String expResult = "";
//        String result = instance.getAplEducation();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of setAplEducation method, of class Applications.
//     */
//    @Test
//    public void testSetAplEducation() {
//        System.out.println("setAplEducation");
//        String education = "";
//        Applications instance = new Applications(448,"Name",45.3,45.3,56.2,0.4,59.0,"Secondary Education",0,0);;
//        instance.setAplEducation(education);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getAplRejected method, of class Applications.
//     */
//    @Test
//    public void testGetAplRejected() {
//        System.out.println("getAplRejected");
//        Applications instance = new Applications(448,"Name",45.3,45.3,56.2,0.4,59.0,"Secondary Education",0,0);;
//        Integer expResult = null;
//        Integer result = instance.getAplRejected();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of setAplRejected method, of class Applications.
//     */
//    @Test
//    public void testSetAplRejected() {
//        System.out.println("setAplRejected");
//        Integer rejected = null;
//        Applications instance = new Applications(448,"Name",45.3,45.3,56.2,0.4,59.0,"Secondary Education",0,0);;
//        instance.setAplRejected(rejected);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getAplInDefault method, of class Applications.
//     */
//    @Test
//    public void testGetAplInDefault() {
//        System.out.println("getAplInDefault");
//        Applications instance = new Applications(448,"Name",45.3,45.3,56.2,0.4,59.0,"Secondary Education",0,0);;
//        Integer expResult = null;
//        Integer result = instance.getAplInDefault();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of setAplInDefault method, of class Applications.
//     */
//    @Test
//    public void testSetAplInDefault() {
//        System.out.println("setAplInDefault");
//        Integer in_default = null;
//        Applications instance = new Applications(448,"Name",45.3,45.3,56.2,0.4,59.0,"Secondary Education",0,0);;
//        instance.setAplInDefault(in_default);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getFieldNames method, of class Applications.
//     */
//    @Test
//    public void testGetFieldNames() {
//        System.out.println("getFieldNames");
//        Map expResult = null;
//        Map result = Applications.getFieldNames();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }

    /**
     * Test of getFieldsWithValues method, of class Applications.
     */
    @Test
    public void testGetFieldsWithValues() {
        System.out.println("getFieldsWithValues");
        Applications instance = new Applications(448,"Name",45.3,45.3,56.2,0.4,59.0,"Secondary Education",0,0);
        String expResult = "Name";
        String result = instance.getAplName();
        assertEquals(expResult, result);
    }
    
}
