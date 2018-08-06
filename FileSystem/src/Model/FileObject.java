package Model;

import java.util.Date;

public abstract class FileObject {
	protected String name;
	protected Date cDate;

	public FileObject(String name,Date cDate) {
		try {
			if(name.length()>32)
				throw new Exception("LONG_NAME_EXCEPTION");
			else if(name.isEmpty())			//added in order not to create "invisible" files.
				throw new Exception("NAME_IS_EMPTY_EXCEPTION");
			else
				this.name=name;

			this.cDate=cDate;

		}catch(Exception exc) {
			switch(exc.getMessage()) {
			case "LONG_NAME_EXCEPTION": System.out.println("LONG_NAME_EXCEPTION");
			case "NAME_IS_EMPTY_EXCEPTION": System.out.println("NAME_IS_EMPTY_EXCEPTION");
			}
		}
	}
	
	public String getName() {
		return this.name;
	}
	
	public String getCDate() {
		return this.cDate.toString();
	}
	
	//int lvl only added as a parameter in order to match to dir more
	public abstract String getStringContent(int lvl);
	
}
