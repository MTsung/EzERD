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

    enum LineEnum {
        Solid,Dotted
    } //實線 虛線
    LineEnum Line=LineEnum.Solid;
    Color PenColor,TextColor,BGColor;
    float PenSize;
    page parent;
    Point Sp,Ep;
    Point ArrSp,ArrEp;
    obj EndObj;
    int ID,Tra=100,Angle=0;
    int w,h,X,Y;
    String str;
    pageActionEnum tempPAE;
    TextField TempTextField;
    obj(){  
    }
    obj(page p,Color c,Color c1,Color c2,float s,int id,int LineSD,String S){
        parent=p;
        PenColor=c;
        BGColor=c1;
        TextColor=c2;
        setLine(LineSD,false);
        PenSize = s;
        ID=id;
        str=S;
        this.addMouseMotionListener(new MouseAdapter(){
            @Override
            public void mouseDragged(MouseEvent e){
                if(parent.ObjEnum==objEnum.arrow){
                    
                }else if(parent.PageActionEnum==pageActionEnum.moving){
                    if(Ep==null)
                        obj.this.addUndo();
                    Ep = e.getPoint();
                    obj.this.setObjLocation(X + (Ep.x - Sp.x),
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
                if (parent.ObjEnum == objEnum.arrow) {
                    obj.this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
                } else {
                    obj.this.setCursor(new Cursor(Cursor.MOVE_CURSOR));
                }
            }
        });
        this.addMouseListener(new MouseAdapter(){
            @Override
            public void mousePressed(MouseEvent e){
                parent.setActiveObj(obj.this);
                parent.repaint();
                tempPAE=parent.PageActionEnum;
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
                                                    ,parent.SObj.ID,parent.EObj.ID
                            ,parent.parent.AttributesToolBar.AttributesBox.ObjAttributesPanel.LineColorBtn.getBackground()));
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
                    Graphics2D g=(Graphics2D)parent.getGraphics();
                    g.setXORMode(new Color(255,255,0));
                    g.setStroke(new BasicStroke(2,CAP_ROUND,JOIN_ROUND));
                    g.drawRect(obj.this.getX()-3, obj.this.getY()-3,
                                obj.this.getWidth() + 6, obj.this.getHeight() + 6);
                }
            }
            @Override
            public void mouseReleased(MouseEvent e){
                if (parent.ObjEnum == objEnum.arrow) {
                } else if (parent.PageActionEnum == pageActionEnum.moving) {
                    obj.this.setPoints();
                    parent.PageActionEnum = tempPAE;
                    if (e.getClickCount() != 1) {
                        if(TempTextField!=null)
                            parent.remove(TempTextField);
                        TempTextField=new TextField(obj.this.str);
                        TempTextField.setLocation(X+20, Y+h/2-15);
                        TempTextField.setSize(w-40, 30);
                        TempTextField.setFont(new programFont());
                        TempTextField.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                obj.this.setText(TempTextField.getText(),true);
                                parent.remove(TempTextField);
                                obj.this.repaint();
                            }
                        });
                        parent.add(TempTextField,0);
                    }
                }
                parent.repaint();
                Ep = null;
                if (e.getModifiers() == InputEvent.BUTTON3_MASK) {
                    parent.popupMenu1.XY = new Point(obj.this.getX() + e.getX(), obj.this.getY() + e.getY());
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
        System.out.println(this.Angle);
        parent.UnObjPoints.add(new objPoint(this.getLocation(),
                 new Point(obj.this.getX() + obj.this.getWidth(), obj.this.getY() + obj.this.getHeight()),
                 this.PenSize, this.PenColor, this.BGColor, this.TextColor, this.ID,
                 this.getLine(), this.str, this.Tra, this.Angle));

        parent.undos.add(0);
        parent.ReObjPoints.removeAllElements();
        parent.redos.removeAllElements();
        parent.parent.TopToolBar.UndoBtn.setEnabled(parent.undos.size() == 0 ? false : true);
        parent.parent.TopToolBar.RedoBtn.setEnabled(parent.redos.size() == 0 ? false : true);
        
        /*
        for (object o : parent.Points) {
            if (o.ObjID == ID) {
                o.Sp = this.getLocation();
                o.Ep = new Point(obj.this.getX() + obj.this.getWidth(), obj.this.getY() + obj.this.getHeight());
                parent.UnObjPoints.add(new objPoint(o.Sp, o.Ep, ID));

                parent.undos.add(0);
                parent.ReObjPoints.removeAllElements();
                parent.redos.removeAllElements();
                parent.parent.TopToolBar.UndoBtn.setEnabled(parent.undos.size() == 0 ? false : true);
                parent.parent.TopToolBar.RedoBtn.setEnabled(parent.redos.size() == 0 ? false : true);
            }
        }*/
    }
    void setPoints(){
        for (object o : parent.Points) {
            if (o.ObjID == ID) {
                o.Sp = this.getLocation();
                o.Ep = new Point(obj.this.getX() + obj.this.getWidth(), obj.this.getY() + obj.this.getHeight());
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
            }
        }
        parent.repaint();
    }
    void setObjSize(int W,int H,Boolean b){
        if (TempTextField != null) {
            TempTextField.setLocation(X + 20, Y + h / 2 - 15);
            TempTextField.setSize(w - 40, 30);
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
            TempTextField.setLocation(X + 20, Y + h / 2 - 15);
            TempTextField.setSize(w - 40, 30);
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
    void setArr(object p) {
        setText(p.str,false);
        setLine(p.LineSD,false);
        setAngle(p.Angle,false);
        setXYwh(p.w,p.h,p.x,p.y,false);
        setTra(p.Tra,false);
    }
    void setPenColor(Color c, Boolean b) {
        if(b)
            this.addUndo();
        PenColor=c;
        if(b)
            this.setPoints();
    }

    void setBGColor(Color c, Boolean b) {
        if(b)
            this.addUndo();
        BGColor=c;
        if(b)
            this.setPoints();
    }

    void setTextColor(Color c, Boolean b) {
        if(b)
            this.addUndo();
        TextColor=c;
        if(b)
            this.setPoints();
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
