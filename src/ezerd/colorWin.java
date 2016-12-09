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
     enum ColorEnum{
        Line,Text,BG
    } 
    ColorEnum colorEnum;
    attributesToolBar AttributesToolBar;    
    colorBox ColorBox;
    colorTextPanel ColorTextPanel;
    JButton OkBtn,CancelBtn;
    
    colorWin(attributesToolBar p,Color c){
        super();
        AttributesToolBar=p;
        ColorBox=new colorBox(this,360);
        ColorTextPanel= new colorTextPanel(AttributesToolBar);
        this.setLayout(new BorderLayout());
        this.setTitle("Color Chooser");
        this.setBackground(new Color(224,234,224));
        this.setSize(420, 600);
        this.addWindowListener(new WindowAdapter(){
            @Override
            public void windowClosing(WindowEvent e)
            {
                colorWin.this.setVisible(false);
            }
        });
        Panel Temp=new Panel();
        
        OkBtn=new JButton("Ok");
        OkBtn.setFont(new programFont());
        OkBtn.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                if(colorEnum==ColorEnum.Line){
                    AttributesToolBar.AttributesBox.ObjAttributesPanel.setLineColor(ColorTextPanel.getColor());
                }else if(colorEnum==ColorEnum.Text){
                    AttributesToolBar.AttributesBox.ObjAttributesPanel.setTextColor(ColorTextPanel.getColor());
                }else if(colorEnum==ColorEnum.BG){
                    AttributesToolBar.AttributesBox.ObjAttributesPanel.setBGColor(ColorTextPanel.getColor());
                }
                colorWin.this.setVisible(false);
            }
        });
        
        CancelBtn=new JButton("Cancel");
        CancelBtn.setFont(new programFont());
        CancelBtn.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                colorWin.this.setVisible(false);
            }
        });
        Temp.add(ColorBox);
        Temp.add(ColorTextPanel);
        Temp.add(OkBtn);
        Temp.add(CancelBtn);
        this.add(Temp);
        this.setVisible(false);
    }
    void Show(){
        this.setVisible(false);
        this.setLocation(AttributesToolBar.getLocationOnScreen().x-this.getWidth(),
                        AttributesToolBar.getLocationOnScreen().y);
        this.setVisible(true);
    }
    void setColor(Color c){
        ColorBox.setColor(c);
    }
    void setColor(String s){
        switch(s){
            case "Line":
                colorEnum=ColorEnum.Line;
                ColorBox.setColor(AttributesToolBar.AttributesBox.ObjAttributesPanel.LineColorBtn.getBackground());
                break;
            case "Text":
                colorEnum=ColorEnum.Text;
                ColorBox.setColor(AttributesToolBar.AttributesBox.ObjAttributesPanel.TextColorBtn.getBackground());
                break;
            case "BG":
                colorEnum=ColorEnum.BG;
                ColorBox.setColor(AttributesToolBar.AttributesBox.ObjAttributesPanel.BGColorBtn.getBackground());
        }
    }
    Color getColor(){
        return ColorTextPanel.ColorPanel.getBackground();
    }
}
