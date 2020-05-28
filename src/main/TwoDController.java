package main;

public class TwoDController {

	public void startAnimation() {
		System.out.println("Animation starts!");
		Planet earth = new Planet("earth", 50, 6.0, 250, 250, 100, 100);
		earth.playOrbit();
	}
	
}
