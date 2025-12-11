/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package joption;
import javax.swing.JOptionPane;
public class JOption {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        JOptionPane.showMessageDialog(null,"HELLO BABY", "Welcome Homies", JOptionPane.WARNING_MESSAGE);
        
        String name = JOptionPane.showInputDialog(null, "ENTER YOUR NAME", "HERE BROO" , JOptionPane.ERROR_MESSAGE);
        
        String age = JOptionPane.showInputDialog(null, "ENTER YOUR AGE", "HERE BROO", JOptionPane.ERROR_MESSAGE);
        
        String add = JOptionPane.showInputDialog(null, "ENTER YOUR ADDRESS", "HERE BROO", JOptionPane.ERROR_MESSAGE);
        
        String money = JOptionPane.showInputDialog(null, "ENTER YOUR MONEY", "HERE BROO", JOptionPane.ERROR_MESSAGE);
        
        JOptionPane.showMessageDialog(null, "Hi Bro, My name is " + name + ". " + "\n" 
                + "My age is " + age + " years in the World " + ". " + "\n"
                + "I'm from the Beautiful Place called " + add + " that's very nice!! " + " YEAAHH?!! " + "\n" 
                + "Penge ako " + money + " pesos" + " PLEASEEE!! "  + "\n" + "MWUAHHHH!!" ,  "RESULT!!", JOptionPane.PLAIN_MESSAGE);
    }
    
}
