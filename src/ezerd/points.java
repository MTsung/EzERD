/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ezerd;

import java.awt.*;
/**
 *
 * @author CMC
 */
public class points {
    Point Sp,Ep;
    float PanSize;
    Color PanColor;
    points(Point s,Point e,float Size,Color color){
        Sp=s;
        Ep=e;
        PanSize=Size;
        PanColor=color;
    }
}
