package main;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Calendar;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Point3D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;

public class Orbit3D {
	private final int WIDTH = 1280;
	private final int HEIGHT= 800;
	private double playSpeed = 1;
	private boolean is_playing = true;
	private int rotateAngle = 0;
	SolarPlanet[] planets = new SolarPlanet[9];
	Pane pane = new Pane();
	Scene scene = new Scene(pane, WIDTH, HEIGHT);
	Pane planetGroup = new Pane();
	Label speedLabel = new Label("x1");
	Label timeLabel = new Label();
	PerspectiveCamera camera = new PerspectiveCamera();
	Timeline timer = new Timeline();
	double year, month, day;
	double[][] planetRadius = {{80,8,12,30,7,20,15,15,13}, {109,0.38,0.94,1,0.53,11.2,9.45,4,3.83}};

	
	public Orbit3D() {
		
		prepareDateLabel();
		pane.setStyle("-fx-background-image: url(\"/resources/planets/stars.jpg\")");
		planetGroup.getTransforms().add(new Rotate(0,Rotate.X_AXIS));
		
		prepareOrbit();
		prepareSpeedLabel();
		prepareSpeedSlider();
		prepareButton();
		prepareMouse();
		prepareKey();
		setTimer();
//		camera.setFarClip(10);
		scene.setCamera(camera);
	}
	
	public void setScene() {
		Main.currentStage.setScene(scene);
	}
	
	private void prepareOrbit() {
		addNodeToPane(planetGroup);
		
		planets[0] = new SolarPlanet("Sun", WIDTH/2, HEIGHT/2, planetRadius[Main.real][0], "/resources/planets/sun.jpg");
		planets[1] = new SolarPlanet("Mercury", WIDTH/2, HEIGHT/2, planetRadius[Main.real][1], "/resources/planets/mercury.jpg");
		planets[2] = new SolarPlanet("Venus", WIDTH/2, HEIGHT/2, planetRadius[Main.real][2], "/resources/planets/venus.jpg");
		planets[3] = new SolarPlanet("Earth", WIDTH/2, HEIGHT/2, planetRadius[Main.real][3], "/resources/planets/earth.jpg");
		planets[4] = new SolarPlanet("Mars", WIDTH/2, HEIGHT/2, planetRadius[Main.real][4], "/resources/planets/mars.jpg");
		planets[5] = new SolarPlanet("Jupiter", WIDTH/2, HEIGHT/2, planetRadius[Main.real][5], "/resources/planets/jupiter.jpg");
		planets[6] = new SolarPlanet("Saturn", WIDTH/2, HEIGHT/2, planetRadius[Main.real][6], "/resources/planets/saturn.jpg");
		planets[7] = new SolarPlanet("Uranus", WIDTH/2, HEIGHT/2, planetRadius[Main.real][7], "/resources/planets/uranus.jpg");
		planets[8] = new SolarPlanet("Neptune", WIDTH/2, HEIGHT/2, planetRadius[Main.real][8], "/resources/planets/neptune.jpg");

		
		for(var a : planets) {
			planetGroup.getChildren().add(a.getPlanetSphere());
			addNodeToPane(a.planetName);			
		}

		for(var a : planets) {
			pane.getChildren().add(a.orbitLinePane);
		}
//		System.out.println(pane.getChildren() + "\n" + pane.getChildren().size());
		
		planetGroup.getChildren().add(planets[3].getMoonSphere());
		addNodeToPane(planets[3].moonName);

		planets[0].playRotation(25);
		planets[1].playRotation(59);
		planets[2].playRotation(243);
		planets[3].playRotation(1);
		planets[4].playRotation(1.03);
		planets[5].playRotation(10.0/24);
		planets[6].playRotation(10.66/24);
		planets[7].playRotation(17.23/24);
		planets[8].playRotation(16.01/24);
		
		
		planets[1].playRevolution(150, 150, 88, 0);
		planets[2].playRevolution(200, 200, 243, 60);
		planets[3].playRevolution(250, 250, 365, 180);
		planets[4].playRevolution(300, 300, 686, 0);
		planets[5].playRevolution(350, 350, 4343, 90);
		planets[6].playRevolution(400, 400, 10585, 256);
		planets[7].playRevolution(450, 450, 30660, 335);
		planets[8].playRevolution(500, 500, 60225, 45);

		
		planets[3].playMoonRotation();
		planets[3].playMoonRevolution();
	}
	
