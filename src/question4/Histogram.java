package question4;

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

public class Histogram extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void start(Stage window) throws Exception {
		VBox root = new VBox();
		HBox bottom = new HBox();
		Scene scene = new Scene(root, 900, 400);
		CategoryAxis x = new CategoryAxis();
        x.setLabel("Letters");
        NumberAxis y = new NumberAxis();
        y.setLabel("Occurances");
        y.setMinorTickLength(1);
        BarChart<String, Number> chart = new BarChart<>(x, y);
        XYChart.Series<String, Number> series = new XYChart.Series<>();
        chart.setLegendVisible(false);
        for (int i = 0 ; i < 26 ; i++) {
        	XYChart.Data<String, Number> data = new XYChart.Data<>(String.valueOf((char)(i+65)),0);
        	series.getData().add(data);
        }
        chart.getData().add(series);
        Label label = new Label("Text: ");
        Label label2 = new Label(" (Press ENTER to update)");
        TextField field = new TextField();
        field.setOnKeyReleased(new EventHandler<KeyEvent>() {
			public void handle(KeyEvent e) {
				if (e.getCode().equals(KeyCode.ENTER)) {
					for (int i = 0 ; i < 26; i++) {
						series.getData().get(i).setYValue(0);
					}
					char[] c = field.getText().toUpperCase().toCharArray();
					for (int i = 0 ; i < c.length ; i++) {
						int n = (int) (c[i])-65;
						if (n >= 0 && n < 26) {
							series.getData().get(n).setYValue((int)series.getData().get(n).getYValue()+1);
						}
					}
				}
			}
        });
        bottom.getChildren().addAll(label, field, label2);
        root.getChildren().addAll(chart, bottom);
        window.setScene(scene);
        window.setTitle("Letter Occurances in a TextField");
        window.setResizable(false);
		window.show();
	}
	
}
