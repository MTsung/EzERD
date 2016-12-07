/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ezerd;

/**
 *
 * @author CMC
 */
import java.awt.*;
import static java.awt.BasicStroke.CAP_ROUND;
import static java.awt.BasicStroke.JOIN_ROUND;
import java.awt.event.*;

public class objText extends obj{
    
    objText(page p,Color c,float s,int id){
        super(p,c,s,id);
        str="Text";
    }

    @Override
    public void paintObj(Graphics g) {
        Graphics2D g2 = (Graphics2D)g;
        g2.setColor(this.PenColor);
        if(str!=null){
            g2.setFont(new programFont());
            g2.drawString(str,this.getWidth()/2-str.length()*6,this.getHeight()/2+7);
        }
    }
}
