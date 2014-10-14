package com.BowWowPow.GameObjects;

import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Dog {

	private Vector2 position;
	private Vector2 velocity;
	private Vector2 acceleration;
	
	private int width;
	private int height;
	private Circle boundingRec;
	
	public Dog(float x, float y, int width, int height){
		this.width = width;
        this.height = height;
        position = new Vector2(x, y);
        velocity = new Vector2(0, 0);
        acceleration = new Vector2(0, 460);
        boundingRec = new Circle(x+22 , y+23 , width-30);
	}
	
	
	public boolean collides(Bird bird){
		 if (position.x < bird.getX() + bird.getWidth()) {
			 return (Intersector.overlaps(bird.getBoundingCircle(), boundingRec));
		 }
		 
		return false;
	}
	  public void update(float delta) {

	        //velocity.add(acceleration.cpy().scl(delta));
//
//	        if (velocity.y > 200) {
//	            velocity.y = 200;
//	        }

	        //position.add(velocity.cpy().scl(delta));

	    }
	  
	  public void onClick() {
		  
		  System.out.println("Dog clicked");
	        //velocity.y = -140;
	    }

	  public void onClick(float x, float y){
		  
		  if(getBoundingRec().contains(x,y)){
			  System.out.println("x is good");
		  }
		  
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
	    
	    public Circle getBoundingRec(){
	    	return boundingRec;
	    }
	    
      private float rotation;
	    public float getRotation() {
	        return rotation;
	    }
}
