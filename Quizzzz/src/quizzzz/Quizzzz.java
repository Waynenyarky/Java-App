/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package quizzzz;

/**
 *
 * @author John Wayne Enrique
 */
import java.util.Scanner;
public class Quizzzz {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner scan;
        scan = new Scanner(System.in);
        
       String[] subjects = {"First", "Second", "Third"};
        float[] grades = new float [3];
        
        for (int i = 0; i < 3; i++) {
            System.out.print("Enter " + subjects[i] + " Grade: ");
            grades[i] = scan.nextFloat();
        }
        
        float sum = 0;
        for (float grade : grades) {
            sum += grade;
        }
        
        float average = sum / grades.length;
        
        String result;
        if (average >= 50) {
            result = "Pass";
        } else {
            result = "Fail";
        }
        
        System.out.println("Average: " + average);
        System.out.println("Result: " + result);
    }
}