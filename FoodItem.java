import java.text.DecimalFormat;
import java.util.InputMismatchException;
import java.util.Scanner;

public class FoodItem {

    //protected instance variables as they need to be accessed within other classes.
    protected int itemCode;
    protected String itemName;
    protected float itemPrice;
    protected float itemCost;

    public FoodItem(){}

    /***
     * Method inputCode() takes in a code from the user and assigns it to a FoodItem.
     * @param input: Takes in a scanner object to be used within it's method.
     * @return: Returns a boolean if method completes successfully.
     */
    public boolean inputCode(Scanner input){
        boolean code = false;
        while (!code) {
            try {
                System.out.println("Enter the code for the item:");
                itemCode = input.nextInt();
                code = true;
            } catch (InputMismatchException e) {
                System.err.println("Invalid code");
                input.next();
            }
        }
        return true;
    }

    /**
     * Method addItem() asks user for details of item and writes them into an arraylist.
     * @param input: Takes in a scanner object to use within the method.
     * @return: Returns a boolean if method completes successfully.
     */
    public boolean addItem(Scanner input){
        System.out.println("Enter the name for the item:");
        itemName = input.next();
        boolean cost = false;
        while (!cost) {
            try {
                System.out.println("Enter the cost for the item:");
                itemCost = input.nextFloat();
                if (itemCost <= 0){
                    cost = false;
                    System.err.println("Invalid cost!");
                } else {
                    cost = true;
                }
            } catch (InputMismatchException e) {
                System.err.println("Invalid cost!");
                input.next();
            }
        }
        boolean price = false;
        while (!price) {
            try {
                System.out.println("Enter the sales price of the item:");
                itemPrice = input.nextFloat();
                if (itemPrice <= 0){
                    price = false;
                    System.err.println("Invalid price!");
                } else {
                    price = true;
                }
            } catch (InputMismatchException e) {
                System.err.println("Invalid price!");
                input.next();
            }
        }
        return true;
    }

    /**
     * Method toString appends it's FoodItem details to a string.
     * @return: Returns a string to the calling function.
     */
    public String toString(){
        DecimalFormat decimal = new DecimalFormat("0.00");
        return "Item: " + itemCode + " " + itemName + " " + "price: $" + decimal.format(itemPrice) +
                " cost: $" + decimal.format(itemCost);
    }
}























