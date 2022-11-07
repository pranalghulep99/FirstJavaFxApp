package com.internshala.demoproject;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.Optional;

// Ctrl  Shift -  (to minimize method implementation)
// Ctrl Shift + (To Expand  method implementation)
public class MyMain extends Application {
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage  primaryStage) throws Exception {
		FXMLLoader loader = new
				FXMLLoader(getClass().getResource("app_layout.fxml"));

//		rootNode
		VBox rootNode = loader.load();

//	5	Calling createMenu that will return MenuBar and Storing this MenuBar in Variable
		MenuBar menuBar=createMenu();

//	6	Adding this MenuBar to our VBox
		rootNode.getChildren().addAll(menuBar);

		Scene scene = new Scene(rootNode, 300, 275);

		primaryStage.setScene(scene);
		primaryStage.setTitle("Hello World");
		primaryStage.show();
	}

	private static MenuBar createMenu(){
//	1	File Menu with name
		Menu fileMenu=new Menu("File");
//	7   Now Defining as many MenuItems to File Menu
		MenuItem newMenuItem=new MenuItem("New");
//	8   EventHandler ====Providing on click action (setOnAction) to menuItems
//		As parameter creating new EventHandler that will override Handle method


//		newMenuItem.setOnAction(new EventHandler<ActionEvent>() {
//			@Override
//			public void handle(ActionEvent actionEvent) {
//				System.out.println("New Item");
//			}
//		});
//		Converting our setOnAction to Lambda expression
		newMenuItem.setOnAction(actionEvent -> {
			System.out.println("New Item");
		});

//		we can specify separator line between two menuItems in the Menu as like
		SeparatorMenuItem separatorMenuItem=new SeparatorMenuItem();


		MenuItem quitMenuItem=new MenuItem("Quit");
		quitMenuItem.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent actionEvent) {
				Platform.exit(); //just signals the JavaFX Toolkit to shut down, so the application instance stop().

				System.exit(0); // terminates the JVM, stopping everything immediately.
			}
		});
//		Lambda Expression of quitMenu setOnAction
//		quitMenuItem.setOnAction(actionEvent -> {
//			Platform.exit();
//			System.exit(0);
//		});



//	8   Adding this MenuItem to Our Menu
		fileMenu.getItems().addAll(newMenuItem, separatorMenuItem,quitMenuItem);


//	2	Help menu with name
		Menu helpMenu= new Menu("Help");
//		Same As Above
		MenuItem aboutMenuItem=new MenuItem("About");
//		aboutMenuItem.setOnAction(new EventHandler<ActionEvent>() {
//			@Override
//			public void handle(ActionEvent actionEvent) {
//				aboutApp();
//			}
//		});
//		Lambda Expression
		aboutMenuItem.setOnAction(actionEvent -> {
			aboutApp();
		});
		
		helpMenu.getItems().addAll(aboutMenuItem);

//	3	Adding above menu to MenuBar
		MenuBar menuBar=new MenuBar();
		menuBar.getMenus().addAll(fileMenu, helpMenu);

//	4	returning this MenuBar to VBox in start Method
		return menuBar;
	}

//9
	private static void aboutApp() {
		Alert alertDialogue=new Alert(Alert.AlertType.INFORMATION);
		alertDialogue.setTitle("My First Desktop App");
		alertDialogue.setHeaderText("Learning JavaFX");
		alertDialogue.setContentText("I am just a beginner and learning JavaFX, in some days I will able to create java games and application");
		ButtonType yesButton=new ButtonType("Yes");
		ButtonType noButton=new ButtonType("No");
		alertDialogue.getButtonTypes().setAll(yesButton, noButton);
//		alertDialogue.show(); // This show method only display the alertDialogue to user
//		so to take clicked action in Yes or No button we have to use showAndWait() method
		Optional<ButtonType> clickedButton=alertDialogue.showAndWait();
		if (clickedButton.isPresent() && clickedButton.get() == yesButton){
			System.out.println("Yes Button Clicked");
		}else{
			System.out.println("No Button Clicked");
		}
	}
}