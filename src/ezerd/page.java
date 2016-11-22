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
import java.awt.geom.GeneralPath;
import java.util.Stack;
import java.util.Vector;
        
/**
 *
 * @author CMC
 */

public class page extends Panel{
    Boolean PaintObj=true,ObjArrowJ=false,ArrowPaint=true;
    ezERD parent;
    Point Sp,Ep;
    Vector<object> Points,RePoints;
    Vector<objPoint> ObjPoints,ReObjPoints;
    Vector<objArrowXY> ObjArrowXYs,ReObjArrowXYs;
    Vector<obj> Objs,ReObjs;
    Stack<Integer> undos,redos;
    int undo=0,PageWidth=1400,PageHeight=800,ObjID=1;
    float PenSize=5;
    Color PenColor=new Color(0,0,0);
    rightClickMenu popupMenu1;
    Image bufferImage;
    Graphics bufferGraphics;
    pageActionEnum PageActionEnum;
    objEnum ObjEnum;
    obj activeObj,SObj,EObj,CopyObj;
    page(ezERD p){
        super();      
        parent=p;      
        Objs=new Vector<obj>();
        ObjPoints=new Vector<objPoint>();
        ReObjPoints=new Vector<objPoint>();
        ObjArrowXYs=new Vector<objArrowXY>();
        ReObjArrowXYs=new Vector<objArrowXY>();
        Points=new Vector<object>();
        RePoints=new Vector<object>();
        undos=new Stack<Integer>();
        redos=new Stack<Integer>();
        PageActionEnum=PageActionEnum.idle;
        activeObj=null;
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
                    Points.add(new object(Sp,Ep,PenSize,PenColor,ObjEnum.graffiti,0));
                    Sp=Ep;
                    undo++; 
                }else if(ObjEnum==ObjEnum.arrow){
                    Graphics2D g = (Graphics2D)page.this.getGraphics();
                    g.setXORMode(Color.yellow);
                    if (Ep != null) {
                        drawAL(Sp.x,Sp.y,Ep.x,Ep.y,g);
                    }
                    Ep = e.getPoint();
                    drawAL(Sp.x,Sp.y,Ep.x,Ep.y,g);
                }else if(PageActionEnum==PageActionEnum.creatingObject){
                    Graphics g = page.this.getGraphics();
                    g.setXORMode(Color.yellow);
                    if (Ep != null) {
                        g.drawRect((Sp.x < Ep.x) ? Sp.x : Ep.x, (Sp.y < Ep.y) ? Sp.y : Ep.y,
                                        Math.abs(Sp.x - Ep.x), Math.abs(Sp.y - Ep.y));
                    }
                    if (e.getModifiers() == 17) {
                        if(e.getX() < Sp.x && e.getY() < Sp.y){
                            Ep = Sp.x - e.getX() > Sp.y - e.getY()
                                    ?new Point(e.getX(),Sp.y-Sp.x + e.getX())
                                    : new Point(Sp.x-Sp.y + e.getY(),e.getY());
                        }else{
                            Ep = e.getX() - Sp.x > e.getY() - Sp.y
                                    ? new Point(e.getX(), e.getY() > Sp.y ? Sp.y + e.getX() - Sp.x : Sp.y - e.getX() + Sp.x)
                                    : new Point(e.getX() > Sp.x ? Sp.x + e.getY() - Sp.y : Sp.x - e.getY() + Sp.y, e.getY());
                        }
                    } else {
                        Ep = e.getPoint();
                    }
                    g.drawRect((Sp.x < Ep.x) ? Sp.x : Ep.x, (Sp.y < Ep.y) ? Sp.y : Ep.y, Math.abs(Sp.x - Ep.x), Math.abs(Sp.y - Ep.y));
                    
                }
                
