package com.intelligentbeans.dare;
import com.badlogic.gdx.Game;
import com.intelligentbeans.boilerplate.*;
public class Dare extends  Game {
		
		@Override
		public void create() {		

			//ZTexturePacker.main();
			
			//this loads the first game screen
			setScreen(new DareGameScreen("data/levels/level.json",this));
			//setScreen(new LevelMenu(this));

			
		}
	}

