package project;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.event.UndoableEditEvent;
import javax.swing.event.UndoableEditListener;
import javax.swing.undo.UndoManager;
import java.util.ArrayList;
import java.util.List;



import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
//import java.util.function.Function;

public class GUI implements ActionListener {

    // TEXT AREA
    JFrame window;
    JTextArea textArea;
    boolean wordWrapOn = false;

    //TOP MENU
    JScrollPane scrollPane;
    JMenuBar menuBar;
    JMenu menuFile, menuEdit, menuFormat, menuColor;

    // FILE MENU
    JMenuItem iNew, iOpen, iSave, iSaveAs, iExit;

    // EDIT MENU
    JMenuItem iUndo, iRedo;

    // FORMAT MENU
    JMenuItem iWrap, iFontArial, iFontCSMS, iFontTNR, iFontSize8, iFontSize12, iFontSize16, iFontSize20, iFontSize24, iFontSize28;
    JMenu menuFont, menuFontSize;

    // COLOR MENU
    JMenuItem iColor1, iColor2, iColor3;

    Function_File file = new Function_File(this);
    Function_Edit edit = new Function_Edit(this);
    Function_Format format = new Function_Format(this);
    Function_Color color = new Function_Color(this);
    

    

    KeyHandler kHandler = new KeyHandler(this);

    UndoManager um = new UndoManager();




    List<Observer_Editing> obs = new ArrayList<>();

    public void obsRegister(Observer_Editing o) {
        obs.add(o);
    }

    public void obsRemove(Observer_Editing o) {
        obs.remove(o);
    }

    public void obsNotify(String eventType, String eventDetails) {
        for (Observer_Editing o : obs) {
            o.event(eventType, eventDetails);
        }
    }

    public static void main(String[] args) {
        
        GUI gui = new GUI();

        Logger logger = new Logger();

        gui.obsRegister(logger);

        

    }

    public GUI() {

        createWindow();
        createTextArea();
        createMenuBar();
        createFileMenu();
        createEditMenu();
        createFormatMenu();
        createColorMenu();

        format.selectedFont = "Arial";
        format.createFont(16);
        format.wordWrap();
        color.changeColor("White");

        
        window.setVisible(true);
    }

