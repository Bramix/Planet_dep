package application.design;

//import com.sun.istack.internal.NotNull;

public enum PathWindows {
    Start("design/fxml/Start.fxml"), Create("design/fxml/Create.fxml"), One("design/fxml/One.fxml"),
    Conformity("design/fxml/Сonformity.fxml");


    private String path;

    public final String getPath() {
        return this.path;
    }

    PathWindows(String path) {
        this.path = path;
    }
}
