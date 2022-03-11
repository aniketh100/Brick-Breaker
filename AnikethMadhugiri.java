//Aniketh Madhugiri

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

public class AnikethMadhugiri extends Application implements EventHandler<InputEvent>{
	GraphicsContext gc;

	ArrayList <Image> red;
	ArrayList <Image> yellow;
	ArrayList <Image> blue;
	ArrayList <Image> green;
	ArrayList <Image> white;

	int speed = 0;
	int pCount = 0;
	double bXSpeed = 0;
	double bYSpeed = 0;
	int x = 500;
	int xBall = 550;
	int y = 700;
	int bX = 120;
	int bY = 50;
	int yBall = 500;
	int life = 0;
	int check = 1;
	int countRed = 0;
	int countYellow = 0;
	int countBlue = 0;
	int totalCount = 0;
	int score = 0;

	Rectangle2D rec;

	AnimateObjects animate;

	Image bg;
	Image paddle;
	Image ball;
	Image redBrick;
	Image yellowBrick;
	Image blueBrick;
	Image greenBrick;
	Image whiteBrick;
	Image cracked1;
	Image cracked2;
	Image cracked3;
	Image crackedR;
	Image blank;

	Canvas canvas;
	boolean hasWon = false;

	URL pBeep;
	URL bBeep;
	URL over;
	URL bounce;
	URL start;
	URL gone;
	URL space;
	URL done;

	AudioClip clip1;
	AudioClip clip2;
	AudioClip clip3;
	AudioClip clip4;
	AudioClip begin;
	AudioClip death;
	AudioClip play;
	AudioClip win;

	difficulty length = new difficulty();
	double pLength = 0;
	double factorSpeed = 0;
	boolean gameOver = false;
	boolean setDiff = false;

	public static void main(String[]args){
		launch();
	}

