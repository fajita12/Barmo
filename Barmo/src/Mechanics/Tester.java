/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Mechanics;

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
    public static void main(String[] args) throws Exception{

        System.out.println("Testing 1 - Send Http GET request");
        globalHTTP.sendGet("customers");

        System.out.println("\nTesting 2 - Send Http POST request");
        JSONObject body = new JSONObject();
        //body.put("first_name", "brad");
        //body.put("last_name", "rogers");
        JSONObject address = new JSONObject();
        address.put("street_number", "new street");
        address.put("street_name", "cool street");
        address.put("city", "new city");
        address.put("state", "MA");
        address.put("zip", "99999");
        body.put("address", address);
        //globalHTTP.sendPost("customers", body);
        
        System.out.println("Testing 3 - Sending Http PUT request");
        
        globalHTTP.sendDelete("customers/58a8e9d41756fc834d9053e9");
    
    }
    
}
