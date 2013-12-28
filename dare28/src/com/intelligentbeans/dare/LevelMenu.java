package com.intelligentbeans.dare;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
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
import com.badlogic.gdx.utils.GdxRuntimeException;
import com.intelligentbeans.boilerplate.SpriteImage;

public class LevelMenu extends SpriteImage {
	
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
    protected Preferences prefs;
	public LevelMenu(Vector2 position, Stage stage){
		super(position, "levelMenu");
		/*
		progress = new SpriteImage(position, "levelprogress");
		progressIndicator =  new SpriteImage(position, "levelindicator");
	
		progress.setX(this.getX() + 12);
		progress.setY(this.getY() + 18);
		progress.setWidth(0);
		
		
		
		progressIndicator.setX(progress.getX() + progress.getWidth() - (progressIndicator.getWidth()/2));
		progressIndicator.setY(this.getY());
		*/
		stage.addActor(this);	
		/*
		stage.addActor(progress);
		stage.addActor(progressIndicator);
		*/
	
		prefs = Gdx.app.getPreferences("my-preferences");
		
		
		int currentLevel = prefs.getInteger("CurrentLevel");
		Gdx.app.log("currentLevel ", currentLevel + "");
		
	 		
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
	
	public void openLevel(int level){
		saveLevelProgress(level);

	}
	
	public void saveLevelProgress(int level){
		prefs.putInteger("CurrentLevel", level);
		
		if(prefs.getInteger("HigestLevelComplete") < level){
			prefs.putInteger("HigestLevelComplete", 0);
			prefs.flush();
		}
		
		/*
		prefs.putInteger("int", 1234);
		prefs.putLong("long", Long.MAX_VALUE);
		prefs.putFloat("float", 1.2345f);
		prefs.putString("string", "test!");
		 
		if(prefs.getBoolean("bool") != true) throw new GdxRuntimeException("bool failed");
		if(prefs.getInteger("int") != 1234) throw new GdxRuntimeException("int failed");
		if(prefs.getLong("long") != Long.MAX_VALUE) throw new GdxRuntimeException("long failed");
		if(prefs.getFloat("float") != 1.2345f) throw new GdxRuntimeException("float failed");
		if(!prefs.getString("string").equals("test!")) throw new GdxRuntimeException("string failed");
		*/
		
	}
	
	
	/*************************************************************************************
	 * This renders stuff on the screen
	 *************************************************************************************/
	@Override
		public void draw(SpriteBatch batch, float parentAlpha) {
			super.draw(batch, parentAlpha);
			
	}


}
