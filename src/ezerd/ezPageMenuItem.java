/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ezerd;

import java.awt.*;

/**
 *
 * @author CMC
 */
public class ezPageMenuItem extends MenuItem{
    int BtnN;
    ezPageMenuItem(String s,int n){
        super();
        this.setLabel(s);
        BtnN=n;
    }
}
