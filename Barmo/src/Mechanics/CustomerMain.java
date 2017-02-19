/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Mechanics;

import java.util.Scanner;

/**
 *
 * @author Brad Rogers
 */
public class CustomerMain {
    
    public static final String custID = "58a8ea2e1756fc834d9053eb";
    
    public static void startUpCustomer(){
        
        System.out.println();
        System.out.println("Starting up for Customer. ID:" + custID);
        System.out.println();
        
        Customer thisCust = new Customer(custID);
        
        Scanner scnr = new Scanner(System.in);
        boolean running = true;
        String cmd;
        String[] splitCmd;
        
        while(running){
            System.out.print("CMD:");
            cmd = scnr.nextLine().toLowerCase();
            splitCmd = cmd.trim().split(" ");
            if(splitCmd[0].equals("help")){
                System.out.println("*********************HELP*******************\n" +
                        "View - Views purchases\n" +
                        "Pay [billID] [amount] - Pay purchases\n" +
                        "Tip [billID] [amount] - Add a tip\n" +
                        "Info - Views customer info\n" +
                        "Update - Updates customer info\n" +
                        "Quit - Quits app\n" + 
                        "********************************************");   
            }else if(splitCmd[0].equals("view")){
                String result = thisCust.getPurchases().toString();
            }else if(splitCmd[0].equals("pay")){
                
            }else if(splitCmd[0].equals("info")){
                
            }else if(splitCmd[0].equals("update")){
                
            }else if(splitCmd[0].equals("make")){
                
            }else if(splitCmd[0].equals("quit")){
                running = false;
            }else{
                System.out.println("Invalid Command. Type 'help' for list of valid commands");
            }
        }
    }
}
