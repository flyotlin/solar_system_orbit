package main;

import java.util.List;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Point3D;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Line;
import javafx.scene.shape.Sphere;
import javafx.scene.text.Font;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;

public class SolarPlanet {
	public Label planetName = new Label();
	public Label moonName = new Label("Moon");
	public Pane orbitLinePane = new Pane();
	private final int WIDTH = 1280;
	private final int HEIGHT= 800;
	private double playSpeed = 1;
	private double radius;
	private double elapsedTime = 0;
	private Sphere planetSphere = new Sphere();
	private Sphere moonSphere = new Sphere(4);
	private boolean start_drawing = false;
	
	PhongMaterial planetMaterial = new PhongMaterial();
	Timeline revolution = new Timeline();
	Timeline rotation = new Timeline();
	Timeline timer = new Timeline();
	Timeline moonRevolution = new Timeline();
	Timeline moonRotation = new Timeline();
	double prev_x, prev_y;
//	double start_x, start_y;
//	boolean notMetTwice = true;
	
	
	public SolarPlanet(String name, double xPos, double yPos, double radius, String url) {
		
		planetName.setVisible(false);
		planetName.setText(name);
		planetName.setTextFill(Color.WHITE);
		planetName.setFont(new Font("微軟正黑體", 15));
		
		moonName.setVisible(false);
		moonName.setTextFill(Color.WHITE);
		moonName.setFont(new Font("微軟正黑體", 12));
		
		planetSphere.setLayoutX(xPos);
		planetSphere.setLayoutY(yPos);
		
		planetSphere.setRadius(radius);
		this.radius = radius;
		
		planetMaterial.setDiffuseMap(new Image(getClass().getResourceAsStream(url)));
		planetSphere.setMaterial(planetMaterial);
		
		
		planetSphere.getTransforms().add(new Rotate(90, Rotate.X_AXIS));
		moonSphere.getTransforms().add(new Rotate(90, Rotate.X_AXIS));
		
		if(Main.real == 0 ) {
			moonSphere.setRadius(4);
		}
		else {
			moonSphere.setRadius(0.27);
		}
		startTimer();
		
		setMouseHover();
		setMouseClicked();
	}
	
	public Sphere getPlanetSphere() {
		return planetSphere;
	}
	
	public Sphere getMoonSphere() {
		return moonSphere;
	}
	
	public void setIsDrawing(boolean tr) {
		start_drawing = tr;
	}
	
	public void playRevolution(double axe_x, double axe_y, double revolutionPeriod, double translate) {
		planetSphere.setRotationAxis(new Point3D(0, Math.sqrt(2)*Math.cos(Math.toRadians(90)), Math.sqrt(2)*Math.sin(Math.toRadians(90))));
		revolution = new Timeline(new KeyFrame(Duration.millis(1), (e)->{
			double x = WIDTH/2 + axe_x*Math.cos(-(elapsedTime*(2*(Math.PI/(revolutionPeriod*1000))) + translate));
			double y = HEIGHT/2 + axe_y*Math.sin(-(elapsedTime*(2*(Math.PI/(revolutionPeriod*1000))) + translate));
			
			planetSphere.setLayoutX(x);
			planetSphere.setLayoutY(y);
			
			if(start_drawing) {
				drawOrbit(prev_x, prev_y, x, y);
				prev_x = x;
				prev_y = y;					
			}

		}));
		revolution.setCycleCount(Timeline.INDEFINITE);
		revolution.play();
	}
	
	public void playRotation(double rotationPeriod) {
		rotation = new Timeline(new KeyFrame(Duration.millis(1), (e)->{
			planetSphere.setRotate(planetSphere.getRotate() - (360.0/(rotationPeriod*1000.0))*playSpeed);
		}));
		rotation.setCycleCount(Timeline.INDEFINITE);
		rotation.play();
	}
	
	public void playAll() {
		timer.play();
		revolution.play();
		rotation.play();
	}
	
	public void stopAll() {
		timer.pause();
		revolution.pause();
		rotation.pause();
	}
	
	public void changePlaySpeed(double newPlaySpeed) {
		this.playSpeed = newPlaySpeed;
	}
	
	public void playMoonRevolution() {
		moonSphere.setRotationAxis(new Point3D(0, Math.sqrt(2)*Math.cos(Math.toRadians(90)), Math.sqrt(2)*Math.sin(Math.toRadians(90))));
		moonRevolution = new Timeline(new KeyFrame(Duration.millis(1), (e)->{
			moonSphere.setLayoutX(planetSphere.getLayoutX() + 40*Math.cos(-1*(elapsedTime*(2*(Math.PI/(27*1000))) )));
			moonSphere.setLayoutY(planetSphere.getLayoutY() + 40*Math.sin(-1*(elapsedTime*(2*(Math.PI/(27*1000))) )));

		}));
		moonRevolution.setCycleCount(Timeline.INDEFINITE);
		moonRevolution.play();
	}
	
	public void playMoonRotation() {
		moonRotation = new Timeline(new KeyFrame(Duration.millis(1), (e)->{
			moonSphere.setRotate(moonSphere.getRotate() - (360.0/(27*1000.0))*playSpeed);
		}));
		moonRotation.setCycleCount(Timeline.INDEFINITE);
		moonRotation.play();
	}
	
	
	private void startTimer() {
		timer = new Timeline(new KeyFrame(Duration.millis(1), (e)->{
			planetName.setLayoutX(planetSphere.getLayoutX() - 10);
			planetName.setLayoutY(planetSphere.getLayoutY() - radius - 13);
			moonName.setLayoutX(moonSphere.getLayoutX() - 10);
			moonName.setLayoutY(moonSphere.getLayoutY() - 5 - 13);
			elapsedTime += 1*playSpeed;
		}));
		timer.setCycleCount(Timeline.INDEFINITE);
		timer.play();
	}
	
	private void setMouseHover() {
		planetSphere.setOnMouseEntered(e->{
			if(!planetName.isVisible()) {
				planetName.setVisible(true);
			}
		});
		
		planetSphere.setOnMouseExited(e->{
			if(planetName.isVisible()) {
				planetName.setVisible(false);
			}
		});
		
		moonSphere.setOnMouseEntered(e->{
			if(!moonName.isVisible()) {
				moonName.setVisible(true);
			}
		});
		
		moonSphere.setOnMouseExited(e->{
			if(moonName.isVisible()) {
				moonName.setVisible(false);
			}
		});
		
	}
	
	private void setMouseClicked() {
		planetSphere.setOnMouseClicked((e) -> {
			if(!start_drawing) {
				start_drawing = true;
				prev_x = planetSphere.getLayoutX();
				prev_y = planetSphere.getLayoutY();
//				start_x = planetSphere.getLayoutX();
//				start_y = planetSphere.getLayoutY();
			}
			else {
				orbitLinePane.getChildren().clear();
				
				start_drawing = false;
			}
//			System.out.println(planetName + "clicked");
		});
	}
	
	private void drawOrbit(double old_x, double old_y, double new_x, double new_y) {
		Line line = new Line(old_x, old_y, new_x, new_y);
		line.setStroke(Color.WHITE);
		orbitLinePane.getChildren().add(line);
//		if(elapsedTime > 5000) {
//			List<Node> newList = orbitLinePane.getChildren().subList(2, orbitLinePane.getChildren().size()-1);
//			orbitLinePane.getChildren().clear();
//			orbitLinePane.getChildren().addAll(newList);
//		}

	}

	
	
}