	public void start(Stage stage){
		check = 1;
		speed = 5;
		bXSpeed = 0;
		bYSpeed = 0;

		red = new ArrayList<Image>();
		yellow = new ArrayList<Image>();
		blue = new ArrayList<Image>();
		green = new ArrayList<Image>();
		white = new ArrayList<Image>();

		stage.setTitle("Atari Breakout");
		Group root = new Group();
		canvas = new Canvas(1200, 800);
		root.getChildren().add(canvas);
		Scene scene = new Scene(root);
		stage.setScene(scene);
		scene.addEventHandler(KeyEvent.KEY_PRESSED,this);
		gc = canvas.getGraphicsContext2D();
		bg = new Image("bg.jpg");
		gc.drawImage(bg, 0, 0);
		pBeep = getClass().getResource("pBeep.wav");
		clip1 = new AudioClip(pBeep.toString());
		bBeep = getClass().getResource("bBeep.wav");
		clip2 = new AudioClip(bBeep.toString());
		over = getClass().getResource("gameOver.mp3");
		clip3 = new AudioClip(over.toString());
		bounce = getClass().getResource("bounce.wav");
		clip4 = new AudioClip(bounce.toString());
		start = getClass().getResource("gameBegin.wav");
		begin = new AudioClip(start.toString());
		gone = getClass().getResource("death.wav");
		death = new AudioClip(gone.toString());
		space = getClass().getResource("space.mp3");
		play = new AudioClip(space.toString());
		done = getClass().getResource("win.mp3");
		win = new AudioClip(done.toString());
		paddle = new Image("paddle.jpg");
		ball = new Image("ball.png");
		redBrick = new Image("RBrick.png");
		yellowBrick = new Image("YBrick.png");
		blueBrick = new Image("BBrick.png");
		greenBrick = new Image("GBrick.png");
		whiteBrick = new Image("WBrick.png");
		cracked1 = new Image("CrackedRBrick.png");
		cracked2 = new Image("CrackedYBrick.png");
		cracked3 = new Image("CrackedBBrick.png");
		crackedR = new Image("CrackedRBrick2.png");
		for(int i = 0; i < 10; i++){
			red.add(redBrick);
			yellow.add(yellowBrick);
			blue.add(blueBrick);
			green.add(greenBrick);
			white.add(whiteBrick);
		}
		begin.play();
		animate = new AnimateObjects();
		animate.start();
		stage.show();
	}
	public class AnimateObjects extends AnimationTimer{
		public void handle(long now){
			totalCount = 0;

			if(life == 0){
				if(check == 1){
					gameOver = true;
					gc.setFill(Color.WHITE);
					gc.setStroke(Color.GRAY);
					gc.setLineWidth(1);
					Font font = Font.font("3Times New Roman", FontWeight.NORMAL, 56);
					gc.setFont(font);
					gc.fillText("WELCOME TO ATARI BREAKOUT!",150, 170);
					gc.strokeText("WELCOME TO ATARI BREAKOUT!", 150, 170);
					font = Font.font("Terminal", FontWeight.NORMAL, 25);
					gc.setFont(font);
					gc.fillText("		 Rules : ", 100, 200);
					gc.fillText("		 1) Use LEFT and RIGHT to move the paddle. ", 100, 230);
					gc.fillText("		 2) WHITE and GREEN Bricks take one hit to break. ", 100, 260);
					gc.fillText("		 3) BLUE and YELLOW Bricks take two.", 100, 290);
					gc.fillText("		 4) RED Bricks take three. ", 100, 320);
					gc.fillText("		 5) Breaking your first RED and YELLOW bricks increase the speed of the ball. ", 100, 350);
					gc.fillText("		 6) Your game ends when you fail to get the ball. Have fun! ", 100, 380);
					gc.fillText("		 7) White (25 PTS), Green (50 PTS), Blue (100 PTS), Yellow (200 PTS), Red (500 PTS)	.", 100, 410);
					gc.fillText("Select Difficulty",225, 550);
					gc.strokeText("Select Difficulty",225, 550);
					gc.fillText("Press E for Easy", 225, 600);
					gc.fillText("Press N for Normal", 225, 625);
					gc.fillText("Press H for Hard", 225, 650);
					gc.fillText("Press I for Impossible", 225, 675);
					gc.fillText("And then press SPACE to continue", 225, 700);
					bYSpeed = 4 * factorSpeed;
				}
				if((check == 0) && (hasWon == true)){
					gameOver = true;
					gc.setFill(Color.CYAN);
					int x2 = x;
					int y2 = y;
					gc.clearRect(xBall,yBall, ball.getWidth(), ball.getHeight());
					gc.drawImage(bg,0,0);
					//gc.fillRect(x2, y2, pLength, paddle.getHeight());
					speed = 0;
					gc.setFill(Color.GREEN);
					gc.setStroke(Color.WHITE);
					gc.setLineWidth(1);
					Font font = Font.font("Arial", FontWeight.NORMAL, 60);
					gc.setFont(font);
					gc.fillText("YOU WIN!",500, 400);
					gc.strokeText("YOU WIN!", 500, 400);
					font = Font.font("Arial", FontWeight.NORMAL, 25);
					gc.setFont(font);
					gc.fillText("Your final score : "+score, 500, 450);
					gc.strokeText("Your final score : "+score, 500, 450);
					gc.fillText("Press SPACE to play again",500, 500);
					gc.strokeText("Press SPACE to play again",500, 500);
					for(int i = 0;i < red.size(); i++){
						red.remove(i);
					}
					for(int i = 0; i < yellow.size(); i++){
						yellow.remove(i);
					}
					for(int i = 0; i < blue.size(); i++){
						blue.remove(i);
					}
					for(int i = 0; i < green.size(); i++){
						green.remove(i);
					}
					for(int i = 0; i < white.size(); i++){
						white.remove(i);
					}
					for(int i = 0; i < 10; i++){
						red.add(redBrick);
						yellow.add(yellowBrick);
						blue.add(blueBrick);
						green.add(greenBrick);
						white.add(whiteBrick);
					}
				}

				else if ((check == 0) && (hasWon == false)){
					setDiff = false;
					gameOver = true;
					gc.setFill(Color.CYAN);
					int x2 = x;
					int y2 = y;
					gc.clearRect(xBall,yBall, ball.getWidth(), ball.getHeight());
					gc.drawImage(bg,0,0);
					//gc.fillRect(x2, y2, pLength, paddle.getHeight());
					gc.setFill(Color.RED);
					gc.setStroke(Color.RED);
					gc.setLineWidth(1);
					Font font = Font.font("Times New Roman", FontWeight.NORMAL, 56);
					gc.setFont(font);
					gc.fillText("GAME OVER!", 400, 350);
					gc.strokeText("GAME OVER!", 400, 350);
					font = Font.font("Times New Roman", FontWeight.NORMAL, 25);
					gc.setFont(font);
					gc.fillText("Your final Score : "+score, 425, 400);
					gc.strokeText("Your final Score : "+score, 425, 400);
					gc.fillText("Try another difficulty level : ", 425, 450);
					gc.fillText("E for Easy, N for Normal ", 425, 500);
					gc.fillText("H for Hard, and I for Impossible", 425, 525);
					gc.fillText("Press SPACE to play again",425, 550);
					gc.strokeText("Press SPACE to play again",425, 550);
					speed = 0;
					for(int i = 0;i < red.size(); i++){
						red.remove(i);
					}
					for(int i = 0; i < yellow.size(); i++){
						yellow.remove(i);
					}
					for(int i = 0; i < blue.size(); i++){
						blue.remove(i);
					}
					for(int i = 0; i < green.size(); i++){
						green.remove(i);
					}
					for(int i = 0; i < white.size(); i++){
						white.remove(i);
					}
					for(int i = 0; i < 10; i++){
						red.add(redBrick);
						yellow.add(yellowBrick);
						blue.add(blueBrick);
						green.add(greenBrick);
						white.add(whiteBrick);
					}
				}
			}
			else if(life == 1){
				gameOver = false;
				gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
				gc.setFill(Color.CYAN);
				xBall += bXSpeed;
				yBall += bYSpeed;
				gc.drawImage(bg,0,0);
				gc.fillRect(x, y, pLength, paddle.getHeight());
				gc.drawImage(ball, xBall, yBall);
				gc.setFill(Color.ORANGE);
				Font font = Font.font("Courier New", FontWeight.NORMAL, 25);
				gc.setFont(font);
				gc.setLineWidth(1);
				gc.fillText("Score : "+score, 50, 600);
				//gc.fillText("Bricks Smashed : "+totalCount, 50, 550);
				for(int i = 0;i < red.size(); i++){
					gc.drawImage(red.get(i), bX*i, 0);
				}
				for(int i = 0;i < yellow.size(); i++){
					gc.drawImage(yellow.get(i), bX*i, 50);
				}
				for(int i = 0;i < blue.size(); i++){
					gc.drawImage(blue.get(i), bX*i, 100);
				}
				for(int i = 0;i < green.size(); i++){
					gc.drawImage(green.get(i), bX*i, 150);
				}
				for(int i = 0;i < white.size(); i++){
					gc.drawImage(white.get(i), bX*i, 200);
				}
				Rectangle2D r1 = new Rectangle2D(0,0,0,canvas.getHeight());
				Rectangle2D r2 = new Rectangle2D(canvas.getWidth(),0,0,canvas.getHeight());
				Rectangle2D r3 = new Rectangle2D(0,0,canvas.getWidth(),0);
				Rectangle2D r4 = new Rectangle2D(0,canvas.getHeight(),canvas.getWidth(),0);
				Rectangle2D rT = new Rectangle2D(x, y, pLength, paddle.getHeight());
				Rectangle2D rP = new Rectangle2D(x + pLength, y, 0, paddle.getHeight());
				Rectangle2D rB = new Rectangle2D(xBall, yBall, ball.getWidth(), ball.getHeight());
				if(rP.intersects(r2)){
					x -= 40;
				}

				for(int i = 0; i < red.size(); i++){
					rec = new Rectangle2D(bX*i, 0, redBrick.getWidth(), redBrick.getHeight());
					if(rB.intersects(rec) && (red.get(i) != blank)){
						clip2.play();
							if((red.get(i) != cracked1) && (red.get(i) != crackedR)){
								red.set(i,cracked1);
							}
							else if((red.get(i) == cracked1) && (red.get(i) != crackedR)){
								red.set(i, crackedR);
							}
							else{
								//totalCount++;
								score += 300;
								countRed++;
								red.set(i, blank);
								if(countRed == 1){
									bYSpeed *= 1.5;
									bXSpeed *= 1.5;
								}
							}
						bYSpeed *= -1;
					}
				}
				for(int i = 0; i < yellow.size(); i++){
					Rectangle2D rec = new Rectangle2D(bX*i, 50, yellowBrick.getWidth(), yellowBrick.getHeight());
					if(rB.intersects(rec) && (yellow.get(i) != blank)){
						clip2.play();
						if(yellow.get(i) != cracked2){
						yellow.set(i, cracked2);
						}
						else{
							score += 200;
							countYellow++;
							//totalCount++;
							yellow.set(i, blank);
							if(countYellow == 1){
								bXSpeed *= 1.5;
								bYSpeed *= 1.5;
							}
						}
						bYSpeed *= -1;
					}
				}
				for(int i = 0; i < blue.size(); i++){
					Rectangle2D rec = new Rectangle2D(bX*i, 100, blueBrick.getWidth(), blueBrick.getHeight());
					if(rB.intersects(rec) && (blue.get(i) != blank)){
						clip2.play();
						if(blue.get(i) != cracked3){
							blue.set(i, cracked3);
						}
						else{
							score += 100;
							//totalCount++;
							blue.set(i, blank);
						}
						bYSpeed *= -1;
					}
				}
				for(int i = 0; i < green.size(); i++){
					Rectangle2D rec = new Rectangle2D(bX*i, 150, greenBrick.getWidth(), greenBrick.getHeight());
					if(rB.intersects(rec) && (green.get(i) != blank)){
						clip2.play();
						score += 50;
						green.set(i, blank);
						//totalCount++;
						bYSpeed *= -1;
					}
				}
				for(int i = 0; i < white.size(); i++){
					Rectangle2D rec = new Rectangle2D(bX*i, 200, whiteBrick.getWidth(), whiteBrick.getHeight());
					if(rB.intersects(rec) && (white.get(i) != blank)){
						clip2.play();
						score += 25;
						white.set(i,blank);
						//totalCount++;
						bYSpeed *= -1;
					}
				}
				if(rB.intersects(x ,y, pLength/2 ,paddle.getHeight())){
					clip1.play();
					bYSpeed *= -1;
					if(bXSpeed < 0){
						bXSpeed *= 1;
					}
					else if(bXSpeed > 0){
						bXSpeed *= -1;
					}
					pCount++;
					if(pCount == 1){
						bXSpeed = 4 * factorSpeed;
					}
				}
				else if(rB.intersects(x + (pLength/2), y, pLength/2, paddle.getHeight())){
					clip1.play();
					bYSpeed *= -1;
					if(bXSpeed < 0){
						bXSpeed *= -1;
					}
					else if(bXSpeed > 0){
						bXSpeed *= 1;
					}
					pCount++;
					if(pCount == 1){
						bXSpeed = 4 * factorSpeed;
					}
				}
				if(rB.intersects(r3)){
					clip4.play();
					bYSpeed *= -1;
				}
				if(rB.intersects(r1)){
					clip4.play();
					bXSpeed *= -1;
				}
				if(rB.intersects(r2)){
					clip4.play();
					bXSpeed *= -1;
				}
				if(rB.intersects(r4)){
					death.play();
				}
				if(rB.intersects(r4)){
					life = 0;
					xBall = 550;
					yBall = 500;
					bXSpeed = 0;
					pCount = 0;
					check = 0;
					clip3.play();
				}
				for(int i = 0; i < 10; i++){
					if(red.get(i) == blank){
						totalCount++;
					}
					if(yellow.get(i) == blank){
						totalCount++;
					}
					if(blue.get(i) == blank){
						totalCount++;
					}
					if(green.get(i) == blank){
						totalCount++;
					}
					if(white.get(i) == blank){
						totalCount++;
					}
				}
				if(totalCount == 50){
					hasWon = true;
				}
				if(hasWon == true){
					life = 0;
					check = 0;
					pCount = 0;
					win.play();
				}
			}
		}
	}
	public void handle(final InputEvent event){
		if(((KeyEvent)event).getCode() == KeyCode.LEFT && x >= 0)
			x-=40;
		if(((KeyEvent)event).getCode() == KeyCode.RIGHT && (x+pLength) <= canvas.getWidth())
			x+=40;
		if(((KeyEvent)event).getCode() == KeyCode.SPACE){
			if(gameOver == true){
				pCount = 0;
				play.play();
				life = 1;
				score = 0;
				hasWon = false;
				x = 500;
				y = 700;
				xBall = 550;
				yBall = 500;
				bYSpeed = 4 * factorSpeed;
			}
		}
		if(((KeyEvent)event).getCode() == KeyCode.E){
			if(setDiff == false){
				pLength = length.setLength(event);
				factorSpeed = length.getFactor(event);
				setDiff = true;
			}
		}
		if(((KeyEvent)event).getCode() == KeyCode.N){
			if(setDiff == false){
				pLength = length.setLength(event);
				factorSpeed = length.getFactor(event);
				setDiff = true;
			}
		}
		if(((KeyEvent)event).getCode() == KeyCode.H){
			if(setDiff == false){
				pLength = length.setLength(event);
				factorSpeed = length.getFactor(event);
				setDiff = true;
			}
		}
		if(((KeyEvent)event).getCode() == KeyCode.I){
			if(setDiff == false){
				pLength = length.setLength(event);
				factorSpeed = length.getFactor(event);
				setDiff = true;
			}
		}
		else{
			if(setDiff == false){
				pLength = length.setLength(event);
				factorSpeed = length.getFactor(event);
				setDiff = true;
			}
		}
	}
}