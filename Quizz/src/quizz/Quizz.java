/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package quizz;

/**
 *
 * @author John Wayne Enrique
 */
import java.util.Scanner;
public class Quizz {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner scan;
        scan = new Scanner(System.in);
        
        System.out.print("Enter First Number: ");
        int first = scan.nextInt();
        
        System.out.print("Enter Second Number: ");
        int second = scan.nextInt();
        
        int add = first + second;
        int sub = first - second;
        int mul = first * second;
        int div = first / second;
        
        System.out.println(add );
        System.out.println(sub );
        System.out.println( mul );
        System.out.println(  div );
        
        scan.close();      
    }
    
}
