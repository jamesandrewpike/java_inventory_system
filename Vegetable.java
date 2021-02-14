import java.util.Scanner;

public class Vegetable extends FoodItem {

    private String farmName;

    public Vegetable(){}

    /**
     * Method addItem() Overrides and calls it's parent class before appending it's own details to the item.
     * @param input: Takes in a scanner object from the main class.
     * @return: returns a boolean true if finishes successful.
     */
    @Override
    public boolean addItem(Scanner input) {
        super.addItem(input);
        System.out.println("Enter the name of the farm supplier:");
        farmName = input.next();
        return true;
    }

    /**
     * Method toString overrides and calls it's parent class to print out the item details.
     * @return: returns the appended string to the calling function.
     */
    @Override
    public String toString(){
    return super.toString() + " farm supplier: " + farmName;
    }
}
