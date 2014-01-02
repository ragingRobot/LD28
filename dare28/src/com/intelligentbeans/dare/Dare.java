package com.intelligentbeans.dare;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.intelligentbeans.boilerplate.*;
public class Dare extends  Game {
	 protected Preferences prefs;
		@Override
		public void create() {		

			//ZTexturePacker.main();
			
			//this loads the first game screen
			
			prefs = Gdx.app.getPreferences("my-preferences");
			int currentLevel = prefs.getInteger("CurrentLevel");
			
			if (currentLevel == 0){
				currentLevel = 1;
			
			}
			
			
			setScreen(new DareGameScreen("data/levels/"+currentLevel+".json",this));
			//setScreen(new LevelMenu(this));

			
		}
	}
