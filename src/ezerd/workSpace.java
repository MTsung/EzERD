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
import java.util.Vector;

/**
 *
 * @author CMC
 */
public class workSpace extends Panel{
    ezERD parent;
    Vector<page> Pages;
    page activePage=null;
    static int count=0;
    
    workSpace(ezERD p){
        parent=p;
        this.setLayout(new BorderLayout());
        this.setBackground(Color.LIGHT_GRAY);
        Pages = new Vector<page>();
        
    }
    
    void cloPage(){
        parent.PageToolBar.delButton();
    }
    
    void prevPage()
    {
        int i;
        for(i=0;!parent.PageToolBar.BtnJ.elementAt(i);i++);
        if(parent.CurPage!=i){
            int n=parent.CurPage;
            while(!parent.PageToolBar.BtnJ.elementAt(--n));
            parent.PageToolBar.Btns.elementAt(n).doClick();
        }
    }

    void nextPage()
    {
        if(parent.TotalPages-1!=parent.CurPage){
            int n=parent.CurPage;
            while(!parent.PageToolBar.BtnJ.elementAt(++n));
            parent.PageToolBar.Btns.elementAt(n).doClick();
        }
    }
    
    void addPageToolBar(pageToolBar p) {
        this.add(p,BorderLayout.NORTH);
    }
    void addPageScrollPane(pageScrollPane p) {
        this.add(p,BorderLayout.CENTER);
    }
}
