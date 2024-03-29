/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ezerd;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
/**
 *
 * @author CMC
 */
public class ezMainWin extends Frame{
    ezERD parent; 
    int mainWinWidth=1500,mainWinHeight=900;
    Dimension ScreenSize = Toolkit.getDefaultToolkit().getScreenSize(); 
    ezMenuBar MenuBar;
    String ClosingMessage,Mess;
    ezMainWin(ezERD p){
        super();
        parent=p;        
        this.setLayout(new BorderLayout());
        this.setTitle("EzERD");
        MenuBar=new ezMenuBar(parent);
        this.setMenuBar(MenuBar);
        mainWinWidth=(int) ((int) ScreenSize.getWidth()*0.8);
        mainWinHeight=(int) ((int) ScreenSize.getHeight()*0.8);
        Mess="Message";
        ClosingMessage="File is modified. Close？";
        this.setSize(mainWinWidth, mainWinHeight);
        this.setLocation((ScreenSize.width-mainWinWidth)/2,(ScreenSize.height-mainWinHeight)/2); 
        this.addWindowListener(new WindowAdapter(){
            @Override
            public void windowClosing(WindowEvent e)
            {
                Boolean B=false;
                for(int i=0;i<parent.PageToolBar.Btns.size();i++)
                    if(parent.PageToolBar.Btns.elementAt(i).getText().endsWith("*") && parent.PageToolBar.BtnJ.elementAt(i))
                        B=true;
                if(B){
                    int n=JOptionPane.showConfirmDialog(null, ClosingMessage,Mess,2,JOptionPane.PLAIN_MESSAGE);
                        if(n==0)
                            System.exit(0);
                }else
                    System.exit(0);
            }
        });
        this.addKeyListener(new ezKeyListener(parent));/**/
    }
    void addWorkSpace(ezWorkSpace p){
        this.add(p, BorderLayout.CENTER);
    }
    void addTopToolbar(ezTopToolBar p){
        this.add(p, BorderLayout.NORTH);
    }
    void addMessagebar(ezMessageBar p){
        this.add(p, BorderLayout.SOUTH);
    }
    void addToolBar(ezToolBar p){
        this.add(p, BorderLayout.WEST);
    }
    void addAttributesToolBar(ezAttributesToolBar p){
        this.add(p, BorderLayout.EAST);
    }
}
