package question4;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Created by: Justin Volckmar, Nathaniel Armogan 
 * Creates an instance of a Histogram window which updates on the press of ENTER
 * and displays the number of letters typed in a textfield (upper and lower together)
 */
public class Histogram extends Application {

	public static void main(String[] args) {
		launch(args); //launch the main window
	}

	/**
	 * Start a new window using JavaFX
	 * @param window - the main window being launched through the main application
	 */
	public void start(Stage window) throws Exception, FileNotFoundException {
		VBox root = new VBox(); //create a new vbox
		HBox bottom = new HBox(); //create a hbox for the bottom of the page
		Scene scene = new Scene(root, 900, 400); //create the scene (vbox is main)
		CategoryAxis x = new CategoryAxis(); //create x as a String axis and set the label to Letters
        x.setLabel("Letters");
        NumberAxis y = new NumberAxis(); //create y as a Number axis and set the label to Occurances
        y.setLabel("Occurances");
        BarChart<String, Number> chart = new BarChart<>(x, y); //create the bar chart object with x and y
        XYChart.Series<String, Number> series = new XYChart.Series<>(); //create a series in which to add data to
        chart.setLegendVisible(false); //hide the legend
        for (int i = 0 ; i < 26 ; i++) { //loop for every letter in the alphabet creating each data object with the value of 0
        	XYChart.Data<String, Number> data = new XYChart.Data<>(String.valueOf((char)(i+65)),0);
        	series.getData().add(data); //add each value to the series
        }
        chart.getData().add(series); //add the default series to the main chart
        Label label = new Label("Filename: "); //create the labels surrounding the field
        Label label2 = new Label(" (Press ENTER to update)");
        TextField field = new TextField("file.txt"); //create the textfield and set a listener for ENTER key pressed while in the field
        field.setOnKeyReleased(new EventHandler<KeyEvent>() {
			public void handle(KeyEvent e) {
				if (e.getCode().equals(KeyCode.ENTER)) {
					for (int i = 0 ; i < 26; i++) { //set all values back to 0
						series.getData().get(i).setYValue(0);
					}
					String filename = field.getText();
					String line;
					BufferedReader reader;
					try {
						reader = new BufferedReader(new FileReader("src/question4/" + filename));
						while ((line = reader.readLine()) != null) {
							char[] c = line.toUpperCase().toCharArray();
							for (int i = 0 ; i < c.length ; i++) { //loop through all characters in the field
								int n = (int) (c[i])-65;
								if (n >= 0 && n < 26) { //if the character at i is a letter, add it to the current total of that letter in the series
									series.getData().get(n).setYValue( (int)(series.getData().get(n).getYValue())+1 );
								}
							}
						}
					} catch (IOException e1) { e1.printStackTrace(); }
				}
			}
        });//end of listener
        //add labels and field to the bottom
        bottom.getChildren().addAll(label, field, label2);
        //add the chart then the bottom to the main window
        root.getChildren().addAll(chart, bottom);
        //launch the window with the new scene
        window.setScene(scene);
        window.setTitle("Letter Occurances in a TextField");
        window.setResizable(false);
		window.show();
	}
	
}
