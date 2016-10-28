

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
    JSlider PanSizeSlider;
    panAttributesBox(attributesToolBar p) {
        super();
        AtoolBat=p;
        //this.setLayout(new BorderLayout());
        ColorBox=new colorBox(AtoolBat);
        ColorTextPanel= new colorTextPanel(this);
        PanSizeSlider=new JSlider(1,25);
        PanSizeSlider.setPreferredSize(new Dimension(250,50));
        PanSizeSlider.setBackground(new Color(205,205,200));
        PanSizeSlider.setMinorTickSpacing(1);
        PanSizeSlider.setMajorTickSpacing(4);
        PanSizeSlider.setPaintTicks(true);
        PanSizeSlider.setPaintLabels(true);
        PanSizeSlider.setSnapToTicks(true);
        PanSizeSlider.setValue(8);
        PanSizeSlider.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent event) {
                AtoolBat.parent.WorkSpace.activePage.PanSize=PanSizeSlider.getValue();
            }
        });
        Label L=new Label("PanSize:");
        this.add(ColorBox);
        this.add(ColorTextPanel);
        this.add(L);
        this.add(PanSizeSlider);
        
    }
}
