package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Ellipse;
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
		
//		Pane pane = new Pane();
//		Button btn = new Button("OK");
//		
//		Planet sun = new Planet("sun", 40, 3.0, 500, 500, 150, 150);
//		sun.planet.setFill(Color.YELLOW);
//		
//		Planet mercury = new Planet("mercury", 7, 3.0, 500, 500, 150, 150);
//		mercury.planet.setFill(Color.DARKGRAY);
//		mercury.playOrbit();
//		
//		Planet earth = new Planet("earth", 10, 6.0, 250, 250, 300, 300);
//		earth.planet.setFill(Color.BLUE);
//		earth.playOrbit();
		
////		Circle earth = new Circle();
////		earth.setRadius(50);
////		earth.setCenterX(250);
////		earth.setCenterY(250);
////		pane.getChildren().add(earth);
//		
//		
////		Ellipse planetOrbit = new ;
//		pane.getChildren().add(sun.planet);
//		pane.getChildren().add(mercury.planet);
//		pane.getChildren().add(earth.planet);
//		pane.getChildren().add(btn);
//		
//		Scene scene = new Scene(pane, 1280, 800);
//		primaryStage.setScene(scene);
//		primaryStage.show();
		
	}

}
