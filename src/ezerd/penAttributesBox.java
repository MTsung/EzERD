

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
public class penAttributesBox extends Panel{
    attributesToolBar AtoolBat;   
    colorBox ColorBox;
    colorTextPanel ColorTextPanel;
    JSlider PenSizeSlider;
    Panel PenSizePanel;
    TextField PenSizeText,PageW,PageH;
    penAttributesBox(attributesToolBar p) {
        super();
        AtoolBat=p;
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        ColorBox=new colorBox(AtoolBat);
        ColorTextPanel= new colorTextPanel(this);
        PenSizePanel=new Panel();
        PenSizeSlider=new JSlider(1,40);
        PenSizeSlider.setPreferredSize(new Dimension(250,50));
        PenSizeSlider.setBackground(new Color(205,205,200));
        PenSizeSlider.setMinorTickSpacing(1);
        PenSizeSlider.setMajorTickSpacing(3);
        PenSizeSlider.setPaintTicks(true);
        PenSizeSlider.setPaintLabels(true);
        PenSizeSlider.setSnapToTicks(true);
        PenSizeSlider.setValue(8);
        PenSizeSlider.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent event) {
                AtoolBat.parent.WorkSpace.activePage.PenSize=PenSizeSlider.getValue();
                PenSizeText.setText(""+PenSizeSlider.getValue());
                AtoolBat.parent.MainWin.requestFocusInWindow();
            }
        });
        
        Label PenSizeLabel=new Label("PenSize :");
        PenSizeLabel.setFont(new programFont());
        
        PenSizeText=new TextField("8");
        PenSizeText.addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e) {
                    int n=8;
                    try{
                        if (Integer.valueOf(PenSizeText.getText()) < 1) {
                            n=1;
                            PenSizeText.setText(""+n);
                        } else if (Integer.valueOf(PenSizeText.getText()) > 40) {
                            n=40;
                            PenSizeText.setText(""+n);
                        }
                    } catch (Exception ex) {
                        PenSizeText.setText(""+n);
                    }
                    PenSizeSlider.setValue(n);
                }
            });
        
        PenSizePanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        PenSizePanel.add(PenSizeLabel);
        PenSizePanel.add(PenSizeText);
        
        PageW = new TextField();
        PageH = new TextField();
        PageW.addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e) {
                    try{
                        if (Integer.valueOf(PageW.getText()) < 1){
                            PageH.setText("1");
                        }
                        if (Integer.valueOf(PageH.getText()) < 1){
                            PageH.setText("1");
                        }
                    } catch (Exception ex) {
                            PageW.setText("2000");
                            PageH.setText("900");
                    }
                    AtoolBat.parent.WorkSpace.activePage.setPageSize(Integer.valueOf(PageW.getText()), Integer.valueOf(PageH.getText()));
                        
                }
        });
        PageH.addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e) {
                    try{
                        if (Integer.valueOf(PageW.getText()) < 1){
                            PageH.setText("1");
                        }
                        if (Integer.valueOf(PageH.getText()) < 1){
                            PageH.setText("1");
                        }
                    } catch (Exception ex) {
                            PageW.setText("2000");
                            PageH.setText("900");
                    }
                    AtoolBat.parent.WorkSpace.activePage.setPageSize(Integer.valueOf(PageW.getText()), Integer.valueOf(PageH.getText()));
                }
        });
        this.add(ColorBox);
        this.add(ColorTextPanel);
        this.add(PenSizePanel);
        this.add(PenSizeSlider);
        this.add(PageW);
        this.add(PageH);
    }
}
