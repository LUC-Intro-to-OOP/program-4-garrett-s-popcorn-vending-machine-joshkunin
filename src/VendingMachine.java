/**
 * Garrett's popcorn vending machine

Garrett's popcorn is looking to launch a vending machine that sells their world renowned popcorn.  The vending machine will contain a total of 9 products. 
The products are listed below in the chart along with purchase price.  To select an item, the end-user will enter the product row along with the column letter.
The program should enforce that only product row numbers and column letters can be entered. The end-user will be able to continously select up to  as they would 
like until they enter a sentinel value.
   

At the conslusion of selecting all items, the program will display the total number of items purchased and the total cost.

**Hints**
- Make use of two dimensional arrays.
- Consider parallel arrays


| Product Row | Column P               | Column N                       | Column R           |
|-------------|------------------------|-----------------------------------------------------|
| 0           | Garrett Mix ($14.99)   |  Pecan Carmel Crisp ($10.99)   | Plain ($6.99)      |
| 1           | Caramel Crisp ($16.99) |  Cashew Carmel Crisp ($9.99)   | Buttery ($8.99     |
| 2           | Cheese Corn ($12.99)   |  Almond Carmel Crisp ($11.99)  | Sweet Corn ($7.99)  


 */
import java.util.Scanner; //Import the Scanner Class
        public class GarrettsPopcorn {
        public static void main(String[] args) throws Exception {
        // Constants for the vending machine
        final int NUM_ROWS = 3;
        final int NUM_COLS = 3;
        final char[] COLUMNS = {'P', 'N', 'R'};
        final String SENTINEL = "END";
        
        // Product names and prices - using parallel 2D arrays
        final String[][] PRODUCT_NAMES = {
            {"Garrett Mix", "Pecan Carmel Crisp", "Plain"},
            {"Caramel Crisp", "Cashew Carmel Crisp", "Buttery"},
            {"Cheese Corn", "Almond Carmel Crisp", "Sweet Corn"}
        };
        
        final double[][] PRODUCT_PRICES = {
            {14.99, 10.99, 6.99},
            {16.99, 9.99, 8.99},
            {12.99, 11.99, 7.99}
        };
        
        // Variables for tracking purchases
        Scanner scnr = new Scanner(System.in);
        String userInput;
        int row;
        char column;
        int totalItems = 0;
        double totalCost = 0.0;
        boolean validEntry;
        
        // Welcome message
        System.out.println("=== Garrett's Popcorn Vending Machine ===");
        System.out.println("Enter row (0-2) and column (P/N/R) to select items");
        System.out.println("Type 'END' when finished");
        System.out.println("");
        
        // Main purchase loop
        while (true) {
            // Get user input
            System.out.print("Enter selection (row column) or 'END' to finish: ");
            userInput = scnr.nextLine().trim().toUpperCase();
            
            // Check for sentinel value
            if (userInput.equals(SENTINEL)) {
                break;
            }
            
            // Parse the input - expecting "row column" format
            String[] parts = userInput.split("\\s+");
            validEntry = false;
            
            // Validate we have exactly two parts
            if (parts.length == 2) {
                try {
                    // Parse row number
                    row = Integer.parseInt(parts[0]);
                    
                    // Parse column letter
                    column = parts[1].charAt(0);
                    
                    // Validate row is between 0-2
                    if (row >= 0 && row < NUM_ROWS) {
                        // Validate column is P, N, or R
                        if (column == 'P' || column == 'N' || column == 'R') {
                            // Convert column letter to array index (P=0, N=1, R=2)
                            int colIndex = (column == 'P') ? 0 : (column == 'N') ? 1 : 2;
                            
                            // Get product info
                            String productName = PRODUCT_NAMES[row][colIndex];
                            double productPrice = PRODUCT_PRICES[row][colIndex];
                            
                            // Display selection and add to totals
                            System.out.println("You selected: " + productName + " - $" + String.format("%.2f", productPrice));
                            System.out.println("Added to cart!");
                            System.out.println("");
                            
                            totalItems++;
                            totalCost += productPrice;
                            validEntry = true;
                        }
                    }
                } catch (NumberFormatException e) {
                    // Invalid row number
                }
            }
            
            // Handle invalid input
            if (!validEntry) {
                System.out.println("Invalid selection! Please use row 0-2 and column P/N/R");
                System.out.println("Example: '1 P' or '2 R'");
                System.out.println("");
            }
        }
        
        // Final summary
        System.out.println("=== Purchase Summary ===");
        System.out.println("Total items purchased: " + totalItems);
        System.out.printf("Total cost: $%.2f%n", totalCost);
        System.out.println("Thank you for shopping at Garrett's Popcorn!");
    }
}
