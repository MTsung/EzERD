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
 * @author CMC
 */
public class colorBox extends Panel{
    attributesToolBar AtoolBat;                                                                                            
    int ColorInt;
    Image bufferImage;
    Graphics bufferGraphics;
    int X=240, Y=260,XX=246,CWidth=360;
    colorBox(attributesToolBar p) {
        super();
        AtoolBat=p;
        this.setPreferredSize(new Dimension(CWidth,CWidth+35));
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
        setColor(0,0,255);
        //setColor(0f,1f,1f);
        
        for(Component a:this.getComponents())/**/
            a.addKeyListener(new keyListener(AtoolBat.parent));/**/
        this.addKeyListener(new keyListener(AtoolBat.parent));/**/
        
    }
    int getColor(){
        return ColorInt;
    } 
    void setColor(int r,int g,int b){
        float f[]=Color.RGBtoHSB(r, g, b, null);
        XX=(int) (f[0]*CWidth)-4;        
        X=(int) (f[1]*CWidth);
        Y=(int) (f[2]*CWidth)+35;
        //System.out.println(f[0]+","+f[1]+","+f[2]);
        //System.out.println(XX+","+X+","+Y);
        this.repaint();
    }
    void setColor(float h,float s,float b){
        XX=(int) (h*CWidth/360)-4;
        X=(int) (s*CWidth/100);
        Y=(int) (b*CWidth/100)+35;
        this.repaint();
    }
    public void upPoint(MouseEvent e){
        if (e.getPoint().y >= 35) {
            if (e.getPoint().x < 0) {
                X = 0;
            } else if (e.getPoint().x > CWidth) {
                X = CWidth;
            } else {
                X = e.getPoint().x;
            }

            if (e.getPoint().y < 35) {
                Y = 35;
            } else if (e.getPoint().y > CWidth+35) {
                Y = CWidth+35;
            } else {
                Y = e.getPoint().y;
            }
        }else if(e.getPoint().y < 20){
            if (e.getPoint().x < 0) {
                XX = -4;
            }else if(e.getPoint().x > CWidth){
                XX = CWidth-4;
            }else{
                XX = e.getPoint().x-4;    
            }
        }
    }
            
    
    
    public void update(Graphics g) {
        paint(g);
    }
    public void paint(Graphics g){
        
        bufferImage = createImage(CWidth+1, CWidth+36);
        bufferGraphics = bufferImage.getGraphics();
        
        for(int i=0;i<=CWidth;i++){
            for(int j=0;j<=CWidth;j++){
                //System.out.println((float)i/nnn );
                bufferGraphics.setColor(new Color(Color.HSBtoRGB((float)(XX+4)/CWidth , (float)i/CWidth , (float)j/CWidth)));
                bufferGraphics.drawLine(i, j+35 , i , j+35);
                
            }
        }
        for (int j = 0; j <= CWidth; j++) {
            bufferGraphics.setColor(new Color(Color.HSBtoRGB((float)j/ CWidth, 1, 1)));
            bufferGraphics.drawLine(j, 0, j, 20);
        }
        bufferGraphics.setColor(Color.BLACK);
        bufferGraphics.drawRect(XX, 0, 8, 20);
        bufferGraphics.drawRect(XX+2, 2, 4, 16);
        bufferGraphics.drawOval(X - 6, Y - 6, 12, 12);
        bufferGraphics.drawOval(X - 4, Y - 4, 8, 8);
        bufferGraphics.setColor(Color.WHITE);
        bufferGraphics.drawRect(XX+1, 1, 6, 18);
        bufferGraphics.drawOval(X - 5, Y - 5, 10, 10);
        g.drawImage(bufferImage, 0, 0, this);
        BufferedImage bufImg = (BufferedImage) bufferImage;
        //System.out.println(XX+","+X+","+Y);
        ColorInt=bufImg.getRGB(X,Y);
        AtoolBat.ColorChooseBox.CTP.setColor(new Color(ColorInt));
        AtoolBat.ColorChooseBox.CTP.Trgb[0].setText(""+new Color(ColorInt).getRed());
        AtoolBat.ColorChooseBox.CTP.Trgb[1].setText(""+new Color(ColorInt).getGreen());
        AtoolBat.ColorChooseBox.CTP.Trgb[2].setText(""+new Color(ColorInt).getBlue());
        AtoolBat.ColorChooseBox.CTP.Thsb[0].setText(""+(float)(XX+4)/CWidth*360);
        AtoolBat.ColorChooseBox.CTP.Thsb[1].setText(""+(float)X/CWidth*100);
        AtoolBat.ColorChooseBox.CTP.Thsb[2].setText(""+(float)(Y-35)/CWidth*100);
        
    } 
}
