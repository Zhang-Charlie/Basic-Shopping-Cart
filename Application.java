import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;
import java.text.DecimalFormat;


public class Application {

	static ArrayList<Product> stock = new ArrayList<Product>();
	static ArrayList<Product> cart = new ArrayList<Product>();
	static ArrayList<Boots> allBoots = new ArrayList<Boots>();
	static ArrayList<Jerseys> allJerseys = new ArrayList<Jerseys>();
	static DecimalFormat currency = new DecimalFormat("0.00"); 
	static double paymentDue;
	static int centralBootID =110;
	static int centralJerseyID = 1013;
	
	static Scanner input = new Scanner(System.in);
	
	static InputStreamReader textInput = new InputStreamReader(System.in);
	static BufferedReader reader = new BufferedReader(textInput);
	public static void main(String[] args) {
		prePopulate();
		mainMenu();
		}
		
	private static void mainMenu() {
		System.out.println("---------------Main Menu----------------------");
		System.out.println("Press 1 to shop for Boots");
		System.out.println("Press 2 to shop for Jerseys");
		System.out.println("Press 3 to shop for All");
		System.out.println("Press 4 to view cart");
		System.out.println("Press a for Admin");
		System.out.println("Press x to Shut Down");
		System.out.println("----------------------------------------------");

		String choice = input.next();
		choice = choice.toLowerCase();
		
		switch(choice) {
			case"1":{
				bootsMenu();
				break;
			}
			case "2":{
				jerseyMenu();
				break;
			}
			case "3":{
				shopAll();
				break;
			}
			case "4":{
				viewCart();
				break;
			}
			case "a":{
				adminMenu();
				break;
			}
			case "x":{
				System.out.println("Shutting Down ...");
				System.exit(0);
				break;
			}
			default:{
				System.out.println("Invalid Choice. Try again");
				break;
			}
		}
		mainMenu();
	}

	private static void viewCart() {

	 
	System.out.println("-----------------Cart-------------------------");
    System.out.println("Currently in Cart: ");
    System.out.println();
    
    for(Product cartItem: cart) {
    	
        System.out.println(cartItem.getProductID() + "    " + cartItem.getProductName() + "    " + "Quanity: " + cartItem.getProductQty() + "    Price: €" + cartItem.getProductQty()*cartItem.getProductPrice());
    }
	System.out.println();
	System.out.println("Cart Total: €" + paymentDue);
	System.out.println("----------------------------------------------");
	payNow();
	}
	private static void shopAll() {
			
			System.out.println("-----------------All Products--------------------");
				for(Product p: stock) {
					
					System.out.println();
					System.out.println("Product: " + p.getProductName());
					System.out.println("  Product Type: " + p.getProductType());
					System.out.println("  ID: " + p.getProductID());
					System.out.println("  Brand: " + p.getProductBrand());
					System.out.println("  Price: €" + p.getProductPrice());

					if(Boots.class.equals(p.getClass())) {
						Boots b =(Boots) p;
						System.out.println("  Size: " + b.getBootSize() + " eu");
						System.out.println("  Colour: " + b.getBootColour());
						System.out.println("  Material: " + b.getBootMaterial());
						
					}
					else if(Jerseys.class.equals(p.getClass())) {
						Jerseys j = (Jerseys) p; //Cast Person as Employee
						System.out.println("  Size: " + j.getJerseySize());
						System.out.println("  Colour: " + j.getJerseyColour());
						System.out.println("  Team: " + j.getJerseyTeam());
						System.out.println("  Sport: " + j.getJerseySport());
					}
					
					if(p.getProductQty() >= 0) {
						System.out.println(" Product Qty: " + p.getProductQty());
					
					}
						else {
							System.out.println(" Out of Stock");
							
						}
				}
				System.out.println("----------------------------------------------");
				addToCart();
			}		
	
	private static void addToCart() {
		System.out.println("----------------------------------------------");
		System.out.println("If you would like to add a product to cart");
		System.out.println("Press y for yes");
		System.out.println("Press b to return to main menu");
		System.out.println("----------------------------------------------");

		String choice = input.next();
		choice = choice.toLowerCase();
		
		switch(choice) {
			case"y":{
				try {
					shoppingCart();
				} catch (Exception e) {
					System.out.println("An error was logged.");
				}
				break;
			}
			case "b":{
				mainMenu();
				System.out.println("Returning to Main Menu");
				break;
			}	
			default:{
				System.out.println("Invalid Choice. Try again");
				break;
			}
		}
		addToCart();
	}
	private static void adminMenu() {
		
		System.out.println("---------------Admin Menu--------------------");
		System.out.println("Press 1 to Add New Products");
		System.out.println("Press 2 to Edit Products");
		System.out.println("Press 3 to Remove Products");
		System.out.println("Press 4 to View All Products");
		System.out.println("Press m to Return to Main Menu");
		System.out.println("----------------------------------------------");

		String choice = input.next();
		choice = choice.toLowerCase();
		
		switch(choice) {
			case"1":{
				try {
					addNewProduct();
				} catch (Exception e) {
					System.out.println("An error was logged.");
				}
				break;
			}
			case "2":{
				editProducts();
				break;
			}
			case "3":{
				removeProducts();
				break;
			}
			case "4":{
				printAllProducts();			
				break;
			}
			
			case "m":{
				mainMenu();
				break;
			}
			default:{
				System.out.println("Invalid Choice. Try again");
				break;
			}
		}
		adminMenu();
	}

