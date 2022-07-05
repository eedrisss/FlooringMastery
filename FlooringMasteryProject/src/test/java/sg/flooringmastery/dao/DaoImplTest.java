/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package sg.flooringmastery.dao;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.Map;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import sg.flooringmastery.dto.Order;

/**
 *
 * @author crouton
 */
public class DaoImplTest {
    
    Dao testDao;
    
    public DaoImplTest() {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        testDao = ctx.getBean("testDao", TestDaoImpl.class);
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
    }
    
    @AfterEach
    public void tearDown() throws IOException {
        File dir = new File("TestOrders/");
        File[] orderFiles = dir.listFiles();
        for(File file : orderFiles){
            PrintWriter out = new PrintWriter(new FileWriter(file));
            out.println("OrderNumber,CustomerName,State,TaxRate,ProductType,Area,"
                + "CostPerSquareFoot,LaborCostPerSquareFoot,MaterialCost,LaborCost,Tax,Total");
            out.close();
        }
    }

    @Test
    public void testAddGetOrder() throws IOException {
        // Create order using constructor
        Integer orderNumber = 1;
        String name = "Baldwin Varner";
        String state = "Virginia";
        BigDecimal taxRate = new BigDecimal("5.30");
        String productType = "BlackTile";
        BigDecimal area = new BigDecimal(100);
        BigDecimal costPerSquareFoot = new BigDecimal("3.50");
        BigDecimal laborCostPerSquareFoot = new BigDecimal("4.15");
        Order testOrder = new Order(orderNumber, name, state, taxRate, productType, area, costPerSquareFoot, laborCostPerSquareFoot);
        testOrder.setDate("01/01/2022");

        
        // Create order using test dao
        testDao.createOrder("01/01/2022", name, state, "2", area.toString());
        // Get order using test dao
        Order retrievedOrder = testDao.searchOrder("01/01/2022", 1);
        
        // Check equalities
        assertTrue(retrievedOrder.equals(testOrder));
    }
    
    @Test
    public void testGetOrdersFromDate() throws IOException{
        // Create 2 orders
        Order firstOrder = testDao.createOrder("01/01/2022", "Baldwin Varner", "Virginia", "2", "100");
        Order secondOrder = testDao.createOrder("01/01/2022", "Baldwin V", "Virginia", "1", "100");
        
        // Create order map
        Map<Integer, Order> orderMap = testDao.getOrdersFromDate("01/01/2022");
        
        
       // Assert equalities
        assertTrue(orderMap != null);
        assertTrue(orderMap.size() == 2);
        assertTrue(orderMap.get(1).equals(firstOrder));
        assertTrue(orderMap.get(2).equals(secondOrder));
    }
    
    @Test
    public void testRemoveOrder() throws IOException{
        // Create 2 orders
        Order firstOrder = testDao.createOrder("01/01/2022", "Baldwin Varner", "Virginia", "2", "100");
        Order secondOrder = testDao.createOrder("01/01/2022", "Baldwin V", "Virginia", "1", "100");
        
        // Remove 1 order
        testDao.removeOrder(firstOrder);
        // Get order map
        Map<Integer, Order> orderMap = testDao.getOrdersFromDate("01/01/2022");
        
        // assertions
        assertTrue(orderMap.get(1) == null);
        assertTrue(orderMap.size() == 1);
        
        // Remove 2nd order
        testDao.removeOrder(secondOrder);
        
        // assertions
        assertTrue(orderMap.get(2) == null);
        assertTrue(orderMap.size() == 0);
    }
    
    @Test
    public void testSearchOrder() throws IOException{
        // Create order
        Order firstOrder = testDao.createOrder("01/01/2022", "Baldwin Varner", "Virginia", "2", "100");
        
        // Search for orders
        Order nullOrder = testDao.searchOrder("01/01/2022", 3);
        Order expectedOrder = testDao.searchOrder("01/01/2022", 1);
        
        // assertions
        assertTrue(nullOrder == null);
        assertTrue(expectedOrder.equals(firstOrder));
    }
    
    @Test
    public void testUpdateOrder() throws IOException{
        // Create order
        Order firstOrder = testDao.createOrder("01/01/2022", "Baldwin Varner", "Virginia", "2", "100");
        
        // Get first order and Update order
        Order oldOrder = testDao.searchOrder("01/01/2022", 1);
        testDao.updateOrder(firstOrder, "01/01/2022", "Bwin Vner", "Virginia", "1", "100");
        Order newOrder = testDao.searchOrder("01/01/2022", 1);
        
        // assertions
        assertTrue(!oldOrder.equals(newOrder));
    }
    
}
