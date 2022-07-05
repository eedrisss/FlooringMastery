/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sg.flooringmastery.controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import sg.flooringmastery.dto.Order;
import sg.flooringmastery.service.InvalidDateException;
import sg.flooringmastery.service.InvalidNameException;
import sg.flooringmastery.service.InvalidStateException;
import sg.flooringmastery.service.NoOrderException;
import sg.flooringmastery.service.ServiceLayer;
import sg.flooringmastery.view.View;


public class Controller {
    

    private View view;
    private ServiceLayer service;
    
    public Controller(ServiceLayer service, View view){
        this.service = service;
        this.view = view;
    }
    
    
    
    public void run(){
        boolean running = true;
        int menuSelection = 0;
        while(running){

            menuSelection = getMenuSelection();
            switch(menuSelection){
                case 1:
                    displayOrders();
                    break;
                case 2:
                    addOrder();
                    break;
                case 3:
                    editOrder();
                    break;
                case 4:
                    removeOrder();
                    break;
                case 5:
                    exportAll();
                    break;
                case 6:
                    running = false;
                    break;
            }
        }
    }
    
    private int getMenuSelection(){
        return view.getMenuSelection();
    }
    
    private void displayOrders(){
        String orderDate = view.getDateInfo();
        try{
            Map<Integer, Order> orderMap = service.getOrdersFromDate(orderDate);
            view.displayOrders(orderMap);
        }catch(InvalidDateException e){
            view.printError(e.getMessage());
        }
    }
    
    private void addOrder(){
        String[] orderInfo = view.getAddOrderInfo(service.getAllProducts());
        Order order;
        try{
            order = service.createOrder(orderInfo);
            String input = view.getOrderConfirmation(order, "Do you want to place this order?: (Yes, Y, No, N)");
            if(input.equalsIgnoreCase("n") || input.equalsIgnoreCase("no"))
                service.removeOrder(order);
        } catch (InvalidDateException ex) {
            view.printError(ex.getMessage());
        } catch (InvalidNameException ex) {
            view.printError(ex.getMessage());
        } catch (InvalidStateException ex) {
            view.printError(ex.getMessage());
        }
    }
    
    private void editOrder(){
        String[] searchInfo = view.getSearchInfo();
        Order order;
        try {
            order = service.searchOrder(searchInfo);
            String[] editInfo = view.getEditInfo(order, service.getAllProducts());
            try {
                service.updateOrder(order, editInfo);
            } catch (InvalidStateException ex) {
                view.printError(ex.getMessage());
            }
        } catch (InvalidDateException ex) {
            view.printError(ex.getMessage());
        } catch (NoOrderException ex) {
            view.printError(ex.getMessage());
        }
    }
    
    private void removeOrder(){
        String[] searchInfo = view.getSearchInfo();
        try {
            Order order = service.searchOrder(searchInfo);
            service.removeOrder(order);
        } catch (InvalidDateException ex) {
            view.printError(ex.getMessage());
        } catch (NoOrderException ex) {
            view.printError(ex.getMessage());
        }
    }
    
    private void exportAll(){
        service.exportAllData();
        view.exportSuccess();
    }
}
