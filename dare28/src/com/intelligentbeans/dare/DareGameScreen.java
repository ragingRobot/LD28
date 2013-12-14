package com.intelligentbeans.dare;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.intelligentbeans.boilerplate.*;
public class DareGameScreen extends GameScreen {

	public DareGameScreen(String level, Game game) {
		super(level, game);
		
	}
	
	@Override
	protected void itemCreationLoop(JSONGameItem item){
		
		
		if (item.getItemType().equals("Player")) {
			Player test = new Player(new Vector2(item.getX(),item.getY()), world,stage);
			stage.addActor(test);
		}else if(item.getItemType().equals("Platform")){
			Platform platform = new Platform(new Vector2(item.getX(),item.getY()), world);
			stage.addActor(platform);
		}

		
		
		
		
	}
	
	

}
