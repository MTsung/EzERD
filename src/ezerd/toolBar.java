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
public class toolBar extends Panel{
    ezERD parent;
    
    JButton ChoBtn = new JButton(new ImageIcon("Choose.png"));
    JButton LineBtn = new JButton(new ImageIcon("Line.png"));
    JButton RecBtn = new JButton(new ImageIcon("Rectangle.png"));
    JButton CirBtn = new JButton(new ImageIcon("Circular.png"));
    
    toolBar(ezERD p){
        super();
        parent=p;
        this.setBackground(new Color(205,205,200));
        ChoBtn.setBackground(this.getBackground());
        ChoBtn.setBorder(null);
        ChoBtn.addMouseListener(new MouseAdapter(){
            @Override
            public void mousePressed(MouseEvent e){
                parent.WorkSpace.activePage.LineT=false;
                parent.WorkSpace.activePage.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
            }
        });
        
        
        LineBtn.setBackground(this.getBackground());
        LineBtn.setBorder(null);
        LineBtn.addMouseListener(new MouseAdapter(){
            @Override
            public void mousePressed(MouseEvent e){
                parent.WorkSpace.activePage.LineT=true;
                parent.WorkSpace.activePage.setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
                //Cursor cusTand = toolBar.this.getToolkit().createCustomCursor( new ImageIcon("Line.png").getImage(),new Point(5,40),"Pan");  
                //parent.Ws.activePage.setCursor(cusTand);
            }
        });
        RecBtn.setBackground(this.getBackground());
        RecBtn.setBorder(null);
        RecBtn.addMouseListener(new MouseAdapter(){
            @Override
            public void mousePressed(MouseEvent e){
                parent.WorkSpace.activePage.setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
            }
        });
        
        CirBtn.setBackground(this.getBackground());
        CirBtn.setBorder(null);
        CirBtn.addMouseListener(new MouseAdapter(){
            @Override
            public void mousePressed(MouseEvent e){
                parent.WorkSpace.activePage.setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
            }
        });
        
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(ChoBtn);
        this.add(LineBtn);
        this.add(RecBtn);
        this.add(CirBtn);
        
        for(Component a:this.getComponents())/**/
            a.addKeyListener(new keyListener(parent));/**/
        
    }
}
