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
    Vector<Boolean> BtnJ=new Vector<Boolean>();
    Color BtnC=new Color(200,255,255);
    int BtnSum=1;
    
    pageToolBar(workSpace Ws){
        super();
        WorkSpace=Ws;
        this.setLayout(new FlowLayout(FlowLayout.LEFT));
        this.setBackground(Color.LIGHT_GRAY);
        
    }
    
    void addBtton(int n,String s){    
        Btns.add(new JButton(s));   
        Btns.elementAt(n).setToolTipText(s);
        BtnJ.add(true);
        
        BtnSum++;
        int BtnWidth=(this.getWidth()-5*BtnSum)/BtnSum;
        for(JButton Btn:Btns)
            Btn.setPreferredSize(new Dimension(BtnWidth > 200 || BtnWidth < 0 ? 200 : BtnWidth ,25));

        Btns.elementAt(n).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(WorkSpace.parent.curPage != n){
                    WorkSpace.parent.curPage = n;
                    updatePage(n);
                    activeButtonColor(n);
                }
            }
        });
        activeButtonColor(n);
        this.add(Btns.elementAt(n));
        Btns.elementAt(n).addKeyListener(new keyListener(WorkSpace.parent));/**/
        allPage();
    }
    
    void delButton(){
        int n=activeButton(),i;
        
        BtnSum--;
        int BtnW=(this.getWidth()-5*BtnSum)/BtnSum;
        for(JButton Btn:Btns)
            Btn.setPreferredSize(new Dimension(BtnW > 200 ? 200 : BtnW ,25));
        
        this.remove(Btns.elementAt(n));
        BtnJ.set(n, false);
        for(i=0;!BtnJ.elementAt(i);i++);
        if(n<i)
            while(!BtnJ.elementAt(++n));
        else 
            while(!BtnJ.elementAt(--n));
        updatePage(n);
        activeButtonColor(n);
        this.repaint();
        WorkSpace.parent.curPage=n;
        WorkSpace.parent.totalPages--;
        allPage();
    }
    
    void updatePage(int n){
        page ep =WorkSpace.Pages.elementAt(n);
        if(WorkSpace.activePage!=null){
            WorkSpace.remove(WorkSpace.activePage);
        }
        WorkSpace.add(ep, BorderLayout.CENTER);
        WorkSpace.validate();
        WorkSpace.activePage=ep;
        WorkSpace.parent.curPage=n;
    }
    
    int activeButton(){
        int i;
        for(i=0;Btns.elementAt(i).getBackground()!=BtnC;i++);
        return i;
    }
    
    void activeButtonColor(int n){
        for(JButton B:Btns)
            B.setBackground(Color.WHITE);
        Btns.elementAt(n).setBackground(BtnC);
    }
    void allPage(){
        for(int i=0;i<BtnJ.size();i++)
            if(BtnJ.elementAt(i))
                System.out.print(i+" ");
        System.out.println();
    }
    
}
