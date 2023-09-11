import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class MenuController {

    @FXML
    private GridPane dessertsGrid;

    @FXML
    private GridPane drinksGrid;

    @FXML
    private GridPane firstGrid;

    @FXML
    private GridPane mainGrid;
    
    @FXML
    private VBox vBox;

    private final static int LABEL_PART_OF_ROW = 2;//Help with the size of Label
    private final static int CHCKBOX_PART_OF_ROW = 6;//Help with the size of CheckBox
    private final static int COMBO_PART_OF_ROW = 3;//Help with the size of CombokBox
    private final static int NUM_OF_TYPE = 4;
    private final static int ROW_LENG = 30;
    private final static int BTN_LENG = 50;
    private final static int MAX_ORDER = 10; //For the ComboBox
    private MenuList list;
    private OrderList bill=new OrderList();
    private static int countRow=0;
    
    public void initialize() {
    	try {
    		
    		list = new MenuList();
    		Scanner input = new Scanner(new File("italian.txt"));
    		storeMenu(list, input);
    		input.close();
    		createAllGrid(list);
    		vBox.setPrefHeight((countRow+NUM_OF_TYPE)*ROW_LENG + BTN_LENG);
    		
    	}
    	catch(IOException e) {
    		System.out.println("File not found");
    	}
    }
    //Create the "Back hand" database
    private static void storeMenu(MenuList list, Scanner input) {
    	while (input.hasNext()){
			String name = input.nextLine();
			String type = input.nextLine();
			double price = Double.parseDouble(input.nextLine());
			if (type.equals("First"))
				list.addFirst(new Dish(name, type, price));
			else if(type.equals("Main"))
				list.addMain(new Dish(name, type, price));
			else if (type.equals("Dessert"))
				list.addDessert(new Dish(name, type, price));
			else if (type.equals("Drink"))
				list.addDrink(new Dish(name, type, price));
			}
    	
    }
    //Create and design the 4 sections of the menu
    private void createAllGrid(MenuList list) {
    	createGrid(firstGrid, list.getFirstList());
    	createGrid(mainGrid, list.getMainList());
    	createGrid(dessertsGrid, list.getDessertList());
    	createGrid(drinksGrid, list.getDrinkList());
    	firstGrid.setStyle("-fx-border-color: black;");
    	mainGrid.setStyle("-fx-border-color: black;");
    	dessertsGrid.setStyle("-fx-border-color: black;");
    	drinksGrid.setStyle("-fx-border-color:black;");
    	firstGrid.setPrefHeight(ROW_LENG*list.getFirstList().size());
    	mainGrid.setPrefHeight(ROW_LENG*list.getMainList().size());
    	dessertsGrid.setPrefHeight(ROW_LENG*list.getDessertList().size());
    	drinksGrid.setPrefHeight(ROW_LENG*list.getDrinkList().size());
    	
    }
    //create the list of each section in the menu
    private static void createGrid(GridPane g, ArrayList<Dish> d) {
    	Label labels[] = new Label[d.size()];
    	ComboBox<Integer> combos[] = new ComboBox[d.size()];
    	CheckBox checks[] = new CheckBox[d.size()];
    	for (int i =0; i<d.size();i++) {
    		labels[i] = new Label(d.get(i).getName()+ "	Price: "+ d.get(i).getPrice());
    		labels[i].setFont(new Font("Ariel", 14));
    		checks[i]= new CheckBox();
    		combos[i] = new ComboBox<Integer>();
    		countRow++;
    		labels[i].setPrefSize(g.getPrefWidth()/LABEL_PART_OF_ROW, ROW_LENG);
    		checks[i].setPrefSize(g.getPrefWidth()/CHCKBOX_PART_OF_ROW, ROW_LENG);
    		combos[i].setPrefSize(g.getPrefWidth()/COMBO_PART_OF_ROW, ROW_LENG);
    		labels[i].setStyle("-fx-border-color: lightblue;");
    		checks[i].setStyle("-fx-border-color: lightblue;");
    		combos[i].setStyle("-fx-border-color: lightblue;");
    		g.add(labels[i], 0, i);
    		g.add(checks[i], 1, i);
    		g.add(combos[i], 2, i);
    		for (int j=0; j<=MAX_ORDER; j++)
    			combos[i].getItems().add(j);
    		combos[i].setValue(0);
    	}
    }
    @FXML
    void orderPresset(ActionEvent event) {
    	String s= "";//string with the bill elaboration
    	double sum=0;
    	createBill(firstGrid);
    	createBill(mainGrid);
    	createBill(dessertsGrid);
    	createBill(drinksGrid);
    	for (int i=0; i<bill.getAmount().size();i++) {
    		s=s+(bill.getOrder().get(i).getName()+" price:"+bill.getOrder().get(i).getPrice()+" X "+ bill.getAmount().get(i)+" Sbttl: "+((double)bill.getAmount().get(i)*bill.getOrder().get(i).getPrice())+ "\n");
    		sum+=(double)bill.getAmount().get(i)*bill.getOrder().get(i).getPrice();
    	}
    	s=s+"Total bill: "+sum;
    	List<String> choices = Arrays.asList("Approve", "Modify", "Cancel");
        ChoiceDialog<String> dialog = new ChoiceDialog<>("Approve", choices);
        dialog.setTitle("Bill");
        dialog.setContentText(s);
        Optional<String> result = dialog.showAndWait();
        if (result.get().equals("Approve")) {
        	TextInputDialog dialog2 = new TextInputDialog();
        	dialog2.setHeaderText("Please enter your name and id without space");
        	Optional<String> result2 = dialog2.showAndWait();
        	if (result2.isPresent()) {
        		try {
        		     FileWriter fw = new FileWriter(result2.get()+".txt");
        		     fw.write(s);
        		     fw.close();
        		     clearPage();
        		} catch (IOException e) {
        		     System.out.println("Error");
        		}
        	}
        }
        else if (result.get().equals("Cancel")) {
        	clearPage();
        }
        bill.clearBill();
    }
    //Add one of the the 4 section to the bill
    private void createBill(GridPane g) {
    	String label=null;
    	Boolean checked=false;
    	for (Node node : g.getChildren() ) {
    		if (node instanceof Label) 
    			label=((Label)node).getText();
    		else if(node instanceof CheckBox && ((CheckBox)node).isSelected()) 
    			checked =true;
    		else if (node instanceof ComboBox<?>) {
    			if (checked) {
    				bill.addDish(new Dish(label.split("	")[0],"",Double.parseDouble(label.split(":")[1])), (int)((ComboBox<Integer>)node).getValue());
    			}
    			checked=false;
    		}
    	}
    }
    private void clearPage() {
    	clearGrid(firstGrid);
    	clearGrid(mainGrid);
    	clearGrid(dessertsGrid);
    	clearGrid(drinksGrid);
    }
    //Clear the "front hand" after an order is finished or canceled
    private void clearGrid(GridPane g) {
    	for (Node node : g.getChildren() ) {
    		if(node instanceof CheckBox) 
    			((CheckBox)node).setSelected(false);
    		else if (node instanceof ComboBox<?>)
    				((ComboBox<Integer>)node).setValue(0);
    	}
    }

}
