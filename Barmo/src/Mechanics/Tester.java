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
public class Tester {
    
    public static HTTP globalHTTP = new HTTP();

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args){
        System.out.println("Welcome to the Barmo Demo");
        System.out.println("What would you like this instance to be? Customer or Merchant?");
        Scanner scnr = new Scanner(System.in);
        System.out.print("This Instance:");
        String cmd = scnr.nextLine();
        if(cmd.equals("Merchant")){
            MerchantMain.startUpMerchant();
        }else if(cmd.equals("Customer")){
            CustomerMain.startUpCustomer();
        }
        System.out.println("Thank you for using Barmo");
    }
    
}
