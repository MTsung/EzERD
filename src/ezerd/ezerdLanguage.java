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
public class ezerdLanguage {
    ezerdLanguage(ezERD p,String s){
        if("Taiwan".equals(s)){
            p.MainWin.Mess="訊息";
            p.MainWin.ClosingMessage="尚有文件未儲存，是否關閉？";
            p.AttributesToolBar.AttributesBox.ColorChooserLabel.setText("顏色選擇:");
            p.AttributesToolBar.AttributesBox.ObjectLabel.setText("物件屬性:");
            p.AttributesToolBar.AttributesBox.PageSizeLabel.setText("頁面大小:");
            p.AttributesToolBar.AttributesBox.PenSizeLabel.setText("筆寬:");
            p.AttributesToolBar.AttributesBox.PageSizePanel.LabelH.setText("高度:");
            p.AttributesToolBar.AttributesBox.PageSizePanel.LabelW.setText("寬度:");
            p.AttributesToolBar.AttributesBox.ObjAttributesPanel.LabelH.setText("高度:");
            p.AttributesToolBar.AttributesBox.ObjAttributesPanel.LabelW.setText("寬度:");
            p.MainWin.MenuBar.AllPageMenu.setLabel("全部頁面");
            p.MainWin.MenuBar.CachingPageMenu.setLabel("已關閉頁面");
            p.MainWin.MenuBar.EditMenu.setLabel("編輯");
            p.MainWin.MenuBar.ExportFileMenu.setLabel("匯出");
            p.MainWin.MenuBar.FileMenu.setLabel("檔案");
            p.MainWin.MenuBar.HelpMenu.setLabel("說明");
            p.MainWin.MenuBar.LanguageMenu.setLabel("語言");
            p.MainWin.MenuBar.PageMenu.setLabel("頁面");
            p.MainWin.MenuBar.cloM.setLabel("關閉頁面(Ctrl+W)");
            p.MainWin.MenuBar.newM.setLabel("新增頁面(Ctrl+N)");
            p.MainWin.MenuBar.openM.setLabel("開啟檔案(Ctrl+O)");
            p.MainWin.MenuBar.undoM.setLabel("復原(Ctrl+Z)");
            p.MainWin.MenuBar.saveM.setLabel("儲存檔案(Ctrl+S)");
            p.MainWin.MenuBar.redoM.setLabel("重做(Ctrl+Y)");
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
            p.AttributesToolBar.AttributesBox.ColorChooserLabel.setText("Color Chooser :");
            p.AttributesToolBar.AttributesBox.ObjectLabel.setText("Object Attributes :");
            p.AttributesToolBar.AttributesBox.PageSizeLabel.setText("Page Size :");
            p.AttributesToolBar.AttributesBox.PenSizeLabel.setText("Pen Size :");
            p.AttributesToolBar.AttributesBox.PageSizePanel.LabelH.setText("Height :");
            p.AttributesToolBar.AttributesBox.PageSizePanel.LabelW.setText("Width :");
            p.AttributesToolBar.AttributesBox.ObjAttributesPanel.LabelH.setText("Height :");
            p.AttributesToolBar.AttributesBox.ObjAttributesPanel.LabelW.setText("Width :");
            p.MainWin.MenuBar.AllPageMenu.setLabel("All Page");
            p.MainWin.MenuBar.CachingPageMenu.setLabel("Caching Page");
            p.MainWin.MenuBar.EditMenu.setLabel("Eidt");
            p.MainWin.MenuBar.ExportFileMenu.setLabel("Export File");
            p.MainWin.MenuBar.FileMenu.setLabel("File");
            p.MainWin.MenuBar.HelpMenu.setLabel("Help");
            p.MainWin.MenuBar.LanguageMenu.setLabel("Language");
            p.MainWin.MenuBar.PageMenu.setLabel("Page");
            p.MainWin.MenuBar.cloM.setLabel("Close Page(Ctrl+W)");
            p.MainWin.MenuBar.newM.setLabel("New Page(Ctrl+N)");
            p.MainWin.MenuBar.openM.setLabel("Open File(Ctrl+O)");
            p.MainWin.MenuBar.redoM.setLabel("Redo(Ctrl+Y)");
            p.MainWin.MenuBar.saveM.setLabel("Save File(Ctrl+S)");
            p.MainWin.MenuBar.undoM.setLabel("Undo(Ctrl+Z)");
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
