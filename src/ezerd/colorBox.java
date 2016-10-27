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
public class colorBox extends Panel{
    attributesToolBar AtoolBat;                                                                                            
    int ColorInt;
    Image bufferImage;
    Graphics bufferGraphics;
    int X=240, Y=260,XX=0,CWidth=250;
    colorBox(attributesToolBar p) {
        super();
        AtoolBat=p;
        this.setPreferredSize(new Dimension(255,275));
        this.addMouseMotionListener(new MouseAdapter(){
            public void mouseDragged(MouseEvent e){
                upPoint(e);
                colorBox.this.repaint();
            }
            public void mouseMoved(MouseEvent e) {
                AtoolBat.parent.Mb.XY=e.getPoint();
                AtoolBat.parent.Mb.updateMessage();
            }
        });
        this.addMouseListener(new MouseAdapter(){
            public void mousePressed(MouseEvent e){
                upPoint(e);
                colorBox.this.repaint();
            }
            public void mouseReleased(MouseEvent e){
                
            }
        });
        setColor(255,0,0);
        //setColor(0.5f,1f,1f);
        
        for(Component a:this.getComponents())/**/
            a.addKeyListener(new keyListener(AtoolBat.parent));/**/
        this.addKeyListener(new keyListener(AtoolBat.parent));/**/
        
    }
    int getColor(){
        return ColorInt;
    } 
    void setColor(int r,int g,int b){
        float f[]=Color.RGBtoHSB(r, g, b, null);
        XX=(int) (f[0]*CWidth);        
        X=(int) (f[1]*CWidth);
        Y=(int) (f[2]*CWidth)+25;
        System.out.println(f[0]+","+f[1]+","+f[2]);
        this.repaint();
    }
    void setColor(float h,float s,float b){
        XX=(int) (h*CWidth);
        X=(int) (s*CWidth);
        Y=(int) (b*CWidth)+25;
        this.repaint();
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
        
        bufferImage = createImage(CWidth+1, 275+1);
        bufferGraphics = bufferImage.getGraphics();
        
        for(int i=0;i<=CWidth;i++){
            for(int j=0;j<=CWidth;j++){
                //System.out.println((float)i/nnn );
                bufferGraphics.setColor(new Color(Color.HSBtoRGB((float)(XX+4)/CWidth , (float)i/CWidth , (float)j/CWidth)));
                bufferGraphics.drawLine(i, j+25 , i , j+25);
                
            }
        }
        for (int j = 0; j <= CWidth; j++) {
            bufferGraphics.setColor(new Color(Color.HSBtoRGB((float) j / CWidth, 1, 1)));
            bufferGraphics.drawLine(j, 0, j, 20);
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
