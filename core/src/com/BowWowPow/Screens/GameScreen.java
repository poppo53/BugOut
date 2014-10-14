package com.BowWowPow.Screens;

import com.BowWowPow.BowWowPow;
import com.BowWowPow.BWPHelpers.InputHandler;
import com.BowWowPow.GameWorld.GameRenderer;
import com.BowWowPow.GameWorld.GameWorld;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;

public class GameScreen implements Screen{
	final BowWowPow game;
	
	private GameWorld world;
	private GameRenderer renderer;
	private float runTime = 0;
	public float screenWidth;
	 public GameScreen(final BowWowPow gam ){
		 
		 	game = gam;
		 	screenWidth = Gdx.graphics.getWidth();
	        float screenHeight = Gdx.graphics.getHeight();      
	        float gameWidth = 136;
	        float gameHeight = screenHeight / (screenWidth / gameWidth);
	        
	        int midPointY = (int) (gameHeight / 2);

		 	world = new GameWorld(midPointY);
		 	renderer = new GameRenderer(world, (int) gameHeight, midPointY);
		 	//Gdx.input.setInputProcessor(new InputHandler(world.getDog()));
		 	Gdx.input.setInputProcessor(new InputHandler(world));
	        System.out.println("GameScreen Attached");
	    }

	 @Override
	 public void render(float delta) {
	     runTime += delta;
	     world.update(delta);
	     renderer.render(runTime);
	 }

	    @Override
	    public void resize(int width, int height) {
	        System.out.println("GameScreen - resizing");
	    }

	    @Override
	    public void show() {
	        System.out.println("GameScreen - show called");
	    }

	    @Override
	    public void hide() {
	        System.out.println("GameScreen - hide called");     
	    }

	    @Override
	    public void pause() {
	        System.out.println("GameScreen - pause called");        
	    }

	    @Override
	    public void resume() {
	        System.out.println("GameScreen - resume called");       
	    }

	    @Override
	    public void dispose() {
	        // Leave blank
	    }

}
