package View;
	
import Controller.FileSystemController;
import Model.FileManager;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.fxml.FXMLLoader;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
		    FXMLLoader loader = new FXMLLoader(getClass().getResource("View.fxml"));
		    BorderPane root = loader.load();
		    Scene scene = new Scene(root,450,300);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			FileManager model = new FileManager();
			ViewLogic viewlogic = (ViewLogic)loader.getController();
			FileSystemController controller = new FileSystemController(viewlogic, model);
			viewlogic.addObserver(controller);
			model.addObserver(controller);
			primaryStage.setScene(scene);
			primaryStage.setTitle("Java MVC FileSystem CLI");
			primaryStage.show();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
