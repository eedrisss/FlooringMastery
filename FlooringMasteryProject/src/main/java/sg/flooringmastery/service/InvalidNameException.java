/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sg.flooringmastery.service;

/**
 *
 * @author aleia
 */
public class InvalidNameException extends Exception{
    public InvalidNameException(String message){
        super(message);
    }
    public InvalidNameException(String message, Throwable cause){
        super(message, cause);
    }
}
