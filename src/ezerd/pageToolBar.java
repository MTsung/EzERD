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
    pageToolBar(workSpace Ws){
        super();
        WorkSpace=Ws;
        this.setLayout(new FlowLayout(FlowLayout.LEFT));
        this.setBackground(Color.LIGHT_GRAY);
    }
    
    void addBtton(int n,String s){
        Btns.add(new JButton(s));
        BtnJ.add(true);
        Btns.elementAt(n).addMouseListener(new MouseAdapter(){
            @Override
            public void mousePressed(MouseEvent e){
                WorkSpace.parent.curPage=n;
                /*       */System.out.println("Click : " + n);
                upPage(n);
                yButton(n);
            }
        });
        yButton(n);
        this.add(Btns.elementAt(n));
        
        /*       */System.out.println("New : " + n);
        /*       */for(int ii=0;ii<BtnJ.size();ii++)
        /*       */    if(BtnJ.elementAt(ii))
        /*       */        System.out.print(ii+" ");
        /*       */System.out.println();
    }
    
    void delButton(){
        int n=activeButton(),i;
        
        /*       */System.out.println("Del : " + n);
        
        this.remove(Btns.elementAt(n));
        BtnJ.set(n, false);
        for(i=0;!BtnJ.elementAt(i);i++);
        if(n<i)
            while(!BtnJ.elementAt(++n));
        else 
            while(!BtnJ.elementAt(--n));
        upPage(n);
        yButton(n);
        WorkSpace.validate();
        this.repaint();
        WorkSpace.parent.totalPages--;
        WorkSpace.parent.Mb.updateMessage();
        
        /*       */for(int ii=0;ii<BtnJ.size();ii++)
        /*       */    if(BtnJ.elementAt(ii))
        /*       */        System.out.print(ii+" ");
        /*       */System.out.println();

    }
    
    void upPage(int n){
        page ep =WorkSpace.Pages.elementAt(n);
        if(WorkSpace.activePage!=null){
            WorkSpace.remove(WorkSpace.activePage);
        }
        WorkSpace.add(ep, BorderLayout.CENTER);
        WorkSpace.validate();
        WorkSpace.activePage=ep;
        WorkSpace.parent.curPage=n;
        WorkSpace.parent.Mb.updateMessage();
    }
    int activeButton(){
        int i;
        for(i=0;Btns.elementAt(i).getBackground()!=BtnC;i++);
        return i;
    }
    void yButton(int n){
        for(JButton B:Btns)
            B.setBackground(Color.WHITE);
         Btns.elementAt(n).setBackground(BtnC);
    }
}
