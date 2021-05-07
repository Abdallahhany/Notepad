import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

public class GUI implements ActionListener{
	
	FunctionFile functionFile = new FunctionFile(this);
	FunctionFormat functionFormat = new FunctionFormat(this);
	FunctionColor color = new FunctionColor(this);
	FunctionEdit functionEdit = new FunctionEdit(this);
	UndoManager um = new UndoManager();
	KeyHandler keyHandler = new KeyHandler(this);

	
	//windows
	protected JFrame window;
	
	//Text Area
	protected JTextArea textArea;
	private JScrollPane scrollPane;
	
	//Menu Bar items
	private JMenuBar menuBar;
	protected JMenu menuFile, menuEdit, menuFormat,menuColor;
	
	//File  Menu
	private JMenuItem iNew, iOpen, iSave, iSaveAs, iExit;
	
	// edit menu
	private JMenuItem iUndo, iRedo;
	
	//color menu
	private JMenuItem iColor1, iColor2, iColor3, iColor4;
	
	//File Format
	protected JMenuItem iWrap, iFontArial, iFontCSMS, iFontTNR, iFontSize8, iFontSize12,
	iFontSize16, iFontSize20,  iFontSize24, iFontSize28;
	private JMenu menuFont, menuFontSize;
	protected boolean wordWrapOn = false;
	
	public static void main(String []args) {
		new GUI();
	}
	
	public GUI() {
		
		createWindow();
		createTextArea();
		createMenuBar();
		createFileMenu();
		createFormateMenu();
		createColorMenu();
		createEditMenu();
		functionFormat.selectedFont = "Arial";
		functionFormat.createFont(16);
		functionFormat.wordWrap();
		color.changeColor("White");
		window.setVisible(true);
	}
	
	public void createWindow() {
		window = new JFrame("NotePad");
		window.setSize(800,600);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public void createTextArea() {
		textArea = new JTextArea();
		
		textArea.addKeyListener(keyHandler);
		
		textArea.getDocument().addUndoableEditListener(new UndoableEditListener() {
			
			@Override
			public void undoableEditHappened(UndoableEditEvent arg0) {
				// TODO Auto-generated method stub
				um.addEdit(arg0.getEdit());
			}
		});
		
		scrollPane = new JScrollPane(textArea,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
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
	
	public void createFormateMenu() {
		
		//word Wrap
		iWrap = new JMenuItem("Word Wrap: off");
		iWrap.addActionListener(this);
		iWrap.setActionCommand("Word Wrap");
		menuFormat.add(iWrap);
		
		//Font Style and items
		menuFont = new JMenu("Font");
		menuFormat.add(menuFont);
		
		//font types
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
		
		//Font Size 
		menuFontSize = new JMenu("Font Size");
		menuFormat.add(menuFontSize);
		
		iFontSize8 = new JMenuItem("8");
		iFontSize8.addActionListener(this);
		iFontSize8.setActionCommand("8");
		menuFontSize.add(iFontSize8);
		
		iFontSize12 = new JMenuItem("12");
		iFontSize12.addActionListener(this);
		iFontSize12.setActionCommand("12");
		menuFontSize.add(iFontSize12);
		
		iFontSize16 = new JMenuItem("16");
		iFontSize16.addActionListener(this);
		iFontSize16.setActionCommand("16");
		menuFontSize.add(iFontSize16);
		
		iFontSize20 = new JMenuItem("20");
		iFontSize20.addActionListener(this);
		iFontSize20.setActionCommand("20");
		menuFontSize.add(iFontSize20);
		
		iFontSize24 = new JMenuItem("24");
		iFontSize24.addActionListener(this);
		iFontSize24.setActionCommand("24");
		menuFontSize.add(iFontSize24);
		
		iFontSize28 = new JMenuItem("28");
		iFontSize28.addActionListener(this);
		iFontSize28.setActionCommand("28");
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
		
		iColor3 = new JMenuItem("Gray");
		iColor3.addActionListener(this);
		iColor3.setActionCommand("Gray");
		menuColor.add(iColor3);
		
		iColor4 = new JMenuItem("Terminal");
		iColor4.addActionListener(this);
		iColor4.setActionCommand("Terminal");
		menuColor.add(iColor4);
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
	
	public void actionPerformed(ActionEvent event) {
		String command = event.getActionCommand();
		switch (command) {
		case "New":
			functionFile.newFile();
			break;
		case "Open":
			functionFile.open();
			break;
		case "Save As":
			functionFile.saveAs();
			break;
		case "Save":
			functionFile.save();
			break;
		case "Exit":
			functionFile.exit();
			break;
		case "Undo":
			functionEdit.undo();
			break;
		case "Redo":
			functionEdit.redo();
			break;
			
		case "Word Wrap":
			functionFormat.wordWrap();
			break;
		case "Arial":
			functionFormat.setFont(command);
			break;
		case "Comic Sans MS":
			functionFormat.setFont(command);
			break;
		case "Times New Roman":
			functionFormat.setFont(command);
			break;
		case "8":
			functionFormat.createFont(8);
			break;
		case "12":
			functionFormat.createFont(12);
			break;
		case "16":
			functionFormat.createFont(16);
			break;
		case "20":
			functionFormat.createFont(20);
			break;
		case "24":
			functionFormat.createFont(24);
			break;
		case "28":
			functionFormat.createFont(28);
			break;
		case "White":
			color.changeColor(command);
			break;
		case "Black" :
			color.changeColor(command);
			break;
		case "Gray" :
			color.changeColor(command);
			break;
		case "Terminal":
			color.changeColor(command);
			break;
		}
	}
}
