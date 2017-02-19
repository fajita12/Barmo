/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Mechanics;

import static Mechanics.MerchantMain.merchID;
import static Mechanics.Tester.globalHTTP;
import java.util.Scanner;
import org.json.JSONObject;

/**
 *
 * @author Brad Rogers
 */
public class CustomerMain {
    
    public static final String custID = "58a8e9fa1756fc834d9053ea";
    
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
                        "Merchant ID: " + merchID + "\n" +
                        "Customer ID: " + custID + "\n\n" +
                        "View - Views purchases\n" +
                        "Pay [billID] [amount] - Pay purchases\n" +
                        "Tip [billID] [amount] - Add a tip\n" +
                        "Info - Views customer info\n" +
                        "Update - Updates customer info (must be in JSON format)\n" +
                        "Admin - overview of everything\n" +
                        "Quit - Quits app\n" + 
                        "********************************************");   
            }else if(splitCmd[0].equals("view")){
            	try {
            		String result = thisCust.getPurchases().toString();
            		System.out.println(result);
            	} catch (NullPointerException e) {
            		System.out.println("Zero purchases on file");
            	}
            }else if(splitCmd[0].equals("pay")){
            	try {
            		System.out.println("Status: " + thisCust.payTab(new Purchase(splitCmd[1]), Double.parseDouble(splitCmd[2])));
            	} catch (ArrayIndexOutOfBoundsException e) {
            		System.out.println("No Payments available to pay");
            	}
            }else if(splitCmd[0].equals("info")){
                System.out.println(thisCust.getInfo());
            }else if(splitCmd[0].equals("update")){
                System.out.println("Status: " + thisCust.updateCustomer(custID, new JSONObject(splitCmd[1])));
            }else if(splitCmd[0].equals("tip")){
                System.out.println("Status: " + thisCust.addTip(new Purchase(splitCmd[1]), Double.parseDouble(splitCmd[2])));
            }else if(splitCmd[0].equals("admin")){
                try{
                    System.out.println("Merchants:");
                    System.out.println(globalHTTP.sendGet("merchants"));
                    System.out.println("Customers:");
                    System.out.println(globalHTTP.sendGet("customers"));
                    System.out.println("Accounts:");
                    System.out.println(globalHTTP.sendGet("accounts"));
                    System.out.println("Purchases:");
                    globalHTTP.sendGet("merchants/57cf75cea73e494d8675ec49/purchases");
                }catch(Exception ex){
                    System.out.println("Failed");
                }
            }else if(splitCmd[0].equals("quit")){
                running = false;
            }else{
                System.out.println("Invalid Command. Type 'help' for list of valid commands");
            }
        }
    }
}
