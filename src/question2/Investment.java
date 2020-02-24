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

public class Investment extends Application {

	public static void main(String[] args) {
		launch(args);
	}
	
	Button button;
	Label i,y,r,o;
	TextField investment, years, investRate, output;
	
	@SuppressWarnings("static-access")
	public void start(Stage window) throws Exception {
		GridPane root = new GridPane();
		root.setHgap(5); root.setVgap(5);
		Scene scene = new Scene(root, 350, 200);
		i = new Label("Investment Amount"); root.setConstraints(i,0,0); 
		investment = new TextField(); root.setConstraints(investment,1,0); 
		y = new Label("Years"); root.setConstraints(y,0,1);
		years = new TextField(); root.setConstraints(years,1,1);
		r = new Label("Annual Interest Rate"); root.setConstraints(r,0,2);
		investRate = new TextField(); root.setConstraints(investRate,1,2);
		o = new Label("Future Value"); root.setConstraints(o,0,3);
		output = new TextField(); output.setEditable(false); root.setConstraints(output,1,3);
		button = new Button("Calculate"); root.setConstraints(button,1,4);
		button.setOnAction(new EventHandler<ActionEvent>() { 
			public void handle(ActionEvent arg0) {
				calculateInvestment();
			}
		});
		root.getChildren().addAll(i,investment,y,years,r,investRate,o,output,button);
		window.setScene(scene);
        window.setTitle("Investment Calculator");
        window.setResizable(false);
		window.show();
	}
	
	public void calculateInvestment() throws NumberFormatException {
		output.setText(String.format("%.2f", (Double.parseDouble(investment.getText()) 
			* Math.pow(1 + Double.parseDouble(investRate.getText())/1200, Double.parseDouble(years.getText())*12))));
	}

}
