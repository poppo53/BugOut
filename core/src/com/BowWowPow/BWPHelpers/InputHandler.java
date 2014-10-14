package com.BowWowPow.BWPHelpers;

import com.BowWowPow.GameObjects.Bird;
import com.BowWowPow.GameObjects.Dog;
import com.BowWowPow.GameObjects.Insect;
import com.BowWowPow.GameObjects.Pincher;
import com.BowWowPow.GameWorld.GameRenderer;
import com.BowWowPow.GameWorld.GameWorld;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;



public class InputHandler implements InputProcessor {

	private Dog myDog;
	private Bird myBird;
	
	private Pincher myPinch;
	private Insect myBug;
	private GameWorld world;

	 // Ask for a reference to the Bird when InputHandler is created.
    public InputHandler(GameWorld myWorld) {
        // myBird now represents the gameWorld's bird.
    	this.world = myWorld;
    	myBird = world.getBird();
    	myPinch = world.getpinch();
        myDog = world.getDog();
        myBug = world.getBug();
       
    }
    
    //TODO need to change this method to fit our game
    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {

    	if (world.isReady()) {
            world.start();
        }
    	
    	Vector3 input = new Vector3(screenX, screenY, 0);
    	GameRenderer.cam.unproject(input); 	
    	//myDog.onClick(input.x, input.y);
    	myBird.onClick(input.x, input.y);
    	myPinch.onClick(input.x, input.y);
    	myBug.onClick(input.x, input.y);
//    	 if (world.isGameOver() ) {
//             // Reset all variables, go to GameState.READ
//             world.restart();
//         }
    	//added for high score
    	if (world.isGameOver() || world.isHighScore()) {
    	    // Reset all variables, go to GameState.READ
    	    world.restart();
    	}
    	 
        return true; // Return true to say we handled the touch.
    }
    
    
	@Override
	public boolean keyDown(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}



	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		// TODO Auto-generated method stub
		return false;
	}

}
