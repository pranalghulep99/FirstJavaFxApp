package com.internshala.TempConvetTool;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ResourceBundle;

public class TempController implements Initializable {
	@FXML
	public Label welcomeLabel;
	@FXML
	public ChoiceBox<String> choiceBox;
	@FXML
	public TextField tempTextField;
	@FXML
	public Button convertButton;
	private static final String Cel_to_F="Celsius to Fahrenheit";
	private static final String F_to_Cel="Fahrenheit to Celsius";
	private static boolean is_C_to_F=true;
	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {
		choiceBox.getItems().add(Cel_to_F);
		choiceBox.getItems().add(F_to_Cel);
		choiceBox.setValue(Cel_to_F);
		choiceBox.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observableValue, String oldValue, String newValue) {
				System.out.println(newValue);
				if (newValue==Cel_to_F){
					is_C_to_F=true;
				}else{
					is_C_to_F=false;
				}
			}
		});
		convertButton.setOnAction(actionEvent -> {
			convert();
		});
	}

	private void convert() {
		String userEnteredText=tempTextField.getText();
		double userTemp=0.0;
		try {
			userTemp = Double.parseDouble(userEnteredText);
		}catch (Exception e){
			invalidInput();
			return;
		}
		double newTemp=0.0;
		if (is_C_to_F){
			newTemp=(userTemp*9/5)+32;
		}else{
			newTemp=(userTemp-32)*5/9;
		}
		display(newTemp);
	}

	private void invalidInput() {
		Alert invalidInputAlert=new Alert(Alert.AlertType.ERROR);
		invalidInputAlert.setTitle("ERROR OCCURRED!");
		invalidInputAlert.setContentText("!!Please Enter Valid Temperature As Input!!");
		invalidInputAlert.show();
	}

	private void display(double newTemp) {
		String unit=is_C_to_F?"F":"C";
		System.out.println("New Temperature is : "+newTemp+" "+unit);
		Alert alert=new Alert(Alert.AlertType.INFORMATION);
		alert.setTitle("Result");
		alert.setContentText("New Temperature is : "+newTemp+" "+unit);
		alert.show();
	}
}
