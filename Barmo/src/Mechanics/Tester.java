/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Mechanics;

import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONObject;

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
        JSONObject obj = new JSONObject();
        obj.put("type", "Credit Card");
        obj.put("nickname", "string");
        obj.put("rewards",0);
        obj.put("balance",0);
        obj.put("account_number","abcdefghijklmnop");
        try {
            //globalHTTP.sendPost("customers/58a8e9fa1756fc834d9053ea/accounts", obj);
        } catch (Exception ex) {
            System.out.println("Failed");
        }
        
        System.out.println("Welcome to the Barmo Demo");
        System.out.println("What would you like this instance to be? Customer or Merchant?");
        Scanner scnr = new Scanner(System.in);
        System.out.print("This Instance:");
        String cmd = scnr.nextLine();
        if(cmd.equals("Merchant") || cmd.equals("merchant")){
            MerchantMain.startUpMerchant();
        }else if(cmd.equals("Customer") || cmd.equals("customer")){
            CustomerMain.startUpCustomer();
        }
        System.out.println("Thank you for using Barmo");
    }
    
}
