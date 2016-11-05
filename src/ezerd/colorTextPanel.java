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
public class colorTextPanel extends Panel{
    attributesToolBar AttributesToolBox;
    TextField[] TextRGB=new TextField[3];
    TextField[] TextHSB=new TextField[3];
    Panel TextPanel,ColorPanel,TempPanel1,TempPanel2;
    colorTextPanel(attributesToolBar p){
        super();
        AttributesToolBox=p;
        TextPanel=new Panel();
        TextPanel.setPreferredSize(new Dimension(270,80));
        TempPanel1=new Panel();
        TempPanel2=new Panel();
        String Str[]={"R:","G:","B:"};
        for(int i=0;i<3;i++){
            TextRGB[i]=new TextField(3);
            Label TempLabel=new Label(Str[i]);
            TempLabel.setFont(new programFont());
            TempPanel1.add(TextPanel.add(TempLabel));
            TempPanel1.add(TextRGB[i]);
            TextRGB[i].addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e) {
                    for(int j=0;j<3;j++){
                        try{
                            if(Integer.valueOf(TextRGB[j].getText())<0)
                                TextRGB[j].setText("0");
                            else if(Integer.valueOf(TextRGB[j].getText())>255)
                                TextRGB[j].setText("255");
                        }catch (Exception ex){
                            TextRGB[j].setText("0");
                        } 
                    }
                    AttributesToolBox.AttributesBox.ColorBox.setColor(Integer.valueOf(TextRGB[0].getText()), Integer.valueOf(TextRGB[1].getText())
                                                    ,Integer.valueOf(TextRGB[2].getText()));
                }
            });
        }
        String Str1[]={"H:","S:","B:"};
        for(int i=0;i<3;i++){
            TextHSB[i]=new TextField(3);
            Label TempLabel=new Label(Str1[i]);
            TempLabel.setFont(new programFont());
            TempPanel2.add(TextPanel.add(TempLabel));
            TempPanel2.add(TextHSB[i]);
            TextHSB[i].addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e) {
                    for(int j=0;j<3;j++){
                        try{
                            if(Float.valueOf(TextHSB[j].getText())<0)
                                TextHSB[j].setText("0.0");
                            else if(Float.valueOf(TextHSB[j].getText())>100 && j>0)
                                TextHSB[j].setText("100.0");
                            else if(Float.valueOf(TextHSB[j].getText())>360 && j==0)
                                TextHSB[j].setText("360.0");
                        }catch (Exception ex){
                            TextHSB[j].setText("0.0");
                        } 
                    }
                    AttributesToolBox.AttributesBox.ColorBox.setColor(Float.valueOf(TextHSB[0].getText()),Float.valueOf(TextHSB[1].getText())
                                                    ,Float.valueOf(TextHSB[2].getText()));
                }
            });
        }
        TextPanel.add(TempPanel1);
        TextPanel.add(TempPanel2);
        ColorPanel=new Panel();
        ColorPanel.setPreferredSize(new Dimension(70,50));
        this.add(ColorPanel);
        this.add(TextPanel);
        
        
        for(Component a:this.getComponents())/**/
            a.addKeyListener(new keyListener(AttributesToolBox.parent));/**/
        this.addKeyListener(new keyListener(AttributesToolBox.parent));/**/
    }
    void setColor(Color c){
        ColorPanel.setBackground(c);
    }
}