	private static void removeProducts() {
		System.out.println("----------------------------------------------");
		System.out.println("Press 1 to remove Boots");
		System.out.println("Press 2 to remove Jersey");
		System.out.println("Press a to Return to Admin Menu");
		System.out.println("----------------------------------------------");

		
		String choice = input.next();
		choice = choice.toLowerCase();
		
		switch(choice) {
			case"1":{
				try {
					removeBoot();
				} catch (Exception e) {
					System.out.println("An error was logged.");
				}
				break;
			}
			case"2":{
				try {
					removeJersey();
				} catch (Exception e) {
					System.out.println("An error was logged.");
				}
				break;
			}
			case "a":{
				adminMenu();
				break;
			}
			default:{
				System.out.println("Invalid Choice. Try again");
				break;
			}
		}
		removeProducts();		
	}
	
/* Algorithm
	1. Begins by printing the header: "-----------------All Product------------------".
	2. Loop through each product in the stock array:
		i. Print an empty line for spacing.
		ii.Print the product name, type, ID, brand, and price using p.getProductName(), p.getProductType(), p.getProductID(), p.getProductBrand(), and p.getProductPrice() respectively.
		iii. If the product is an instance of Boots, cast it to a Boots object and print its size, colour, and material using b.getBootSize(), b.getBootColour(), and b.getBootMaterial() respectively.
		iv. If the product is an instance of Jerseys, cast it to a Jerseys object and print its size, colour, team, and sport using j.getJerseySize(), j.getJerseyColour(), j.getJerseyTeam(), and j.getJerseySport() respectively.
		v. If the product's quantity is greater than or equal to 0, print its quantity using p.getProductQty(). Otherwise, print "Out of Stock".
	3. Print the footer: "----------------------------------------------".
	4. Call the adminMenu() method to return to the admin menu.
 */
	
/*
 * Casting is a process of converting one data type into another. 
 * In code below, casting is used to convert an object of the parent class (Product) into an object of the child class (Boots or Jerseys)
 * so that the methods and properties specific to the child class can be accessed.
 */
	private static void printAllProducts() {
		
		System.out.println("-----------------All Products------------------");
			for(Product p: stock) {
				
				System.out.println();
				System.out.println();
				System.out.println("Product: " + p.getProductName());
				System.out.println("  Product Type: " + p.getProductType());
				System.out.println("  ID: " + p.getProductID());
				System.out.println("  Brand: " + p.getProductBrand());
				System.out.println("  Price: €" + p.getProductPrice());

				if(Boots.class.equals(p.getClass())) {
					Boots b =(Boots) p;
					System.out.println("  Size: " + b.getBootSize() + " eu");
					System.out.println("  Colour: " + b.getBootColour());
					System.out.println("  Material: " + b.getBootMaterial());
					
				}
				else if(Jerseys.class.equals(p.getClass())) {
					Jerseys j = (Jerseys) p; //Cast Person as Employee
					System.out.println("  Size: " + j.getJerseySize());
					System.out.println("  Colour: " + j.getJerseyColour());
					System.out.println("  Team: " + j.getJerseyTeam());
					System.out.println("  Sport: " + j.getJerseySport());
				}
				
				if(p.getProductQty() >= 0) {
					System.out.println(" Product Qty: " + p.getProductQty());
				
				}
					else {
						System.out.println(" Out of Stock");
						
					}
			}
			System.out.println("---------------------------------------------");
			adminMenu();
	}
		
	private static void removeJersey() {
		for(Jerseys j: allJerseys) {
			System.out.println("ID: " + j.getProductID() + "\t " + j.getProductName());
		}
		System.out.println("Choose Boots by id number");
		int chosenjersey = input.nextInt();
		boolean isFound = false;

		for(Jerseys currentJerseys: allJerseys) {
			if(currentJerseys.getProductID() == chosenjersey) {
				isFound=true;
				stock.remove(currentJerseys);
				System.out.println(currentJerseys.getProductName() + " has been removed.");
				break;
			}
			
		}
		if(isFound==false) {
			System.out.println("No book with id number " + chosenjersey + " was found.");
		}		
	}

