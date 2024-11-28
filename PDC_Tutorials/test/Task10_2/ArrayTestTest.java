/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Task10_2;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Masaomi
 */
public class ArrayTestTest {

    public ArrayTestTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        ArrayTest arTest = new ArrayTest();
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of main method, of class ArrayTest.
     */
    @Test
    public void testMain() {
        System.out.println("main");
        String[] args = null;
        ArrayTest.main(args);
        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }

    /**
     * Test of find method, of class ArrayTest. Case 1: Error case (index < 0)
     */
    @Test(expected = IllegalArgumentException.class)
    public void testFind1() {
        System.out.println("testFind1");
        int index = -1;
        int[] intArray = {1, 2, 3, 4, 5};
        ArrayTest instance = new ArrayTest();
//        double expResult = 0.0;
//        double result = 0.0;
        instance.find(index, intArray);
    }

    /**
     * Case 2: Error case (array == null)
     */
    @Test(expected = IllegalArgumentException.class)
    public void testFind2() {
        System.out.println("testFind2");
        int index = 0;
        int[] intArray = null;
        ArrayTest instance = new ArrayTest();
//        double expResult = 0.0;
//        double result = 0.0;
        instance.find(index, intArray);
    }

    /**
     *Case3: Normal case (find avarage, when array.length = 0)
     */
    @Test(expected = IllegalArgumentException.class)
    public void testFind3() {
        System.out.println("testFind3");
        int index = 0;
        int[] intArray = {2};
        ArrayTest instance = new ArrayTest();
        double expResult = 2.0;
        double result = instance.find(index, intArray);
    }
    
    /**
     *Case4: Normal case (find average, when array.length > 0)
     */
    @Test
    public void testFind4() {
        System.out.println("testFind4");
        int index = 0;
        int[] intArray = {1,5,3,4,2};
        ArrayTest instance = new ArrayTest();
        double expResult = 3.0;
        double result = instance.find(index, intArray);
        assertEquals(expResult, result, 0);
    }
    
    /**
     *Case4: Normal case (find minimum)
     */
    @Test
    public void testFind5() {
        System.out.println("testFind5");
        int index = 1;
        int[] intArray = {1,5,3,4,2};
        ArrayTest instance = new ArrayTest();
        double expResult = 1.0;
        double result = instance.find(index, intArray);
        assertEquals(expResult, result, 0);
    }
    
    /**
     *Case4: Normal case (find maximum)
     */
    @Test
    public void testFind6() {
        System.out.println("testFind6");
        int index = 2;
        int[] intArray = {1,5,3,4,2};
        ArrayTest instance = new ArrayTest();
        double expResult = 5.0;
        double result = instance.find(index, intArray);
        assertEquals(expResult, result, 0);
    }
    
    /**
     * other test case: index > 2. result 0 is expected.
     */
    @Test
    public void testFind7() {
        System.out.println("testFind7");
        int index = 3;
        int[] intArray = {1, 2, 3, 4, 5};
        ArrayTest instance = new ArrayTest();
        double expResult = 0.0;
        double result = instance.find(index, intArray);
        assertEquals(expResult, result, 0);
    }
}
