/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package quizzz;

/**
 *
 * @author John Wayne Enrique
 */
import java.util.Scanner;
public class Quizzz {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner scan;
        scan = new Scanner(System.in);
        
        float num1, num2, num3, ave;
        
        System.out.print("Enter First Grade: ");
        num1 = scan.nextInt();
        
        System.out.print("Enter Second Grade: ");
        num2 = scan.nextInt();
        
        System.out.print("Enter Third Grade: ");
        num3 = scan.nextInt();
        
        ave = (num1 + num2 + num3) / 3;
        
         String result;
        if (ave >= 50) {
            result = "Pass";
        } else {
            result = "Fail";
        }
        
        System.out.println("Average: " + ave);
        System.out.println("Result: " + result);
                               
    } 
}

