package com.BowWowPow;

import com.BowWowPow.BWPHelpers.AssetLoader;
import com.BowWowPow.Screens.GameScreen;
import com.BowWowPow.Screens.MainMenuScreen;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class BowWowPow extends Game{

	
	
	  public SpriteBatch batch;
	    public BitmapFont font;

	    public void create() {
	        batch = new SpriteBatch();
	        //Use LibGDX's default Arial font.
	        font = new BitmapFont();
	        AssetLoader.load();
	        this.setScreen(new MainMenuScreen(this));
	        
	    }

	    public void render() {
	        super.render(); //important!
	    }

	    public void dispose() {
	        batch.dispose();
	        font.dispose();
	    }
//	@Override
//	public void create() {
//		// TODO Auto-generated method stub
//		System.out.println("ZBGame Created!");
//		 AssetLoader.load();
//		 setScreen(new GameScreen());
//	}
//
//	 @Override
//	    public void dispose() {
//	        super.dispose();
//	        AssetLoader.dispose();
//	    }
}
