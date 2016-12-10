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
    Color PenColor,TextColor,BGColor;
    int ObjID,LineSD,Tra=100,Angle=0;
    int w,h,x,y;
    String str;
    object(Point s,Point e,float Size,Color c,Color c1,Color c2,objEnum O,int id,int SD,String S){
        Sp=s;
        Ep=e;
        PenSize=Size;
        PenColor=c;
        BGColor=c1;
        TextColor=c2;
        ObjEnum=O;
        ObjID=id;
        LineSD=SD;
        str=S;
    }
    object(Point s,Point e,float Size,Color c,Color c1,Color c2
            ,objEnum O,int id,int SD,String S,int angle,int tra
            ,int X,int Y,int W,int H){
        Sp=s;
        Ep=e;
        PenSize=Size;
        PenColor=c;
        BGColor=c1;
        TextColor=c2;
        ObjEnum=O;
        ObjID=id;
        LineSD=SD;
        str=S;
        Angle=angle;
        Tra=tra;
        x=X;
        y=Y;
        w=W;
        h=H;
    }

    
}
