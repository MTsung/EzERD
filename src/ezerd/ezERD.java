/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ezerd;

import java.awt.Frame;
import java.io.IOException;

/**
 *
 * @author CMC
 */
public class ezERD {
    mainWin MainWin;
    topToolBar TopToolBar;
    messageBar MessageBar;
    workSpace WorkSpace;
    pageToolBar PageToolBar;
    toolBar ToolBar;
    attributesToolBar AttributesToolBar;
    int TotalPages=1;
    int CurPage=0;
    
    ezERD() {
        MainWin =new mainWin(this);
        TopToolBar=new topToolBar(this);
        MessageBar=new messageBar(this);
        WorkSpace=new workSpace(this);
        ToolBar=new toolBar(this);
        AttributesToolBar=new attributesToolBar(this);
        PageToolBar=new pageToolBar(WorkSpace);
        
        MainWin.addToolBar(ToolBar);
        MainWin.addWorkSpace(WorkSpace);
        MainWin.addTopToolbar(TopToolBar);
        MainWin.addMessagebar(MessageBar);
        WorkSpace.addPageToolBar(PageToolBar);
        WorkSpace.addPage(new page(this),"NewPage.sss");
        MainWin.addAttributesToolBar(AttributesToolBar);
    }
    void run(){
        MainWin.setVisible(true);
        MainWin.setExtendedState(Frame.MAXIMIZED_BOTH);
    }
}
