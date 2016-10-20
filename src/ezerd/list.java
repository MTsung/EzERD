/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ezerd;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
/**
 *
 * @author CMC
 */
public class list extends JList<String>{
    DefaultListModel<String> ListN;
    list(DefaultListModel<String> model){
        super(model);
        ListN=model;
        
        this.setBackground(new Color(150,150,150));
        this.setPreferredSize(new Dimension(200,0));
    }
        //ListN.addElement("Hello");
}
