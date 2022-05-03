/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simpleedit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.io.*;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
/**
 /**
 *
 * @author Topchilo
 */
class SimpleEditorListener extends WindowAdapter
        implements ActionListener, AutoCloseable {

    private SimpleEditor editor;
    private File file;
    private FileReader reader;
    private FileWriter writer;
    public static String textInFile = "";
     public static String textBuff;
    public static String fileName ;
    FileInputStream is;
    
    public SimpleEditorListener(SimpleEditor editor) {
        this.editor = editor;
    }
   

   @Override
    public void actionPerformed(ActionEvent ae) {

        switch (ae.getActionCommand()) {
        case "Open":
           open();
          break;

        case "Save":
           save ();
           editor.setTitle(editor.title);
          break;

        case "Save as":
           saveAS();
          break;       
        
        case "New":
            editor.text.setText("");
            editor.setTitle("Simple text editor");
           break;
        
        case "Exit":
            System.exit(0);
           break;  
          
        case "Copy":
            textBuff = editor.text.getSelectedText();
           break;   
          
        case "Paste":
            editor.text.insert(textBuff, editor.text.getCaretPosition());
           break;    
          
        case "Cut":
            textBuff = editor.text.getSelectedText();
            editor.text.replaceRange("", editor.text.getSelectionStart(), editor.text.getSelectionEnd());
           break;  
          
        case "Select All":
            editor.text.selectAll();
          break;   
            }
        }

        @Override
        public void close() {

        }

    public void open() {
        JFileChooser fileopen = new JFileChooser();             
        int ret = fileopen.showDialog(null, "Открыть файл");                
        file = fileopen.getSelectedFile();
        editor.title = file.getName();
        editor.setTitle(editor.title);

        try(FileReader fr = new FileReader(file)) {
            StringBuilder sb = new StringBuilder();
            char[] buf = new char[4096];
            int n;
            while((n = fr.read(buf)) >= 0) {
                sb.append(buf, 0, n);
            }
            editor.text.setText(sb.toString());
            editor.text.setCaretPosition(0);
        } catch (IOException ex) {
           editor.text.setText("Error reading file: " + ex.getMessage() + ".");
        }

          fileName = file.getPath();         
        }
       
     
    private void save() {
       textInFile = editor.text.getText();
       byte buf[] = textInFile.getBytes();
          if (fileName != null) {
           try 
        { 
          FileOutputStream os = new FileOutputStream(
            fileName);
          os.write(buf);
          os.close();
          editor.setTitle(editor.title);
                } catch (IOException ex) {
                    Logger.getLogger(SimpleEditorListener.class.getName()).log(Level.SEVERE, null, ex);
                }
            }  
          else saveAS();
    }
            
    private void saveAS(){      
        FileNameExtensionFilter filter = new FileNameExtensionFilter("*.txt","*.*");
        JFileChooser faleSave = new JFileChooser();
        faleSave.setFileFilter(filter);
        if ( faleSave.showSaveDialog(null) == JFileChooser.APPROVE_OPTION ) { 
           fileName =faleSave.getSelectedFile().getPath() + ".txt";
           editor.title = faleSave.getSelectedFile().getName();
           save();
        }      
        }      
}

