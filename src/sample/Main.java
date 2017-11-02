package sample;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.stage.Stage;


public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    Label points = new Label("Points");
    Label mult  = new Label("Multiplier");
    TextField pointNo = new TextField();
    TextField multNo = new TextField();
    Button btn = new Button("Draw");
    @Override
    public void start(Stage primaryStage) {
        Slider slider = new Slider(50,1000,500);

        Slider slider2 = new Slider(0,1000,500);
        slider.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> arg0, Number oldValue, Number newValue)
            {
                pointNo.setText(String.valueOf(newValue));

            }
        });
        slider2.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> arg0, Number oldValue, Number newValue)
            {
                multNo.setText(String.valueOf(newValue));

            }
        });
        GridPane gridPane = new GridPane();
        gridPane.add(points,0,0);
        gridPane.add(pointNo,1,0);
        gridPane.add(slider,2,0);
        gridPane.add(mult,0,1);
        gridPane.add(multNo,1,1);
        gridPane.add(slider2,2,1);
        gridPane.add(btn,0,2);

        gridPane.setVgap(5);
        gridPane.setHgap(5);
        multNo.setPrefColumnCount(5);
        pointNo.setPrefColumnCount(5);

        gridPane.setAlignment(Pos.CENTER);

        Pane pane = new Pane();

        btn.setOnAction(event -> {
            double N= Double.parseDouble(pointNo.getText());
            double k =Double.parseDouble(multNo.getText());
            int radius = N<300?200:270;
            double centerx = primaryStage.getWidth()/2;
            double centery = primaryStage.getHeight()/2;
            pane.getChildren().clear();
            Circle c = new Circle(centerx,centery,radius);
            for (int i = 0; i < N; i++) {
                Line line=new Line((centerx + radius * Math.cos(Math.PI * 2 * i / N)),
                        (centery + radius * Math.sin(Math.PI * 2 * i / N)),
                        (centerx + radius * Math.cos(Math.PI * 2 *k* i / N)),
                        (centery + radius * Math.sin(Math.PI * 2 *k* i / N))
                );
                //line.setStroke(Color.rgb(2,100,100));
                line.setStroke(Color.rgb(0,0,0));
                pane.getChildren().addAll(line);
            }
            c.setStroke(Color.BLACK);
            c.setStrokeWidth(5);
            c.setFill(null);
            pane.getChildren().addAll(c);
        });

        BorderPane borderPane= new BorderPane();

        borderPane.setTop(gridPane);
        borderPane.setCenter(pane);
        //borderPane.setPadding(new Insets(5,5,5,5));

        Scene scene = new Scene(borderPane,700,700);
        primaryStage.setScene(scene);
        primaryStage.show();



    }
}
