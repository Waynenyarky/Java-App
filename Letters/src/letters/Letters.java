/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package letters;
import java.util.Scanner;
public class Letters {

    /*
    Enrique, John Wayne M.
    02/28/2024
    BSIT1-04
     */
    //Problem#7
    
    public static void main(String[] args) {
        Scanner scan;
        scan = new Scanner(System.in);
        
        System.out.println("Enter 10 letters:");
        
        int upCase = 0; // Variable to count uppercase letters
        int lowCase = 0; // Variable to count lowercase letters
        
        // Loop to read each letter and count uppercase and lowercase letters
        for (int i = 0; i < 10; i++) {
            char letter = scan.next().charAt(0); // Read a single character
            
            // Check if the character is uppercase or lowercase
            if (Character.isUpperCase(letter)) {
                upCase++;
            } else if (Character.isLowerCase(letter)) {
                lowCase++;
            }
        }
        
        System.out.println("UPPERCASE: " + upCase);
        System.out.println("LOWERCASE: " + lowCase);
        
        scan.close();
    }
    
}
