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
public class rightClickMenu extends PopupMenu{
    ezERD parent;
    MenuItem UndoMenuItem,RedoMenuItem,CopyMenuItem,PasteMenuItem;
    Point XY;
    rightClickMenu(ezERD p){
        parent=p;
        UndoMenuItem = new MenuItem("Undo(Ctrl+Z)");
        UndoMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                parent.TopToolBar.UndoBtn.doClick();
            }
        });
        RedoMenuItem =new MenuItem("Rndo(Ctrl+Y)");
        RedoMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                parent.TopToolBar.RedoBtn.doClick();
            }
        });
        CopyMenuItem = new MenuItem("Copy(Ctrl+C)");
        CopyMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                parent.WorkSpace.activePage.CopyObj=parent.WorkSpace.activePage.activeObj;
            }
        });
        PasteMenuItem =new MenuItem("Paste(Ctrl+V)");
        PasteMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                obj o=null;
                try {
                    for (object p : parent.WorkSpace.activePage.Points) {
                        if (p.ObjID == parent.WorkSpace.activePage.CopyObj.ID) {
                            parent.WorkSpace.activePage.undos.add(1);
                            parent.WorkSpace.activePage.RedoPoints.removeAllElements();
                            parent.WorkSpace.activePage.redos.removeAllElements();
                            if (p.ObjEnum == objEnum.rectangle) {
                                o = new objRectangle(parent.WorkSpace.activePage, p.PenColor, p.BGColor, p.TextColor,
                                         p.PenSize, parent.WorkSpace.activePage.ObjID, p.LineSD, p.str,true);
                            } else if (p.ObjEnum == objEnum.circular) {
                                o = new objCircular(parent.WorkSpace.activePage, p.PenColor, p.BGColor, p.TextColor,
                                         p.PenSize, parent.WorkSpace.activePage.ObjID, p.LineSD, p.str,true);
                            } else if (p.ObjEnum == objEnum.diamond) {
                                o = new objDiamond(parent.WorkSpace.activePage, p.PenColor, p.BGColor, p.TextColor,
                                         p.PenSize, parent.WorkSpace.activePage.ObjID, p.LineSD, p.str,true);
                            } else if (p.ObjEnum == p.ObjEnum.text) {
                                o = new objText(parent.WorkSpace.activePage, p.PenColor, p.BGColor, p.TextColor,
                                         p.PenSize, parent.WorkSpace.activePage.ObjID, p.LineSD, p.str,true);
                            }
                            parent.WorkSpace.activePage.add(o, 0);
                            parent.AttributesToolBar.ObjList.addObj(parent.WorkSpace.activePage.ObjID);
                            parent.AttributesToolBar.ObjList.setActiveObj(parent.WorkSpace.activePage.ObjID);
                            parent.WorkSpace.activePage.Objs.add(o);
                            parent.WorkSpace.activePage.setActiveObj(o);
                            parent.WorkSpace.activePage.CopyObj = o;
                            parent.WorkSpace.activePage.Points.add(new object(new Point(XY.x,XY.y),
                                    new Point(XY.x+Math.abs(p.Sp.x - p.Ep.x), XY.y+Math.abs(p.Sp.y - p.Ep.y)), p.PenSize, p.PenColor,
                                    p.BGColor,p.TextColor,p.ObjEnum, parent.WorkSpace.activePage.ObjID++,p.LineSD,p.str
                                    ,p.Angle,p.Tra,p.x,p.y,p.w,p.h,true));
                            o.setArr(p);
                            o.setObjLocation(XY.x-parent.WorkSpace.activePage.CopyObj.getWidth()/2,XY.y-parent.WorkSpace.activePage.CopyObj.getHeight()/2,false);
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
            }
        });
        this.add(UndoMenuItem);
        this.add(RedoMenuItem);
        this.add(CopyMenuItem);
        this.add(PasteMenuItem);
    }
}
