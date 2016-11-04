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
    page parent;
    int x,y;
    Point Sp,Ep;
    obj(){
    }
    obj(Color c,float s){
        PenColor=c;
        PenSize=s;
        this.addMouseMotionListener(new MouseAdapter(){
            @Override
            public void mouseDragged(MouseEvent e){
                Ep=e.getPoint();
                obj.this.setLocation(obj.this.getLocation().x + (Ep.x-Sp.x),
                                     obj.this.getLocation().y + (Ep.y-Sp.y));
            } 
            @Override
            public void mouseMoved(MouseEvent e) {
                //parent.parent.MessageBar.XY=new Point(parent.parent.MessageBar.XY.x-e.getX(),parent.parent.MessageBar.XY.y-e.getY());
                //parent.parent.MessageBar.updateMessage();
            }
        });
        this.addMouseListener(new MouseAdapter(){
            @Override
            public void mousePressed(MouseEvent e){
                Sp=e.getPoint();
            }
            public void mouseReleased(MouseEvent e){
                
            }
        });
    }
    
    public abstract void paintObj(Graphics g);
    public void paint(Graphics g)
    {
        paintObj(g);
    }
}
