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
            cmd = scnr.nextLine();
            splitCmd = cmd.trim().split(" ");
            
        }
        
    }
}
