/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package sg.flooringmastery.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import sg.flooringmastery.dao.Dao;
import sg.flooringmastery.dao.TestDaoImpl;
import sg.flooringmastery.dto.Order;

/**
 *
 * @author crouton
 */
public class ServiceLayerFileImplTest {
    
    ServiceLayer testService;
    Dao testDao;
    
    public ServiceLayerFileImplTest() {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        testService = ctx.getBean("testService", ServiceLayerFileImpl.class);
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
    public void tearDown() {
    }

    @Test
    public void testCreateOrder() {
        try{
            testService.createOrder(new String[]{"01/01/2022", "Baldwin Varner", "Virginia", "2", "100"});
            fail("Expected Invalid Date Exception was not thrown");
        } catch (InvalidNameException | InvalidStateException e){
            fail("Expected Invalid Date Exception. Wrong exceptions thrown");
        } catch (InvalidDateException e) {
            
        }
        
        try{
            testService.createOrder(new String[]{"01-01-2022", "Baldwin Varner", "Virginia", "2", "100"});
            fail("Expected Invalid Date Exception was not thrown");
        } catch (InvalidNameException | InvalidStateException e){
            fail("Expected Invalid Date Exception. Wrong exceptions thrown");
        } catch (InvalidDateException e) {
            
        }
        
        try{
            testService.createOrder(new String[]{"07/30/2022", "", "Virginia", "2", "100"});
            fail("Expected Invalid Name Exxception");
        } catch (InvalidDateException | InvalidStateException e){
            fail("Expected Invalid Name Exception. Wrong Exceptions thrown");
        } catch (InvalidNameException e){
            
        }
        
        try{
            testService.createOrder(new String[]{"07/30/2022", "Baldwin Varner", "Guam", "2", "100"});
            fail("Expected Invalid State Exxception");
        } catch (InvalidDateException | InvalidNameException e){
            fail("Expected Invalid State Exception. Wrong Exceptions thrown");
        } catch (InvalidStateException e){
            return;
        }
    }
    
    @Test
    public void testGetOrdersFromDate(){
        try{
            testService.getOrdersFromDate("01-01-2022");
            fail("Expected Invalid Date Exception");
        } catch (InvalidDateException e){
            
        }
        
        try{
            testService.getOrdersFromDate("01/01/2222");
            fail("Expected Invalid Date Exception");
        } catch (InvalidDateException e){
            return;
        }
    }
    
    @Test
    public void testSearchOrder(){
        try{
            testService.searchOrder(new String[]{"01-01-2022", "1"});
            fail("Expected Invalid Date Exception");
        } catch (NoOrderException e){
            fail("Expected Invalid Date Exception. Wrong Error Thrown");
        } catch (InvalidDateException e){
            
        }
        
        try{
            testService.searchOrder(new String[]{"01/01/2022", "1"});
            fail("Expected No Order Exception");
        } catch (NoOrderException e){
            
        } catch (InvalidDateException e){
            fail("Expected No Order Exception. Wrong Error Thrown");
        }
        
        try{
            testService.searchOrder(new String[]{"01/01/2222", "1"});
            fail("Expected Invalid Date Exception");
        } catch (InvalidDateException e){
            fail("Expected No Order Exception. Wrong Error Thrown");
        } catch (NoOrderException e){
            return;
        } 
    }
    
    @Test
    public void testUpdateOrder() throws InvalidDateException, InvalidNameException, InvalidStateException{

        // Create order
        Order createdOrder;
        createdOrder = testService.createOrder(new String[]{"07/29/2022", "Baldwin Varner", "Virginia", "2", "100"});

        try{
            testService.updateOrder(createdOrder, new String[]{"01-01-2022", "Baldwin Varner", "Virginia", "2", "100"});
            fail("Ecpected Invalid Date Exception");
        } catch(InvalidStateException e){
            fail("Expected Invalid Date Exception. Wrong Error Thrown");
        } catch(InvalidDateException e){

        }

        try{
            testService.updateOrder(createdOrder, new String[]{"01/01/2022", "Baldwin Varner", "Virginia", "2", "100"});
            fail("Ecpected Invalid Date Exception");
        } catch(InvalidStateException e){
            fail("Expected Invalid Date Exception. Wrong Error Thrown");
        } catch(InvalidDateException e){

        }

        try{
            testService.updateOrder(createdOrder, new String[]{"07/30/2022", "Baldwin Varner", "Guam", "2", "100"});
            fail("Ecpected Invalid State Exception");
        } catch(InvalidDateException e){
            fail("Expected Invalid State Exception. Wrong Error Thrown");
        } catch(InvalidStateException e){
            return;
        }

    }
}
