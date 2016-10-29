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
            parent.AttributesToolBar.PenAttributesBox.PenSizeSlider.setValue(parent.AttributesToolBar.PenAttributesBox.PenSizeSlider.getValue()+1);
        }else if(e.isControlDown() && e.getKeyCode() == 109){//ctrl + '-'
            parent.AttributesToolBar.PenAttributesBox.PenSizeSlider.setValue(parent.AttributesToolBar.PenAttributesBox.PenSizeSlider.getValue()-1);
        }
        parent.MainWin.requestFocusInWindow();
               
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
    
}
