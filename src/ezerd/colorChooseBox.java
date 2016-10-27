

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
public class colorChooseBox extends Panel{
    attributesToolBar AtoolBat;   
    colorBox CB;
    colorTextPanel CTP;
    colorChooseBox(attributesToolBar p) {
        super();
        AtoolBat=p;
        //this.setLayout(new BorderLayout());
        CB=new colorBox(AtoolBat);
        CTP= new colorTextPanel(this);
        this.add(CB);
        this.add(CTP);
        
        
    }
}