    public void createWindow() {

        window = new JFrame("Text Editor");
        window.setSize(800, 600);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void createTextArea() {
        
        textArea = new JTextArea();

        textArea.addKeyListener(kHandler);

        textArea.getDocument().addUndoableEditListener(new UndoableEditListener() {
            @Override
            public void undoableEditHappened(UndoableEditEvent e) {
                    
                um.addEdit(e.getEdit());
            }
        });
            
        

        scrollPane = new JScrollPane(textArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        window.add(scrollPane);

    }

    public void createMenuBar() {
        
        menuBar = new JMenuBar();
        window.setJMenuBar(menuBar);
        
        menuFile = new JMenu("File");
        menuBar.add(menuFile);

        menuEdit = new JMenu("Edit");
        menuBar.add(menuEdit);

        menuFormat = new JMenu("Format");
        menuBar.add(menuFormat);

        menuColor = new JMenu("Color");
        menuBar.add(menuColor);


    }

    public void createFileMenu() {

        iNew = new JMenuItem("New");
        iNew.addActionListener(this);
        iNew.setActionCommand("New");
        menuFile.add(iNew);

        iOpen = new JMenuItem("Open");
        iOpen.addActionListener(this);
        iOpen.setActionCommand("Open");
        menuFile.add(iOpen);

        iSave = new JMenuItem("Save");
        iSave.addActionListener(this);
        iSave.setActionCommand("Save");
        menuFile.add(iSave);

        iSaveAs = new JMenuItem("Save As");
        iSaveAs.addActionListener(this);
        iSaveAs.setActionCommand("Save As");
        menuFile.add(iSaveAs);

        iExit = new JMenuItem("Exit");
        iExit.addActionListener(this);
        iExit.setActionCommand("Exit");
        menuFile.add(iExit);


    }

    public void createEditMenu() {

        iUndo = new JMenuItem("Undo");
        iUndo.addActionListener(this);
        iUndo.setActionCommand("Undo");
        menuEdit.add(iUndo);

        iRedo = new JMenuItem("Redo");
        iRedo.addActionListener(this);
        iRedo.setActionCommand("Redo");
        menuEdit.add(iRedo);
    }


    public void createFormatMenu() {

        iWrap = new JMenuItem("Word Wrap: Off");
        iWrap.addActionListener(this);
        iWrap.setActionCommand("Word Wrap");
        menuFormat.add(iWrap);

        menuFont = new JMenu("Font");
        menuFormat.add(menuFont);

        iFontArial = new JMenuItem("Arial");
        iFontArial.addActionListener(this);
        iFontArial.setActionCommand("Arial");
        menuFont.add(iFontArial);

        iFontCSMS = new JMenuItem("Comic Sans MS");
        iFontCSMS.addActionListener(this);
        iFontCSMS.setActionCommand("Comic Sans MS");
        menuFont.add(iFontCSMS);

        iFontTNR = new JMenuItem("Times New Roman");
        iFontTNR.addActionListener(this);
        iFontTNR.setActionCommand("Times New Roman");
        menuFont.add(iFontTNR);


        menuFontSize = new JMenu("Font Size");
        menuFormat.add(menuFontSize);

        iFontSize8 = new JMenuItem("8");
        iFontSize8.addActionListener(this);
        iFontSize8.setActionCommand("size8");
        menuFontSize.add(iFontSize8);

        iFontSize12 = new JMenuItem("12");
        iFontSize12.addActionListener(this);
        iFontSize12.setActionCommand("size12");
        menuFontSize.add(iFontSize12);

        iFontSize16 = new JMenuItem("16");
        iFontSize16.addActionListener(this);
        iFontSize16.setActionCommand("size16");
        menuFontSize.add(iFontSize16);

        iFontSize20 = new JMenuItem("20");
        iFontSize20.addActionListener(this);
        iFontSize20.setActionCommand("size20");
        menuFontSize.add(iFontSize20);

        iFontSize24 = new JMenuItem("24");
        iFontSize24.addActionListener(this);
        iFontSize24.setActionCommand("size24");
        menuFontSize.add(iFontSize24);

        iFontSize28 = new JMenuItem("28");
        iFontSize28.addActionListener(this);
        iFontSize28.setActionCommand("size28");
        menuFontSize.add(iFontSize28);

    }

    public void createColorMenu() {
        
        iColor1 = new JMenuItem("White");
        iColor1.addActionListener(this);
        iColor1.setActionCommand("White");
        menuColor.add(iColor1);

        iColor2 = new JMenuItem("Black");
        iColor2.addActionListener(this);
        iColor2.setActionCommand("Black");
        menuColor.add(iColor2);

        iColor3 = new JMenuItem("Blue");
        iColor3.addActionListener(this);
        iColor3.setActionCommand("Blue");
        menuColor.add(iColor3);

    }







    @Override
    public void actionPerformed(ActionEvent e) {
        
        String command = e.getActionCommand();

        switch(command) {
            case "New": file.newFile(); obsNotify("File", "New file created"); break;
            case "Open": file.open(); obsNotify("File", "File is opened"); break;
            case "Save": file.save(); obsNotify("File", "File is saved"); break;
            case "Save As": file.saveAs(); break;
            case "Exit": file.exit(); obsNotify("File", "Text Editor is closed"); break;

            case "Undo": edit.undo(); obsNotify("Edit", "Undo operation"); break;
            case "Redo": edit.redo(); obsNotify("Edit", "Redo operation"); break;

            case "Word Wrap": format.wordWrap(); break;

            case "Arial": format.setFont(command); obsNotify("Format", "Font Arial chosen"); break;
            case "Comic Sans MS": format.setFont(command); obsNotify("Format", "Font CSMS chosen"); break;
            case "Times New Roman": format.setFont(command); obsNotify("Format", "Font Times New Roman chosen"); break;

            case "size8": format.createFont(8); obsNotify("Format", "Font size: 8"); break;
            case "size12": format.createFont(12); obsNotify("Format", "Font size: 12"); break;
            case "size16": format.createFont(16); obsNotify("Format", "Font size: 16"); break;
            case "size20": format.createFont(20); obsNotify("Format", "Font size: 20"); break;
            case "size24": format.createFont(24); obsNotify("Format", "Font size: 24"); break;
            case "size28": format.createFont(28); obsNotify("Format", "Font size: 28"); break;

            case "White": color.changeColor(command); obsNotify("Color", "Color 'White' chosen"); break;
            case "Black": color.changeColor(command); obsNotify("Color", "Color 'Black' chosen"); break;
            case "Blue": color.changeColor(command); obsNotify("Color", "Color 'Blue' chosen"); break;

        }
    }

}
