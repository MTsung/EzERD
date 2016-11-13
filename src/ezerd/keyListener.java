/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ezerd;

import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Iterator;

/**
 *
 * @author CMC
 */
public class keyListener implements KeyListener{
    ezERD parent;
    keyListener(ezERD p){
        super();
        parent=p;
    }        
    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.isControlDown() && e.getKeyCode() == KeyEvent.VK_N) {
            parent.TopToolBar.NewPageBtn.doClick();
        }else if(e.isControlDown() && e.getKeyCode() == KeyEvent.VK_W){
            parent.TopToolBar.ClosePageBtn.doClick();
        }else if(e.isControlDown() && e.getKeyCode() == KeyEvent.VK_Z){
            parent.TopToolBar.UndoBtn.doClick();
        }else if(e.isControlDown() && e.getKeyCode() == KeyEvent.VK_Y){
            parent.TopToolBar.RedoBtn.doClick();
        }else if(e.isControlDown() && e.getKeyCode() == KeyEvent.VK_O){
            parent.TopToolBar.OpenBtn.doClick();
        }else if(e.isControlDown() && e.getKeyCode() == KeyEvent.VK_S){
            parent.TopToolBar.SaveBtn.doClick();
        }else if(e.isControlDown() && e.getKeyCode() == 107){//ctrl + '+'
            parent.AttributesToolBar.AttributesBox.PenSizeSlider.setValue(parent.AttributesToolBar.AttributesBox.PenSizeSlider.getValue()+1);
        }else if(e.isControlDown() && e.getKeyCode() == 109){//ctrl + '-'
            parent.AttributesToolBar.AttributesBox.PenSizeSlider.setValue(parent.AttributesToolBar.AttributesBox.PenSizeSlider.getValue()-1);
        }else if(e.isControlDown() && e.getKeyCode() == 33){//ctrl + PgUp
            parent.WorkSpace.prevPage();
        }else if(e.isControlDown() && e.getKeyCode() == 34){//ctrl + PgDn
            parent.WorkSpace.nextPage();
        }else if(e.isControlDown() && e.getKeyCode() == KeyEvent.VK_C){
            parent.WorkSpace.activePage.CopyObj=parent.WorkSpace.activePage.activeObj;
        }else if(e.isControlDown() && e.getKeyCode() == KeyEvent.VK_V){
            obj o=null;
            try {
                for (object p : parent.WorkSpace.activePage.Points) {
                    if (p.ObjID == parent.WorkSpace.activePage.CopyObj.ID) {
                        if (p.ObjEnum == objEnum.rectangle) {
                            o = new objRectangle(parent.WorkSpace.activePage, p.PenColor, p.PenSize, parent.WorkSpace.activePage.ObjID);
                            parent.WorkSpace.activePage.add(o, 0);
                        } else if (p.ObjEnum == objEnum.circular) {
                            o = new objCircular(parent.WorkSpace.activePage, p.PenColor, p.PenSize, parent.WorkSpace.activePage.ObjID);
                            parent.WorkSpace.activePage.add(o, 0);
                        } else if (p.ObjEnum == objEnum.diamond) {
                            o = new objDiamond(parent.WorkSpace.activePage, p.PenColor, p.PenSize, parent.WorkSpace.activePage.ObjID);
                            parent.WorkSpace.activePage.add(o, 0);
                        }
                        parent.WorkSpace.activePage.add(o, 0);
                        parent.AttributesToolBar.ObjList.addObj(parent.WorkSpace.activePage.ObjID);
                        parent.AttributesToolBar.ObjList.setActiveObj(parent.WorkSpace.activePage.ObjID);
                        o.setLocation(0, 0);
                        o.setSize(Math.abs(p.Sp.x - p.Ep.x), Math.abs(p.Sp.y - p.Ep.y));
                        parent.WorkSpace.activePage.Objs.add(o);
                        parent.WorkSpace.activePage.CopyObj = o;
                        parent.WorkSpace.activePage.Points.add(new object(new Point(0, 0),
                                new Point(Math.abs(p.Sp.x - p.Ep.x), Math.abs(p.Sp.y - p.Ep.y)), p.PenSize, p.PenColor,
                                p.ObjEnum, parent.WorkSpace.activePage.ObjID++));
                    }
                }
            }catch (Throwable ee) {
            }
            
        }
        //System.out.println(e.getKeyCode());
        parent.MainWin.requestFocusInWindow();
               
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
    
}
