public class Jerseys extends Product {

	private String jerseySize;
	private String jerseyColour;
	private String jerseyTeam;
	private String jerseySport;
	
	public Jerseys() {
		
	}

	public Jerseys(int productID, String productName, String productType, String productBrand, double productPrice,
			int productQty, String jerseySize, String jerseyColour, String jerseyTeam, String jerseySport) {
		super(productID, productName, productType, productBrand, productPrice, productQty);
		this.jerseySize = jerseySize;
		this.jerseyColour = jerseyColour;
		this.jerseyTeam = jerseyTeam;
		this.jerseySport = jerseySport;
	}

	public String getJerseySize() {
		return jerseySize;
	}

	public void setJerseySize(String jerseySize) {
		this.jerseySize = jerseySize;
	}

	public String getJerseyColour() {
		return jerseyColour;
	}

	public void setJerseyColour(String jerseyColour) {
		this.jerseyColour = jerseyColour;
	}

	public String getJerseyTeam() {
		return jerseyTeam;
	}

	public void setJerseyTeam(String jerseyTeam) {
		this.jerseyTeam = jerseyTeam;
	}

	public String getJerseySport() {
		return jerseySport;
	}

	public void setJerseySport(String jerseySport) {
		this.jerseySport = jerseySport;
	}

	

}
