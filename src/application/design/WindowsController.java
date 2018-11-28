package application.design;

import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class WindowsController {
    public static Stage getStage(Node node) {
        return (Stage) node.getScene().getWindow();
    }

    public static void closeStage(Node node) {
        getStage(node).close();
    }
    public static void showAlert(String title, String header, String content){
    	Alert alert = new Alert(AlertType.INFORMATION);
    	alert.setTitle(title);
    	alert.setHeaderText(header);
    	alert.setContentText(content);
    	alert.showAndWait();
    }
}
