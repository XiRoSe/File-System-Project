package View;

import java.util.Observable;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class ViewLogic extends Observable{
	
	@FXML
	private TextArea input;
	
	private String command;
	
	public ViewLogic() {
		command = new String(); 
	}
	
	public String getCommand() {
		return command;
	}
	
	public void showFileSystem(String fileStructure) {
		this.input.appendText("\n" + fileStructure + "\n");
	}
	
	public void enterCommand(KeyEvent event) {
		if(event.getCode().equals(KeyCode.ENTER) || event.getCharacter().getBytes()[0] == '\n' || event.getCharacter().getBytes()[0] == '\r') {
			command = input.getText().substring(input.getText().lastIndexOf('\n')+1, input.getText().length());
			this.setChanged();
			this.notifyObservers();
		}
	}
	
	public void about() {
        String content = new String();
		Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("About");
        alert.setHeaderText("About");
        content = "Author : Matan Avitan. \nCreation Date : 14/05/18.";
        alert.setContentText(content);
        alert.showAndWait();
	}
	
	public void cmndList() {
        String content = new String();
		Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Command List");
        alert.setHeaderText("Command List");
        content = "Command List:\n1.addFile(parentDirName,fileName,fileSize).\n2.addDir(parentDirName,dirName).\n"
        		+ "3.delete(name).\n4.showFileSystem().\n\n"
        		+ "Examples:\nadding a file - addFile(Root,file1,450).\nadding a directory - addDir(Root,dir1).\n"
        		+ "deleting a file - delete(file1) or delete(dir1).\nshowing the file system - showFileSystem().\n\n"
        		+ "P.S - Very Important~!\n1.the Root directory is the first and the main directory.\n2.all the commands and files are case sensitive and must be typed correctly.\n"
        		+ "3.please enter the functions parameters without any spaces.\n"
        		+ "4.I hope you liked the program!.";
        alert.setContentText(content);
        alert.showAndWait();
	}
	
	public void close() {
		System.exit(2000);
	}
	
}
