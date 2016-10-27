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
    colorChooseBox ColorChooseBox;
    Panel P;
    public attributesToolBar(ezERD p) {
        super();
        parent = p;
        this.setPreferredSize(new Dimension(380,0));
        this.setBackground(new Color(205,205,200));
        this.setLayout(new BorderLayout());
        slider=new JSlider(1,50);
        slider.setMajorTickSpacing(5);
        slider.setPaintTicks(true);
        slider.setValue(8);
        slider.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent event) {
                parent.Ws.activePage.PanSize=slider.getValue();
                //colorBox.repaint();
            }
        });
        ColorChooseBox =new colorChooseBox(this);
        this.add(ColorChooseBox,BorderLayout.CENTER);
        this.add(slider, BorderLayout.SOUTH);
        P=new Panel();
        P.setBackground(Color.LIGHT_GRAY);
        P.setPreferredSize(new Dimension(10,0));
        P.addMouseListener(new MouseAdapter(){
            @Override                   
            public void mousePressed(MouseEvent e){
                System.out.println("mousePressed");
                if(attributesToolBar.this.getWidth()<20)
                    attributesToolBar.this.setPreferredSize(new Dimension(380,0));
                else
                    attributesToolBar.this.setPreferredSize(new Dimension(10,0));
                parent.Win.validate();
                
            }
        });
        this.add(P,BorderLayout.WEST);
        
        for(Component a:this.getComponents())/**/
            a.addKeyListener(new keyListener(parent));/**/
        this.addKeyListener(new keyListener(parent));/**/
    }

}
