package main;

import java.io.File;
import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class MainController {
	@FXML
	Button twoD;
	@FXML
	Button threeD;	
	@FXML
	Button more;
	@FXML
	Button exit;	
	
	public static int WIDTH = 1280;
	public static int HEIGHT = 800;
	
	@FXML
	public void on2dPressed(ActionEvent event) throws IOException {
//		Parent twoDD = FXMLLoader.load(getClass().getResource("2dScene.fxml"));
		Pane twoDD = new Pane();
		twoDD.setStyle("-fx-background-color: #000000;");
		Scene twoDScene = new Scene(twoDD, 1280, 800);
		
		// back button
		Button exit = new Button("Back");
		System.out.println(exit.getWidth());
		exit.setTranslateX(WIDTH-50);
		twoDD.getChildren().add(exit);
		exit.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				// TODO Auto-generated method stub
				try {
					Parent root = FXMLLoader.load(getClass().getResource("mainScene.fxml"));
					Scene menuScene = new Scene(root);
					Main.currentStage.setScene(menuScene);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		});
		
		// about the planet
		Pane gsun = new Pane();
		Text ss = new Text("\n\n\n\n\n\n\n\nSun");
		Text tsun = new Text("\n\n\n\n\n\n\n\n\nDiameter: 91740 km\nMass: 300000 Earth Mass\nAverage Temperature: 5083¢XC\nYellow Dwarf, Main-sequence Star");
		Image image1 = new Image(new File("image/sun.jpg").toURI().toString());
		ImageView img1 = new ImageView(image1);
		ss.setFill(Color.WHITE);
		tsun.setFill(Color.WHITE);
		ss.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 20));
		tsun.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 20));
		
		
		gsun.getChildren().addAll(tsun, ss, img1);
		twoDD.getChildren().add(gsun);

		gsun.setVisible(false);
		
		
		Planet sun = new Planet("sun", 40, 3.0, WIDTH/2, HEIGHT/2, 150, 150);
		sun.planet.setFill(Color.YELLOW);
		sun.planet.setCenterX(WIDTH/2);
		sun.planet.setCenterY(HEIGHT/2);
		
		sun.planet.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent arg0) {
				// TODO Auto-generated method stub
				if(gsun.isVisible() == false) {
					gsun.setVisible(true);
				}
				else {
					gsun.setVisible(false);
				}
			}
			
		});
		
		
		Planet mercury = new Planet("mercury", 5, 5.0, WIDTH/2, HEIGHT/2, 150, 150);
		mercury.planet.setFill(Color.DARKGRAY);
		mercury.playOrbit();
		
		Planet venus = new Planet("venus", 7, 7.0, WIDTH/2, HEIGHT/2, 200, 200);
		venus.planet.setFill(Color.rgb(252, 224, 203));
		venus.playOrbit();
		
		Planet earth = new Planet("earth", 10, 12.0, WIDTH/2, HEIGHT/2, 300, 300);
		earth.planet.setFill(Color.BLUE);
		earth.playOrbit();
		
		twoDD.getChildren().add(sun.planet);
		twoDD.getChildren().add(mercury.planet);
		twoDD.getChildren().add(venus.planet);
		twoDD.getChildren().add(earth.planet);
		
		twoDScene.getRoot().requestFocus();	// let mazeScene pane get focus, to get keyboard event
		Main.currentStage.setScene(twoDScene);
		

	}
	
	@FXML
	public void onExitPressed() {
		Main.currentStage.close();
	}
	

	
}
