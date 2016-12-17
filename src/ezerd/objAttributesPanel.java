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
    TextField TextTra,TextAngle,PenSizeText;
    Choice choice;
    Panel TextPanel,SizePanel,LocationPanel,TarPanel,AnglePanel,LineColorPanel,TextColorPanel,BGColorPanel;
    Panel LineColorBtn,TextColorBtn,BGColorBtn;
    Label LabelW,LabelH,LabelX,LabelY,LabelTra,LabelAngle,LabelLineColor,LabelTextColor,LabelBGColor,LabelLine,Labelsize;
    objAttributesPanel(attributesToolBar p){
        super();
        AtoolBar=p;
        this.setLayout(new FlowLayout(FlowLayout.LEFT));
        TextPanel=new Panel();
        TextPanel.setPreferredSize(new Dimension(270,420));
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
        
        LineColorBtn.setPreferredSize(new Dimension(45,30));
        LineColorBtn.setBackground(Color.BLACK);
        LineColorBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        BGColorBtn.setPreferredSize(new Dimension(45,30));
        BGColorBtn.setBackground(Color.WHITE);
        BGColorBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        TextColorBtn.setPreferredSize(new Dimension(45,30));
        TextColorBtn.setBackground(Color.BLACK);
        TextColorBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        
        LabelW=new Label("Width:");
        LabelH=new Label("Height:");
        LabelX=new Label("X       :");
        LabelY=new Label("Y        :");
        LabelTra=new Label("Opacity         :");
        LabelAngle=new Label("Angle            :");
        LabelLineColor=new Label("Line Color      :");
        LabelBGColor=new Label("BG Color       :");
        LabelTextColor=new Label("Text Color      :");
        Labelsize=new Label("Line Size        :");
        LabelLine=new Label("Line Style       :");
        
        LabelW.setFont(new programFont());
        LabelH.setFont(new programFont());
        LabelX.setFont(new programFont());
        LabelY.setFont(new programFont());
        LabelTra.setFont(new programFont());
        LabelAngle.setFont(new programFont());
        LabelLineColor.setFont(new programFont());
        LabelBGColor.setFont(new programFont());
        Labelsize.setFont(new programFont());
        LabelLine.setFont(new programFont());
        LabelTextColor.setFont(new programFont());
        
        
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
                AtoolBar.parent.MainWin.requestFocusInWindow();
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
                AtoolBar.parent.MainWin.requestFocusInWindow();
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
                AtoolBar.parent.MainWin.requestFocusInWindow();
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
                AtoolBar.parent.MainWin.requestFocusInWindow();
            }
        });
        
        
        TextTra=new TextField("100",3); 
        TextTra.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AtoolBar.parent.WorkSpace.activePage.ArrowPaint=true;
                    try {
                        if(Integer.valueOf(TextTra.getText())>100)
                            TextTra.setText(""+100);
                        if(Integer.valueOf(TextTra.getText())<0)
                            TextTra.setText(""+0);
                        if (AtoolBar.parent.WorkSpace.activePage.activeObj != null) 
                            AtoolBar.parent.WorkSpace.activePage.activeObj.setTra(Integer.valueOf(TextTra.getText()), true);
                        resetColor();
                    } catch (Exception ex) {
                        if (AtoolBar.parent.WorkSpace.activePage.activeObj != null) 
                            TextTra.setText("" + AtoolBar.parent.WorkSpace.activePage.activeObj.getTra());
                        else
                            TextTra.setText("100");
                        resetColor();
                    }
                    //AtoolBar.parent.WorkSpace.activePage.activeObj.setTra(Integer.valueOf(TextTra.getText()),true);
                
                AtoolBar.parent.MainWin.requestFocusInWindow();
            }
        });
        TextAngle=new TextField("0",3); 
        TextAngle.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AtoolBar.parent.WorkSpace.activePage.ArrowPaint=true;
                if(AtoolBar.parent.WorkSpace.activePage.activeObj!=null){
                    try {
                        if(Integer.valueOf(TextAngle.getText())>359)
                            TextAngle.setText(""+0);
                        if(Integer.valueOf(TextAngle.getText())<0)
                            TextAngle.setText(""+(360+Integer.valueOf(TextAngle.getText())));
                        AtoolBar.parent.WorkSpace.activePage.activeObj.setAngle(Integer.valueOf(TextAngle.getText()),true);
                    } catch (Exception ex) {
                        TextAngle.setText("" + AtoolBar.parent.WorkSpace.activePage.activeObj.getAngle());
                    }
                }else{
                    TextAngle.setText("0");
                }
                AtoolBar.parent.MainWin.requestFocusInWindow();
            }
        });
        
        LineColorBtn.addMouseListener(new MouseAdapter(){
            @Override                   
            public void mouseReleased(MouseEvent e){
                AtoolBar.AttributesBox.ColorWin.Show();
                AtoolBar.AttributesBox.ColorWin.setColor("Line");
            }
        });
        PenSizeText=new TextField("3.0",3);
        PenSizeText.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int n = 3;
                try {
                    if (Integer.valueOf(PenSizeText.getText()) < 1) {
                        n = 1;
                    } else {
                        n = Integer.valueOf(PenSizeText.getText());
                    }
                    if (AtoolBar.parent.WorkSpace.activePage.activeObj != null) {
                        AtoolBar.parent.WorkSpace.activePage.activeObj.setPenSize(n, true);
                        AtoolBar.parent.WorkSpace.activePage.repaint();
                    }
                    PenSizeText.setText("" + n);
                } catch (Exception ex) {
                    PenSizeText.setText("" + AtoolBar.parent.WorkSpace.activePage.activeObj.PenSize);
                }
                AtoolBar.parent.MainWin.requestFocusInWindow();
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
        
        choice = new Choice();
        choice.setFont(new programFont());
        choice.add("Solid     ");
        choice.add("Dotted");
        choice.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                if(AtoolBar.parent.WorkSpace.activePage.activeObj!=null)
                    AtoolBar.parent.WorkSpace.activePage.activeObj.setLine(choice.getSelectedIndex(),true);
                AtoolBar.parent.MainWin.requestFocusInWindow();
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
        TextPanel.add(Labelsize);
        TextPanel.add(PenSizeText);
        TextPanel.add(LabelLine);
        TextPanel.add(choice);
        TextPanel.add(BGColorPanel);
        TextPanel.add(TextColorPanel);
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
    void setTextAngle(int a){
        TextAngle.setText(""+a);
    }
    void setLineColor(Color c){
        LineColorBtn.setBackground(c);
        if(AtoolBar.parent.WorkSpace.activePage.activeObj!=null){
            AtoolBar.parent.WorkSpace.activePage.activeObj.setPenColor(c,true);
        }
    }
    void setBGColor(Color c){
        BGColorBtn.setBackground(c);
        if(AtoolBar.parent.WorkSpace.activePage.activeObj!=null){
            AtoolBar.parent.WorkSpace.activePage.activeObj.setBGColor(c,true);
        }
    }
    void setTextColor(Color c){
        TextColorBtn.setBackground(c);
        if(AtoolBar.parent.WorkSpace.activePage.activeObj!=null){
            AtoolBar.parent.WorkSpace.activePage.activeObj.setTextColor(c,true);
        }
    }
    void resetColor(){
        LineColorBtn.setBackground(
                new Color(LineColorBtn.getBackground().getRed(),LineColorBtn.getBackground().getGreen()
                        ,LineColorBtn.getBackground().getBlue(),(int)(Integer.valueOf(TextTra.getText())*2.55)));
        BGColorBtn.setBackground(
                new Color(BGColorBtn.getBackground().getRed(),BGColorBtn.getBackground().getGreen()
                        ,BGColorBtn.getBackground().getBlue(),(int)(Integer.valueOf(TextTra.getText())*2.55)));
        TextColorBtn.setBackground(
                new Color(TextColorBtn.getBackground().getRed(),TextColorBtn.getBackground().getGreen()
                        ,TextColorBtn.getBackground().getBlue(),(int)(Integer.valueOf(TextTra.getText())*2.55)));
    }
    void setLineSD(int n){
        choice.select(n);
    }
    void setPenSize(float n){
        PenSizeText.setText(""+n);
    }
    void setActiveObjAtt(obj p){
        setTextLocation(p.X,p.Y);
        setTextSize(p.w, p.h);
        setTextTra(p.Tra);
        setTextAngle(p.Angle);
        setLineSD(p.getLine());
        setPenSize(p.PenSize);
        LineColorBtn.setBackground(p.PenColor);
        BGColorBtn.setBackground(p.BGColor);
        TextColorBtn.setBackground(p.TextColor);
        
    }
}
