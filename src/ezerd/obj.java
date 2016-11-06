/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ezerd;
import static ezerd.page.drawAL;
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
    obj EndObj;
    obj(){  
    }
    obj(page p,Color c,float s){
        parent=p;
        PenColor=c;
        PenSize = s > 8 ? 8 : s;
        this.addMouseMotionListener(new MouseAdapter(){
            @Override
            public void mouseDragged(MouseEvent e){
                if(obj.this.parent.ObjEnum==objEnum.arrow){
                    
                }else if(obj.this.parent.PageActionEnum==pageActionEnum.idle){
                    Ep = e.getPoint();
                    obj.this.setLocation(obj.this.getLocation().x + (Ep.x - Sp.x),
                                         obj.this.getLocation().y + (Ep.y - Sp.y));
                }
                    obj.this.parent.ArrowPaint=true;
            } 
            @Override
            public void mouseMoved(MouseEvent e) {
                
            }
        });
        this.addMouseListener(new MouseAdapter(){
            @Override
            public void mousePressed(MouseEvent e){
                parent.activeObj=obj.this;
                if(obj.this.parent.ObjEnum==objEnum.arrow&&!obj.this.parent.ObjArrowJ){
                    obj.this.parent.ObjArrowJ=true;
                    obj.this.parent.Sp=new Point(obj.this.getX()+obj.this.getWidth()/2,obj.this.getY()+obj.this.getHeight()/2);
                    for(obj ob:obj.this.parent.Objs){
                        if(ob==obj.this){
                            obj.this.parent.SObj=ob;
                        }
                    }
                }else if(obj.this.parent.ObjEnum==objEnum.arrow&&obj.this.parent.ObjArrowJ){
                    obj.this.parent.ObjArrowJ=false;
                    obj.this.parent.Ep=new Point(obj.this.getX()+obj.this.getWidth()/2,obj.this.getY()+obj.this.getHeight()/2);
                    Graphics g = obj.this.parent.getGraphics();
                    g.setColor(PenColor);
                    //System.out.println(obj.this.parent.Sp.x+","+obj.this.parent.Sp.y+","+obj.this.parent.Ep.x+","+obj.this.parent.Ep.y);
                    g.drawLine(obj.this.parent.Sp.x,obj.this.parent.Sp.y,obj.this.parent.Ep.x,obj.this.parent.Ep.y);
                    //drawAL(obj.this.parent.Sp.x,obj.this.parent.Sp.y,obj.this.parent.Ep.x,obj.this.parent.Ep.y,g);
                    obj.this.parent.Points.add(new object(obj.this.parent.Sp,obj.this.parent.Ep
                                                ,1,PenColor,obj.this.parent.ObjEnum));
                    for(obj ob:obj.this.parent.Objs){
                        if(ob==obj.this){
                            obj.this.parent.EObj=ob;
                        }
                    }
                    obj.this.parent.repaint();
                }else if(obj.this.parent.PageActionEnum==pageActionEnum.idle){
                    Sp=e.getPoint();
                    parent.activeObj=obj.this;
                }
            }
            public void mouseReleased(MouseEvent e){
                if(obj.this.parent.ObjEnum==objEnum.arrow){
                }
            }
        });
    }
    public abstract void paintObj(Graphics g);
    public void paint(Graphics g)
    {
        paintObj(g);
    }
}
