/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Mechanics;

import java.util.Scanner;
import org.json.JSONObject;

/**
 *
 * @author Brad Rogers
 */
public class MerchantMain {
    //static merchant id
    public static final String merchID = "";
    
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
                        "View - Views purchases\n" +
                        "Bill [custID] [amount] [desc] - bill someone\n" +
                        "UpdateBill [purchID] [amount] [desc] - updates a bill\n" +
                        "DeleteBill [purchID] [amount] - deletes a bill\n" +
                        "Info - Views merchant info\n" +
                        "Update - Updates merchant info\n" +
                        "Quit - Quits app\n" +
                        "********************************************");
                
                
            }else if(splitCmd[0].equals("view")){
                System.out.println(thisMerch.getTransactions());
            }else if(splitCmd[0].equals("bill")){
                System.out.println();
            }else if(splitCmd[0].equals("updatebill")){
                System.out.println();
            }else if(splitCmd[0].equals("deletebill")){
                System.out.println();
            }else if(splitCmd[0].equals("info")){
                System.out.println(thisMerch.getInfo());
            }else if(splitCmd[0].equals("update")){
                JSONObject obj = new JSONObject();
                for(int i = 1; i < splitCmd.length; i++){
                    obj.put(splitCmd[i].split(":")[0], splitCmd[i].split(":")[1]);
                }
                thisMerch.updateMerchant(obj);
            }else if(splitCmd[0].equals("quit")){
                running = false;
            }else{
                System.out.println("Invalid Command. Type 'help' for list of valid commands");
            }
        }
        
    }
}
