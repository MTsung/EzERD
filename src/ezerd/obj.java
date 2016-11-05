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
    Point Sp,Ep;
    Point ArrSp,ArrEp;
    obj(){  
    }
    obj(page p,Point Sp,Point Ep,Color c){
        parent=p;
        ArrSp=Sp;
        ArrEp=Ep;
        PenColor=c;
        this.addMouseMotionListener(new MouseAdapter(){
            @Override
            public void mouseDragged(MouseEvent e){
                if(obj.this.parent.PageActionEnum==pageActionEnum.idle){
                    obj.this.Ep = e.getPoint();
                    obj.this.setLocation(obj.this.getLocation().x + (obj.this.Ep.x - obj.this.Sp.x),
                                         obj.this.getLocation().y + (obj.this.Ep.y - obj.this.Sp.y));
                }
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
                if(obj.this.parent.PageActionEnum==pageActionEnum.idle){
                    obj.this.Sp=e.getPoint();
                    parent.activeObj=obj.this;
                }
            }
            public void mouseReleased(MouseEvent e){
                
            }
        });
    }
    obj(page p,Color c,float s){
        parent=p;
        PenColor=c;
        PenSize=s>8?8:s;
        this.addMouseMotionListener(new MouseAdapter(){
            @Override
            public void mouseDragged(MouseEvent e){
                if(obj.this.parent.PageActionEnum==pageActionEnum.idle){
                    Ep = e.getPoint();
                    obj.this.setLocation(obj.this.getLocation().x + (Ep.x - Sp.x),
                                         obj.this.getLocation().y + (Ep.y - Sp.y));
                }
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
                if(obj.this.parent.PageActionEnum==pageActionEnum.idle){
                    Sp=e.getPoint();
                    parent.activeObj=obj.this;
                }
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
