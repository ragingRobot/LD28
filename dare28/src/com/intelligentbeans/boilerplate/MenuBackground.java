package com.intelligentbeans.boilerplate;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class MenuBackground extends SpriteImage {
	private Texture texture;
	private float offset = 0;
	public MenuBackground(){
    	super(new Vector2(0,0), Gdx.graphics.getWidth(),Gdx.graphics.getHeight(), "data/menu.png", true);
    	
	}
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//GAME LOOP STUFF	
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    
	/*************************************************************************************
	 * This handles physics stuff
	 *************************************************************************************/
    @Override
	public void act(float delta) {
    	offset -= .001;
		super.act(delta);		
	}
   
    /*************************************************************************************
	 * This renders stuff on the screen
	 *************************************************************************************/
    @Override
   	public void draw(SpriteBatch batch, float parentAlpha) {
    	final Color c = getColor();
        batch.setColor(c.r, c.g, c.b, c.a * parentAlpha);
        batch.draw(texture, getX(), getY(), getWidth(), getHeight(),-offset, offset, texture.getWidth(),texture.getHeight());

   }
    
}
