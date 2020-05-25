package main;

import javafx.animation.PathTransition;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Main extends Application {

	private static final int HEIGHT = 400;
	private static final int WIDTH = 600;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		Pane pane = new Pane();
		
		// TODO Auto-generated method stub
		Rectangle rect = new Rectangle(100, 100);
		Circle circle = new Circle(WIDTH/2, HEIGHT/2, 150);
		// sun
		Circle sun = new Circle(WIDTH/2, HEIGHT/2, 30);
		sun.setFill(javafx.scene.paint.Color.YELLOW);
		
		// mercury
		Circle mercury = new Circle(200, 200, 5);
		mercury.setFill(javafx.scene.paint.Color.DARKGRAY);		
		
		// venus
		Circle venus = new Circle(100, 200, 10);
		venus.setFill(javafx.scene.paint.Color.ORANGERED);	
		venus.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent arg0) {
				// TODO Auto-generated method stub
				Text t_venus = new Text("Venus\nDiameter: 12696 meters\nMass: 1.82 Earth Mass");
				t_venus.setTranslateY(10);
				pane.getChildren().add(t_venus);
				System.out.println("Clicked");					
			}	
			
		});
		// earth
		Circle earth = new Circle(100, 100, 10);
		earth.setFill(javafx.scene.paint.Color.BLUE);	
		earth.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent arg0) {
				// TODO Auto-generated method stub
				Text t_earth = new Text("Earth\nDiameter: 12696 meters\nMass: 1.82 Earth Mass");
				t_earth.setTranslateY(10);
				pane.getChildren().add(t_earth);
				System.out.println("Clicked");					
			}	
			
		});
		
		
		PathTransition transition = new PathTransition();
		transition.setNode(earth);
		transition.setDuration(Duration.seconds(4));
		transition.setPath(circle);
		transition.setCycleCount(PathTransition.INDEFINITE);
		transition.play();
		
		
		pane.getChildren().addAll(sun, mercury, venus, earth);
		
		Scene scene = new Scene(pane, WIDTH, HEIGHT);
		
		primaryStage.setScene(scene);
		primaryStage.show();
	}

}
