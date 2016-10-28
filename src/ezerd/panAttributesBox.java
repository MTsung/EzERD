

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
public class panAttributesBox extends Panel{
    attributesToolBar AtoolBat;   
    colorBox ColorBox;
    colorTextPanel ColorTextPanel;
    JSlider slider;
    panAttributesBox(attributesToolBar p) {
        super();
        AtoolBat=p;
        //this.setLayout(new BorderLayout());
        ColorBox=new colorBox(AtoolBat);
        ColorTextPanel= new colorTextPanel(this);
        slider=new JSlider(1,50);
        slider.setMajorTickSpacing(5);
        slider.setPaintTicks(true);
        slider.setValue(8);
        slider.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent event) {
                AtoolBat.parent.WorkSpace.activePage.PanSize=slider.getValue();
            }
        });
        this.add(ColorBox);
        this.add(ColorTextPanel);
        this.add(slider);
        
        
    }
}
