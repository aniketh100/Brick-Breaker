import java.util.ArrayList;
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

public class difficulty{

	private double pLength;
	public double sFactor;

	public difficulty(){

		pLength = 200;
		sFactor = 1.1;
	}

	public double setLength(final InputEvent event){
		if(((KeyEvent)event).getCode() == KeyCode.N){
			pLength = 200;
			return pLength;
		}
		else if(((KeyEvent)event).getCode() == KeyCode.H){
			pLength = 155;
			return pLength;
		}
		else if(((KeyEvent)event).getCode() == KeyCode.I){
			pLength = 100;
			return pLength;
		}
		else if(((KeyEvent)event).getCode() == KeyCode.E){
			pLength = 350;
			return pLength;
		}
		else{
			return pLength;
		}
	}
	public double getFactor(final InputEvent event){
		if(((KeyEvent)event).getCode() == KeyCode.N){
			sFactor = 1.1;
			return sFactor;
		}
		else if(((KeyEvent)event).getCode() == KeyCode.H){
			sFactor = 1.25;
			return sFactor;
		}
		else if(((KeyEvent)event).getCode() == KeyCode.I){
			sFactor = 1.5;
			return sFactor;
		}
		else if(((KeyEvent)event).getCode() == KeyCode.E){
			sFactor = 1;
			return sFactor;
		}
		else{
			//sFactor = 1.1;
			return sFactor;
		}
	}
}
