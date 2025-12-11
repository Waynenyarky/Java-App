/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package vowels;
import java.util.Scanner;
public class Vowels {

    /*
    Enrique, John Wayne M.
    02/28/2024
    BSIT1-04
     */
    //Problem#3
    
    public static void main(String[] args) {
        Scanner scan;
        scan = new Scanner(System.in);
        
         while (true) {
            // Prompt the user to enter a character
            System.out.print("Enter a Character: ");
            char ch = scan.next().charAt(0);
            
            // Check if the entered character is a vowel or not a vowel
            if (isVowel(ch)) {
                System.out.println("VOWEL");
                System.out.println("{A, a, e, I, I, O, o, U, u}");
            } else {
                System.out.println("NOT A VOWEL");
                System.out.println("{3, 7, r, W, $, @, <, and the likes.}");
            }
            
            // Print separator
            System.out.println("----------------------------------------------");
        }
    }
    
    // Method to check if a character is a vowel
    public static boolean isVowel(char v) {
        v = Character.toLowerCase(v); // Convert character to lowercase
        return v == 'a' || v == 'e' || v == 'i' || v == 'o' || v == 'u';
    }
    
}
