package Mechanics;
import static Mechanics.Tester.globalHTTP;

import org.json.JSONObject;

import Mechanics.Purchase.Status;

public class Customer {
	
	private String customerId;
	private String accountId;
	private String firstName;
	private String lastName;
	private String[] address;
	

	public Customer(String customerId) {
		
		this.customerId = customerId;
		
		try {
			
			JSONObject obj = new JSONObject(globalHTTP.sendGet("customers/" + customerId));
            this.firstName = obj.getString("first_name");
            this.lastName = obj.getString("last_name");
            address = new String[5];
            JSONObject address = new JSONObject(obj.getString("address"));
            this.address[0] = address.getString("street_number");
            this.address[1] = address.getString("street_name");
            this.address[2] = address.getString("city");
            this.address[3] = address.getString("state");
            this.address[4] = address.getString("zip");
            
        } catch(Exception e) {
        	
        	
			
		}
		// post /customers
	}
	
	public int getCustomerByAccount(String customerId) {
		
		try {
			
			globalHTTP.sendGet("accounts/" + customerId + "/customer");
			return 0;
		} catch (Exception e) {
			
			return -1;
		}
		// get /accounts/{id}/customer
	}
	
	public int getAllCustomers() {
		
		try {
			
			globalHTTP.sendGet("customers");
			return 0;
		} catch (Exception e) {
			
			return -1;
		}
		// get /customers
	}
	
	public int getCustomerByID(String customerId) {
		
		try {
			
			globalHTTP.sendGet("customers/" + customerId);
			return 0;
		} catch (Exception e) {
			
			return -1;
		}
		// get /customers/{id}
	}
	
	public int updateCustomer(String customerId, JSONObject body) {
		
		try {
			
			globalHTTP.sendPut("customers/" + customerId, body);
			return 0;
		} catch (Exception e) {
			
			return -1;
		}
		// put /customers/{id}
	}
        
    public JSONObject getPurchases() {
        return null;
    }
    
    public double payTab(Purchase purchase, double amount) {
    	
    	double withstanding = purchase.getAmount() - amount;
    	
    	JSONObject obj = new JSONObject();
    	obj.put("amount", withstanding);
    	
    	if (withstanding == 0) {
    		obj.put("status", Status.COMPLETED.toString().toLowerCase());
    	}
    
    	purchase.updatePurchase(purchase.getPurchaseId(), obj);
    	
    	return withstanding;
    }
    
    public double addTip(Purchase purchase, double tip) {
    	
    	double total = purchase.getAmount() + tip;
    	String merchantId = purchase.getMerchantId();
    	
    	
    	JSONObject obj = new JSONObject();
    	obj.put("amount", total);
    	
    	
    	
    	return total;
    }
    
    public String getAccountId() {
    	
    	return accountId;
    }
    
    public String getCustomerId() {
    	
    	return customerId;
    }
    
    public String getInfo() {
    	
    	return "Info for Customer:" + this.customerId + "\n" +
                "First name:      " + this.firstName + "\n" +
    			"Last name:		  " + this.lastName + "\n" +
                "Address:\n" + 
                "Street Number: " + this.address[0] + "\n" +
                "Street Name:   " + this.address[1] + "\n" +
                "City:          " + this.address[2] + "\n" + 
                "State:         " + this.address[3] + "\n" + 
                "Zip:           " + this.address[4];
        
    }
}
