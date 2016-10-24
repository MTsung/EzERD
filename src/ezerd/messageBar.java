/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ezerd;
import java.awt.*;
import java.awt.event.*;

/**
 *
 * @author CMC
 */
public class messageBar extends Panel{
    ezERD parent;
    String message;
    Label pageInfo;
    Point XY=new Point(1000,0);
    
    public void updatePageInfo()
    {
        //pageInfo.setText(""+ parent.curPage + "/" + parent.totalPages);
        pageInfo.setText("(" + XY.x + "," + XY.y + ")");// +parent.totalPages);
    }
    
    public void updateMessage()
    {
        updatePageInfo();
        parent.Win.setTitle("EzERD-" + parent.Ptb.Btns.elementAt(parent.Ptb.activeButton()).getText());
    }
    
    public void showMessage()
    {
        pageInfo = new Label(message);
        this.add(pageInfo);
    }
    
    messageBar(ezERD p){
        super();
        parent=p;
        message="(" + XY.x + "," + XY.y + ")";
        this.setLayout(new FlowLayout(FlowLayout.LEFT));
        this.setBackground(Color.LIGHT_GRAY);
        showMessage();
    }
            
    
}
