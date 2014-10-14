package com.BowWowPow.GameObjects;

import com.BowWowPow.BWPHelpers.AssetLoader;
import com.BowWowPow.GameWorld.GameWorld;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;

public class Insect extends Bird{

	private Vector2 position;
	private Vector2 velocity;
	private Vector2 acceleration;
	
	private int width;
	private int height;
	private Circle boundingCircle;
	private boolean beenClicked = false;
	
	public Insect(float x, float y, int width, int height){
		super(x,y, width, height);
		this.width = width;
        this.height = height;
        position = new Vector2(x, y);
        velocity = new Vector2(0, 0);
        acceleration = new Vector2(0, 460);
        boundingCircle = new Circle();
	}
	int score;
	int booster;
	
	 public void update(float delta) {
		 
		 velocity.add(acceleration.cpy().scl(delta));

		  score = GameWorld.score;
		  booster = score%5 ;
		  
		 if (velocity.y > (65+booster) ){
			 velocity.y = (65+booster);
		 }
		 
		 if(position.x > 68 && position.x < 136 ){
			 velocity.x = -20;
		 }else if(position.x > 0 && position.x < 68){
			 velocity.x = 20;
		 }
	        position.add(velocity.cpy().scl(delta));
	        
	        boundingCircle.set(position.x + 9, position.y + 6, 8.5f); 
	        
	        //boundingCircle.set(position.x + 19, position.y + 16, 6.5f); 
	 }
	 

	 public void reset(float newX){
		 position.x = newX;
		 position.y = -20;
		 
		 velocity.x = 0;
		 velocity.y = 1;
		 beenClicked = false;
	 }
	 
	 public void die(){
		 position.y = -12;
		 velocity.y = 0;
		 position.x = 0;
		 boundingCircle.set(position.x + 9, position.y + 6, 8.5f); 
	 }
	 
	 public void onClick(float x, float y){
		 
		 if(getBoundingCircle().contains(x,y)){
			 AssetLoader.pop.play();
			 velocity.x = 140;
			 beenClicked = true;
			 GameWorld.score = GameWorld.score + 5;
			 System.out.println(GameWorld.score);
		 }
	 }
	 
	 	public boolean getBeenClicked(){
	 		
	 		return beenClicked;
	 	}
	 	
	    public float getX() {
	        return position.x;
	    }

	    public float getY() {
	        return position.y;
	    }

	    public float getWidth() {
	        return width;
	    }

	    public float getHeight() {
	        return height;
	    }
	    public Circle getBoundingCircle() {
	        return boundingCircle;
	    }
}