	private void addNodeToPane(Node node) {
		pane.getChildren().add(node);
	}
	
	private void prepareSpeedLabel() {
		speedLabel.setTextFill(Color.WHITE);
		speedLabel.setFont(new Font("Lucida Console", 20));
		speedLabel.setLayoutX(WIDTH - 450);
		speedLabel.setLayoutY(HEIGHT - 100);
		addNodeToPane(speedLabel);
	}
	
	private void prepareSpeedSlider() {
		Slider slider = new Slider();
		
		slider.valueProperty().addListener((e)->{
			playSpeed = slider.getValue();
			DecimalFormat df = new DecimalFormat("##.00");
			double ps = Double.parseDouble(df.format(playSpeed));
			if(ps >= 0) {
				speedLabel.setText("+" + ps*1 + " Day(s) per sec");				
			}
			else {
				speedLabel.setText(ps*1 + " Day(s) per sec");
			}
			for(var a : planets) {
				a.changePlaySpeed(playSpeed);
			}
		});
		
		slider.setMin(-5);
		slider.setMax(365);
		slider.setValue(1);
		slider.setShowTickLabels(true);
		slider.setShowTickMarks(true);
		slider.setMinorTickCount(5);
		slider.setPrefWidth(300d);
		slider.setLayoutX(WIDTH/2 - slider.getPrefWidth()/2);
		slider.setLayoutY(HEIGHT - 100);
		slider.setStyle("-fx-base: white; -fx-text-fill: white;");
		addNodeToPane(slider);
	}
	
