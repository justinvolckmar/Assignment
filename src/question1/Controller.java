package question1;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Controller {

	@FXML Button button;
	@FXML ImageView image1, image2, image3;
	
	public Controller() {
		button = new Button();
		image1 = new ImageView();
		image2 = new ImageView();
		image3 = new ImageView();
		
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
