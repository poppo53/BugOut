package com.BowWowPow.GameWorld;

import java.util.Random;

import com.BowWowPow.BWPHelpers.AssetLoader;
import com.BowWowPow.GameObjects.Background;
import com.BowWowPow.GameObjects.Bird;
import com.BowWowPow.GameObjects.Dog;
import com.BowWowPow.GameObjects.Insect;
import com.BowWowPow.GameObjects.Pincher;
import com.BowWowPow.GameObjects.ScrollHandler;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;

public class GameWorld {
	private Dog dog;
	private Bird bird;
	
	private Pincher pinch;
	private Insect bug;
	private Background bg;
	private boolean isAlive = true;
	private ScrollHandler scroller;
	private Random r;
	public int screenHeight;
	private int pause;
	public static int score = 0;

	public enum GameState {
		READY, RUNNING, GAMEOVER,  HIGHSCORE
	}

	private GameState currentState;
	public int screenWidth;

	public GameWorld(int midPointY) {
		// TODO will need to change for our game
		screenHeight = midPointY * 2;
		dog = new Dog(50, midPointY+Background.FLOOR - 52, 51, 36);
		bird = new Bird(68, -12, 17, 12);
		pinch = new Pincher(48, -18,18, 12);
		
		bug = new Insect(68, -12, 17, 12);
		currentState = GameState.READY;

		bg = new Background();

		scroller = new ScrollHandler(midPointY + 66);
		r = new Random();

	}

	public void update(float delta) {

//		switch (currentState) {
//		case READY:
//			updateReady(delta);
//			break;
//			
//		case RUNNING:
//		default:
//			updateRunning(delta);
//			break;
//		}
		
		 switch (currentState) {
	        case READY:
	            updateReady(delta);
	            break;

	        case RUNNING:
	            updateRunning(delta);
	            break;
	        default:
	            break;
	        }	

	}

	private void updateReady(float delta) {
		// Do nothing for now
	}

	public void restart() {
		currentState = GameState.READY;
		score = 0;
		
		pause = 0;
		int value = r.nextInt(110);
		bird.reset(value);
		int value2 = r.nextInt(110);
		pinch.reset(value2);
		
		int value3 = r.nextInt(110);
		bug.reset(value3);
		currentState = GameState.READY;
	}

	public void updateRunning(float delta) {
		dog.update(delta);
		if (isAlive) {
			bird.update(delta);
		}
		// bird.update(delta);
		bg.update(delta);

		if (pause > 75 && isAlive) {
			pinch.update(delta);
			pause++;
		} else {
			pause++;
		}
		
		if( pause > 250 && isAlive){
			bug.update(delta);
		}

		if ((dog.collides(bird) || dog.collides(pinch) || dog.collides(bug)) && isAlive) {
			AssetLoader.dead.play();
			isAlive = false;
			System.out.println("Boom");
			bird.die();
			pinch.die();
			bug.die();
			currentState = GameState.GAMEOVER;
			
			//hi score code
			if (score > AssetLoader.getHighScore()) {
				AssetLoader.setHighScore(score);
	            currentState = GameState.HIGHSCORE;
	        }
			
		}

		if (bird.getY() > screenHeight - 62 && isAlive) {
			int value = r.nextInt(110);
			bird.reset(value);
			setScore(getScore() - 5);
			
			AssetLoader.splat.play(0.1f);
		} else if (bird.getBeenClicked()) {

			int value = r.nextInt(110);
			bird.reset(value);
		}

		if (pinch.getY() > screenHeight - 62 && isAlive) {
			int value = r.nextInt(110);
			pinch.reset(value);
			setScore(getScore() - 5);
			
			AssetLoader.splat.play(0.1f);
		} else if (pinch.getBeenClicked()) {

			int value = r.nextInt(110);
			pinch.reset(value);
		}
		
		if (bug.getY() > screenHeight - 62 && isAlive) {
			int value = r.nextInt(110);
			bug.reset(value);
			setScore(getScore() - 5);
			AssetLoader.splat.play(0.1f);
		} else if (bug.getBeenClicked()) {

			int value = r.nextInt(110);
			bug.reset(value);
		}

		scroller.update(delta);
		// System.out.println("GameWorld - update");
	}

	public boolean isGameOver() {
		return currentState == GameState.GAMEOVER;
	}

	public boolean isReady() {
		return currentState == GameState.READY;
	}

	public void start() {
		isAlive = true;
		currentState = GameState.RUNNING;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public Bird getBird() {
		return bird;
	}

	public Pincher getpinch() {
		return pinch;
	}

	public Dog getDog() {
		return dog;
	}

	public Insect getBug(){
		return bug;
	}
	public ScrollHandler getScroller() {
		return scroller;
	}

	public Background getBackground() {
		// TODO Auto-generated method stub
		return bg;
	}
	
	public boolean isHighScore() {
	    return currentState == GameState.HIGHSCORE;
	}
}
