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
import com.badlogic.gdx.scenes.scene2d.Stage;

public class ProgressBar extends SpriteImage {
	
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
    protected SpriteImage progress;
    protected SpriteImage progressIndicator;
    protected float maxWidth = 502;
	public ProgressBar(Vector2 position, Stage stage){
		super(position, "levelbar");
		
		progress = new SpriteImage(position, "levelprogress");
		progressIndicator =  new SpriteImage(position, "levelindicator");
	
		progress.setX(this.getX() + 12);
		progress.setY(this.getY() + 18);
		progress.setWidth(0);
		
		
		
		progressIndicator.setX(progress.getX() + progress.getWidth() - (progressIndicator.getWidth()/2));
		progressIndicator.setY(this.getY());
		
		stage.addActor(this);	
		stage.addActor(progress);
		stage.addActor(progressIndicator);
		
	 		
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
	
	public void setPercentage(float percentage){
	//Gdx.app.log( maxWidth+ "/" + percentage + "=", (meters/levellenght) * 100 + "" );
		progress.setWidth(maxWidth * (percentage/100));
		progressIndicator.setX(progress.getX() + progress.getWidth() - (progressIndicator.getWidth()/2));
	}
	
	
	/*************************************************************************************
	 * This renders stuff on the screen
	 *************************************************************************************/
	@Override
		public void draw(SpriteBatch batch, float parentAlpha) {
			super.draw(batch, parentAlpha);
			
	}


}
