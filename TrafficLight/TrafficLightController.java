import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class TrafficLightController extends Thread{

    @FXML
    private Canvas canv;
    private GraphicsContext gc;
    
    private final int CIRC_SIZE = 25;
    private final int RECT_LEN = 20;
    private final int RECT_WID = 15;
    private final int DIFF_SIZE = 5;
    
    public void initialize() {
    	gc = canv.getGraphicsContext2D();
    	start();
    }
    public void run() {
    	super.run();
    	while(true) {
	    	try {
	    		buildGreenLight(CIRC_SIZE*2,canv.getHeight()/2-CIRC_SIZE*2,
	    				canv.getWidth()-CIRC_SIZE*2,canv.getHeight()/2-CIRC_SIZE*2);
	    		buildRedLight(canv.getWidth()/2-CIRC_SIZE/2,50,
	    				canv.getWidth()/2-CIRC_SIZE/2,canv.getHeight()-CIRC_SIZE*4);
	    		buildGreenLight(canv.getWidth()/2-CIRC_SIZE/2,50,
	    				canv.getWidth()/2-CIRC_SIZE/2,canv.getHeight()-CIRC_SIZE*4);
	    		buildRedLight(CIRC_SIZE*2,canv.getHeight()/2-CIRC_SIZE*2,
	    				canv.getWidth()-CIRC_SIZE*2,canv.getHeight()/2-CIRC_SIZE*2);
	    		
	    	}catch(InterruptedException e){}
	    	
    	}
    	
    }
    
    private void buildRedLight(double Ax, double Ay, double Bx, double By) throws InterruptedException {
    	gc.setFill(Color.RED);
    	gc.fillOval(Ax, Ay, CIRC_SIZE, CIRC_SIZE);
    	gc.fillOval(Bx, By, CIRC_SIZE, CIRC_SIZE);
    	gc.setFill(Color.GREY);
    	gc.fillOval(Ax, Ay+(CIRC_SIZE), CIRC_SIZE, CIRC_SIZE);
    	gc.fillOval(Bx, By+(CIRC_SIZE), CIRC_SIZE, CIRC_SIZE);
    	
    	gc.fillRect(Ax+DIFF_SIZE, Ay+2*CIRC_SIZE, RECT_WID, RECT_LEN);
    	gc.fillRect(Bx+DIFF_SIZE, By+2*CIRC_SIZE, RECT_WID, RECT_LEN);
    	for (int i=0; i<10; i++) {
    		gc.setFill(Color.GREEN);
    		gc.fillRect(Ax+DIFF_SIZE, Ay+2*CIRC_SIZE+RECT_LEN, RECT_WID, RECT_LEN);
    		gc.fillRect(Bx+DIFF_SIZE, By+2*CIRC_SIZE+RECT_LEN, RECT_WID, RECT_LEN);
    		sleep(200);
    		gc.setFill(Color.GREY);
    		gc.fillRect(Ax+DIFF_SIZE, Ay+2*CIRC_SIZE+RECT_LEN, RECT_WID, RECT_LEN);
    		gc.fillRect(Bx+DIFF_SIZE, By+2*CIRC_SIZE+RECT_LEN, RECT_WID, RECT_LEN);
    		sleep(200);
    	}
    	
    }
    private void buildGreenLight(double Ax, double Ay, double Bx, double By) throws InterruptedException {
    	gc.setFill(Color.GREY);
    	gc.fillOval(Ax, Ay, CIRC_SIZE, CIRC_SIZE);
    	gc.fillOval(Bx, By, CIRC_SIZE, CIRC_SIZE);
    	gc.setFill(Color.GREEN);
    	gc.fillOval(Ax, Ay+(CIRC_SIZE), CIRC_SIZE, CIRC_SIZE);
    	gc.fillOval(Bx, By+(CIRC_SIZE), CIRC_SIZE, CIRC_SIZE);
    	gc.setFill(Color.GREY);
    	gc.fillRect(Ax+DIFF_SIZE, Ay+2*CIRC_SIZE, RECT_WID, RECT_LEN);
    	gc.fillRect(Bx+DIFF_SIZE, By+2*CIRC_SIZE, RECT_WID, RECT_LEN);
    	gc.setFill(Color.RED);
    	gc.fillRect(Ax+DIFF_SIZE, Ay+2*CIRC_SIZE+RECT_LEN, RECT_WID, RECT_LEN);
    	gc.fillRect(Bx+DIFF_SIZE, By+2*CIRC_SIZE+RECT_LEN, RECT_WID, RECT_LEN);
    }

}
