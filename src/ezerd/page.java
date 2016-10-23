/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ezerd;
import java.awt.*;
import static java.awt.BasicStroke.*;
import java.awt.event.*;
import java.util.Stack;
import java.util.Vector;
import javax.swing.*;
        
/**
 *
 * @author CMC
 */
public class page extends Panel{
    static int count=0;
    Boolean LineT=false;
    ezERD parent;
    Point Sp,Ep;
    Vector<points> Points,RePoints;
    Stack<Integer> undos,redos;
    int undo=0;
    float PanSize=1;
    
    page(ezERD p){
        super();      
        parent=p;      
        this.setBackground(Color.WHITE);
        Points=new Vector<points>();
        RePoints=new Vector<points>();
        undos=new Stack<Integer>();
        redos=new Stack<Integer>();
        
        this.addMouseMotionListener(new MouseAdapter(){
            public void mouseDragged(MouseEvent e){
                //System.out.println("mouseReleased");
                if(LineT){
                    Graphics2D g = (Graphics2D)page.this.getGraphics();    
                    g.setStroke(new BasicStroke(PanSize,CAP_ROUND,JOIN_ROUND));
                    g.setColor(Color.BLUE);
                    Ep=e.getPoint();
                    g.drawLine(Sp.x, Sp.y, Ep.x, Ep.y);
                    Points.add(new points(Sp,Ep));
                    Sp=Ep;
                    parent.Win.setTitle("EzERD ("+e.getX()+","+e.getY()+")");
                    undo++; 
                    if(!parent.Ptb.Btns.elementAt(parent.Ptb.activeButton()).getText().endsWith("*"))
                        parent.Ptb.Btns.elementAt(parent.Ptb.activeButton()).setText(
                        parent.Ptb.Btns.elementAt(parent.Ptb.activeButton()).getText()+"*");
                }
            }
            
            public void mouseMoved(MouseEvent e) {
                
            }
        });
        
        this.addMouseListener(new MouseAdapter(){
            public void mousePressed(MouseEvent e){
                //System.out.println("mousePressed");
                if(LineT){
                    Sp=e.getPoint();
                    undo=0;
                }
            }
            
            public void mouseReleased(MouseEvent e){
                //System.out.println("mouseReleased");
                if(LineT){
                    if(undo!=0)
                        undos.add(undo);
                    RePoints.removeAllElements();
                    redos.removeAllElements();
                    //parent.Ttb.undoBtn.setEnabled(undos.size()==0 ? false:true);
                    //parent.Ttb.redoBtn.setEnabled(redos.size()==0 ? false:true);
                    //System.out.println(undo);
                    parent.Win.setTitle("EzERD");
                }
            }
        });
        
        
        for(Component a:this.getComponents())/**/
            a.addKeyListener(new keyListener(parent));/**/
        this.addKeyListener(new keyListener(parent));/**/
    }
    
    public void paint(Graphics g) {
        parent.Win.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        Graphics2D g2 = (Graphics2D)g;  
        g2.setStroke(new BasicStroke(PanSize,CAP_ROUND,JOIN_ROUND));
        g2.setColor(Color.BLUE);
        for(points p:Points)
            g2.drawLine(p.Sp.x, p.Sp.y, p.Ep.x, p.Ep.y);
        parent.Win.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
        //parent.Ttb.undoBtn.setEnabled(undos.size()==0 ? false:true);
        //parent.Ttb.redoBtn.setEnabled(redos.size()==0 ? false:true);  
        
    }
}
