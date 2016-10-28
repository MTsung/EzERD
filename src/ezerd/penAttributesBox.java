

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
public class penAttributesBox extends Panel{
    attributesToolBar AtoolBat;   
    colorBox ColorBox;
    colorTextPanel ColorTextPanel;
    JSlider PenSizeSlider;
    penAttributesBox(attributesToolBar p) {
        super();
        AtoolBat=p;
        //this.setLayout(new BorderLayout());
        ColorBox=new colorBox(AtoolBat);
        ColorTextPanel= new colorTextPanel(this);
        PenSizeSlider=new JSlider(1,25);
        PenSizeSlider.setPreferredSize(new Dimension(250,50));
        PenSizeSlider.setBackground(new Color(205,205,200));
        PenSizeSlider.setMinorTickSpacing(1);
        PenSizeSlider.setMajorTickSpacing(4);
        PenSizeSlider.setPaintTicks(true);
        PenSizeSlider.setPaintLabels(true);
        PenSizeSlider.setSnapToTicks(true);
        PenSizeSlider.setValue(8);
        PenSizeSlider.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent event) {
                AtoolBat.parent.WorkSpace.activePage.PenSize=PenSizeSlider.getValue();
            }
        });
        Label L=new Label("PenSize:");
        L.setFont(new programFont());
        this.add(ColorBox);
        this.add(ColorTextPanel);
        this.add(L);
        this.add(PenSizeSlider);
        
    }
}
