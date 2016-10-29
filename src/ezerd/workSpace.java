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
    void addPage(page p,String s){
        parent.PageToolBar.addBtton(count++,s);
        if(activePage!=null){
            this.remove(activePage);
        }
        this.add(p);
        this.validate();
        activePage=p;
        Pages.add(p);
    }
    
    void cloPage(){
        parent.PageToolBar.delButton();
    }
    
    void addPageToolBar(pageToolBar p) {
        this.add(p,BorderLayout.NORTH);
    }
}
