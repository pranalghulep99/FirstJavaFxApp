package com.internshala.translation_animation;

import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

import java.net.URL;
import java.util.ResourceBundle;

public class TranslationController implements Initializable {
	@FXML
	public Pane myPane;
	@FXML
	public Button animationButton;

	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {
		Circle circle=new Circle();
		circle.setRadius(50);
		circle.setFill(Color.RED);
		circle.setTranslateX(70);
		circle.setTranslateY(70);
		myPane.getChildren().add(circle);

		animationButton.setOnAction(actionEvent -> {
			startAnimation(circle);
		});
	}

	private void startAnimation(Circle circle) {
		TranslateTransition translateTransition=new TranslateTransition();
		translateTransition.setDuration(Duration.seconds(1));
		translateTransition.setNode(circle);
//		translateTransition.setToX(400);
		translateTransition.setToY(200);
//		translateTransition.setToZ(50);
		translateTransition.play();
	}
}
