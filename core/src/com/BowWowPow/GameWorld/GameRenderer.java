package com.BowWowPow.GameWorld;

import java.util.Random;

import com.BowWowPow.BWPHelpers.AssetLoader;
import com.BowWowPow.GameObjects.Background;
import com.BowWowPow.GameObjects.Bird;
import com.BowWowPow.GameObjects.Dog;
import com.BowWowPow.GameObjects.Grass;
import com.BowWowPow.GameObjects.Insect;
import com.BowWowPow.GameObjects.Pincher;
import com.BowWowPow.GameObjects.ScrollHandler;
import com.BowWowPow.GameObjects.Trees;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;

public class GameRenderer {

	private GameWorld myWorld;
	public static OrthographicCamera cam;
	private ShapeRenderer shapeRenderer;
	private SpriteBatch batcher;
	private int midPointY;
	private int gameHeight;
	// Game Objects
	private Dog dog;
	private Bird bird;

	private Pincher pinch;
	private Insect bug;
	private Background background;
	float lastFrame = 0;
	private ScrollHandler scroller;
	private Grass frontGrass, backGrass;
	// private Trees tree1, tree2, tree3;

	private TextureRegion bg, grass, birdTex;
	private Animation dogAnimation;
	// private TextureRegion trunk, treeTop;
	private TextureRegion groundEarth;
	public static boolean DebugMode = false;

	public GameRenderer(GameWorld world, int gameHeight, int midPointY) {
		myWorld = world;
		this.gameHeight = gameHeight;
		this.midPointY = midPointY;
		cam = new OrthographicCamera();
		cam.setToOrtho(true, 136, 204);

		batcher = new SpriteBatch();
		// Attach batcher to camera
		batcher.setProjectionMatrix(cam.combined);
		shapeRenderer = new ShapeRenderer();
		shapeRenderer.setProjectionMatrix(cam.combined);

		// Call helper methods to initialize instance variables
		initGameObjects();
		initAssets();
	}

	
	 private void initGameObjects() {
	        dog = myWorld.getDog();
	        bird = myWorld.getBird();
	        pinch = myWorld.getpinch();
	        bug = myWorld.getBug();
	        background = myWorld.getBackground();
	    }
	 
	 private void initAssets(){
		  bg = AssetLoader.bg;
	      grass = AssetLoader.grass;
	      dogAnimation = AssetLoader.dogAnimation;
	      groundEarth = AssetLoader.groundEarth;
	 }
	
