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
public class ezObject {
    ezObjEnum ObjEnum;
    Point Sp,Ep;
    float PenSize;
    Color PenColor,TextColor,BGColor;
    int ObjID,LineSD,Tra=100,Angle=0;
    int w,h,x,y;
    String str;
    Boolean Visible=true;
    ezObject(Point s,Point e,float Size,Color c,Color c1,Color c2,ezObjEnum O,int id,int SD,String S){
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
    ezObject(Point s,Point e,float Size,Color c,Color c1,Color c2
            ,ezObjEnum O,int id,int SD,String S,int angle,int tra
            ,int X,int Y,int W,int H,Boolean V){
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
        Visible=V;
    }
    ezObject(ezObject o){
        move(o);
    }
    void move(ezObject o){
        
        Sp = o.Sp;
        Ep = o.Ep;
        PenSize = o.PenSize;
        PenColor = o.PenColor;
        BGColor = o.BGColor;
        TextColor = o.TextColor;
        ObjEnum = o.ObjEnum;
        ObjID = o.ObjID;
        LineSD = o.LineSD;
        str = o.str;
        Angle = o.Angle;
        Tra =o.Tra;
        x = o.x;
        y = o.y;
        w = o.w;
        h = o.h;
        Visible = o.Visible;
    }

}
