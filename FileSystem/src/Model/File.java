package Model;

import java.util.Date;

public class File extends FileObject{
	
	private long size;
	
	public File(String name,int size,Date cDate) {
		super(name,cDate);
		
		try {
			if(size<1)
				throw new Exception("SIZE_NOT_POSITIVE_EXCEPTION");
			else
				this.size=size;
			
		}catch(Exception exc) {
			switch(exc.getMessage()) {
				case "SIZE_NOT_POSITIVE_EXCEPTION": System.out.println("SIZE_NOT_POSITIVE_EXCEPTION");
			}
		}
	}
	
	public String getStringContent(int lvl) {
		StringBuilder fileStructure = new StringBuilder();
		fileStructure.append("File name: " + this.name + ", " + "File size: " + this.size + ", " + "Creation date: " + this.cDate.toString() + "." + '\n');
		return fileStructure.toString();
	}

}
