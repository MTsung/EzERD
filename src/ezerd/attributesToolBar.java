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
    Panel SizePanel,TempPanel;
    ScrollPane AttributesScrollPane;
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
        TempPanel=new Panel();
        TempPanel.add(AttributesBox);
        AttributesScrollPane=new ScrollPane();
        AttributesScrollPane.add(TempPanel);
        this.add(AttributesScrollPane,BorderLayout.CENTER);
        this.add(SizePanel,BorderLayout.WEST);
        
        for(Component a:this.getComponents())/**/
            a.addKeyListener(new keyListener(parent));/**/
        this.addKeyListener(new keyListener(parent));/**/
    }
}
