package question1;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Cards extends Application {
	Button button;
	ImageView image1, image2, image3;
	
	public static void main(String[] args) {
		launch(args);
	}
	
	public void start(Stage window) {
		VBox root = new VBox();
		HBox cards = new HBox();
		Scene scene = new Scene(root, 600, 320);
		Image back = new Image(getClass().getResource("images/backofcard.jpg").toString());
		image1 = new ImageView(back);
		image1.setFitWidth(200); image1.setFitHeight(300);
		image2 = new ImageView(back);
		image2.setFitWidth(200); image2.setFitHeight(300);
		image3 = new ImageView(back);
		image3.setFitWidth(200); image3.setFitHeight(300);
		button = new Button("Randomize");
		button.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent arg0) {
				randomize();
			}
		});
		cards.getChildren().addAll(image1, image2, image3);
		root.getChildren().addAll(cards, button);
		window.setScene(scene);
        window.setTitle("Random Cards");
        window.setResizable(false);
		window.show();
		
	}
	
	@FXML public void randomize() {
		int i,j,k,l,m,n;
		String r = "images/";
		i = (int) (Math.random()*4+1);
		j = (int) (Math.random()*13+1);
		switch (j) { case 11: r += "J"; break; case 12: r += "Q"; break; case 13: r += "K"; break; case 1: r += "A"; break; default: r += String.valueOf(j); break; }
		switch (i) { case 1: r += "D"; break;case 2: r += "H"; break;case 3: r += "C"; break;case 4: r += "S"; break;default: r += "D"; break; }
		r += ".png";
		image1.setImage(new Image(getClass().getResource(r).toString()));
		r = "images/";
		do {
			k = (int) (Math.random()*4+1); 
			l = (int) (Math.random()*13+1);
		} while (i == k || j == l);
		switch (l) { case 11: r += "J"; break; case 12: r += "Q"; break; case 13: r += "K"; break; case 1: r += "A"; break; default: r += String.valueOf(l); break; }
		switch (k) { case 1: r += "D"; break;case 2: r += "H"; break;case 3: r += "C"; break;case 4: r += "S"; break;default: r += "D"; break; }
		r += ".png";
		image2.setImage(new Image(getClass().getResource(r).toString()));
		r = "images/";
		do {
			m = (int) (Math.random()*4+1); 
			n = (int) (Math.random()*13+1);
		} while ((m == k && m == i) || (n == l && n == j));
		switch (n) { case 11: r += "J"; break; case 12: r += "Q"; break; case 13: r += "K"; break; case 1: r += "A"; break; default: r += String.valueOf(n); break; }
		switch (m) { case 1: r += "D"; break;case 2: r += "H"; break;case 3: r += "C"; break;case 4: r += "S"; break;default: r += "D"; break; }
		r += ".png";
		image3.setImage(new Image(getClass().getResource(r).toString()));
	}
}
