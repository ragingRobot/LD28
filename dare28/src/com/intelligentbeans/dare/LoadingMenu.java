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
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.GdxRuntimeException;
import com.intelligentbeans.boilerplate.SpriteImage;

public class LoadingMenu extends SpriteImage {
	
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
    protected int numLevels = 9;
    protected Array<LevelButton> buttons;
    protected int currentLevel = 1;
    SpriteImage logo;
    DareGameScreen game;
    SpriteImage border;
    float originalWidth= 0; 
    float originalHeight= 0;
	public LoadingMenu(Vector2 position, DareGameScreen game){
		super(position, "levelMenu");
		
		
		float xpos = Gdx.graphics.getWidth()/2 - (getWidth()/2);
		float ypos = Gdx.graphics.getHeight()/2 - (getHeight()/2);
		originalWidth = getWidth();
		originalHeight = getHeight();
		border = new SpriteImage(new Vector2(-xpos,-ypos),"boarder");
		SpriteImage box = new SpriteImage(new Vector2(0,0),"levelMenu");
		SpriteImage text = new SpriteImage(new Vector2(125,200),"loading");
		border.setWidth(Gdx.graphics.getWidth());
		border.setHeight(Gdx.graphics.getHeight());
		this.addActor( border);
		this.addActor( box);
		this.addActor( text);
		this.game = game;
	
	}
	
	public void updatePosition(){
		float xpos = -getX();
		float ypos = -getY();
		border.setX(xpos);
		border.setY(ypos);
		border.setWidth(Gdx.graphics.getWidth());
		border.setHeight(Gdx.graphics.getHeight());
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
			super.draw(batch, parentAlpha);
			
	}


}
