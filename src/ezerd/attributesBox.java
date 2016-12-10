

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

public class attributesBox extends Panel{
    attributesToolBar AtoolBar;   
    colorWin ColorWin;
    pageSizePanel PageSizePanel;
    objAttributesPanel ObjAttributesPanel;
    Label PageSizeLabel,ObjectLabel;
    attributesBox(attributesToolBar p) {
        super();
        AtoolBar=p;
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        PageSizePanel=new pageSizePanel(AtoolBar);
        ObjAttributesPanel=new objAttributesPanel(AtoolBar);
        ColorWin=new colorWin(AtoolBar,Color.BLACK);
        
        PageSizeLabel=new Label("PageSize :");
        PageSizeLabel.setPreferredSize(new Dimension(360,50));
        PageSizeLabel.setBackground(Color.LIGHT_GRAY);
        PageSizeLabel.setFont(new programFont());
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
        ObjectLabel.setFont(new programFont());
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
