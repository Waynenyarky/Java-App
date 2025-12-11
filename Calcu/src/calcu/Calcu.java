/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package calcu;
import java.util.Scanner;
public class Calcu {

    /*
    Enrique, John Wayne M.
    02/28/2024
    BSIT1-04
     */
    //Problem#6
    
    public static void main(String[] args) {
        Scanner scan;
        scan = new Scanner(System.in);
        
        System.out.print("Enter an operation: ");        
        String input = scan.nextLine();
        
        // Read the input from the user
        String[] parts = input.split(" ");
        
        // Check if the input format is valid (should have three parts)
        if (parts.length != 3) {
            System.out.println("Invalid input format.");
            return;
        }
        
        double val1, val2;
        char operator;
        try {
            // Parse the first operand to double
            val1 = Double.parseDouble(parts[0]);
            
            // Get the operator character
            operator = parts[1].charAt(0);
            
            // Parse the second operand to double
            val2 = Double.parseDouble(parts[2]);
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            System.out.println("Invalid input format.");
            return;
        }
        
        // Declare variables to store the result of arithmetic operations
        double sum, diff, pro, quot, mod;
        
        // Perform the arithmetic operation based on the operator
        switch (operator) {
            case '+' -> {
                sum = val1 + val1;
                System.out.println("Sum: " + sum);
            }
            case '-' -> {
                diff = val1 - val2;
                System.out.println("Difference: " + diff);
            }
            case '*' -> {
                pro = val1 * val2;
                System.out.println("Product: " + pro);
            }
            case '/' -> {
                if (val2 == 0) {
                    System.out.println("Cannot divide by zero.");
                    return;
                }
                quot = val1 / val2;
                System.out.println("Quotient: " + quot);
            }
            case '%' -> {
                if (val2 == 0) {
                    System.out.println("Cannot calculate modulo by zero.");
                    return;
                }
                mod = val1 % val2;
                System.out.println("Modulo: " + mod);
            }
            default -> System.out.println("Invalid operator.");
        }
        
        scan.close();
     
    }
    
}
