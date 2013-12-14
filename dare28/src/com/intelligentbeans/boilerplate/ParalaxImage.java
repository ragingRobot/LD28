package com.intelligentbeans.boilerplate;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class ParalaxImage extends SpriteImage {
	public ParalaxImage(){
	
		super(new Vector2(-(Gdx.graphics.getWidth() * 2.5f),0), Gdx.graphics.getWidth() * 5,Gdx.graphics.getHeight(), "data/background.jpg", true);
	}
	
	public ParalaxImage(String image){
		super(new Vector2(-(Gdx.graphics.getWidth() * 2.5f),0), Gdx.graphics.getWidth() * 5,Gdx.graphics.getHeight(), image, true);
    	
	}

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//GAME LOOP STUFF	
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    
    /*************************************************************************************
	* This handles physics stuff
	*************************************************************************************/
    @Override
	public void act(float delta) {
		super.act(delta);		
		
	}
   
   
    /*************************************************************************************
	 * This renders stuff on the screen
	 *************************************************************************************/
    @Override
   	public void draw(SpriteBatch batch, float parentAlpha) {
    	final Color c = getColor();
        batch.setColor(c.r, c.g, c.b, c.a * parentAlpha);
   		super.draw(batch, parentAlpha);
   		
   }
    
}
