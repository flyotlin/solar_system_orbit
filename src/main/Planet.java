package main;

import javafx.animation.Interpolator;
import javafx.animation.PathTransition;
import javafx.animation.Timeline;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Ellipse;
import javafx.util.Duration;

public class Planet {
	
	public String name;
	public Circle planet = new Circle();
	public Ellipse planetOrbit = new Ellipse();
	public PathTransition transitionPlanet = new PathTransition();
	
	// constructor
	public Planet(String name, int planetRadius, double period, int centerX, int centerY, int radiusX, int radiusY) {
		
		// planet
		this.name = name;
		planet.setRadius(planetRadius);
		
		// orbit path
//		planet.setCenterX(centerX);
//		planet.setCenterY(centerY);
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
	
	public void playOrbit() {
		transitionPlanet.play();
	}
}
