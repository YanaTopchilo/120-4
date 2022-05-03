/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simpleedit;

import java.awt.*;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

/**
 *
 * @author Topchilo
 */
public class SimpleEditor extends JFrame {
    public JLabel fileName;
    public JTextArea text;
    private JMenuBar bar;
    private JMenu menuFile, menuEdit;
    public static JMenuItem fileNew,open,save,saveAs, exit, cut,copy,paste,select;
    private JButton[] commandButton;
    private SimpleEditorListener listener;
    String title = "Simple text editor";
    
    DocumentListener docListener = new DocumentListener(){
        @Override
        public void insertUpdate(DocumentEvent de) {
             setTitle(title + "*");
        }

        @Override
        public void removeUpdate(DocumentEvent de) {
              setTitle(title + "*");
        }

        @Override
        public void changedUpdate(DocumentEvent de) {
               setTitle(title + "*");
        }
    }; 
    
    protected SimpleEditor() {
        super("SimpleEditor");
        
        setTitle(title);
        setSize(800, 500);
        setLocation(400, 300);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setJMenuBar(initJMenuBar());
        init();
        createMenuFile();   
        setVisible(true);
    }
    private JTextArea textArea(){
        text = new JTextArea();  
        text.getDocument().addDocumentListener(docListener);
        return text;  
    }
  
    private JMenuBar initJMenuBar (){
 
         bar = new JMenuBar();
         bar.setLayout(new FlowLayout(FlowLayout.LEFT));
         bar.setSize(200, 50);
         bar.add(createMenuFile());
         bar.add(createMenuEdit());
         return bar;
   }
    public static void main(String[] args) {
        SimpleEditor simpleEditor = new SimpleEditor();
    }

    private void init() {
        Container cp = getContentPane();
        cp.setLayout(new BorderLayout());
        JPanel bottomPanel = new JPanel(new FlowLayout());
        cp.add(bottomPanel, BorderLayout.NORTH);
        cp.add(new JScrollPane(textArea()), BorderLayout.CENTER);
   
    }

    public JMenu createMenuFile() {
        menuFile = new JMenu(" File ");
        fileNew = new JMenuItem("New");
        fileNew.addActionListener(new SimpleEditorListener(this)); 
        menuFile.add(fileNew);
        open = new JMenuItem("Open");
        open.addActionListener(new SimpleEditorListener(this)); 
        menuFile.add(open);
        save = new JMenuItem("Save");
        save.addActionListener(new SimpleEditorListener(this)); 
        menuFile.add(save);
        saveAs = new JMenuItem("Save as");
        saveAs.addActionListener(new SimpleEditorListener(this)); 
        menuFile.add(saveAs);
        exit = new JMenuItem("Exit");
        exit.addActionListener(new SimpleEditorListener(this)); 
        menuFile.add(exit); 
        return menuFile;
    }
   
    public JMenu createMenuEdit() {
        menuEdit  = new JMenu("Edit");
        cut = new JMenuItem("Cut");
        cut.addActionListener(new SimpleEditorListener(this));
        menuEdit.add(cut);
        copy = new JMenuItem("Copy");
        copy.addActionListener(new SimpleEditorListener(this));
        menuEdit.add(copy);
        paste = new JMenuItem("Paste");
        paste.addActionListener(new SimpleEditorListener(this));
        menuEdit.add(paste);
        select = new JMenuItem("Select All");
        select.addActionListener(new SimpleEditorListener(this));
        menuEdit.add(select);
        return menuEdit;
    }    
}
