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
/**
 *
 * @author CMC
 */
public class objAttributesPanel extends Panel{
    attributesToolBar AtoolBar;
    TextField[] TextSize=new TextField[2];
    TextField[] TextLocation=new TextField[2];
    TextField TextTra;
    Panel TextPanel,TempPanel1,TempPanel2,TempPanel3;
    Label LabelW,LabelH,LabelTra;
    objAttributesPanel(attributesToolBar p){
        super();
        AtoolBar=p;
        TextPanel=new Panel();
        TextPanel.setPreferredSize(new Dimension(270,140));
        TempPanel1=new Panel();
        TempPanel2=new Panel();
        TempPanel3=new Panel();
        LabelW=new Label("Width:");
        LabelH=new Label("Height:");
        LabelTra=new Label("Transparency:");
        LabelW.setFont(new programFont());
        LabelH.setFont(new programFont());
        LabelTra.setFont(new programFont());
        TextSize[0]=new TextField("0",3);
        TempPanel1.add(LabelW);
        TempPanel1.add(TextSize[0]);
        TextSize[0].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AtoolBar.parent.WorkSpace.activePage.ArrowPaint=true;
                if(AtoolBar.parent.WorkSpace.activePage.activeObj!=null){
                    try {
                        AtoolBar.parent.WorkSpace.activePage.activeObj.setSize(Integer.valueOf(TextSize[0].getText()),
                                 Integer.valueOf(TextSize[1].getText()));
                    } catch (Exception ex) {
                        TextSize[0].setText("" + AtoolBar.parent.WorkSpace.activePage.activeObj.getWidth());
                        TextSize[1].setText("" + AtoolBar.parent.WorkSpace.activePage.activeObj.getHeight());
                    }
                }else{
                    TextSize[0].setText("0");
                    TextSize[1].setText("0");
                    TextLocation[0].setText("0");
                    TextLocation[1].setText("0");
                }
            }
        });
        TextSize[1]=new TextField("0",3);
        TempPanel1.add(LabelH);
        TempPanel1.add(TextSize[1]);
        TextSize[1].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AtoolBar.parent.WorkSpace.activePage.ArrowPaint=true;
                if(AtoolBar.parent.WorkSpace.activePage.activeObj!=null){
                    try {
                        AtoolBar.parent.WorkSpace.activePage.activeObj.setSize(Integer.valueOf(TextSize[0].getText()),
                                 Integer.valueOf(TextSize[1].getText()));
                    } catch (Exception ex) {
                        TextSize[0].setText("" + AtoolBar.parent.WorkSpace.activePage.activeObj.getWidth());
                        TextSize[1].setText("" + AtoolBar.parent.WorkSpace.activePage.activeObj.getHeight());
                    }
                }else{
                    TextSize[0].setText("0");
                    TextSize[1].setText("0");
                    TextLocation[0].setText("0");
                    TextLocation[1].setText("0");
                }
            }
        });
        Label LabelX=new Label("X       :");
        Label LabelY=new Label("Y        :");
        LabelX.setFont(new programFont());
        LabelY.setFont(new programFont());
        TextLocation[0]=new TextField("0",3);
        TempPanel2.add(LabelX);
        TempPanel2.add(TextLocation[0]);
        TextLocation[0].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AtoolBar.parent.WorkSpace.activePage.ArrowPaint=true;
                if(AtoolBar.parent.WorkSpace.activePage.activeObj!=null){
                    try {
                        AtoolBar.parent.WorkSpace.activePage.activeObj.setLocation(Integer.valueOf(TextLocation[0].getText()),
                                 Integer.valueOf(TextLocation[1].getText()));
                    } catch (Exception ex) {
                        TextLocation[0].setText("" + AtoolBar.parent.WorkSpace.activePage.activeObj.getX());
                        TextLocation[1].setText("" + AtoolBar.parent.WorkSpace.activePage.activeObj.getY());
                    }
                }else{
                    TextSize[0].setText("0");
                    TextSize[1].setText("0");
                    TextLocation[0].setText("0");
                    TextLocation[1].setText("0");
                }
            }
        });
        TextLocation[1]=new TextField("0",3);
        TempPanel2.add(LabelY);
        TempPanel2.add(TextLocation[1]);
        TextLocation[1].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AtoolBar.parent.WorkSpace.activePage.ArrowPaint=true;
                if(AtoolBar.parent.WorkSpace.activePage.activeObj!=null){
                    try {
                        AtoolBar.parent.WorkSpace.activePage.activeObj.setLocation(Integer.valueOf(TextLocation[0].getText()),
                                 Integer.valueOf(TextLocation[1].getText()));
                    } catch (Exception ex) {
                        TextLocation[0].setText("" + AtoolBar.parent.WorkSpace.activePage.activeObj.getX());
                        TextLocation[1].setText("" + AtoolBar.parent.WorkSpace.activePage.activeObj.getY());
                    }
                }else{
                    TextSize[0].setText("0");
                    TextSize[1].setText("0");
                    TextLocation[0].setText("0");
                    TextLocation[1].setText("0");
                }
            }
        });
        TextTra=new TextField("100",3); 
        TextTra.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AtoolBar.parent.WorkSpace.activePage.ArrowPaint=true;
                if(AtoolBar.parent.WorkSpace.activePage.activeObj!=null){
                    try {
                        if(Integer.valueOf(TextTra.getText())>100)
                            TextTra.setText(""+100);
                        if(Integer.valueOf(TextTra.getText())<0)
                            TextTra.setText(""+0);
                        AtoolBar.parent.WorkSpace.activePage.activeObj.setTra(Integer.valueOf(TextTra.getText()));
                    } catch (Exception ex) {
                        TextTra.setText("" + AtoolBar.parent.WorkSpace.activePage.activeObj.getTra());
                    }
                }else{
                    TextTra.setText("100");
                }
            }
        });
        TempPanel3.add(LabelTra);
        TempPanel3.add(TextTra);
        TextPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        TextPanel.add(TempPanel1);
        TextPanel.add(TempPanel2);
        TextPanel.add(TempPanel3);
        this.add(TextPanel);
    }
    void setTextLocation(int x,int y){
        TextLocation[0].setText(""+x);
        TextLocation[1].setText(""+y);
    }
    void setTextSize(int w,int h){
        TextSize[0].setText(""+w);
        TextSize[1].setText(""+h);
    }
    void setTextTra(int t){
        TextTra.setText(""+t);
    }
}
