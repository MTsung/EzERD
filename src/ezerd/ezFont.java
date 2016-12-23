/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ezerd;

import java.awt.Font;

/**
 *
 * @author CMC
 */
public class ezFont extends Font{
    ezFont(){
        super(Font.DIALOG,Font.PLAIN,20);
    }ezFont(int n){
        super(Font.DIALOG,Font.PLAIN,n);
    }
    void setFontSize(int n){
        this.size=n;
    }
}
