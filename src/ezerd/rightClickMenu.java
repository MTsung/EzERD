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
public class rightClickMenu extends PopupMenu{
    ezERD parent;
    MenuItem UndoMenuItem,RedoMenuItem;
    
    rightClickMenu(ezERD p){
        parent=p;
        UndoMenuItem = new MenuItem("Undo(Ctrl+Z)");
        UndoMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                parent.TopToolBar.UndoBtn.doClick();
            }
        });
        RedoMenuItem =new MenuItem("Rndo(Ctrl+Y)");
        RedoMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                parent.TopToolBar.RedoBtn.doClick();
            }
        });
        this.add(UndoMenuItem);
        this.add(RedoMenuItem);
    }
}
