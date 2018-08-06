package Model;

import java.util.Hashtable;

public class FileMap {
	private Hashtable<String,String> dirs;	 //file name, directory name
	
	public FileMap() {
		dirs = new Hashtable<>();
	}
	
	//maps all files by their containing folder
	public void mapFileToFolder(String parentDirName, String fileName) {
			dirs.put(fileName,parentDirName);
	}
	
	//get's the containing folder of a file
	public String getFolderByFile(String name) {
		if(dirs.containsKey(name))
			return dirs.get(name);
		else
			return null;
	}
	
}
