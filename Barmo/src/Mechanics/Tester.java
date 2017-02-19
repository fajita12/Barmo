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
        /**
        JSONObject obj = new JSONObject();
        obj.put("name", "Capital One");
        obj.put("category", new String[]{"cat1"});
        JSONObject address = new JSONObject();
        address.put("street_numer", "1234");
        address.put("street_name", "street");
        address.put("city", "Madison");
        address.put("state", "WI");
        address.put("zip", "12345");
        obj.put("address", address);
        JSONObject geocode = new JSONObject();
        geocode.put("lat", 0);
        geocode.put("lng", 0);
        obj.put("geocode", geocode);
        
        try {
            globalHTTP.sendPost("merchants", obj);
            System.out.println(globalHTTP.sendGet("merchants"));
        } catch (Exception ex) {
            System.out.println("Failed");
        }
        **/
        
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
