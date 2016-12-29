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
public class ezMenuBar extends MenuBar{
    ezERD parent;
    Menu  FileMenu, EditMenu, LanguageMenu,ExportFileMenu,CachingPageMenu,AllPageMenu,PageMenu;
    MenuItem NewM,CloseM,OpenM,SaveM,UndoM,RedoM,TW,EN,JpgM,PngM,GifM,BmpM,CopyM,PasteM,DelM;
    ezMenuBar(ezERD p){
        super();
        parent=p;
        this.setFont(new ezFont());
        FileMenu = new Menu("File");
        NewM = new MenuItem("New Page(Ctrl+N)");
        NewM.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                parent.TopToolBar.NewPageBtn.doClick();
            }
        });
        CloseM = new MenuItem("Close Page(Ctrl+W)");
        CloseM.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                parent.TopToolBar.ClosePageBtn.doClick();
            }
        });
        
        OpenM = new MenuItem("Open File(Ctrl+O)");
        OpenM.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                parent.TopToolBar.OpenBtn.doClick();
            }
        });
        SaveM = new MenuItem("Save File(Ctrl+S)");
        SaveM.addActionListener(new ActionListener() {
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
        UndoM = new MenuItem("Undo(Ctrl+Z)");
        UndoM.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                parent.TopToolBar.UndoBtn.doClick();
            }
        });
        RedoM = new MenuItem("Redo(Ctrl+Y)");
        RedoM.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                parent.TopToolBar.RedoBtn.doClick();
            }
        });
        CopyM = new MenuItem("Copy(Ctrl+C)");
        CopyM.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                parent.WorkSpace.activePage.CopyObj=parent.WorkSpace.activePage.activeObj;
                PasteM.setEnabled(true);
            }
        });
        PasteM = new MenuItem("Paste(Ctrl+V)");
        PasteM.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ezObj o=null;
                try {
                    for (ezObject p : parent.WorkSpace.activePage.Points) {
                        if (p.ObjID == parent.WorkSpace.activePage.CopyObj.ID) {
                            if (p.ObjEnum == ezObjEnum.rectangle) {
                                o = new ezObjRectangle(parent.WorkSpace.activePage, p.PenColor, p.BGColor, p.TextColor,
                                         p.PenSize, parent.WorkSpace.activePage.ObjID, p.LineSD, p.str,true);
                            } else if (p.ObjEnum == ezObjEnum.circular) {
                                o = new ezObjCircular(parent.WorkSpace.activePage, p.PenColor, p.BGColor, p.TextColor,
                                         p.PenSize, parent.WorkSpace.activePage.ObjID, p.LineSD, p.str,true);
                            } else if (p.ObjEnum == ezObjEnum.diamond) {
                                o = new ezObjDiamond(parent.WorkSpace.activePage, p.PenColor, p.BGColor, p.TextColor,
                                         p.PenSize, parent.WorkSpace.activePage.ObjID, p.LineSD, p.str,true);
                            } else if (p.ObjEnum == p.ObjEnum.text) {
                                o = new ezObjText(parent.WorkSpace.activePage, p.PenColor, p.BGColor, p.TextColor,
                                         p.PenSize, parent.WorkSpace.activePage.ObjID, p.LineSD, p.str,true);
                            }
                            parent.WorkSpace.activePage.add(o, 0);
                            parent.AttributesToolBar.ObjList.addObj(parent.WorkSpace.activePage.ObjID);
                            parent.AttributesToolBar.ObjList.setActiveObj(parent.WorkSpace.activePage.ObjID);
                            o.setLocation(0,0);
                            o.setSize(Math.abs(p.Sp.x - p.Ep.x), Math.abs(p.Sp.y - p.Ep.y));
                            o.setXYwh();
                            parent.WorkSpace.activePage.Objs.add(o);
                            parent.WorkSpace.activePage.CopyObj = o;
                            parent.WorkSpace.activePage.Points.add(new ezObject(new Point(0, 0),
                                    new Point(Math.abs(p.Sp.x - p.Ep.x), Math.abs(p.Sp.y - p.Ep.y)), p.PenSize, p.PenColor,
                                    p.BGColor,p.TextColor,p.ObjEnum, parent.WorkSpace.activePage.ObjID++,p.LineSD,p.str));
                        }
                    }
                } catch (Throwable ee) {
                }
            }
        });
        
        DelM = new MenuItem("Delete(Delete)");
        DelM.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                parent.TopToolBar.DelBtn.doClick();
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
                    ezMenuBar.this.parent.setLanguage();
                } catch (IOException ex) {
                    Logger.getLogger(ezMenuBar.class.getName()).log(Level.SEVERE, null, ex);
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
                    ezMenuBar.this.parent.setLanguage();
                } catch (IOException ex) {
                    Logger.getLogger(ezMenuBar.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        
        this.add(FileMenu);
        this.add(EditMenu);
        this.add(PageMenu);
        this.add(LanguageMenu);
        FileMenu.add(NewM);
        FileMenu.add(CloseM);
        FileMenu.add(OpenM);
        FileMenu.add(SaveM);
        FileMenu.add(ExportFileMenu);
        ExportFileMenu.add(JpgM);
        ExportFileMenu.add(PngM);
        ExportFileMenu.add(GifM);
        ExportFileMenu.add(BmpM);
        EditMenu.add(UndoM);
        EditMenu.add(RedoM);
        EditMenu.add(CopyM);
        EditMenu.add(PasteM);
        EditMenu.add(DelM);
        PageMenu.add(AllPageMenu);
        PageMenu.add(CachingPageMenu);
        LanguageMenu.add(TW);
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
            parent.WorkSpace.activePage.MoveNode.HideNode();
            parent.WorkSpace.activePage.paint(img.getGraphics());
            try {
                ImageIO.write(img,S, new File(fileChooser.getDirectory() + fileChooser.getFile()));

            } catch (Exception ex) {
                System.out.println("panel not saved" + ex.getMessage());
            }
            if (parent.WorkSpace.activePage.activeObj != null) {
                parent.WorkSpace.activePage.MoveNode.ShowNode();
            }
        }
    }
}
