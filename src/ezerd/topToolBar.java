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
import javax.swing.filechooser.*;

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
    /*
    JButton newPageBtn = new JButton("New");
    JButton cloPageBtn = new JButton("Close");
    JButton undoBtn = new JButton("Undo");
    JButton redoBtn = new JButton("Redo");*/
    
    topToolBar(ezERD p){
        super();
        parent=p;
        this.setLayout(new FlowLayout(FlowLayout.LEFT));
        this.setBackground(new Color(205,205,205));
        //newPageBtn = new JButton("New",new ImageIcon("new.png"));
        //newPageBtn.setBorder(null);
        //newPageBtn.setBackground(Color.WHITE);
        newPageBtn.setBackground(this.getBackground());
        newPageBtn.setToolTipText("New Page(Ctrl+N)");
        newPageBtn.setActionCommand("New");
        newPageBtn.setBorder(null);
        this.add(newPageBtn);
        newPageBtn.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                String name=JOptionPane.showInputDialog("檔名");
                if(name != null){
                    parent.totalPages++;
                    parent.Mb.updateMessage();
                    parent.Ws.addPage(new page(parent), "".equals(name) ? "未命名.sss" : name+".sss");
                }
            }
        });
        
        cloPageBtn.setBackground(Color.WHITE);
        cloPageBtn.setBackground(this.getBackground());
        cloPageBtn.setToolTipText("Close Page(Ctrl+W)");
        cloPageBtn.setActionCommand("Close");
        cloPageBtn.setBorder(null);
        this.add(cloPageBtn);
        cloPageBtn.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                if(parent.totalPages!=1)
                    if(0==JOptionPane.showConfirmDialog(null, "是否關閉","Message",2 ) )
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
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setFileFilter(new FileNameExtensionFilter("Page(*.sss)", "sss"));
                int returnValue = fileChooser.showOpenDialog(null);
                if (returnValue == JFileChooser.APPROVE_OPTION)
                { 
                    File selectedFile = fileChooser.getSelectedFile();
                    try {
                        String newLine = null;
                        Point Sp = null,Ep = null;
                        int i=0;
                        BufferedReader br = new BufferedReader(new FileReader(selectedFile));
                        parent.Ws.addPage(new page(parent),selectedFile.getName());
                        while((newLine=br.readLine()) != null){
                            //System.out.println(newLine);
                            String[] L = newLine.split(",");
                            parent.Ws.activePage.Points.add(new points(new Point(Integer.parseInt(L[0]),Integer.parseInt(L[1]))
                                                                    ,new Point(Integer.parseInt(L[2]),Integer.parseInt(L[3]))));
                        }
                         parent.Ws.activePage.repaint();
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
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setSelectedFile(new File(parent.Ptb.Btns.elementAt(parent.Ptb.activeButton()).getText()));
                int n = fileChooser.showSaveDialog(null);
                if(n==JFileChooser.APPROVE_OPTION){
                    String Str=fileChooser.getSelectedFile().getAbsolutePath();
                    try {
                        PrintWriter pw   = new PrintWriter(new File(Str));
                        for(points p:parent.Ws.activePage.Points){
                            pw.write(""+ p.Sp.x +","+ p.Sp.y +","+ p.Ep.x +","+ p.Ep.y +"\r\n");
                        }
                        pw.close();
                    } catch (FileNotFoundException ex) {
                        Logger.getLogger(topToolBar.class.getName()).log(Level.SEVERE, null, ex);
                    } 
                    parent.Ptb.Btns.elementAt(parent.Ptb.activeButton()).setText(fileChooser.getSelectedFile().getName());
                }
            }
        });
        
        undoBtn.setBackground(Color.WHITE);
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
            }
        });
        
        redoBtn.setBackground(Color.WHITE);
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
