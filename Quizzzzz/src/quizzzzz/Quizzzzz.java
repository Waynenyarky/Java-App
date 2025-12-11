/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package quizzzzz;

/**
 *
 * @author John Wayne Enrique
 */
import java.util.Scanner;
public class Quizzzzz {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner scan;
        scan = new Scanner(System.in);
        
        System.out.print("Please enter principal amount: ");
        float principal = scan.nextInt();
        
        System.out.print("Enter time in years: ");
        float time = scan.nextInt();
        
        System.out.print("Enter rate annually: ");
        float rate = scan.nextInt();
        
        float simpleInterest = (principal * time * rate) / 100;
        
        System.out.println("Simple interest calculated by the program is: " + simpleInterest);
    }
}