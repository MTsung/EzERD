/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ezerd;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.*;
/**
 *
 * @author CMC
 */
public class toolBar extends Panel{
    ezERD parent;
    JButton ChoBtn,GraffitiBtn,RecBtn,CirBtn,DiaBtn,ArrBtn;
    
    toolBar(ezERD p){
        super();
        parent=p;
        this.setBackground(new Color(205,205,200));
        
        try{
            ChoBtn = new JButton(new ImageIcon(ImageIO.read(this.getClass().getResourceAsStream("icon/Choose.png"))));
            GraffitiBtn = new JButton(new ImageIcon(ImageIO.read(this.getClass().getResourceAsStream("icon/Graffiti.png"))));
            ArrBtn = new JButton(new ImageIcon(ImageIO.read(this.getClass().getResourceAsStream("icon/Arrow.png"))));
            RecBtn = new JButton(new ImageIcon(ImageIO.read(this.getClass().getResourceAsStream("icon/Rectangle.png"))));
            DiaBtn = new JButton(new ImageIcon(ImageIO.read(this.getClass().getResourceAsStream("icon/Diamond.png"))));
            CirBtn = new JButton(new ImageIcon(ImageIO.read(this.getClass().getResourceAsStream("icon/Circular.png"))));
        }catch (IOException ex) {
            System.err.println(ex.getMessage());
        }
        
        ChoBtn.setOpaque(true);
        ChoBtn.setBorderPainted(false);
        ChoBtn.setBackground(this.getBackground());
        ChoBtn.setBorder(null);
        ChoBtn.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                creatingObj(objEnum.N,ChoBtn);
                parent.WorkSpace.activePage.PageActionEnum=pageActionEnum.idle;
                parent.WorkSpace.activePage.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
            }
        });
        
        
        GraffitiBtn.setOpaque(true);
        GraffitiBtn.setBorderPainted(false);
        GraffitiBtn.setBackground(this.getBackground());
        GraffitiBtn.setBorder(null);
        GraffitiBtn.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                creatingObj(objEnum.graffiti,GraffitiBtn);
                parent.WorkSpace.activePage.setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
                //Cursor cusTand = toolBar.this.getToolkit().createCustomCursor( new ImageIcon("Line.png").getImage(),new Point(5,40),"Pan");  
                //parent.Ws.activePage.setCursor(cusTand);
            }
        });
        ArrBtn.setOpaque(true);
        ArrBtn.setBorderPainted(false);
        ArrBtn.setBackground(this.getBackground());
        ArrBtn.setBorder(null);
        ArrBtn.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                parent.WorkSpace.activePage.setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
                creatingObj(objEnum.arrow,ArrBtn);
            }
        });
        RecBtn.setOpaque(true);
        RecBtn.setBorderPainted(false);
        RecBtn.setBackground(this.getBackground());
        RecBtn.setBorder(null);
        RecBtn.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                parent.WorkSpace.activePage.setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
                creatingObj(objEnum.rectangle,RecBtn);
            }
        });
        DiaBtn.setOpaque(true);
        DiaBtn.setBorderPainted(false);
        DiaBtn.setBackground(this.getBackground());
        DiaBtn.setBorder(null);
        DiaBtn.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                parent.WorkSpace.activePage.setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
                creatingObj(objEnum.diamond,DiaBtn);
            }
        });
        CirBtn.setOpaque(true);
        CirBtn.setBorderPainted(false);
        CirBtn.setBackground(this.getBackground());
        CirBtn.setBorder(null);
        CirBtn.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                parent.WorkSpace.activePage.setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
                creatingObj(objEnum.circular,CirBtn);
            }
        });
        
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(ChoBtn);
        this.add(GraffitiBtn);
        this.add(ArrBtn);
        this.add(RecBtn);
        this.add(DiaBtn);
        this.add(CirBtn);
        
        for(Component a:this.getComponents())/**/
            a.addKeyListener(new keyListener(parent));/**/
        
    }
    
    void creatingObj(objEnum obj,JButton Btn)
    {
        for (Component b : this.getComponents()) {
            if (b == Btn) {
                b.setBackground(new Color(150,150,150));
            }else{
                b.setBackground(this.getBackground());
            }
        }
        if(parent.WorkSpace.activePage!=null){ 
            //if(parent.WorkSpace.activePage.PageActionEnum==pageActionEnum.idle)
            {
                parent.WorkSpace.activePage.PageActionEnum=pageActionEnum.ready2createObject;
                parent.WorkSpace.activePage.ObjEnum = obj;
            }
        }
    }
}
