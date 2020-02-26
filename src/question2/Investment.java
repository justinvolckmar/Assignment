package question2;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 * Created by: Justin Volckmar, Nathaniel Armogan 
 * Creates an instance of an investment calculator based on loan, years, and investment rate. 
 * Extends the application window from JavaFX
 */
public class Investment extends Application {

	public static void main(String[] args) {
		launch(args); //launch the main window
	}
	
	Button button; // calculate button
	Label i,y,r,o; //labels for each field
	TextField investment, years, investRate, output; //textfields for collecting/displaying numbers
	
	@SuppressWarnings("static-access") //for setContstraints (static method with a non-static instance of a GridPane)
	public void start(Stage window) throws Exception {
		GridPane root = new GridPane(); //main layout instance
		root.setHgap(5); root.setVgap(5); //set basic gaps to separate each grid box
		Scene scene = new Scene(root, 350, 200); //set the scene
		//create the investment label and field and place them beside eachother at row 0
		i = new Label("Investment Amount"); root.setConstraints(i,0,0); 
		investment = new TextField(); root.setConstraints(investment,1,0); 
		//create the num years and field and place them beside eachother at row 1
		y = new Label("Years"); root.setConstraints(y,0,1);
		years = new TextField(); root.setConstraints(years,1,1);
		//create the interest rate label and field and place them beside eachother at row 2
		r = new Label("Annual Interest Rate"); root.setConstraints(r,0,2);
		investRate = new TextField(); root.setConstraints(investRate,1,2);
		//create the output label and field and place them beside eachother at row 3
		o = new Label("Future Value"); root.setConstraints(o,0,3);
		output = new TextField(); output.setEditable(false); root.setConstraints(output,1,3);
		//create the button in row 4 column 1 (0 based)
		button = new Button("Calculate"); root.setConstraints(button,1,4);
		button.setOnAction(new EventHandler<ActionEvent>() { 
			public void handle(ActionEvent arg0) {
				calculateInvestment(); //calculate the new investment and update the output field
			}
		});
		//add all objects to the gridpane
		root.getChildren().addAll(i,investment,y,years,r,investRate,o,output,button);
		//launch the main window
		window.setScene(scene);
        window.setTitle("Investment Calculator");
        window.setResizable(false);
		window.show();
	}
	
	/**
	 * Set the output text to the future investment value based on the provided formula from the assignment page
	 * @throws NumberFormatException - Use of letters in the textfield or an empty field will 100% throw an error but not crash the application window.
	 */
	public void calculateInvestment() throws NumberFormatException {
		output.setText(String.format("%.2f", (Double.parseDouble(investment.getText()) //set the text to the calculated investment value
			* Math.pow(1 + Double.parseDouble(investRate.getText())/1200, Double.parseDouble(years.getText())*12))));
	}

}
