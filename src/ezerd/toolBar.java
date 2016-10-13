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
    toolBar(ezERD p){
        super();
        parent=p;
        this.setLayout(new FlowLayout(FlowLayout.LEFT));
        this.setBackground(Color.GRAY);
        JButton newPageBtn = new JButton("New");
        //JButton newPageBtn = new JButton("",new ImageIcon("new.png"));
        //newPageBtn.setBorder(null);
        newPageBtn.setBackground(Color.WHITE);
        this.add(newPageBtn);
        newPageBtn.addMouseListener(new MouseAdapter()
        {
            @Override
            public void mouseClicked(MouseEvent e)
            {
                parent.totalPages++;
                parent.curPage++;
                parent.Mb.updateMessage();
                parent.Ws.addPage(new page());
            }
        });
        
    }
}
