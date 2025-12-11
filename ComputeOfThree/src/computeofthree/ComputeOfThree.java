/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package computeofthree;
import java.util.Scanner;
public class ComputeOfThree {
    
/*
Enrique, John Wayne M.
02/28/2024
BSIT1-04
 */
//Problem#2

    public static void main(String[] args) {
      
        Scanner scan;
        scan = new Scanner (System.in);
        
        // Reading the three numbers from the user
        System.out.println("Enter three numbers:");
        int num1 = scan.nextInt();
        int num2 = scan.nextInt();
        int num3 = scan.nextInt();
        
        int sum = num1 + num2 + num3; // Calculate the sum
        
        // Printing the total with the input numbers in the desired format
        System.out.println("The sum of " + num1 + ", " + num2 + ", and " + num3 + " is: " + sum);
        
        scan.close();
        
    }
    
}
