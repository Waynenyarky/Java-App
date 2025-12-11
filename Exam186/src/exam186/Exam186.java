/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package exam186;

/**
 *
 * @author John Wayne Enrique
 */
import java.util.Scanner;
public class Exam186 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner scan;
        scan = new Scanner(System.in);
        
        // Menu items and their prices
        String[] fruits = {"Apple", "Peaches", "Pineapple", "Orange", "Lime"};
        int[] prices = {10, 10, 35, 10, 10};
        
        char choice = 0;
        int total = 0;
        
        do {
            System.out.println("Menu:");
            for (int i = 0; i < fruits.length; i++) {
                System.out.println(fruits[i] + ": " + prices[i]);
            }
            
            System.out.print("What do you like?\nA for Apple\nP for Peaches\nPA for Pineapple\nO for Orange\nL for Lime\nEnter Selection: ");
            String selection = scan.next().toUpperCase();
            
            int index;
            switch (selection) {
                case "A" -> index = 0;
                case "P" -> index = 1;
                case "PA" -> index = 2;
                case "O" -> index = 3;
                case "L" -> index = 4;
                default -> {
                    System.out.println("Invalid selection.");
                    continue;
                }
            }
            
            System.out.print("How many pieces: ");
            int quantity = scan.nextInt();
            
            total += prices[index] * quantity;
            
            System.out.print("Would you like to have another order? (Y/N): ");
            choice = scan.next().charAt(0);
        } while (choice == 'Y' || choice == 'y');
        
        System.out.println("Total: " + total);
        
        if (total >= 500) {
            float discountedTotal;
            discountedTotal = (float) (total * 0.9);
            System.out.println("Discounted Total: " + discountedTotal);
        }
        
        scan.close();
        }
}