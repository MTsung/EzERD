/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ezerd;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import javax.swing.*;

/**
 *
 * @author User
 */
public class colorChoose extends Panel{
    attributesToolBar AtoolBat;
    int ColorInt;
    Image bufferImage;
    Graphics bufferGraphics;
    int X=240, Y=260,XX=0;
    colorChoose(attributesToolBar p) {
        super();
        AtoolBat=p;
        this.addMouseMotionListener(new MouseAdapter(){
            public void mouseDragged(MouseEvent e){
                upPoint(e);
                colorChoose.this.repaint();
            }
            public void mouseMoved(MouseEvent e) {
                AtoolBat.parent.Mb.XY=e.getPoint();
                AtoolBat.parent.Mb.updateMessage();
                    
            }
        });
        this.addMouseListener(new MouseAdapter(){
            public void mousePressed(MouseEvent e){
                upPoint(e);
                colorChoose.this.repaint();
            }
            public void mouseReleased(MouseEvent e){
                
            }
        });
        
        
        for(Component a:this.getComponents())/**/
            a.addKeyListener(new keyListener(AtoolBat.parent));/**/
        this.addKeyListener(new keyListener(AtoolBat.parent));/**/
        
    }
    int getColor(){
        //System.out.println(new Color(ColorInt));
        return ColorInt;
    } 
    public void upPoint(MouseEvent e){
        if (e.getPoint().y >= 25) {
            if (e.getPoint().x < 0) {
                X = 0;
            } else if (e.getPoint().x > 250) {
                X = 250;
            } else {
                X = e.getPoint().x;
            }

            if (e.getPoint().y < 25) {
                Y = 25;
            } else if (e.getPoint().y > 275) {
                Y = 275;
            } else {
                Y = e.getPoint().y;
            }
        }else{
            if (e.getPoint().x < 0) {
                XX = -4;
            }else if(e.getPoint().x > 250){
                XX = 246;
            }else{
                XX = e.getPoint().x-4;    
            }
                
        }
        
    }
            
    
    
    public void update(Graphics g) {
        paint(g);
    }
    public void paint(Graphics g){
        
        int nnn=250;
        bufferImage = createImage(nnn+1, 275+1);
        bufferGraphics = bufferImage.getGraphics();
        
        for(int i=0;i<=nnn;i++){
            for(int j=0;j<=nnn;j++){
                //System.out.println((float)i/nnn );
                int a=Color.HSBtoRGB((float)(XX+4)/250 , (float)i/nnn , (float)j/nnn );
                bufferGraphics.setColor(new Color(a));
                bufferGraphics.drawLine(i, j+25 , i , j+25);
                
            }
        }
        for(int i=0;i<20;i++){
            for(int j=0;j<=250;j++){
                int a=Color.HSBtoRGB((float)j/250, 1, 1);
                bufferGraphics.setColor(new Color(a));
                bufferGraphics.drawLine(j, i, j, i);
            }
        }
        bufferGraphics.setColor(Color.BLACK);
        bufferGraphics.drawRect(XX, 0, 8, 19);
        bufferGraphics.drawRect(XX+2, 2, 4, 15);
        bufferGraphics.drawOval(X - 6, Y - 6, 12, 12);
        bufferGraphics.drawOval(X - 4, Y - 4, 8, 8);
        bufferGraphics.setColor(Color.WHITE);
        bufferGraphics.drawRect(XX+1, 1, 6, 17);
        bufferGraphics.drawOval(X - 5, Y - 5, 10, 10);
        g.drawImage(bufferImage, 0, 0, this);
        BufferedImage bufImg = (BufferedImage) bufferImage;
        ColorInt=bufImg.getRGB(X,Y);
        
    } 
}
