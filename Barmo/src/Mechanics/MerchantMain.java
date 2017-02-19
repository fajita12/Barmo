/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Mechanics;

import static Mechanics.CustomerMain.custID;
import static Mechanics.Tester.globalHTTP;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONObject;

/**
 *
 * @author Brad Rogers
 */
public class MerchantMain {
    //static merchant id
    public static final String merchID = "57cf75cea73e494d8675ec49";
    
    public static void startUpMerchant(){
        
        System.out.println();
        System.out.println("Starting up for Merchant. ID:" + merchID);
        System.out.println();
        
        Merchant thisMerch = new Merchant(merchID);
        
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
                        "Bill [custID] [amount] [desc] - bill someone\n" +
                        "UpdateBill [custID] [purchID] [amount] [desc] - updates a bill\n" +
                        "DeleteBill [purchID] [amount] - deletes a bill\n" +
                        "Info - Views merchant info\n" +
                        "Update - Updates merchant info (must be in JSON format)\n" +
                        "Admin - overview of everything" + 
                        "Quit - Quits app\n" +
                        "********************************************");
                
                
            }else if(splitCmd[0].equals("view")){
                System.out.println(thisMerch.getTransactions());
            }else if(splitCmd[0].equals("bill")){
                System.out.println("before");
                Customer c = new Customer(splitCmd[1]);
                System.out.println("after");
                thisMerch.sendTransaction(c, new Purchase(splitCmd[1], splitCmd[3], Double.parseDouble(splitCmd[2]), merchID));
            }else if(splitCmd[0].equals("updatebill")){
                try {
                    System.out.println("Status:" + thisMerch.updateTransaction(new Customer(splitCmd[1]), new Purchase(splitCmd[2]), merchID, Double.parseDouble(splitCmd[3])));
                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                }
            }else if(splitCmd[0].equals("deletebill")){
                System.out.println("Not implemented since no need");
            }else if(splitCmd[0].equals("info")){
                System.out.println(thisMerch.getInfo());
            }else if(splitCmd[0].equals("update")){
                JSONObject obj = new JSONObject();
                for(int i = 1; i < splitCmd.length; i++){
                    obj.put(splitCmd[i].split(":")[0], splitCmd[i].split(":")[1]);
                }
                thisMerch.updateMerchant(obj);
            }else if(splitCmd[0].equals("admin")){
                try{
                    System.out.println("Merchants:");
                    globalHTTP.sendGet("merchants");
                    System.out.println("Customers:");
                    globalHTTP.sendGet("customers");
                    System.out.println("Accounts:");
                    globalHTTP.sendGet("accounts");
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
