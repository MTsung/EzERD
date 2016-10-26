/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ezerd;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 *
 * @author User
 */
public class colorChoose extends Panel{
    attributesToolBar AtoolBat;
    JColorChooser colorChooser;
    Image bufferImage;
    Graphics bufferGraphics;
    colorChoose(attributesToolBar p) {
        super();
        AtoolBat=p;
        /*
        colorChooser= new JColorChooser();
        colorChooser.setColor(0, 0, 0);
        colorChooser.setPreviewPanel(new JPanel());
        
        for(AbstractColorChooserPanel ppp:colorChooser.getChooserPanels()){
            System.out.println(ppp.getDisplayName());
            if((ppp.getDisplayName().indexOf("HSV")<0)&&(ppp.getDisplayName().indexOf("RGB")<0))
                colorChooser.removeChooserPanel(ppp);
        }
        this.add(colorChooser);
        */
    }
    Color getColor(){
        return colorChooser.getColor();
    } 
    public void update(Graphics g) {
        paint(g);
    }
    public void paint(Graphics g){
        
        bufferImage = createImage(255, 255);
        bufferGraphics = bufferImage.getGraphics();
        
        int nnn=250;
        for(int i=0;i<nnn;i++){
            for(int j=0;j<nnn;j++){
                int a=Color.HSBtoRGB((float)AtoolBat.slider1.getValue()/255,(float)i/nnn ,(float)j/nnn );
                bufferGraphics.setColor(new Color(a));
                bufferGraphics.drawLine(j, i+20, j, i+20);
                
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