	private static void removeBoot() {

		for(Boots b: allBoots) {
			System.out.println("ID: " + b.getProductID() + "\t " + b.getProductName());
		}
		System.out.println("Choose Boots by id number");
		int chosenBoots = input.nextInt();
		boolean isFound = false;

		for(Boots currentBoot: allBoots) {
			if(currentBoot.getProductID() == chosenBoots) {
				isFound=true;
				stock.remove(currentBoot);
				System.out.println(currentBoot.getProductName() + " has been removed.");
				break;
			}
			
		}
		if(isFound==false) {
			System.out.println("No book with id number " + chosenBoots + " was found.");
		}
	}
	
	


	private static void addNewProduct() {
		System.out.println("----------------------------------------------");
		System.out.println("Press 1 to Add new Boots");
		System.out.println("Press 2 to Add new Jersey");
		System.out.println("Press a to Return to Admin Menu");
		System.out.println("----------------------------------------------");

		
		String choice = input.next();
		choice = choice.toLowerCase();
		
		switch(choice) {
			case"1":{
				try {
					addNewBoot();
				} catch (Exception e) {
					System.out.println("An error was logged.");
				}
				break;
			}
			case"2":{
				try {
					addNewJersey();
				} catch (Exception e) {
					System.out.println("An error was logged.");
				}
				break;
			}
			case "a":{
				adminMenu();
				break;
			}
			default:{
				System.out.println("Invalid Choice. Try again");
				break;
			}
		}
		addNewProduct();
	}

	private static void editProducts() {
		System.out.println("----------------------------------------------");
		System.out.println("Press 1 to edit Boots");
		System.out.println("Press 2 to edit Jersey");
		System.out.println("Press a to Return to Admin Menu");
		System.out.println("----------------------------------------------");

		
		String choice = input.next();
		choice = choice.toLowerCase();
		
		switch(choice) {
			case"1":{
				try {
					editBoots();
				} catch (Exception e) {
					System.out.println("An error was logged.");
				}
				break;
			}
			case"2":{
				try {
					editJerseys();
					} catch (Exception e) {
					System.out.println("An error was logged.");
				}
				break;
			}
			case "a":{
				adminMenu();
				break;
			}
			default:{
				System.out.println("Invalid Choice. Try again");
				break;
			}
		}
		editProducts();
	}
	
	private static void editBoots() {
		for(Boots b: allBoots) {
			System.out.println("ID: " + b.getProductID() + "\t " + b.getProductName());
		}
		System.out.println("Choose Boots by id number");
		int chosenBoots = input.nextInt();
		boolean isFound = false;
		
		for(Boots currentBoot: allBoots) {
			if(currentBoot.getProductID() == chosenBoots) {
				isFound=true;
				try {
					editBootsMenu(currentBoot);
				} catch (Exception e) {
					System.out.println("Error editing boots");
				}		 		
			}
		}
		
		if(isFound==false) {
			System.out.println("No book with id number " + chosenBoots + " was found.");
		}
		
	}
	
	private static void editJerseys() {
		for(Jerseys j: allJerseys) {
			System.out.println("ID: " + j.getProductID() + "\t " + j.getProductName());
		}
		System.out.println("Choose Boots by id number");
		int chosenJersey = input.nextInt();
		boolean isFound = false;
		
		for(Jerseys currentJersey: allJerseys) {
			if(currentJersey.getProductID() == chosenJersey) {
				isFound=true;
				try {
					editJerseysMenu(currentJersey);
				} catch (Exception e) {
					System.out.println("Error editing boots");
				}				
			}
		}
		
		if(isFound==false) {
			System.out.println("No book with id number " + chosenJersey + " was found.");
		}
		
	}

		
	private static void editJerseysMenu(Jerseys currentJersey) throws Exception {
		System.out.println("-------------Edit Jersey Menu-----------------");
		System.out.println("Press 1 to change jersey name");
		System.out.println("Press 2 to change price");
		System.out.println("Press 3 to change jersey quanity");
		System.out.println("Press 4 to change product type");
		System.out.println("Press 5 to change jersey brand");
		System.out.println("Press 6 to change jersey size");
		System.out.println("Press 7 to change jersey colour");
		System.out.println("Press 8 to change jersey team");
		System.out.println("Press 8 to change jersey sport");
		System.out.println("Press a to return to Admin Menu");
		System.out.println("----------------------------------------------");

		String choice = input.next();
		choice = choice.toLowerCase();
		
		switch(choice) {
			case "1":{
				System.out.println("Enter new jersey name");
				currentJersey.setProductName(reader.readLine());
				break;
			}
			case "2":{
				System.out.println("Enter new price");
				currentJersey.setProductPrice(input.nextDouble());
			}
			case "3":{
				System.out.println("Enter new quanity");
				currentJersey.setProductQty(input.nextInt());
			}
			case "4":{
				System.out.println("Enter new product type");
				currentJersey.setProductType(reader.readLine());
				break;
			}
			case "5":{
				System.out.println("Enter new jersey brand");
				currentJersey.setProductBrand(reader.readLine());
				break;
			}
			case "6":{
				System.out.println("Enter new jersey size");
				currentJersey.setJerseySize(reader.readLine());
				break;
			}
			case "7":{
				System.out.println("Enter new colour");
				currentJersey.setJerseyColour(reader.readLine());
				break;
			}
			case "8":{
				System.out.println("Enter new team");
				currentJersey.setJerseyTeam(reader.readLine());
				break;
			}
			case "9":{
				System.out.println("Enter new sport");
				currentJersey.setJerseySport(reader.readLine());
				break;
			}
			
			case "a":{
				adminMenu();
				break;
			}
			default:{
				System.out.println("Invalid Choice. Try again");
				break;
			}
		}
		editJerseysMenu(currentJersey);
	}

