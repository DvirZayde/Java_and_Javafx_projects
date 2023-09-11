
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Optional;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class Maman14q2Controller {

	private HashMap<Date, String> hash;
    @FXML
    private ComboBox<String> dayCombo;

    @FXML
    private ComboBox<String> monthCombo;

    @FXML
    private TextArea textMemo;
   
    @FXML
    private VBox vBox;
   
    @FXML
    private ComboBox<String> yearCombo;

    
    public void initialize() {
		final int Month = 12, Day=30, START_YEAR=2015, END_YEAR=2025;
		hash = new HashMap<Date, String>();
		comboStart(1,Day,dayCombo);
		comboStart(1,Month,monthCombo);
		comboStart(START_YEAR,END_YEAR,yearCombo);
		
		loadFile();
    }

    private void comboStart (int star, int end, ComboBox<String> box) {
    	for (int i = star; i <= end; i++)
    		box.getItems().add(String.valueOf(i));
    	box.setValue(star+"");
    }
    private Date getDate() {
    	return new Date(Integer.parseInt(dayCombo.getValue()), 
				Integer.parseInt(monthCombo.getValue()),
				Integer.parseInt(yearCombo.getValue()));
    }
    @FXML
    void loadPressed(ActionEvent event) {
    	Date date = getDate();
    	textMemo.setText(hash.get(date));
    	
    }

    @FXML
    void savePressed(ActionEvent event) {
    	Date date = getDate();
    	hash.put(date, textMemo.getText());
    	
    	addClosingEvent();
    }
    
    private void loadFile() {
    	File calander = getFile();
    	if (calander !=null) {
    		try {
    		     FileInputStream fi = new FileInputStream(calander);
    		     ObjectInputStream ois = new ObjectInputStream(fi);
    		     hash  = (HashMap<Date, String>)ois.readObject();
    		     ois.close();
    		     fi.close();
	    	}catch (FileNotFoundException e) {
				System.out.println("File not found");
	    	}catch (IOException e) {
				System.out.println(" IO exception");
	    	}catch (ClassNotFoundException e) {
				System.out.println("HashMap<Date, String> not found in file");
	    	}
    	}
    }
    
    private File getFile() {
		FileChooser fileChooser = new FileChooser(); 
		fileChooser.setTitle("select a file"); 
		fileChooser.setInitialDirectory(new File(".")); 
		return fileChooser.showOpenDialog(null);
	}
    
    private void saveFile(){
    	TextInputDialog calander = new TextInputDialog();
    	calander.setHeaderText("Please enter name for the file, to override use the same name");
    	Optional<String> result = calander.showAndWait();
    	if (result.isPresent()) {
    		try {
    		FileOutputStream fo = new FileOutputStream(result.get()+".txt");
      	     ObjectOutputStream out = new ObjectOutputStream(fo);
      	     out.writeObject(hash);
      	     out.close();
      	     fo.close();
	       	}catch (Exception e) {
	       		System.out.println(e.getMessage());       	
	       	}
    	}
    }
    private void addClosingEvent() {
		Stage stage = (Stage)((Node) vBox).getScene().getWindow();
		stage.getScene().getWindow().addEventHandler(WindowEvent.WINDOW_CLOSE_REQUEST, event1 -> {
			saveFile();
		});	
	}

}
