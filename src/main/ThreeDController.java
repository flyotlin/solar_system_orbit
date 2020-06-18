package main;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Pagination;
import javafx.scene.control.Slider;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;

public class ThreeDController implements Initializable, EventHandler<KeyEvent> {
	private double HEIGHT = 800;
	private double WIDTH = 1280;
	public static Planet3D planet = new Planet3D(300);
	private double planetRadius = 300;

	@FXML
	Pane pane;
	@FXML
	Pagination pagination;
	@FXML
	Slider magnificationSlider;
	@FXML
	Button back;

	@Override
	public void handle(KeyEvent e) {
//		switch(e.getCode()) {
//		case W:
//			planet.setPlanetTranslateZ(1);
//			break;
//		case S:
//			planet.setPlanetTranslateZ(-1);
//			break;
//		case A:
//			System.out.println(magnificationSlider.valueProperty());
//		default:
//			break;
//		}
		if(e.getCode() == KeyCode.A) {
//			planet.planet.setRotate(planet.planet.getRotate() + 5);
//			System.out.println("HI");
			planet.setRotate(planet.getRotate() + 5);
		}
		if(e.getCode() == KeyCode.D) {
//			planet.planet.setRotate(planet.planet.getRotate() - 5);			
			planet.setRotate(planet.getRotate() - 5);
		}
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		magnificationSlider.setMin(1);
		magnificationSlider.setMax(400);
		magnificationSlider.setValue(300);
		magnificationSlider.setShowTickLabels(true);
		magnificationSlider.setShowTickMarks(true);
//		magnificationSlider.setMajorTickUnit(50);
//		magnificationSlider.setMinorTickCount(5);
//		magnificationSlider.setBlockIncrement(10);
//		planet.planet.translateZProperty().bind(magnificationSlider.valueProperty());

		magnificationSlider.valueProperty().addListener((e)->{
			planetRadius = magnificationSlider.getValue();
			planet.setRadius(planetRadius);
		});

		Navigator navigate = new Navigator(pagination);
//		pane.getChildren().add(planet.getPlanet());
		addNode(planet.getPlanet());
	}
	
	private void addNode(Node node) {
		pane.getChildren().add(node);
	}
	
	public void back() throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("mainScene.fxml"));
		Scene menuScene = new Scene(root);
		Main.currentStage.setScene(menuScene);
	}
	
}