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

public class ezPage extends Panel{
    Boolean PaintObj=true,ObjArrowJ=false,ArrowPaint=true;
    ezERD parent;
    Point Sp,Ep;
    Vector<ezObject> Points,UndoPoints,RedoPoints;
    Vector<ezObjArrowXY> ObjArrowXYs,ReObjArrowXYs;
    Vector<ezObj> Objs;
    Stack<Integer> undos,redos;
    int undo=0,PageWidth=1400,PageHeight=800,ObjID=1;
    float PenSize=3;
    ezRightClickMenu popupMenu1;
    Image bufferImage;
    Graphics bufferGraphics;
    ezPageActionEnum PageActionEnum;
    ezObjEnum ObjEnum;
    ezObj activeObj;
    ezObj SObj,EObj,CopyObj;
    ezMoveNode MoveNode;
    ezPage(ezERD p){
        super();      
        parent=p;      
        Objs=new Vector<ezObj>();
        ObjArrowXYs=new Vector<ezObjArrowXY>();
        ReObjArrowXYs=new Vector<ezObjArrowXY>();
        Points=new Vector<ezObject>();
        UndoPoints=new Vector<ezObject>();
        RedoPoints=new Vector<ezObject>();
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
                ezObjAttributesPanel temp=parent.AttributesToolBar.AttributesBox.ObjAttributesPanel;
                //System.out.println("mouseReleased");
                if (e.getModifiers() == InputEvent.BUTTON3_MASK) {
                }else if(ObjEnum == ObjEnum.graffiti){
                    Graphics2D g = (Graphics2D)ezPage.this.getGraphics();    
                    g.setStroke(new BasicStroke(PenSize,CAP_ROUND,JOIN_ROUND));
                    g.setColor(temp.LineColorBtn.getBackground());
                    Ep=e.getPoint();
                    g.drawLine(Sp.x, Sp.y, Ep.x, Ep.y);
                    Points.add(new ezObject(Sp,Ep,PenSize,temp.LineColorBtn.getBackground(),temp.BGColorBtn.getBackground(),
                                temp.TextColorBtn.getBackground(),ObjEnum.graffiti,0,0,""));
                    Sp=Ep;
                    undo++; 
                }else if(ObjEnum==ObjEnum.arrow && Sp!=null){
                    Graphics2D g = (Graphics2D)ezPage.this.getGraphics();
                    g.setXORMode(Color.yellow);
                    if (Ep != null) {
                        drawAL(Sp.x,Sp.y,Ep.x,Ep.y,g,temp.choice.getSelectedIndex());
                    }
                    Ep = e.getPoint();
                    drawAL(Sp.x,Sp.y,Ep.x,Ep.y,g,temp.choice.getSelectedIndex());
                }else if(PageActionEnum==PageActionEnum.creatingObject){
                    Graphics g = ezPage.this.getGraphics();
                    g.setXORMode(Color.yellow);
                    if (Ep != null) {
                        g.drawRect((Sp.x < Ep.x) ? Sp.x : Ep.x, (Sp.y < Ep.y) ? Sp.y : Ep.y,
                                        Math.abs(Sp.x - Ep.x), Math.abs(Sp.y - Ep.y));
                    }
                    if (e.getModifiers() == 17) {
                        if(e.getX() < Sp.x && e.getY() < Sp.y){
                            Ep = Sp.x - e.getX() > Sp.y - e.getY()
                                    ? new Point(e.getX(),Sp.y-Sp.x + e.getX())
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
                ezObjAttributesPanel temp=parent.AttributesToolBar.AttributesBox.ObjAttributesPanel;
                if (ObjEnum == ObjEnum.arrow && ObjArrowJ && Sp!=null) {
                    Graphics2D g = (Graphics2D)ezPage.this.getGraphics();
                    g.setXORMode(Color.yellow);
                    if (Ep != null) {
                        drawAL(Sp.x,Sp.y,Ep.x,Ep.y,g,temp.choice.getSelectedIndex());
                    }
                    Ep = e.getPoint();
                    drawAL(Sp.x,Sp.y,Ep.x,Ep.y,g,temp.choice.getSelectedIndex());
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
                PenSize=Float.valueOf(parent.AttributesToolBar.AttributesBox.ObjAttributesPanel.PenSizeText.getText());
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
                ezObjAttributesPanel temp=parent.AttributesToolBar.AttributesBox.ObjAttributesPanel;
                //System.out.println("mouseReleased");
                if (e.getModifiers() == InputEvent.BUTTON3_MASK) {
                    popupMenu1.XY=e.getPoint();
                    popupMenu1.show(ezPage.this, e.getX(), e.getY());
                } else if(ObjEnum == ObjEnum.graffiti){
                    if(undo!=0)
                        undos.add(undo);
                    RedoPoints.removeAllElements();
                    redos.removeAllElements();
                } else if (ObjEnum == ObjEnum.arrow && SObj == null && EObj == null && Ep != null) {
                    undos.add(1);
                    RedoPoints.removeAllElements();
                    redos.removeAllElements();
                    Graphics2D g = (Graphics2D)ezPage.this.getGraphics();
                    g.setColor(temp.LineColorBtn.getBackground());
                    drawAL(Sp.x,Sp.y,Ep.x,Ep.y,g,temp.choice.getSelectedIndex());
                    Points.add(new ezObject(Sp,Ep,1,temp.LineColorBtn.getBackground(),temp.BGColorBtn.getBackground(),
                                temp.TextColorBtn.getBackground(),ObjEnum,0,temp.choice.getSelectedIndex(),""));
                } else if (PageActionEnum==PageActionEnum.creatingObject) {
                    if (Ep != null && Sp.x != Ep.x && Sp.y != Ep.y) {
                        undos.add(1);
                        RedoPoints.removeAllElements();
                        redos.removeAllElements();
                        Graphics g = ezPage.this.getGraphics();
                        g.setXORMode(Color.yellow);
                        g.drawRect((Sp.x < Ep.x) ? Sp.x : Ep.x, (Sp.y < Ep.y) ? Sp.y : Ep.y
                                , Math.abs(Sp.x - Ep.x), Math.abs(Sp.y - Ep.y));
                        ezObj o = null;
                        if (ObjEnum == ObjEnum.rectangle) {
                            o = new ezObjRectangle(ezPage.this,temp.LineColorBtn.getBackground()
                                    ,temp.BGColorBtn.getBackground(),temp.TextColorBtn.getBackground(), PenSize,ObjID
                                    ,temp.choice.getSelectedIndex(),"",true);
                        } else if (ObjEnum == ObjEnum.circular) {
                            o = new ezObjCircular(ezPage.this,temp.LineColorBtn.getBackground()
                                    ,temp.BGColorBtn.getBackground(),temp.TextColorBtn.getBackground(), PenSize,ObjID
                                    ,temp.choice.getSelectedIndex(),"",true);
                        } else if (ObjEnum == ObjEnum.diamond) {
                            o = new ezObjDiamond(ezPage.this,temp.LineColorBtn.getBackground()
                                    ,temp.BGColorBtn.getBackground(),temp.TextColorBtn.getBackground(), PenSize,ObjID
                                    ,temp.choice.getSelectedIndex(),"",true);
                        }else if (ObjEnum == ObjEnum.text) {
                            o = new ezObjText(ezPage.this,temp.LineColorBtn.getBackground()
                                    ,temp.BGColorBtn.getBackground(),temp.TextColorBtn.getBackground(), PenSize,ObjID
                                    ,temp.choice.getSelectedIndex(),"text",true);
                        }
                        ezPage.this.add(o, 0);
                        parent.AttributesToolBar.ObjList.addObj(ObjID);
                        parent.AttributesToolBar.ObjList.setActiveObj(ObjID);
                       
                        o.setObjLocation((Sp.x < Ep.x) ? Sp.x : Ep.x, (Sp.y < Ep.y) ? Sp.y : Ep.y,false);
                        o.setObjSize(Math.abs(Sp.x - Ep.x), Math.abs(Sp.y - Ep.y),false);
                        Objs.add(o);
                        activeObj=o;
                        Points.add(new ezObject(Sp,Ep,PenSize,o.PenColor,o.BGColor,o.TextColor,ObjEnum,ObjID++,
                                    temp.choice.getSelectedIndex(),o.str));
                        o.setTra(Integer.valueOf(temp.TextTra.getText()), false);
                        o.setXYwh();
                    }
                    PageActionEnum=PageActionEnum.ready2createObject;
                }  
                //page.this.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
                Ep=Sp=null;
                ezPage.this.repaint();
            }
        });
        popupMenu1 =new ezRightClickMenu(parent);
        popupMenu1.setFont(new ezFont());
        this.add(popupMenu1);
        MoveNode = new ezMoveNode(this);
        //this.add(MoveNode);
        for(Component a:this.getComponents())/**/
            a.addKeyListener(new ezKeyListener(parent));/**/
        this.addKeyListener(new ezKeyListener(parent));/**/

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
        parent.TopToolBar.DelBtn.setEnabled(activeObj != null);
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
        if(activeObj!=null){
            parent.AttributesToolBar.AttributesBox.ObjAttributesPanel.setActiveObjAtt(activeObj);
            if(PageActionEnum!=PageActionEnum.moving && activeObj.isVisible()&&MoveNode.isRotateing()){
                MoveNode.ShowNode();
            }else
                MoveNode.HideNode();
        }else{
            parent.AttributesToolBar.AttributesBox.ObjAttributesPanel.setTextLocation(0,0);
            parent.AttributesToolBar.AttributesBox.ObjAttributesPanel.setTextSize(0,0);
            MoveNode.HideNode();
        }
        
    }
    Image paintPage(){
        bufferImage = createImage(PageWidth, PageHeight);
        bufferGraphics = bufferImage.getGraphics();
        if(PaintObj){
            Objs.removeAllElements();
            MoveNode = new ezMoveNode(this);
        }
        parent.MainWin.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        Graphics2D g2 = (Graphics2D)bufferGraphics;  
        for (ezObject p : Points) {
            g2.setColor(p.PenColor);
            g2.setStroke(new BasicStroke(p.PenSize, CAP_ROUND, JOIN_ROUND));
            if (p.ObjEnum == ObjEnum.graffiti) {
                g2.drawLine(p.Sp.x, p.Sp.y, p.Ep.x, p.Ep.y);
            } else if (p.ObjEnum == ObjEnum.arrow) {
                drawAL(p.Sp.x, p.Sp.y, p.Ep.x, p.Ep.y, g2,p.LineSD);
            } else if (p.ObjEnum != ObjEnum.graffiti && PaintObj) {
                ezObj o = null;
                System.out.println(p.ObjEnum);
                if (p.ObjEnum == ObjEnum.rectangle) {
                    o = new ezObjRectangle(this, p.PenColor, p.BGColor, p.TextColor, p.PenSize, p.ObjID, p.LineSD, p.str,p.Visible);
                } else if (p.ObjEnum == ObjEnum.circular) {
                    o = new ezObjCircular(this, p.PenColor, p.BGColor, p.TextColor, p.PenSize, p.ObjID, p.LineSD, p.str,p.Visible);
                } else if (p.ObjEnum == ObjEnum.diamond) {
                    o = new ezObjDiamond(this, p.PenColor, p.BGColor, p.TextColor, p.PenSize, p.ObjID, p.LineSD, p.str,p.Visible);
                } else if (p.ObjEnum == ObjEnum.text) {
                    o = new ezObjText(this, p.PenColor, p.BGColor, p.TextColor, p.PenSize, p.ObjID, p.LineSD, p.str,p.Visible);
                }
                o.setArr(p);
                Objs.add(o);
                this.add(o, 0);
                ObjID = p.ObjID + 1;
                if(p.Visible)
                    parent.AttributesToolBar.ObjList.addObj(p.ObjID);
                o.setLocation((p.Sp.x < p.Ep.x) ? p.Sp.x : p.Ep.x, (p.Sp.y < p.Ep.y) ? p.Sp.y : p.Ep.y);
                o.setSize(Math.abs(p.Sp.x - p.Ep.x), Math.abs(p.Sp.y - p.Ep.y));
                activeObj = null;
            }

        }
        for(ezObjArrowXY obja:ObjArrowXYs){
            for (ezObj o : Objs) {
                if (obja.SObjID == o.ID) {
                    obja.SObj = o;
                }
                if (obja.EObjID == o.ID) {
                    obja.EObj = o;
                }
            }
            if (obja.SObj != null && obja.EObj != null) {
                if (obja.SObj.Visible && obja.EObj.Visible) {
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
                    drawAL(SX, SY, EX, EY, g2,obja.Solid);
                    if (ArrowPaint) {
                        repaint();
                        ArrowPaint = false;
                    }
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
    void setActiveObj(ezObj o){
        activeObj=o;
        if(o!=null)
            parent.AttributesToolBar.ObjList.setActiveObj(o.ID);
        else{
            parent.AttributesToolBar.ObjList.setActiveObj(0);
        }
        this.repaint();
    }
     
     /*http://blog.csdn.net/wqjsir/article/details/6095277*/
    public static void drawAL(int sx, int sy, int ex, int ey, Graphics2D g2,int s) {
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
        if (s == 1) {
            g2.setStroke(new BasicStroke(1, CAP_ROUND, JOIN_ROUND,
                    0, new float[]{12, 6}, 0));
        }else{
            g2.setStroke(new BasicStroke(1, CAP_ROUND, JOIN_ROUND));
        }
        g2.drawLine(sx, sy, ex, ey);
        //  
        GeneralPath triangle = new GeneralPath();
        triangle.moveTo(ex, ey);
        triangle.lineTo(x3, y3);
        triangle.lineTo(x4, y4);
        //triangle.closePath();  
        //实心箭头  
        g2.setStroke(new BasicStroke(1, CAP_ROUND, JOIN_ROUND));
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
