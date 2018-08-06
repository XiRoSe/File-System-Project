package Controller;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.function.Function;

import Model.FileManager;
import View.ViewLogic;

public class FileSystemController implements Observer{

	private ViewLogic viewlogic;
	private FileManager model;
	private ExecutorService trdPool;
	private Future<String> fut;		//used in order to store the value from showFileSystem() Command

	public FileSystemController(ViewLogic viewlogic, FileManager model) {
		this.viewlogic = viewlogic;
		this.model = model;
		this.trdPool = Executors.newCachedThreadPool();
	}


	private List<String> getCommandDetails(String input) {
		List<String> list = new LinkedList<String>();

			String command = input.substring(0, input.indexOf('('));
			String parentDirName="",fileObjName="",fileSize="",delete="";

			if(command.equals("delete")) 
				delete = input.substring(input.indexOf('(')+1,input.indexOf(')'));
			else if(!command.equals("showFileSystem")) {
				parentDirName = input.substring(input.indexOf('(')+1,input.indexOf(','));
				if(command.equals("addFile")) {
					fileObjName = input.substring(input.indexOf(',')+1,input.lastIndexOf(','));
					fileSize = input.substring(input.lastIndexOf(',')+1,input.lastIndexOf(')'));
				}
				else //for addDir Command
					fileObjName = input.substring(input.indexOf(',')+1,input.lastIndexOf(')'));
			}

			list.addAll(Arrays.asList(command,parentDirName,fileObjName,fileSize,delete));

		return list;
	}


	@Override
	public void update(Observable obs, Object obj){
		if(obs.getClass() == ViewLogic.class) {		//update from view
			//			System.out.println(this.viewlogic.getCommand()); 	//ADDED FOR CHECK
			String input = this.viewlogic.getCommand();

			if(input.matches(".*(.*).*")) {

				List<String> list = this.getCommandDetails(input);
				String command=list.get(0),parentDirName=list.get(1),fileObjName=list.get(2),fileSize=list.get(3),delete=list.get(4);

				//act according to the proper command structure
				switch(command) {
				case "addFile": this.trdPool.execute(new ParameterRunnable<>((x,y,z)->model.addFile(x,y,z),parentDirName,fileObjName, Integer.parseInt(fileSize)));break;
				case "addDir": this.trdPool.execute(new ParameterRunnable<>((x,y)->model.addDir(x,y),parentDirName,fileObjName));break;
				case "delete": this.trdPool.execute(new ParameterRunnable<>((x)->model.delete(x),delete));break;
				case "showFileSystem": fut = this.trdPool.submit(()->model.showFileSystem());break;
				default: System.out.println("COMMAND_NOT_FOUND");break;
				}
			}
		}


		else if(obs.getClass() == FileManager.class) {		//update from model, currently only on showFileSystem() method

			this.trdPool.execute(()->{
				try {
					viewlogic.showFileSystem(fut.get());
				} catch (InterruptedException | ExecutionException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			});
		}

	}	
}
