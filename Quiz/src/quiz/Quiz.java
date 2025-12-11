/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package quiz;

/**
 *
 * @author John Wayne Enrique
 */
import java.util.Scanner;
public class Quiz {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner scan;
        scan = new Scanner (System.in);
        
        System.out.print("Enter Name: ");
        String name = scan.nextLine();
        
        System.out.print("Enter Age: ");
        int age = scan.nextInt();
        
        scan.nextLine();
        
        System.out.print("House Number: ");
        int house = scan.nextInt();
        
        scan.nextLine();

        System.out.print("Barangay: ");
        String bara = scan.nextLine();
        
        System.out.print("Municipality: ");
        String muni = scan.nextLine();
        
        System.out.print("Province: ");
        String pro = scan.nextLine();
        
        System.out.print("Birthdate: ");
        String birth = scan.nextLine();
        
        System.out.print("I'm " + name + " and I'm " + age + " years old live in " + house + ", " + bara + ", " + muni + ", " + pro + " and my Birthdate is " + birth );
        
        scan.close();
        
        }
    
}
