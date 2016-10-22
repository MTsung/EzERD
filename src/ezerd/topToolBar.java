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
public class topToolBar extends Panel{
    ezERD parent;
    
    JButton newPageBtn = new JButton(new ImageIcon("New.png"));
    JButton cloPageBtn = new JButton(new ImageIcon("Close.png"));
    JButton undoBtn = new JButton(new ImageIcon("Undo.png"));
    JButton redoBtn = new JButton(new ImageIcon("Redo.png"));
    /*
    JButton newPageBtn = new JButton("New");
    JButton cloPageBtn = new JButton("Close");
    JButton undoBtn = new JButton("Undo");
    JButton redoBtn = new JButton("Redo");*/
    
    topToolBar(ezERD p){
        super();
        parent=p;
        this.setLayout(new FlowLayout(FlowLayout.LEFT));
        this.setBackground(new Color(205,205,205));
        //newPageBtn = new JButton("New",new ImageIcon("new.png"));
        //newPageBtn.setBorder(null);
        //newPageBtn.setBackground(Color.WHITE);
        newPageBtn.setBackground(this.getBackground());
        newPageBtn.setToolTipText("New Page");
        newPageBtn.setBorder(null);
        this.add(newPageBtn);
        newPageBtn.addMouseListener(new MouseAdapter()
        {
            @Override
            public void mouseClicked(MouseEvent e)
            {
                String name=JOptionPane.showInputDialog("檔名");
                if(name != null){
                    parent.totalPages++;
                    parent.Mb.updateMessage();
                    parent.Ws.addPage(new page(parent), "".equals(name) ? "未命名" : name);
                }
            }
        });
        
        cloPageBtn.setBackground(Color.WHITE);
        cloPageBtn.setBackground(this.getBackground());
        cloPageBtn.setToolTipText("Close Page");
        cloPageBtn.setBorder(null);
        this.add(cloPageBtn);
        cloPageBtn.addMouseListener(new MouseAdapter()
        {
            @Override
            public void mouseClicked(MouseEvent e)
            {
                if(parent.totalPages!=1)
                    if(0==JOptionPane.showConfirmDialog(null, "是否關閉","Message",2 ) )
                        parent.Ws.cloPage();
            }
        });
        
        undoBtn.setBackground(Color.WHITE);
        undoBtn.setBackground(this.getBackground());
        undoBtn.setToolTipText("Undo");
        undoBtn.setBorder(null);
        this.add(undoBtn);
        undoBtn.addMouseListener(new MouseAdapter()
        {
            @Override
            public void mouseClicked(MouseEvent e)
            {
                if(parent.Ws.activePage.undos.size()!=0){
                    int temp=parent.Ws.activePage.undos.pop();
                    parent.Ws.activePage.redos.add(temp);
                    for(int i=temp;i>0;i--){
                        parent.Ws.activePage.RePoints.add(parent.Ws.activePage.Points.elementAt(parent.Ws.activePage.Points.size()-i));
                        parent.Ws.activePage.Points.remove(parent.Ws.activePage.Points.size()-i);
                    }
                    parent.Ws.activePage.repaint();
                }
            }
        });
        
        redoBtn.setBackground(Color.WHITE);
        redoBtn.setBackground(this.getBackground());
        redoBtn.setToolTipText("Redo");
        redoBtn.setBorder(null);
        this.add(redoBtn);
        redoBtn.addMouseListener(new MouseAdapter()
        {
            @Override
            public void mouseClicked(MouseEvent e)
            {
                if(parent.Ws.activePage.redos.size()!=0){
                    int temp=parent.Ws.activePage.redos.pop();
                    parent.Ws.activePage.undos.add(temp);
                    for(int i=temp;i>0;i--){
                        parent.Ws.activePage.Points.add(parent.Ws.activePage.RePoints.elementAt(parent.Ws.activePage.RePoints.size()-i));
                        parent.Ws.activePage.RePoints.remove(parent.Ws.activePage.RePoints.size()-i);
                    }
                    parent.Ws.activePage.repaint();
                }
            }
        });
        /*
        JSlider slider=new JSlider(1,50);
        slider.setMajorTickSpacing(5);
        slider.setPaintTicks(true);
        slider.setValue(1);
        slider.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent event) {
                parent.Ws.activePage.PanSize=slider.getValue();
            }
        });
        this.add(slider);*/
    }
}
