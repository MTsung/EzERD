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
import static javax.swing.JFrame.EXIT_ON_CLOSE;

/**
 *
 * @author CMC
 */
public class ezERD {
    mainWin Win;
    toolBar Tb;
    messageBar Mb;
    workSpace Ws;
    pageToolBar Ptb;
    list Lt;
    colorWin Cw;
    int totalPages=1;
    int curPage=0;
    DefaultListModel<String> model = new DefaultListModel<>();
    
    ezERD(){
        Win =new mainWin(this);
        Tb=new toolBar(this);
        Mb=new messageBar(this);
        Ws=new workSpace(this);
        Ptb=new pageToolBar(Ws);
        Lt=new list(model);
        Cw=new colorWin(this);
        
        Win.addList(Lt);
        Win.addWorkSpace(Ws);
        Win.addToolbar(Tb);
        Win.addMessagebar(Mb);
        Ws.addPageToolBar(Ptb);
        Ws.addPage(new page(this),"新頁面");
        
    }
    void run(){
        Win.setVisible(true);
        //Cw.setVisible(true);
    }
}