	private static void editBootsMenu(Boots currentBoots) throws Exception {
		System.out.println("--------------Edit Boots Menu-----------------");
		System.out.println("Press 1 to change boots name");
		System.out.println("Press 2 to change price");
		System.out.println("Press 3 to change boots quanity");
		System.out.println("Press 4 to change product type");
		System.out.println("Press 5 to change boots brand");
		System.out.println("Press 6 to change boots size");
		System.out.println("Press 7 to change boots colour");
		System.out.println("Press 8 to change boots material");
		System.out.println("Press a to return to Admin Menu");
		System.out.println("----------------------------------------------");
		
		String choice = input.next();
		choice = choice.toLowerCase();
		
		switch(choice) {
			case "1":{
				System.out.println("Enter new boots name");
				currentBoots.setProductName(reader.readLine());
				break;
			}
			case "2":{
				System.out.println("Enter new price");
				currentBoots.setProductPrice(input.nextDouble());
			}
			case "3":{
				System.out.println("Enter new quanity");
				currentBoots.setProductQty(input.nextInt());
			}
			case "4":{
				System.out.println("Enter new product type");
				currentBoots.setProductType(reader.readLine());
				break;
			}
			case "5":{
				System.out.println("Enter new boot brand");
				currentBoots.setProductBrand(reader.readLine());
				break;
			}
			case "6":{
				System.out.println("Enter new boots size");
				currentBoots.setBootSize(input.nextDouble());
				break;
			}
			case "7":{
				System.out.println("Enter new colour");
				currentBoots.setBootColour(reader.readLine());
				break;
			}
			case "8":{
				System.out.println("Enter new changed material");
				currentBoots.setBootMaterial(reader.readLine());
				break;
			}
			
			case "a":{
				adminMenu();
				break;
			}
			default:{
				System.out.println("Invalid Choice. Try again");
				break;
			}
		
		}
		editBootsMenu(currentBoots);
	}
/* Algorithm
1. Create a new Boots object b.
2. Set the product ID of b to centralBootID.
3. Increment the value of centralBootID by 1.
4. Ask the user to enter the product name and set it to b's product name.
5. Ask the user to enter the product type and set it to b's product type.
6. Ask the user to enter the product brand and set it to b's product brand.
7. Ask the user to enter the product price and set it to b's product price.
8. Ask the user to enter the boot size and set it to b's boot size.
9. Ask the user to enter the product colour and set it to b's boot colour.
10. Ask the user to enter the product material and set it to b's boot material.
11. Ask the user to enter the product quantity and set it to b's boot quantity.
12. Add b to the stock list.
13. End the function.
	 */
	private static void addNewBoot() throws Exception {
		
		Boots b = new Boots();
		
		b.setProductID(centralBootID);
		centralBootID++;  //increment value by 1, so unique value is given
	
		System.out.println("Enter Product Name");
		b.setProductName(reader.readLine());	
		System.out.println("Enter Product Type");
		b.setProductType(reader.readLine());	
		System.out.println("Enter Product Brand");
		b.setProductBrand(reader.readLine());	
		System.out.println("Enter Product Price");
		b.setProductPrice(input.nextDouble());	
		System.out.println("Enter Boots Size");
		b.setBootSize(input.nextDouble());	
		System.out.println("Enter Product Colour");
		b.setBootColour(reader.readLine());	
		System.out.println("Enter Product Material");
		b.setBootMaterial(reader.readLine());	
		System.out.println("Enter Product Quanity");
		b.setBootSize(input.nextInt());	
		stock.add(b);
	
	}
	
private static void addNewJersey() throws Exception {
		
		Jerseys j = new Jerseys();
		
		j.setProductID(centralJerseyID);
		centralJerseyID++;  //increment value by 1, so unique value is given
	
		System.out.println("Enter Product Name");
		j.setProductName(reader.readLine());	
		System.out.println("Enter Product Type");
		j.setProductType(reader.readLine());	
		System.out.println("Enter Product Brand");
		j.setProductBrand(reader.readLine());	
		System.out.println("Enter Product Price");
		j.setProductPrice(input.nextDouble());	
		System.out.println("Enter Boots Size");
		j.setJerseySize(reader.readLine());	
		System.out.println("Enter Product Colour");
		j.setJerseyColour(reader.readLine());	
		System.out.println("Enter Product Material");
		j.setJerseyColour(reader.readLine());	
		System.out.println("Enter Product Material");
		j.setJerseySport(reader.readLine());
		System.out.println("Enter Product Quanity");
		j.setProductQty(input.nextInt());	
		stock.add(j);
		
	}
	private static void bootsMenu() {
		
		System.out.println("-----------------Boot Menu--------------------");
		System.out.println("Press 1 to Shop by Nike");
		System.out.println("Press 2 to Shop by Adidas");
		System.out.println("Press 3 to Shop all Boots");
		System.out.println("Press M to Return to Main Menu");
		System.out.println("----------------------------------------------");

		String choice = input.next();
		choice = choice.toLowerCase();
		
		switch(choice) {
			case "1":{
				try {
					bootsByNike();
				} catch (Exception e) {
					System.out.println("An error was logged.");
				}
				break;
			}
			case "2":{
				bootsByAdidas();				
				break;
			}
			case "3":{
				showAllBoots();
				break;
			}
			case "m":{
					mainMenu();
					break;
			}
			default:{
				System.out.println("Invalid Choice. Try again");
				break;
			}
		}
		
		bootsMenu();
	}
	
