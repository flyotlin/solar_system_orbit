package main;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;

public class MainController {
	@FXML
	Button twoD;
	
	@FXML
	Button threeD;
	
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
	public void onExitPressed() {
		Main.currentStage.close();
	}
}
