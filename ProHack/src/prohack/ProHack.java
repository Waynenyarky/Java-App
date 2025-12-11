/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package prohack;

/**
 *
 * @author John Wayne Enrique
 */
import java.util.Scanner;
public class ProHack {
     public static void main(String[] args) {
        // TODO code application logic here
          Scanner scan;
          scan = new Scanner (System.in);

          System.out.print("Enter your Name: ");
          String name = scan.nextLine();

          System.out.print("Enter your Course: ");
          String course = scan.nextLine();

          System.out.print("Enter 1st Number: ");
          int value = scan.nextInt();

          System.out.print("Enter 2nd Number: ");
          int num = scan.nextInt();

          int add = value + num;
          int sub = value - num;
          int mul = value * num;
          int quot = value / num;

          System.out.print (name + " of under the " + course + " .The sum of " + value + " and " + num +  " is " +add +" .The difference of " +value+ " and " + num+ " is " +sub+ " .The product of "+ value + " and "+num+ " is " + mul + " The quotient of " + value + " and " + num + " is " +quot+".");

          scan.close();
     }
}