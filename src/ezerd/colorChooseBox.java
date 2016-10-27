package ezerd;

import java.awt.Dimension;
import java.awt.Panel;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author CMC
 */
public class colorChooseBox extends Panel{
    attributesToolBar AtoolBat;   
    colorBox CB;
    colorChooseBox(attributesToolBar p) {
        super();
        AtoolBat=p;
        CB=new colorBox(AtoolBat);
        this.add(CB);
    }
}
