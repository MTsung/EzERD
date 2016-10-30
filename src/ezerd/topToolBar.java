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
    
    topToolBar(ezERD p) {
        super();
        parent=p;
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
                parent.WorkSpace.addPage(new page(parent), "NewPage(" + i++ + ").sss");
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
                        if(0==JOptionPane.showConfirmDialog(null, "File " + 
                                                                parent.PageToolBar.Btns.elementAt(parent.PageToolBar.activeButton()).getText().replace("*","") 
                                                                + " is modified. Close？","Message",2 ) )
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
                        int i=0;
                        BufferedReader br = new BufferedReader(new FileReader(selectedFile));
                        parent.CurPage=++parent.TotalPages;
                        parent.WorkSpace.addPage(new page(parent),selectedFile.getName());
                        while((newLine=br.readLine()) != null){
                            String[] L = newLine.split(",");
                            parent.WorkSpace.activePage.Points.add(new points(new Point(Integer.parseInt(L[0]),Integer.parseInt(L[1]))
                                                                    ,new Point(Integer.parseInt(L[2]),Integer.parseInt(L[3]))
                                                                    ,Float.parseFloat(L[4]),new Color(Integer.parseInt(L[5]))
                                                                    ));
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
                        PrintWriter pw   = new PrintWriter(selectedFile);
                        for(points p:parent.WorkSpace.activePage.Points){
                            pw.write(""+ p.Sp.x +","+ p.Sp.y +","+ p.Ep.x +","+ p.Ep.y + ","
                                    + p.PenSize + "," + p.PenColor.getRGB() +"\r\n");
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
                    for(int i=temp;i>0;i--){
                        parent.WorkSpace.activePage.RePoints.add(parent.WorkSpace.activePage.Points.elementAt(parent.WorkSpace.activePage.Points.size()-i));
                        parent.WorkSpace.activePage.Points.remove(parent.WorkSpace.activePage.Points.size()-i);
                    }
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
                    for(int i=temp;i>0;i--){
                        parent.WorkSpace.activePage.Points.add(parent.WorkSpace.activePage.RePoints.elementAt(parent.WorkSpace.activePage.RePoints.size()-i));
                        parent.WorkSpace.activePage.RePoints.remove(parent.WorkSpace.activePage.RePoints.size()-i);
                    }
                    parent.WorkSpace.activePage.repaint();
                }
                parent.MainWin.requestFocusInWindow();
            }
        });
        
        for(Component a:this.getComponents())/**/
            a.addKeyListener(new keyListener(parent));/**/
    }

}
