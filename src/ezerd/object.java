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
public class object {
    objEnum ObjEnum;
    Point Sp,Ep;
    float PenSize;
    Color PenColor;
    Boolean MoveB;
    object(Point s,Point e,float Size,Color color,objEnum O){
        Sp=s;
        Ep=e;
        PenSize=Size;
        PenColor=color;
        ObjEnum=O;
        MoveB=false;
    }
    object(Point s,Point e,Boolean B){
        Sp=s;
        Ep=e;
        MoveB=B;
    }
}
