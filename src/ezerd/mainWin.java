/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ezerd;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
/**
 *
 * @author CMC
 */
public class mainWin extends Frame{
    ezERD parent; 
    int mainWinWidth=1000,mainWinHeight=700;
    Dimension ScreenSize = Toolkit.getDefaultToolkit().getScreenSize(); 
    
    mainWin(ezERD p){
        super();
        parent=p;        
        this.setLayout(new BorderLayout());
        this.setTitle("EzERD");
        this.setSize(mainWinWidth, mainWinHeight);
        
        this.setLocation((ScreenSize.width-mainWinWidth)/2,(ScreenSize.height-mainWinHeight)/2); 
        
        this.addWindowListener(new WindowAdapter(){
            @Override
            public void windowClosing(WindowEvent e)
            {
                Boolean B=false;
                for(int i=0;i<parent.Ptb.Btns.size();i++)
                    if(parent.Ptb.Btns.elementAt(i).getText().endsWith("*") && parent.Ptb.BtnJ.elementAt(i))
                        B=true;
                if(B){
                    int n=JOptionPane.showConfirmDialog(null, "尚有頁面未儲存，是否關閉？","Message",2);
                        if(n==0)
                            System.exit(0);
                }else
                    System.exit(0);
            }
        });
        
        this.addKeyListener(new keyListener(parent));/**/
        
       
    }
    void addWorkSpace(workSpace p){
        this.add(p, BorderLayout.CENTER);
    }
    void addToolbar(topToolBar p){
        this.add(p, BorderLayout.NORTH);
    }
    void addMessagebar(messageBar p){
        this.add(p, BorderLayout.SOUTH);
    }
    void addList(toolBar p){
        this.add(p, BorderLayout.WEST);
    }
}
