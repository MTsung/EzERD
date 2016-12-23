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
/**
 *
 * @author CMC
 */
public class ezPageSizePanel extends Panel{
    ezAttributesToolBar AtoolBar;   
    TextField PageW,PageH;
    Label LabelW,LabelH;
    ezPageSizePanel(ezAttributesToolBar p){
        AtoolBar=p;
        PageW = new TextField(5);
        PageH = new TextField(5);
        LabelW=new Label("Width:");
        LabelH=new Label("Height:");
        LabelW.setFont(new ezFont());
        LabelH.setFont(new ezFont());
        PageW.addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e) {
                    try{
                        if (Integer.valueOf(PageW.getText()) < 1){
                            PageH.setText("1");
                        }
                        if (Integer.valueOf(PageH.getText()) < 1){
                            PageH.setText("1");
                        }
                    } catch (Exception ex) {
                            PageW.setText(""+AtoolBar.parent.WorkSpace.activePage.getWidth());
                            PageH.setText(""+AtoolBar.parent.WorkSpace.activePage.getHeight());
                    }
                    AtoolBar.parent.WorkSpace.activePage.setPageSize(Integer.valueOf(PageW.getText())
                                                                    , Integer.valueOf(PageH.getText()));
                    AtoolBar.parent.MainWin.requestFocusInWindow();
                }
        });
        PageH.addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e) {
                    try{
                        if (Integer.valueOf(PageW.getText()) < 1){
                            PageH.setText("1");
                        }
                        if (Integer.valueOf(PageH.getText()) < 1){
                            PageH.setText("1");
                        }
                    } catch (Exception ex) {
                            PageW.setText(""+AtoolBar.parent.WorkSpace.activePage.getWidth());
                            PageH.setText(""+AtoolBar.parent.WorkSpace.activePage.getHeight());
                    }
                    AtoolBar.parent.WorkSpace.activePage.setPageSize(Integer.valueOf(PageW.getText())
                                                                    , Integer.valueOf(PageH.getText()));
                    AtoolBar.parent.MainWin.requestFocusInWindow();
                }
        });
        this.add(LabelW);
        this.add(PageW);
        this.add(LabelH);
        this.add(PageH);
    }
}
