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

/**
 *
 * @author CMC
 */
public class ezMessageBar extends Panel{
    ezERD parent;
    String message;
    Label pageInfo;
    Point XY=new Point(1000,1000);
    
    public void updatePageInfo()
    {
        pageInfo.setText("(" + XY.x + "," + XY.y + ")");
    }
    
    public void updateMessage()
    {
        updatePageInfo();
        parent.MainWin.setTitle("EzERD-" + parent.PageToolBar.Btns.elementAt(parent.PageToolBar.activeButton()).getText());
    }
    
    public void showMessage()
    {
        pageInfo = new Label(message);
        this.add(pageInfo);
    }
    
    ezMessageBar(ezERD p){
        super();
        parent=p;
        message="(" + XY.x + "," + XY.y + ")";
        this.setLayout(new FlowLayout(FlowLayout.LEFT));
        this.setBackground(new Color(222,222,222));
        showMessage();
    }
            
    
}
