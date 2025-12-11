/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package pesoval;
import java.util.Scanner;
public class PesoVal {

    /*
    Enrique, John Wayne M.
    02/28/2024
    BSIT1-04
     */
    //Problem#5
   
    public static void main(String[] args) {
        Scanner scan;
        scan = new Scanner(System.in);
        
        // Prompting the user to enter a Peso value
        System.out.print("Enter PESO value: ");
        double pesoVal = scan.nextDouble(); // Reading the Peso value from the user
        
        // Convert Peso to Dollar, Yen, and Riyal based on the legend
        double dollarVal = pesoVal / 44.52; // Converting Peso to Dollar
        double yenVal = pesoVal / 0.437; // Converting Peso to Yen
        double riyalVal = pesoVal * 0.084; // Converting Peso to Riyal
        
        // Display the converted values 
        System.out.println("Exchange value of "+ pesoVal + ":");
        System.out.println("Php " + pesoVal + " = " + dollarVal + " Dollar");
        System.out.println("Php " + pesoVal + " = " + yenVal + " Yen");
        System.out.println("Php " + pesoVal + " = " + riyalVal + " Riyal");
        
        scan.close();
    }
    
}
