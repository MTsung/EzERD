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
import java.util.Vector;
import javax.swing.colorchooser.*;
import javax.swing.border.*;

/**
 *
 * @author CMC
 */
public class attributesToolBar extends Panel {
    ezERD parent;
    JSlider slider,slider1;
    colorChoose colorBox=new colorChoose(this);
    JColorChooser J;
    public attributesToolBar(ezERD p) {
        super();
        parent = p;
        //this.setPreferredSize(new Dimension(250,0));
        this.setBackground(new Color(205,205,200));
        this.setLayout(new BorderLayout());
        slider=new JSlider(1,50);
        slider.setMajorTickSpacing(5);
        slider.setPaintTicks(true);
        slider.setValue(8);/*
        slider1=new JSlider(1,255);
        slider1.setMajorTickSpacing(5);
        slider1.setPaintTicks(true);
        slider1.setValue(8);
        slider1.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent event) {
                colorBox.repaint();
            }
        });*/
        slider.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent event) {
                parent.Ws.activePage.PanSize=slider.getValue();
                //colorBox.repaint();
            }
        });
        J= new JColorChooser();
        J.setColor(0, 0, 0);
        
        
        this.add(J,BorderLayout.CENTER);
        this.add(slider, BorderLayout.NORTH);
        //this.add(slider1, BorderLayout.SOUTH);
    }

}
