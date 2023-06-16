public class Product {
	
	private int productID;
	private String productName;
	private String productType;
	private String productBrand;
	private double productPrice;
	private int productQty;

	public Product() {
	}

	public Product(int productID, String productName, String productType, String productBrand, double productPrice,
			int productQty) {
		this.productID = productID;
		this.productName = productName;
		this.productType = productType;
		this.productBrand = productBrand;
		this.productPrice = productPrice;
		this.productQty = productQty;
	}
	
	public int getProductID() {
		return productID;
	}
	public void setProductID(int productID) {
		this.productID = productID;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getProductType() {
		return productType;
	}
	public void setProductType(String productType) {
		this.productType = productType;
	}
	public String getProductBrand() {
		return productBrand;
	}
	public void setProductBrand(String productBrand) {
		this.productBrand = productBrand;
	}
	public double getProductPrice() {
		return productPrice;
	}
	public void setProductPrice(double productPrice) {
		this.productPrice = productPrice;
	}
	public int getProductQty() {
		return productQty;
	}
	public void setProductQty(int productQty) {
		this.productQty = productQty;
	}
	
}
