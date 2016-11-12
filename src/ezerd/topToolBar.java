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
public class topToolBar extends Panel{
    ezERD parent;
    JButton NewPageBtn,ClosePageBtn,OpenBtn,SaveBtn,UndoBtn,RedoBtn;
    String ClosingMessage,ClosingMessage1,Mess;
    
    topToolBar(ezERD p) {
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
                parent.PageScrollPane.addPage(new page(parent), "NewPage(" + i++ + ").sss");
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
                            parent.WorkSpace.cloPage();
                    }else
                        parent.WorkSpace.cloPage();
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
                FileDialog fileChooser=new FileDialog(topToolBar.this.parent.MainWin, "Open", FileDialog.LOAD);
                fileChooser.setFile("*.sss");
                fileChooser.setVisible(true);
                if(fileChooser.getDirectory() != null && fileChooser.getFile() != null){
                    File selectedFile = new File(fileChooser.getDirectory() + fileChooser.getFile());
                    try {
                        String newLine = null;
                        BufferedReader br = new BufferedReader(new FileReader(selectedFile));
                        parent.CurPage=++parent.TotalPages;
                        parent.PageScrollPane.addPage(new page(parent),selectedFile.getName());
                        while((newLine=br.readLine()) != null){
                            if(newLine.startsWith("Arr,")){
                                String[] L = newLine.split(",");
                                parent.WorkSpace.activePage.ObjArrowXYs.add(new objArrowXY(null,null,Integer.parseInt(L[1]),Integer.parseInt(L[2])));
                            }else if(newLine.startsWith("pageSize,")){
                                String[] L = newLine.split(",");
                                parent.WorkSpace.activePage.setPageSize(Integer.parseInt(L[1]), Integer.parseInt(L[2]));
                            }else{
                                String[] L = newLine.split(",");
                                parent.WorkSpace.activePage.Points.add(new object(new Point(Integer.parseInt(L[0]),Integer.parseInt(L[1]))
                                                                        ,new Point(Integer.parseInt(L[2]),Integer.parseInt(L[3]))
                                                                        ,Float.parseFloat(L[4]),new Color(Integer.parseInt(L[5]))
                                                                        ,objEnum.valueOf(L[6]),Integer.parseInt(L[7])
                                                                        ));
                            }
                        }
                        parent.PageToolBar.Btns.elementAt(parent.PageToolBar.activeButton()).setToolTipText(fileChooser.getDirectory() + fileChooser.getFile());
                    } catch (IOException ex) {
                        Logger.getLogger(topToolBar.class.getName()).log(Level.SEVERE, null, ex);
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
                FileDialog fileChooser=new FileDialog(topToolBar.this.parent.MainWin, "Save", FileDialog.SAVE);
                fileChooser.setFile(parent.PageToolBar.Btns.elementAt(parent.PageToolBar.activeButton()).getText().replace("*",""));
                fileChooser.setVisible(true);
                if(fileChooser.getDirectory() != null && fileChooser.getFile() != null){
                    File selectedFile = new File(fileChooser.getDirectory() + fileChooser.getFile());
                    try {
                        Point P=parent.WorkSpace.activePage.getPageSize();
                        PrintWriter pw   = new PrintWriter(selectedFile);
                        pw.write("pageSize," + P.x + "," + P.y + "\r\n");
                        for(object p:parent.WorkSpace.activePage.Points){
                            pw.write(""+ p.Sp.x +","+ p.Sp.y +","+ p.Ep.x +","+ p.Ep.y + ","
                                    + p.PenSize + "," + p.PenColor.getRGB() + "," + p.ObjEnum +","+p.ObjID+"\r\n");
                        }
                        for(objArrowXY obja:parent.WorkSpace.activePage.ObjArrowXYs){
                            pw.write("Arr," + obja.SObjID + "," + obja.EObjID+"\r\n");
                        }
                        pw.close();
                        parent.PageToolBar.Btns.elementAt(parent.PageToolBar.activeButton()).setToolTipText(fileChooser.getDirectory() + fileChooser.getFile());
                    } catch (FileNotFoundException ex) {
                        Logger.getLogger(topToolBar.class.getName()).log(Level.SEVERE, null, ex);
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
                        int id=parent.WorkSpace.activePage.ObjPoints.elementAt(parent.WorkSpace.activePage.ObjPoints.size()-1).ID;
                        for (object o :parent.WorkSpace.activePage.Points) {
                            if (o.ObjID == id) {
                                parent.WorkSpace.activePage.ReObjPoints.add(new objPoint(o.Sp,o.Ep,id));
                                o.Sp = parent.WorkSpace.activePage.ObjPoints.elementAt(
                                                            parent.WorkSpace.activePage.ObjPoints.size() - 1).Sp;
                                o.Ep = parent.WorkSpace.activePage.ObjPoints.elementAt(
                                                            parent.WorkSpace.activePage.ObjPoints.size() - 1).Ep;
                            }
                        }
                        parent.WorkSpace.activePage.ObjPoints.remove(parent.WorkSpace.activePage.ObjPoints.size()-1);
                    }else if(temp>0){
                        for(int i=temp;i>0;i--){
                            parent.WorkSpace.activePage.RePoints.add(parent.WorkSpace.activePage.Points.elementAt(
                                    parent.WorkSpace.activePage.Points.size() - i));
                            parent.WorkSpace.activePage.Points.remove(parent.WorkSpace.activePage.Points.size() - i);
                        }
                    }else if(temp==-1){
                        parent.WorkSpace.activePage.ReObjArrowXYs.add(parent.WorkSpace.activePage.ObjArrowXYs.elementAt(
                                                                parent.WorkSpace.activePage.ObjArrowXYs.size()-1));
                        parent.WorkSpace.activePage.ObjArrowXYs.remove(parent.WorkSpace.activePage.ObjArrowXYs.size()-1);
                    }
                    
                    
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
                        int id=parent.WorkSpace.activePage.ReObjPoints.elementAt(parent.WorkSpace.activePage.ReObjPoints.size()-1).ID;
                        for (object o : topToolBar.this.parent.WorkSpace.activePage.Points) {
                            if (o.ObjID == id) {
                                parent.WorkSpace.activePage.ObjPoints.add(new objPoint(o.Sp,o.Ep,id));
                                o.Sp = parent.WorkSpace.activePage.ReObjPoints.elementAt(
                                                            parent.WorkSpace.activePage.ReObjPoints.size() - 1).Sp;
                                o.Ep = parent.WorkSpace.activePage.ReObjPoints.elementAt(
                                                            parent.WorkSpace.activePage.ReObjPoints.size() - 1).Ep;
                            }
                        }
                        parent.WorkSpace.activePage.ReObjPoints.remove(parent.WorkSpace.activePage.ReObjPoints.size()-1);
                    }else if(temp>0){
                        for(int i=temp;i>0;i--){
                            parent.WorkSpace.activePage.Points.add(parent.WorkSpace.activePage.RePoints.elementAt(
                                                            parent.WorkSpace.activePage.RePoints.size() - i));
                            parent.WorkSpace.activePage.RePoints.remove(parent.WorkSpace.activePage.RePoints.size() - i);
                        }
                    }else if(temp==-1){
                        parent.WorkSpace.activePage.ObjArrowXYs.add(parent.WorkSpace.activePage.ReObjArrowXYs.elementAt(
                                                                parent.WorkSpace.activePage.ReObjArrowXYs.size()-1));
                        parent.WorkSpace.activePage.ReObjArrowXYs.remove(parent.WorkSpace.activePage.ReObjArrowXYs.size()-1);
                    }
                    parent.WorkSpace.activePage.PaintObj=true;
                    parent.WorkSpace.activePage.ArrowPaint=true;
                    parent.WorkSpace.activePage.removeAll();
                    parent.AttributesToolBar.ObjList.reset();
                    parent.WorkSpace.activePage.repaint();
                }
                parent.MainWin.requestFocusInWindow();
            }
        });
        
        for(Component a:this.getComponents())/**/
            a.addKeyListener(new keyListener(parent));/**/
    }

}
