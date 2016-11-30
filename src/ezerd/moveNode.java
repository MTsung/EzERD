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
    int NodeW = 10, w, h, MinW = 7;
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
                Ep = new Point(Sp.x - e.getX(), Sp.y - e.getY());
                w += Ep.x - Sp.x;
                h += Ep.y - Sp.y;
                parent.activeObj.setLocation(w > MinW ? parent.activeObj.getX() - Ep.x + Sp.x : Node[7].getX() - 3 - MinW,
                         h > MinW ? parent.activeObj.getY() - Ep.y + Sp.y : Node[7].getY() - 3 - MinW);
                parent.activeObj.setSize(w < MinW ? MinW : w, h < MinW ? MinW : h);
                parent.repaint();
            }
        });
        Node[0].addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                Sp = Node[0].getLocation();
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
                Ep = new Point(Node[1].getX(), Sp.y - e.getY());
                w = parent.activeObj.getWidth();
                h = parent.activeObj.getHeight() + Ep.y - Sp.y;
                parent.activeObj.setLocation(parent.activeObj.getX(),
                         h > MinW ? parent.activeObj.getY() - Ep.y + Sp.y : Node[7].getY() - 3 - MinW);
                parent.activeObj.setSize(w < MinW ? MinW : w, h < MinW ? MinW : h);
                parent.repaint();
            }
        });
        Node[1].addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                Sp = Node[1].getLocation();
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
                Ep = new Point(Sp.x + e.getX(), Sp.y - e.getY());
                w = parent.activeObj.getWidth() + Ep.x - Sp.x;
                h = parent.activeObj.getHeight() + Ep.y - Sp.y;
                parent.activeObj.setLocation(parent.activeObj.getX(),
                         h > MinW ? parent.activeObj.getY() - Ep.y + Sp.y : Node[7].getY() - 3 - MinW);
                parent.activeObj.setSize(w < MinW ? MinW : w, h < MinW ? MinW : h);
                parent.repaint();
            }
        });
        Node[2].addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                Sp = Node[2].getLocation();
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
                Ep = new Point(Sp.x - e.getX(), Node[3].getY());
                w = parent.activeObj.getWidth() + Ep.x - Sp.x;
                h = parent.activeObj.getHeight();
                parent.activeObj.setLocation(w > MinW ? parent.activeObj.getX() - Ep.x + Sp.x : Node[7].getX() - 3 - MinW,
                         parent.activeObj.getY());
                parent.activeObj.setSize(w < MinW ? MinW : w, h < MinW ? MinW : h);
                parent.repaint();
            }
        });
        Node[3].addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                Sp = Node[3].getLocation();
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
                Ep = new Point(Sp.x + e.getX(), Node[4].getY());
                w = parent.activeObj.getWidth() + Ep.x - Sp.x;
                h = parent.activeObj.getHeight();
                parent.activeObj.setSize(w < MinW ? MinW : w, h < MinW ? MinW : h);
                parent.repaint();
            }
        });
        Node[4].addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                Sp = Node[4].getLocation();
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
                Ep = new Point(Sp.x - e.getX(), Sp.y + e.getY());
                w = parent.activeObj.getWidth() + Ep.x - Sp.x;
                h = parent.activeObj.getHeight() + Ep.y - Sp.y;
                parent.activeObj.setLocation(w > MinW ? parent.activeObj.getX() - Ep.x + Sp.x : Node[7].getX() - 3 - MinW,
                         parent.activeObj.getY());
                parent.activeObj.setSize(w < MinW ? MinW : w, h < MinW ? MinW : h);
                parent.repaint();
            }
        });
        Node[5].addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                Sp = Node[5].getLocation();
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
                Ep = new Point(Node[6].getX(), Sp.y + e.getY());
                w = parent.activeObj.getWidth();
                h = parent.activeObj.getHeight() + Ep.y - Sp.y;
                parent.activeObj.setSize(w < MinW ? MinW : w, h < MinW ? MinW : h);
                parent.repaint();
            }
        });
        Node[6].addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                Sp = Node[6].getLocation();
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
                Ep = new Point(Sp.x + e.getX(), Sp.y + e.getY());
                w = parent.activeObj.getWidth() + Ep.x - Sp.x;
                h = parent.activeObj.getHeight() + Ep.y - Sp.y;
                parent.activeObj.setSize(w < MinW ? MinW : w, h < MinW ? MinW : h);
                parent.repaint();

            }
        });
        Node[7].addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                Sp = Node[7].getLocation();
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
