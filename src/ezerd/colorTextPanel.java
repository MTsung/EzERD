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
    panAttributesBox PanAttributesBox;
    TextField[] TextRGB=new TextField[3];
    TextField[] TextHSB=new TextField[3];
    Panel TextPanel,ColorPanel;
    colorTextPanel(panAttributesBox c){
        super();
        PanAttributesBox=c;
        TextPanel=new Panel();
        TextPanel.setPreferredSize(new Dimension(270,60));
        for(int i=0;i<3;i++){
            TextRGB[i]=new TextField("255");
            switch (i) {
                case 0:
                    TextPanel.add(new Label("R:"));
                    break;
                case 1:
                    TextPanel.add(new Label("G:"));
                    break;
                case 2:
                    TextPanel.add(new Label("B:"));
            }
            TextPanel.add(TextRGB[i]);
            TextRGB[i].addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e) {
                    PanAttributesBox.ColorBox.setColor(Integer.valueOf(TextRGB[0].getText()), Integer.valueOf(TextRGB[1].getText())
                                    ,Integer.valueOf(TextRGB[2].getText()));
                }
            });
        }
        for(int i=0;i<3;i++){
            TextHSB[i]=new TextField("255");
            switch (i) {
                case 0:
                    TextPanel.add(new Label("H:"));
                    break;
                case 1:
                    TextPanel.add(new Label("S:"));
                    break;
                case 2:
                    TextPanel.add(new Label("B:"));
            }
            TextPanel.add(TextHSB[i]);
            TextHSB[i].addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e) {
                    PanAttributesBox.ColorBox.setColor(Float.valueOf(TextHSB[0].getText()),Float.valueOf(TextHSB[1].getText())
                                    ,Float.valueOf(TextHSB[2].getText()));
                }
            });
        }
        ColorPanel=new Panel();
        ColorPanel.setPreferredSize(new Dimension(80,50));
        this.add(ColorPanel);
        this.add(TextPanel);
    }
    void setColor(Color c){
        ColorPanel.setBackground(c);
    }
}
