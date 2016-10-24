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
    
    JButton newPageBtn = new JButton(new ImageIcon("New.png"));
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
                parent.totalPages++;
                parent.Ws.addPage(new page(parent), "未命名(" + i++ + ").sss");
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
                System.out.println(parent.totalPages);
                if(parent.totalPages!=1)
                    if(parent.Ptb.Btns.elementAt(parent.Ptb.activeButton()).getText().endsWith("*")){
                        if(0==JOptionPane.showConfirmDialog(null, "尚未儲存，是否關閉？","Message",2 ) )
                            parent.Ws.cloPage();
                    }else
                        parent.Ws.cloPage();
                        
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
                FileDialog fileChooser=new FileDialog(topToolBar.this.parent.Win, "Open", FileDialog.LOAD);
                fileChooser.setFile("*.sss");
                fileChooser.setVisible(true);
                if(fileChooser.getDirectory() != null && fileChooser.getFile() != null){
                    File selectedFile = new File(fileChooser.getDirectory() + fileChooser.getFile());
                    try {
                        String newLine = null;
                        int i=0;
                        BufferedReader br = new BufferedReader(new FileReader(selectedFile));
                        parent.totalPages++;
                        parent.Ws.addPage(new page(parent),selectedFile.getName());
                        while((newLine=br.readLine()) != null){
                            String[] L = newLine.split(",");
                            parent.Ws.activePage.Points.add(new points(new Point(Integer.parseInt(L[0]),Integer.parseInt(L[1]))
                                                                    ,new Point(Integer.parseInt(L[2]),Integer.parseInt(L[3]))
                                                                    ,Float.parseFloat(L[4])
                                                                    ));
                        }
                        parent.Ptb.Btns.elementAt(parent.Ptb.activeButton()).setToolTipText(fileChooser.getDirectory() + fileChooser.getFile());
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
                FileDialog fileChooser=new FileDialog(topToolBar.this.parent.Win, "Save", FileDialog.SAVE);
                fileChooser.setFile(parent.Ptb.Btns.elementAt(parent.Ptb.activeButton()).getText().replace("*",""));
                fileChooser.setVisible(true);
                if(fileChooser.getDirectory() != null && fileChooser.getFile() != null){
                    File selectedFile = new File(fileChooser.getDirectory() + fileChooser.getFile());
                    try {
                        PrintWriter pw   = new PrintWriter(selectedFile);
                        for(points p:parent.Ws.activePage.Points){
                            pw.write(""+ p.Sp.x +","+ p.Sp.y +","+ p.Ep.x +","+ p.Ep.y + "," + p.PanSize + "\r\n");
                        }
                        pw.close();
                        parent.Ptb.Btns.elementAt(parent.Ptb.activeButton()).setToolTipText(fileChooser.getDirectory() + fileChooser.getFile());
                    } catch (FileNotFoundException ex) {
                        Logger.getLogger(topToolBar.class.getName()).log(Level.SEVERE, null, ex);
                    } 
                    parent.Ptb.Btns.elementAt(parent.Ptb.activeButton()).setText(selectedFile.getName());
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
                if(parent.Ws.activePage.undos.size()!=0){
                    int temp=parent.Ws.activePage.undos.pop();
                    parent.Ws.activePage.redos.add(temp);
                    for(int i=temp;i>0;i--){
                        parent.Ws.activePage.RePoints.add(parent.Ws.activePage.Points.elementAt(parent.Ws.activePage.Points.size()-i));
                        parent.Ws.activePage.Points.remove(parent.Ws.activePage.Points.size()-i);
                    }
                    parent.Ws.activePage.repaint();
                }
                parent.Win.requestFocusInWindow();
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
                if(parent.Ws.activePage.redos.size()!=0){
                    int temp=parent.Ws.activePage.redos.pop();
                    parent.Ws.activePage.undos.add(temp);
                    for(int i=temp;i>0;i--){
                        parent.Ws.activePage.Points.add(parent.Ws.activePage.RePoints.elementAt(parent.Ws.activePage.RePoints.size()-i));
                        parent.Ws.activePage.RePoints.remove(parent.Ws.activePage.RePoints.size()-i);
                    }
                    parent.Ws.activePage.repaint();
                }
                parent.Win.requestFocusInWindow();
            }
        });
       
        parent.Win.setMenuBar(new menuBar(parent));
        
        
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
