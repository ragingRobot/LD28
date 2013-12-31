package com.intelligentbeans.boilerplate;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureWrap;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;

public class SpriteImageGroup extends Group{
	
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//PRIVATE/PROTECTED STUFF
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////	
    protected Sprite sprite;
    protected Texture texture;
    protected AtlasRegion region;
    protected TextureAtlas atlas;
    protected Boolean dead = false;
    protected int frame = 0;
    protected String sheet ="data/z/dare-textures";
    
    
	public SpriteImageGroup(Vector2 position, String name){
	
		if(name != null){
			atlas = new TextureAtlas(Gdx.files.internal(sheet));
			region = atlas.findRegion(name);
			
		    sprite = new Sprite(region);
		   
		    setWidth(region.packedWidth);
			setHeight(region.packedHeight);
		}
		setX( position.x);
		setY( position.y);
	 
	 		
	}
	
	
	public SpriteImageGroup(Vector2 position, float width, float height, String name, Boolean repeat){
		
		
			texture = new Texture(Gdx.files.internal(name));
		
			if(repeat){
				texture.setWrap(TextureWrap.Repeat, TextureWrap.Repeat);
			}
		   
		
		setWidth(width);
		setHeight(height);
		setX( position.x);
		setY( position.y);
	 
	
	}
	
	public SpriteImageGroup(){
		
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
	        
	        
	       
	        	if(texture != null){
	        		batch.draw(texture, getX(), getY(), getWidth(), getHeight());
	        	}else if(region != null){
	        		batch.draw(region, getX() , getY(), getWidth(), getHeight());
	        	}	
	   
	        
	        
			super.draw(batch, parentAlpha);
			
	}


}
