

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ezerd;

import java.awt.*;
import static java.awt.BasicStroke.CAP_ROUND;
import static java.awt.BasicStroke.JOIN_ROUND;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

/**
 *
 * @author CMC
 */

public class ezAttributesBox extends Panel{
    ezAttributesToolBar AtoolBar;   
    ezColorWin ColorWin;
    ezPageSizePanel PageSizePanel;
    ezObjAttributesPanel ObjAttributesPanel;
    Label PageSizeLabel,ObjectLabel;
    ezAttributesBox(ezAttributesToolBar p) {
        super();
        AtoolBar=p;
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        PageSizePanel=new ezPageSizePanel(AtoolBar);
        ObjAttributesPanel=new ezObjAttributesPanel(AtoolBar);
        ColorWin=new ezColorWin(AtoolBar,Color.BLACK);
        
        PageSizeLabel=new Label("PageSize :");
        PageSizeLabel.setPreferredSize(new Dimension(360,50));
        PageSizeLabel.setBackground(Color.LIGHT_GRAY);
        PageSizeLabel.setFont(new ezFont());
        PageSizeLabel.addMouseListener(new MouseAdapter(){
            @Override                   
            public void mousePressed(MouseEvent e){
                PageSizePanel.setVisible(PageSizePanel.isVisible()?false:true);
                AtoolBar.parent.MainWin.validate();
            }
        });
        ObjectLabel=new Label("Object Attributes :");
        ObjectLabel.setPreferredSize(new Dimension(360,50));
        ObjectLabel.setBackground(Color.LIGHT_GRAY);
        ObjectLabel.setFont(new ezFont());
        ObjectLabel.addMouseListener(new MouseAdapter(){
            @Override                   
            public void mousePressed(MouseEvent e){
                ObjAttributesPanel.setVisible(ObjAttributesPanel.isVisible()?false:true);
                AtoolBar.parent.MainWin.validate();
            }
        });
        
        this.add(PageSizeLabel);
        this.add(PageSizePanel);
        this.add(ObjectLabel);
        this.add(ObjAttributesPanel);
    }
}
