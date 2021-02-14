import java.util.InputMismatchException;
import java.util.Scanner;

public class Preserve extends FoodItem {

    private int jarSize;

    public Preserve(){}

    /**
     * Method addItem() Overrides and calls it's parent class before appending it's own details to the item.
     * @param input: Takes in a scanner object from the main class.
     * @return: returns a boolean true if finishes successful.
     */
    @Override
    public boolean addItem(Scanner input) {
        super.addItem(input);

        boolean size = false;
        while (!size) {
            try {
                System.out.println("Enter the size of jar in millilitres");
                jarSize = input.nextInt();
                if (jarSize <= 0) {
                    size = false;
                    System.err.println("Invalid size!");
                } else {
                    size = true;
                }
            } catch (InputMismatchException e) {
                System.err.println("Invalid size!");
                input.next();
            }
        }
            return true;
    }

    /**
     * Method toString overrides and calls it's parent class to print out the item details.
     * @return: returns the appended string to the calling function.
     */
    @Override
    public String toString(){
       return super.toString() + " size: " + jarSize + "mL";
    }
}
