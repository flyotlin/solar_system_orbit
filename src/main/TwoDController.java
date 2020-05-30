package main;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class TwoDController implements Initializable, EventHandler<KeyEvent> {
	
	public static int WIDTH = 1280;
	public static int HEIGHT = 800;
	public int planet_num = 8;
	public boolean is_playing = true; 
	
	Planet sun = new Planet(8, "sun", Color.ORANGE, 109, 3.0, WIDTH/2, HEIGHT/2, 150, 150);
	Planet[] solarPlanet = new Planet[8];	// the remaining 8 planets
	
	@FXML
	Pane pane2D;
	
	@FXML
	VBox vSun;
	@FXML
	VBox v0;
	@FXML
	VBox v1;
	@FXML
	VBox v2;
	@FXML
	VBox v3;
	@FXML
	VBox v4;
	@FXML
	VBox v5;
	@FXML
	VBox v6;
	@FXML
	VBox v7;
	
	public int i = 0;
	
	ArrayList<VBox> vv = new ArrayList<VBox>();
	
	@Override
	public void handle(KeyEvent e) {
		// TODO Auto-generated method stub
		if(e.getCode() == KeyCode.SPACE) {
			if(is_playing == true) {
				for(int i = 0; i < planet_num; i++) {
					solarPlanet[i].pauseOrbit();
				}	
				is_playing = false;
			}
			else {
				for(int i = 0; i < planet_num; i++) {
					solarPlanet[i].playOrbit();
				}
				is_playing = true;
			}
		}
	}

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		vv.add(v0);
		vv.add(v1);
		vv.add(v2);
		vv.add(v3);
		vv.add(v4);
		vv.add(v5);
		vv.add(v6);
		vv.add(v7);
		vv.add(vSun);
		// sun
		sun.planet.setTranslateX(WIDTH/2);
		sun.planet.setTranslateY(HEIGHT/2);
		
		// the other planets
		solarPlanet[0] = new Planet(0, "mercury", Color.GRAY, 8, 4.385, WIDTH/2, HEIGHT/2, 150, 150);
		solarPlanet[1] = new Planet(1, "venus", Color.rgb(207,153,52), 12, 11.238, WIDTH/2, HEIGHT/2, 200, 200);
		solarPlanet[2] = new Planet(2, "earth", Color.BLUE, 11, 18.25, WIDTH/2, HEIGHT/2, 250, 250);
		solarPlanet[3] = new Planet(3, "mars", Color.RED, 7, 34.3, WIDTH/2, HEIGHT/2, 300, 300);
		solarPlanet[4] = new Planet(4, "jupiter", Color.rgb(255,140,0), 20, 216.6/5, WIDTH/2, HEIGHT/2, 350, 350);
		solarPlanet[5] = new Planet(5, "saturn", Color.rgb(112,128,144), 15, 538.375/6, WIDTH/2, HEIGHT/2, 400, 400);
		solarPlanet[6] = new Planet(6, "uranus", Color.rgb(196,233,238), 15, 1533/10, WIDTH/2, HEIGHT/2, 450, 450);
		solarPlanet[7] = new Planet(7, "neptune", Color.rgb(66, 98, 243), 13, 3007.6/16, WIDTH/2, HEIGHT/2, 500, 500);
		
		System.out.println(vv.get(2));
		// set 
		sun.setMouse(vv);
		for(i = 0; i < planet_num; i++) {
			solarPlanet[i].setMouse(vv);
		}
		// add, play
		sun.addNode(pane2D);
		for(int i = 0; i < planet_num; i++) {
			solarPlanet[i].addNode(pane2D);
			solarPlanet[i].playOrbit();
		}
	}
	
	@FXML
	public void onBackPressed() throws IOException {
		back();
	}
	
	public void back() throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("mainScene.fxml"));
		Scene menuScene = new Scene(root);
		Main.currentStage.setScene(menuScene);
	}


}