	private static void bootsByNike() {
		System.out.println("-------------Boots by Nike--------------------");
 		for(Product p: stock) {
 			if(Boots.class.equals(p.getClass())) {
				if(p.getProductBrand().equals("Nike")) { 
					Boots b =(Boots) p;
					
					System.out.println("Product: " + p.getProductName());
					System.out.println("  Product Type: " + p.getProductType());
					System.out.println("  ID: " + p.getProductID());
					System.out.println("  Brand: " + p.getProductBrand());
					System.out.println("  Price: €" + p.getProductPrice());
					System.out.println("  Size: " + b.getBootSize() + " eu");
					System.out.println("  Colour: " + b.getBootColour());
					System.out.println("  Material: " + b.getBootMaterial());
					if(p.getProductQty() >= 0) {
						System.out.println(" Product Qty: " + p.getProductQty());
						System.out.println();
						System.out.println();
					}
					else {
						System.out.println(" Out of Stock");
						System.out.println();
						System.out.println();
					}
				}
			}
		}
		System.out.println("----------------------------------------------");
		addToCart();
	}
	
	private static void bootsByAdidas() {
		System.out.println("-------------Boots by Adidas------------------");
 		for(Product p: stock) {
 			if(Boots.class.equals(p.getClass())) {
				if(p.getProductBrand().equals("Adidas")) { 
					Boots b =(Boots ) p; // Casting
					
					System.out.println("Product: " + p.getProductName());
					System.out.println("  Product Type: " + p.getProductType());
					System.out.println("  ID: " + p.getProductID());
					System.out.println("  Brand: " + p.getProductBrand());
					System.out.println("  Price: €" + p.getProductPrice());
					System.out.println("  Size: " + b.getBootSize() + " eu");
					System.out.println("  Colour: " + b.getBootColour());
					System.out.println("  Material: " + b.getBootMaterial());
					if(p.getProductQty() >= 0) {
						System.out.println(" Product Qty: " + p.getProductQty());
						System.out.println();
						System.out.println();
					}
					else {
						System.out.println(" Out of Stock");
						System.out.println();
						System.out.println();
					}
				}
			}
		}
		System.out.println("----------------------------------------------");
		addToCart();
	}

	private static void showAllBoots() {
		System.out.println("-----------------All Boots--------------------");
 		for(Product p: stock) {

			if(Boots.class.equals(p.getClass())) {
				Boots b =(Boots) p;
				
				System.out.println("Product: " + p.getProductName());
				System.out.println("  Product Type: " + p.getProductType());
				System.out.println("  ID: " + p.getProductID());
				System.out.println("  Brand: " + p.getProductBrand());
				System.out.println("  Price: €" + p.getProductPrice());
				System.out.println("  Size: " + b.getBootSize() + " eu");
				System.out.println("  Colour: " + b.getBootColour());
				System.out.println("  Material: " + b.getBootMaterial());
				if(p.getProductQty() >= 0) {
					System.out.println(" Product Qty: " + p.getProductQty());
					System.out.println();
					System.out.println();
				}
					else {
						System.out.println(" Out of Stock");
						System.out.println();
						System.out.println();
					}
			}
		}
		System.out.println("----------------------------------------------");
		addToCart();
	}
	
	
	private static void jerseyMenu() {
		
		System.out.println("----------------Jersey Menu-------------------");
		System.out.println("Press 1 to Shop Jerseys by Nike");
		System.out.println("Press 2 to Shop Jerseys by Adidas");
		System.out.println("Press 3 to Shop all Jerseys");
		System.out.println("Press M to Return to Main Menu");
		System.out.println("----------------------------------------------");

		String choice = input.next();
		choice = choice.toLowerCase();
		switch(choice) {
		case "1":{
			try {
				jerseysByNike();
			} catch (Exception e) {
				System.out.println("An error was logged.");
			}
			break;
		}
		case "2":{
			jerseysByAdidas();
			break;
		}
		case "3":{
			showAllJerseys();
			break;
		}
		case "m":{
				mainMenu();
				break;
			}
		default:{
			System.out.println("Invalid Choice. Try again");
			break;
		}
	}
	
		bootsMenu();
		
	}
	
