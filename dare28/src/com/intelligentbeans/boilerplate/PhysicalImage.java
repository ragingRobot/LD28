package com.intelligentbeans.boilerplate;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.World;
import com.intelligentbeans.boilerplate.BodyFactory;

public class PhysicalImage extends SpriteImage {
	public Body body;
	public PhysicalImage (Vector2 position, String name, World world, boolean staticbody, boolean sensor, float friction){
		super(position, name);
		BodyType type = BodyType.DynamicBody;
		if(staticbody){
			type = BodyType.StaticBody;
		}
		body = BodyFactory.CreateSquareBody(world,type ,position.x, position.y , region.getRotatedPackedWidth(), region.getRotatedPackedHeight(), sensor, .005f, 0f, friction );
    	body.setUserData(this);
    	body.setSleepingAllowed(staticbody);
    
	}
	
	
	public PhysicalImage (Vector2 position, String name, World world, boolean staticbody, boolean sensor, float friction, float density){
		super(position, name);
		BodyType type = BodyType.DynamicBody;
		if(staticbody){
			type = BodyType.StaticBody;
		}
		body = BodyFactory.CreateSquareBody(world,type ,position.x, position.y , region.getRotatedPackedWidth(), region.getRotatedPackedHeight(), sensor, density, 0f, friction );
    	body.setUserData(this);
    	body.setSleepingAllowed(staticbody);
    
	}
	
	public void updateX(float newX){
		body.setTransform(newX * GameScreen.WORLD_TO_BOX, getY() * GameScreen.WORLD_TO_BOX, body.getAngle());
		
	}
	
	public void updateY(float newY){
		body.setTransform(getX() * GameScreen.WORLD_TO_BOX, newY * GameScreen.WORLD_TO_BOX, body.getAngle());
		
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
		
		setX(Math.round((body.getPosition().x * GameScreen.BOX_TO_WORLD) - (getWidth())/2));
		setY(Math.round((body.getPosition().y* GameScreen.BOX_TO_WORLD) - (getHeight())/2));
		
	}
   
	/*************************************************************************************
	 * This renders stuff on the screen
	 *************************************************************************************/
    @Override
   	public void draw(SpriteBatch batch, float parentAlpha) {
   		super.draw(batch, parentAlpha);
   }
}
