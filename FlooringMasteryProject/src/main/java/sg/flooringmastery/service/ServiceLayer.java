/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package sg.flooringmastery.service;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.Map;
import sg.flooringmastery.dto.Order;
import sg.flooringmastery.dto.Product;

/**
 *
 * @author aleia
 */
public interface ServiceLayer {
    Map<Integer, Order> getOrdersFromDate(String date) throws InvalidDateException;
    List<Product> getAllProducts();    
    Order createOrder(String[] orderInfo)
            throws InvalidDateException, InvalidNameException, InvalidStateException;   
    Order searchOrder(String[] orderInfo) throws InvalidDateException, NoOrderException;
    void updateOrder(Order order, String[] orderInfo) throws InvalidDateException, InvalidStateException;   
    void removeOrder(Order order); 
    void exportAllData();
}
