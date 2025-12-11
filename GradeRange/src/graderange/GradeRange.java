/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package graderange;
import java.util.Scanner;
public class GradeRange {
    
    /*
    Enrique, John Wayne M.
    02/28/2024
    BSIT1-04
     */
    //Problem#4

    public static void main(String[] args) {
        Scanner scan;
        scan = new Scanner(System.in);
        
         // Prompt the user to enter a grade value
        System.out.print("Enter a grade value: ");
        double grade = scan.nextDouble();
        
        // Display corresponding numeric value based on the legend
        if (grade >= 90 && grade <= 100) {
            System.out.println("90 - 100 = A");
        } else if (grade >= 75 && grade < 90) {
            System.out.println("75 to below 90 = B");
        } else if (grade >= 50 && grade < 75) {
            System.out.println("50 to below 75 = C");
        } else if (grade >= 0 && grade < 50) {
            System.out.println("Below 50 = F");
        } else {
            System.out.println("Below 0 or above 100 = OUT OF RANGE");
        }
        
        scan.close();
    }
    
}
