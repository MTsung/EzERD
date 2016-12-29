/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ezerd;


/**
 *
 * @author CMC
 */
public class ezLanguage {
    ezLanguage(ezERD p,String s){
        if("Taiwan".equals(s)){
            p.MainWin.Mess="訊息";
            p.MainWin.ClosingMessage="尚有文件未儲存，是否關閉？";
            p.AttributesToolBar.AttributesBox.ObjectLabel.setText("物件屬性:");
            p.AttributesToolBar.AttributesBox.PageSizeLabel.setText("頁面大小:");
            p.AttributesToolBar.AttributesBox.PageSizePanel.LabelH.setText("高度:");
            p.AttributesToolBar.AttributesBox.PageSizePanel.LabelW.setText("寬度:");
            p.AttributesToolBar.AttributesBox.ObjAttributesPanel.LabelH.setText("高度    :");
            p.AttributesToolBar.AttributesBox.ObjAttributesPanel.LabelW.setText("寬度   :");
            p.AttributesToolBar.AttributesBox.ObjAttributesPanel.LabelLine.setText("框線樣式         :");
            p.AttributesToolBar.AttributesBox.ObjAttributesPanel.LabelBGColor.setText("背景顏色        :");
            p.AttributesToolBar.AttributesBox.ObjAttributesPanel.LabelTextColor.setText("文字顏色        :");
            p.AttributesToolBar.AttributesBox.ObjAttributesPanel.LabelLineColor.setText("框線顏色        :");
            p.AttributesToolBar.AttributesBox.ObjAttributesPanel.Labelsize.setText("框線大小         :");
            p.AttributesToolBar.AttributesBox.ObjAttributesPanel.LabelAngle.setText("角度               :");
            p.AttributesToolBar.AttributesBox.ObjAttributesPanel.LabelTra.setText("不透明度         :");
            p.AttributesToolBar.AttributesBox.ObjAttributesPanel.choice.removeAll();
            p.AttributesToolBar.AttributesBox.ObjAttributesPanel.choice.add("實線  ");
            p.AttributesToolBar.AttributesBox.ObjAttributesPanel.choice.add("虛線  ");
            
            p.MainWin.MenuBar.AllPageMenu.setLabel("全部頁面");
            p.MainWin.MenuBar.CachingPageMenu.setLabel("已關閉頁面");
            p.MainWin.MenuBar.EditMenu.setLabel("編輯");
            p.MainWin.MenuBar.ExportFileMenu.setLabel("匯出");
            p.MainWin.MenuBar.FileMenu.setLabel("檔案");
            p.MainWin.MenuBar.LanguageMenu.setLabel("語言");
            p.MainWin.MenuBar.PageMenu.setLabel("頁面");
            p.MainWin.MenuBar.CloseM.setLabel("關閉頁面(Ctrl+W)");
            p.MainWin.MenuBar.NewM.setLabel("新增頁面(Ctrl+N)");
            p.MainWin.MenuBar.OpenM.setLabel("開啟檔案(Ctrl+O)");
            p.MainWin.MenuBar.UndoM.setLabel("復原(Ctrl+Z)");
            p.MainWin.MenuBar.SaveM.setLabel("儲存檔案(Ctrl+S)");
            p.MainWin.MenuBar.RedoM.setLabel("重做(Ctrl+Y)");
            p.TopToolBar.ClosePageBtn.setToolTipText("關閉頁面(Ctrl+W)");
            p.TopToolBar.NewPageBtn.setToolTipText("新增頁面(Ctrl+N)");
            p.TopToolBar.OpenBtn.setToolTipText("開啟檔案(Ctrl+O)");
            p.TopToolBar.UndoBtn.setToolTipText("復原(Ctrl+Z)");
            p.TopToolBar.SaveBtn.setToolTipText("儲存檔案(Ctrl+S)");
            p.TopToolBar.RedoBtn.setToolTipText("重做(Ctrl+Y)");
            p.TopToolBar.ClosingMessage="文件";
            p.TopToolBar.ClosingMessage1="未儲存，是否關閉？";
            p.TopToolBar.Mess="訊息";
        }else if("Japan".equals(s)){
            
        }else{
            p.MainWin.Mess="Mssage";
            p.MainWin.ClosingMessage="File is modified. Close？";
            p.AttributesToolBar.AttributesBox.ObjectLabel.setText("Object Attributes :");
            p.AttributesToolBar.AttributesBox.PageSizeLabel.setText("Page Size :");
            p.AttributesToolBar.AttributesBox.PageSizePanel.LabelH.setText("Height :");
            p.AttributesToolBar.AttributesBox.PageSizePanel.LabelW.setText("Width :");
            p.AttributesToolBar.AttributesBox.ObjAttributesPanel.LabelH.setText("Height :");
            p.AttributesToolBar.AttributesBox.ObjAttributesPanel.LabelW.setText("Width :");
            p.AttributesToolBar.AttributesBox.ObjAttributesPanel.LabelLine.setText("Line Style       :");
            p.AttributesToolBar.AttributesBox.ObjAttributesPanel.LabelBGColor.setText("BG Color       :");
            p.AttributesToolBar.AttributesBox.ObjAttributesPanel.LabelTextColor.setText("Text Color      :");
            p.AttributesToolBar.AttributesBox.ObjAttributesPanel.LabelLineColor.setText("Line Color      :");
            p.AttributesToolBar.AttributesBox.ObjAttributesPanel.Labelsize.setText("Line Size        :");
            p.AttributesToolBar.AttributesBox.ObjAttributesPanel.LabelAngle.setText("Angle            :");
            p.AttributesToolBar.AttributesBox.ObjAttributesPanel.LabelTra.setText("Opacity         :");
            p.AttributesToolBar.AttributesBox.ObjAttributesPanel.choice.removeAll();
            p.AttributesToolBar.AttributesBox.ObjAttributesPanel.choice.add("Solid     ");
            p.AttributesToolBar.AttributesBox.ObjAttributesPanel.choice.add("Dotted");
            
            p.MainWin.MenuBar.AllPageMenu.setLabel("All Page");
            p.MainWin.MenuBar.CachingPageMenu.setLabel("Caching Page");
            p.MainWin.MenuBar.EditMenu.setLabel("Eidt");
            p.MainWin.MenuBar.ExportFileMenu.setLabel("Export File");
            p.MainWin.MenuBar.FileMenu.setLabel("File");
            p.MainWin.MenuBar.LanguageMenu.setLabel("Language");
            p.MainWin.MenuBar.PageMenu.setLabel("Page");
            p.MainWin.MenuBar.CloseM.setLabel("Close Page(Ctrl+W)");
            p.MainWin.MenuBar.NewM.setLabel("New Page(Ctrl+N)");
            p.MainWin.MenuBar.OpenM.setLabel("Open File(Ctrl+O)");
            p.MainWin.MenuBar.RedoM.setLabel("Redo(Ctrl+Y)");
            p.MainWin.MenuBar.SaveM.setLabel("Save File(Ctrl+S)");
            p.MainWin.MenuBar.UndoM.setLabel("Undo(Ctrl+Z)");
            p.TopToolBar.ClosePageBtn.setToolTipText("Close Page(Ctrl+W)");
            p.TopToolBar.NewPageBtn.setToolTipText("New Page(Ctrl+N)");
            p.TopToolBar.OpenBtn.setToolTipText("Open File(Ctrl+O)");
            p.TopToolBar.RedoBtn.setToolTipText("Redo(Ctrl+Y)");
            p.TopToolBar.SaveBtn.setToolTipText("Save File(Ctrl+S)");
            p.TopToolBar.UndoBtn.setToolTipText("Undo(Ctrl+Z)");
            p.TopToolBar.ClosingMessage="File";
            p.TopToolBar.ClosingMessage1=" is modified. Close？";
            p.TopToolBar.Mess="Mssage";
        }
    }
}
