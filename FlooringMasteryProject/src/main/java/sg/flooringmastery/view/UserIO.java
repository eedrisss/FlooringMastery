/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package sg.flooringmastery.view;

/**
 *
 * @author aleia
 */
public interface UserIO {
    void print(String msg);
    String readInput(String prompt);
    int readInt(String prompt);
    int readInt(String prompt, int min, int max);
    double readDouble(String prompt);
    double readDouble(String prompt, int min);
    String getUserConfirmation(String prompt);
}
