/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ezerd;

import java.awt.*;
import java.awt.event.*;
/**
 *
 * @author CMC
 */
public class mainWin extends Frame{
    ezERD parent; 
    int mainWinWidth=1000,mainWinHeight=700;
    
    mainWin(ezERD p){
        super();
        parent=p;        
        this.setLayout(new BorderLayout());
        this.setSize(mainWinWidth, mainWinHeight);
        
        Dimension ScreenSize = Toolkit.getDefaultToolkit().getScreenSize(); 
        this.setLocation((ScreenSize.width-mainWinWidth)/2,(ScreenSize.height-mainWinHeight)/2); 
        
        this.addWindowListener(new WindowAdapter(){
            @Override
            public void windowClosing(WindowEvent e)
            {
                //int n=JOptionPane.showConfirmDialog(null, "是否關閉","Message",1);
                //if(n==0)
                   System.exit(0);
            }
        });
    }
    void addWorkSpace(workSpace p){
        this.add(p, BorderLayout.CENTER);
    }
    void addToolbar(toolBar p){
        this.add(p, BorderLayout.NORTH);
    }
    void addMessagebar(messageBar p){
        this.add(p, BorderLayout.SOUTH);
    }
    void addList(list p){
        this.add(p, BorderLayout.WEST);
    }
}
