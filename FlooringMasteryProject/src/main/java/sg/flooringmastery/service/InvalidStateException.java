/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sg.flooringmastery.service;

/**
 *
 * @author aleia
 */
public class InvalidStateException extends Exception{
    public InvalidStateException(String message){
        super(message);
    }
    public InvalidStateException(String message, Throwable cause){
        super(message, cause);
    }
}
