

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class maman1q2Controller {

    @FXML
    private Canvas canv;
    private GraphicsContext gc;
    //Arrays for the labels of years and months
    private final String mounth[]= {"jan", "feb" , "mar" ,"apr","may","jun","jul","aug","sep","oct","nov","dec"};
    private final String year[]= {"2000", "2001","2002","2003","2004"};
    private final int MONTHS = 12;
    private final int YEARS = 5;
    private final int COLOM_WIDTH = 20;
    //Construct the data-base matrix with random values
    DataBase db = new DataBase();
    int countYear=0;
    public void initialize() {
    	gc = canv.getGraphicsContext2D();

    }
    @FXML
    
    
    void press(ActionEvent event) {
    	gc.clearRect(0, 0, canv.getWidth(), canv.getHeight());
    	gc.setFill(Color.GREY);
    	int min=250, minS=0 ,max=0, maxS=0;
    	for (int i =0; i < MONTHS;i++ ) {
    		int height = db.getTemp(countYear, i);
    		//This part keeps the value an the month with max and min temp.
    		if (height<min) {
    			minS=i;
    			min=height;
    		}
    		if (height>max) {
    			maxS=i;
    			max=height;
    		}
    		//Drawing the columns
    		gc.fillRect((2*i*COLOM_WIDTH)+50, canv.getHeight()-50 - height , COLOM_WIDTH, height);
    		gc.strokeText(mounth[i],(2*i*COLOM_WIDTH)+50,canv.getHeight()-10);
    	}
    	//Changing the color of the max and min temp months
    	gc.setFill(Color.RED);
    	gc.fillRect((2*maxS*COLOM_WIDTH)+50, canv.getHeight()-50 - max , COLOM_WIDTH, max);
    	gc.setFill(Color.BLUE);
    	gc.fillRect((2*minS*COLOM_WIDTH)+50, canv.getHeight()-50 - min , COLOM_WIDTH, min);
    	//Drawing the axis and labels
    	gc.strokeLine(25, 50, 25, canv.getHeight()-25);
		gc.strokeLine(25, canv.getHeight()-25 , canv.getWidth()-25, canv.getHeight()-25);
		gc.strokeText("42C", 0, canv.getHeight()-250);
		gc.strokeText("21C", 0, canv.getHeight()-150);
		gc.strokeText("0C", 0, canv.getHeight()-50);
		gc.strokeText(year[countYear], canv.getWidth()/2, canv.getHeight()/4);
		//Keeps the 5 year cycle
		if (countYear<YEARS-1)
			countYear++;
		else
			countYear=0;
		
    }
   


}
