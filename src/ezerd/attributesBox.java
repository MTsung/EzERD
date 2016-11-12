

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
    colorBox ColorBox;
    colorTextPanel ColorTextPanel;
    pageSizePanel PageSizePanel;
    objAttributesPanel ObjAttributesPanel;
    JSlider PenSizeSlider;
    Panel PenSizePanel;
    TextField PenSizeText;
    Label PenSizeLabel,PageSizeLabel,ColorChooserLabel,ObjectLabel;
    attributesBox(attributesToolBar p) {
        super();
        AtoolBar=p;
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        ColorBox=new colorBox(AtoolBar,360);
        ColorTextPanel= new colorTextPanel(AtoolBar);
        PageSizePanel=new pageSizePanel(AtoolBar);
        ObjAttributesPanel=new objAttributesPanel(AtoolBar);
        PenSizePanel=new Panel();
        
        PenSizeSlider=new JSlider(1,40);
        PenSizeSlider.setPreferredSize(new Dimension(300,50));
        PenSizeSlider.setBackground(new Color(205,205,200));
        PenSizeSlider.setMinorTickSpacing(1);
        PenSizeSlider.setMajorTickSpacing(3);
        PenSizeSlider.setPaintTicks(true);
        PenSizeSlider.setPaintLabels(true);
        PenSizeSlider.setSnapToTicks(true);
        PenSizeSlider.setValue(5);
        PenSizeSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent event) {
                AtoolBar.parent.WorkSpace.activePage.PenSize=PenSizeSlider.getValue();
                PenSizeText.setText(""+PenSizeSlider.getValue());
                if(AtoolBar.parent.WorkSpace.activePage.activeObj!=null
                        &&AtoolBar.parent.WorkSpace.activePage.PageActionEnum==pageActionEnum.idle){
                    AtoolBar.parent.WorkSpace.activePage.activeObj.PenSize=PenSizeSlider.getValue()>8?8:PenSizeSlider.getValue();
                    for (object o : AtoolBar.parent.WorkSpace.activePage.Points) {
                        if (o.ObjID == AtoolBar.parent.WorkSpace.activePage.activeObj.ID) {
                            o.PenSize = PenSizeSlider.getValue()>8?8:PenSizeSlider.getValue();
                        }
                    }
                    AtoolBar.parent.WorkSpace.activePage.repaint();
                }
                AtoolBar.parent.MainWin.requestFocusInWindow();
            }
        });
        
        
        PenSizeText=new TextField("5",3);
        PenSizeText.addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e) {
                    int n=8;
                    try{
                        if (Integer.valueOf(PenSizeText.getText()) < 1) {
                            n=1;
                        } else if (Integer.valueOf(PenSizeText.getText()) > 40) {
                            n=40;
                        }else{
                            n=Integer.valueOf(PenSizeText.getText());
                        }
                        PenSizeText.setText(""+n);
                    } catch (Exception ex) {
                        PenSizeText.setText(""+n);
                    }
                    PenSizeSlider.setValue(n);
                    AtoolBar.parent.MainWin.requestFocusInWindow();
                }
            });
        
        PenSizePanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        PenSizePanel.add(PenSizeSlider);
        PenSizePanel.add(PenSizeText);
        
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
        
        ColorChooserLabel=new Label("Color Chooser :");
        ColorChooserLabel.setPreferredSize(new Dimension(360,50));
        ColorChooserLabel.setBackground(Color.LIGHT_GRAY);
        ColorChooserLabel.setFont(new programFont());
        ColorChooserLabel.addMouseListener(new MouseAdapter(){
            @Override                   
            public void mousePressed(MouseEvent e){
                ColorBox.setVisible(ColorBox.isVisible()?false:true);
                ColorTextPanel.setVisible(ColorTextPanel.isVisible()?false:true);
                AtoolBar.parent.MainWin.validate();
            }
        });
        PenSizeLabel=new Label("PenSize :");
        PenSizeLabel.setPreferredSize(new Dimension(360,50));
        PenSizeLabel.setBackground(Color.LIGHT_GRAY);
        PenSizeLabel.setFont(new programFont());
        PenSizeLabel.addMouseListener(new MouseAdapter(){
            @Override                   
            public void mousePressed(MouseEvent e){
                PenSizePanel.setVisible(PenSizePanel.isVisible()?false:true);
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
        this.add(ColorChooserLabel);
        this.add(ColorBox);
        this.add(ColorTextPanel);
        this.add(PenSizeLabel);
        this.add(PenSizePanel);
    }
}
