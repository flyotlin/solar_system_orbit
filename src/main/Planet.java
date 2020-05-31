package main;

import java.util.ArrayList;

import javafx.animation.Interpolator;
import javafx.animation.PathTransition;
import javafx.animation.Timeline;
import javafx.event.EventHandler;
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
	public double theta;
	public double period;
	public Pane pane2D;
	public boolean is_drawn = false;
	
	// constructor
	public Planet(int index, double theta, String name, Color planet_color, int planetRadius, double period, int centerX, int centerY, int radiusX, int radiusY) {
		
		this.index = index;
		this.theta = theta;
		this.period = period;
		
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
		transitionPlanet.setInterpolator(Interpolator.LINEAR);	// 動畫補間方式
		transitionPlanet.setDuration(Duration.seconds(period));	// revolution period
		transitionPlanet.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);	// 不知道是幹嘛的
		transitionPlanet.setCycleCount(Timeline.INDEFINITE);	// 不知道是幹嘛的2
		
	}
	
	public void addNode(Pane pane2d) {
		this.pane2D = pane2d;
		pane2d.getChildren().add(this.planet);
	}
	
	public void startOrbit() {
		transitionPlanet.playFrom(Duration.seconds(period * (theta/360)));		
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
				// show the info
				if(vvv.get(index).isVisible() == true) {
					vvv.get(index).setVisible(false);
					
				}
				else {
					for(var a : vvv) {
						a.setVisible(false);
					}
					vvv.get(index).setVisible(true);
				}
				 
				// draw
				if(index != 8) {	// not the sun
					if(is_drawn == false) {
						planetOrbit.setStroke(Color.WHITE);
						planetOrbit.setFill(Color.TRANSPARENT);					
						pane2D.getChildren().add(1, planetOrbit);
						is_drawn = true;
					}
					else {
						pane2D.getChildren().remove(planetOrbit);
						is_drawn = false;
					}					
				}
			}
			
		};
		this.planet.setOnMouseClicked(click);
	}
	
	
}