                parent.MessageBar.XY=e.getPoint();
                parent.MessageBar.updateMessage();
            }
            
            @Override
            public void mouseMoved(MouseEvent e) {
                if (ObjEnum == ObjEnum.arrow && ObjArrowJ) {
                    Graphics2D g = (Graphics2D)page.this.getGraphics();
                    g.setXORMode(Color.yellow);
                    if (Ep != null) {
                        drawAL(Sp.x,Sp.y,Ep.x,Ep.y,g);
                    }
                    Ep = e.getPoint();
                    drawAL(Sp.x,Sp.y,Ep.x,Ep.y,g);
                }
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
                }else if(SObj!=null||EObj!=null){
                    SObj=EObj=null;
                    ObjArrowJ=false;
                }else if(ObjEnum==ObjEnum.arrow){
                    Sp=e.getPoint();
                }else if(PageActionEnum == PageActionEnum.ready2createObject ){
                    Sp = e.getPoint();
                    PageActionEnum = PageActionEnum.creatingObject;
                }
                setActiveObj(null);
            }
            
            @Override
            public void mouseReleased(MouseEvent e){
                //System.out.println("mouseReleased");
                if (e.getModifiers() == InputEvent.BUTTON3_MASK) {
                    popupMenu1.XY=e.getPoint();
                    popupMenu1.show(page.this, e.getX(), e.getY());
                } else if(ObjEnum == ObjEnum.graffiti){
                    if(undo!=0)
                        undos.add(undo);
                    RePoints.removeAllElements();
                    redos.removeAllElements();
                } else if (ObjEnum == ObjEnum.arrow && SObj == null && EObj == null && Ep != null) {
                    undos.add(1);
                    RePoints.removeAllElements();
                    redos.removeAllElements();
                    Graphics2D g = (Graphics2D)page.this.getGraphics();
                    g.setColor(PenColor);
                    drawAL(Sp.x,Sp.y,Ep.x,Ep.y,g);
                    Points.add(new object(Sp,Ep,1,PenColor,ObjEnum,0));
                } else if (PageActionEnum==PageActionEnum.creatingObject) {
                    Graphics g = page.this.getGraphics();
                    g.setXORMode(Color.yellow);
                    if (Ep != null && Sp.x != Ep.x && Sp.y != Ep.y) {
                        undos.add(1);
                        RePoints.removeAllElements();
                        redos.removeAllElements();
                        g.drawRect((Sp.x < Ep.x) ? Sp.x : Ep.x, (Sp.y < Ep.y) ? Sp.y : Ep.y
                                , Math.abs(Sp.x - Ep.x), Math.abs(Sp.y - Ep.y));
                        obj o = null;
                        if (ObjEnum == ObjEnum.rectangle) {
                            o = new objRectangle(page.this,PenColor, PenSize,ObjID);
                            page.this.add(o,0);
                        } else if (ObjEnum == ObjEnum.circular) {
                            o = new objCircular(page.this,PenColor, PenSize,ObjID);
                            page.this.add(o,0);
                        } else if (ObjEnum == ObjEnum.diamond) {
                            o = new objDiamond(page.this,PenColor, PenSize,ObjID);
                            page.this.add(o,0);
                        }
                        page.this.add(o, 0);
                        parent.AttributesToolBar.ObjList.addObj(ObjID);
                        parent.AttributesToolBar.ObjList.setActiveObj(ObjID);
                        o.setLocation((Sp.x < Ep.x) ? Sp.x : Ep.x, (Sp.y < Ep.y) ? Sp.y : Ep.y);
                        o.setSize(Math.abs(Sp.x - Ep.x), Math.abs(Sp.y - Ep.y));
                        Objs.add(o);
                        activeObj=o;
                        Points.add(new object(Sp,Ep,PenSize,PenColor,ObjEnum,ObjID++));
                    }
                    PageActionEnum=PageActionEnum.ready2createObject;
                }  
                //page.this.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
                Ep=Sp=null;
                page.this.repaint();
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
        parent.TopToolBar.UndoBtn.setEnabled(undos.size() != 0 );
        parent.MainWin.MenuBar.undoM.setEnabled(undos.size() != 0);
        popupMenu1.UndoMenuItem.setEnabled(undos.size() != 0);
        parent.TopToolBar.RedoBtn.setEnabled(redos.size() != 0);
        parent.MainWin.MenuBar.redoM.setEnabled(redos.size() != 0);
        popupMenu1.RedoMenuItem.setEnabled(redos.size() != 0);
        parent.TopToolBar.CopyBtn.setEnabled(activeObj != null);
        parent.MainWin.MenuBar.copyM.setEnabled(activeObj != null);
        popupMenu1.CopyMenuItem.setEnabled(activeObj != null);
        parent.TopToolBar.PasteBtn.setEnabled(CopyObj != null);
        parent.MainWin.MenuBar.pasteM.setEnabled(CopyObj != null);
        popupMenu1.PasteMenuItem.setEnabled(CopyObj != null);
        if(!parent.PageToolBar.Btns.elementAt(parent.PageToolBar.activeButton()).getText().endsWith("*")&&undos.size()!=0)
                    parent.PageToolBar.Btns.elementAt(parent.PageToolBar.activeButton()).setText(
                                                        parent.PageToolBar.Btns.elementAt(parent.PageToolBar.activeButton()).getText()+"*");
        
        parent.AttributesToolBar.AttributesBox.PageSizePanel.PageW.setText(""+PageWidth);
        parent.AttributesToolBar.AttributesBox.PageSizePanel.PageH.setText(""+PageHeight);
        PenColor=parent.AttributesToolBar.AttributesBox.ColorBox.getColor();
        if(activeObj!=null){
            parent.AttributesToolBar.AttributesBox.ObjAttributesPanel.setTextLocation(activeObj.getX(), activeObj.getY());
            parent.AttributesToolBar.AttributesBox.ObjAttributesPanel.setTextSize(activeObj.getWidth(),activeObj.getHeight());
            parent.AttributesToolBar.AttributesBox.ObjAttributesPanel.setTextTra(activeObj.getTra());
            if(PageActionEnum!=PageActionEnum.moving && activeObj.isVisible()){
                Graphics2D g2 = (Graphics2D) this.getGraphics();
                g2.setColor(Color.BLUE);
                //g2.rotate(Math.toRadians(50), activeObj.getX()+activeObj.getWidth()/2, activeObj.getY()+activeObj.getHeight()/2);
                g2.setStroke(new BasicStroke(2, CAP_ROUND, JOIN_ROUND));
                g2.drawRect(activeObj.getX() - 3, activeObj.getY() - 3,
                        activeObj.getWidth() + 6, activeObj.getHeight() + 6);
            }
        }else{
            parent.AttributesToolBar.AttributesBox.ObjAttributesPanel.setTextLocation(0,0);
            parent.AttributesToolBar.AttributesBox.ObjAttributesPanel.setTextSize(0,0);
            parent.AttributesToolBar.AttributesBox.ObjAttributesPanel.setTextTra(100);
        }
        
    }
    Image paintPage(){
        bufferImage = createImage(PageWidth, PageHeight);
        bufferGraphics = bufferImage.getGraphics();
        if(PaintObj)
            Objs.removeAllElements();
        parent.MainWin.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        Graphics2D g2 = (Graphics2D)bufferGraphics;  
        for(object p:Points){
            g2.setColor(p.PenColor);
            g2.setStroke(new BasicStroke(p.PenSize, CAP_ROUND, JOIN_ROUND));
            if (p.ObjEnum == ObjEnum.graffiti) {
                g2.drawLine(p.Sp.x, p.Sp.y, p.Ep.x, p.Ep.y);
            } else if (p.ObjEnum == ObjEnum.arrow) {
                drawAL(p.Sp.x, p.Sp.y, p.Ep.x, p.Ep.y, g2);
            } else if (p.ObjEnum != ObjEnum.graffiti && PaintObj) {
                obj o = null;
                if (p.ObjEnum == ObjEnum.rectangle) {
                    o = new objRectangle(this, p.PenColor, p.PenSize,p.ObjID);
                } else if (p.ObjEnum == ObjEnum.circular) {
                    o = new objCircular(this, p.PenColor, p.PenSize,p.ObjID);
                } else if (p.ObjEnum == ObjEnum.diamond) {
                    o = new objDiamond(this, p.PenColor, p.PenSize,p.ObjID);
                }
                activeObj=o;
                Objs.add(o);
                this.add(o, 0);
                parent.AttributesToolBar.ObjList.addObj(p.ObjID);
                parent.AttributesToolBar.ObjList.setActiveObj(p.ObjID);
                o.setLocation((p.Sp.x < p.Ep.x) ? p.Sp.x : p.Ep.x, (p.Sp.y < p.Ep.y) ? p.Sp.y : p.Ep.y);
                o.setSize(Math.abs(p.Sp.x - p.Ep.x), Math.abs(p.Sp.y - p.Ep.y));
            }
        }
        for(objArrowXY obja:ObjArrowXYs){
            if(obja.SObj!=null&&obja.EObj!=null){
                g2.setColor(obja.ArrowColor);
                int SX = obja.SObj.getX(), SY = obja.SObj.getY(), EX = obja.EObj.getX(), EY = obja.EObj.getY();
                if (obja.SObj.getX() + obja.SObj.getWidth() < obja.EObj.getX()) {
                    SX += obja.SObj.getWidth();
                    SY += obja.SObj.getHeight() / 2;
                    EY += obja.EObj.getHeight() / 2;
                } else if (obja.SObj.getX() > obja.EObj.getX() + obja.EObj.getWidth()) {
                    SY += obja.SObj.getHeight() / 2;
                    EX += obja.EObj.getWidth();
                    EY += obja.EObj.getHeight() / 2;
                } else {
                    if (obja.SObj.getY() + obja.SObj.getHeight() < obja.EObj.getY()) {
                        SX += obja.SObj.getWidth() / 2;
                        SY += obja.SObj.getHeight();
                        EX += obja.EObj.getWidth() / 2;
                    } else if (obja.SObj.getY() > obja.EObj.getY() + obja.EObj.getHeight()) {
                        SX += obja.SObj.getWidth() / 2;
                        EX += obja.EObj.getWidth() / 2;
                        EY += obja.EObj.getHeight();
                    } else {
                        SX += obja.SObj.getWidth() / 2;
                        SY += obja.SObj.getHeight() / 2;
                        EX += obja.EObj.getWidth() / 2;
                        EY += obja.EObj.getHeight() / 2;
                    }
                }
                g2.setStroke(new BasicStroke(1, CAP_ROUND, JOIN_ROUND));
                drawAL(SX, SY, EX, EY, g2);
                if (ArrowPaint) {
                    repaint();
                    ArrowPaint = false;
                }
            }
        }
        super.paintComponents(g2);
        PaintObj=false;
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
    void setActiveObj(obj o){
        activeObj=o;
        if(o!=null)
            parent.AttributesToolBar.ObjList.setActiveObj(o.ID);
        else{
            parent.AttributesToolBar.ObjList.setActiveObj(0);
        }
        this.repaint();
    }
     
     /*http://blog.csdn.net/wqjsir/article/details/6095277*/
    public static void drawAL(int sx, int sy, int ex, int ey, Graphics2D g2) {
        double H = 20; // 箭头高度  
        double L = 10; // 底边的一半  
        int x3 = 0;
        int y3 = 0;
        int x4 = 0;
        int y4 = 0;
        double awrad = Math.atan(L / H); // 箭头角度  
        double arraow_len = Math.sqrt(L * L + H * H); // 箭头的长度  
        double[] arrXY_1 = rotateVec(ex - sx, ey - sy, awrad, true, arraow_len);
        double[] arrXY_2 = rotateVec(ex - sx, ey - sy, -awrad, true, arraow_len);
        double x_3 = ex - arrXY_1[0]; // (x3,y3)是第一端点  
        double y_3 = ey - arrXY_1[1];
        double x_4 = ex - arrXY_2[0]; // (x4,y4)是第二端点  
        double y_4 = ey - arrXY_2[1];

        Double X3 = new Double(x_3);
        x3 = X3.intValue();
        Double Y3 = new Double(y_3);
        y3 = Y3.intValue();
        Double X4 = new Double(x_4);
        x4 = X4.intValue();
        Double Y4 = new Double(y_4);
        y4 = Y4.intValue();
        // 画线  
        g2.drawLine(sx, sy, ex, ey);
        //  
        GeneralPath triangle = new GeneralPath();
        triangle.moveTo(ex, ey);
        triangle.lineTo(x3, y3);
        triangle.lineTo(x4, y4);
        //triangle.closePath();  
        //实心箭头  
        g2.fill(triangle);
        //非实心箭头  
        //g2.draw(triangle);  

    }

    // 计算  
    public static double[] rotateVec(int px, int py, double ang,
            boolean isChLen, double newLen) {

        double mathstr[] = new double[2];
        // 矢量旋转函数，参数含义分别是x分量、y分量、旋转角、是否改变长度、新长度  
        double vx = px * Math.cos(ang) - py * Math.sin(ang);
        double vy = px * Math.sin(ang) + py * Math.cos(ang);
        if (isChLen) {
            double d = Math.sqrt(vx * vx + vy * vy);
            vx = vx / d * newLen;
            vy = vy / d * newLen;
            mathstr[0] = vx;
            mathstr[1] = vy;
        }
        return mathstr;
    }  
    /*http://blog.csdn.net/wqjsir/article/details/6095277*/
}
