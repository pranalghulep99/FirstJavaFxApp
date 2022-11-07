package com.internshala.TempConvetTool;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.awt.*;
import java.util.Optional;

public class MyMain extends Application {
//1
	public static void main(String[] args) {
		launch(args);
	}

//2
	@Override
	public void init() throws Exception {
		super.init();
	}

//	3
	@Override
	public void start(Stage stage) throws Exception {
		FXMLLoader loader=new FXMLLoader(getClass().getResource("TempConvertTool.fxml"));
		VBox rootNode=loader.load();

		MenuBar menuBar=createMenu();

		rootNode.getChildren().add(0, menuBar);

		Scene scene=new Scene(rootNode);

		stage.setScene(scene);
		stage.setTitle("Temperature Tool");
		stage.show();
	}

	private static MenuBar createMenu() {
		MenuBar menuBar=new MenuBar();

		Menu fileMenu=new Menu("File");
		MenuItem newItem=new MenuItem("New");
		newItem.setOnAction(actionEvent -> {
			System.out.println("Creating new File");
		});
		SeparatorMenuItem separatorMenuItem=new SeparatorMenuItem();
		MenuItem quitItem=new MenuItem("Quit");
		quitItem.setOnAction(actionEvent -> {
			System.out.println("we are quiting the Application");
			Platform.exit();
			System.exit(0);
		});
		fileMenu.getItems().addAll(newItem, separatorMenuItem , quitItem);

		Menu helpMenu=new Menu("Help");
		MenuItem aboutItem =new MenuItem("About");
		aboutItem.setOnAction(actionEvent -> {
			aboutApp();
		});
		helpMenu.getItems().addAll(aboutItem);

		menuBar.getMenus().addAll(fileMenu, helpMenu);

		return menuBar;
	}

	private static void aboutApp() {
		Alert alertDialogue=new Alert(Alert.AlertType.INFORMATION);
		alertDialogue.setTitle("My Second Desktop App");
		alertDialogue.setHeaderText("Learning JavaFX");
		alertDialogue.setContentText("Currently I am learning the JavaFX as beginner, " +
				"but after some time I will able to create java Application");
		ButtonType yesButton=new ButtonType("Yes");
		ButtonType noButton=new ButtonType("No");
		alertDialogue.getButtonTypes().setAll(yesButton, noButton);
//		alertDialogue.getButtonTypes().setAll(yesButton, noButton);
		Optional<ButtonType> clickedButton=alertDialogue.showAndWait();
		if (clickedButton.isPresent() && clickedButton.get()==yesButton){
			System.out.println("Yes Button Clicked");
		}else {
			System.out.println("No Button Clicked");
		}

	}

	//	Last
	@Override
	public void stop() throws Exception {
		super.stop();
	}
}
