package com.intelligentbeans.dare;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.intelligentbeans.boilerplate.GameScreen;
import com.intelligentbeans.boilerplate.PhysicalImage;

public class Spikes extends PhysicalImage implements EggBreaker{
	public Spikes(Vector2 position, World world) {	
    	super(position,"spikes",world, true, false, 2);
    
     		
    }
	
	public void updateX(float newX){
		body.setTransform((newX + 50) * GameScreen.WORLD_TO_BOX, getY() * GameScreen.WORLD_TO_BOX, body.getAngle());
		
	}
	
	public void updateY(float newY){
		body.setTransform(getX() * GameScreen.WORLD_TO_BOX, newY * GameScreen.WORLD_TO_BOX, body.getAngle());
		
	}
	
	/*************************************************************************************
	 * This handles physics stuff
	 *************************************************************************************/
    @Override
	public void act(float delta) {
		super.act(delta);
		
		setX(Math.round((body.getPosition().x * GameScreen.BOX_TO_WORLD) - (getWidth())/2) - 50);
		setY(Math.round((body.getPosition().y* GameScreen.BOX_TO_WORLD) - (getHeight())/2));
		
	}
}
