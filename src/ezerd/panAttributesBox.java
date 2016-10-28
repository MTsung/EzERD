

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ezerd;

import java.awt.*;
import static java.awt.BasicStroke.CAP_ROUND;
import static java.awt.BasicStroke.JOIN_ROUND;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

/**
 *
 * @author CMC
 */
public class panAttributesBox extends Panel{
    attributesToolBar AtoolBat;   
    colorBox ColorBox;
    colorTextPanel ColorTextPanel;
    JSlider slider;
    Panel P;
    panAttributesBox(attributesToolBar p) {
        super();
        AtoolBat=p;
        //this.setLayout(new BorderLayout());
        ColorBox=new colorBox(AtoolBat);
        ColorTextPanel= new colorTextPanel(this);
        slider=new JSlider(1,50);
        slider.setPreferredSize(new Dimension(250,50));
        slider.setBackground(new Color(205,205,200));
        slider.setPaintTicks(true);
        slider.setValue(8);
        slider.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent event) {
                AtoolBat.parent.WorkSpace.activePage.PanSize=slider.getValue();
                P.repaint();
            }
        });
        P=new Panel(){
            public void paint(Graphics g){
                Graphics2D g2 = (Graphics2D)g;  
                //g2.setColor(new Color(ColorBox.getColor()));
                g2.setStroke(new BasicStroke(slider.getValue(),CAP_ROUND,JOIN_ROUND));
                g2.drawLine(P.getWidth()/2-15 , P.getHeight()/2 , P.getWidth()/2 , P.getHeight()/2);
            }
        };
        P.setPreferredSize(new Dimension(90,50));
        this.add(ColorBox);
        this.add(ColorTextPanel);
        this.add(P);
        this.add(slider);
        
        
    }
}
