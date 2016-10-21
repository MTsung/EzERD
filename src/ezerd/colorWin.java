/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ezerd;
import java.awt.event.*;  
import java.awt.*;  
import javax.swing.*;  

/**
 *
 * @author CMC
 */
  
public class colorWin extends Frame{  
    ezERD parent; 
    int mainWinWidth=300,mainWinHeight=350;
    
    colorWin(ezERD p){
        super();
        parent=p;        
        this.setLayout(new BorderLayout());
        this.setSize(mainWinWidth, mainWinHeight);
        this.setLocation(parent.Win.mainWinWidth+parent.Win.getX(),parent.Win.getY()); 
        
        this.addWindowListener(new WindowAdapter(){
            @Override
            public void windowClosing(WindowEvent e)
            {
                //int n=JOptionPane.showConfirmDialog(null, "是否關閉","Message",1);
                //if(n==0)
                  // System.exit(0);
                colorWin.this.setVisible(false);
            }
        });
        
    }
    
}  
