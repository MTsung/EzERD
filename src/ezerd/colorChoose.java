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
import java.util.Vector;
import javax.swing.JColorChooser;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 *
 * @author User
 */
public class colorChoose extends Panel {
    attributesToolBar AtoolBat;
    colorChoose(attributesToolBar p) {
        super();
        AtoolBat=p;
    }
    public void paint(Graphics g){
        int nnn=250;
        for(int i=0;i<nnn;i++){
            for(int j=0;j<nnn;j++){
                int a=Color.HSBtoRGB((float)AtoolBat.slider1.getValue()/255,(float)i/nnn ,(float)j/nnn );
                g.setColor(new Color(a));
                g.drawLine(j, i+295, j, i+295);
                
            }
        }
        for(int i=0;i<20;i++){
            for(int j=0;j<250;j++){
                int a=Color.HSBtoRGB((float)j/250, 1, 1);
                g.setColor(new Color(a));
                g.drawLine(j, i+570, j, i+570);
            }
        }
    }
}
