package main;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.animation.AnimationTimer;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Point3D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Sphere;

public class MainController implements Initializable, EventHandler<KeyEvent> {
	public static int WIDTH = 1280;
	public static int HEIGHT = 800;
	Sphere sphere_earth = new Sphere(150);
	
	public double anchorX, anchorY;
	@FXML
	Pane pane;
	@FXML
	Slider slider;
	@FXML
	Button twoD;
	@FXML
	Button threeD;
	@FXML
	Button wiki;
	@FXML
	Button more;
	@FXML
	Button exit;	
	
	@FXML
	public void on2dPressed(ActionEvent event) throws IOException {
		Parent twoDD = FXMLLoader.load(getClass().getResource("2dScene.fxml"));
		Scene twoDScene = new Scene(twoDD);
		twoDScene.getRoot().requestFocus();	// let mazeScene pane get focus, to get keyboard event
		Main.currentStage.setScene(twoDScene);
		

	}
	
	@FXML
	public void on3dPressed() throws IOException {
//		ThreeDController render3D = new ThreeDController();
//		render3D.setScene();
		Orbit3D renderOribt3D = new Orbit3D();
		renderOribt3D.setScene();
	}
	
	@FXML
	public void onWikiPressed() throws IOException {
		Parent threeDD = FXMLLoader.load(getClass().getResource("3dScene.fxml"));
		Scene threeDScene = new Scene(threeDD);
		threeDScene.getRoot().requestFocus();	// let mazeScene pane get focus, to get keyboard event
		Main.currentStage.setScene(threeDScene);
	}
	
	@FXML
	public void onExitPressed() {
		Main.currentStage.close();
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		PhongMaterial earthMaterial = new PhongMaterial();
		earthMaterial.setDiffuseMap(new Image(getClass().getResourceAsStream("/resources/earth/earth-d.jpg")));
		earthMaterial.setSelfIlluminationMap(new Image(getClass().getResourceAsStream("/resources/earth/earth-l.jpg")));
		earthMaterial.setSpecularMap(new Image(getClass().getResourceAsStream("/resources/earth/earth-s.jpg")));
		earthMaterial.setBumpMap(new Image(getClass().getResourceAsStream("/resources/earth/earth-n.jpg")));
		
		sphere_earth.setMaterial(earthMaterial);
		sphere_earth.setTranslateX(400);
		sphere_earth.setTranslateY(450);
		
		pane.getChildren().add(sphere_earth);
		prepareAnimation();
		
		
		// slider
		slider.setMax(800);
		slider.setMin(-1000);
		slider.setPrefWidth(300d);
		
		slider.setShowTickLabels(true);
	    slider.setTranslateZ(5);
	    slider.setStyle("-fx-base: black");
	    
	    
//	    sphere_earth.rotateProperty().bind(slider.valueProperty());
//	    sphere_earth.translateZProperty().bind(slider.valueProperty());
		
	}
	
	private void prepareAnimation() {
		sphere_earth.setRotationAxis(new Point3D(0, 1, 0));
	    AnimationTimer timer = new AnimationTimer() {
	      @Override
	      public void handle(long now) {
	    	  sphere_earth.rotateProperty().set(sphere_earth.getRotate() + 0.2);
	      }
	    };
	    timer.start();
	}
	

	


	@Override
	public void handle(KeyEvent e) {
		if(e.getCode() == KeyCode.A) {
			sphere_earth.setRotate(sphere_earth.getRotate() + 5);
		}
		if(e.getCode() == KeyCode.D) {
			sphere_earth.setRotate(sphere_earth.getRotate() - 5);				
		}
	}

	
}