	private static void jerseysByNike() {
		System.out.println("-----------Jerseys by Nike--------------------");
 		for(Product p: stock) {
 			if(Jerseys.class.equals(p.getClass())) {
				if(p.getProductBrand().equals("Nike")) { 
					Jerseys j =(Jerseys) p;

					System.out.println("Product: " + p.getProductName());
					System.out.println("  Product Type: " + p.getProductType());
					System.out.println("  ID: " + p.getProductID());
					System.out.println("  Brand: " + p.getProductBrand());
					System.out.println("  Price: €" + p.getProductPrice());
					System.out.println("  Size: " + j.getJerseySize());
					System.out.println("  Colour: " + j.getJerseyColour());
					System.out.println("  Team: " + j.getJerseyTeam());
					System.out.println("  Sport: " + j.getJerseySport());
					if(p.getProductQty() >= 0) {
						System.out.println(" Product Qty: " + p.getProductQty());
						System.out.println();
						System.out.println();
					}
						else {
							System.out.println(" Out of Stock");
							System.out.println();
							System.out.println();
						}
				}
			}
		}
		System.out.println("----------------------------------------------");
		addToCart();
		}

	private static void jerseysByAdidas() {
		System.out.println("-----------Jerseys by Adidas------------------");
 		for(Product p: stock) {
 			if(Jerseys.class.equals(p.getClass())) {
				if(p.getProductBrand().equals("Adidas")) { 
					Jerseys j =(Jerseys) p;

					System.out.println("Product: " + p.getProductName());
					System.out.println("  Product Type: " + p.getProductType());
					System.out.println("  ID: " + p.getProductID());
					System.out.println("  Brand: " + p.getProductBrand());
					System.out.println("  Price: €" + p.getProductPrice());
					System.out.println("  Size: " + j.getJerseySize());
					System.out.println("  Colour: " + j.getJerseyColour());
					System.out.println("  Team: " + j.getJerseyTeam());
					System.out.println("  Sport: " + j.getJerseySport());
					if(p.getProductQty() >= 0) {
						System.out.println(" Product Qty: " + p.getProductQty());
						System.out.println();
						System.out.println();
					}
						else {
							System.out.println(" Out of Stock");
							System.out.println();
							System.out.println();
						}
				}
			}
		}
		System.out.println("----------------------------------------------");
		addToCart();	
	}
	private static void showAllJerseys() {
		System.out.println("-----------Jerseys by All------------------");
 		for(Product p: stock) {
 			if(Jerseys.class.equals(p.getClass())) {
					Jerseys j =(Jerseys) p;

					System.out.println("Product: " + p.getProductName());
					System.out.println("  Product Type: " + p.getProductType());
					System.out.println("  ID: " + p.getProductID());
					System.out.println("  Brand: " + p.getProductBrand());
					System.out.println("  Price: €" + p.getProductPrice());
					System.out.println("  Size: " + j.getJerseySize());
					System.out.println("  Colour: " + j.getJerseyColour());
					System.out.println("  Team: " + j.getJerseyTeam());
					System.out.println("  Sport: " + j.getJerseySport());
					if(p.getProductQty() >= 0) {
						System.out.println(" Product Qty: " + p.getProductQty());
						System.out.println();
						System.out.println();
					}
					else {
						System.out.println(" Out of Stock");
						System.out.println();
						System.out.println();
					}
				}
		}
		System.out.println("----------------------------------------------");
		addToCart();
		}
	
