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
public class ezERD {
    mainWin Win;
    topToolBar Ttb;
    messageBar Mb;
    workSpace Ws;
    pageToolBar Ptb;
    toolBar Tb;
    colorWin Cw;
    int totalPages=1;
    int curPage=0;
    DefaultListModel<String> model = new DefaultListModel<>();
    
    ezERD(){
        Win =new mainWin(this);
        Ttb=new topToolBar(this);
        Mb=new messageBar(this);
        Ws=new workSpace(this);
        Tb=new toolBar(this);
        Ptb=new pageToolBar(Ws);
        Cw=new colorWin(this);
        
        Win.addList(Tb);
        Win.addWorkSpace(Ws);
        Win.addToolbar(Ttb);
        Win.addMessagebar(Mb);
        Ws.addPageToolBar(Ptb);
        Ws.addPage(new page(this),"新頁面");
    }
    void run(){
        Win.setVisible(true);
        //Cw.setVisible(true);
    }
}
