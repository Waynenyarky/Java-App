/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */

package computeoffour;
import java.util.Scanner;
public class ComputeOfFour {

    /*
     Enrique, John Wayne M.
    02/28/2024
    BSIT1-04
     */
    
    //Problem#1
    public static void main(String[] args) {
      Scanner scan;
      scan = new Scanner (System.in);
      
       System.out.println("Enter four numbers:");
        int total = 0; // Variable to store the sum
        int count = 4; // Variable to store the count of numbers
                       // Input four numbers and calculate the sum
        
        for (int i = 0; i < count; i++) {
            int num = scan.nextInt();
            total += num;
        }
        
        double average; // Calculate the average
        average = (double) total / count;
        
        System.out.println("TOTAL: " + total);
        System.out.println("AVERAGE: " + average);
        
        scan.close();
    }
    
}
