package com.BowWowPow.Screens;

import com.BowWowPow.BowWowPow;
import com.BowWowPow.BWPHelpers.AssetLoader;
import com.BowWowPow.GameObjects.Bird;
import com.BowWowPow.GameObjects.Dog;
import com.BowWowPow.GameObjects.Insect;
import com.BowWowPow.GameObjects.Pincher;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.StretchViewport;

public class MainMenuScreen implements Screen {
//	
	private SpriteBatch batcher;
	OrthographicCamera camera;
	public float gWidth;
	public float gHeight;
	
	private Dog myDog;
	private Bird myBird;
	private Insect myInsect;
	private Pincher myPincher;
//	private Animation dogAnimation, birdAnimation, insectAnimation;
	
	private Stage stage;
    private Skin skin;
    private TextureAtlas atlas;
    final BowWowPow game;
    float screenWidth;
	float screenHeight;
	float gameHeight;
    
    public MainMenuScreen(final BowWowPow gam) {
		game = gam;

		camera = new OrthographicCamera();
		camera.setToOrtho(true, 136, 204);
		gWidth = Gdx.graphics.getWidth();
		gHeight = Gdx.graphics.getHeight();
		batcher = new SpriteBatch();
		// Attach batcher to camera

		batcher.setProjectionMatrix(camera.combined);
		AssetLoader.mainMenu.loop(0.5f);

		 screenWidth = Gdx.graphics.getWidth();
		 screenHeight = Gdx.graphics.getHeight();
		 gameHeight = screenHeight / (screenWidth / 136);

		myDog = new Dog(35, 95, 75, 75);
		myBird = new Bird(62, 75, 15, 15);
		myInsect = new Insect(28, 75, 15, 15);
		myPincher = new Pincher(97, 75, 15, 15);
    }
//    
	@Override
	public void render(float delta) {

		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		camera.update();
		// game.batch.setProjectionMatrix(camera.combined);
		batcher.begin();

		// game.batch.begin();

		// game.font.setScale(.8f);
		// game.font.draw(game.batch, "Welcome to BugOut!! ", 15, 50);
		// game.font.draw(game.batch, "Tap to begin!", 30, 100);
		// AssetLoader.shadow.draw(batcher, "BUG OUT!", (136 / 2) - (60), 76);
		// Draw text
		AssetLoader.font
				.draw(batcher, "BUG OUT!", (gWidth - (gWidth - 32)), 45);
		//drawButton();
		// Draw main character
		batcher.draw(AssetLoader.dogAnimation.getKeyFrame(delta), myDog.getX(),
				myDog.getY(), myDog.getWidth(), myDog.getHeight());
		
		// Draw minor characters
		batcher.draw(AssetLoader.birdAnimation.getKeyFrame(delta), myBird.getX(),
				myBird.getY(), myBird.getWidth(), myBird.getHeight());
		batcher.draw(AssetLoader.ghostAnimation.getKeyFrame(delta), myInsect.getX(),
				myInsect.getY(), myInsect.getWidth(), myInsect.getHeight());
		batcher.draw(AssetLoader.pincherAnimation.getKeyFrame(delta), myPincher.getX(),
				myPincher.getY(), myPincher.getWidth(), myPincher.getHeight());
		
		batcher.end();
		// game.batch.end();

		 stage.act();
         stage.draw();
	}
	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void show() {
		int width = Gdx.graphics.getWidth();
		int height = Gdx.graphics.getHeight();
		atlas = new TextureAtlas("uiskin.atlas");
		
		stage = new Stage();
		
		Gdx.input.setInputProcessor(stage);
		skin = new Skin(Gdx.files.internal("uiskin.json"), atlas);
		
		TextButton contButton = new TextButton("Continue", skin);
		//contButton.setPosition(140, 10);
		contButton.setPosition(280, 20);
		//contButton.setSize(125.0f, 50.0f);
		contButton.setSize(250.0f, 100.0f);
		contButton.setColor(Color.BLUE);
		contButton.addListener(new ClickListener() {
			 public void clicked(InputEvent event, float x, float y) {
				 game.setScreen(new GameScreen(game));
			 }
           
        });
 
		TextButton newButton = new TextButton("New Game", skin);
		
		//newButton.setPosition(10, 10);
		newButton.setPosition(10, 20);
		//newButton.setSize(125.0f, 50.0f);
		newButton.setSize(250.0f, 100.0f);
		
		
		newButton.setColor(Color.BLUE);
		newButton.addListener(new ClickListener() {
			 public void clicked(InputEvent event, float x, float y) {
				 AssetLoader.prefs.putInteger("highScore", 0);
				 game.setScreen(new GameScreen(game));
			 }
           
        });
        // adding button to stage
        stage.addActor(contButton);
        stage.addActor(newButton);
		// TODO Auto-generated method stub
		
	}
	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}
	 	
}
