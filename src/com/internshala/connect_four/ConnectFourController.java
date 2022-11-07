package com.internshala.connect_four;

import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Point2D;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.util.Duration;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ConnectFourController implements Initializable {
	private static  final int Column=7;
	private static  final int Row=6;
	private static  final int circleDiameter=80;
	private static  final String discOneColour="#b30059";
	private static  final String discTwoColour="#009900";
	private static String playerOne="Player One";
	private static String playerTwo="Player Two";
	private static boolean isPlayerOneTurn=true;
	private Disc[][] insertedDiscArray=new Disc[Row][Column];

	private boolean isAllowedToInsertDisc=true;
	@FXML
	public GridPane gridPane;
	@FXML
	public Pane menuPane;
	@FXML
	public Pane insertDiscPane;
	@FXML
	public VBox playerVBox;
	@FXML
	public TextField player1TextField;
	@FXML
	public TextField player2TextField;
	@FXML
	public Button setNameButton;
	@FXML
	public Label playerLabel;
	@FXML
	public Label turnLabel;

	public void createPlayground(){
		Shape rectangleWithHoles=createGameStructuralGrid();
		gridPane.add(rectangleWithHoles, 0, 1);
		ArrayList<Rectangle> rectangleArrayList=createRectangle();
		for (Rectangle rectangle:rectangleArrayList) {
			gridPane.add(rectangle, 0, 1);
		}
		setNameButton.setOnAction(actionEvent -> {
			setButton();
		});
	}

	private void setButton() {
		playerOne=player1TextField.getText();
		playerTwo=player2TextField.getText();
		playerLabel.setText(playerOne+"'s");
	}

	private Shape createGameStructuralGrid() {
		Shape rectangleWithHoles=new Rectangle((Column+1)*circleDiameter, (Row+1)*circleDiameter);

		for (int row=0; row<Row; row++){
			for (int col=0; col<Column; col++){
				Circle circle=new Circle();
				circle.setRadius(circleDiameter/2);
				circle.setCenterX(circleDiameter/2);
				circle.setCenterY(circleDiameter/2);
				circle.setSmooth(true);
				circle.setTranslateX(col*(circleDiameter+5)+ circleDiameter/4);
				circle.setTranslateY(row*(circleDiameter+5) + circleDiameter/4);
				rectangleWithHoles=Shape.subtract(rectangleWithHoles, circle);
			}
		}
		rectangleWithHoles.setFill(Color.WHITE);
		return rectangleWithHoles;
	}
	public ArrayList<Rectangle> createRectangle(){
		ArrayList<Rectangle> rectangleArrayList=new ArrayList<Rectangle>();
		for(int col=0; col<Column; col++){
			Rectangle rectangle=new Rectangle(circleDiameter, (Row+1)*circleDiameter);
			rectangle.setFill(Color.TRANSPARENT);
			rectangle.setTranslateX(col*(circleDiameter+5)+circleDiameter/4);
			rectangle.setOnMouseEntered(actionEvent ->{rectangle.setFill(Color.valueOf("#eeeeee22"));});
			rectangle.setOnMouseExited(mouseEvent -> {rectangle.setFill(Color.TRANSPARENT);});
			final int column=col;
			rectangle.setOnMouseClicked(mouseEvent -> {
				if (isAllowedToInsertDisc) {
					isAllowedToInsertDisc=false;
					insertDisc(new Disc(isPlayerOneTurn), column);
				}
			});
			rectangleArrayList.add(rectangle);
		}
		return rectangleArrayList;
	}

	private void insertDisc(Disc disc, int column) {
		int row=Row-1;
		while (row>=0){
			if (getDiscIfPresent(row, column)==null){
				break;
			}else{
				row--;
			}
		}
		if (row<0){
			return;
		}
		insertedDiscArray[row][column]=disc;
		insertDiscPane.getChildren().add(disc);
		disc.setTranslateX(column *(circleDiameter+5)+ circleDiameter/4);
//		disc.setTranslateY(5 * (circleDiameter+5) + circleDiameter/4);
		TranslateTransition translateTransition=new TranslateTransition(Duration.seconds(0.5), disc);
		translateTransition.setToY(row * (circleDiameter+5) + circleDiameter/4);
		final int currentRow=row;
		translateTransition.setOnFinished(actionEvent -> {
			isAllowedToInsertDisc=true;
			if(gameEnded(currentRow, column)){
				gameOver();
				return;
			}
			isPlayerOneTurn = !isPlayerOneTurn;
			String name1=playerOne+"'s";
			String name2=playerTwo+"'s";
			playerLabel.setText(isPlayerOneTurn?name1:name2);
		});
		translateTransition.play();
	}

	private void gameOver() {
		String winner=isPlayerOneTurn?playerOne:playerTwo;
		System.out.println("Winner is : "+ winner);
		Alert winnerAlert=new Alert(Alert.AlertType.INFORMATION);
		winnerAlert.setTitle("!!Connect Four Result!!");
		winnerAlert.setHeaderText("Congratulation Winner is % "+winner+" %");
		winnerAlert.setContentText("Do You Want to Play Again ?");
		ButtonType yesButton=new ButtonType("Yes");
//		yesButton.setOnAction(actionEvent -> {resetGame();});
		ButtonType noButton=new ButtonType("No");
//		noButton.setOnAction(actionEvent -> {
//			Platform.exit();
//			System.exit(0);
//		});
		winnerAlert.getButtonTypes().setAll(yesButton, noButton);
		Platform.runLater(()->{
			Optional<ButtonType> clickedButton=winnerAlert.showAndWait(); //ShowAndWait Method Does not allow user to
			// Run the Code of showing information alert while inserting a disc object
			// It csn be shown after current disc get inserted Completely
//			Platform.runLater(); helps us to show alert after disc object get placed

			if (clickedButton.isPresent() && clickedButton.get()==yesButton){
				resetGame();
			}else {
				Platform.exit();
				System.exit(0);
			}
		});

	}

	public void resetGame() {
		insertDiscPane.getChildren().clear();  // clearing all the children of insertDiscPane

		for (int row=0; row< insertedDiscArray.length; row++){ // Making array Null
			for (int col=0; col< insertedDiscArray[row].length; col++){
				insertedDiscArray[row][col]=null;
			}
		}
		isPlayerOneTurn=true; // now again setting up playerOne Turn
		playerLabel.setText(playerOne); // label
		createPlayground();// calling it to create playground Again
	}

	private boolean gameEnded(int currentRow, int column) {
//		we have to check Four Combinations :
		List<Point2D> verticalPoints= IntStream.rangeClosed(currentRow-3, currentRow+3)
				.mapToObj(row -> new Point2D(row, column))
				.collect(Collectors.toList());

		List<Point2D> horizontalPoints=IntStream.rangeClosed(column-3, column+3)
				.mapToObj(col -> new Point2D(currentRow, col))
				.collect(Collectors.toList());

		Point2D startPoint1=new Point2D(currentRow-3, column+3);
		List<Point2D> diagonal1Points=IntStream.rangeClosed(0, 6)
				.mapToObj(i -> startPoint1.add(i, -i))
				.collect(Collectors.toList());

		Point2D startPoint2=new Point2D(currentRow-3, column-3);
		List<Point2D> diagonal2Points=IntStream.rangeClosed(0, 6)
				.mapToObj(i -> startPoint2.add(i, i))
				.collect(Collectors.toList());

		boolean isEnded= checkCombination(verticalPoints) || checkCombination(horizontalPoints)
				|| checkCombination(diagonal1Points) || checkCombination(diagonal2Points);
		return isEnded;
	}

	private boolean checkCombination(List<Point2D> points) {
		int chain=0;
		for (Point2D point:points) {
			int rowIndexForArray= (int) point.getX();
			int colIndexForArray=(int) point.getY();
			Disc disc=getDiscIfPresent(rowIndexForArray, colIndexForArray);
			if(disc != null && disc.isPlayerOneMove==isPlayerOneTurn){
				chain++;
				if (chain==4){
					return true;
				}
			}else {
				chain=0;
			}

		}
		return  false;
	}

	private Disc getDiscIfPresent(int rowIndexForArray, int colIndexForArray) {
		if (rowIndexForArray>=Row || rowIndexForArray <0 || colIndexForArray>=Column || colIndexForArray<0){
			return null;
		}else {
			return insertedDiscArray[rowIndexForArray][colIndexForArray];
		}
	}

	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {

	}

	private static class Disc extends  Circle{
		public final boolean isPlayerOneMove;

		public Disc(boolean isPlayerOneTurn) {
			this.isPlayerOneMove = isPlayerOneTurn;
			setRadius(circleDiameter/2);
			setFill(isPlayerOneTurn?Color.valueOf(discOneColour):Color.valueOf(discTwoColour));
			setCenterX(circleDiameter/2);
			setCenterY(circleDiameter/2);
		}
	}
}
