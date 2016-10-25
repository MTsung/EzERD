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

/**
 *
 * @author User
 */
public class colorChoose extends Panel {
attributesToolBar pp;
    colorChoose(attributesToolBar p) {
        super();
        pp=p;
    }
    public void paint(Graphics g) {
        Graphics2D g1 = (Graphics2D)g;
        g1.setStroke(new BasicStroke(1, CAP_ROUND, JOIN_ROUND));
        for(int i=0;i<256;i++){
            for(int j=0;j<256;j++){
                g1.setColor(new Color(j,pp.slider.getValue(),i));
                g1.drawLine(j, i, j+1, i+1);
            }
        }
    }
}
