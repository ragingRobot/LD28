package com.intelligentbeans.dare;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Application.ApplicationType;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.WorldManifold;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Array;
import com.intelligentbeans.boilerplate.GameScreen;
import com.intelligentbeans.boilerplate.PhysicalImage;

public class Player extends PhysicalImage{
	final static float MAX_VELOCITY = 2f;	
	final static float MAX_JUMP = 3f;
	private static final int WALK_CYCLE = 2;
	private Fixture bottomSensor;
	
    public Boolean jumpDown =false;
    public Boolean rightDown = false;
    public Boolean leftDown = false;
    public Egg egg;
    public World world;
    public Boolean walking = false;
	private int frameCount = 0;
	private String direction = "walk";
	private String sad ="";
	private Stage stage;
	private String directionmoving = "";
	public Player(Vector2 position,  World world, Stage stage){
		super(position, "player-happy",world, false, true,.2f);
		this.world = world;
		egg = new Egg(new  Vector2(position.x + 50,position.y + region.getRegionHeight()),world);
		this.stage= stage;

		
		
		
		
		
		float posX =0;
		float posY =  -(region.getRegionHeight() * GameScreen.WORLD_TO_BOX / 4);
		  // Create a bodyShape shape and set its radius to 6
        PolygonShape bottombodyShape = new PolygonShape();
        bottombodyShape.setAsBox(region.getRegionWidth() * GameScreen.WORLD_TO_BOX /4, region.getRegionHeight() * GameScreen.WORLD_TO_BOX / 4, new Vector2(posX,posY),0); 
       

       

     // Create a fixture definition to apply our shape to
        FixtureDef bottomfixtureDef = new FixtureDef();
        bottomfixtureDef.shape = bottombodyShape;
        bottomfixtureDef.density = 0f; 
        bottomfixtureDef.friction = 2f;
        bottomfixtureDef.restitution = 0.2f; // Make it bounce a little bit
        //bottomfixtureDef.isSensor = true;
        
        
   
        
     // Create our fixture and attach it to the body
        bottomSensor = body.createFixture(bottomfixtureDef);
       
        
        // Remember to dispose of any shapes after you're done with them!
        // BodyDef and FixtureDef don't need disposing, but shapes do.
        bottombodyShape.dispose();
		
	}
	
	public void addEgg(){
		stage.addActor(egg);

	}
	
	
	  /*************************************************************************************
	* This handles physics stuff
	*************************************************************************************/
    @Override
	public void act(float delta) {
		super.act(delta);		
		
		
		setY(Math.round((body.getPosition().y/GameScreen.WORLD_TO_BOX) - (getHeight())/2) - 5);
	
		Vector2 vel = bottomSensor.getBody().getLinearVelocity();
		Vector2 pos = bottomSensor.getBody().getPosition();
		
	
   	
	   	// apply left impulse, but only if max velocity is not reached yet
	   	if((Gdx.input.isKeyPressed(Keys.A)|| leftDown) && vel.x > -MAX_VELOCITY) {
	   		bottomSensor.getBody().applyLinearImpulse(-.02f, 0, pos.x, pos.y, true);
	   		direction = "leftwalk";
	   		walking = true;
	   		directionmoving = "left";
	   	} else if((Gdx.input.isKeyPressed(Keys.D)|| rightDown) && vel.x < MAX_VELOCITY) {
	   		bottomSensor.getBody().applyLinearImpulse(.02f, 0, pos.x, pos.y ,true);
	   		direction = "walk";
	   		walking = true;
	   		directionmoving = "";
	   	}else if(!Gdx.input.isKeyPressed(Keys.D) && !Gdx.input.isKeyPressed(Keys.A) && Gdx.app.getType() != ApplicationType.Android){
	   		walking = false;
	   	}else if(!rightDown && !leftDown && Gdx.app.getType() == ApplicationType.Android){
	   		walking = false;
	   	}
	   	
	   	if(Gdx.input.isKeyPressed(Keys.SPACE) || jumpDown){
	   		jump();
	   	}
	   	
    	
	   	
	   	
	   	if(egg.broken){
	   		
	   		sad = "sad";
	   	}
		if(frameCount >= 6  && walking){
		    	if(frame < WALK_CYCLE){
		    		frame++;
		    	}else{
		    		frame = 1;
		    	}
		    	region = atlas.findRegion(sad + direction + frame);
		    	setWidth(region.packedWidth);
				setHeight(region.packedHeight);
		    	frameCount = 0;
		}else if(!walking){
			if(egg.broken){
				region = atlas.findRegion( directionmoving+"player-sad");
				setWidth(region.packedWidth);
				setHeight(region.packedHeight);
			}else{
				region = atlas.findRegion(directionmoving+"player-happy");
				setWidth(region.packedWidth);
				setHeight(region.packedHeight);
			}
			
		}
		frameCount ++;
	    	
	 
		}
		
    
    
    public boolean isGrounded() {				
   	 
        Array<Contact> contactList = world.getContactList();
        for(int i = 0; i < contactList.size; i++) {
                Contact contact = contactList.get(i);
                if(contact.isTouching() && (contact.getFixtureA() == bottomSensor || contact.getFixtureB() == bottomSensor)) {                            

                        Vector2 pos = bottomSensor.getBody().getPosition();
                        
                
                        WorldManifold manifold = contact.getWorldManifold();
                        boolean below = true;
                        for(int j = 0; j < manifold.getNumberOfContactPoints(); j++) {
                                below = (manifold.getPoints()[j].y < pos.y);
                        }

                        if(below) {                                                                             
                                return true;                    
                        }

                        return false;
                }
        }
        return false;
    }
    
    
    /*************************************************************************************
	 * This makes the player jump if they are on the ground
	 *************************************************************************************/
    public void jump(){
       if(isGrounded()){
    	   Vector2 vel = body.getLinearVelocity();
    	   if(vel.y < MAX_JUMP * body.getGravityScale()){
    		   body.applyForceToCenter(0f, 4f * body.getGravityScale(), true);
    	   }
       }
       
    }
   
}
