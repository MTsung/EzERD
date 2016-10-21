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
    //static Color c[]={Color.YELLOW, Color.CYAN};
    static int count=0;
    ezERD parent;
    Point Sp,Ep;
    Vector<points> Points;
    Stack<Integer> undos;
    int undo=0;
    
    page(ezERD p){
        super();      
        parent=p;      
        this.setBackground(Color.WHITE);
        Points=new Vector<points>();
        undos=new Stack<Integer>();
        
        this.addMouseMotionListener(new MouseAdapter(){
            public void mouseDragged(MouseEvent e){
                //System.out.println("mouseReleased");
                Graphics2D g = (Graphics2D)page.this.getGraphics();    
                g.setStroke(new BasicStroke(10.0f,CAP_ROUND,JOIN_ROUND));
                g.setColor(Color.BLUE);
                Ep=e.getPoint();
                g.drawLine(Sp.x, Sp.y, Ep.x, Ep.y);
                Points.add(new points(Sp,Ep));
                Sp=Ep;
                parent.Win.setTitle("EzERD ("+e.getX()+","+e.getY()+")");
                undo++;
            }
            public void mouseMoved(MouseEvent e) {
            }
        });
        
        this.addMouseListener(new MouseAdapter(){
            public void mousePressed(MouseEvent e){
                Sp=e.getPoint();
                undo=0;
                //System.out.println("mousePressed");
            }
            
            public void mouseReleased(MouseEvent e){
                //System.out.println("mouseReleased");
                undos.add(undo);
                System.out.println(undo);
                parent.Win.setTitle("EzERD");
            }
        });
        
    }
    public void paint(Graphics g) {
        Graphics2D g2 = (Graphics2D)g;  
        g2.setStroke(new BasicStroke(10.0f,CAP_ROUND,JOIN_ROUND));
        g.setColor(Color.BLUE);
        for(points p:Points)
            g.drawLine(p.Sp.x, p.Sp.y, p.Ep.x, p.Ep.y);
    }
}
