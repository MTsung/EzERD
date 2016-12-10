/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ezerd;

import static ezerd.workSpace.count;
import java.awt.*;

/**
 *
 * @author CMC
 */
public class pageScrollPane extends ScrollPane{
    ezERD parent;
    Panel TempPanel;
    pageScrollPane(ezERD p){
        super();
        parent=p;
        TempPanel=new Panel();
        TempPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
    }
    void addPage(page p,String s){
        parent.PageToolBar.addBtton(count++,s);
        if(parent.WorkSpace.activePage!=null){
            this.remove(parent.WorkSpace.activePage);
        }
        parent.MainWin.setTitle("EzERD-" + parent.PageToolBar.Btns.elementAt(parent.PageToolBar.activeButton()).getText());
        TempPanel.removeAll();
        TempPanel.add(p);
        this.add(TempPanel);
        parent.WorkSpace.validate();
        parent.WorkSpace.activePage=p;
        parent.WorkSpace.Pages.add(p);
        parent.ToolBar.ChoBtn.doClick();
    }
}
