package com.internshala.translation_animation;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class TranslationMain extends Application {
	@Override
	public void start(Stage stage) throws Exception {
		FXMLLoader fxmlLoader=new FXMLLoader(getClass().getResource("Translation.fxml"));
		VBox rootNode=fxmlLoader.load();

		Scene scene=new Scene(rootNode);
		stage.setScene(scene);
		stage.setTitle("Translation Animation");
		stage.show();
	}
}
