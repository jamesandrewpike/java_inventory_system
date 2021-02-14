import java.time.LocalDate;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Inventory {

    //An ArrayList is the core data structure of this program. Each element will store a Deqeue from the InventoryItem class.
    private ArrayList<InventoryItem> inventory;
    private int numItems;

    //Default constructor creates an empty ArrayList named inventory.
    public Inventory() {                        //No-Arg default constructor sets FoodItem array to size 20.
        inventory = new ArrayList<>();
    }

    /**
     * A toString method that utilizes StringBuilder object to append each inventory item to a String and output.
     * @return: returns a string to the calling function.
     */
    public String toString() {
        StringBuilder sb = new StringBuilder();             //Creates StringBuilder object.
        System.out.println("Inventory:");
        for (int i = 0; i < numItems; i++) {                //For each inventory item, append it to the string with a newline.
            sb.append(inventory.get(i).toString());
            sb.append("\n");
        }
        return sb.toString();                   //Return this string to the calling method.
    }

    /**
     * Method removeExpiredItems scans through each inventory item and removes expired localDate objects from each Queue.
     * @param today: takes in a local date object in the parameter.
     */
    public void removeExpiredItems(LocalDate today){
        for (int i = 0; i < inventory.size(); i++){
            inventory.get(i).removeExpiredItems(today);
        }
    }

    /**
     *Method printExpirySummary scans through each inventory item and prints the quantity and expiry dates of a specified code.
     * @param input: Takes in a Scanner input from the Main Assign1 class to be used within it's method.
     */
    public void printExpirySummary(Scanner input){
        System.out.println("Enter the code for the item: ");
        int code = input.nextInt();

        for (int i = 0; i < numItems; i++){
            if (inventory.get(i).getItemCode() == code){
                System.out.println(inventory.get(i).toString());
                inventory.get(i).printExpirySummary(input);
                return;
            }
        }
        System.err.println("Code not found in inventory...");
    }

    /**
     * Method alreadyExists() to determine whether or not an item already exists in the inventory.
     * @param item: takes in a FoodItem parameter.
     * @return: returns 1 or -1 if item exists or not.
     */
    public int alreadyExists(FoodItem item) {
        for (int i = 0; i < numItems; i++) {
            if (inventory.get(i).item.itemCode == item.itemCode) {          //If itemCode in inventory matches, return 1.
                return 1;
            }
        }
        return -1;
    }

    /**
     * Method searchForItem prompts user to input an itemCode and searches the inventory to see if any exist.
     * @param input: takes in a scanner object.
     */
    public void searchForItem(Scanner input) {
        System.out.println("Enter the code for the item: ");
        int code = input.nextInt();

        for (int i = 0; i < numItems; i++){
            if (inventory.get(i).getItemCode() == code){
                System.out.println(inventory.get(i).toString());
                return;
            }
        }
        System.err.println("Code not found in inventory...");
    }

    /**
     * Method addItem() allows the user to enter a new foodItem.
     * @param input: takes in a Scanner object from the main class.
     * @return: returns a boolean if the method finishes.
     */
    public boolean addItem(Scanner input) {

        System.out.println("Do you wish to add a fruit(f), vegetable(v) or a preserve(p)?");
        String option = input.next();

        while (!option.equals("f") && !option.equals("v") && !option.equals("p")){
            System.err.println("Invalid Entry");
            System.out.println("Do you wish to add a fruit(f), vegetable(v) or a preserve(p)?");
            option = input.next();
        }

        if (option.equals("f")){
            Fruit fruit = new Fruit();
                fruit.inputCode(input);
            if (alreadyExists(fruit) != -1){
                System.err.println("Error: code already exists in inventory\r");
                return false;
            }
            InventoryItem f = new InventoryItem(fruit);
            f.addItem(input);
            inventory.add(f);
            numItems++;
        }
        if (option.equals("v")){
            Vegetable vegetable = new Vegetable();
            vegetable.inputCode(input);
            if (alreadyExists(vegetable) != -1){
                System.err.println("Error: code already exists in inventory");
                return false;
            }
            InventoryItem v = new InventoryItem(vegetable);
            v.addItem(input);
            inventory.add(v);
            numItems++;
        }
        if (option.equals("p")){
            Preserve preserve = new Preserve();
            preserve.inputCode(input);
            if (alreadyExists(preserve) != -1){
                System.err.println("Error: code already exists in inventory");
                return false;
            }
            InventoryItem p = new InventoryItem(preserve);
            p.addItem(input);
            inventory.add(p);
            numItems++;
        }
        System.out.println();
        return true;
    }

    /**
     * Method updateQuantity() updates the itemQuantityInStock variable when buying/selling.
     * @param input: takes in Scanner object from the main class to use within the method.
     * @param buyOrSell: takes in a Buy or Sell flag from the main class.
     * @return: returns a boolean true if method succeeds.
     */
    public boolean updateQuantity(Scanner input, boolean buyOrSell) {
        if (numItems == 0) {
            if (buyOrSell) {
                System.err.println("Error...could not buy item");
                return false;
            } else System.err.println("Error: could not sell item");
            return false;
        }
        try {
            System.out.println("Enter valid item code: ");
            int code = input.nextInt();
            if (buyOrSell) {
                for (int i = 0; i < numItems; i++) {
                    if (inventory.get(i).item.itemCode == code) {
                        System.out.println("Enter valid quantity to buy: ");
                        int temp = input.nextInt();
                        if (temp > 0){
                            inventory.get(i).updateQuantity(input, temp);
                            return true;
                        }
                        else{
                            System.err.println("Invalid Quantity..\nError: could not buy item");
                            return false;
                        }
                    }
                }
                System.err.println("Code not found in inventory..");
                System.err.println("Error: could not buy item");

            } else {
                for (int i = 0; i < numItems; i++) {
                    if (inventory.get(i).item.itemCode == code) {
                        System.out.println("Enter valid quantity to sell: ");
                        int temp = input.nextInt();
                        if (temp <= 0) {
                            System.err.println("Invalid Quantity..\nError: could not sell item");
                            return false;
                        } else {
                            temp = -temp;
                            inventory.get(i).updateQuantity(input,temp);
                            return true;
                        }
                    }
                }
            }
        } catch (InputMismatchException e){
            System.err.println("Error: Invalid Input!");
            input.next();
        }
        return false;
    }
}
































