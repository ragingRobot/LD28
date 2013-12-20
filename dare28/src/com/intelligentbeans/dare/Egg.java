package com.intelligentbeans.dare;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.WorldManifold;
import com.badlogic.gdx.utils.Array;
import com.intelligentbeans.boilerplate.GameScreen;
import com.intelligentbeans.boilerplate.PhysicalImage;
import com.intelligentbeans.boilerplate.SoundManager;

public class Egg  extends PhysicalImage{
	 public World world;
	 public boolean broken = false;
	 public int breakX = 0;
	 public int breakY = 0;
	public Egg(Vector2 position,  World world){
		super(position, "egg",world, false, false, 10, .002f);
		this.world = world;
	}
	
	  /*************************************************************************************
	* This handles physics stuff
	*************************************************************************************/
    @Override
	public void act(float delta) {
		super.act(delta);		
		
		if(isGrounded() || broken){
			
			
			if(!broken){
				region = atlas.findRegion("egg-broken");
				setWidth(region.packedWidth);
				setHeight(region.packedHeight);
				breakX = Math.round((body.getPosition().x/GameScreen.WORLD_TO_BOX) - (getWidth())/2);
				breakY = Math.round((body.getPosition().y/GameScreen.WORLD_TO_BOX) - (getHeight())/2) - 30;
				body.getFixtureList().first().setSensor(true);
				broken = true;
				SoundManager.getInstance().stopSong();
				SoundManager.getInstance().playSound("data/sounds/death.mp3");
			}
		
		}
		
		if(broken){
			setX(breakX);
			setY(breakY);
		}
	 
	}
    
    public void reset(){
    	region = atlas.findRegion("egg");
		setWidth(region.packedWidth);
		setHeight(region.packedHeight);
		breakX = 0;
		breakY = 0;
		body.getFixtureList().first().setSensor(false);
		broken = false;
    	
    }
		
	public boolean isGrounded() {				
	   	 
        Array<Contact> contactList = world.getContactList();
        for(int i = 0; i < contactList.size; i++) {
                Contact contact = contactList.get(i);
                if(contact.isTouching() && (contact.getFixtureA() == body.getFixtureList().first() || contact.getFixtureB() == body.getFixtureList().first())) {                            

                        Vector2 pos = body.getPosition();
                        
                
                        WorldManifold manifold = contact.getWorldManifold();
                        boolean below = true;
                        for(int j = 0; j < manifold.getNumberOfContactPoints(); j++) {
                                below = (manifold.getPoints()[j].y < pos.y);
                        }

                        if(below) { 
                        	
                        	if(contact.getFixtureA().getBody().getUserData() instanceof EggBreaker || contact.getFixtureB().getBody().getUserData() instanceof EggBreaker )
                                return true;                    
                        }

                        return false;
                }
        }
        return false;
    }
	
	
}