	 public void render(float runTime) {
	        //System.out.println("GameRenderer - render");
	     // We will move these outside of the loop for performance later.
	         //Dog dog = myWorld.getDog();

	        // Fill the entire screen with black, to prevent potential flickering.
	        Gdx.gl.glClearColor(0, 0, 0, 1);
	        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

	        // Begin ShapeRenderer
	        shapeRenderer.begin(ShapeType.Filled);
	        
	        // Draw Background color
	        shapeRenderer.setColor(55 / 255.0f, 80 / 255.0f, 100 / 255.0f, 1);
	        shapeRenderer.rect(0, 0, 136, midPointY + 66);

	        // Draw Grass
	        shapeRenderer.setColor(111 / 255.0f, 186 / 255.0f, 45 / 255.0f, 1);
	        shapeRenderer.rect(0, midPointY + 66, 136, 11);

	        // Draw Dirt
	        shapeRenderer.setColor(147 / 255.0f, 80 / 255.0f, 27 / 255.0f, 1);
	        shapeRenderer.rect(0, midPointY + 77, 136, 52);
	        
	        // End ShapeRenderer
	        shapeRenderer.end();

	        // Begin SpriteBatch
	        batcher.begin();
	        // Disable transparency 
	        // This is good for performance when drawing images that do not require
	        // transparency.
	        
	        batcher.disableBlending();
	        batcher.draw(AssetLoader.bg, background.getX(), 0, ((1020 / 348) * gameHeight), gameHeight);

	        
	        // The bird needs transparency, so we enable that again.
	        batcher.enableBlending();

        	for (int i =0; i <10; i++){
        		batcher.draw(AssetLoader.cloud, background.getCloudX() + i*100 , (i*20) %50);
        	}
	        
        	int FLOOR = Background.FLOOR;
	        for (int i = 0; i < 10; i ++){
	        	batcher.draw(AssetLoader.tree, background.getTreeX() + i*100, midPointY+FLOOR-100,61, 84);
	        }
	        for (int i = 0; i < 10; i ++){

	        batcher.draw(AssetLoader.shrub1, background.getShrubX()+30 +i*100, midPointY+FLOOR-31);
	        batcher.draw(AssetLoader.shrub2, background.getShrubX()+100 + i*100, midPointY+FLOOR-31);
	        
	        }
	        for (int i =0; i*21 < 21*30; i++){
	        	batcher.draw(AssetLoader.groundEarth, (background.getEarthX()+i*21),midPointY+FLOOR-21,21,21);
	        	batcher.draw(AssetLoader.belowGroundEarth, (background.getEarthX()+i*21),midPointY+FLOOR-21+21);

	        }
	        
	        batcher.draw( AssetLoader.birdAnimation.getKeyFrame(runTime), bird.getX(), bird.getY(), bird.getWidth(), bird.getHeight());
	        
	        batcher.draw( AssetLoader.pincherAnimation.getKeyFrame(runTime), pinch.getX(), pinch.getY(), 18, 18);
	        
	        batcher.draw( AssetLoader.ghostAnimation.getKeyFrame(runTime), bug.getX(), bug.getY(), bug.getWidth(), bug.getHeight());
	        
	       
	        // Draw bird at its coordinates. Retrieve the Animation object from AssetLoader
	        // Pass in the runTime variable to get the current frame.
	        if (myWorld.isGameOver() ||myWorld.isReady()){
	        	batcher.draw(AssetLoader.dogAnimation.getKeyFrame(lastFrame),
		                dog.getX(), dog.getY(), dog.getWidth(), dog.getHeight());
	        } else {
	        batcher.draw(AssetLoader.dogAnimation.getKeyFrame(runTime),
	                dog.getX(), dog.getY(), dog.getWidth(), dog.getHeight());
	         lastFrame = runTime;
 
	        }
	        
	        // End SpriteBatch
	        
//	        String score = myWorld.getScore() + "";
//	     // Draw shadow first
//	        AssetLoader.shadow.draw(batcher, "" + myWorld.getScore(), (136 / 2)
//	                - (3 * score.length()), 12);
//	        // Draw text
//	        AssetLoader.font.draw(batcher, "" + myWorld.getScore(), (136 / 2)
//	                - (3 * score.length() - 1), 11);
//	        
	        
	        // TEMPORARY CODE! We will fix this section later:
	        //working on high score
//	        if (myWorld.isReady()) {
//	            // Draw shadow first
//	            AssetLoader.shadow.draw(batcher, "Touch me", (136 / 2)
//	                    - (42), 76);
//	            // Draw text
//	            AssetLoader.font.draw(batcher, "Touch me", (136 / 2)
//	                    - (42 - 1), 75);
//	        } else {
//
//	            if (myWorld.isGameOver()) {
//	                AssetLoader.shadow.draw(batcher, "Game Over", 25, 56);
//	                AssetLoader.font.draw(batcher, "Game Over", 24, 55);
//	                
//	                AssetLoader.shadow.draw(batcher, "Try again?", 23, 76);
//	                AssetLoader.font.draw(batcher, "Try again?", 24, 75);                
//	                
//	            }
	          //added for high score  
	        if (myWorld.isReady()) {
	            // Draw shadow first
	            AssetLoader.shadow.draw(batcher, "Tap to Start", (136 / 2) - (60), 76);
	            // Draw text
	            AssetLoader.font
	                    .draw(batcher, "Tap to Start", (136 / 2) - (60 - 1), 75);
	        } else {

	            if (myWorld.isGameOver() || myWorld.isHighScore()) {

	                if (myWorld.isGameOver()) {
	                    AssetLoader.shadow.draw(batcher, "Game Over", 25, 56);
	                    AssetLoader.font.draw(batcher, "Game Over", 24, 55);

//	                    AssetLoader.shadow.draw(batcher, "High Score:", 23, 106);
//	                    AssetLoader.font.draw(batcher, "High Score:", 22, 105);
//
//	                    String highScore = AssetLoader.getHighScore() + "";
//
//	                    // Draw shadow first
//	                    AssetLoader.shadow.draw(batcher, highScore, (136 / 2)
//	                            - (3 * highScore.length()), 128);
//	                    // Draw text
//	                    AssetLoader.font.draw(batcher, highScore, (136 / 2)
//	                            - (3 * highScore.length() - 1), 127);
	                    
	                    
	                } else {
	                    AssetLoader.shadow.draw(batcher, "High Score!", 19, 56);
	                    AssetLoader.font.draw(batcher, "High Score!", 18, 55);
	                }

	                AssetLoader.shadow.draw(batcher, " Ads here!", 9 , midPointY+FLOOR -11);
	                AssetLoader.font.draw(batcher, " Ads here!", 10 , midPointY+FLOOR -12);
	                
	                AssetLoader.shadow.draw(batcher, "Try again?", 23, 76);
	                AssetLoader.font.draw(batcher, "Try again?", 24, 75);

	                // Convert integer into String
	                String score = myWorld.getScore() + "";

	                // Draw shadow first
	                AssetLoader.shadow.draw(batcher, score,
	                        (136 / 2) - (3 * score.length()), 12);
	                // Draw text
	                AssetLoader.font.draw(batcher, score,
	                        (136 / 2) - (3 * score.length() - 1), 11);

	            }
	            
	        
	            // Convert integer into String
	            String score = myWorld.getScore() + "";

	            // Draw shadow first
	            AssetLoader.shadow.draw(batcher, "" + myWorld.getScore(), (136 / 2)
	                    - (3 * score.length()), 12);
	            // Draw text
	            AssetLoader.font.draw(batcher, "" + myWorld.getScore(), (136 / 2)
	                    - (3 * score.length() - 1), 11);
	        }
	        
	        batcher.end();
	        
	        //used for visual debugging
//	        shapeRenderer.begin(ShapeType.Filled);
//	        shapeRenderer.setColor(Color.RED);
//	        shapeRenderer.circle(bird.getBoundingCircle().x, bird.getBoundingCircle().y, bird.getBoundingCircle().radius);
//	        shapeRenderer.end();
//	        
//	        shapeRenderer.begin(ShapeType.Filled);
//	        shapeRenderer.setColor(Color.RED);
//	        shapeRenderer.circle(dog.getBoundingRec().x, dog.getBoundingRec().y, dog.getBoundingRec().radius);
//	        shapeRenderer.end();


	 }
//	 public OrthographicCamera getCam(){
//		 return cam;
//	 }


//	public void render(float runTime) {
//		// System.out.println("GameRenderer - render");
//		// We will move these outside of the loop for performance later.
//		// Dog dog = myWorld.getDog();
//
//		// Fill the entire screen with black, to prevent potential flickering.
//		Gdx.gl.glClearColor(0, 0, 0, 1);
//		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
//
//		// Begin ShapeRenderer
//		shapeRenderer.begin(ShapeType.Filled);
//		if (background.getX() <= -30){
//			AssetLoader.swapTextureSnow();
//		}
//
//		// Draw Background color
//		shapeRenderer.setColor(55 / 255.0f, 80 / 255.0f, 100 / 255.0f, 1);
//		shapeRenderer.rect(0, 0, 136, midPointY + 66);
//
//		// Draw Grass
//		shapeRenderer.setColor(111 / 255.0f, 186 / 255.0f, 45 / 255.0f, 1);
//		shapeRenderer.rect(0, midPointY + 66, 136, 11);
//
//		// Draw Dirt
//		shapeRenderer.setColor(147 / 255.0f, 80 / 255.0f, 27 / 255.0f, 1);
//		shapeRenderer.rect(0, midPointY + 77, 136, 52);
//
//		// End ShapeRenderer
//		shapeRenderer.end();
//
//		// Begin SpriteBatch
//		batcher.begin();
//		// Disable transparency
//		// This is good for performance when drawing images that do not require
//		// transparency.
//
//		drawBackground();
//
//		// The bird needs transparency, so we enable that again.
//		batcher.enableBlending();
//
//		batcher.draw(AssetLoader.birdAnimation.getKeyFrame(runTime),
//				bird.getX(), bird.getY(), bird.getWidth(), bird.getHeight());
//
//		batcher.draw(AssetLoader.pincherAnimation.getKeyFrame(runTime),
//				pinch.getX(), pinch.getY(), 18, 18);
//
//		batcher.draw(AssetLoader.birdAnimation.getKeyFrame(runTime),
//				bug.getX(), bug.getY(), bug.getWidth(), bug.getHeight());
//
//		// Draw bird at its coordinates. Retrieve the Animation object from
//		// AssetLoader
//		// Pass in the runTime variable to get the current frame.
//		if (myWorld.isGameOver() || myWorld.isReady()) {
//			batcher.draw(AssetLoader.dogAnimation.getKeyFrame(lastFrame),
//					dog.getX(), dog.getY(), dog.getWidth(), dog.getHeight());
//		} else {
//			batcher.draw(AssetLoader.dogAnimation.getKeyFrame(runTime),
//					dog.getX(), dog.getY(), dog.getWidth(), dog.getHeight());
//			lastFrame = runTime;
//
//		}
//
//		// End SpriteBatch
//
//		// String score = myWorld.getScore() + "";
//		// // Draw shadow first
//		// AssetLoader.shadow.draw(batcher, "" + myWorld.getScore(), (136 / 2)
//		// - (3 * score.length()), 12);
//		// // Draw text
//		// AssetLoader.font.draw(batcher, "" + myWorld.getScore(), (136 / 2)
//		// - (3 * score.length() - 1), 11);
//		//
//
//		// TEMPORARY CODE! We will fix this section later:
//		// working on high score
//		// if (myWorld.isReady()) {
//		// // Draw shadow first
//		// AssetLoader.shadow.draw(batcher, "Touch me", (136 / 2)
//		// - (42), 76);
//		// // Draw text
//		// AssetLoader.font.draw(batcher, "Touch me", (136 / 2)
//		// - (42 - 1), 75);
//		// } else {
//		//
//		// if (myWorld.isGameOver()) {
//		// AssetLoader.shadow.draw(batcher, "Game Over", 25, 56);
//		// AssetLoader.font.draw(batcher, "Game Over", 24, 55);
//		//
//		// AssetLoader.shadow.draw(batcher, "Try again?", 23, 76);
//		// AssetLoader.font.draw(batcher, "Try again?", 24, 75);
//		//
//		// }
//		// added for high score
//		if (myWorld.isReady()) {
//			// Draw shadow first
//			AssetLoader.shadow.draw(batcher, "Tap to Start", (136 / 2) - (60),
//					76);
//			// Draw text
//			AssetLoader.font.draw(batcher, "Tap to Start",
//					(136 / 2) - (60 - 1), 75);
//		} else {
//
//			if (myWorld.isGameOver() || myWorld.isHighScore()) {
//
//				if (myWorld.isGameOver()) {
//					AssetLoader.shadow.draw(batcher, "Game Over", 25, 56);
//					AssetLoader.font.draw(batcher, "Game Over", 24, 55);
//
//					// AssetLoader.shadow.draw(batcher, "High Score:", 23, 106);
//					// AssetLoader.font.draw(batcher, "High Score:", 22, 105);
//					//
//					// String highScore = AssetLoader.getHighScore() + "";
//					//
//					// // Draw shadow first
//					// AssetLoader.shadow.draw(batcher, highScore, (136 / 2)
//					// - (3 * highScore.length()), 128);
//					// // Draw text
//					// AssetLoader.font.draw(batcher, highScore, (136 / 2)
//					// - (3 * highScore.length() - 1), 127);
//				} else {
//					AssetLoader.shadow.draw(batcher, "High Score!", 19, 56);
//					AssetLoader.font.draw(batcher, "High Score!", 18, 55);
//				}
//
//				 AssetLoader.shadow.draw(batcher, " Ads here!", 9 , midPointY+FLOOR - 5);
//	                AssetLoader.font.draw(batcher, " Ads here!", 10 , midPointY+FLOOR -6);
//	                
//	                AssetLoader.shadow.draw(batcher, "Try again?", 23, 76);
//	                AssetLoader.font.draw(batcher, "Try again?", 24, 75);
//	                
//				AssetLoader.shadow.draw(batcher, "Try again?", 23, 76);
//				AssetLoader.font.draw(batcher, "Try again?", 24, 75);
//
//				// Convert integer into String
//				String score = myWorld.getScore() + "";
//
//				// Draw shadow first
//				AssetLoader.shadow.draw(batcher, score,
//						(136 / 2) - (3 * score.length()), 12);
//				// Draw text
//				AssetLoader.font.draw(batcher, score,
//						(136 / 2) - (3 * score.length() - 1), 11);
//
//			}
//
//			// Convert integer into String
//			String score = myWorld.getScore() + "";
//
//			// Draw shadow first
//			AssetLoader.shadow.draw(batcher, "" + myWorld.getScore(), (136 / 2)
//					- (3 * score.length()), 12);
//			// Draw text
//			AssetLoader.font.draw(batcher, "" + myWorld.getScore(), (136 / 2)
//					- (3 * score.length() - 1), 11);
//		}
//
//		batcher.end();
//
//		// used for visual debugging
//		// shapeRenderer.begin(ShapeType.Filled);
//		// shapeRenderer.setColor(Color.RED);
//		// shapeRenderer.circle(bird.getBoundingCircle().x,
//		// bird.getBoundingCircle().y, bird.getBoundingCircle().radius);
//		// shapeRenderer.end();
//		//
//		// shapeRenderer.begin(ShapeType.Filled);
//		// shapeRenderer.setColor(Color.RED);
//		// shapeRenderer.circle(dog.getBoundingRec().x, dog.getBoundingRec().y,
//		// dog.getBoundingRec().radius);
//		// shapeRenderer.end();
//
//	}