	private static void shoppingCart() {
		
		System.out.println("Enter product id to add to cart:");
	    int chosenProduct = input.nextInt();
		boolean isFound = false;

	    for(Product stockItem: stock) {
	        if(chosenProduct == stockItem.getProductID()) {
				isFound=true;
	            Product cartItem = new Product(); //Create a new product
	            cartItem.setProductID(stockItem.getProductID());
	            cartItem.setProductName(stockItem.getProductName());
	            cartItem.setProductPrice(stockItem.getProductPrice());
	            
	            System.out.println("How many units do you wish to add to your cart?");
	            int cartQty = input.nextInt();

	            while(cartQty > stockItem.getProductQty()) {
	                System.out.println("Not enough items in stock.");
	                System.out.println("Current stock: " + stockItem.getProductQty());
	                System.out.println("Please try again.");
	                cartQty = input.nextInt();
	            }

	            
	            stockItem.setProductQty(stockItem.getProductQty() - cartQty);
	            cartItem.setProductQty(cartQty);
	            cart.add(cartItem);
	            System.out.println(cartQty + " " + stockItem.getProductName() + " added to cart.");
	            paymentDue += stockItem.getProductPrice() * cartItem.getProductQty() ;
	            break;
	        }
		    
	    }
	    if(isFound==false) {
    		System.out.println("----------------------------------------------");
    		System.out.println("No Product with id " + chosenProduct + " was found.");
    		System.out.println("----------------------------------------------");
    		System.out.println("Returning to Main Menu ...  ");
    		mainMenu();
    	}
	    
		System.out.println("----------------------------------------------");
	    System.out.println("Currently in Cart: ");
	    System.out.println();
	    
	    for(Product cartItem: cart) {
	        System.out.println(cartItem.getProductID() + "\t" + cartItem.getProductName() + "\t" + "Quanity: " + cartItem.getProductQty() + "  Price: €" + cartItem.getProductPrice());
	    }
		System.out.println();
		System.out.println("Cart Total: " + paymentDue);
		System.out.println("----------------------------------------------");
		payNow();
	    
	}

	private static void payNow() {
		
		System.out.println("Press 1 to Pay Now");
		System.out.println("Press 2 to Contiue Shopping");
		System.out.println("Press 3 to remove an item from Cart");
		System.out.println("Press 4 to Clear Cart");
		System.out.println("----------------------------------------------");

		String choice = input.next();
		choice = choice.toLowerCase();
		
		switch(choice) {
			case "1":{
				try {
					finalTransaction();
				} catch (Exception e) {
					System.out.println("An error was logged creating an author.");
				}
				break;
			}
			case "2":{
				mainMenu();
				break;
			}
			case "3":{
				removeCartItem();
				break;
			}
			case "4":{
				cart.clear();
				paymentDue = 0;
				System.out.println("Cart cleared");
				mainMenu();
				break;
			}
		}		
	}

	private static void removeCartItem() {
		for(Product p: cart) {
			System.out.println("ID: " + p.getProductID() + "\t " + p.getProductName());
		}
		System.out.println("Enter product id to remove:");
	    int chosenProduct = input.nextInt();
		boolean isFound = false;

	    for(Product cartItem: cart) {
	        if(chosenProduct == cartItem.getProductID()) {
				isFound=true;
	            Product stockItem = new Product(); // Create a new product
	            stockItem.setProductID(cartItem.getProductID());
	            stockItem.setProductName(cartItem.getProductName());
	            stockItem.setProductPrice(cartItem.getProductPrice());
	            stockItem.setProductQty(cartItem.getProductQty());
	            
	            System.out.println("How many units do you wish to add remove from your cart?");
	            int cartQty = input.nextInt();
	            
	            // If units entered is greater than the stock it shows not enough in stock
	            while(cartQty > cartItem.getProductQty()) {
	                System.out.println("Not enough items in cart.");
	                System.out.println("Current stock: " + cartItem.getProductQty());
	                System.out.println("Please try again.");
	                cartQty = input.nextInt();
	            }
	            // If units entered is 0 or less an error appears
	            
	            cartItem.setProductQty(cartItem.getProductQty() -  cartQty);
	            
	            stock.add(stockItem);
	            System.out.println(cartQty + " " + cartItem.getProductName() + "  was removed from cart.");
	            paymentDue -= stockItem.getProductPrice() * stockItem.getProductQty() ;
	            break;
	        }
	        if(isFound==false) {
	    		System.out.println("----------------------------------------------");
	    		System.out.println("No Product with id " + chosenProduct + " was found.");
	    		System.out.println("----------------------------------------------");
	    		System.out.println("Returning to Main Menu ...  ");
	    		mainMenu();
	    	}
		    
	     }    
		mainMenu();

	}

	private static void finalTransaction() {
		
		System.out.println("Transaction Price is €" + paymentDue);
		System.out.println("Please enter payment");
		double moneyEntered = input.nextDouble(); //Holds money entered 
		
		while(moneyEntered<paymentDue) {
			double balance = paymentDue - moneyEntered;
			System.out.println("Balance Remaining: " + currency.format(balance));
			System.out.println("Please enter remaining balance."); 
			moneyEntered = moneyEntered + input.nextDouble();
		}
	
		System.out.println("Transaction Successful, Please take your product.");
		
		if(moneyEntered>paymentDue) {
			double change = moneyEntered - paymentDue;
			System.out.println("Change due: €" + currency.format(change));
		}
		System.out.println("-----------------receipt-----------------------");
	    System.out.println();
	    
	    for(Product cartItem: cart) {
	    	
	        System.out.println(cartItem.getProductID() + "   " + cartItem.getProductName() + "    " + "Quanity: " + cartItem.getProductQty() + "    Price: €" + cartItem.getProductQty()*cartItem.getProductPrice());
	    }
		double change = moneyEntered - paymentDue;
		System.out.println();
		System.out.print("Total due: €" + paymentDue);
		System.out.print("  Payment: €" + moneyEntered);
		System.out.println("\t Change due: €" + currency.format(change));
		System.out.println("----------------------------------------------");			
		cart.clear();
		paymentDue = 0;
		mainMenu();
	}				


