/*
 * An inventory system designed to manage the creating, buying, selling, and tracking of fruits, vegetables, and preserves and their expiry dates.
 * Student Name: James Pike
 * Student Number: 040 979 805
 * Course: CST8130 - Data Structures : CET-CS-Level 3
 * @author James Pike
 */

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Main {

    /**
     * Main driver method of the program. Uses a classic do/while structure to repeatedly display a menu until user exits.
     * @param args: String arguments.
     */

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);                 //Create new scanner object input.
        input.useDelimiter(Pattern.compile("[\r\n]+"));         //Use Delimiter with scanner to accept blank space.
        LocalDate date = LocalDate.now();                       //Creation of a LocalDate object and assigned to current date.
        Inventory x = new Inventory();                          //Creation of an Inventory instance to store FoodItems.
        System.out.println();                                   //Newline before displaying menu.

        do {
            try{
                displayMenu();                                  //Display menu options 1-9.

                switch (input.nextInt()) {
                    case 1:
                        x.addItem(input);                       //Method calls addItem from the Inventory class.
                        break;
                    case 2:
                        System.out.println(x.toString());           //Method prints the output of Inventory's toString method.
                        break;
                    case 3:
                        x.updateQuantity(input, true);      //Updates Inventory's quantity with a "Buy" flag.
                        break;
                    case 4:
                        x.updateQuantity(input, false);     //Updates Inventory's quantity with a "Sell" flag.
                        break;
                    case 5:
                        x.searchForItem(input);                     //Searches Inventory for a user specified item code.
                        break;
                    case 6:
                        x.removeExpiredItems(date);                 //Removes any expired items from Inventory based on today's date.
                        break;
                    case 7:
                        x.printExpirySummary(input);            //Prints a summary of all quantities of a code's expiry dates.
                        break;
                    case 8:
                        System.out.println("Please enter today's date (yyyy-mm-dd):");          //Prompts user to enter date.
                        String dateChoice = input.next();
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");      //Creates DateFormatter object to parse String.
                        date = LocalDate.parse(dateChoice, formatter);
                        break;
                    case 9:
                        System.out.println("Exiting Program...");           //Exits program with status code 0.
                        System.exit(0);
                        break;
                    default:
                        System.err.println("Error: Invalid Input.");        //Displays Invalid Input when user chooses unavailable option.
                        break;
                }
            } catch(InputMismatchException e){                          //Catch block to display error when user inputs wrong field.
                System.err.println("Error: Invalid Input.");
                input.next();
            }
        } while (true);             //Loop indefinitely while true.
    }

    /**
     *Method to display menu repeatedly until user exits.
     */
    public static void displayMenu(){
        System.out.println("Please select one of the following:\n" +
                "1: Add Item to Inventory\n" +
                "2: Display Current Inventory\n" +
                "3: Buy Item(s)\n" +
                "4: Sell Item(s)\n" +
                "5: Search for Item\n" +
                "6: Remove Expired Items\n" +
                "7: Print Expiry\n" +
                "8: Change Today's Date\n" +
                "9: To Exit\n" +
                "> ");
    }
}

















