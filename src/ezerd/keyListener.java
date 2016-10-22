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
            parent.Ttb.newPageBtn.doClick();
        }else if(e.isControlDown() && e.getKeyCode() == KeyEvent.VK_W){
            parent.Ttb.cloPageBtn.doClick();
        }else if(e.isControlDown() && e.getKeyCode() == KeyEvent.VK_Z){
            parent.Ttb.undoBtn.doClick();
        }else if(e.isControlDown() && e.getKeyCode() == KeyEvent.VK_Y){
            parent.Ttb.redoBtn.doClick();
        }
               
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
    
}
