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
    toolBar(ezERD p){
        super();
        parent=p;
        this.setLayout(new FlowLayout(FlowLayout.LEFT));
        this.setBackground(new Color(205,205,205));
        //JButton newPageBtn = new JButton("",new ImageIcon("new.png"));
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
                    parent.Ws.addPage(new page(), "".equals(name) ? "未命名" : name);
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

    }
}
