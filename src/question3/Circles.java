package question3;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.StrokeType;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * Created by: Justin Volckmar, Nathaniel Armogan 
 * Creates an instance of 3 points along a circle with connected lines and displayed adjustable angles. 
 * Extends the application window from JavaFX
 */
public class Circles extends Application {
	
	public Point[] p = new Point[3]; //3 point objects
	public Circle circle; //main circle object
	
	public static void main(String[] args) {
		launch(args); //launch the main window
	}
	
	/**
	 * Start a new window using JavaFX
	 * @param window - the main window being launched through the main application
	 */
	public void start(Stage window) throws Exception {
		Group root = new Group(); //initialize group and scene
		Scene scene = new Scene(root, 500, 400);
		//create the point objects (inherits circle, contains text object)
		p[0] = new Point(361,277); //random arbitrary start points on the circle
		p[1] = new Point(115,205);
		p[2] = new Point(133,267);
		//create binded lines to the different points
		p[0].line = connect(p[1], p[2]);
		p[1].line = connect(p[0], p[2]);
		p[2].line = connect(p[0], p[1]);
		//create the main circle object
		circle = new Circle(250, 200, 135, Color.TRANSPARENT);
		circle.setStroke(Color.BLACK);
		circle.setStrokeType(StrokeType.OUTSIDE);
		findAngles(); //fetch the angles (updates the text objects)
		//add all objects (and sub-objects) to the group
		root.getChildren().addAll(circle, p[0], p[1], p[2]);
		root.getChildren().addAll(p[0].text, p[1].text, p[2].text);
		root.getChildren().addAll(p[0].line, p[1].line, p[2].line);
		window.setScene(scene); //set the scene
		window.setResizable(false); //no need to resize the window
		window.setTitle("Movable Triangle along a Circle"); //set the title
		window.show(); //display the GUI
	}
	
	/**
	 * Creates a line binded to 2 points.
	 * @param c1 - The Point object to bind to the start of the new line
	 * @param c2 - The Point object to bind to the end of the new line
	 */
	private Line connect(Point c1, Point c2) {
	    Line line = new Line(); //create the new line
	    //bind start to c1
	    line.startXProperty().bind(c1.centerXProperty());
	    line.startYProperty().bind(c1.centerYProperty());
	    //bind end to c2
	    line.endXProperty().bind(c2.centerXProperty());
	    line.endYProperty().bind(c2.centerYProperty());
	    line.setStrokeWidth(1); //set line thickness
	    return line; //return the new line object
	}
	
	/**
	 * Point object extending Circle, containing an opposing line and text object to which can be binded to the point.
	 */
	public class Point extends Circle {
		
		protected Line line; //the line opposite the angle
		protected Text text; //the angle display
		protected double angle; //the storage for the adjustable vertex angle local to the point
		
		/**
		 * Initialize a new point object at x,y.
		 * @param x - the horizontal distance from the left of the page which to place the center of the circle
		 * @param y - the vertical distance from the top of the page which to place the center of the circle
		 */
		public Point(double x, double y) {
			//create the point "circle" object with at the provided coordinates
			this.setCenterX(x);
			this.setCenterY(y);
			this.setRadius(8);
			this.setFill(Color.RED);
			//create a text object and bind it to the point
			text = new Text();
			text.xProperty().bind(this.centerXProperty().add(10));
			text.yProperty().bind(this.centerYProperty().subtract(10));
			this.setOnMouseDragged(new EventHandler<MouseEvent>() { //create a new event listener for the point object instance
				public void handle(MouseEvent e) { //e is the listener on the mouse storing its current position
					//fetch the new angle based on the mouse position
					angle = Math.atan2(e.getX() - circle.getCenterY(), e.getY() - circle.getCenterX());
					shift(); //update the point
				}
			});
		}
		
		/**
		 * Shift the current point based on the updated angle fetched by the mouse listener.
		 */
		public void shift() {
			this.setCenterX(circle.getCenterX() + circle.getRadius() * Math.sin(angle));
			this.setCenterY(circle.getCenterY() + circle.getRadius() * Math.cos(angle));
			findAngles();
		}
		
	} // end of Point object

	/**
	 * Fetch all angles (in degrees) and display them the their appropriate text objects
	 */
	public void findAngles() {
		double a = Math.sqrt(Math.pow(p[1].getCenterX() - p[2].getCenterX(), 2) + Math.pow(p[1].getCenterY() - p[2].getCenterY(), 2));
		double b = Math.sqrt(Math.pow(p[0].getCenterX() - p[2].getCenterX(), 2) + Math.pow(p[0].getCenterY() - p[2].getCenterY(), 2));
		double c = Math.sqrt(Math.pow(p[1].getCenterX() - p[0].getCenterX(), 2) + Math.pow(p[1].getCenterY() - p[0].getCenterY(), 2));
		p[0].angle = Math.round(Math.toDegrees(Math.acos((a * a - b * b - c * c) / (-2 * b * c))));
		p[1].angle = Math.round(Math.toDegrees(Math.acos((b * b - a * a - c * c) / (-2 * a * c))));
		p[2].angle = Math.round(Math.toDegrees(Math.acos((c * c - b * b - a * a) / (-2 * a * b))));
		p[0].text.setText(String.valueOf((int)p[0].angle));
		p[1].text.setText(String.valueOf((int)p[1].angle));
		p[2].text.setText(String.valueOf((int)p[2].angle));
	}
	
}
