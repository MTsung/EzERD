/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ezerd;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.awt.image.BufferedImage;

/**
 *
 * @author CMC
 */
public class ezColorBox extends Panel{
    ezColorWin parent;                                                                                            
    int ColorInt;
    Image bufferImage;
    Graphics bufferGraphics;
    int X=0, Y=35,XX=-4,ColorBoxWidth=360,pressY;
    ezColorBox(ezColorWin p,int w) {
        super();
        parent=p;
        ColorBoxWidth=w;
        this.setPreferredSize(new Dimension(ColorBoxWidth,ColorBoxWidth+35));
        this.addMouseMotionListener(new MouseAdapter(){
            public void mouseDragged(MouseEvent e){
                upPoint(e);
                ezColorBox.this.repaint();
            }
            public void mouseMoved(MouseEvent e) {
            }
        });
        this.addMouseListener(new MouseAdapter(){
            public void mousePressed(MouseEvent e){
                pressY=e.getY();
                upPoint(e);
                ezColorBox.this.repaint();
            }
            public void mouseReleased(MouseEvent e){
            }
        });
        for(Component a:this.getComponents())/**/
            a.addKeyListener(new ezKeyListener(parent.AttributesToolBar.parent));/**/
        this.addKeyListener(new ezKeyListener(parent.AttributesToolBar.parent));/**/
        
    }
    int getColorInt(){
        return ColorInt;
    } 
    Color getColor(){
        return new Color(ColorInt);
    }
    int R,G,B;
    Boolean RGBJ=false;
    void setColor(Color c){
        setColor(c.getRed(),c.getGreen(),c.getBlue());
    }
    void setColor(int r,int g,int b){
        R=r;G=g;B=b;RGBJ=true;
        float f[]=Color.RGBtoHSB(r, g, b, null);
        XX=(int) (f[0]*ColorBoxWidth)-4;        
        X=(int) (f[1]*ColorBoxWidth);
        Y=(int) (f[2]*ColorBoxWidth)+35;
        this.repaint();
    }
    void setColor(float h,float s,float b){
        XX=(int) (h*ColorBoxWidth/360)-4;
        X=(int) (s*ColorBoxWidth/100);
        Y=(int) (b*ColorBoxWidth/100)+35;
        this.repaint();
    }
    public void upPoint(MouseEvent e){
        if ((e.getPoint().y >= 35 && pressY>35)||(e.getPoint().y < 35 && pressY > 35)) {
            if (e.getPoint().x < 0) {
                X = 0;
            } else if (e.getPoint().x > ColorBoxWidth) {
                X = ColorBoxWidth;
            } else {
                X = e.getPoint().x;
            }

            if (e.getPoint().y < 35) {
                Y = 35;
            } else if (e.getPoint().y > ColorBoxWidth+35) {
                Y = ColorBoxWidth+35;
            } else {
                Y = e.getPoint().y;
            }
        }else if((e.getPoint().y < 21 && pressY < 21)||(e.getPoint().y > 21 && pressY < 21)){
            if (e.getPoint().x < 0) {
                XX = -4;
            }else if(e.getPoint().x > ColorBoxWidth){
                XX = ColorBoxWidth-4;
            }else{
                XX = e.getPoint().x-4;    
            }
        }
    }
            
    
    
    public void update(Graphics g) {
        paint(g);
    }
    public void paint(Graphics g){
        bufferImage = createImage(ColorBoxWidth+1, ColorBoxWidth+36);
        bufferGraphics = bufferImage.getGraphics();
        for(int i=0;i<=ColorBoxWidth;i++){
            for(int j=0;j<=ColorBoxWidth;j++){
                bufferGraphics.setColor(new Color(Color.HSBtoRGB((float)(XX+4)/ColorBoxWidth , (float)i/ColorBoxWidth , (float)j/ColorBoxWidth)));
                bufferGraphics.drawLine(i, j+35 , i , j+35);
            }
        }
        for (int j = 0; j <= ColorBoxWidth; j++) {
            bufferGraphics.setColor(new Color(Color.HSBtoRGB((float)j/ ColorBoxWidth, 1, 1)));
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
        if(RGBJ){//RGB轉HSB會有誤差 輸入RGB時要用RGB
            bufferGraphics.setColor(new Color(R,G,B));
            bufferGraphics.drawLine(X, Y, X, Y);
            RGBJ=false;
        }
        g.drawImage(bufferImage, 0, 0, this);
        BufferedImage bufImg = (BufferedImage) bufferImage;
        ColorInt=bufImg.getRGB(X,Y);
        parent.ColorTextPanel.setColor(new Color(ColorInt));
        parent.ColorTextPanel.TextRGB[0].setText(""+new Color(ColorInt).getRed());
        parent.ColorTextPanel.TextRGB[1].setText(""+new Color(ColorInt).getGreen());
        parent.ColorTextPanel.TextRGB[2].setText(""+new Color(ColorInt).getBlue());
        parent.ColorTextPanel.TextHSB[0].setText(""+(float)(XX+4)/ColorBoxWidth*360);
        parent.ColorTextPanel.TextHSB[1].setText(""+(float)X/ColorBoxWidth*100);
        parent.ColorTextPanel.TextHSB[2].setText(""+(float)(Y-35)/ColorBoxWidth*100);
        parent.AttributesToolBar.parent.MainWin.requestFocusInWindow();
    } 
}
