/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ezerd;

import java.awt.*;
import java.awt.event.*;
import java.util.Vector;
import javax.swing.*;
import javax.swing.event.*;

/**
 *
 * @author CMC
 */
public class objList extends Panel{
    attributesToolBar AtoolBar;   
    Vector<objListPanel> objListPanels;
    objList(attributesToolBar p) {
        super();
        AtoolBar=p;
        objListPanels=new Vector<objListPanel>();
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
    }
    void addObj(int ObjID){
        objListPanel o=new objListPanel(this,ObjID);
        objListPanels.add(o);
        this.add(o);
        AtoolBar.validate();
    }
    int getActiveObjID(){
        for(objListPanel o:objListPanels){
            Color C=new Color(150,150,150);
            if(o.getBackground()==C){
                return o.ObjID;
            }
        }
        return 0;
    }
    void setActiveObj(int ID){
        for(objListPanel o:objListPanels){
            Color C=new Color(150,150,150);
            o.setBackground(Color.LIGHT_GRAY);
            if (o.ObjID == ID) {
                o.setBackground(C);
            }
        }
    }
}
