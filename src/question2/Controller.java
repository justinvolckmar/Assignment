package question2;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class Controller {

	@FXML Button button;
	@FXML TextField text1, text2, text3, text4;
	
	public Controller() {
		button = new Button();
		text1 = new TextField();
		text2 = new TextField();
		text3 = new TextField();
		text4 = new TextField();
	}
	
	@FXML public void calculateInvestment() {
		double investment = Double.parseDouble(text1.getText());
		double years = Double.parseDouble(text2.getText());
		double investRate = Double.parseDouble(text3.getText());
		text4.setText(String.format("%.2f", (investment * Math.pow(1 + investRate/1200, years*12))));
	}
	
}
