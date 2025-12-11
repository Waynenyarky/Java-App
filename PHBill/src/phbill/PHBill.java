/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package phbill;
import java.util.Scanner;
public class PHBill {

    /*
    Enrique, John Wayne M.
    02/28/2024
    BSIT1-04
     */
    //Problem#10
    
    public static void main(String[] args) {
        Scanner scan;
        scan = new Scanner(System.in);
        
        System.out.print("Enter amount value:");
        int ph = scan.nextInt();

        // Validate the amount range
        if (ph < 1 || ph > 999) {
            System.out.println("Invalid amount. \nPlease enter a value between 1 and 999." );
            return;
        }

        // Define denominations
        int[] bill = { 500, 200, 100, 50, 20, 10, 5, 1 };
        int[] length = new int[bill.length];

        // Calculate bills and coins
        for (int i = 0; i < bill.length; i++) {
            if (ph >= bill[i]) {
                length[i] = ph / bill[i];
                ph %= bill[i];
            }
        }

        // Print the results
        for (int i = 0; i < bill.length; i++) {
            System.out.println(bill[i] + " = " + length[i]);
        }

        scan.close();
        
    }
    
}
