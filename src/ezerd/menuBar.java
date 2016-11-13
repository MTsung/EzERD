/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ezerd;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.*;

/**
 *
 * @author CMC
 */
public class menuBar extends MenuBar{
    ezERD parent;
    Menu  FileMenu, EditMenu, LanguageMenu,HelpMenu,ExportFileMenu,CachingPageMenu,AllPageMenu,PageMenu;
    MenuItem newM,cloM,openM,saveM,undoM,redoM,TW,JP,EN,JpgM,PngM,GifM,BmpM,copyM,pasteM;
    menuBar(ezERD p){
        super();
        parent=p;
        this.setFont(new programFont());
        FileMenu = new Menu("File");
        newM = new MenuItem("New Page(Ctrl+N)");
        newM.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                parent.TopToolBar.NewPageBtn.doClick();
            }
        });
        cloM = new MenuItem("Close Page(Ctrl+W)");
        cloM.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                parent.TopToolBar.ClosePageBtn.doClick();
            }
        });
        
        openM = new MenuItem("Open File(Ctrl+O)");
        openM.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                parent.TopToolBar.OpenBtn.doClick();
            }
        });
        saveM = new MenuItem("Save File(Ctrl+S)");
        saveM.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                parent.TopToolBar.SaveBtn.doClick();
            }
        });
        ExportFileMenu=new Menu("Export File");
        JpgM = new MenuItem("JPG");
        JpgM.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                savePageToImage("jpg");
            }
        });
        PngM = new MenuItem("PNG");
        PngM.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                savePageToImage("png");
            }
        });
        GifM = new MenuItem("GIF");
        GifM.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                savePageToImage("gif");
            }
        });
        BmpM = new MenuItem("BMP");
        BmpM.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                savePageToImage("bmp");
            }
        });
        CachingPageMenu = new Menu("Caching Page");
        AllPageMenu=new Menu("All Page");
        
        EditMenu = new Menu("Edit");
        undoM = new MenuItem("Undo(Ctrl+Z)");
        undoM.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                parent.TopToolBar.UndoBtn.doClick();
            }
        });
        redoM = new MenuItem("Redo(Ctrl+Y)");
        redoM.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                parent.TopToolBar.RedoBtn.doClick();
            }
        });
        copyM = new MenuItem("Copy(Ctrl+C)");
        copyM.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                parent.WorkSpace.activePage.CopyObj=parent.WorkSpace.activePage.activeObj;
                pasteM.setEnabled(true);
            }
        });
        pasteM = new MenuItem("Paste(Ctrl+V)");
        pasteM.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                obj o=null;
                try {
                    for (object p : parent.WorkSpace.activePage.Points) {
                        if (p.ObjID == parent.WorkSpace.activePage.CopyObj.ID) {
                            if (p.ObjEnum == objEnum.rectangle) {
                                o = new objRectangle(parent.WorkSpace.activePage, p.PenColor, p.PenSize, parent.WorkSpace.activePage.ObjID);
                                parent.WorkSpace.activePage.add(o, 0);
                            } else if (p.ObjEnum == objEnum.circular) {
                                o = new objCircular(parent.WorkSpace.activePage, p.PenColor, p.PenSize, parent.WorkSpace.activePage.ObjID);
                                parent.WorkSpace.activePage.add(o, 0);
                            } else if (p.ObjEnum == objEnum.diamond) {
                                o = new objDiamond(parent.WorkSpace.activePage, p.PenColor, p.PenSize, parent.WorkSpace.activePage.ObjID);
                                parent.WorkSpace.activePage.add(o, 0);
                            }
                            parent.WorkSpace.activePage.add(o, 0);
                            parent.AttributesToolBar.ObjList.addObj(parent.WorkSpace.activePage.ObjID);
                            parent.AttributesToolBar.ObjList.setActiveObj(parent.WorkSpace.activePage.ObjID);
                            o.setLocation(0,0);
                            o.setSize(Math.abs(p.Sp.x - p.Ep.x), Math.abs(p.Sp.y - p.Ep.y));
                            parent.WorkSpace.activePage.Objs.add(o);
                            parent.WorkSpace.activePage.CopyObj = o;
                            parent.WorkSpace.activePage.Points.add(new object(new Point(0, 0),
                                    new Point(Math.abs(p.Sp.x - p.Ep.x), Math.abs(p.Sp.y - p.Ep.y)), p.PenSize, p.PenColor,
                                    p.ObjEnum, parent.WorkSpace.activePage.ObjID++));
                        }
                    }
                } catch (Throwable ee) {
                }
            }
        });
        
        PageMenu = new  Menu("Page");
        
        LanguageMenu = new Menu("Language");
        TW= new MenuItem("中文(Taiwan)");
        TW.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    File selectedFile = new File("Language.ini");
                    PrintWriter pw   = new PrintWriter(selectedFile);
                    pw.write("Taiwan");     
                    pw.close();
                    menuBar.this.parent.setLanguage();
                } catch (IOException ex) {
                    Logger.getLogger(menuBar.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        JP= new MenuItem("日本語(Japan)");
        JP.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    File selectedFile = new File("Language.ini");
                    PrintWriter pw   = new PrintWriter(selectedFile);
                    pw.write("Japan");     
                    pw.close();
                    menuBar.this.parent.setLanguage();
                } catch (IOException ex) {
                    Logger.getLogger(menuBar.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        EN= new MenuItem("English");
        EN.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    File selectedFile = new File("Language.ini");
                    PrintWriter pw   = new PrintWriter(selectedFile);
                    pw.write("English");     
                    pw.close();
                    menuBar.this.parent.setLanguage();
                } catch (IOException ex) {
                    Logger.getLogger(menuBar.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        
        HelpMenu = new Menu("Help");
        this.add(FileMenu);
        this.add(EditMenu);
        this.add(PageMenu);
        this.add(LanguageMenu);
        this.add(HelpMenu);
        FileMenu.add(newM);
        FileMenu.add(cloM);
        FileMenu.add(openM);
        FileMenu.add(saveM);
        FileMenu.add(ExportFileMenu);
        ExportFileMenu.add(JpgM);
        ExportFileMenu.add(PngM);
        ExportFileMenu.add(GifM);
        ExportFileMenu.add(BmpM);
        EditMenu.add(undoM);
        EditMenu.add(redoM);
        EditMenu.add(copyM);
        EditMenu.add(pasteM);
        PageMenu.add(AllPageMenu);
        PageMenu.add(CachingPageMenu);
        LanguageMenu.add(TW);
        LanguageMenu.add(JP);
        LanguageMenu.add(EN);
    }
    void savePageToImage(String S){
        FileDialog fileChooser = new FileDialog(parent.MainWin, "Save", FileDialog.SAVE);
        String temp;
        if (parent.PageToolBar.Btns.elementAt(parent.PageToolBar.activeButton()).getText().indexOf("*") > -1) {
            temp = parent.PageToolBar.Btns.elementAt(parent.PageToolBar.activeButton()).getText().replace(".sss*", "");
        } else {
            temp = parent.PageToolBar.Btns.elementAt(parent.PageToolBar.activeButton()).getText().replace(".sss", "");
        }
        fileChooser.setFile(temp + "."+ S);
        fileChooser.setVisible(true);
        if (fileChooser.getDirectory() != null && fileChooser.getFile() != null) {
            BufferedImage img = new BufferedImage(parent.WorkSpace.activePage.getWidth(),
                                                    parent.WorkSpace.activePage.getHeight(), BufferedImage.TYPE_INT_RGB);
            parent.WorkSpace.activePage.paint(img.getGraphics());
            
            try {
                ImageIO.write(img,S, new File(fileChooser.getDirectory() + fileChooser.getFile()));

            } catch (Exception ex) {
                System.out.println("panel not saved" + ex.getMessage());
            }
        }
    }
}
