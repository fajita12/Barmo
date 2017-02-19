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
                        "Bill [custID] [amount] [desc] - Pay purchases\n" +
                        "UpdateBill [purchID] [amount] [desc] - updates a purchase\n" +
                        "DeleteBill [purchID] [amount] - deletes a purchase\n" +
                        "Info - Views merchant info\n" +
                        "Update - Updates merchant info\n" +
                        "Quit - Quits app\n" +
                        "********************************************");
                
                
            }else if(splitCmd[0].equals("view")){
                running = false;
            }else if(splitCmd[0].equals("bill")){
                running = false;
            }else if(splitCmd[0].equals("updatebill")){
                running = false;
            }else if(splitCmd[0].equals("deletebill")){
                running = false;
            }else if(splitCmd[0].equals("info")){
                running = false;
            }else if(splitCmd[0].equals("update")){
                running = false;
            }else if(splitCmd[0].equals("quit")){
                running = false;
            }else{
                System.out.println("Invalid Command. Type 'help' for list of valid commands");
            }
        }
        
    }
}
