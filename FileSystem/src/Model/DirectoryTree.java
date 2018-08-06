package Model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class DirectoryTree{
	protected Dir data = null;		//a reference to the current directory
	protected List<DirectoryTree> children = new ArrayList<>();
	protected DirectoryTree parent = null; 	//a reference to the upper level in the directoryTree
	//a right and left references in order to create a kind of a sub binary lexicographic tree
	protected DirectoryTree biggerLex = null;
	protected DirectoryTree smallerLex = null;


	public DirectoryTree(Dir data) {
		this.data=data;
	}
	
	public String showFileSystem() {
		StringBuilder fileStructure = new StringBuilder();
		fileStructure.append("Dir name: " + this.data.getName() + ", " + "Creation date: " + this.data.getCDate() + "." + '\n');
		fileStructure.append(this.childrenToStr(1));		//prints all dirs inside the current dir
		

		return fileStructure.toString();
	}
	
	protected String childrenToStr(int lvl) {
		StringBuilder fileStructure = new StringBuilder();

		String adder = new String(new char[lvl]).replace("\0", "--");
		for(DirectoryTree itr: children) {		//prints all dirs inside the current dir
			fileStructure.append(adder + "Dir name: " + itr.data.getName() + ", " + "Creation date: " + itr.data.getCDate() + "." + '\n');
			fileStructure.append(itr.getData().getStringContent(lvl+1)); 		// print all files inside a current Dir
			fileStructure.append(itr.childrenToStr(lvl+1));
		}
		
		return fileStructure.toString();

	}

	public void addDir(String parentDirName, String dirName){
		DirectoryTree child = new DirectoryTree(new Dir(dirName,new Date()));
		DirectoryTree parent = getNode(parentDirName);
		parent.addChild(child);
		child.setParent(parent);
		this.updateLexicographicPos(parent, child);
	}
	
	public DirectoryTree getNode(String nodeName) {
		if(nodeName == null)
			return null;
		if(this.data.getName().compareTo(nodeName)==0)
			return this;
		else if(this.data.getName().compareTo(nodeName)>0) { //if nodeName is bigger then current or vice versa
			if(biggerLex == null) 	//if he can't go on and search
				return null;
			return biggerLex.getNode(nodeName);
		}
		else {
			if(smallerLex==null)		//if he can't go on and search
				return null;
			return smallerLex.getNode(nodeName);
		}

	}

	private void updateLexicographicPos(DirectoryTree parentDir, DirectoryTree curDir) {
		if(parentDir.getData().getName().compareTo(curDir.getData().getName())>0) {
			if(biggerLex!=null)
				biggerLex.updateLexicographicPos(parentDir, curDir);
			else
				biggerLex = curDir;
		}
		else {
			if(smallerLex!=null)
				smallerLex.updateLexicographicPos(parentDir, curDir);
			else
				smallerLex = curDir;
		}
	}

	private void addChild(DirectoryTree child) {
		child.setParent(this);
		this.children.add(child);
	}

	public boolean deleteDir(String name) {
		DirectoryTree rmv = this.getNode(name);
		if(rmv==null) //not found
			return false;
		rmv.getParent().getChildren().remove(rmv);
		rmv.setParent(null);
		rmv.getData().delete();

		return true;
	}

	public List<DirectoryTree> getChildren() {
		return children;
	}

	public Dir getData() {
		return data;
	}
	
	protected void setParent(DirectoryTree parent) {
		this.parent = parent;
	}

	public DirectoryTree getParent() {
		return parent;
	}
	
}
