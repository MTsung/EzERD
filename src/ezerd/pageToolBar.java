/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ezerd;

import java.awt.*;
import java.awt.event.*;
import java.util.Vector;
import javax.swing.*;

/**
 *
 * @author CMC
 */
public class pageToolBar extends Panel{
    workSpace WorkSpace;
    Vector<JButton> Btns=new Vector<JButton>();
    pageToolBar(workSpace Ws){
        super();
        WorkSpace=Ws;
        this.setLayout(new FlowLayout(FlowLayout.LEFT));
        this.setBackground(Color.LIGHT_GRAY);
    }
    void addBtton(int n){
        JButton button=new JButton(""+(n+1));
        button.addMouseListener(new MouseAdapter(){
            @Override
            public void mousePressed(MouseEvent e)
            {
                page ep =WorkSpace.Pages.elementAt(n);

                if(WorkSpace.activePage!=null){
                    WorkSpace.remove(WorkSpace.activePage);
                }
                WorkSpace.add(ep, BorderLayout.CENTER);
                WorkSpace.validate();
                WorkSpace.parent.curPage=n+1;
                WorkSpace.parent.Mb.updateMessage();
                WorkSpace.activePage=ep;
                for(int i=0;i<Btns.size();i++)
                    Btns.elementAt(i).setBackground(Color.WHITE);
                button.setBackground(Color.YELLOW);
            }
        });
        for(int i=0;i<Btns.size();i++)
            Btns.elementAt(i).setBackground(Color.WHITE);
        button.setBackground(Color.YELLOW);
        this.add(button);
        Btns.add(button);
    }
}
