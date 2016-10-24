/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ezerd;

import java.awt.*;
import java.awt.event.*;

/**
 *
 * @author CMC
 */
public class menuBar extends MenuBar{
    ezERD parent;
    Menu  menu1, menu2, menu3;
    MenuItem newM,cloM,openM,saveM,undoM,redoM;
    menuBar(ezERD p){
        super();
        parent=p;
        
        menu1 = new Menu("File");
        newM = new MenuItem("New Page(Ctrl+N)");
        newM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                parent.Ttb.newPageBtn.doClick();
            }
        });
        cloM = new MenuItem("Close Page(Ctrl+W)");
        cloM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                parent.Ttb.cloPageBtn.doClick();
            }
        });
        
        openM = new MenuItem("Open File(Ctrl+O)");
        openM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                parent.Ttb.openBtn.doClick();
            }
        });
        saveM = new MenuItem("Save File(Ctrl+S)");
        saveM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                parent.Ttb.saveBtn.doClick();
            }
        });
        
        menu2 = new Menu("Edit");
        undoM = new MenuItem("Undo(Ctrl+Z)");
        undoM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                parent.Ttb.undoBtn.doClick();
            }
        });
        redoM = new MenuItem("Redo(Ctrl+Y)");
        redoM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                parent.Ttb.redoBtn.doClick();
            }
        });
        
        menu3 = new Menu("Help");
        
        this.add(menu1);
        this.add(menu2);
        this.add(menu3);
        menu1.add(newM);
        menu1.add(cloM);
        menu1.add(openM);
        menu1.add(saveM);
        menu2.add(undoM);
        menu2.add(redoM);
    }
}
