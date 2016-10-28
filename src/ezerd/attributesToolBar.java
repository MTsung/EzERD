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

public class attributesToolBar extends Panel {
    ezERD parent;
    Panel SizePanel;
    penAttributesBox PenAttributesBox=new penAttributesBox(this);
    
    public attributesToolBar(ezERD p) {
        super();
        parent = p;
        this.setPreferredSize(new Dimension(380,0));
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
                    attributesToolBar.this.setPreferredSize(new Dimension(380,0));
                else
                    attributesToolBar.this.setPreferredSize(new Dimension(15,0));
                parent.MainWin.validate();
            }
        });
        
        
        this.add(PenAttributesBox,BorderLayout.CENTER);
        this.add(SizePanel,BorderLayout.WEST);
        
        for(Component a:this.getComponents())/**/
            a.addKeyListener(new keyListener(parent));/**/
        this.addKeyListener(new keyListener(parent));/**/
    }
}
