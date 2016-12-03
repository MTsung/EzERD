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

/**
 *
 * @author CMC
 */
public class moveNode extends Panel {

    page parent;
    Point Sp, Ep;
    Panel[] Node = new Panel[8];
    int NodeW = 10, w, h, MinW = 7,x,y;
    Cursor CursorTemp;
    
    moveNode(page p) {
        super();
        parent = p;
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
                parent.activeObj.setLocation(Ep.x < w ? x + Ep.x : x + w + NodeW,
                                            Ep.y < h ? y + Ep.y : y + h + NodeW);
                parent.activeObj.setSize(Math.abs(Ep.x - w), Math.abs(Ep.y - h));
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
                parent.activeObj.setLocation(parent.activeObj.getX(),
                                            Ep.y < h ? y + Ep.y : y + h + NodeW);
                parent.activeObj.setSize(w,Math.abs(Ep.y - h));
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
                parent.activeObj.setLocation(Ep.x < w ? x : x - Ep.x +w,
                                            Ep.y < h ? y + Ep.y : y + h + NodeW);
                parent.activeObj.setSize(Math.abs(Ep.x - w),Math.abs(Ep.y - h));
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
                parent.activeObj.setLocation(Ep.x < w ? x + Ep.x : x + w + NodeW,
                                            parent.activeObj.getY());
                parent.activeObj.setSize(Math.abs(Ep.x - w),h);
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
                parent.activeObj.setLocation(Ep.x < w ? x : x - Ep.x +w,
                                            parent.activeObj.getY());
                parent.activeObj.setSize(Math.abs(Ep.x - w),h);
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
                parent.activeObj.setLocation(Ep.x < w ? x + Ep.x : x + w + NodeW,
                                            Ep.y < h ? y : y - Ep.y + h - NodeW);
                parent.activeObj.setSize(Math.abs(Ep.x - w),Math.abs(Ep.y - h));
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
                parent.activeObj.setLocation(parent.activeObj.getX(),
                                            Ep.y < h ? y  : y - Ep.y + h - NodeW);
                parent.activeObj.setSize(w,Math.abs(Ep.y - h));
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
                parent.activeObj.setLocation(Ep.x < w ? x : x-Ep.x + w - NodeW,
                                            Ep.y < h ? y : y-Ep.y + h - NodeW);
                parent.activeObj.setSize(Math.abs(Ep.x - w),Math.abs(Ep.y - h));
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

        for (Component a : this.getComponents())/**/ {
            a.addKeyListener(new keyListener(parent.parent));/**/
        }
        this.addKeyListener(new keyListener(parent.parent));/**/
    }

    void ShowNode() {
        for (Panel p : Node) {
            p.setVisible(true);
        }
        int X = parent.activeObj.getX(), Y = parent.activeObj.getY();
        int W = parent.activeObj.getWidth(), H = parent.activeObj.getHeight();
        Node[0].setLocation(X - 3 - NodeW, Y - 3 - NodeW);
        Node[1].setLocation(X + W / 2 - NodeW / 2, Y - 3 - NodeW);
        Node[2].setLocation(X + W + 3, Y - 3 - NodeW);
        Node[3].setLocation(X - 3 - NodeW, Y + H / 2 - NodeW / 2);
        Node[4].setLocation(X + W + 3, Y + H / 2 - NodeW / 2);
        Node[5].setLocation(X - 3 - NodeW, Y + H + 3);
        Node[6].setLocation(X + W / 2 - NodeW / 2, Y + H + 3);
        Node[7].setLocation(X + W + 3, Y + H + 3);
    }

    void HideNode() {
        for (Panel p : Node) {
            p.setVisible(false);
        }
    }
}
