package com.internshala.connect_four;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class ConnectFourMain extends Application {
	private ConnectFourController connectFourController;

	@Override
	public void start(Stage stage) throws Exception {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ConnectFour.fxml"));
		GridPane rootNode = fxmlLoader.load();
		connectFourController=fxmlLoader.getController();
		connectFourController.createPlayground();
		MenuBar menuBar = createMenuBar();
		menuBar.prefWidthProperty().bind(stage.widthProperty());
		Pane menuPane = (Pane) rootNode.getChildren().get(0);
		menuPane.getChildren().addAll(menuBar);
		Scene scene = new Scene(rootNode);
		stage.setScene(scene);
		stage.setTitle("Connect Four Game");
		stage.setResizable(false);
		stage.show();
	}

	private MenuBar createMenuBar() {
		MenuBar menuBar = new MenuBar();

		Menu fileMenu = new Menu("File");
		MenuItem newGame = new MenuItem("New Game");
		newGame.setOnAction(actionEvent -> connectFourController.resetGame());
		MenuItem resetGame = new MenuItem("Reset Game");
		resetGame.setOnAction(actionEvent -> connectFourController.resetGame());
		SeparatorMenuItem separatorMenuItem = new SeparatorMenuItem();
		MenuItem exitGame = new MenuItem("Exit Game");
		exitGame.setOnAction(actionEvent -> exitGame());
		fileMenu.getItems().addAll(newGame, resetGame, separatorMenuItem, exitGame);

		Menu helpMenu = new Menu("Help");
		MenuItem aboutGame = new MenuItem("About Game");
		aboutGame.setOnAction(actionEvent -> aboutGame());
		SeparatorMenuItem separatorMenuItem1 = new SeparatorMenuItem();
		MenuItem aboutDeveloper = new MenuItem("About Developer");
		aboutDeveloper.setOnAction(actionEvent -> aboutDeveloper());
		helpMenu.getItems().addAll(aboutGame, separatorMenuItem1, aboutDeveloper);

		menuBar.getMenus().addAll(fileMenu, helpMenu);
		return menuBar;
	}

	private void aboutDeveloper() {
		Alert dialogue = new Alert(Alert.AlertType.INFORMATION);
		dialogue.setTitle("About Developer");
		dialogue.setHeaderText("Deliger Pranal G.");
		dialogue.setContentText("I Love to play around with code and create game." +
				"Connect four is my first game that I am learning how to code and build game " +
				"using JavaFX");
		dialogue.show();
	}

	private void aboutGame() {
		Alert dialogue = new Alert(Alert.AlertType.INFORMATION);
		dialogue.setTitle("About Connect Four");
		dialogue.setHeaderText("How TO Play?");
		dialogue.setContentText("Connect Four is a two-player connection game in which " +
				"the players first choose " +
				"a color and then take turns dropping colored discs from the top into a " +
				"seven-column, six-row " +
				"vertically suspended grid. The pieces fall straight down, occupying the " +
				"next available space " +
				"within the column. The objective of the game is to be the first to form " +
				"a horizontal, vertical, " +
				"or diagonal line of four of one's own discs. Connect Four is a solved game." +
				" The first player " +
				"can always win by playing the right moves.");
		dialogue.show();
	}

	private void exitGame() {
		Platform.exit();
		System.exit(0);
	}

}
