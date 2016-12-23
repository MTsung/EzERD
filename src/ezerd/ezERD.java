/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ezerd;

import java.awt.Frame;
import java.io.*;

/**
 *
 * @author CMC
 */
public class ezERD {
    ezMainWin MainWin;
    ezTopToolBar TopToolBar;
    ezMessageBar MessageBar;
    ezWorkSpace WorkSpace;
    ezPageToolBar PageToolBar;
    ezPageScrollPane PageScrollPane;
    ezToolBar ToolBar;
    ezAttributesToolBar AttributesToolBar;
    int TotalPages=1;
    int CurPage=0;
    
    ezERD() {
        MainWin =new ezMainWin(this);
        TopToolBar=new ezTopToolBar(this);
        MessageBar=new ezMessageBar(this);
        WorkSpace=new ezWorkSpace(this);
        ToolBar=new ezToolBar(this);
        AttributesToolBar=new ezAttributesToolBar(this);
        PageToolBar=new ezPageToolBar(WorkSpace);
        PageScrollPane=new ezPageScrollPane(this);
        
        MainWin.addToolBar(ToolBar);
        MainWin.addWorkSpace(WorkSpace);
        MainWin.addTopToolbar(TopToolBar);
        MainWin.addMessagebar(MessageBar);
        WorkSpace.addPageToolBar(PageToolBar);
        WorkSpace.addPageScrollPane(PageScrollPane);
        PageScrollPane.addPage(new ezPage(this),"NewPage.sss");
        MainWin.addAttributesToolBar(AttributesToolBar);
    }
    void run(){
        MainWin.setVisible(true);
        MainWin.setExtendedState(Frame.MAXIMIZED_BOTH);
        setLanguage();
    }
    void setLanguage(){
        try {
            File selectedFile = new File("Language.ini");
            BufferedReader br = new BufferedReader(new FileReader(selectedFile));
            new ezLanguage(this,br.readLine());
        } catch (Exception ex) {
        }
    }
}
