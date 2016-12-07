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
    objRectangle(page p,Color c,float s,int id){
        super(p,c,s,id);
    }

    @Override
    public void paintObj(Graphics g) {
        //System.out.println(180-Angle);
        Graphics2D g2 = (Graphics2D)g;
        g2.rotate(Math.toRadians(Angle), this.getWidth()/2, this.getHeight()/2);
        g2.setColor(Color.WHITE);
        g2.fillRect(this.getWidth()/2-w/2+(int)PenSize/2, this.getHeight()/2-h/2+(int)PenSize/2,
                w-(int)PenSize,h-(int)PenSize);
        if(Line==LineEnum.Solid){
            g2.setStroke(new BasicStroke(PenSize, CAP_ROUND, JOIN_ROUND));
        }else if(Line==LineEnum.Dotted){
            g2.setStroke(new BasicStroke(PenSize, CAP_ROUND, JOIN_ROUND,
                     0, new float[]{16, 8}, 0));
        }
        g2.setColor(this.PenColor);
        g2.drawRect(this.getWidth()/2-w/2+(int)PenSize/2, this.getHeight()/2-h/2+(int)PenSize/2,
                w-(int)PenSize,h-(int)PenSize);
        if(str!=null){
            g2.setFont(new programFont());
            g2.drawString(str,this.getWidth()/2-str.length()*6,this.getHeight()/2+7);
        }
    }
}
