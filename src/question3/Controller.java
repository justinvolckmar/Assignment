package question3;

import javafx.application.Application;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

public class Controller extends Application {
	
	public static void main(String[] args) {
		launch(args);
	}

	Circle mainCircle, circleA, circleB, circleC;
	Line lineAB, lineBC, lineAC;
	Label LabelA, LabelB, LabelC;
	double orgMouseX, orgMouseY;
	
	private Line connect(Circle c1, Circle c2) {
	    Line line = new Line();
	    line.startXProperty().bind(c1.centerXProperty());
	    line.startYProperty().bind(c1.centerYProperty());
	    line.endXProperty().bind(c2.centerXProperty());
	    line.endYProperty().bind(c2.centerYProperty());
	    line.setStrokeWidth(1);
	    return line;
	  }
	
	private Circle Circle(double x, double y, double r, Color color) {
	    Circle circle = new Circle(x, y, r, color);
	    circle.setCursor(Cursor.HAND); //set the c
	    circle.setOnMousePressed((mouseEvent) -> { 
	    	orgMouseX = mouseEvent.getSceneX(); orgMouseY = mouseEvent.getSceneY(); //collect initial origin of pressed circle
	    	//Circle c = (Circle) (event.getSource()); // a pointer to "circle" since it cannot be accessed in this scope.
	    	circle.toFront(); //bring to front
	    });
	    circle.setOnMouseDragged((mouseEvent) -> {
	    	double offsetX = mouseEvent.getSceneX() - orgMouseX; //get difference of mouse movement
    		double offsetY = mouseEvent.getSceneY() - orgMouseY; 
	    	if (mainCircle.contains(circle.getCenterX(), circle.getCenterY())) {
	    		Circle c = (Circle) (mouseEvent.getSource()); // a pointer to "circle" since it cannot be accessed in this scope.
	    		c.setCenterX(c.getCenterX() + offsetX); //set the x and y positions of the adjusted circle to the offset
	    		c.setCenterY(c.getCenterY() + offsetY); //which is the distance the mouse travelled.
	    		orgMouseX = mouseEvent.getSceneX(); //get the new mouse location
	    		orgMouseY = mouseEvent.getSceneY(); //x and y
	    		calcAngles();
	    	} else {
	    		circle.setCenterX(mainCircle.getCenterX()+offsetX);
	    		circle.setCenterY(mainCircle.getCenterY()+offsetY);
	    	}
	    });
	    return circle;
	  }
	
	@Override
	public void start(Stage window) throws Exception {
		Group root = new Group();
		Scene scene = new Scene(root, 500, 400);
		mainCircle = new Circle(250, 200, 135, Color.WHITE);
		mainCircle.setStroke(Color.BLACK);
		circleA = Circle(120, 169, 5, Color.RED);
		circleB = Circle(330, 98, 5, Color.RED);
		circleC = Circle(360, 265, 5, Color.RED);
		root.getChildren().addAll(mainCircle, circleA, circleB, circleC);
		lineAB = connect(circleA, circleB);
		lineBC = connect(circleB, circleC);
		lineAC = connect(circleA, circleC);
		root.getChildren().addAll(lineAB, lineBC, lineAC);
		LabelA = new Label();
		LabelB = new Label();
		LabelC = new Label();
		calcAngles();
		root.getChildren().addAll(LabelA, LabelB, LabelC);
		window.setScene(scene);
		window.setResizable(false);
		window.setTitle("Triangles in a Circle");
		window.show();
	}
	
	private void calcAngles() {
		double a = Math.pow(circleB.getCenterX() - circleC.getCenterX(), 2) + Math.pow(circleB.getCenterY() - circleC.getCenterY(), 2) ;
		double b = Math.pow(circleA.getCenterX() - circleC.getCenterX(), 2) + Math.pow(circleA.getCenterY() - circleC.getCenterY(), 2) ;
		double c = Math.pow(circleA.getCenterX() - circleB.getCenterX(), 2) + Math.pow(circleA.getCenterY() - circleB.getCenterY(), 2) ;
		double A = Math.round(100*180*Math.acos((b + c - a) / (2 * Math.sqrt(b) * Math.sqrt(c)))/Math.PI)/100.0;
		double B = Math.round(100*180*Math.acos((a + c - b) / (2 * Math.sqrt(a) * Math.sqrt(c)))/Math.PI)/100.0;
		double C = Math.round(100*180*Math.acos((a + b - c) / (2 * Math.sqrt(a) * Math.sqrt(b)))/Math.PI)/100.0;
		LabelA.setLayoutX(circleA.getCenterX()+10);
		LabelA.setLayoutY(circleA.getCenterY()-10);
		LabelB.setLayoutX(circleB.getCenterX()+10);
		LabelB.setLayoutY(circleB.getCenterY()-10);
		LabelC.setLayoutX(circleC.getCenterX()+10);
		LabelC.setLayoutY(circleC.getCenterY()-10);
		LabelA.setText(String.valueOf(A));
		LabelB.setText(String.valueOf(B));
		LabelC.setText(String.valueOf(C));
	}
	
}
