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
    protected int numLevels = 9;
    protected Array<LevelButton> buttons;
    protected int currentLevel = 1;
    SpriteImage logo;
    DareGameScreen game;
	public LevelMenu(Vector2 position, Stage stage, DareGameScreen game){
		super(position, "levelMenu");
		this.game = game;
		buttons = new Array<LevelButton>(numLevels);
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
		//prefs.clear();
		//prefs.flush();
		int highestLevel = prefs.getInteger("HigestLevelComplete");
		
		if(highestLevel == 0){
			highestLevel = 1;
		}
		currentLevel = prefs.getInteger("CurrentLevel");
		
		if(currentLevel == 0){
			currentLevel =1;
		}
		Gdx.app.log("highLevel ",highestLevel + "");
		Gdx.app.log("currentLevel ", currentLevel + "");
		
		
		logo=  new SpriteImage(new Vector2(getWidth()/2  - 125,getHeight() - 140), "levelselect");
		//stage.addActor(logo);
		
		this.addActor(logo);
		int xCount = 0;
		int yCount = 0;
		
		for( int i = 1; i<= numLevels; i ++){
			LevelButton button;
			if( i <= highestLevel ){
				
					button =  new LevelButton(position, true,i);
			
			}else {
				button =  new LevelButton(position, false,i);
			}
			
			
			button.setX(225 + (xCount * (button.getWidth() + 10)));
			button.setY((this.getHeight()- 270) - (yCount * (button.getHeight() + 10)));
			
			final LevelMenu proxy = this;
			
			button.addListener(new InputListener() {
				public boolean touchDown(InputEvent event, float x, float y,int pointer, int button) {
					int level = ((LevelButton)event.getTarget()).number;
					proxy.game.loadNewLevel("data/levels/"+ level +".json", level);
					proxy.game.toggleLevelMenu();
					return true;
				}

				public void touchUp(InputEvent event, float x, float y,int pointer, int button) {
					
				}

			});
			
			buttons.add(button);
			
			this.addActor(button);
			
			xCount ++;
			if(xCount > 2){
				xCount = 0;
				yCount ++;
			}
		}
	
	}
	

	
	
	public void hideButtons(){
		logo.setVisible(false);
	
			for( int i = 0; i< numLevels; i ++){
				LevelButton button = buttons.get(i);
				button.setVisible(false);
				
			}
	}
	
	public void showButtons(){
		this.toFront();
		logo.toFront();
		logo.setVisible(true);
		for( int i = 0; i< numLevels; i ++){
			LevelButton button = buttons.get(i);
			button.setVisible(true);
			button.toFront();
			
		}
	}
	
	
	
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//GAME LOOP STUFF	
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	/*************************************************************************************
	* This handles physics stuff
	*************************************************************************************/
	@Override
	public void act(float delta) {
		this.toFront();
		super.act(delta);		
	}
	
	public void openLevel(int level){
		saveLevelProgress(level);

	}
	
	public void saveLevelProgress(int level){
		prefs.putInteger("CurrentLevel", level);
		
		if(prefs.getInteger("HigestLevelComplete") < level){
			prefs.putInteger("HigestLevelComplete", level);
			
		}
		prefs.flush();
		
	}
	
	
	/*************************************************************************************
	 * This renders stuff on the screen
	 *************************************************************************************/
	@Override
		public void draw(SpriteBatch batch, float parentAlpha) {
			super.draw(batch, parentAlpha);
			
	}


}
