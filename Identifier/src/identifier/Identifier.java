/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package identifier;
import java.util.Scanner;
public class Identifier {

    /*
    Enrique, John Wayne M.
    02/28/2024
    BSIT1-04
    */
    //Problem#9
    public static void main(String[] args) {
        Scanner scan;
        scan = new Scanner(System.in);
        
        System.out.println("Enter three numbers: ");
        
        int val1 = scan.nextInt();
        int val2 = scan.nextInt();
        int val3 = scan.nextInt();
        
        // Find the smallest, largest, and middle numbers
        int Low = Math.min(Math.min(val1, val2), val3);
        int High = Math.max(Math.max(val1, val2), val3);
        int mid = (val1 + val2 + val3) - Low - High;
        
        // Check if all three numbers are equal
        if (val1 == val2 && val2 == val3) {
            // Print a message if all numbers are equal
            System.out.println("EQUAL VALUES");
        } else {
            // Print the numbers in ascending and descending order
            System.out.println("ASCENDING: " + Low + ", " + mid + ", " + High);
            System.out.println("DESCENDING: " + High + ", " + mid + ", " + Low);
        }
        
        scan.close();
    }
    
}
