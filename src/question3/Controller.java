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

public class Controller extends Application {
	
	public Point[] p = new Point[3];
	public Circle circle;
	
	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void start(Stage window) throws Exception {
		Group root = new Group();
		Scene scene = new Scene(root, 500, 400);
		p[0] = new Point(361,277);
		p[1] = new Point(115,205);
		p[2] = new Point(133,267);
		p[0].line = connect(p[1], p[2]);
		p[1].line = connect(p[0], p[2]);
		p[2].line = connect(p[0], p[1]);
		circle = new Circle(250, 200, 135, Color.TRANSPARENT);
		circle.setStroke(Color.BLACK);
		circle.setStrokeType(StrokeType.OUTSIDE);
		findAngles();
		root.getChildren().addAll(circle, p[0], p[1], p[2]);
		root.getChildren().addAll(p[0].text, p[1].text, p[2].text);
		root.getChildren().addAll(p[0].line, p[1].line, p[2].line);
		window.setScene(scene);
		window.setResizable(false);
		window.setTitle("Movable Triangle along a Circle");
		window.show();
	}
	
	private Line connect(Point c1, Point c2) {
	    Line line = new Line();
	    line.startXProperty().bind(c1.centerXProperty());
	    line.startYProperty().bind(c1.centerYProperty());
	    line.endXProperty().bind(c2.centerXProperty());
	    line.endYProperty().bind(c2.centerYProperty());
	    line.setStrokeWidth(1);
	    return line;
	}
	
	public class Point extends Circle {
		
		protected Line line;
		protected Text text;
		protected double angle;
		protected double length;
		
		public Point(double x, double y) {
			this.setCenterX(x);
			this.setCenterY(y);
			this.setRadius(8);
			this.setFill(Color.RED);
			text = new Text();
			text.xProperty().bind(this.centerXProperty().add(10));
			text.yProperty().bind(this.centerYProperty().subtract(10));
			this.setOnMouseClicked(new EventHandler<MouseEvent>() {
				public void handle(MouseEvent event) {
					System.out.println("q");
				}
				
			});
			this.setOnMouseDragged(new EventHandler<MouseEvent>() {
				public void handle(MouseEvent e) {
					angle = Math.atan2(e.getX() - circle.getCenterY(), e.getY() - circle.getCenterX());
					shift();
				}
			});
		}
		
		public void shift() {
			this.setCenterX(circle.getCenterX() + circle.getRadius() * Math.sin(angle));
			this.setCenterY(circle.getCenterY() + circle.getRadius() * Math.cos(angle));
			findAngles();
		}
		
	}

	
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
