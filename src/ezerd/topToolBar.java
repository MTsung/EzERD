/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ezerd;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.event.*;

/**
 *
 * @author CMC
 */
public class topToolBar extends Panel{
    ezERD parent;
    
    JButton newPageBtn = new JButton(new ImageIcon("Neww.png"));
    JButton cloPageBtn = new JButton(new ImageIcon("Close.png"));
    JButton openBtn = new JButton(new ImageIcon("Open.png"));
    JButton saveBtn = new JButton(new ImageIcon("Save.png"));
    JButton undoBtn = new JButton(new ImageIcon("Undo.png"));
    JButton redoBtn = new JButton(new ImageIcon("Redo.png"));
    
    
    topToolBar(ezERD p){
        super();
        parent=p;
        this.setLayout(new FlowLayout(FlowLayout.LEFT));
        this.setBackground(new Color(205,205,205));
        
        newPageBtn.setBackground(this.getBackground());
        newPageBtn.setToolTipText("New Page(Ctrl+N)");
        newPageBtn.setActionCommand("New");
        newPageBtn.setBorder(null);
        this.add(newPageBtn);
        newPageBtn.addActionListener(new ActionListener(){
            int i=1;
            @Override
            public void actionPerformed(ActionEvent e) {
                parent.CurPage=++parent.TotalPages;
                parent.WorkSpace.addPage(new page(parent), "未命名(" + i++ + ").sss");
            }
        });
        
        cloPageBtn.setBackground(this.getBackground());
        cloPageBtn.setToolTipText("Close Page(Ctrl+W)");
        cloPageBtn.setActionCommand("Close");
        cloPageBtn.setBorder(null);
        this.add(cloPageBtn);
        cloPageBtn.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println(parent.TotalPages);
                if(parent.TotalPages!=1)
                    if(parent.PageToolBar.Btns.elementAt(parent.PageToolBar.activeButton()).getText().endsWith("*")){
                        if(0==JOptionPane.showConfirmDialog(null, "尚未儲存，是否關閉？","Message",2 ) )
                            parent.WorkSpace.cloPage();
                    }else
                        parent.WorkSpace.cloPage();
                        
            }
        });
        
        openBtn.setBackground(this.getBackground());
        openBtn.setToolTipText("Open File(Ctrl+O)");
        openBtn.setActionCommand("New");
        openBtn.setBorder(null);
        this.add(openBtn);
        openBtn.addActionListener(new ActionListener(){
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
        
        saveBtn.setBackground(this.getBackground());
        saveBtn.setToolTipText("Save File(Ctrl+S)");
        saveBtn.setActionCommand("New");
        saveBtn.setBorder(null);
        this.add(saveBtn);
        saveBtn.addActionListener(new ActionListener(){
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
                                    + p.PanSize + "," + p.PanColor.getRGB() +"\r\n");
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
        
        undoBtn.setBackground(this.getBackground());
        undoBtn.setToolTipText("Undo(Ctrl+Z)");
        undoBtn.setActionCommand("Undo");
        undoBtn.setBorder(null);
        this.add(undoBtn);
        undoBtn.addActionListener(new ActionListener(){
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
        
        redoBtn.setBackground(this.getBackground());
        redoBtn.setToolTipText("Redo(Ctrl+Y)");
        redoBtn.setActionCommand("Redo");
        redoBtn.setBorder(null);
        this.add(redoBtn);
        redoBtn.addActionListener(new ActionListener(){
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
        /*
        
        JSlider slider=new JSlider(1,50);
        slider.setMajorTickSpacing(5);
        slider.setPaintTicks(true);
        slider.setValue(1);
        slider.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent event) {
                parent.Ws.activePage.PanSize=slider.getValue();
            }
        });
        this.add(slider);*/
    }

}
