/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ezerd;
import java.awt.*;
import java.awt.event.*;
/**
 *
 * @author CMC
 */
public abstract class obj extends Component {
    Color PenColor;
    float PenSize;
    obj(){
    }
    obj(Color c,float s){
        PenColor=c;
        PenSize=s;
    }
    
    public abstract void paintPattern(Graphics g);
    public void paint(Graphics g)
    {
        paintPattern(g);
    }
}
