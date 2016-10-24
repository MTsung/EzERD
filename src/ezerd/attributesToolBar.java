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
    public attributesToolBar(ezERD p) {
        super();
        parent = p;
        //this.setPreferredSize(new Dimension(50,0));
        this.setLayout(new BorderLayout());
        JSlider slider=new JSlider(1,50);
        slider.setMajorTickSpacing(5);
        slider.setPaintTicks(true);
        slider.setValue(8);
        slider.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent event) {
                parent.Ws.activePage.PanSize=slider.getValue();
            }
        });
        this.add(slider, BorderLayout.NORTH);
    }

}
