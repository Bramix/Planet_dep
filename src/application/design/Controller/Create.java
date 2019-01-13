package application.design.Controller;

import java.net.URL;
import java.util.ResourceBundle;

import application.MathLogic.Lines;
import application.design.Const;
import application.design.component.ChartWorker;
import javafx.animation.AnimationTimer;
import javafx.animation.Interpolator;
import javafx.animation.PathTransition;
import javafx.animation.Timeline;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.input.ScrollEvent;
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

	private Sphere sphere;
	private final Sphere star = new Sphere (45);

	@FXML
	private TextField x, y ,a , b , c, xC, yC;
	@FXML
	private Label e, center, part1,part2, com, labelA;
	@FXML
	private ComboBox <String> comboBox ;
	@FXML
	private void getEath(){
		double xR = Double.parseDouble(x.getText());
		double yR = Double.parseDouble(y.getText());
		Camera camera = new PerspectiveCamera(true);
		camera.setNearClip(1);
		camera.setFarClip(10000);
		camera.translateZProperty().set(-1000);
		prepareEarth(25, 1 , 1, 1);
		prepareStar();
		//sphere.setRotationAxis(Rotate.Y_AXIS);
		//sphere.setMaterial(earthMaterial);
		Ellipse ellipseEarth = new Ellipse( xR*85, yR * 85);
		ellipseEarth.setStroke(Color.RED);
		ellipseEarth.setFill(Color.GRAY);
		PathTransition transitionEarth = new PathTransition();
		transitionEarth.setPath(ellipseEarth);
		transitionEarth.setNode(sphere);
		transitionEarth.setInterpolator(Interpolator.LINEAR);
		if (xR > yR)
			transitionEarth.setDuration(Duration.seconds(5));
		if (xR == yR)
			transitionEarth.setDuration(Duration.seconds(10));
		if (xR < yR)
			transitionEarth.setDuration(Duration.seconds(15));
		transitionEarth.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);
		transitionEarth.setCycleCount(Timeline.INDEFINITE);
		transitionEarth.play();

		prepareAnimation();
		Circle circle = new Circle();
		Group world = new Group();
		if (xR >= 4 || yR >= 4 )
			world.translateZProperty().set(world.getTranslateZ() + 1500 * Math.max(xR, yR)/4 );
		world.getChildren().addAll(sphere,circle, ellipseEarth, star);

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
	private void getOnlyEath(){
		double xR = Double.parseDouble(a.getText());
		double yR = Double.parseDouble(b.getText());
		double zR = Double.parseDouble(c.getText());
		Camera camera = new PerspectiveCamera(true);
		camera.setNearClip(1);
		camera.setFarClip(10000);
		camera.translateZProperty().set(-1000);
		prepareEarth(100, xR,yR,zR);
		prepareAnimation();
		Group world = new Group(sphere);
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
	private void setCombo (){
		if (comboBox.getSelectionModel().getSelectedItem().equals(Const.par) && !comboBox.getSelectionModel().getSelectedItem().isEmpty()){
			labelA.setText("p =");
			y.setVisible(false);
		}
		if(!comboBox.getSelectionModel().getSelectedItem().equals(Const.par) && !comboBox.getSelectionModel().getSelectedItem().isEmpty()){
			labelA.setText("a =");
			y.setVisible(true);
		}
		xC.clear();
		yC.clear();
	}
	@FXML
	private void derermineE(){
		e.setVisible(true);xC.setVisible(true);yC.setVisible(true);center.setVisible(true);part1.setVisible(true);part2.setVisible(true); com.setVisible(true);
		double a, b = 0, answ = -1, yCenter = 0, xCenter = 0, K = 0;
		a = Double.parseDouble(x.getText());
		if (!comboBox.getSelectionModel().getSelectedItem().equals(Const.par))
			b = Double.parseDouble(y.getText());
		if (xC.getText().isEmpty()){
			if (comboBox.getSelectionModel().getSelectedItem().equals(Const.par)){
				xC.setText("0");
				yC.setText("0");
			}
			else  {
				xC.setText(Double.toString(a));
				yC.setText("0");
			}
		}
		if (comboBox.getSelectionModel().getSelectedItem().equals(Const.el)) {
			answ = Math.sqrt(1 - Math.min(a, b) * Math.min(a, b) / (Math.max(a, b) * Math.max(a, b)));
			K = Lines.elipse(a,b,Double.parseDouble(xC.getText()),Double.parseDouble(yC.getText()));
			xCenter = Lines.elX(a,b,Double.parseDouble(xC.getText()),Double.parseDouble(yC.getText()));
			yCenter = Lines.elY(a,b,Double.parseDouble(xC.getText()),Double.parseDouble(yC.getText()));
		}
		if (comboBox.getSelectionModel().getSelectedItem().equals(Const.gip)){
			answ = Math.sqrt(a * a + b * b) / a;
			K = Lines.elipse(a,b,Double.parseDouble(xC.getText()),Double.parseDouble(yC.getText()));
			xCenter = Lines.hipX(a,b,Double.parseDouble(xC.getText()),Double.parseDouble(yC.getText()));
			yCenter = Lines.hipY(a,b,Double.parseDouble(xC.getText()),Double.parseDouble(yC.getText()));
		}
		if (comboBox.getSelectionModel().getSelectedItem().equals(Const.par)) {
			answ = 1;
			K = Lines.parable(a, Double.parseDouble(xC.getText()),Double.parseDouble(yC.getText()));
			xCenter = Lines.parX(a,Double.parseDouble(yC.getText()));
			yCenter = Lines.parY(a,Double.parseDouble(xC.getText()));
		}
		e.setText(String.format("e = %.3f",  answ));
		part2.setText(String.format(") = %.3f ", K));
		center.setText(String.format("Центр кривизни : ( %.3f; %.3f )", xCenter, yCenter));
	}


	private void prepareAnimation() {
		AnimationTimer timer =  new AnimationTimer() {
			@Override
			public void handle(long now) {
				sphere.rotateProperty().set(sphere.getRotate() + 0.1);
				star.rotateProperty().set(star.getRotate() + 0.14);
		}
		};
		timer.start();
	}

	private Node prepareEarth(double size, double x, double y, double z) {
		sphere = new Sphere(size);
		sphere.setScaleX(x); sphere.setScaleY(y); sphere.setScaleZ(z);
		PhongMaterial earthMaterial = new PhongMaterial();
		earthMaterial.setDiffuseMap(new Image(getClass().getResourceAsStream("/resources/earth/earth-d.jpg")));
		earthMaterial.setSelfIlluminationMap(new Image(getClass().getResourceAsStream("/resources/earth/earth-l.jpg")));
		earthMaterial.setSpecularMap(new Image(getClass().getResourceAsStream("/resources/earth/earth-s.jpg")));
		earthMaterial.setBumpMap(new Image(getClass().getResourceAsStream("/resources/earth/earth-n.jpg")));
		sphere.setRotationAxis(Rotate.Y_AXIS);
		sphere.setMaterial(earthMaterial);
		return sphere;
	}
	private void prepareStar() {
		PhongMaterial earthMaterial = new PhongMaterial();
		earthMaterial.setDiffuseMap(new Image(getClass().getResourceAsStream("/resources/star/1xb.jpg")));
		//earthMaterial.setDiffuseColor(Color.RED);
		//earthMaterial.setSpecularColor(Color.RED);
		//earthMaterial.setSelfIlluminationMap(new Image(getClass().getResourceAsStream("/resources/earth/earth-l.jpg")));
		//earthMaterial.setSpecularMap(new Image(getClass().getResourceAsStream("/resources/earth/earth-s.jpg")));
		//earthMaterial.setBumpMap(new Image(getClass().getResourceAsStream("/resources/earth/earth-n.jpg")));
		star.setRotationAxis(Rotate.Y_AXIS);
		star.setMaterial(earthMaterial);
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
		comboBox.setItems(Const.list);

	}
}
