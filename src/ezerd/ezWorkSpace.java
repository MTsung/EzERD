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
public class ezWorkSpace extends Panel{
    ezERD parent;
    Vector<ezPage> Pages;
    ezPage activePage=null;
    static int count=0;
    
    ezWorkSpace(ezERD p){
        parent=p;
        this.setLayout(new BorderLayout());
        this.setBackground(Color.LIGHT_GRAY);
        Pages = new Vector<ezPage>();
        
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
    
    void addPageToolBar(ezPageToolBar p) {
        this.add(p,BorderLayout.NORTH);
    }
    void addPageScrollPane(ezPageScrollPane p) {
        this.add(p,BorderLayout.CENTER);
    }
}
