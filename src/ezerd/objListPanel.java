/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ezerd;

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import javax.swing.*;
import javax.swing.event.*;

/**
 *
 * @author CMC
 */
public class objListPanel extends Panel{
    objList ObjList;
    int ObjID;
    Label ObjIDLabel,ObjHorDLabel;
    String HideS,DisplayS;
    objListPanel(objList p,int ID){
        super();
        ObjList=p;
        ObjID=ID;
        HideS="hide";
        DisplayS="display";
        this.setPreferredSize(new Dimension(360,50));
        this.setBackground(Color.LIGHT_GRAY);
        ObjIDLabel=new Label(""+ObjID);
        ObjIDLabel.setPreferredSize(new Dimension(50,20));
        ObjIDLabel.setFont(new programFont());
        ObjHorDLabel=new Label(DisplayS);
        ObjHorDLabel.setFont(new programFont());
        ObjHorDLabel.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e) {
                String s=ObjHorDLabel.getText();
                if(HideS.equals(s)) {
                    ObjHorDLabel.setText(DisplayS);
                    for(obj o:ObjList.AtoolBar.parent.WorkSpace.activePage.Objs){
                        if(o.ID==ObjID){
                            o.setVisible(true);
                        }

                    }
                }else if(DisplayS.equals(s)){
                    ObjHorDLabel.setText(HideS);
                    for(obj o:ObjList.AtoolBar.parent.WorkSpace.activePage.Objs){
                        if(o.ID==ObjID){
                            o.setVisible(false);
                        }
                    }
                }
            }
        });
        this.add(ObjIDLabel);
        this.add(ObjHorDLabel);
    }
    public void paint(Graphics g) {
        for (obj o : ObjList.AtoolBar.parent.WorkSpace.activePage.Objs) {     
            if(o.ID==ObjID){
                int w=o.getWidth(),h=o.getHeight();
                float x=(float)w/100>(float)h/50?(float)w/100:(float)h/50;
                Graphics2D g2 = (Graphics2D)g;
                BufferedImage img = new BufferedImage((int)(100*x), (int)(50*x), BufferedImage.TYPE_INT_RGB);
                Graphics2D imgG = (Graphics2D)img.getGraphics();      
                imgG.setColor(this.getBackground());  
                imgG.fillRect(0, 0, img.getWidth(), img.getHeight());    
                o.paintObj(img.getGraphics());
                g2.drawImage(img,AffineTransform.getScaleInstance(1/x, 1/x), this);
            }
        }
    }
    
}
