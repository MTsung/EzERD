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
public class colorTextPanel extends Panel{
    colorChooseBox CCB;
    TextField Trgb[]=new TextField[3];
    TextField Thsb[]=new TextField[3];
    Panel textPanel,colorPanel;
    colorTextPanel(colorChooseBox c){
        super();
        CCB=c;
        textPanel=new Panel();
        textPanel.setPreferredSize(new Dimension(270,60));
        for(int i=0;i<3;i++){
            Trgb[i]=new TextField("255");
            switch (i) {
                case 0:
                    textPanel.add(new Label("R:"));
                    break;
                case 1:
                    textPanel.add(new Label("G:"));
                    break;
                case 2:
                    textPanel.add(new Label("B:"));
            }
            textPanel.add(Trgb[i]);
            Trgb[i].addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e) {
                    CCB.CB.setColor(Integer.valueOf(Trgb[0].getText()), Integer.valueOf(Trgb[1].getText())
                                    ,Integer.valueOf(Trgb[2].getText()));
                }
            });
        }
        for(int i=0;i<3;i++){
            Thsb[i]=new TextField("255");
            switch (i) {
                case 0:
                    textPanel.add(new Label("H:"));
                    break;
                case 1:
                    textPanel.add(new Label("S:"));
                    break;
                case 2:
                    textPanel.add(new Label("B:"));
            }
            textPanel.add(Thsb[i]);
            Thsb[i].addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e) {
                    CCB.CB.setColor(Float.valueOf(Thsb[0].getText()),Float.valueOf(Thsb[1].getText())
                                    ,Float.valueOf(Thsb[2].getText()));
                }
            });
        }
        colorPanel=new Panel();
        colorPanel.setPreferredSize(new Dimension(80,50));
        this.add(colorPanel);
        this.add(textPanel);
    }
    void setColor(Color c){
        colorPanel.setBackground(c);
    }
}
