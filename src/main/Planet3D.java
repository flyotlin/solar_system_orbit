package main;

import javafx.animation.Animation;
import javafx.animation.Interpolator;
import javafx.animation.RotateTransition;
import javafx.scene.image.Image;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Sphere;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;

public class Planet3D {
	public Sphere planet = new Sphere();
	private PhongMaterial planetMaterial = new PhongMaterial();
	private RotateTransition rotation = new RotateTransition();
	private double[] magnification = {0.6, 0.8, 1, 1.2, 1.6, 20, 30, 50, 100, 150, 200, 225, 250};
	private int magnificationNow = 1;
	private double radius;
	
	public Planet3D(double radius) {
		this.radius = radius;
		planet.setRadius(radius);
		setRotation();
	}
	
	public void setRadius(double radius) {
		this.radius = radius;
		planet.setRadius(radius);
	}
	
	public void setMaterial(String materialURL) {
		planetMaterial.setDiffuseMap(new Image(getClass().getResourceAsStream(materialURL)));
		planet.setMaterial(planetMaterial);
	}
	
	public Sphere getPlanet() {
		return planet;
	}
	
	public void setPosition(double x, double y) {
		planet.setTranslateX(x);
		planet.setTranslateY(y);
	}
	
	public void setPlanetTranslateZ(double delta) {
		if(delta == 1) {
			if(magnificationNow != magnification.length-1)
				magnificationNow += 1;
			planet.setRadius(radius * magnification[magnificationNow]);
		}
		else {
			if(magnificationNow != 0)
				magnificationNow -= 1;
			planet.setRadius(radius * magnification[magnificationNow]);
		}
			
	}
	
	private void setRotation() {
		rotation.setNode(planet);
		rotation.setDuration(Duration.seconds(15));
		rotation.setAxis(Rotate.Y_AXIS);
		rotation.setFromAngle(0);
		rotation.setToAngle(360);
		rotation.setInterpolator(Interpolator.LINEAR);
		rotation.setCycleCount(Animation.INDEFINITE);
		rotation.play();
	}
	
	
	
}
