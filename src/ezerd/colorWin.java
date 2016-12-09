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
public class colorWin extends Frame{
    attributesToolBar AttributesToolBar;    
    colorBox ColorBox;
    colorTextPanel ColorTextPanel;
    
    colorWin(attributesToolBar p,Color c){
        super();
        AttributesToolBar=p;
        ColorBox=new colorBox(this,360);
        ColorTextPanel= new colorTextPanel(AttributesToolBar);
        this.setLayout(new BorderLayout());
        this.setTitle("Color Chooser");
        this.setBackground(Color.LIGHT_GRAY);
        this.setSize(450, 650);
        this.setLocation(AttributesToolBar.getX()-500,AttributesToolBar.getY()+200); 
        this.addWindowListener(new WindowAdapter(){
            @Override
            public void windowClosing(WindowEvent e)
            {
                colorWin.this.setVisible(false);
            }
        });
        Panel Temp=new Panel();
        Temp.add(ColorBox);
        Temp.add(ColorTextPanel);
        this.add(Temp);
        this.setVisible(true);
    }
}
