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
 * @author CMC
 */
public class objRectangle extends obj{
    objRectangle(){
        super();
    }
    objRectangle(Color c,float s){
        super(c,s);
    }

    @Override
    public void paintObj(Graphics g) {
        Graphics2D g2 = (Graphics2D)g;
        g2.setStroke(new BasicStroke(PenSize,CAP_ROUND,JOIN_ROUND));
        g2.setColor(this.PenColor);
        g2.drawRect((int)PenSize/2, (int)PenSize/2, this.getWidth()-(int)PenSize,this.getHeight()-(int)PenSize);
   }
}