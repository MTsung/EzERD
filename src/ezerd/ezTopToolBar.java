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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.*;


/**
 *
 * @author CMC
 */
public class ezTopToolBar extends Panel{
    ezERD parent;
    JButton NewPageBtn,ClosePageBtn,OpenBtn,SaveBtn,UndoBtn,RedoBtn,DelBtn,CopyBtn,PasteBtn;
    String ClosingMessage,ClosingMessage1,Mess;
    
    ezTopToolBar(ezERD p) {
        super();
        parent=p;
        Mess="Message";
        ClosingMessage="File";
        ClosingMessage1=" is modified. Close？";
        this.setLayout(new FlowLayout(FlowLayout.LEFT));
        this.setBackground(new Color(205,205,205));
        try{
            NewPageBtn = new JButton(new ImageIcon(ImageIO.read(this.getClass().getResourceAsStream("icon/Neww.png"))));
            ClosePageBtn = new JButton(new ImageIcon(ImageIO.read(this.getClass().getResourceAsStream("icon/Close.png"))));
            OpenBtn = new JButton(new ImageIcon(ImageIO.read(this.getClass().getResourceAsStream("icon/Open.png"))));
            SaveBtn = new JButton(new ImageIcon(ImageIO.read(this.getClass().getResourceAsStream("icon/Save.png"))));
            UndoBtn = new JButton(new ImageIcon(ImageIO.read(this.getClass().getResourceAsStream("icon/Undo.png"))));
            RedoBtn = new JButton(new ImageIcon(ImageIO.read(this.getClass().getResourceAsStream("icon/Redo.png"))));
            CopyBtn = new JButton(new ImageIcon(ImageIO.read(this.getClass().getResourceAsStream("icon/Copy.png"))));
            PasteBtn = new JButton(new ImageIcon(ImageIO.read(this.getClass().getResourceAsStream("icon/Paste.png"))));
            DelBtn = new JButton(new ImageIcon(ImageIO.read(this.getClass().getResourceAsStream("icon/Delete.png"))));
        }catch (IOException ex) {
            System.err.println(ex.getMessage());
        }
        NewPageBtn.setBackground(this.getBackground());
        NewPageBtn.setToolTipText("New Page(Ctrl+N)");
        NewPageBtn.setActionCommand("New");
        NewPageBtn.setBorder(null);
        this.add(NewPageBtn);
        NewPageBtn.addActionListener(new ActionListener(){
            int i=1;
            @Override
            public void actionPerformed(ActionEvent e) {
                parent.CurPage=++parent.TotalPages;
                parent.PageScrollPane.addPage(new ezPage(parent), "NewPage(" + i++ + ").sss");
            }
        });
        
        ClosePageBtn.setBackground(this.getBackground());
        ClosePageBtn.setToolTipText("Close Page(Ctrl+W)");
        ClosePageBtn.setActionCommand("Close");
        ClosePageBtn.setBorder(null);
        this.add(ClosePageBtn);
        ClosePageBtn.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                if(parent.TotalPages!=1)
                    if(parent.PageToolBar.Btns.elementAt(parent.PageToolBar.activeButton()).getText().endsWith("*")){
                        if(0==JOptionPane.showConfirmDialog(null, ClosingMessage + 
                                                                parent.PageToolBar.Btns.elementAt(parent.PageToolBar.activeButton()).getText().replace("*","") 
                                                                + ClosingMessage1,Mess,2,JOptionPane.PLAIN_MESSAGE ) )
                            parent.PageScrollPane.closePage();
                    }else
                        parent.PageScrollPane.closePage();
            }
        });
        
        OpenBtn.setBackground(this.getBackground());
        OpenBtn.setToolTipText("Open File(Ctrl+O)");
        OpenBtn.setActionCommand("New");
        OpenBtn.setBorder(null);
        this.add(OpenBtn);
        OpenBtn.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                FileDialog fileChooser=new FileDialog(ezTopToolBar.this.parent.MainWin, "Open", FileDialog.LOAD);
                fileChooser.setFile("*.sss");
                fileChooser.setVisible(true);
                if(fileChooser.getDirectory() != null && fileChooser.getFile() != null){
                    File selectedFile = new File(fileChooser.getDirectory() + fileChooser.getFile());
                    try {
                        String newLine = null;
                        BufferedReader br = new BufferedReader(new FileReader(selectedFile));
                        parent.CurPage=++parent.TotalPages;
                        parent.PageScrollPane.addPage(new ezPage(parent),selectedFile.getName());
                        while((newLine=br.readLine()) != null){
                            if(newLine.startsWith("Arr,")){
                                String[] L = newLine.split(",");
                                parent.WorkSpace.activePage.ObjArrowXYs.add(new ezObjArrowXY(null,null,Integer.parseInt(L[1])
                                                        ,Integer.parseInt(L[2]),new Color(Integer.parseInt(L[3])),Integer.parseInt(L[4])));
                            }else if(newLine.startsWith("pageSize,")){
                                String[] L = newLine.split(",");
                                parent.WorkSpace.activePage.setPageSize(Integer.parseInt(L[1]), Integer.parseInt(L[2]));
                            }else{
                                String[] L = newLine.split(",");
                                parent.WorkSpace.activePage.Points.add(new ezObject(new Point(Integer.parseInt(L[0]),Integer.parseInt(L[1]))
                                                                        ,new Point(Integer.parseInt(L[2]),Integer.parseInt(L[3]))
                                                                        ,Float.parseFloat(L[4]),new Color(Integer.parseInt(L[5]))
                                                                        ,new Color(Integer.parseInt(L[6])),new Color(Integer.parseInt(L[7]))
                                                                        ,ezObjEnum.valueOf(L[8]),Integer.parseInt(L[9]),Integer.parseInt(L[10])
                                                                        ,L[11],Integer.parseInt(L[12]),Integer.parseInt(L[13]),Integer.parseInt(L[14])
                                                                        ,Integer.parseInt(L[15]),Integer.parseInt(L[16]),Integer.parseInt(L[17])
                                                                        ,true));
                            }
                        }
                        parent.PageToolBar.Btns.elementAt(parent.PageToolBar.activeButton()).setToolTipText(fileChooser.getDirectory() + fileChooser.getFile());
                    } catch (IOException ex) {
                        Logger.getLogger(ezTopToolBar.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        });
        
        SaveBtn.setBackground(this.getBackground());
        SaveBtn.setToolTipText("Save File(Ctrl+S)");
        SaveBtn.setActionCommand("New");
        SaveBtn.setBorder(null);
        this.add(SaveBtn);
        SaveBtn.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                FileDialog fileChooser=new FileDialog(ezTopToolBar.this.parent.MainWin, "Save", FileDialog.SAVE);
                fileChooser.setFile(parent.PageToolBar.Btns.elementAt(parent.PageToolBar.activeButton()).getText().replace("*",""));
                fileChooser.setVisible(true);
                if(fileChooser.getDirectory() != null && fileChooser.getFile() != null){
                    File selectedFile = new File(fileChooser.getDirectory() + fileChooser.getFile());
                    try {
                        Point P=parent.WorkSpace.activePage.getPageSize();
                        PrintWriter pw   = new PrintWriter(selectedFile);
                        pw.write("pageSize," + P.x + "," + P.y + "\r\n");
                        for(ezObject p:parent.WorkSpace.activePage.Points){
                            if(p.Visible){
                                pw.write("" + p.Sp.x + "," + p.Sp.y + "," + p.Ep.x + "," + p.Ep.y + "," + p.PenSize
                                        + "," + p.PenColor.getRGB() + "," + p.BGColor.getRGB() + "," + p.TextColor.getRGB()
                                        + "," + p.ObjEnum + "," + p.ObjID + "," + p.LineSD
                                        + "," + p.str + "," + p.Angle + "," + p.Tra
                                        + "," + p.x + "," + p.y + "," + p.w + "," + p.h + "\r\n");
                            }
                        }
                        for(ezObjArrowXY obja:parent.WorkSpace.activePage.ObjArrowXYs){
                            pw.write("Arr," + obja.SObjID + "," + obja.EObjID + "," + obja.ArrowColor.getRGB() +
                                    "," + obja.Solid +"\r\n");
                        }
                        pw.close();
                        parent.PageToolBar.Btns.elementAt(parent.PageToolBar.activeButton()).setToolTipText(fileChooser.getDirectory() + fileChooser.getFile());
                    } catch (FileNotFoundException ex) {
                        Logger.getLogger(ezTopToolBar.class.getName()).log(Level.SEVERE, null, ex);
                    } 
                    parent.PageToolBar.Btns.elementAt(parent.PageToolBar.activeButton()).setText(selectedFile.getName());
                }
            }
        });
        
        UndoBtn.setBackground(this.getBackground());
        UndoBtn.setToolTipText("Undo(Ctrl+Z)");
        UndoBtn.setActionCommand("Undo");
        UndoBtn.setBorder(null);
        this.add(UndoBtn);
        UndoBtn.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                if(parent.WorkSpace.activePage.undos.size()!=0){
                    int temp=parent.WorkSpace.activePage.undos.pop();
                    parent.WorkSpace.activePage.redos.add(temp);
                    if(temp==0){
                        int id=parent.WorkSpace.activePage.UndoPoints.elementAt(parent.WorkSpace.activePage.UndoPoints.size()-1).ObjID;
                        for (ezObject o :parent.WorkSpace.activePage.Points) {
                            if (o.ObjID == id) {
                                parent.WorkSpace.activePage.RedoPoints.add(new ezObject(o));
                                o.move(parent.WorkSpace.activePage.UndoPoints.elementAt(
                                                            parent.WorkSpace.activePage.UndoPoints.size() - 1));
                            }
                        }
                        parent.WorkSpace.activePage.UndoPoints.remove(parent.WorkSpace.activePage.UndoPoints.size()-1);
                    }else if(temp>0){
                        for(int i=temp;i>0;i--){
                            parent.WorkSpace.activePage.RedoPoints.add(parent.WorkSpace.activePage.Points.elementAt(
                                    parent.WorkSpace.activePage.Points.size() - i));
                            parent.WorkSpace.activePage.Points.remove(parent.WorkSpace.activePage.Points.size() - i);
                        }
                    }else if(temp==-1){
                        parent.WorkSpace.activePage.ReObjArrowXYs.add(parent.WorkSpace.activePage.ObjArrowXYs.elementAt(
                                                                parent.WorkSpace.activePage.ObjArrowXYs.size()-1));
                        parent.WorkSpace.activePage.ObjArrowXYs.remove(parent.WorkSpace.activePage.ObjArrowXYs.size()-1);
                    }
                    
                    parent.WorkSpace.activePage.setActiveObj(null);
                    parent.WorkSpace.activePage.PaintObj=true;
                    parent.WorkSpace.activePage.ArrowPaint=true;
                    parent.WorkSpace.activePage.removeAll();
                    parent.AttributesToolBar.ObjList.reset();
                    parent.WorkSpace.activePage.repaint();
                }
                parent.MainWin.requestFocusInWindow();
            }
        });
        
        RedoBtn.setBackground(this.getBackground());
        RedoBtn.setToolTipText("Redo(Ctrl+Y)");
        RedoBtn.setActionCommand("Redo");
        RedoBtn.setBorder(null);
        this.add(RedoBtn);
        RedoBtn.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                if(parent.WorkSpace.activePage.redos.size()!=0){
                    int temp=parent.WorkSpace.activePage.redos.pop();
                    parent.WorkSpace.activePage.undos.add(temp);
                    if(temp==0){
                        int id=parent.WorkSpace.activePage.RedoPoints.elementAt(parent.WorkSpace.activePage.RedoPoints.size()-1).ObjID;
                        for (ezObject o : parent.WorkSpace.activePage.Points) {
                            if (o.ObjID == id) {
                                parent.WorkSpace.activePage.UndoPoints.add(new ezObject(o));
                                o.move(parent.WorkSpace.activePage.RedoPoints.elementAt(
                                                            parent.WorkSpace.activePage.RedoPoints.size() - 1));
                            }
                        }
                        parent.WorkSpace.activePage.RedoPoints.remove(parent.WorkSpace.activePage.RedoPoints.size()-1);
                    }else if(temp>0){
                        for(int i=temp;i>0;i--){
                            parent.WorkSpace.activePage.Points.add(parent.WorkSpace.activePage.RedoPoints.elementAt(
                                                            parent.WorkSpace.activePage.RedoPoints.size() - i));
                            parent.WorkSpace.activePage.RedoPoints.remove(parent.WorkSpace.activePage.RedoPoints.size() - i);
                        }
                    }else if(temp==-1){
                        parent.WorkSpace.activePage.ObjArrowXYs.add(parent.WorkSpace.activePage.ReObjArrowXYs.elementAt(
                                                                parent.WorkSpace.activePage.ReObjArrowXYs.size()-1));
                        parent.WorkSpace.activePage.ReObjArrowXYs.remove(parent.WorkSpace.activePage.ReObjArrowXYs.size()-1);
                    }
                    parent.WorkSpace.activePage.setActiveObj(null);
                    parent.WorkSpace.activePage.PaintObj=true;
                    parent.WorkSpace.activePage.ArrowPaint=true;
                    parent.WorkSpace.activePage.removeAll();
                    parent.AttributesToolBar.ObjList.reset();
                    parent.WorkSpace.activePage.repaint();
                }
                parent.MainWin.requestFocusInWindow();
            }
        });
        
        CopyBtn.setBackground(this.getBackground());
        CopyBtn.setToolTipText("Copy(Ctrl+C)");
        CopyBtn.setActionCommand("Copy");
        CopyBtn.setBorder(null);
        this.add(CopyBtn);
        CopyBtn.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                parent.WorkSpace.activePage.CopyObj=parent.WorkSpace.activePage.activeObj;
                parent.WorkSpace.activePage.repaint();
                parent.MainWin.requestFocusInWindow();
            }
        });
        
        PasteBtn.setBackground(this.getBackground());
        PasteBtn.setToolTipText("Paste(Ctrl+V)");
        PasteBtn.setActionCommand("Paste");
        PasteBtn.setBorder(null);
        this.add(PasteBtn);
        PasteBtn.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                ezObj o = null;
                try {
                    for (ezObject p : parent.WorkSpace.activePage.Points) {
                        if (p.ObjID == parent.WorkSpace.activePage.CopyObj.ID) {
                            parent.WorkSpace.activePage.undos.add(1);
                            parent.WorkSpace.activePage.RedoPoints.removeAllElements();
                            parent.WorkSpace.activePage.redos.removeAllElements();
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
                            parent.WorkSpace.activePage.Objs.add(o);
                            parent.WorkSpace.activePage.setActiveObj(o);
                            parent.WorkSpace.activePage.CopyObj = o;
                            parent.WorkSpace.activePage.Points.add(new ezObject(new Point(0, 0),
                                    new Point(Math.abs(p.Sp.x - p.Ep.x), Math.abs(p.Sp.y - p.Ep.y)), p.PenSize, p.PenColor,
                                    p.BGColor,p.TextColor,p.ObjEnum, parent.WorkSpace.activePage.ObjID++,p.LineSD,p.str
                                    ,p.Angle,p.Tra,p.x,p.y,p.w,p.h,true));
                            o.setArr(p);
                            o.setObjLocation(0, 0,false);
                            o.setObjSize(Math.abs(p.Sp.x - p.Ep.x), Math.abs(p.Sp.y - p.Ep.y),false);
                            parent.WorkSpace.activePage.PaintObj = true;
                            parent.WorkSpace.activePage.ArrowPaint = true;
                            parent.WorkSpace.activePage.removeAll();
                            parent.AttributesToolBar.ObjList.reset();
                            parent.WorkSpace.activePage.repaint();
                        }
                    }
                } catch (Throwable ee) {
                }
                parent.MainWin.requestFocusInWindow();
            }
        });
        
        DelBtn.setBackground(this.getBackground());
        DelBtn.setToolTipText("Delete(Delete)");
        DelBtn.setActionCommand("Delete");
        DelBtn.setBorder(null);
        this.add(DelBtn);
        DelBtn.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                parent.WorkSpace.activePage.activeObj.setObjVisible(false,true);
                parent.WorkSpace.activePage.setActiveObj(null);
                parent.WorkSpace.activePage.PaintObj = true;
                parent.WorkSpace.activePage.ArrowPaint = true;
                parent.WorkSpace.activePage.removeAll();
                parent.AttributesToolBar.ObjList.reset();
                parent.WorkSpace.activePage.repaint();
                parent.MainWin.requestFocusInWindow();
            }
        });
        
        for(Component a:this.getComponents())/**/
            a.addKeyListener(new ezKeyListener(parent));/**/
    }

}
