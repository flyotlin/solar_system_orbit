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
		transitionPlanet.setInterpolator(Interpolator.LINEAR);	// �ʵe�ɶ��覡
		transitionPlanet.setDuration(Duration.seconds(period));	// revolution period
		transitionPlanet.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);	// �����D�O�F����
		transitionPlanet.setCycleCount(Timeline.INDEFINITE);	// �����D�O�F����2
		
		
		
	}
	
	public void playOrbit() {
		transitionPlanet.play();
	}
}
