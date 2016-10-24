/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ezerd;

import java.awt.*;
import java.awt.event.*;

/**
 *
 * @author CMC
 */
public class rightClickMenu extends PopupMenu{
        MenuItem menuItem1 = new MenuItem();
    rightClickMenu(){
        menuItem1.setLabel(".........");
        menuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
            }
        });
        this.add(menuItem1);
    }
}
