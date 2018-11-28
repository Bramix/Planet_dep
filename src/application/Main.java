package application;

import application.design.PathWindows;
import application.design.component.WindowsWorker;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        WindowsWorker windowsStart = new WindowsWorker(PathWindows.Create.getPath(), true);
      windowsStart.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
