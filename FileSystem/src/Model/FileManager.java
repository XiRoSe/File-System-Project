package Model;

import java.util.Date;
import java.util.Observable;

public class FileManager extends Observable{
	private DirectoryTree dt;
	private FileMap fm;
	
	public FileManager() {
		dt = new DirectoryTree(new Dir("Root",new Date()));
		fm = new FileMap();
	}
	
	public void addDir(String parentDirName, String dirName) {
		try {
			DirectoryTree parent=dt.getNode(parentDirName);
			if(parent==null)
				throw new Exception("NO_SUCH_DIRECTORY_EXCEPTION");
			dt.addDir(parentDirName, dirName);
		}catch(Exception exc) {
			System.out.println(exc.getMessage());
		}
		
		this.setChanged();
	}
	
	public void addFile(String parentDirName, String fileName, Integer fileSize) {
		try {
		DirectoryTree parent=dt.getNode(parentDirName);
		if(parent==null)
			throw new Exception("NO_SUCH_DIRECTORY_EXCEPTION");
		parent.getData().addFile(fileName, fileSize);
		fm.mapFileToFolder(parentDirName, fileName);
		}catch(Exception exc) {
			System.out.println(exc.getMessage());
		}
		
		this.setChanged();
	}
	
	public String showFileSystem() {
		String str = dt.showFileSystem();
		this.setChanged();
		this.notifyObservers();
		return str;
	}
	
	public void delete(String name) {
		try {
			if(name=="Root")
				throw new Exception("CANNOT_DELETE_ROOT_EXCEPTION");
			if(dt.getData().delete() && dt.deleteDir(name)) // means it's a Dir
				return;
			else if(fm.getFolderByFile(name)!=null) //means it's a file
				dt.getNode(fm.getFolderByFile(name)).getData().delete(name);
		}catch(Exception exc) {
			System.out.println(exc.getMessage());
		}
		
		this.setChanged();
	}
		
}
