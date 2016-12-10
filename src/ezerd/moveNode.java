/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ezerd;

import java.awt.*;
import static java.awt.BasicStroke.CAP_ROUND;
import static java.awt.BasicStroke.JOIN_ROUND;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

/**
 *
 * @author CMC
 */
public class moveNode{

    page parent;
    Point Sp, Ep;
    Panel[] Node = new Panel[8];
    Panel N;
    int NodeW = 10, w, h,x,y,TempAngle;
    Cursor CursorTemp;
    Boolean P=true;
    
    moveNode(page p) {
        super();
        parent = p;
        N=new Panel();
        N.setSize(15, 15);
        N.setBackground(Color.GREEN);
        N.setVisible(false);
        parent.add(N);
        for (int i = 0; i < 8; i++) {
            Node[i] = new Panel();
            Node[i].setSize(NodeW, NodeW);
            Node[i].setBackground(Color.BLUE);
            Node[i].setVisible(false);
            Node[i].addKeyListener(new keyListener(parent.parent));/**/
            parent.add(Node[i]);
            
        }
        Node[0].setCursor(Cursor.getPredefinedCursor(Cursor.SE_RESIZE_CURSOR));
        Node[0].addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                Ep = new Point(e.getXOnScreen() - Sp.x, e.getYOnScreen() - Sp.y);
                parent.activeObj.setObjLocation(Ep.x < w ? x + Ep.x : x + w + NodeW,
                                            Ep.y < h ? y + Ep.y : y + h + NodeW,false);
                parent.activeObj.setObjSize(Math.abs(Ep.x - w), Math.abs(Ep.y - h),false);
                parent.repaint();
            }
        });
        Node[0].addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                Sp = e.getLocationOnScreen();
                x =parent.activeObj.getX();
                y =parent.activeObj.getY();
                w =parent.activeObj.getWidth();
                h =parent.activeObj.getHeight();
                CursorTemp = parent.getCursor();
                parent.setCursor(Node[0].getCursor());
                parent.activeObj.addUndo();
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                parent.setCursor(CursorTemp);
                parent.activeObj.setPoints();
            }
        });

        Node[1].setCursor(Cursor.getPredefinedCursor(Cursor.N_RESIZE_CURSOR));
        Node[1].addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                Ep = new Point(e.getXOnScreen() - Sp.x, e.getYOnScreen() - Sp.y);
                parent.activeObj.setObjLocation(parent.activeObj.getX(),
                                            Ep.y < h ? y + Ep.y : y + h + NodeW,false);
                parent.activeObj.setObjSize(w,Math.abs(Ep.y - h),false);
                parent.repaint();
            }
        });
        Node[1].addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                Sp = e.getLocationOnScreen();
                x =parent.activeObj.getX();
                y =parent.activeObj.getY();
                w =parent.activeObj.getWidth();
                h =parent.activeObj.getHeight();
                CursorTemp = parent.getCursor();
                parent.setCursor(Node[1].getCursor());
                parent.activeObj.addUndo();
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                parent.setCursor(CursorTemp);
                parent.activeObj.setPoints();
            }
        });

        Node[2].setCursor(Cursor.getPredefinedCursor(Cursor.SW_RESIZE_CURSOR));
        Node[2].addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                Ep = new Point(Sp.x - e.getXOnScreen() , e.getYOnScreen() - Sp.y);
                parent.activeObj.setObjLocation(Ep.x < w ? x : x - Ep.x +w,
                                            Ep.y < h ? y + Ep.y : y + h + NodeW,false);
                parent.activeObj.setObjSize(Math.abs(Ep.x - w),Math.abs(Ep.y - h),false);
                parent.repaint();
            }
        });
        Node[2].addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                Sp = e.getLocationOnScreen();
                x =parent.activeObj.getX();
                y =parent.activeObj.getY();
                w =parent.activeObj.getWidth();
                h =parent.activeObj.getHeight();
                CursorTemp = parent.getCursor();
                parent.setCursor(Node[2].getCursor());
                parent.activeObj.addUndo();
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                parent.setCursor(CursorTemp);
                parent.activeObj.setPoints();
            }
        });

        Node[3].setCursor(Cursor.getPredefinedCursor(Cursor.E_RESIZE_CURSOR));
        Node[3].addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                Ep = new Point(e.getXOnScreen() - Sp.x, e.getYOnScreen() - Sp.y);
                parent.activeObj.setObjLocation(Ep.x < w ? x + Ep.x : x + w + NodeW,
                                            parent.activeObj.getY(),false);
                parent.activeObj.setObjSize(Math.abs(Ep.x - w),h,false);
                parent.repaint();
            }
        });
        Node[3].addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                Sp = e.getLocationOnScreen();
                x =parent.activeObj.getX();
                y =parent.activeObj.getY();
                w =parent.activeObj.getWidth();
                h =parent.activeObj.getHeight();
                CursorTemp = parent.getCursor();
                parent.setCursor(Node[3].getCursor());
                parent.activeObj.addUndo();
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                parent.setCursor(CursorTemp);
                parent.activeObj.setPoints();
            }
        });

        Node[4].setCursor(Cursor.getPredefinedCursor(Cursor.E_RESIZE_CURSOR));
        Node[4].addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                Ep = new Point(Sp.x - e.getXOnScreen() , e.getYOnScreen() - Sp.y);
                parent.activeObj.setObjLocation(Ep.x < w ? x : x - Ep.x +w,
                                            parent.activeObj.getY(),false);
                parent.activeObj.setObjSize(Math.abs(Ep.x - w),h,false);
                parent.repaint();
            }
        });
        Node[4].addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                Sp = e.getLocationOnScreen();
                x =parent.activeObj.getX();
                y =parent.activeObj.getY();
                w =parent.activeObj.getWidth();
                h =parent.activeObj.getHeight();
                CursorTemp = parent.getCursor();
                parent.setCursor(Node[4].getCursor());
                parent.activeObj.addUndo();
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                parent.setCursor(CursorTemp);
                parent.activeObj.setPoints();
            }
        });

        Node[5].setCursor(Cursor.getPredefinedCursor(Cursor.NE_RESIZE_CURSOR));
        Node[5].addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                Ep = new Point(e.getXOnScreen() - Sp.x, Sp.y - e.getYOnScreen());
                parent.activeObj.setObjLocation(Ep.x < w ? x + Ep.x : x + w + NodeW,
                                            Ep.y < h ? y : y - Ep.y + h - NodeW,false);
                parent.activeObj.setObjSize(Math.abs(Ep.x - w),Math.abs(Ep.y - h),false);
                parent.repaint();
            }
        });
        Node[5].addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                Sp = e.getLocationOnScreen();
                x =parent.activeObj.getX();
                y =parent.activeObj.getY();
                w =parent.activeObj.getWidth();
                h =parent.activeObj.getHeight();
                CursorTemp = parent.getCursor();
                parent.setCursor(Node[5].getCursor());
                parent.activeObj.addUndo();
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                parent.setCursor(CursorTemp);
                parent.activeObj.setPoints();
            }
        });

        Node[6].setCursor(Cursor.getPredefinedCursor(Cursor.N_RESIZE_CURSOR));
        Node[6].addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                Ep = new Point(e.getXOnScreen() - Sp.x, Sp.y - e.getYOnScreen());
                parent.activeObj.setObjLocation(parent.activeObj.getX(),
                                            Ep.y < h ? y  : y - Ep.y + h - NodeW,false);
                parent.activeObj.setObjSize(w,Math.abs(Ep.y - h),false);
                parent.repaint();
            }
        });
        Node[6].addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                Sp = e.getLocationOnScreen();
                x =parent.activeObj.getX();
                y =parent.activeObj.getY();
                w =parent.activeObj.getWidth();
                h =parent.activeObj.getHeight();
                CursorTemp = parent.getCursor();
                parent.setCursor(Node[6].getCursor());
                parent.activeObj.addUndo();
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                parent.setCursor(CursorTemp);
                parent.activeObj.setPoints();
            }
        });
        Node[7].setCursor(Cursor.getPredefinedCursor(Cursor.SE_RESIZE_CURSOR));
        Node[7].addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                Ep = new Point(Sp.x-e.getXOnScreen() , Sp.y-e.getYOnScreen());
                parent.activeObj.setObjLocation(Ep.x < w ? x : x-Ep.x + w - NodeW,
                                            Ep.y < h ? y : y-Ep.y + h - NodeW,false);
                parent.activeObj.setObjSize(Math.abs(Ep.x - w),Math.abs(Ep.y - h),false);
                parent.repaint();

            }
        });
        Node[7].addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                Sp = e.getLocationOnScreen();
                x =parent.activeObj.getX();
                y =parent.activeObj.getY();
                w =parent.activeObj.getWidth();
                h =parent.activeObj.getHeight();
                CursorTemp = parent.getCursor();
                parent.setCursor(Node[7].getCursor());
                parent.activeObj.addUndo();
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                parent.setCursor(CursorTemp);
                parent.activeObj.setPoints();
            }
        });

        N.addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                int tt =(int) Math.toDegrees(Math.atan(
                        ((float) (Sp.x) - e.getXOnScreen())
                        / (e.getYOnScreen() - (float) (Sp.y))
                        ));
                if((float)(Sp.y)>e.getYOnScreen()){
                    if((float) (Sp.x) > e.getXOnScreen()){
                        tt+=360;
                    }
                }else{
                    tt+=180;
                }
                parent.activeObj.setAngle(tt+TempAngle,false);
                parent.repaint();
            }
        });
        N.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                Sp = new Point(parent.activeObj.getLocationOnScreen().x + parent.activeObj.getWidth() / 2,
                        parent.activeObj.getLocationOnScreen().y + parent.activeObj.getHeight() / 2);
                TempAngle=parent.activeObj.Angle;
                P=false;
                parent.activeObj.addUndo();
            }
            @Override
            public void mouseReleased(MouseEvent e) {
                ShowNode();
                P=true;
                parent.activeObj.setPoints();
            }
        });
    }
    Boolean isRotateing(){
        return P;
    }
    void ShowNode() {
        for (Panel p : Node) {
            p.setVisible(true);
        }
        N.setVisible(true);
        int X = parent.activeObj.getX(), Y = parent.activeObj.getY();
        int W = parent.activeObj.getWidth(), H = parent.activeObj.getHeight();
        Node[0].setLocation(X - 3 - NodeW, Y - 3 - NodeW);
        Node[1].setLocation(X + W / 2 - NodeW / 2, Y - 3 - NodeW);
        Node[2].setLocation(X + W + 3, Y - 3 - NodeW);
        N.setLocation(X + W / 2 - 15 / 2, Y - 30);
        Node[3].setLocation(X - 3 - NodeW, Y + H / 2 - NodeW / 2);
        Node[4].setLocation(X + W + 3, Y + H / 2 - NodeW / 2);
        Node[5].setLocation(X - 3 - NodeW, Y + H + 3);
        Node[6].setLocation(X + W / 2 - NodeW / 2, Y + H + 3);
        Node[7].setLocation(X + W + 3, Y + H + 3);
        Graphics2D g2 = (Graphics2D) parent.getGraphics();
        g2.setColor(Color.BLUE);
        g2.setStroke(new BasicStroke(2, CAP_ROUND, JOIN_ROUND,
                     0, new float[]{16, 8}, 0));
        g2.drawRect(parent.activeObj.getX() - 3, parent.activeObj.getY() - 3,
                parent.activeObj.getWidth() + 6, parent.activeObj.getHeight() + 6);
    }

    void HideNode() {
        for (Panel p : Node) {
            p.setVisible(false);
        }
        N.setVisible(false);
    }
}
