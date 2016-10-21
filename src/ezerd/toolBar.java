/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ezerd;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 *
 * @author CMC
 */
public class toolBar extends Panel{
    ezERD parent;
    JButton cloPageBtn = new JButton("Close");
    JButton newPageBtn = new JButton("New");
    JButton undoBtn = new JButton("Undo");
    JButton redoBtn = new JButton("Redo");
    
    toolBar(ezERD p){
        super();
        parent=p;
        this.setLayout(new FlowLayout(FlowLayout.LEFT));
        this.setBackground(new Color(205,205,205));
        //newPageBtn = new JButton("New",new ImageIcon("new.png"));
        //newPageBtn.setBorder(null);
        newPageBtn.setBackground(Color.WHITE);
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
    }
}
