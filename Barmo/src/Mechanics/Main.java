package Mechanics;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import static Mechanics.HTTP.sendPost;
import static Mechanics.HTTP.sendGet;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author Brad Rogers
 */
public class Main {

    public static String customer = "accounts";
    public static String createCustomer = "";
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args){
        try {
            /**     try {
             * System.out.println(sendRequest(customer));
             * } catch (IOException ex) {
             * Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
             * }
             **/
            sendPost("customers");
        } catch (Exception ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
}
