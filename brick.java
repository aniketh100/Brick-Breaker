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

public class brick{

	private ArrayList <Rectangle2D> red;
	private ArrayList <Rectangle2D> yellow;
	private ArrayList <Rectangle2D> blue;
	private ArrayList <Rectangle2D> green;
	private ArrayList <Rectangle2D> white;

	public brick(){
		red = new ArrayList <>();
		yellow = new ArrayList <>();
		blue = new ArrayList <>();
		green = new ArrayList <>();
		white = new ArrayList <>();
	}
	public ArrayList <Rectangle2D> getRed(int len, int size, int ph){
		for(int i = 0; i < len; i++){
			red.add(new Rectangle2D(ph,0,size,5));
			ph += size + 1;
		}
		return red;
	}
	public ArrayList <Rectangle2D> getYellow(int len, int size, int ph){
		for(int i = 0; i < len; i++){
			yellow.add(new Rectangle2D(ph,6,size,5));
			ph += size + 1;
		}
		return yellow;
	}
	public ArrayList <Rectangle2D> getBlue(int len, int size, int ph){
		for(int i = 0; i < len; i++){
			blue.add(new Rectangle2D(ph,11,size,5));
			ph += size + 1;
		}
		return blue;
	}
	public ArrayList <Rectangle2D> getGreen(int len, int size, int ph){
		for(int i = 0; i < len; i++){
			yellow.add(new Rectangle2D(ph,16,size,5));
			ph += size + 1;
		}
		return green;
	}
	public ArrayList <Rectangle2D> getWhite(int len, int size, int ph){
		for(int i = 0; i < len; i++){
			white.add(new Rectangle2D(ph,21,size,5));
			ph += size + 1;
		}
		return white;
	}
}