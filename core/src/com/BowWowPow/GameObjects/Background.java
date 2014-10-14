package com.BowWowPow.GameObjects;

public class Background {
	private float x = -0;
	private float speed = (float) 0.003;
	private float earthX = 0;
	private float treeX = 0;
	private float treeSpeed  = (float).1;
	private float shrubSpeed = (float).20;
	public static int FLOOR = 80;
	private float cloudX = 0;
	private float cloudSpeed = (float).05;
	
	private float shrubX = 0;
	
	public void update(float delta) {
		if (this.earthX <= -(21)*20) {
			this.earthX =0;
		}
		this.earthX -= (float) (this.speed * 400);
		
		this.x -= this.speed;
		
		this.treeX -= this.treeSpeed;
		this.shrubX -=this.shrubSpeed;
		this.cloudX -= this.cloudSpeed;
		
	}

	public float getX() {
		return this.x;
	}

	public float getEarthX() {
		return this.earthX;

	}
	public float getTreeX() {
		return this.treeX;

	}
	public float getShrubX() {
		return this.shrubX;

	}
	public float getCloudX() {
		return this.cloudX;

	}
}
