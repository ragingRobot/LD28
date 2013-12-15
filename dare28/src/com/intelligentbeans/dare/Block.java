package com.intelligentbeans.dare;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.intelligentbeans.boilerplate.GameScreen;
import com.intelligentbeans.boilerplate.PhysicalImage;

public class Block extends PhysicalImage  implements EggBreaker{
	public boolean moving = false;
	private float maxHeight;
	private float minHeight;
	private int speedy = 5;
	private float startY;
	public Block(Vector2 position, World world) {	
    	super(position,"block",world, true, false, 2);
    	body.setGravityScale(0);
    	maxHeight = position.y + 180;
    	minHeight = position.y - 180;
    	startY = body.getPosition().y;
     		
    }
	public void setMoving(boolean move){
		moving = move;
		body.setTransform(body.getPosition().x,startY, body.getAngle());
	}
	  /*************************************************************************************
	* This handles physics stuff
	*************************************************************************************/
    @Override
	public void act(float delta) {
		super.act(delta);
		if(moving){
		if(getY() >= maxHeight || getY() <= minHeight){
			speedy = speedy * -1;
		}
		
		float newY  = ((body.getPosition().y * GameScreen.BOX_TO_WORLD) + speedy) * GameScreen.WORLD_TO_BOX;
		
		
		body.setTransform(body.getPosition().x,newY, body.getAngle());
		}
		
		
    }
}
