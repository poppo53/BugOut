package com.BowWowPow.BWPHelpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public class AssetLoader {

	public static Texture texture, texture2;
    public static TextureRegion bg, grass, bird;
    
    public static Animation dogAnimation;
    public static Animation birdAnimation;
    public static TextureRegion dog1, dog2, dog3, dog4, dog5, dog6, dog7, dog8, dog9;
    public static Sound dead, pop, splat, mainMenu;
    public static BitmapFont font, shadow,font2, shadow2;
	public static Texture texturefly;
	private static TextureRegion bat1;
	private static TextureRegion bat2;
	private static TextureRegion insect;
	private static Texture textureBG;
	private static Texture treeText;
	public static TextureRegion tree;
    
	private static Texture belowGroundEarthText;
	public static TextureRegion belowGroundEarth;
	
	private static Texture groundEarthTexture;
	public static TextureRegion groundEarth;
	private static Texture envSprites;
	public static TextureRegion shrub1;
	public static TextureRegion shrub2;
	public static TextureRegion cloud;
	
	public static Preferences prefs;
	private static Texture pincherTexture;
	private static TextureRegion pincher1;
	private static TextureRegion pincher2;
	public static Animation pincherAnimation;
	private static Texture groundTundraText;
	public static TextureRegion belowGroundTundra;
	public static TextureRegion groundTundra;
	
	public static Texture ghostBoyTexture;
	public static TextureRegion ghostBoy1;
	public static TextureRegion ghostBoy2;
	public static Animation ghostAnimation;

	public static final Skin skin = new Skin(Gdx.files.internal("uiskin.json"));

	
    public static void load(){
    	
    	// Create (or retrieve existing) preferences file
    	 prefs = Gdx.app.getPreferences("BugOut");

    	// Provide default high score of 0
    	 if (!prefs.contains("highScore")) {
    	     prefs.putInteger("highScore", 0);
    	 }
    	
    	 texture = new Texture(Gdx.files.internal("textureBG.png"));
    	 texture.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
         //x,y, width, height
         texture2 = new Texture(Gdx.files.internal("WalkerOne.png"));
         texture2.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
         
         belowGroundEarthText =  new Texture(Gdx.files.internal("beneathGroundEarth.png"));
         belowGroundEarth = new TextureRegion(belowGroundEarthText, 0,0,21,10);
         belowGroundEarth.flip(false, true);
         
         groundTundraText = new Texture(Gdx.files.internal("tundraEarth.png"));
         
         belowGroundTundra = new TextureRegion(groundTundraText, 21,0,21,20);
         belowGroundTundra.flip(false, true);
         groundTundra = new TextureRegion(groundTundraText, 0,0,21,20);
         groundTundra.flip(false, true);
         pincherTexture = new Texture(Gdx.files.internal("pincher.png"));
         pincher1 = new TextureRegion(pincherTexture, 0,0,18,18);
         pincher2 = new TextureRegion(pincherTexture, 18,0,18,18);
         
         ghostBoyTexture = new Texture(Gdx.files.internal("ghostboy.png"));
         
         ghostBoy1 = new TextureRegion(ghostBoyTexture, 0,0,21,20);
         ghostBoy2 = new TextureRegion(ghostBoyTexture, 21,0,21,20);
         ghostBoy1.flip(false, true);
         ghostBoy2.flip(false, true);

         
         treeText =  new Texture(Gdx.files.internal("TreeUna.png"));
        tree = new TextureRegion(treeText, 0,0,61,84);
        tree.flip(false, true);
        
        envSprites =  new Texture(Gdx.files.internal("envsprites.png"));
        
        shrub1 = new TextureRegion(envSprites, 0,0,22,10);
        cloud = new TextureRegion(envSprites, 22,0,23,10);
        shrub1.flip(false, true);
        cloud.flip(false, true);

        shrub2 = new TextureRegion(envSprites, 43,0,22,10);
        shrub2.flip(false, true);

        
         texturefly = new Texture(Gdx.files.internal("Enemy.png"));

         textureBG = new Texture(Gdx.files.internal("bluesky.png"));
    	 textureBG.setFilter(TextureFilter.Nearest, TextureFilter.Linear);

         groundEarthTexture = new Texture(Gdx.files.internal("GroundEarth.png"));

         groundEarth = new TextureRegion(groundEarthTexture,0,0,21,21);
         groundEarth.flip(false, true);

         bg = new TextureRegion(textureBG, 0, 0, 231, 63);
         bg.flip(false, true);
         

         grass = new TextureRegion(texture, 0, 43, 143, 11);
         grass.flip(false, true);
         
         //bird =  new TextureRegion(texture, 136, 0, 17, 12);
         bat1 = new TextureRegion(texturefly, 0, 0, 21, 21);
         bat2 = new TextureRegion(texturefly, 21, 0, 21, 21);
         bat1.flip(false, true);
         bat2.flip(false, true);
               
         //x = 0, 88, 150 
         // y = 0, 50, 50
         
         font = new BitmapFont(Gdx.files.internal("text.fnt"));
         font.setScale(.25f, -.25f);
         shadow = new BitmapFont(Gdx.files.internal("shadow.fnt"));
         shadow.setScale(.25f, -.25f);
         
//         font2 = new BitmapFont(Gdx.files.internal("text.fnt"));
//         font2.setScale(.25f, -.25f);
//         
//         shadow2 = new BitmapFont(Gdx.files.internal("shadow.fnt"));
//         shadow2.setScale(.25f, -.25f);
         
         dog1 = new TextureRegion(texture2 , 0, 0, 21, 24);
         dog1.flip(false, true);
         
         dog2 = new TextureRegion(texture2 , 21,  0, 21, 24);
         dog2.flip(false, true);
         
         dog3 = new TextureRegion(texture2 , 42,  0, 21, 24);
         dog3.flip(false, true);
         
         dog4 = new TextureRegion(texture2, 63, 0, 21, 24);
         dog4.flip(false, true);
         
         dead = Gdx.audio.newSound(Gdx.files.internal("dead.wav"));
         pop = Gdx.audio.newSound(Gdx.files.internal("pop.wav"));
         splat =  Gdx.audio.newSound(Gdx.files.internal("spalt2.wav"));
         mainMenu =  Gdx.audio.newSound(Gdx.files.internal("mainMenu.wav"));
         TextureRegion[] dogs = { dog1,dog2, dog3, dog4 };
         TextureRegion[] birds = {bat1, bat2};
         TextureRegion[] pinchers = {pincher1, pincher2};
         TextureRegion[] ghosts = {ghostBoy1, ghostBoy2};
         
         birdAnimation = new Animation(.09f, birds);
         birdAnimation.setPlayMode(Animation.PlayMode.LOOP);
         
         dogAnimation = new Animation(0.09f, dogs);
         dogAnimation.setPlayMode(Animation.PlayMode.LOOP);
         
         
         ghostAnimation = new Animation(.09f, ghosts);
         ghostAnimation.setPlayMode(Animation.PlayMode.LOOP);

         
         pincherAnimation = new Animation(0.09f, pinchers);
         pincherAnimation.setPlayMode(Animation.PlayMode.LOOP);
    	
    }
    public static void setHighScore(int val) {
        prefs.putInteger("highScore", val);
        prefs.flush();
    }

    public static int getHighScore() {
        return prefs.getInteger("highScore");
    }
    
    public static void dispose() {
        // We must dispose of the texture when we are finished.
    	font.dispose();
    	shadow.dispose();
        texture.dispose();
    }
	public static void swapTextureSnow() {
		groundEarth = groundTundra;
		belowGroundEarth = belowGroundTundra;
		
	}
}
