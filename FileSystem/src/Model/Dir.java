package Model;

import java.util.Date;
import java.util.HashMap;

public class Dir extends FileObject{	
	private HashMap<String,FileObject> content; 	//each folder contains only it's files;

	public Dir(String name,Date cDate) {
		super(name,cDate);
		this.content = new HashMap<String,FileObject>();
	}

	public String getName() {
		return this.name;
	}

	public void addFile(String fileName, Integer fileSize) {
		content.put(fileName, new File(fileName, fileSize, new Date()));
	}

	public void delete(String name) {
		if(this.content.containsKey(name)) 
			this.content.remove(name);
	}
	
	public String getStringContent(int lvl) {
		StringBuilder fileStructure = new StringBuilder();
		if(!this.content.isEmpty()) {
			String adder="";
			for(FileObject itr: content.values()) {
				if(lvl>=0)
					adder = new String(new char[lvl]).replace("\0","--");
				fileStructure.append(adder + itr.getStringContent(0));
			}
		}
		return fileStructure.toString();

	}

	public boolean delete() {
		content.clear();
		return true;
	}

}

