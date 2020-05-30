package main;

import java.util.ArrayList;

import javafx.animation.Interpolator;
import javafx.animation.PathTransition;
import javafx.animation.Timeline;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Ellipse;
import javafx.util.Duration;

public class Planet {
	
	public String name;
	public Circle planet = new Circle();
	public Ellipse planetOrbit = new Ellipse();
	public PathTransition transitionPlanet = new PathTransition();
	public int index;
	
	// constructor
	public Planet(int index, String name, Color planet_color, int planetRadius, double period, int centerX, int centerY, int radiusX, int radiusY) {
		
		this.index = index;
		// planet
		this.name = name;
		planet.setRadius(planetRadius);
		planet.setFill(planet_color);
		
		// orbit path
		planetOrbit.setRadiusX(radiusX);
		planetOrbit.setRadiusY(radiusY);
		planetOrbit.setCenterX(centerX);
		planetOrbit.setCenterY(centerY);
		
		// orbit animation
		transitionPlanet.setPath(planetOrbit);
		transitionPlanet.setNode(planet);
		transitionPlanet.setInterpolator(Interpolator.LINEAR);	// �ʵe�ɶ��覡
		transitionPlanet.setDuration(Duration.seconds(period));	// revolution period
		transitionPlanet.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);	// �����D�O�F����
		transitionPlanet.setCycleCount(Timeline.INDEFINITE);	// �����D�O�F����2
		
		
	}
	
	public void addNode(Pane pane2d) {
		pane2d.getChildren().add(this.planet);
	}
	
	public void playOrbit() {
		transitionPlanet.play();
	}
	
	public void pauseOrbit() {
		transitionPlanet.pause();
	}
	
	public void setMouse(ArrayList<VBox> vvv) {
		EventHandler<MouseEvent> click = new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				if(vvv.get(index).isVisible() == true) {
					vvv.get(index).setVisible(false);
					
				}
				else {
					for(var a : vvv) {
						a.setVisible(false);
					}
					vvv.get(index).setVisible(true);
				}
			}
			
		};
		this.planet.setOnMouseClicked(click);
	}
}
