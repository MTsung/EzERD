/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ezerd;

import java.awt.Color;
import java.awt.Point;

/**
 *
 * @author CMC
 */
public class objPoint {
    Point Sp,Ep;
    float PenSize;
    Color PenColor,TextColor,BGColor;
    int ID,LineSD,Tra=100,Angle=0;
    int w,h,x,y;
    String str;
    objPoint(objPoint p){
        Sp=p.Sp;
        Ep=p.Ep;
        PenSize=p.PenSize;
        PenColor=p.PenColor;
        BGColor=p.BGColor;
        TextColor=p.TextColor;
        ID=p.ID;
        LineSD=p.LineSD;
        str=p.str;
        Tra=p.Tra;
        Angle=p.Angle;
    }
    objPoint(Point s,Point e,float Size,Color c,Color c1,Color c2
            ,int id,int SD,String S,int tra,int angle,int X,int Y,int W,int H){
        Sp=s;
        Ep=e;
        PenSize=Size;
        PenColor=c;
        BGColor=c1;
        TextColor=c2;
        ID=id;
        LineSD=SD;
        str=S;
        Tra=tra;
        Angle=angle;
        x=X;
        y=Y;
        w=W;
        h=H;
    }

    objPoint(object o) {
        Sp=o.Sp;
        Ep=o.Ep;
        PenSize=o.PenSize;
        PenColor=o.PenColor;
        BGColor=o.BGColor;
        TextColor=o.TextColor;
        ID=o.ObjID;
        LineSD=o.LineSD;
        str=o.str;
        Tra=o.Tra;
        Angle=o.Angle;
        x=o.x;
        y=o.y;
        w=o.w;
        h=o.h;
    }
}
