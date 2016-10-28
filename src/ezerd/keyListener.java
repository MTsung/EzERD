/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ezerd;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

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
            parent.TopToolBar.newPageBtn.doClick();
        }else if(e.isControlDown() && e.getKeyCode() == KeyEvent.VK_W){
            parent.TopToolBar.cloPageBtn.doClick();
        }else if(e.isControlDown() && e.getKeyCode() == KeyEvent.VK_Z){
            parent.TopToolBar.undoBtn.doClick();
        }else if(e.isControlDown() && e.getKeyCode() == KeyEvent.VK_Y){
            parent.TopToolBar.redoBtn.doClick();
        }else if(e.isControlDown() && e.getKeyCode() == KeyEvent.VK_O){
            parent.TopToolBar.openBtn.doClick();
        }else if(e.isControlDown() && e.getKeyCode() == KeyEvent.VK_S){
            parent.TopToolBar.saveBtn.doClick();
        }else if(e.isControlDown() && e.getKeyCode() == 107){//ctrl + '+'
            parent.AttributesToolBar.PanAttributesBox.PanSizeSlider.setValue(parent.AttributesToolBar.PanAttributesBox.PanSizeSlider.getValue()+1);
        }else if(e.isControlDown() && e.getKeyCode() == 109){//ctrl + '-'
            parent.AttributesToolBar.PanAttributesBox.PanSizeSlider.setValue(parent.AttributesToolBar.PanAttributesBox.PanSizeSlider.getValue()-1);
        }
        parent.MainWin.requestFocusInWindow();
               
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
    
}
