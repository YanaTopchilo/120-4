/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simpleeditor;


import java.awt.*;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Properties;
import javax.swing.*;



/**
 *
 * @author Asus
 */
public class SimpleEditor extends JFrame {
//    private Container cp = getContentPane();
    private JLabel fileName;
    private JTextArea text;
    private JMenuBar bar;
    private JMenu menu;
    private JMenuItem[] commandMenu;
    private JButton[] commandButton;
    private SimpleEditorListener listener;

    protected SimpleEditor() {
        super("SimpleEditor");
        setTitle("Simple text editor");
        setSize(800, 500);
        setLocation(400, 300);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setJMenuBar(initJMenuBar());
        init();
        createMenu();   
      
        setVisible(true);
    }
    private JTextArea textArea(){
        text = new JTextArea();
        return text;
    }

    private JButton getText (){
        JButton getText = new JButton("get Text");
        getText.addActionListener((e) -> {
            System.out.println(text.getText());
        });
        return getText;
    }
    
    private JMenuBar initJMenuBar (){
 
         bar = new JMenuBar();
         bar.setLayout(new FlowLayout(FlowLayout.LEFT));
         bar.setSize(200, 50);
         bar.add(createMenu());
         Button save = new Button ("Save"); 
         Dimension but = save.getSize();
         save.setSize(10, 10);
         bar.add(save, BorderLayout.WEST);
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

    private JMenu createMenu() {
        menu = new JMenu("Fale");
        Button file = new Button("New");
       // file.addActionListener(new DemoActionListener(cp));
        menu.add(file);
        Button open = new Button("Open");
        menu.add(open);
        Button saveAs = new Button("Save as");
        menu.add(saveAs);
        JMenu edit  = new JMenu("Edit");
        menu.add(edit);
        JMenu settings = new JMenu("Settings");
        menu.add(settings);
        return menu;
    }
    
    
    void appendText(String str, boolean append) {

    }

}
