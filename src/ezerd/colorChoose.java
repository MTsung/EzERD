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
    int C;
    Image bufferImage;
    Graphics bufferGraphics;
    colorChoose(attributesToolBar p) {
        super();
        AtoolBat=p;
        this.addMouseMotionListener(new MouseAdapter(){
            public void mouseDragged(MouseEvent e){
                
            }
            public void mouseMoved(MouseEvent e) {
                    
            }
        });
        this.addMouseListener(new MouseAdapter(){
            public void mouseReleased(MouseEvent e){
                BufferedImage bufImg = (BufferedImage) bufferImage;
                System.out.println(bufImg.getRGB(e.getPoint().x, e.getPoint().y));
                C=bufImg.getRGB(e.getPoint().x, e.getPoint().y);
            }
        });
    }
    int getColor(){
        return C;
    } 
    public void update(Graphics g) {
        paint(g);
    }
    public void paint(Graphics g){
        
        bufferImage = createImage(300, 300);
        bufferGraphics = bufferImage.getGraphics();
        
        int nnn=250;
        for(int i=0;i<nnn;i++){
            for(int j=0;j<nnn;j++){
                int a=Color.HSBtoRGB((float)AtoolBat.slider1.getValue()/255,(float)i/nnn ,(float)j/nnn );
                bufferGraphics.setColor(new Color(a));
                bufferGraphics.drawLine(j, i+25, j, i+25);
                
            }
        }
        for(int i=0;i<20;i++){
            for(int j=0;j<250;j++){
                int a=Color.HSBtoRGB((float)j/250, 1, 1);
                bufferGraphics.setColor(new Color(a));
                bufferGraphics.drawLine(j, i, j, i);
            }
        }
        g.drawImage(bufferImage, 0, 0, this);
        
    } 
}
