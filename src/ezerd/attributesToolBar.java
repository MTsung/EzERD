/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ezerd;
import java.awt.*;
import static java.awt.ScrollPane.SCROLLBARS_AS_NEEDED;
import java.awt.event.*;
import javax.swing.*;
import static javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER;
import javax.swing.event.*;

/**
 *
 * @author CMC
 */

public class attributesToolBar extends Panel {
    ezERD parent;
    Panel SizePanel,AttributesPanel,ObjPanel,TempPanel;
    ScrollPane AttributesScrollPane,ObjScrollPane;
    objList ObjList=new objList(this);
    attributesBox AttributesBox=new attributesBox(this);
    
    public attributesToolBar(ezERD p) {
        super();
        parent = p;
        this.setPreferredSize(new Dimension(420,0));
        this.setBackground(new Color(205,205,200));
        this.setLayout(new BorderLayout());
        SizePanel=new Panel(){
            public void paint(Graphics g){
                g.setColor(Color.GRAY);
                if(attributesToolBar.this.getWidth()>21){
                    for(int i=4;i<12;i++)
                        g.drawLine(i,attributesToolBar.this.getHeight()/2-10+i*10/12,i,attributesToolBar.this.getHeight()/2+10-i*10/12);
                }else{
                    for(int i=4;i<12;i++)
                        g.drawLine(i,attributesToolBar.this.getHeight()/2-10+(16-i)*10/12,i,attributesToolBar.this.getHeight()/2+10-(16-i)*10/12);
                }
                AttributesScrollPane.setPreferredSize(new Dimension(0,attributesToolBar.this.getHeight()*3/4));
                ObjScrollPane.setPreferredSize(new Dimension(0,attributesToolBar.this.getHeight()/4));
                TempPanel.revalidate();
            }
        };
        SizePanel.setBackground(Color.LIGHT_GRAY);
        SizePanel.setPreferredSize(new Dimension(15,0));
        SizePanel.addMouseListener(new MouseAdapter(){
            @Override                   
            public void mousePressed(MouseEvent e){
                if(attributesToolBar.this.getWidth()<21)
                    attributesToolBar.this.setPreferredSize(new Dimension(420,0));
                else
                    attributesToolBar.this.setPreferredSize(new Dimension(15,0));
                parent.MainWin.validate();
                parent.PageToolBar.resetButtonSize();
            }
        });
        ObjPanel=new Panel();
        ObjPanel.add(ObjList);
        AttributesPanel=new Panel();
        AttributesPanel.add(AttributesBox);
        AttributesScrollPane=new ScrollPane();
        AttributesScrollPane.add(AttributesPanel);
        ObjScrollPane=new ScrollPane();
        ObjScrollPane.add(ObjPanel);
        TempPanel=new Panel();
        TempPanel.setLayout(new BoxLayout(TempPanel, BoxLayout.Y_AXIS));
        TempPanel.add(AttributesScrollPane);
        TempPanel.add(ObjScrollPane);
        this.add(TempPanel,BorderLayout.CENTER);
        this.add(SizePanel,BorderLayout.WEST);
        
        for(Component a:this.getComponents())/**/
            a.addKeyListener(new keyListener(parent));/**/
        this.addKeyListener(new keyListener(parent));/**/
    }
}
