
public class Merchant {

	private String id;
	private String name;
	private String category;
	private String[] address;
	private String lat;
	private String lo;
	
	public Merchant() {
		
		// post /merchants
	
	}
	
	public void getAllMerchants() {
		
		// get /merchants
	}
	
	public void getMerchantByID() {
		
		// get /merchants/{id}
	}
	
	public void updateMerchant() {
		
		// put /merchants/{id}
	}
	
	public User createUser() {
		
		return new User();
	}
	
	
}
