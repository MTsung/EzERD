/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ezerd;
import java.awt.*;
import static java.awt.BasicStroke.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Stack;
import java.util.Vector;
//import javafx.scene.image.Image;
import javax.imageio.ImageIO;
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
    float PanSize=8;
    Color PanColor=new Color(0,0,0);
    rightClickMenu popupMenu1=new rightClickMenu();
    Image bufferImage;
    Graphics bufferGraphics;
    
    page(ezERD p){
        super();      
        parent=p;      
        this.setBackground(Color.WHITE);
        Points=new Vector<points>();
        RePoints=new Vector<points>();
        undos=new Stack<Integer>();
        redos=new Stack<Integer>();
        
        this.addMouseMotionListener(new MouseAdapter(){
            @Override
            public void mouseDragged(MouseEvent e){
                //System.out.println("mouseReleased");
                PanColor=new Color(parent.Atb.ColorChooseBox.CB.getColor());
                if(LineT && (e.getModifiers() == InputEvent.BUTTON1_MASK)){
                    Graphics2D g = (Graphics2D)page.this.getGraphics();    
                    g.setStroke(new BasicStroke(PanSize,CAP_ROUND,JOIN_ROUND));
                    g.setColor(PanColor);
                    Ep=e.getPoint();
                    g.drawLine(Sp.x, Sp.y, Ep.x, Ep.y);
                    Points.add(new points(Sp,Ep,PanSize,PanColor));
                    Sp=Ep;
                    undo++; 
                    if(!parent.Ptb.Btns.elementAt(parent.Ptb.activeButton()).getText().endsWith("*"))
                        parent.Ptb.Btns.elementAt(parent.Ptb.activeButton()).setText(
                                                 parent.Ptb.Btns.elementAt(parent.Ptb.activeButton()).getText()+"*");
                }
                parent.Mb.XY=e.getPoint();
                parent.Mb.updateMessage();
            }
            
            @Override
            public void mouseMoved(MouseEvent e) {
                    parent.Mb.XY=e.getPoint();
                    parent.Mb.updateMessage();
            }
        });
        
        this.addMouseListener(new MouseAdapter(){
            @Override
            public void mousePressed(MouseEvent e){
                //System.out.println("mousePressed");
                if(LineT && (e.getModifiers() == InputEvent.BUTTON1_MASK)){
                    Sp=e.getPoint();
                    undo=0;
                }
            }
            
            @Override
            public void mouseReleased(MouseEvent e){
                //System.out.println("mouseReleased");
                if(LineT && (e.getModifiers() == InputEvent.BUTTON1_MASK)){
                    if(undo!=0)
                        undos.add(undo);
                    RePoints.removeAllElements();
                    redos.removeAllElements();
                    parent.Ttb.undoBtn.setEnabled(undos.size()==0 ? false:true);
                    parent.Ttb.redoBtn.setEnabled(redos.size()==0 ? false:true);
                }
                else if (e.getModifiers() == InputEvent.BUTTON3_MASK) {
                    //popupMenu1.show(page.this, e.getX(), e.getY());
                }
            }
        });
        
        //this.add(popupMenu1);
        for(Component a:this.getComponents())/**/
            a.addKeyListener(new keyListener(parent));/**/
        this.addKeyListener(new keyListener(parent));/**/

    }
    
    
    public void update(Graphics g) {
        paint(g);
    }
    @Override
    public void paint(Graphics g) {
        bufferImage = createImage(parent.Win.ScreenSize.width, parent.Win.ScreenSize.height);
        bufferGraphics = bufferImage.getGraphics();
        parent.Win.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        Graphics2D g2 = (Graphics2D)bufferGraphics;  
        for(points p:Points){
            g2.setColor(p.PanColor);
            g2.setStroke(new BasicStroke(p.PanSize,CAP_ROUND,JOIN_ROUND));
            g2.drawLine(p.Sp.x, p.Sp.y, p.Ep.x, p.Ep.y);
        }
        g.drawImage(bufferImage, 0, 0, this);
        parent.Win.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
        parent.Ttb.undoBtn.setEnabled(undos.size()==0 ? false:true);
        parent.Ttb.redoBtn.setEnabled(redos.size()==0 ? false:true);  
        parent.Win.setTitle("EzERD-" + parent.Ptb.Btns.elementAt(parent.Ptb.activeButton()).getText());
        
    }     
}
