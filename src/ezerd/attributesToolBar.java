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
public class attributesToolBar extends JPanel {
    ezERD parent;
        JSlider slider;
        colorChoose ppp=new colorChoose(this);
    public attributesToolBar(ezERD p) {
        super();
        parent = p;
        this.setPreferredSize(new Dimension(300,0));
        this.setBackground(new Color(205,205,200));
        this.setLayout(new BorderLayout());
        slider=new JSlider(1,255);
        slider.setMajorTickSpacing(5);
        slider.setPaintTicks(true);
        slider.setValue(8);
        slider.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent event) {
                parent.Ws.activePage.PanSize=slider.getValue();
                ppp.repaint();
            }
        });
        this.add(ppp,BorderLayout.CENTER);
        this.add(slider, BorderLayout.NORTH);
    }

}
