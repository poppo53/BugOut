package com.BowWowPow.GameObjects;

public class ScrollHandler {

	 private Grass frontGrass, backGrass;
	 private Trees tree1, tree2, tree3;
	 
	 public static final int SCROLL_SPEED = -59;
	 public static final int TREE_GAP = 49;// might not need
	 
	 public ScrollHandler(float yPos) {
	        frontGrass = new Grass(0, yPos, 143, 11, SCROLL_SPEED);
	        backGrass = new Grass(frontGrass.getTailX(), yPos, 143, 11,
	                SCROLL_SPEED);

	        tree1 = new Trees(210, 0, 22, 60, SCROLL_SPEED);
	        tree2 = new Trees(tree1.getTailX() + TREE_GAP, 0, 22, 70, SCROLL_SPEED);
	        tree3 = new Trees(tree2.getTailX() + TREE_GAP, 0, 22, 60, SCROLL_SPEED);
	 }    
	    public void update(float delta) {
	    	frontGrass.update(delta);
	        backGrass.update(delta);
	        
//	        tree1.update(delta);
//	        tree2.update(delta);
//	        tree3.update(delta);
//
//	        // Check if any of the pipes are scrolled left,
//	        // and reset accordingly
//	        if (tree1.isScrolledLeft()) {
//	            tree1.reset(tree3.getTailX() + TREE_GAP);
//	        } else if (tree2.isScrolledLeft()) {
//	            tree2.reset(tree1.getTailX() + TREE_GAP);
//
//	        } else if (tree3.isScrolledLeft()) {
//	            tree3.reset(tree2.getTailX() + TREE_GAP);
//	        }
//
//	        // Same with grass
//	        if (frontGrass.isScrolledLeft()) {
//	            frontGrass.reset(backGrass.getTailX());
//
//	        } else if (backGrass.isScrolledLeft()) {
//	            backGrass.reset(frontGrass.getTailX());
//
//	        }
	    }

	    // The getters for our five instance variables
	    public Grass getFrontGrass() {
	        return frontGrass;
	    }

	    public Grass getBackGrass() {
	        return backGrass;
	    }

	    public Trees getTree1() {
	        return tree1;
	    }

	    public Trees getTree2() {
	        return tree2;
	    }

	    public Trees getTree3() {
	        return tree3;
	    }

}
