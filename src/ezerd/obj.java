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
    int ID,Tra=100;
    pageActionEnum tempPAE;
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
                if(parent.ObjEnum==objEnum.arrow){
                    
                }else if(parent.PageActionEnum==pageActionEnum.moving){
                    Ep = e.getPoint();
                    obj.this.setLocation(obj.this.getLocation().x + (Ep.x - Sp.x),
                                         obj.this.getLocation().y + (Ep.y - Sp.y));
                }
                parent.ArrowPaint=true;
            } 
            @Override
            public void mouseMoved(MouseEvent e) {
                if (parent.ObjArrowJ) {
                    Graphics2D g = (Graphics2D) parent.getGraphics();
                    g.setColor(Color.BLUE);
                    g.setStroke(new BasicStroke(2, CAP_ROUND, JOIN_ROUND));
                    g.drawRect(obj.this.getX()-3, obj.this.getY()-3,
                                obj.this.getWidth() + 6, obj.this.getHeight() + 6);
                    
                    Graphics2D g2 = (Graphics2D)parent.getGraphics();
                    g2.setXORMode(Color.yellow);
                    if (parent.Ep != null) {
                        drawAL(parent.Sp.x,parent.Sp.y,parent.Ep.x,parent.Ep.y,g2);
                    }
                    parent.Ep=new Point(obj.this.getX()+e.getX(),obj.this.getY()+e.getY());
                    drawAL(parent.Sp.x,parent.Sp.y,parent.Ep.x,parent.Ep.y,g2);
                    
                }
            }
        });
        this.addMouseListener(new MouseAdapter(){
            @Override
            public void mousePressed(MouseEvent e){
                parent.setActiveObj(obj.this);
                parent.repaint();
                if(parent.PageActionEnum==pageActionEnum.idle)
                    parent.PageActionEnum=pageActionEnum.moving;
                if(parent.ObjEnum==objEnum.arrow&&!parent.ObjArrowJ){
                    parent.ObjArrowJ=true;
                    parent.Sp=new Point(obj.this.getX()+obj.this.getWidth()/2
                                                ,obj.this.getY()+obj.this.getHeight()/2);
                    parent.SObj=obj.this;
                }else if(parent.ObjEnum==objEnum.arrow&&parent.ObjArrowJ){
                    parent.ObjArrowJ=false;
                    parent.Ep=new Point(obj.this.getX()+obj.this.getWidth()/2,obj.this.getY()+obj.this.getHeight()/2);
                    parent.EObj=obj.this;
                    parent.ObjArrowXYs.add(new objArrowXY(parent.SObj,parent.EObj
                                                    ,parent.SObj.ID,parent.EObj.ID,parent.PenColor));
                    parent.undos.add(-1);
                    parent.ReObjPoints.removeAllElements();
                    parent.redos.removeAllElements();
                    parent.SObj=null;
                    parent.EObj=null;
                    parent.repaint();
                    parent.Ep=null;
                }else if(parent.PageActionEnum==pageActionEnum.moving){
                    Sp=e.getPoint();
                    parent.parent.AttributesToolBar.AttributesBox.ObjAttributesPanel.setTextLocation(obj.this.getX(), obj.this.getY());
                    parent.parent.AttributesToolBar.AttributesBox.ObjAttributesPanel.setTextSize(obj.this.getWidth(), obj.this.getHeight());
                    parent.parent.AttributesToolBar.AttributesBox.PenSizeSlider.setValue((int) PenSize);
                    parent.parent.AttributesToolBar.AttributesBox.ColorBox.setColor(PenColor);
                    for(object o:parent.Points){
                        if(o.ObjID==ID){
                            o.Sp=obj.this.getLocation();
                            o.Ep=new Point(obj.this.getX()+obj.this.getWidth(),obj.this.getY()+obj.this.getHeight());
                            parent.ObjPoints.add(new objPoint(o.Sp,o.Ep,ID));
                            
                            parent.undos.add(0);
                            parent.ReObjPoints.removeAllElements();
                            parent.redos.removeAllElements();
                            parent.parent.TopToolBar.UndoBtn.setEnabled(parent.undos.size() == 0 ? false : true);
                            parent.parent.TopToolBar.RedoBtn.setEnabled(parent.redos.size() == 0 ? false : true);
                        }
                    }
                    Graphics2D g=(Graphics2D)parent.getGraphics();
                    g.setXORMode(new Color(255,255,0));
                    g.setStroke(new BasicStroke(2,CAP_ROUND,JOIN_ROUND));
                    g.drawRect(obj.this.getX()-3, obj.this.getY()-3,
                                obj.this.getWidth() + 6, obj.this.getHeight() + 6);
                }
            }
            @Override
            public void mouseReleased(MouseEvent e){
                if(parent.ObjEnum==objEnum.arrow){
                }else if(parent.PageActionEnum==pageActionEnum.moving){
                    for(object o:parent.Points){
                        if(o.ObjID==ID){
                            o.Sp=obj.this.getLocation();
                            o.Ep=new Point(obj.this.getX()+obj.this.getWidth(),obj.this.getY()+obj.this.getHeight());
                        }
                    }
                    parent.PageActionEnum=pageActionEnum.idle;
                }
                parent.repaint();
                if (e.getModifiers() == InputEvent.BUTTON3_MASK) {
                    parent.popupMenu1.XY=new Point(obj.this.getX()+e.getX(),obj.this.getY()+e.getY());
                    parent.popupMenu1.show(obj.this, e.getX(), e.getY());
                }
            }
            @Override
            public void mouseEntered(MouseEvent e) {
                //tempPAE=parent.PageActionEnum;
                //parent.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
            }
            @Override
            public void mouseExited(MouseEvent e) {
                if (parent.ObjArrowJ) {
                    Graphics2D g=(Graphics2D)parent.getGraphics();
                    g.setXORMode(new Color(255,255,0));
                    g.setStroke(new BasicStroke(2,CAP_ROUND,JOIN_ROUND));
                    g.drawRect(obj.this.getX()-3, obj.this.getY()-3,
                                obj.this.getWidth() + 6, obj.this.getHeight() + 6);
                }
                //if(parent.PageActionEnum!=pageActionEnum.idle)
                    //parent.setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
            }
        });
    }
    
    void setTra(int n){
        Tra=n;
        PenColor=new Color(PenColor.getRed(),PenColor.getGreen(),PenColor.getBlue(),(int)(Tra*2.55));
        this.repaint();
    }
    
    int getTra(){
       return Tra; 
    }
    
    public abstract void paintObj(Graphics g);
    public void paint(Graphics g)
    {
        paintObj(g);
        for (object o : parent.Points) {
            if (o.ObjID == ID) {
                for (objArrowXY obja : parent.ObjArrowXYs) {
                    if (obja.SObjID == o.ObjID) {
                        obja.SObj = obj.this;
                    }
                    if (obja.EObjID == o.ObjID) {
                        obja.EObj = obj.this;
                    }
                }
            }
        }
        
    }

}
