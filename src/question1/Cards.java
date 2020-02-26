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

/**
 * Created by: Justin Volckmar, Nathaniel Armogan 
 * Creates an instance of a Randomizing Unique Cards from a standard deck with them initially face down.
 * Extends the application window from JavaFX
 */
public class Cards extends Application {
	
	Button button; // randomize button
	ImageView image1, image2, image3; //card imageviews
	
	public static void main(String[] args) {
		launch(args); //launch the main window
	}

	public void start(Stage window) {
		VBox root = new VBox(); //create the outer vbox as the root
		HBox cards = new HBox(); //create an hbox for the cards
		Scene scene = new Scene(root, 600, 320); //set the scene
		Image back = new Image(getClass().getResource("images/backofcard.jpg").toString()); //create a image for the backofcard
		//set all 3 images to the back of a card with a fit width and height of 200 and 300 respectively
		image1 = new ImageView(back);
		image1.setFitWidth(200); image1.setFitHeight(300);
		image2 = new ImageView(back);
		image2.setFitWidth(200); image2.setFitHeight(300);
		image3 = new ImageView(back);
		image3.setFitWidth(200); image3.setFitHeight(300);
		//create the randomize button with a listener when clicked
		button = new Button("Randomize");
		button.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent arg0) {
				randomize();//call the randomzie function to update the cards to random unique cards
			}
		});
		//add all cards to the hbox
		cards.getChildren().addAll(image1, image2, image3);
		//add the hbox and button to the main vbox
		root.getChildren().addAll(cards, button);
		//launch the new scene
		window.setScene(scene);
        window.setTitle("Random Cards");
        window.setResizable(false);
		window.show();
		
	}
	
	/**
	 * Randomly generate 3 unique strings which are used as file paths for the card images (1-4 for suits, 1-13 for values)
	 */
	@FXML public void randomize() {
		int i,j,k,l,m,n; //create all random integers
		String r = "images/"; //initialize the string to the correct folder
		//generate random values
		i = (int) (Math.random()*4+1); 
		j = (int) (Math.random()*13+1);
		//check if the value should be a letter, else use numeric value in string
		switch (j) { case 11: r += "J"; break; case 12: r += "Q"; break; case 13: r += "K"; break; case 1: r += "A"; break; default: r += String.valueOf(j); break; }
		//set appropriate suit
		switch (i) { case 1: r += "D"; break;case 2: r += "H"; break;case 3: r += "C"; break;case 4: r += "S"; break;default: r += "D"; break; }
		r += ".png"; //add png tag and set the image
		image1.setImage(new Image(getClass().getResource(r).toString()));
		r = "images/"; //reset the imagepath
		do { //generate random numbers till theres a new unique card
			k = (int) (Math.random()*4+1); 
			l = (int) (Math.random()*13+1);
		} while (i == k || j == l);
		//check if the value should be a letter, else use numeric value in string
		switch (l) { case 11: r += "J"; break; case 12: r += "Q"; break; case 13: r += "K"; break; case 1: r += "A"; break; default: r += String.valueOf(l); break; }
		//set appropriate suit
		switch (k) { case 1: r += "D"; break;case 2: r += "H"; break;case 3: r += "C"; break;case 4: r += "S"; break;default: r += "D"; break; }
		r += ".png"; //add png tag and set the image
		image2.setImage(new Image(getClass().getResource(r).toString()));
		r = "images/"; //reset the imagepath
		do { //generate random numbers till theres a new unique card
			m = (int) (Math.random()*4+1); 
			n = (int) (Math.random()*13+1);
		} while ((m == k && m == i) || (n == l && n == j));
		//check if the value should be a letter, else use numeric value in string
		switch (n) { case 11: r += "J"; break; case 12: r += "Q"; break; case 13: r += "K"; break; case 1: r += "A"; break; default: r += String.valueOf(n); break; }
		//set appropriate suit
		switch (m) { case 1: r += "D"; break;case 2: r += "H"; break;case 3: r += "C"; break;case 4: r += "S"; break;default: r += "D"; break; }
		r += ".png"; //add png tag and set the image
		image3.setImage(new Image(getClass().getResource(r).toString()));
	}
}
