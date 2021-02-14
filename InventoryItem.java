import java.util.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Iterator;

public class InventoryItem implements Iterator {       //InventoryItem implements iterator interface to allow for iterating through each Queue of LocalDate objects.
    protected int itemQuantityInStock;
    protected FoodItem item;
    protected Deque expires;          //Initially chose a Queue but upgraded it to a Deque to allow for forward and backwards addition and removal.


    /**
     * Parameterized constructor creates a new Queue of LocalDates and links it to an existing fooditem.
     * @param item: Takes in a fooditem and links the new Queue to it.
     */
    public InventoryItem(FoodItem item) {
        expires = new LinkedList<LocalDate>();
        this.item = item;
    }

    /**
     * Method printExpirySummary() iterates through a specified itemCode and prints the quantity and expiry date of each batch of that item.
     * @param input: Takes in a scanner object to be used for user input.
     */
    public void printExpirySummary(Scanner input){
        System.out.println("Expiry Details:");
        Iterator<LocalDate> x = expires.iterator();             //Creates Iterator of LocalDate objects and assigns it to our Queue.
        LocalDate temp = x.next();                      //Create temp LocalDate object and assign it to first iteration of Queue.
        LocalDate temp2 = temp;                         //Create 2nd temp LocalDate object and assign it to temp for swap purposes.
        System.out.println(temp + ": " + Collections.frequency(expires, temp));         //Print first occurence as it's guaranteed to be unique.

        while (x.hasNext()){            //Now, while the Queue still has more objects,
            temp = x.next();                //AND they aren't the same as temp1,
            if (temp != temp2){
                System.out.println(temp + ": " + Collections.frequency(expires, temp));     //Print out the new occurence along with how many times it occurs in the Queue.
                temp2 = temp;           //Assign temp2 back to temp for swap purposes.
            }
        }                                   //Repeat this loop for aslong as there are more items in the Queue!
        System.out.println();
    }

    /**
     * Method removeExpiredItems() iterates through each Queue stored in the Inventory ArrayList and removes them if they expire before the current date.
     * @param today: Takes in a LocalDate object of the current date to compare expires with.
     */
    public void removeExpiredItems (LocalDate today){
        for (int i = 0; i < expires.size(); i++){           //Run the loop as large as expires Queue.
            Iterator<LocalDate> x = expires.iterator();         //Create LocalDate object iterator for the Queue.
            while(x.hasNext()){                         //While there are still more objects in Queue,
                if (x.next().isBefore(today)){          //If next date expires before today,
                    x.remove();                         //Remove it from the list
                    itemQuantityInStock--;              //And decrement its stock count.
                }
            }
        }
    }

    /**
     * Method getItemCode() is a getter method to return itemCode.
     * @return: Returns itemCode.
     */
    public int getItemCode(){
        return item.itemCode;
    }


    @Override
    public boolean hasNext() {          //Method hasNext() required to use iterator interface.
        return false;
    }

    @Override
    public Object next() {           //Method next() required to use iterator interface.
        return null;
    }

    @Override
    public void remove() {           //Method remove() required to use iterator interface.

    }

    /**
     * Method addItem() continues on from Inventory's addItem() to add additional information stored in this class
     * @param input: Takes in a Scanner object to be used within it's method.
     * @return: Returns a boolean true if method finishes successfully.
     */
    public boolean addItem(Scanner input) {
        try {
            item.addItem(input);
            boolean quantity = false;
            while (!quantity) {
                try {
                    System.out.println("Enter the quantity for the item:");
                    itemQuantityInStock = input.nextInt();
                    if (itemQuantityInStock <= 0) {
                        quantity = false;
                        System.err.println("Invalid quantity!");
                    } else {
                        quantity = true;
                    }
                } catch (InputMismatchException e) {
                    System.err.println("Invalid quantity!");
                    input.next();
                }
            }

            System.out.println("Enter the expiry date of the item (yyyy-mm-dd or none): ");
            String inputDate = input.next();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate date = LocalDate.parse(inputDate, formatter);

            for (int i = 0; i < itemQuantityInStock; i++) {
                expires.add(date);
            }
            return true;

        } catch (DateTimeParseException e) {
            System.out.println("Could not create date from input, please use format yyyy-mm-dd");
            System.out.println(e.getLocalizedMessage());
        }
        return true;
    }

    /**
     * Method updateQuantity continues on from Inventory's updateQuantity() to update additional information stored in this class.
     * @param input: Takes in a scanner object to be used in this class.
     * @param amount: Takes in an integer amount to indicate how much to add/subtract from itemQuantityInStock.
     * @return: Returns a boolean if method completes successfully.
     */
    public boolean updateQuantity (Scanner input, int amount){
        LocalDate s = null;
        try {
        if ((itemQuantityInStock + amount) < 0) {
            System.err.println("Insufficient stock in inventory...");
            System.err.println("Error: could not sell item");
            return false;
        } else {
            itemQuantityInStock += amount;
            if (amount > 0) {
                boolean x = true;
                while (x){
                    try {
                        System.out.println("Enter the expiry date of the item (yyyy-mm-dd or none): ");
                        String inputDate = input.next();
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                        LocalDate date = LocalDate.parse(inputDate, formatter);
                        s = date;
                        x = false;
                    } catch (DateTimeParseException e) {
                        System.out.println("Could not create date from input, please use format yyyy-mm-dd");
                        System.out.println(e.getLocalizedMessage());
                        x = true;
                    }
                }
                for (int i = 0; i < amount; i++) {
                    expires.addFirst(s);                //Add localdate object to the front of Queue.

                }
            }
            if (amount < 0) {
                for (int i = 0; i > amount; i--) {
                    expires.removeLast();               //Remove localdate object from the back of Queue.
                }
            }
        }
        } catch (DateTimeParseException e) {
            System.out.println("Could not create date from input, please use format yyyy-mm-dd");
            System.out.println(e.getLocalizedMessage());            //This is a neat way to print out the error message from console.
        }
        return true;
    }

    /**
     * Method toString() creates a StringBuilder object and appends this class's information to the existing toString call.
     * @return: Returns the appended string to the calling method.
     */
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append(item.toString()).append(" qty: ").append(itemQuantityInStock);
        return sb.toString();
    }


}