	private void prepareButton() {
		Button playButton = new Button("Play");
		Button pauseButton = new Button("Pause");
		Button backButton = new Button("Back");
		Button clearButton = new Button("Clear");
		
		playButton.setPrefWidth(50);
		playButton.setPrefHeight(30);
		pauseButton.setPrefWidth(50);
		pauseButton.setPrefHeight(30);
		backButton.setPrefWidth(50);
		backButton.setPrefHeight(30);
		clearButton.setPrefWidth(50);
		clearButton.setPrefHeight(30);
		
		
		playButton.setLayoutX(WIDTH/2 - 450);
		playButton.setLayoutY(HEIGHT - 100);
		pauseButton.setLayoutX(WIDTH/2 - 350);
		pauseButton.setLayoutY(HEIGHT - 100);
		backButton.setLayoutX(0-10);
		backButton.setLayoutY(HEIGHT - 50);
		clearButton.setLayoutX(HEIGHT - 400);
		clearButton.setLayoutY(HEIGHT - 50);

		
		addNodeToPane(playButton);
		addNodeToPane(pauseButton);
		addNodeToPane(backButton);
		addNodeToPane(clearButton);

		
		playButton.setOnMouseClicked((e)->{
			if(!is_playing) {
				for(int i = 1; i < 9; i++) {
					planets[i].playAll();
				}
				planets[0].playAll();
				is_playing = true;
				timer.play();
			}
		});
		
		pauseButton.setOnMouseClicked((e)->{
			if(is_playing) {
				for(var a : planets) {
					a.stopAll();
				}
				is_playing = false;
				timer.pause();
			}
		});
		
		backButton.setOnMouseClicked((e)->{
			Parent root;
			try {
				root = FXMLLoader.load(getClass().getResource("mainScene.fxml"));
				Scene menuScene = new Scene(root);
				Main.currentStage.setScene(menuScene);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		});
		
		clearButton.setOnMouseClicked((e)->{
			for(var a : planets) {
				a.orbitLinePane.getChildren().clear();
				a.setIsDrawing(false);
			}
		});
		
		
		setButtonHover(playButton);
		setButtonHover(pauseButton);
		setButtonHover(backButton);
		setButtonHover(clearButton);

	}
	
	private void prepareMouse() {
		scene.setOnScroll((e)->{
			// Adjust the zoom factor as per your requirement
            double zoomFactor = 1.05;
            double deltaY = e.getDeltaY();
            System.out.println(deltaY);
            if(deltaY > 0) {
            	planetGroup.setTranslateZ(planetGroup.getTranslateZ() - deltaY);
            }
            else {
            	planetGroup.setTranslateZ(planetGroup.getTranslateZ() - deltaY);
            }
		});
	}
	
	private void prepareKey() {
		scene.setOnKeyPressed((e)->{
			
			switch(e.getCode()) {
			case A:
				
//				planetGroup.setRotationAxis(Rotate.Z_AXIS);
//				planetGroup.setRotate(planetGroup.getRotate() + 10);
				break;
			case D:
//				planetGroup.setRotationAxis(Rotate.Z_AXIS);
//				planetGroup.setRotate(planetGroup.getRotate() - 10);
				break;
			case W:
//				planetGroup.getTransforms().set(0, new Rotate(rotateAngle - 10, Rotate.X_AXIS));
//				rotateAngle -= 10;
//				planetGroup.setRotationAxis(Rotate.X_AXIS);
				planetGroup.setRotationAxis(new Point3D(1, 0, 0));
				planetGroup.setRotate(planetGroup.getRotate() - 10);
				break;
			case S:
//				planetGroup.getTransforms().set(0, new Rotate(rotateAngle + 10, Rotate.X_AXIS));
//				rotateAngle += 10;
//				planetGroup.setRotationAxis(Rotate.X_AXIS);
				planetGroup.setRotationAxis(new Point3D(1, 0, 0));
				planetGroup.setRotate(planetGroup.getRotate() + 10);
				break;
			case Q:
//				camera.setTranslateZ(camera.getTranslateZ() - 10);
				planetGroup.setTranslateZ(planetGroup.getTranslateZ() - 10);
				break;
			case E:
//				camera.setTranslateZ(camera.getTranslateZ() + 10);
				planetGroup.setTranslateZ(planetGroup.getTranslateZ() + 10);
				break;
			default:
				break;
			}
			
			
		});
	}
	

	private void setButtonHover(Button btn) {
		btn.setStyle("-fx-background-color: transparent; -fx-padding: 10px, 10px; -fx-text-fill: white; -fx-pref-width: 150px; -fx-font-family: 'Lucida Console'; -fx-font-size: 25px;");
		btn.setOnMouseEntered(e->{
			btn.setLayoutY(btn.getLayoutY() + 3);
			btn.setStyle("-fx-underline: true; -fx-background-color: transparent; -fx-padding: 10px, 10px; -fx-text-fill: white; -fx-pref-width: 150px; -fx-font-family: 'Lucida Console'; -fx-font-size: 25px;");
		});
		
		
		btn.setOnMouseExited(e->{
			btn.setLayoutY(btn.getLayoutY() - 3);
			btn.setStyle("-fx-underline: false; -fx-background-color: transparent; -fx-padding: 10px, 10px; -fx-text-fill: white; -fx-pref-width: 150px; -fx-font-family: 'Lucida Console'; -fx-font-size: 25px;");
		});
	}
	
	private void prepareDateLabel() {
		Calendar timeNow = Calendar.getInstance();
		year = timeNow.get(Calendar.YEAR);
		month = timeNow.get(Calendar.MONTH) + 1;
		day = timeNow.get(Calendar.DATE);
		
		timeLabel.setText(Math.round(year) + "年" + Math.round(month) + "月" + Math.round(day) + "日");
		timeLabel.setTextFill(Color.WHITE);
		timeLabel.setFont(new Font("Lucida Console", 25));
		timeLabel.setLayoutX(WIDTH - 450);
		timeLabel.setLayoutY(HEIGHT - 70);
		
		addNodeToPane(timeLabel);
	}
	
	private void setTimer() {
		timer = new Timeline(new KeyFrame(Duration.seconds(1), (e)->{
			day += 1*playSpeed;
			dateCarry();
			
			timeLabel.setText(Math.round(year) + "年" + Math.round(month) + "月" + Math.round(day) + "日");
		}));
		timer.setCycleCount(Timeline.INDEFINITE);
		timer.play();
	}
	
	private void dateCarry() {
		if(day > 30) {
			month += (day/30);
			day = (day%30 );
		}
		if(month > 12) {
			year += (month/12);
			month = (month%12 + 1);
		}
	}


}
