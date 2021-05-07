
public class FunctionEdit {
GUI gui;
	
	public FunctionEdit(GUI gui) {
		
		this.gui = gui;
	}
	
	public void undo() {
		
		gui.um.undo();
	}
	
	
	public void redo() {
		
		gui.um.redo();
	}
}
