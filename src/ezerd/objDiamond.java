/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ezerd;

import java.awt.BasicStroke;
import static java.awt.BasicStroke.CAP_ROUND;
import static java.awt.BasicStroke.JOIN_ROUND;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

/**
 *
 * @author CMC
 */
public class objDiamond extends obj{
    objDiamond(){
        super();
    }
    objDiamond(page p,Color c,float s){
        super(p,c,s);
    }

    @Override
    public void paintObj(Graphics g) {
        Graphics2D g2 = (Graphics2D)g;
        g2.setStroke(new BasicStroke(PenSize,CAP_ROUND,JOIN_ROUND));
        g2.setColor(this.PenColor);
        /*g2.drawLine(this.getWidth(),this.getHeight()/2,this.getWidth()/2,0);
        g2.drawLine(0,this.getHeight()/2,this.getWidth()/2,0);
        g2.drawLine(0,this.getHeight()/2,this.getWidth()/2,this.getHeight());
        g2.drawLine(this.getWidth()/2,this.getHeight(),this.getWidth(),this.getHeight()/2);*/
        g2.drawLine(this.getWidth()-(int)PenSize/2,this.getHeight()/2,this.getWidth()/2,(int)PenSize/2);
        g2.drawLine((int)PenSize/2,this.getHeight()/2,this.getWidth()/2,(int)PenSize/2);
        g2.drawLine((int)PenSize/2,this.getHeight()/2,this.getWidth()/2,this.getHeight()-(int)PenSize/2);
        g2.drawLine(this.getWidth()/2,this.getHeight()-(int)PenSize/2,this.getWidth()-(int)PenSize/2,this.getHeight()/2);
   }
}