	private void drawBackground() {
		drawForest();
		
		if (DebugMode){
			AssetLoader.font.draw(batcher, Float.toString(background.getX()),
				(136 / 2) - (60 - 1), 75);
		}
	}

	private void drawForest() {

		batcher.disableBlending();
		
		//TO-DO: MAKE THIS INFINITE
		batcher.draw(AssetLoader.bg, background.getX(), 0,
				((1020 / 348) * gameHeight), gameHeight);


		batcher.enableBlending();

		for (int i = 0; i < 10; i++) {
			batcher.draw(AssetLoader.cloud, background.getCloudX() + i * 100,
					(i * 20) % 50);
		}
		int FLOOR = Background.FLOOR;

		for (int i = 0; i < 10; i++) {
			batcher.draw(AssetLoader.tree, background.getTreeX() + i * 100,
					midPointY + FLOOR - 100, 61, 84);
		}
		for (int i = 0; i < 10; i++) {

			batcher.draw(AssetLoader.shrub1, background.getShrubX() + 30 + i
					* 100, midPointY + FLOOR - 31);
			batcher.draw(AssetLoader.shrub2, background.getShrubX() + 100 + i
					* 100, midPointY + FLOOR - 31);

		}
		for (int i = 0; i * 21 < 21 * 30; i++) {
			batcher.draw(AssetLoader.groundEarth,
					(background.getEarthX() + i * 21), midPointY + FLOOR - 21,
					21, 21);
			batcher.draw(AssetLoader.belowGroundEarth,
					(background.getEarthX() + i * 21), midPointY + FLOOR - 21
							+ 21);

		}		
	}

}
