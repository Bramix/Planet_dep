package application.design.component;

import java.io.IOException;

import application.Main;
import com.sun.java.swing.plaf.windows.WindowsOptionPaneUI;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class WindowsWorker extends Stage {
    final private String pathIcon = "/application/design/image/logo.png";

    public WindowsWorker(String path, boolean modal) {
        loadFXML(path, modal);
        //getIcons().add(new Image(pathIcon));
        setTitle("E");
    }


    private void loadFXML(String path, boolean modal) {
        Parent root = null;
        try {
            root = FXMLLoader.load(Main.class.getResource(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
        setScene(new Scene(root));
        if (modal) {
            //initModality(Modality.APPLICATION_MODAL);
            setResizable(false);
        }
    }
    
}
