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
    objDiamond(page p,Color c,Color c1,Color c2,float s,int id,int n,String S,Boolean V){
        super(p,c,c1,c2,s,id,n,S,V);
        ObjEnum=ObjEnum.diamond;
    }

    @Override
    public void paintObj(Graphics g) {
        Graphics2D g2 = (Graphics2D)g;
        
        g2.rotate(Math.toRadians(Angle), this.getWidth()/2, this.getHeight()/2);
                                                                     
        g2.setColor(BGColor);
        int a = this.getWidth() / 2 - w / 2;
        int b = this.getHeight() / 2 - h / 2;
        int x[] = {a + w - (int) PenSize / 2 - 2, a + w / 2, a + (int) PenSize / 2, a + w / 2};
        int y[] = {b + h / 2, b + (int) PenSize / 2, b + h / 2, b + h - (int) PenSize / 2};
        g2.fillPolygon(x, y, 4);
        
        if(Line==LineEnum.Solid){
            g2.setStroke(new BasicStroke(PenSize, CAP_ROUND, JOIN_ROUND));
        }else if(Line==LineEnum.Dotted){
            g2.setStroke(new BasicStroke(PenSize, CAP_ROUND, JOIN_ROUND,
                     0, new float[]{PenSize*4, PenSize*2}, 0));
        }
        g2.setColor(PenColor);
        g2.drawPolygon(x, y, 4);
        
        g2.setColor(TextColor);
        if(str!=null){
            g2.setFont(new programFont());
            g2.drawString(str,this.getWidth()/2-str.length()*6,this.getHeight()/2+7);
        }
        
   }
}
