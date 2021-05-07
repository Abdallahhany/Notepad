import java.awt.FileDialog;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;

public class FunctionFile {
	GUI gui;
	private String fileName;
	private String fileAddress;

	public FunctionFile(GUI gui) {
		this.gui = gui;
	}
	public void newFile() {
		gui.textArea.setText("");
		gui.window.setTitle("new");
		fileName = null;
		fileAddress = null;
	}
	public void open() {
		FileDialog fileDialog = new FileDialog(gui.window, "Open",FileDialog.LOAD);
		fileDialog.setVisible(true);
		if(fileDialog.getFile()!= null) {
			fileName = fileDialog.getFile();
			fileAddress = fileDialog.getDirectory();
			gui.window.setTitle(fileName);
		}
		System.out.print(fileAddress + fileName);
		try {
			BufferedReader bufferedReader = new BufferedReader(new FileReader(fileAddress + fileName));
			
			gui.textArea.setText("");
			
			String line = null;
			while((line = bufferedReader.readLine() )!= null) {
				gui.textArea.append(line + "\n");
			}
			bufferedReader.close();
		
		}catch(Exception e) {
			System.out.print(e);
		}
	}
	public void save() {
		if(fileName == null) {//new File
			saveAs();
		}else {
			try {//overWrite the current file
				FileWriter fileWriter = new FileWriter(fileAddress + fileName);
				fileWriter.write(gui.textArea.getText());
				gui.window.setTitle(fileName);
				fileWriter.close();
				
			}catch(Exception e) {
				System.out.print(e);
			}
		}
	}
	public void saveAs() {
		FileDialog fileDialog = new FileDialog(gui.window, "Save",FileDialog.SAVE );
		fileDialog.setVisible(true);
		if(fileDialog.getFile()!= null) {
			fileName = fileDialog.getFile();
			fileAddress = fileDialog.getDirectory();
			gui.window.setTitle(fileName);
		}
		try {
			FileWriter fileWriter = new FileWriter(fileAddress + fileName);
			fileWriter.write(gui.textArea.getText());
			fileWriter.close();
		}catch(Exception e) {
			System.out.print(e);
		}
	}
	public void exit() {
		System.exit(0);
	}
}

