package application.design.Controller;

import java.net.URL;
import java.util.ResourceBundle;

import application.design.WindowsController;
import com.sun.java.swing.plaf.windows.WindowsCheckBoxMenuItemUI;
import javafx.animation.AnimationTimer;
import javafx.animation.Interpolator;
import javafx.animation.PathTransition;
import javafx.animation.Timeline;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.*;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.effect.Effect;
import javafx.scene.image.Image;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Sphere;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;
import javafx.util.Duration;


public class Create implements Initializable {
	private static final float WIDTH = 800;
	private static final float HEIGHT = 800;

	private double anchorX, anchorY;
	private double anchorAngleX = 0;
	private double anchorAngleY = 0;
	private final DoubleProperty angleX = new SimpleDoubleProperty(0);
	private final DoubleProperty angleY = new SimpleDoubleProperty(0);

	private final Sphere sphere = new Sphere(35);

	@FXML
	private TextArea x;
	@FXML
	private TextArea y;
	@FXML
	private TextArea z;
	@FXML
	private Label e;
	@FXML
	private ScrollPane scrollPane;


	@FXML
	private void getEath(){
		double xR = Double.parseDouble(x.getText());
		double yR = Double.parseDouble(y.getText());
		Camera camera = new PerspectiveCamera(true);
		camera.setNearClip(1);
		camera.setFarClip(10000);
		camera.translateZProperty().set(-1000);
		PhongMaterial earthMaterial = new PhongMaterial();
		earthMaterial.setDiffuseMap(new Image(getClass().getResourceAsStream("/resources/earth/earth-d.jpg")));
		earthMaterial.setSelfIlluminationMap(new Image(getClass().getResourceAsStream("/resources/earth/earth-l.jpg")));
		earthMaterial.setSpecularMap(new Image(getClass().getResourceAsStream("/resources/earth/earth-s.jpg")));
		earthMaterial.setBumpMap(new Image(getClass().getResourceAsStream("/resources/earth/earth-n.jpg")));
		sphere.setRotationAxis(Rotate.Y_AXIS);
		sphere.setMaterial(earthMaterial);
		Ellipse ellipseEarth = new Ellipse( xR*80, yR * 80);
		ellipseEarth.setStroke(Color.BLACK);
		ellipseEarth.setFill(Color.GRAY);
		PathTransition transitionEarth = new PathTransition();
		transitionEarth.setPath(ellipseEarth);
		transitionEarth.setNode(sphere);
		transitionEarth.setInterpolator(Interpolator.LINEAR);
		transitionEarth.setDuration(Duration.seconds(7.000017421));
		transitionEarth.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);
		transitionEarth.setCycleCount(Timeline.INDEFINITE);

		transitionEarth.play();

		prepareAnimation();
		Circle circle = new Circle();
		Group world = new Group();
		if (xR >= 5 || yR >= 5 )
			world.translateZProperty().set(world.getTranslateZ() + 1500 * Math.max(xR, yR)/5 );
		world.getChildren().addAll(prepareEarth(),circle, ellipseEarth);

		Scene scene = new Scene(world, WIDTH, HEIGHT, true);
		scene.setFill(Color.SILVER);
		scene.setCamera(camera);
		Stage primaryStage = new Stage();

		initMouseControl(world, scene, primaryStage);

		primaryStage.setTitle("Model of Eath");
		primaryStage.setScene(scene);
		primaryStage.show();


	}
	@FXML
	private void derermineE(){
		double a = Double.parseDouble(x.getText()); double b = Double.parseDouble(y.getText());
		double answ = Math.sqrt(Math.abs(a*a - b*b))/a;
		e.setText("e = " + Double.toString(answ));






	}
	private void prepareAnimation() {
		AnimationTimer timer = new AnimationTimer() {
			@Override
			public void handle(long now) {
				sphere.rotateProperty().set(sphere.getRotate() + 100);
			}
		};
		timer.start();
	}

	private Node prepareEarth() {
		PhongMaterial earthMaterial = new PhongMaterial();
		earthMaterial.setDiffuseMap(new Image(getClass().getResourceAsStream("/resources/earth/earth-d.jpg")));
		earthMaterial.setSelfIlluminationMap(new Image(getClass().getResourceAsStream("/resources/earth/earth-l.jpg")));
		earthMaterial.setSpecularMap(new Image(getClass().getResourceAsStream("/resources/earth/earth-s.jpg")));
		earthMaterial.setBumpMap(new Image(getClass().getResourceAsStream("/resources/earth/earth-n.jpg")));

		sphere.setRotationAxis(Rotate.Y_AXIS);
		sphere.setMaterial(earthMaterial);
		return sphere;
	}

	private void initMouseControl(Group group, Scene scene, Stage stage) {
		Rotate xRotate;
		Rotate yRotate;
		group.getTransforms().addAll(
				xRotate = new Rotate(0, Rotate.X_AXIS),
				yRotate = new Rotate(0, Rotate.Y_AXIS)
		);
		xRotate.angleProperty().bind(angleX);
		yRotate.angleProperty().bind(angleY);

		scene.setOnMousePressed(event -> {
			anchorX = event.getSceneX();
			anchorY = event.getSceneY();
			anchorAngleX = angleX.get();
			anchorAngleY = angleY.get();
		});

		scene.setOnMouseDragged(event -> {
			angleX.set(anchorAngleX - (anchorY - event.getSceneY()));
			angleY.set(anchorAngleY + anchorX - event.getSceneX());
		});

		stage.addEventHandler(ScrollEvent.SCROLL, event -> {
			double delta = event.getDeltaY();
			group.translateZProperty().set(group.getTranslateZ() + delta);
		});
	}
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

	}
}
