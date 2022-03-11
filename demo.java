import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.*;
import javafx.scene.media.AudioClip;
import java.net.URL;
import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.event.*;
import javafx.scene.input.*;
import javafx.scene.text.*;
import javafx.application.Application;
import javafx.stage.Stage;

public class demo extends Application implements EventHandler<InputEvent>{
	GraphicsContext gc;
	int speed = 0;
	int x = 0;
	int y = 0;
	AnimateObjects animate;
	Image trooper;
	Canvas canvas;
	URL resource;
	AudioClip clip;

	public static void main(String[]args){
		launch();
	}

	public void start(Stage stage){
		speed = 10;
		x = 180;
		y = 100;
		stage.setTitle("Final Project Title");
		Group root = new Group();
		canvas = new Canvas(800, 400);
		root.getChildren().add(canvas);
		Scene scene = new Scene(root);
		stage.setScene(scene);
		scene.addEventHandler(KeyEvent.KEY_PRESSED,this);
		gc = canvas.getGraphicsContext2D();
		trooper = new Image("trooper.jpg");
		gc.drawImage(trooper, 180, 100);
		animate = new AnimateObjects();
		animate.start();
		resource = getClass().getResource("test.wav");
		clip = new AudioClip(resource.toString());
		clip.play();
		stage.show();
	}

	public class AnimateObjects extends AnimationTimer{
		public void handle(long now){
			gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
			//x += speed;
			gc.drawImage(trooper, x, y);
			Rectangle2D r1 = new Rectangle2D(0,0,0,canvas.getHeight());
			Rectangle2D r2 = new Rectangle2D(canvas.getWidth(),0,0,canvas.getHeight());
			Rectangle2D rT = new Rectangle2D(x, y, trooper.getWidth(), trooper.getHeight());
			if(rT.intersects(r1) || rT.intersects(r2)){
				x*=-1;
				clip.play();
			}
			if (x>-50) {
			//all the code for drawing your image on the screen goes here
			}
			else{
			// we are going to display Game over if the user moves 50 pixels to the left
			gc.setFill( Color.YELLOW); //Fills the text in yellow
			gc.setStroke( Color.BLACK ); //Changes the outline the black
			gc.setLineWidth(1); //How big the black lines will be
			Font font = Font.font( "Arial",FontWeight.NORMAL, 48 );
			gc.setFont( font );
			gc.fillText( "Game Over", 100, 50 ); //draws the yellow part of the text
			gc.strokeText( "Game Over", 100, 50 ); //draws the outline part of the text
			gc.clearRect(x, y, trooper.getWidth(), trooper.getHeight());
			}
		}
	}
	public void handle(final InputEvent event){
		if(((KeyEvent)event).getCode() == KeyCode.LEFT)
			x-=10;
		if(((KeyEvent)event).getCode() == KeyCode.RIGHT)
			x+=10;
		if(((KeyEvent)event).getCode() == KeyCode.DOWN)
			y+=7;
		if(((KeyEvent)event).getCode() == KeyCode.UP)
			y-=7;
	}
}