	private static void prePopulate() {
				
		Boots Predator = new Boots(101,"Predator Accuracy+","Boots","Adidas",200,2,39.5,"Blue","Cotton");
		Boots Superfly  = new Boots(102,"Superfly","Boots","Nike",149.99,5,42.0,"Red","Leather");
		Boots Mercurial  = new Boots(103,"Mercurial","Boots","Nike",60,3,43.0,"Black","Nylon");
		Boots Tiempo  = new Boots(104,"Tiempo","Boots","Nike",249.99,5,35.5,"Grey","Leather");	
		Boots Phantom  = new Boots(105,"Phantom","Boots","Nike",124.99,2,39.5,"Orange","Cotton");
		Boots Speedportal  = new Boots(106,"Speedportal ","Boots","Adidas",300,1,45.0,"Green","Leather");	
		Boots Copa  = new Boots(107,"Copa","Boots","Adidas",300,3,40.5,"White","Cotton");
		Boots Vapor  = new Boots(108,"Vapor","Boots","Nike",239.99,4,44.0,"Blue","Leather");	
		Boots Tekela  = new Boots(109,"Tekela","Boots","New Balance",140,5,46.0,"Orange","Nylon");	
		
		stock.add(Predator);
		stock.add(Superfly);
		stock.add(Mercurial);
		stock.add(Tiempo);
		stock.add(Speedportal);
		stock.add(Phantom);
		stock.add(Copa);
		stock.add(Tekela);
		stock.add(Vapor);
		
		allBoots.add(Predator);
		allBoots.add(Superfly);
		allBoots.add(Mercurial);
		allBoots.add(Tiempo);
		allBoots.add(Speedportal);
		allBoots.add(Phantom);
		allBoots.add(Copa);
		allBoots.add(Tekela);
		allBoots.add(Vapor);
		
		Jerseys Mbappe = new Jerseys(1001,"Mbappe Kylian","Jersey","Nike",200,2,"M","Blue","PSG","Football");
		Jerseys Messi = new Jerseys(1002,"Lionel Messi","Jersey","Nike",200,4,"L","White","BCN","Football");
		Jerseys Ronaldo = new Jerseys(1003,"Cristiano Ronaldo","Jersey","Adidas",200,2,"M","Red","MUN","Football");
		Jerseys Curry = new Jerseys(1004,"Stephen Curry","Jersey","Nike",180,6,"M","White","GSW","Basketball");
		Jerseys Durant = new Jerseys(1005,"Kevin Durant","Jersey","Nike",180,1,"S","BLack","BKN","Basketball");
		Jerseys James = new Jerseys(1006,"LeBron James","Jersey","Nike",180,2,"M","Yellow","LAL","Basketball");
		Jerseys Doncic = new Jerseys(1007,"Luka Doncic","Jersey","Adidas",180,3,"M","Blue","DAL","Basketball");
		Jerseys Iverson = new Jerseys(1008,"Allen Iverson","Jersey","Nike",200,4,"L","BLack","PHI","Basketball");
		Jerseys Betts = new Jerseys(1009,"Mookie Betts","Jersey","Adidas",159.99,2,"M","White","LAD","Baseball");
		Jerseys Tatis = new Jerseys(1011,"Fernando Tatis Jr.","Jersey","Nike",169.99,2,"S","Grey","SDP","Baseball");
		Jerseys Kershaw = new Jerseys(1012,"Clayton Kershaw","Jersey","Nike",149.99,6,"M","Green","LAD","Baseball");

	
		stock.add(Mbappe);
		stock.add(Messi);
		stock.add(Ronaldo);
		stock.add(Curry);
		stock.add(Durant);
		stock.add(James);
		stock.add(Doncic);
		stock.add(Iverson);
		stock.add(Betts);
		stock.add(Tatis);
		stock.add(Kershaw);
		

		allJerseys.add(Mbappe);
		allJerseys.add(Messi);
		allJerseys.add(Ronaldo);
		allJerseys.add(Curry);
		allJerseys.add(Durant);
		allJerseys.add(James);
		allJerseys.add(Doncic);
		allJerseys.add(Iverson);
		allJerseys.add(Betts);
		allJerseys.add(Tatis);
		allJerseys.add(Kershaw);
	}
	
}
