package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
	public static Stage currentStage;
	public static Scene menuScene;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		currentStage = primaryStage;
		Parent root = FXMLLoader.load(getClass().getResource("mainScene.fxml"));
		menuScene = new Scene(root);
		
		currentStage.setTitle("Solar System Orbit Simulation");
		currentStage.setScene(menuScene);
		currentStage.setResizable(false);	// set the window not resizable
		currentStage.show();
	
		
	}

}
