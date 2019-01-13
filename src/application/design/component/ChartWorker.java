package application.design.component;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Ellipse;
import javafx.stage.Stage;

import java.util.ArrayList;

public class ChartWorker {
    private static Stage stage;

    public void drawChart() {

        final NumberAxis xAxis = new NumberAxis();
        final NumberAxis yAxis = new NumberAxis();
        final LineChart<Number, Number> lineChart = new LineChart<Number, Number>(xAxis, yAxis);
        //lineChart.setData(getChartData());
          // for (XYChart.Data<Number, Number> data : series1.getData()) {
        HBox hbox = new HBox();
        lineChart.getData().addAll(getElipseList(1,1));
        //XChart.Series series = getEllipseLt(1,2);
        for (XYChart.Series<Number, Number> series : lineChart.getData())
            for (XYChart.Data<Number, Number> data : series.getData())
                data.getNode().setVisible(false);

        hbox.getChildren().addAll(lineChart, new Ellipse(1,1));
        Scene scene = new Scene(hbox, 800, 600);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();

    }

    private XYChart.Series getElipseList(double a, double b) {
        double y;
        XYChart.Series list = new XYChart.Series();
        //list.setData();
        list.setName("green"); //i added name to all series, to find easy in loop

        //ObservableList<XYChart.Data<Number, Number>> list = FXCollections.observableArrayList(new ArrayList<XYChart.Data<Number, Number>>);
        for (double i = Math.sqrt(a*a + b*b); i <= 2 * Math.sqrt(a*a + b*b); i+= 0.005){
            y = Math.sqrt ((i*i/a*a - 1) * b*b);
            System.out.println(i);
            System.out.println(y);
            list.getData().addAll(new XYChart.Data(i, y), new XYChart.Data(-i, -y));
        }
        double i = a;
        y = Math.sqrt ((1 - i*i/a*a) * b*b);
        //list.getData().addAll(new XYChart.Data(i, y), new XYChart.Data(-i, y),new XYChart.Data(i, -y),new XYChart.Data(-i, -y));
        return list;
    }
    private ObservableList<XYChart.Series<Double, Double>> getChartData(double a , double b) {
        double y;
        ObservableList<XYChart.Series<Double, Double>> answer = FXCollections.observableArrayList();
        XYChart.Series<Double, Double> aSeries = new XYChart.Series<Double, Double>();
        XYChart.Series<Double, Double> cSeries = new XYChart.Series<Double, Double>();
        for (double i = 0; i <= a; i+= 0.005){
            y = Math.sqrt ((1 - i*i/a*a) * b*b);
            System.out.println(i);
            System.out.println(y);
            aSeries.getData().addAll(new XYChart.Data(i, y), new XYChart.Data(-i, -y));
            cSeries.getData().addAll(new XYChart.Data(-i, y), new XYChart.Data(i, -y));
        }

        answer.addAll(aSeries, cSeries);
        return answer;
    }
    private XYChart.Series getElipseList2(double a, double b) {
        double y;
        XYChart.Series list = new XYChart.Series();
        //list.setData();
        list.setName("green"); //i added name to all series, to find easy in loop

        //ObservableList<XYChart.Data<Number, Number>> list = FXCollections.observableArrayList(new ArrayList<XYChart.Data<Number, Number>>);
        for (double i = 0; i <= a; i+= 5e-3){
            y = Math.sqrt ((1 - i*i/a*a) * b*b);
            System.out.println(i);
            System.out.println(y);
            list.getData().addAll(new XYChart.Data(-i, y), new XYChart.Data(i, -y));
        }
        double i = a;
        y = Math.sqrt ((1 - i*i/a*a) * b*b);
        list.getData().addAll(new XYChart.Data(-i, y),new XYChart.Data(i, -y));
        return list;
    }

}


