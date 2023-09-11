import java.util.Optional;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;

public class SodukoController {

    @FXML
    private GridPane grid;
    private final int SQ_SIZE = 3; //this is the squire of the full size (if change to 4 than a row will have 16 fields
    private final int size= SQ_SIZE*SQ_SIZE;//number of fields in a row
    private TextField field[];
    private SodukoCheck table;//A class object with a char matrix that will help us check rows, columns ad cubes
    //create and place the TextFields on the grid
    public void initialize() {
		field = new TextField[size*size];
		table = new SodukoCheck(size);//create a char matrix that will help us check the inputs
		for (int i=0; i<size*size ;i++) {
			field[i]= new TextField();
			field[i].setPrefSize(grid.getPrefWidth()/size, grid.getPrefHeight()/size);
			grid.add(field[i], i%size, i/size);
			field[i].setId(i+"");//set fx:id for each TextField
			field[i].setStyle("-fx-border-color: #000000;");
			if ((i/size)/SQ_SIZE==1^(i%size)/SQ_SIZE==1)//Method to paint like a soduko board
				field[i].setBackground(new Background(new BackgroundFill(Color.BLANCHEDALMOND, CornerRadii.EMPTY,Insets.EMPTY)));
			field[i].setOnAction(new EventHandler<ActionEvent>() {
				public void handle (ActionEvent event) {
					textHandle(event);
				}
			});
			
		}
    }
    //Method that handle ENTER in a TextField
    private void textHandle(ActionEvent event) {
    	String s =((TextField) event.getSource()).getText();
    	int index=Integer.parseInt((((TextField)event.getSource()).getId()));
    	System.out.println(index);//print to console for programmer use
    	if (s.length()!=0) {//TextField is not empty
    		if (s.length()!=1 || !Character.isDigit(s.charAt(0))) {
    			Alert alert = new Alert(AlertType.CONFIRMATION);
				alert.setTitle("Error");
				alert.setHeaderText("Error at choosing a digit");
				alert.setContentText("Must choose 1 digit only (0-9), no latters or special characters");
				Optional<ButtonType> option = alert.showAndWait();
				((TextField) event.getSource()).clear();
    		}
    		else if(!table.checkInsert(index%size,index/size, s.charAt(0))) {
    			Alert alert = new Alert(AlertType.CONFIRMATION);
				alert.setTitle("Error");
				alert.setHeaderText("The digit is already exist in row/column or cube");
				alert.setContentText("Please try again");
				Optional<ButtonType> option = alert.showAndWait();
				((TextField) event.getSource()).clear();
    		}
    	}
    	else {//TextField is  empty
    		((TextField) event.getSource()).clear();
    		table.clearField(index%size, index/size);
    	}
    }
    //Method that clears the soduko and we can start over
    @FXML
    void clearPressed(ActionEvent event) {

    	for (int i=0; i<size*size;i++) {
    		field[i].setEditable(true);
    		field[i].clear();
    		field[i].setStyle("-fx-border-color: #000000;");
    	}
    	table.clear();
    }

    //Method that set and finish the preparing stage of the soduko
    @FXML
    void setPressed(ActionEvent event) {

    	for (int i=0; i<size*size; i++) {
    		if (field[i].getText().length()!=0) {
    			field[i].setEditable(false);
    			field[i].setStyle("-fx-text-inner-color: #FF0000; -fx-border-color: #000000;");
    			
    		}
    	}
    }

}
