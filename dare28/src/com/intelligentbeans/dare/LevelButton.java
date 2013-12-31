package com.intelligentbeans.dare;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.intelligentbeans.boilerplate.SpriteImage;

public class LevelButton extends SpriteImage {

	BitmapFont yourBitmapFontName;
	int number = 0;
	public LevelButton(Vector2 position, boolean active, int number){
		super(position,"levelOptionInactive");
		this.number = number ;
		yourBitmapFontName = new BitmapFont(Gdx.files.internal("data/fonts/meters.fnt"),Gdx.files.internal("data/fonts/meters.png"),false);
		
		
		if(active){
			region = atlas.findRegion("levelOptionActive");
		}else{
			yourBitmapFontName.setColor(58.0f, 0.0f, 0.0f, .5f);
		}
		
		
	}
	
	
	
	/*************************************************************************************
	 * This renders stuff on the screen
	 *************************************************************************************/
	@Override
		public void draw(SpriteBatch batch, float parentAlpha) {
			final Color c = getColor();
	        batch.setColor(c.r, c.g, c.b, c.a * parentAlpha);
	        super.draw(batch, parentAlpha);
	      
	    	yourBitmapFontName.draw(batch, this.number + "",getX() + 28,getY() + 45); 
	     
	        
	        
			
			
	}
	
	
	
	
}
