/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ezerd;

import java.awt.*;
import static java.awt.BasicStroke.CAP_ROUND;
import static java.awt.BasicStroke.JOIN_ROUND;

/**
 *
 * @author User
 */
public class colorPanel extends Panel{
    colorPanel(){
        Graphics2D g1 = (Graphics2D)g;
        g1.setStroke(new BasicStroke(1, CAP_ROUND, JOIN_ROUND));
        for(int i=0;i<10;i++){
            for(int j=0;j<300;j++){
                int a=Color.HSBtoRGB((float)j/300, 1, 1);
                g1.setColor(new Color(a));
                g1.drawLine(j, i, j, i);
                
            }
        }
        int nnn=100;
        for(int i=0;i<nnn;i++){
            for(int j=0;j<nnn;j++){
                int a=Color.HSBtoRGB((float)pp.slider.getValue()/255,(float)i/nnn ,(float)j/nnn );
                g1.setColor(new Color(a));
                g1.drawLine(j, i+15, j, i+15);
                
            }
        }
    }
}
