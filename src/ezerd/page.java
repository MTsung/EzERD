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
public class page extends Panel{
    static Color c[]={Color.YELLOW, Color.BLUE, Color.GREEN, Color.PINK};
    static int count=0;
    page(){
        super();        
        //this.setBackground(Color.YELLOW);
        this.setBackground(c[count++%4]);
    }

}
