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
    TextField TextAngle;
    Panel TextPanel,SizePanel,LocationPanel,TarPanel,AnglePanel,LineColorPanel,TextColorPanel,BGColorPanel;
    Panel LineColorBtn,TextColorBtn,BGColorBtn;
    Label LabelW,LabelH,LabelX,LabelY,LabelTra,LabelAngle,LabelLineColor,LabelTextColor,LabelBGColor;
    objAttributesPanel(attributesToolBar p){
        super();
        AtoolBar=p;
        TextPanel=new Panel();
        TextPanel.setPreferredSize(new Dimension(270,330));
        SizePanel=new Panel();
        LocationPanel=new Panel();
        TarPanel=new Panel();
        AnglePanel=new Panel();
        LineColorPanel=new Panel();
        TextColorPanel=new Panel();
        BGColorPanel=new Panel();
        LineColorBtn=new Panel();
        TextColorBtn=new Panel();
        BGColorBtn=new Panel();
        LineColorBtn.setPreferredSize(new Dimension(60,30));
        LineColorBtn.setBackground(Color.BLACK);
        TextColorBtn.setPreferredSize(new Dimension(60,30));
        TextColorBtn.setBackground(Color.WHITE);
        BGColorBtn.setPreferredSize(new Dimension(60,30));
        BGColorBtn.setBackground(Color.BLACK);
        
        
        LabelW=new Label("Width:");
        LabelH=new Label("Height:");
        LabelX=new Label("X       :");
        LabelY=new Label("Y        :");
        LabelTra=new Label("Transparency:");
        LabelAngle=new Label("Angle:");
        LabelLineColor=new Label("Line Color             :");
        LabelTextColor=new Label("Text Color             :");
        LabelBGColor=new Label("Background Color  :");
        
        LabelW.setFont(new programFont());
        LabelH.setFont(new programFont());
        LabelX.setFont(new programFont());
        LabelY.setFont(new programFont());
        LabelTra.setFont(new programFont());
        LabelAngle.setFont(new programFont());
        LabelLineColor.setFont(new programFont());
        LabelTextColor.setFont(new programFont());
        LabelBGColor.setFont(new programFont());
        
        
        TextSize[0]=new TextField("0",3);
        SizePanel.add(LabelW);
        SizePanel.add(TextSize[0]);
        TextSize[0].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AtoolBar.parent.WorkSpace.activePage.ArrowPaint=true;
                if(AtoolBar.parent.WorkSpace.activePage.activeObj!=null){
                    try {
                        AtoolBar.parent.WorkSpace.activePage.activeObj.setObjSize(Integer.valueOf(TextSize[0].getText()),
                                 Integer.valueOf(TextSize[1].getText()),true);
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
        SizePanel.add(LabelH);
        SizePanel.add(TextSize[1]);
        TextSize[1].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AtoolBar.parent.WorkSpace.activePage.ArrowPaint=true;
                if(AtoolBar.parent.WorkSpace.activePage.activeObj!=null){
                    try {
                        AtoolBar.parent.WorkSpace.activePage.activeObj.setObjSize(Integer.valueOf(TextSize[0].getText()),
                                 Integer.valueOf(TextSize[1].getText()),true);
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
        
        
        TextLocation[0]=new TextField("0",3);
        LocationPanel.add(LabelX);
        LocationPanel.add(TextLocation[0]);
        TextLocation[0].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AtoolBar.parent.WorkSpace.activePage.ArrowPaint=true;
                if(AtoolBar.parent.WorkSpace.activePage.activeObj!=null){
                    try {
                        AtoolBar.parent.WorkSpace.activePage.activeObj.setObjLocation(Integer.valueOf(TextLocation[0].getText()),
                                 Integer.valueOf(TextLocation[1].getText()),true);
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
        LocationPanel.add(LabelY);
        LocationPanel.add(TextLocation[1]);
        TextLocation[1].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AtoolBar.parent.WorkSpace.activePage.ArrowPaint=true;
                if(AtoolBar.parent.WorkSpace.activePage.activeObj!=null){
                    try {
                        AtoolBar.parent.WorkSpace.activePage.activeObj.setObjLocation(Integer.valueOf(TextLocation[0].getText()),
                                 Integer.valueOf(TextLocation[1].getText()),true);
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
        TextAngle=new TextField("0",3); 
        TextAngle.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            }
        });
        LineColorBtn.addMouseListener(new MouseAdapter(){
            @Override                   
            public void mouseReleased(MouseEvent e){
                AtoolBar.AttributesBox.ColorWin.Show();
                AtoolBar.AttributesBox.ColorWin.setColor("Line");
            }
        });
        TextColorBtn.addMouseListener(new MouseAdapter(){
            @Override                   
            public void mouseReleased(MouseEvent e){
                AtoolBar.AttributesBox.ColorWin.Show();
                AtoolBar.AttributesBox.ColorWin.setColor("Text");
            }
        });
        BGColorBtn.addMouseListener(new MouseAdapter(){
            @Override                   
            public void mouseReleased(MouseEvent e){
                AtoolBar.AttributesBox.ColorWin.Show();
                AtoolBar.AttributesBox.ColorWin.setColor("BG");
            }
        });
        AnglePanel.add(LabelAngle);
        LineColorPanel.add(LabelLineColor);
        LineColorPanel.add(LineColorBtn);
        TextColorPanel.add(LabelTextColor);
        TextColorPanel.add(TextColorBtn);
        BGColorPanel.add(LabelBGColor);
        BGColorPanel.add(BGColorBtn);
        
        TarPanel.add(LabelTra);
        TarPanel.add(TextTra);
        TextPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        TextPanel.add(SizePanel);
        TextPanel.add(LocationPanel);
        TextPanel.add(TarPanel);
        TextPanel.add(AnglePanel);
        TextPanel.add(TextAngle);
        TextPanel.add(LineColorPanel);
        TextPanel.add(TextColorPanel);
        TextPanel.add(BGColorPanel);
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
    void setTextColor(Color c){
        TextColorBtn.setBackground(c);
    }
    void setLineColor(Color c){
        LineColorBtn.setBackground(c);
    }
    void setBGColor(Color c){
        BGColorBtn.setBackground(c);
    }
}
