/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ezerd;
import static ezerd.page.drawAL;
import java.awt.*;
import static java.awt.BasicStroke.CAP_ROUND;
import static java.awt.BasicStroke.JOIN_ROUND;
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
    int ID;
    obj(){  
    }
    obj(page p,Color c,float s,int id){
        parent=p;
        PenColor=c;
        PenSize = s > 8 ? 8 : s;
        ID=id;
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
                if (obj.this.parent.ObjArrowJ) {
                    Graphics2D g = (Graphics2D) obj.this.parent.getGraphics();
                    g.setColor(Color.BLUE);
                    g.setStroke(new BasicStroke(2, CAP_ROUND, JOIN_ROUND));
                    g.drawRect(obj.this.getX()-3, obj.this.getY()-3,
                                obj.this.getWidth() + 6, obj.this.getHeight() + 6);
                    
                    Graphics2D g2 = (Graphics2D)obj.this.parent.getGraphics();
                    g2.setXORMode(Color.yellow);
                    if (obj.this.parent.Ep != null) {
                        drawAL(obj.this.parent.Sp.x,obj.this.parent.Sp.y,obj.this.parent.Ep.x,obj.this.parent.Ep.y,g2);
                    }
                    obj.this.parent.Ep=new Point(obj.this.getX()+e.getX(),obj.this.getY()+e.getY());
                    drawAL(obj.this.parent.Sp.x,obj.this.parent.Sp.y,obj.this.parent.Ep.x,obj.this.parent.Ep.y,g2);
                    
                }
            }
        });
        this.addMouseListener(new MouseAdapter(){
            @Override
            public void mousePressed(MouseEvent e){
                parent.activeObj=obj.this;
                if(obj.this.parent.ObjEnum==objEnum.arrow&&!obj.this.parent.ObjArrowJ){
                    obj.this.parent.ObjArrowJ=true;
                    obj.this.parent.Sp=new Point(obj.this.getX()+obj.this.getWidth()/2
                                                ,obj.this.getY()+obj.this.getHeight()/2);
                    obj.this.parent.SObj=obj.this;
                }else if(obj.this.parent.ObjEnum==objEnum.arrow&&obj.this.parent.ObjArrowJ){
                    obj.this.parent.ObjArrowJ=false;
                    obj.this.parent.Ep=new Point(obj.this.getX()+obj.this.getWidth()/2,obj.this.getY()+obj.this.getHeight()/2);
                    obj.this.parent.EObj=obj.this;
                    obj.this.parent.ObjArrowXYs.add(new objArrowXY(obj.this.parent.SObj,obj.this.parent.EObj));
                    obj.this.parent.SObj=null;
                    obj.this.parent.EObj=null;
                    obj.this.parent.repaint();
                    obj.this.parent.Ep=null;
                }else if(obj.this.parent.PageActionEnum==pageActionEnum.idle){
                    Sp=e.getPoint();
                    parent.activeObj=obj.this;
                    for(object o:obj.this.parent.Points){
                        if(o.ObjID==ID){
                            o.Sp=obj.this.getLocation();
                            o.Ep=new Point(obj.this.getX()+obj.this.getWidth(),obj.this.getY()+obj.this.getHeight());
                            obj.this.parent.ObjPoints.add(new objPoint(o.Sp,o.Ep,ID));
                            
                            obj.this.parent.ReObjPoints.removeAllElements();
                            obj.this.parent.redos.removeAllElements();
                            obj.this.parent.parent.TopToolBar.UndoBtn.setEnabled(obj.this.parent.undos.size() == 0 ? false : true);
                            obj.this.parent.parent.TopToolBar.RedoBtn.setEnabled(obj.this.parent.redos.size() == 0 ? false : true);
                            obj.this.parent.undos.add(0);
                        }
                    }
                }
            }
            @Override
            public void mouseReleased(MouseEvent e){
                if(obj.this.parent.ObjEnum==objEnum.arrow){
                }else if(obj.this.parent.PageActionEnum==pageActionEnum.idle){
                    for(object o:obj.this.parent.Points){
                        if(o.ObjID==ID){
                            o.Sp=obj.this.getLocation();
                            o.Ep=new Point(obj.this.getX()+obj.this.getWidth(),obj.this.getY()+obj.this.getHeight());
                        }
                    }
                }
            }
            @Override
            public void mouseEntered(MouseEvent e) {
                if (obj.this.parent.ObjArrowJ) {
                }
            }
            @Override
            public void mouseExited(MouseEvent e) {
                if (obj.this.parent.ObjArrowJ) {
                    Graphics2D g=(Graphics2D)obj.this.parent.getGraphics();
                    g.setXORMode(new Color(255,255,0));
                    g.setStroke(new BasicStroke(2,CAP_ROUND,JOIN_ROUND));
                    g.drawRect(obj.this.getX()-3, obj.this.getY()-3,
                                obj.this.getWidth() + 6, obj.this.getHeight() + 6);
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
