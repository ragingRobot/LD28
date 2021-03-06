package com.intelligentbeans.boilerplate;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class Background extends SpriteImage {
	public Background(){
		super(new Vector2(0,0), Gdx.graphics.getWidth(),Gdx.graphics.getHeight(), "data/backgrounds/background.jpg", true);
	}
	
	public Background(String image){
		super(new Vector2(0,0), Gdx.graphics.getWidth(),Gdx.graphics.getHeight(), image, true);
    	
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
