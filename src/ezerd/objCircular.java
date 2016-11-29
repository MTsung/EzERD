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
public class objCircular extends obj{
    objCircular(){
        super();
    }
    objCircular(page p,Color c,float s,int id){
        super(p,c,s,id);
    }
    @Override
    public void paintObj(Graphics g) {
        Graphics2D g2 = (Graphics2D)g;
        //g2.rotate(Math.toRadians(50),this.getWidth() /2, this.getHeight()/2);
        
        g2.setColor(Color.WHITE);
        g2.fillOval((int)PenSize/2, (int)PenSize/2, this.getWidth()-(int)PenSize,this.getHeight()-(int)PenSize);
        
        g2.setStroke(new BasicStroke(PenSize,CAP_ROUND,JOIN_ROUND));
        g2.setColor(this.PenColor);
        g2.drawOval((int)PenSize/2, (int)PenSize/2, this.getWidth()-(int)PenSize,this.getHeight()-(int)PenSize);
        
   }
}
