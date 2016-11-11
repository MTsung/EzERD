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
public class objList extends Panel{
    attributesToolBar AtoolBar;   
    
    objList(attributesToolBar p) {
        super();
        AtoolBar=p;
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
    }
    void addObj(int ObjID){
        objListPanel o=new objListPanel(this,ObjID);
        this.add(o);
        AtoolBar.validate();
    }
}
