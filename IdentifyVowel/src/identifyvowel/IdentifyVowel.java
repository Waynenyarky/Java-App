/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package identifyvowel;
import java.util.Scanner;
public class IdentifyVowel {

    /*
    Enrique, John Wayne M.
    02/28/2024
    BSIT1-04
     */
    //Problem#8
    
    public static void main(String[] args) {
        Scanner scan;
        scan = new Scanner (System.in);
       
        
        System.out.print("Enter an alphabet: ");
        String input = scan.nextLine();
        
        // Check if the input is a single character
        if (input.length() != 1) {
            System.out.println("INVALID INPUT");
            return;
        }
        
        char up = input.charAt(0);
        
        // Check if the input is an alphabet
        if (!Character.isLetter(up)) {
            System.out.println("INVALID INPUT");
            return;
        }
        
        // Convert to uppercase for easier comparison
        up = Character.toUpperCase(up);
        
        // Check if the alphabet is a vowel or consonant
        if (up == 'A' || up == 'E' || up == 'I' || up == 'O' || up == 'U') {
            System.out.println("VOWEL");
        } else {
            System.out.println("CONSONANT");
        }
        
        // Check if the alphabet is uppercase or lowercase
        if (Character.isUpperCase(input.charAt(0))) {
            System.out.println("UPPERCASE");
        } else {
            System.out.println("LOWERCASE");
        }
        
        scan.close();
       
    }
    
}
