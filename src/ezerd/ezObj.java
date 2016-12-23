/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ezerd;
import static ezerd.ezPage.drawAL;
import java.awt.*;
import static java.awt.BasicStroke.CAP_ROUND;
import static java.awt.BasicStroke.JOIN_ROUND;
import java.awt.event.*;
/**
 *
 * @author CMC
 */ 

public abstract class ezObj extends Component {

    enum LineEnum {
        Solid,Dotted
    } //實線 虛線
    LineEnum Line=LineEnum.Solid;
    ezObjEnum ObjEnum;
    Color PenColor,TextColor,BGColor;
    float PenSize;
    ezPage parent;
    Point Sp,Ep;
    Point ArrSp,ArrEp;
    ezObj EndObj;
    int ID,Tra=100,Angle=0;
    int w,h,X,Y;
    String str;
    ezPageActionEnum tempPAE;
    TextField TempTextField;
    Boolean Visible;
    ezObj(){  
    }
    ezObj(ezPage p,Color c,Color c1,Color c2,float s,int id,int LineSD,String S,Boolean V){
        parent=p;
        PenColor=c;
        BGColor=c1;
        TextColor=c2;
        setLine(LineSD,false);
        PenSize = s;
        ID=id;
        str=S;
        Visible=V;
        this.setVisible(Visible);
        this.addMouseMotionListener(new MouseAdapter(){
            @Override
            public void mouseDragged(MouseEvent e){
                if(parent.ObjEnum==ezObjEnum.arrow){
                    
                }else if(parent.PageActionEnum==ezPageActionEnum.moving){
                    if(Ep==null)
                        ezObj.this.addUndo();
                    Ep = e.getPoint();
                    ezObj.this.setObjLocation(X + (Ep.x - Sp.x),
                                         Y + (Ep.y - Sp.y),false);
                }
                parent.ArrowPaint=true;
            } 
            @Override
            public void mouseMoved(MouseEvent e) {
                if (parent.ObjArrowJ) {
                    Graphics2D g = (Graphics2D) parent.getGraphics();
                    g.setColor(Color.BLUE);
                    g.setStroke(new BasicStroke(2, CAP_ROUND, JOIN_ROUND));
                    g.drawRect(ezObj.this.getX()-3, ezObj.this.getY()-3,
                                ezObj.this.getWidth() + 6, ezObj.this.getHeight() + 6);
                    
                    Graphics2D g2 = (Graphics2D)parent.getGraphics();
                    g2.setXORMode(Color.yellow);
                    if (parent.Ep != null) {
                        drawAL(parent.Sp.x,parent.Sp.y,parent.Ep.x,parent.Ep.y,g2
                        ,parent.parent.AttributesToolBar.AttributesBox.ObjAttributesPanel.choice.getSelectedIndex());
                    }
                    parent.Ep=new Point(ezObj.this.getX()+e.getX(),ezObj.this.getY()+e.getY());
                    drawAL(parent.Sp.x,parent.Sp.y,parent.Ep.x,parent.Ep.y,g2
                        ,parent.parent.AttributesToolBar.AttributesBox.ObjAttributesPanel.choice.getSelectedIndex());
                }
                if (parent.ObjEnum == ezObjEnum.arrow) {
                    ezObj.this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
                } else {
                    ezObj.this.setCursor(new Cursor(Cursor.MOVE_CURSOR));
                }
            }
        });
        this.addMouseListener(new MouseAdapter(){
            @Override
            public void mousePressed(MouseEvent e){
                tempPAE=parent.PageActionEnum;
                parent.PageActionEnum=ezPageActionEnum.moving;
                if(parent.ObjEnum==ezObjEnum.arrow&&!parent.ObjArrowJ){
                    parent.ObjArrowJ=true;
                    parent.Sp=new Point(ezObj.this.getX()+ezObj.this.getWidth()/2
                                                ,ezObj.this.getY()+ezObj.this.getHeight()/2);
                    parent.SObj=ezObj.this;
                }else if(parent.ObjEnum==ezObjEnum.arrow&&parent.ObjArrowJ){
                    parent.ObjArrowJ=false;
                    parent.Ep=new Point(ezObj.this.getX()+ezObj.this.getWidth()/2,ezObj.this.getY()+ezObj.this.getHeight()/2);
                    parent.EObj=ezObj.this;
                    parent.ObjArrowXYs.add(new ezObjArrowXY(parent.SObj,parent.EObj
                                                    ,parent.SObj.ID,parent.EObj.ID
                            ,parent.parent.AttributesToolBar.AttributesBox.ObjAttributesPanel.LineColorBtn.getBackground()
                            ,parent.parent.AttributesToolBar.AttributesBox.ObjAttributesPanel.choice.getSelectedIndex()));
                    parent.undos.add(-1);
                    parent.RedoPoints.removeAllElements();
                    parent.redos.removeAllElements();
                    parent.SObj=null;
                    parent.EObj=null;
                    parent.repaint();
                    parent.Ep=null;
                }else if(parent.PageActionEnum==ezPageActionEnum.moving){
                    Sp=e.getPoint();
                    parent.parent.AttributesToolBar.AttributesBox.ObjAttributesPanel.setTextLocation(ezObj.this.getX(), ezObj.this.getY());
                    parent.parent.AttributesToolBar.AttributesBox.ObjAttributesPanel.setTextSize(ezObj.this.getWidth(), ezObj.this.getHeight());
                    
                    parent.setActiveObj(ezObj.this);
                    parent.repaint();
                }
            }
            @Override
            public void mouseReleased(MouseEvent e){
                if (parent.ObjEnum == ezObjEnum.arrow) {
                } else if (parent.PageActionEnum == ezPageActionEnum.moving) {
                    ezObj.this.setPoints();
                    parent.PageActionEnum = tempPAE;
                    if (e.getClickCount() != 1) {
                        if(TempTextField!=null)
                            parent.remove(TempTextField);
                        TempTextField=new TextField(ezObj.this.str);
                        TempTextField.setLocation(ezObj.this.getX()+20,ezObj.this.getY()+ezObj.this.getHeight()/2-15);
                        TempTextField.setSize(ezObj.this.getWidth()-40, 30);
                        TempTextField.setFont(new ezFont());
                        TempTextField.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                ezObj.this.setText(TempTextField.getText(),true);
                                parent.remove(TempTextField);
                                parent.parent.MainWin.requestFocusInWindow();
                                ezObj.this.repaint();
                            }
                        });
                        parent.add(TempTextField,0);
                    }
                }
                parent.repaint();
                Ep = null;
                if (e.getModifiers() == InputEvent.BUTTON3_MASK) {
                    parent.popupMenu1.XY = new Point(ezObj.this.getX() + e.getX(), ezObj.this.getY() + e.getY());
                    parent.popupMenu1.show(ezObj.this, e.getX(), e.getY());
                }
                
            }
            @Override
            public void mouseEntered(MouseEvent e) {
            }
            @Override
            public void mouseExited(MouseEvent e) {
                if (parent.ObjArrowJ) {
                    Graphics2D g=(Graphics2D)parent.getGraphics();
                    g.setXORMode(new Color(255,255,0));
                    g.setStroke(new BasicStroke(2,CAP_ROUND,JOIN_ROUND));
                    g.drawRect(ezObj.this.getX()-3, ezObj.this.getY()-3,
                                ezObj.this.getWidth() + 6, ezObj.this.getHeight() + 6);
                }
            }
        });
    }
    
    void setTra(int n,Boolean b){
        if(b)
            this.addUndo();
        Tra=n;
        PenColor=new Color(PenColor.getRed(),PenColor.getGreen(),PenColor.getBlue(),(int)(Tra*2.55));
        TextColor=new Color(TextColor.getRed(),TextColor.getGreen(),TextColor.getBlue(),(int)(Tra*2.55));
        BGColor=new Color(BGColor.getRed(),BGColor.getGreen(),BGColor.getBlue(),(int)(Tra*2.55));
        this.repaint();
        if(b)
            this.setPoints();
    }
    
    int getTra(){
       return Tra; 
    }
    void addUndo(){
        parent.UndoPoints.add(new ezObject(this.getLocation(),
                new Point(getX() + getWidth(), getY() + getHeight()),
                PenSize, PenColor, BGColor, TextColor, ObjEnum, ID,
                getLine(), str, Angle, Tra, X, Y, w, h, Visible));
        parent.undos.add(0);
        parent.RedoPoints.removeAllElements();
        parent.redos.removeAllElements();
        parent.parent.TopToolBar.UndoBtn.setEnabled(parent.undos.size() == 0 ? false : true);
        parent.parent.TopToolBar.RedoBtn.setEnabled(parent.redos.size() == 0 ? false : true);
        
    }
    void setPoints(){
        for (ezObject o : parent.Points) {
            if (o.ObjID == ID) {
                o.Sp = this.getLocation();
                o.Ep = new Point(ezObj.this.getX() + ezObj.this.getWidth(), ezObj.this.getY() + ezObj.this.getHeight());
                o.PenSize=PenSize;
                o.PenColor=PenColor;
                o.BGColor=BGColor;
                o.TextColor=TextColor;
                o.LineSD=getLine();
                o.str=str;
                o.Angle=Angle;
                o.Tra=Tra;
                o.x=X;
                o.y=Y;
                o.w=w;
                o.h=h;
                o.Visible=Visible;
            }
        }
        parent.repaint();
    }
    void setObjSize(int W,int H,Boolean b){
        if (TempTextField != null) {
            TempTextField.setLocation(getX() + 20, getY() + getHeight() / 2 - 15);
            TempTextField.setSize(getWidth() - 40, 30);
        }
        if(b)
            this.addUndo();
        double Tx,Ty,T;//克拉瑪
        T = Math.abs(Math.cos(Math.toRadians(Angle))) * Math.abs(Math.cos(Math.toRadians(Angle))) 
                - Math.abs(Math.sin(Math.toRadians(Angle))) * Math.abs(Math.sin(Math.toRadians(Angle)));
        Tx = W * Math.abs(Math.cos(Math.toRadians(Angle))) - H * Math.abs(Math.sin(Math.toRadians(Angle)));
        Ty = Math.abs(Math.cos(Math.toRadians(Angle))) * H - Math.abs(Math.sin(Math.toRadians(Angle))) * W;
        this.setSize(W,H);
        w=(int)(Tx/T);
        h=(int)(Ty/T);
        this.repaint();
        if(b)
            this.setPoints();
    }

    void setObjLocation(int X,int Y,Boolean b){
        if (TempTextField != null) {
            TempTextField.setLocation(getX() + 20, getY() + getHeight() / 2 - 15);
            TempTextField.setSize(getWidth() - 40, 30);
        }
        if(b)
            this.addUndo();
        this.X=X;
        this.Y=Y;
        this.setLocation(X,Y);
        this.repaint();
        if(b)
            this.setPoints();
    }
    void setAngle(int A,Boolean b){
        if(b)
            this.addUndo();
        Angle=A%360;
        this.setSize((int)(w * Math.abs(Math.cos(Math.toRadians(Angle))) + h * Math.abs(Math.sin(Math.toRadians(Angle)))) ,
                 (int)(w * Math.abs(Math.sin(Math.toRadians(Angle))) + h * Math.abs(Math.cos(Math.toRadians(Angle)))));
        this.setLocation(X-this.getWidth()/2+w/2, Y-this.getHeight()/2+h/2);
        this.repaint();
        if(b)
            this.setPoints();
    }
    int getAngle(){
        return Angle;
    }
    void setXYwh(int a,int b,int c,int d,Boolean B){
        if(B)
            this.addUndo();
        w=a;
        h=b;
        X=c;
        Y=d;
        if(B)
            this.setPoints();
    }
    void setXYwh(){
        w=this.getWidth();
        h=this.getHeight();
        X=this.getX();
        Y=this.getY();
        this.setPoints();
    }
    void setPenSize(int size,Boolean b) {
        if(b)
            this.addUndo();
        PenSize=size;
        if(b)
            this.setPoints();
        this.repaint();
    }
    void setLine(int n,Boolean b){
        if(b)
            this.addUndo();
        if(n==0)
            Line=LineEnum.Solid;
        else
            Line=LineEnum.Dotted;
        this.repaint();
        if(b)
            this.setPoints();
    }
    int getLine(){
        if(Line==LineEnum.Solid)
            return 0;
        else
            return 1;
    }
    void setText(String s,Boolean b){
        if(b)
            this.addUndo();
        str=s;
        if(b)
            this.setPoints();
    }
    void setArr(ezObject p) {
        setText(p.str,false);
        setLine(p.LineSD,false);
        setXYwh(p.w,p.h,p.x,p.y,false);
        setAngle(p.Angle,false);
        setTra(p.Tra,false);
        setObjVisible(p.Visible,false);
    }
    void setPenColor(Color c, Boolean b) {
        if(b)
            this.addUndo();
        PenColor=new Color(c.getRed(),c.getGreen(),c.getBlue(),(int)(Tra*2.55));
        if(b)
            this.setPoints();
    }

    void setBGColor(Color c, Boolean b) {
        if(b)
            this.addUndo();
        BGColor=new Color(c.getRed(),c.getGreen(),c.getBlue(),(int)(Tra*2.55));
        if(b)
            this.setPoints();
    }

    void setTextColor(Color c, Boolean b) {
        if(b)
            this.addUndo();
        TextColor=new Color(c.getRed(),c.getGreen(),c.getBlue(),(int)(Tra*2.55));
        if(b)
            this.setPoints();
    }
    
    void setObjVisible(Boolean V,Boolean b) {
        if(b)
            this.addUndo();
        Visible=V;
        this.setVisible(Visible);
        if(b)
            this.setPoints();
    }
    
    public abstract void paintObj(Graphics g);
    public void paint(Graphics g)
    {
        paintObj(g);
        for(ezObjListPanel p:parent.parent.AttributesToolBar.AttributesBox.AtoolBar.ObjList.objListPanels){
            if(p.ObjID==this.ID)
                p.repaint();
        }
    }

}
