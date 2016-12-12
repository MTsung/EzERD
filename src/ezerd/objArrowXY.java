/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ezerd;

import java.awt.Color;

/**
 *
 * @author CMC
 */
public class objArrowXY {
    obj SObj,EObj;
    int SObjID,EObjID,Solid;
    Color ArrowColor;
    objArrowXY(obj s,obj e,int sid,int eid,Color C,int S){
        SObj=s;
        EObj=e;
        SObjID=sid;
        EObjID=eid;
        ArrowColor=C;
        Solid=S;
    }
}
