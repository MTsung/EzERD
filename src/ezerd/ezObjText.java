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

public class ezObjText extends ezObj{
    
    ezObjText(ezPage p,Color c,Color c1,Color c2,float s,int id,int n,String S,Boolean V){
        super(p,c,c1,c2,s,id,n,S,V);
        ObjEnum=ObjEnum.text;
    }

    @Override
    public void paintObj(Graphics g) {
        Graphics2D g2 = (Graphics2D)g;
        
        g2.setColor(TextColor);
        if(str!=null){
            g2.setFont(new ezFont());
            int ch = 0;
            int en = 0;
            for (int i = 0; i < str.length(); i++) {
                char c = str.charAt(i);
                if ((int) c < 256) {
                    en++;
                } else {
                    ch++;
                }
            }
            g2.drawString(str,this.getWidth()/2-(ch*2+en)*5,this.getHeight()/2+7);
        }
    }
}
