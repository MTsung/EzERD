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
    mainWin win;
    toolBar Tb;
    messageBar Mb;
    workSpace Ws;
    pageToolBar Ptb;
    list Lt;
    int totalPages=1;
    int curPage=0;
    DefaultListModel<String> model = new DefaultListModel<>();
    
    ezERD(){
        win =new mainWin(this);
        Tb=new toolBar(this);
        Mb=new messageBar(this);
        Ws=new workSpace(this);
        Ptb=new pageToolBar(Ws);
        Lt=new list(model);
        
        win.addList(Lt);
        win.addWorkSpace(Ws);
        Ws.addPageToolBar(Ptb);
        win.addToolbar(Tb);
        win.addMessagebar(Mb);
        Ws.addPage(new page(),"新頁面");
        
    }
    void run(){
        win.setVisible(true);
    }
}
