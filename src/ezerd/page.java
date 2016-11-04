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
import static java.awt.BasicStroke.*;
import java.util.Stack;
import java.util.Vector;
        
/**
 *
 * @author CMC
 */

public class page extends Panel{
    Boolean PaintObj=true;
    ezERD parent;
    Point Sp,Ep;
    Vector<object> Points,RePoints;
    Stack<Integer> undos,redos;
    int undo=0,PageWidth=2000,PageHeight=900;
    float PenSize=8;
    Color PenColor=new Color(0,0,0);
    rightClickMenu popupMenu1;
    Image bufferImage;
    Graphics bufferGraphics;
    pageActionEnum PageActionEnum;
    objEnum ObjEnum;
    
    page(ezERD p){
        super();      
        parent=p;      
        Points=new Vector<object>();
        RePoints=new Vector<object>();
        undos=new Stack<Integer>();
        redos=new Stack<Integer>();
        PageActionEnum=PageActionEnum.idle;
        this.setBackground(Color.WHITE);
        this.setLayout(null);
        this.setPreferredSize(new Dimension(PageWidth,PageHeight));
        this.addMouseMotionListener(new MouseAdapter(){
            @Override
            public void mouseDragged(MouseEvent e){
                //System.out.println("mouseReleased");
                //PenColor=new Color(parent.AttributesToolBar.PenAttributesBox.ColorBox.getColorInt());
                if (e.getModifiers() == InputEvent.BUTTON3_MASK) {
                }else if(ObjEnum == ObjEnum.graffiti){
                    Graphics2D g = (Graphics2D)page.this.getGraphics();    
                    g.setStroke(new BasicStroke(PenSize,CAP_ROUND,JOIN_ROUND));
                    g.setColor(PenColor);
                    Ep=e.getPoint();
                    g.drawLine(Sp.x, Sp.y, Ep.x, Ep.y);
                    Points.add(new object(Sp,Ep,PenSize,PenColor,ObjEnum.graffiti));
                    Sp=Ep;
                    undo++; 
                }else if(PageActionEnum==PageActionEnum.creatingObject){
                    Graphics g = page.this.getGraphics();
                    g.setXORMode(Color.yellow);
                    if (Ep != null) {
                        g.drawRect((Sp.x < Ep.x) ? Sp.x : Ep.x, (Sp.y < Ep.y) ? Sp.y : Ep.y, Math.abs(Sp.x - Ep.x), Math.abs(Sp.y - Ep.y));
                    }
                    Ep = e.getPoint();
                    g.drawRect((Sp.x < Ep.x) ? Sp.x : Ep.x, (Sp.y < Ep.y) ? Sp.y : Ep.y, Math.abs(Sp.x - Ep.x), Math.abs(Sp.y - Ep.y));
                }
                
                parent.MessageBar.XY=e.getPoint();
                parent.MessageBar.updateMessage();
            }
            
            @Override
            public void mouseMoved(MouseEvent e) {
                parent.MessageBar.XY=e.getPoint();
                parent.MessageBar.updateMessage();
            }
        });
        //&& ((e.getModifiers() == InputEvent.BUTTON1_MASK+2) || (e.getModifiers() == InputEvent.BUTTON1_MASK))
        this.addMouseListener(new MouseAdapter(){
            @Override
            public void mousePressed(MouseEvent e){
                //System.out.println("mousePressed");
                if (e.getModifiers() == InputEvent.BUTTON3_MASK) {
                }else if(ObjEnum == ObjEnum.graffiti){
                    Sp=e.getPoint();
                    undo=0;
                }else if(PageActionEnum == PageActionEnum.ready2createObject )
                {
                    Sp = e.getPoint();
                    PageActionEnum = PageActionEnum.creatingObject;
                }
            }
            
            @Override
            public void mouseReleased(MouseEvent e){
                //System.out.println("mouseReleased");
                if (e.getModifiers() == InputEvent.BUTTON3_MASK) {
                    popupMenu1.show(page.this, e.getX(), e.getY());
                }else if(ObjEnum == ObjEnum.graffiti){
                    if(undo!=0)
                        undos.add(undo);
                    RePoints.removeAllElements();
                    redos.removeAllElements();
                    parent.TopToolBar.UndoBtn.setEnabled(undos.size()==0 ? false:true);
                    parent.TopToolBar.RedoBtn.setEnabled(redos.size()==0 ? false:true);
                } else if (PageActionEnum==PageActionEnum.creatingObject) {
                    Graphics g = page.this.getGraphics();
                    g.setXORMode(Color.yellow);
                    if (Ep != null) {
                        undos.add(1);
                        RePoints.removeAllElements();
                        redos.removeAllElements();
                        g.drawRect((Sp.x < Ep.x) ? Sp.x : Ep.x, (Sp.y < Ep.y) ? Sp.y : Ep.y, Math.abs(Sp.x - Ep.x), Math.abs(Sp.y - Ep.y));
                    
                        obj o = null;
                        if (ObjEnum == ObjEnum.rectangle) {
                            o = new objRectangle(page.this,PenColor, PenSize);
                            page.this.add(o,0);
                            o.setLocation((Sp.x < Ep.x) ? Sp.x : Ep.x, (Sp.y < Ep.y) ? Sp.y : Ep.y);
                            o.setSize(Math.abs(Sp.x - Ep.x), Math.abs(Sp.y - Ep.y));
                            //o.setBackground(PenColor);
                        } else if (ObjEnum == ObjEnum.circular) {
                            o = new objCircular(page.this,PenColor, PenSize);
                            page.this.add(o,0);
                            o.setLocation((Sp.x < Ep.x) ? Sp.x : Ep.x, (Sp.y < Ep.y) ? Sp.y : Ep.y);
                            o.setSize(Math.abs(Sp.x - Ep.x), Math.abs(Sp.y - Ep.y));
                        } else if (ObjEnum == ObjEnum.diamond) {
                            o = new objDiamond(page.this,PenColor, PenSize);
                            page.this.add(o,0);
                            o.setLocation((Sp.x < Ep.x) ? Sp.x : Ep.x, (Sp.y < Ep.y) ? Sp.y : Ep.y);
                            o.setSize(Math.abs(Sp.x - Ep.x), Math.abs(Sp.y - Ep.y));
                        } else if (ObjEnum == ObjEnum.arrow) {
                            o = new objArrow(page.this,Sp,Ep,PenColor);
                            page.this.add(o,0);
                            o.setLocation((Sp.x < Ep.x) ? Sp.x : Ep.x, (Sp.y < Ep.y) ? Sp.y : Ep.y);
                            o.setSize(Math.abs(Sp.x - Ep.x), Math.abs(Sp.y - Ep.y));
                        }
                        Points.add(new object(Sp,Ep,PenSize,PenColor,ObjEnum));
                    }
                    PageActionEnum=PageActionEnum.ready2createObject;
                }  
                if(!parent.PageToolBar.Btns.elementAt(parent.PageToolBar.activeButton()).getText().endsWith("*")&&Ep!=null)
                    parent.PageToolBar.Btns.elementAt(parent.PageToolBar.activeButton()).setText(
                                                        parent.PageToolBar.Btns.elementAt(parent.PageToolBar.activeButton()).getText()+"*");
                //page.this.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
                Ep=Sp=null;
            }
        });
        popupMenu1 =new rightClickMenu(parent);
        popupMenu1.setFont(new programFont());
        this.add(popupMenu1);
       
        for(Component a:this.getComponents())/**/
            a.addKeyListener(new keyListener(parent));/**/
        this.addKeyListener(new keyListener(parent));/**/

    }
    
    
    public void update(Graphics g) {
        paint(g);
    }
    @Override
    public void paint(Graphics g) {
        g.drawImage(paintPage(), 0, 0, this);
        parent.MainWin.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
        parent.TopToolBar.UndoBtn.setEnabled(undos.size()==0 ? false:true);
        parent.TopToolBar.RedoBtn.setEnabled(redos.size()==0 ? false:true);  
        parent.AttributesToolBar.PenAttributesBox.PageW.setText(""+PageWidth);
        parent.AttributesToolBar.PenAttributesBox.PageH.setText(""+PageHeight);
        
    }
    Image paintPage(){
        bufferImage = createImage(PageWidth, PageHeight);
        bufferGraphics = bufferImage.getGraphics();
        parent.MainWin.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        Graphics2D g2 = (Graphics2D)bufferGraphics;  
        for(object p:Points){
            g2.setColor(p.PenColor);
            g2.setStroke(new BasicStroke(p.PenSize,CAP_ROUND,JOIN_ROUND));
            obj o = null;
            if(p.ObjEnum==ObjEnum.graffiti){
                g2.drawLine(p.Sp.x, p.Sp.y, p.Ep.x, p.Ep.y);
            }else if(p.ObjEnum==ObjEnum.rectangle&&PaintObj){
                o = new objRectangle(this,p.PenColor, p.PenSize);
                this.add(o, 0);
                o.setLocation((p.Sp.x < p.Ep.x) ? p.Sp.x : p.Ep.x, (p.Sp.y < p.Ep.y) ? p.Sp.y : p.Ep.y);
                o.setSize(Math.abs(p.Sp.x - p.Ep.x), Math.abs(p.Sp.y - p.Ep.y));
            } else if (p.ObjEnum == ObjEnum.circular && PaintObj) {
                o = new objCircular(this,p.PenColor, p.PenSize);
                this.add(o, 0);
                o.setLocation((p.Sp.x < p.Ep.x) ? p.Sp.x : p.Ep.x, (p.Sp.y < p.Ep.y) ? p.Sp.y : p.Ep.y);
                o.setSize(Math.abs(p.Sp.x - p.Ep.x), Math.abs(p.Sp.y - p.Ep.y));
            } else if (p.ObjEnum == ObjEnum.diamond && PaintObj) {
                o = new objDiamond(this,p.PenColor, p.PenSize);
                this.add(o, 0);
                o.setLocation((p.Sp.x < p.Ep.x) ? p.Sp.x : p.Ep.x, (p.Sp.y < p.Ep.y) ? p.Sp.y : p.Ep.y);
                o.setSize(Math.abs(p.Sp.x - p.Ep.x), Math.abs(p.Sp.y - p.Ep.y));
            } else if (p.ObjEnum == ObjEnum.arrow && PaintObj) {
                o = new objArrow(this,p.Sp,p.Ep,p.PenColor);
                this.add(o, 0);
                o.setLocation((p.Sp.x < p.Ep.x) ? p.Sp.x : p.Ep.x, (p.Sp.y < p.Ep.y) ? p.Sp.y : p.Ep.y);
                o.setSize(Math.abs(p.Sp.x - p.Ep.x), Math.abs(p.Sp.y - p.Ep.y));
            }
        }
        PaintObj=false;
        super.paintComponents(g2);
        return bufferImage;
    }
    void setPageSize(int W,int H){
        PageWidth=W;
        PageHeight=H;
        this.setPreferredSize(new Dimension(PageWidth,PageHeight));
        this.revalidate();
    }
     Point getPageSize(){
        return new Point(PageWidth,PageHeight);
    }
}
