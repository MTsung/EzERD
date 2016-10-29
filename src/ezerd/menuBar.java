/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ezerd;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.*;

/**
 *
 * @author CMC
 */
public class menuBar extends MenuBar{
    ezERD parent;
    Menu  FileMenu, EditMenu, LanguageMenu,HelpMenu,ExportFileMenu;
    MenuItem newM,cloM,openM,saveM,undoM,redoM,TW,JP,EN,PngM;
    menuBar(ezERD p){
        super();
        parent=p;
        this.setFont(new programFont());
        FileMenu = new Menu("File");
        newM = new MenuItem("New Page(Ctrl+N)");
        newM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                parent.TopToolBar.NewPageBtn.doClick();
            }
        });
        cloM = new MenuItem("Close Page(Ctrl+W)");
        cloM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                parent.TopToolBar.ClosePageBtn.doClick();
            }
        });
        
        openM = new MenuItem("Open File(Ctrl+O)");
        openM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                parent.TopToolBar.OpenBtn.doClick();
            }
        });
        saveM = new MenuItem("Save File(Ctrl+S)");
        saveM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                parent.TopToolBar.SaveBtn.doClick();
            }
        });
        ExportFileMenu=new Menu("Export File");
        PngM = new MenuItem("PNG");
        PngM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                FileDialog fileChooser=new FileDialog(menuBar.this.parent.MainWin, "Save", FileDialog.SAVE);
                String temp;
                if(parent.PageToolBar.Btns.elementAt(parent.PageToolBar.activeButton()).getText().indexOf("*")>-1)
                    temp=parent.PageToolBar.Btns.elementAt(parent.PageToolBar.activeButton()).getText().replace(".sss*","");
                else
                    temp=parent.PageToolBar.Btns.elementAt(parent.PageToolBar.activeButton()).getText().replace(".sss","");
                fileChooser.setFile(temp+".png");
                fileChooser.setVisible(true);
                if(fileChooser.getDirectory() != null && fileChooser.getFile() != null){
                    BufferedImage img = new BufferedImage(parent.WorkSpace.activePage.getWidth(),
                                                           parent.WorkSpace.activePage.getHeight(), BufferedImage.TYPE_INT_RGB);
                    parent.WorkSpace.activePage.paint(img.getGraphics());
                    try {
                        ImageIO.write(img, "png", new File(fileChooser.getDirectory() + fileChooser.getFile()));

                    } catch (Exception ex) {
                        System.out.println("panel not saved" + ex.getMessage());
                    }
                }
            }
        });
        
        EditMenu = new Menu("Edit");
        undoM = new MenuItem("Undo(Ctrl+Z)");
        undoM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                parent.TopToolBar.UndoBtn.doClick();
            }
        });
        redoM = new MenuItem("Redo(Ctrl+Y)");
        redoM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                parent.TopToolBar.RedoBtn.doClick();
            }
        });
        
        LanguageMenu = new Menu("Language");
        TW= new MenuItem("中文(Taiwan)");
        JP= new MenuItem("日本語(Japanese)");
        EN= new MenuItem("English");
        HelpMenu = new Menu("Help");
        this.add(FileMenu);
        this.add(EditMenu);
        this.add(LanguageMenu);
        this.add(HelpMenu);
        FileMenu.add(newM);
        FileMenu.add(cloM);
        FileMenu.add(openM);
        FileMenu.add(saveM);
        FileMenu.add(ExportFileMenu);
        ExportFileMenu.add(PngM);
        EditMenu.add(undoM);
        EditMenu.add(redoM);
        LanguageMenu.add(TW);
        LanguageMenu.add(JP);
        LanguageMenu.add(EN);
    }
}
