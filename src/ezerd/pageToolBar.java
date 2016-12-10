/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ezerd;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.util.Vector;

/**
 *
 * @author CMC
 */
public class pageToolBar extends Panel{
    workSpace WorkSpace;
    Vector<JButton> Btns=new Vector<JButton>();
    Vector<Boolean> BtnJ=new Vector<Boolean>();
    Color BtnC=new Color(200,255,255);
    int BtnSum=1,i;
    
    pageToolBar(workSpace Ws){
        super();
        WorkSpace=Ws;
        this.setLayout(new FlowLayout(FlowLayout.LEFT));
        this.setBackground(Color.LIGHT_GRAY);
    }
    
    void addBtton(int n,String s){    
        Btns.add(new JButton(s));   
        Btns.elementAt(n).setToolTipText(s);
        BtnJ.add(true);
        
        BtnSum++;
        resetButtonSize();
        Btns.elementAt(n).setOpaque(true);
        Btns.elementAt(n).setBorderPainted(false);
        Btns.elementAt(n).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(WorkSpace.parent.CurPage != n){
                    WorkSpace.parent.CurPage = n;
                    updatePage(n);
                    activeButtonColor(n);
                    resetButtonSize();
                    WorkSpace.activePage.removeAll();
                    WorkSpace.parent.ToolBar.ChoBtn.doClick();
                }
            }
        });
        WorkSpace.parent.CurPage = n;
        activeButtonColor(n);
        this.add(Btns.elementAt(n));
        WorkSpace.parent.AttributesToolBar.ObjList.reset();
        Btns.elementAt(n).addKeyListener(new keyListener(WorkSpace.parent));/**/
        allPage();
        resetPageMenu();
        for (Component b : WorkSpace.parent.ToolBar.getComponents()) {
            b.setBackground(WorkSpace.parent.ToolBar.getBackground());
        }
    }
    
    void delButton(){
        int n=activeButton(),i;
        BtnSum--;
        resetButtonSize();
        this.remove(Btns.elementAt(n));
        BtnJ.set(n, false);
        for(i=0;!BtnJ.elementAt(i);i++);
        if(n<i)
            while(!BtnJ.elementAt(++n));
        else 
            while(!BtnJ.elementAt(--n));
        updatePage(n);
        activeButtonColor(n);
        this.repaint();
        WorkSpace.parent.CurPage=n;
        WorkSpace.parent.TotalPages--;
        allPage();
        resetPageMenu();
    }
    
    void updatePage(int n){
        page ep =WorkSpace.Pages.elementAt(n);
        WorkSpace.parent.PageScrollPane.TempPanel.removeAll();
        if(WorkSpace.activePage!=null){
            WorkSpace.parent.PageScrollPane.TempPanel.remove(WorkSpace.activePage);
        }
        WorkSpace.parent.PageScrollPane.TempPanel.add(ep, BorderLayout.CENTER);
        WorkSpace.validate();
        WorkSpace.activePage=ep;
        WorkSpace.parent.CurPage=n;
        WorkSpace.parent.MainWin.setTitle("EzERD-" + WorkSpace.parent.PageToolBar.Btns.elementAt(
                                                     WorkSpace.parent.PageToolBar.activeButton()).getText());
        WorkSpace.activePage.PaintObj = true;
        WorkSpace.parent.AttributesToolBar.ObjList.reset();
        WorkSpace.activePage.removeAll();
        WorkSpace.activePage.repaint();
    }
    void resetButtonSize(){
        //System.out.println(this.getWidth());
        int BtnWidth=(this.getWidth()-5*BtnSum)/BtnSum;
        for(JButton Btn:Btns)
            Btn.setPreferredSize(new Dimension(BtnWidth > 200 || BtnWidth < 0 ? 200 : BtnWidth ,25));
        this.revalidate();
    }
    
    int activeButton(){
        int i;
        for(i=0;Btns.elementAt(i).getBackground()!=BtnC;i++);
        return i;
    }
    
    void activeButtonColor(int n){
        for(JButton B:Btns)
            B.setBackground(Color.WHITE);
        Btns.elementAt(n).setBackground(BtnC);
    }
    void allPage(){
        System.out.print("All Page:");
        for(int i=0;i<BtnJ.size();i++)
            if(BtnJ.elementAt(i))
                System.out.print(i+" ");
        System.out.println();
    }
    void resetPageMenu(){
        WorkSpace.parent.MainWin.MenuBar.CachingPageMenu.removeAll();
        WorkSpace.parent.MainWin.MenuBar.AllPageMenu.removeAll();
        for(i=0;i<BtnJ.size();i++){
            if(!BtnJ.elementAt(i)){
                pageMenuItem temp=new pageMenuItem(Btns.elementAt(i).getText(),i);
                temp.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        WorkSpace.parent.PageToolBar.removeAll();
                        BtnJ.set(temp.BtnN, true);
                        for (int j = 0; j < Btns.size(); j++) {
                            if (BtnJ.elementAt(j)) {
                                WorkSpace.parent.PageToolBar.add(Btns.elementAt(j));
                            }
                        }
                        Btns.elementAt(temp.BtnN).doClick();
                        WorkSpace.parent.TotalPages++;
                        BtnSum++;
                        resetButtonSize();
                        allPage();
                        pageToolBar.this.resetPageMenu();
                    }
                });
                WorkSpace.parent.MainWin.MenuBar.CachingPageMenu.add(temp);
            }else{
                pageMenuItem temp=new pageMenuItem(Btns.elementAt(i).getText(),i);
                temp.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        Btns.elementAt(temp.BtnN).doClick();
                    }
                });
                WorkSpace.parent.MainWin.MenuBar.AllPageMenu.add(temp);
            }           
        } 
    }
    
}
