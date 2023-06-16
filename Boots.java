public class Boots extends Product {

	private double bootSize;
	private String bootColour;
	private String bootMaterial;

	public Boots() {
		
	}

	public Boots(int productID, String productName, String productType, String productBrand, double productPrice,
			int productQty, double bootSize, String bootColour, String bootMaterial) {
		super(productID, productName, productType, productBrand, productPrice, productQty);
		this.bootSize = bootSize;
		this.bootColour = bootColour;
		this.bootMaterial = bootMaterial;
	}

	public double getBootSize() {
		return bootSize;
	}

	public void setBootSize(double bootSize) {
		this.bootSize = bootSize;
	}

	public String getBootColour() {
		return bootColour;
	}

	public void setBootColour(String bootColour) {
		this.bootColour = bootColour;
	}

	public String getBootMaterial() {
		return bootMaterial;
	}

	public void setBootMaterial(String bootMaterial) {
		this.bootMaterial = bootMaterial;
	}

}
		
