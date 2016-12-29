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
public class ezToolBar extends Panel{
    ezERD parent;
    JButton ChoBtn,GraffitiBtn,RecBtn,CirBtn,DiaBtn,ArrBtn,TextBtn;
    
    ezToolBar(ezERD p){
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
            TextBtn = new JButton(new ImageIcon(ImageIO.read(this.getClass().getResourceAsStream("icon/Text.png"))));
        }catch (IOException ex) {
            System.err.println(ex.getMessage());
        }
        
        ChoBtn.setBackground(this.getBackground());
        ChoBtn.setBorder(null);
        ChoBtn.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                creatingObj(ezPageActionEnum.idle,ezObjEnum.N,ChoBtn);
                parent.WorkSpace.activePage.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
            }
        });
        
        
        GraffitiBtn.setBackground(this.getBackground());
        GraffitiBtn.setBorder(null);
        GraffitiBtn.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                creatingObj(ezPageActionEnum.creatingGraffiti,ezObjEnum.graffiti,GraffitiBtn);
                parent.WorkSpace.activePage.setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
            }
        });
        ArrBtn.setBackground(this.getBackground());
        ArrBtn.setBorder(null);
        ArrBtn.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                creatingObj(ezPageActionEnum.creatingArrow,ezObjEnum.arrow,ArrBtn);
                parent.WorkSpace.activePage.setActiveObj(null);
                parent.WorkSpace.activePage.setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
            }
        });
        RecBtn.setBackground(this.getBackground());
        RecBtn.setBorder(null);
        RecBtn.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                creatingObj(ezPageActionEnum.creatingObj,ezObjEnum.rectangle,RecBtn);
                parent.WorkSpace.activePage.setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
            }
        });
        DiaBtn.setBackground(this.getBackground());
        DiaBtn.setBorder(null);
        DiaBtn.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                parent.WorkSpace.activePage.setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
                creatingObj(ezPageActionEnum.creatingObj,ezObjEnum.diamond,DiaBtn);
            }
        });
        CirBtn.setBackground(this.getBackground());
        CirBtn.setBorder(null);
        CirBtn.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                parent.WorkSpace.activePage.setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
                creatingObj(ezPageActionEnum.creatingObj,ezObjEnum.circular,CirBtn);
            }
        });
        TextBtn.setBackground(this.getBackground());
        TextBtn.setBorder(null);
        TextBtn.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                parent.WorkSpace.activePage.setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
                creatingObj(ezPageActionEnum.creatingObj,ezObjEnum.text,TextBtn);
            }
        });
        
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(ChoBtn);
        this.add(GraffitiBtn);
        this.add(ArrBtn);
        this.add(RecBtn);
        this.add(DiaBtn);
        this.add(CirBtn);
        this.add(TextBtn);
        
        for(Component a:this.getComponents())/**/
            a.addKeyListener(new ezKeyListener(parent));/**/
        
    }
    
    void creatingObj(ezPageActionEnum page,ezObjEnum obj,JButton Btn)
    {
        for (Component b : this.getComponents()) {
            if (b == Btn) {
                b.setBackground(new Color(150,150,150));
            }else{
                b.setBackground(this.getBackground());
            }
        }
        if(parent.WorkSpace.activePage!=null){ 
            parent.WorkSpace.activePage.PageActionEnum = page;
            parent.WorkSpace.activePage.ObjEnum = obj;
        }
    }
}
