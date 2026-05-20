/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snakegame;

import java.awt.Color;
import javax.swing.JFrame;

/**
 *
 * @author HP
 */
public class Main {
     
    public static void main(String[] args) {
        // main method
        Loginpage l1=new Loginpage();
        l1.setVisible(true);
        }


    void setvisible(boolean b) {
        //
  
         JFrame jf1=new JFrame();
        jf1.setTitle("Snake Game");
        jf1.setBounds(10,10 ,905 ,700 );
        jf1.setResizable(false);
        jf1.setDefaultCloseOperation(3);
        GamePanal p1=new GamePanal();
        p1.setBackground(Color.DARK_GRAY);
        jf1.add(p1);   //adding panal
        jf1.setVisible(true);
    
    }
    
    
}
