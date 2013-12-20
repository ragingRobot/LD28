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
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.Timer.Task;
import com.intelligentbeans.boilerplate.ParticalActor;
import com.intelligentbeans.boilerplate.GameScreen;
import com.intelligentbeans.boilerplate.PhysicalImage;

public class Player extends PhysicalImage{
	final static float MAX_VELOCITY = 2f;	
	final static float MAX_JUMP = 4f;
	private static final int WALK_CYCLE = 2;
	private Fixture bottomSensor;
	private Fixture eggholderSensor;
    public Boolean jumpDown =false;
    public Boolean rightDown = false;
    public Boolean leftDown = false;
    public boolean leftbuttonpressed = false;
    public boolean rightbuttonpressed = true;
    public boolean jumpbuttonpressed = false;
    private ParticalActor particals;
    public Egg egg;
    public World world;
    public Boolean walking = true;
	private int frameCount = 0;
	private String direction = "walk";
	private String sad ="";
	private Stage stage;
	private String directionmoving = "";
	private Vector2 deathPos;
	private DareGameScreen gameScreen;
	boolean started = false;
	boolean resetJump = true;
	
	public Player(Vector2 position,  World world, Stage stage, DareGameScreen screen){
		super(position, "player-happy",world, false, true,.2f);
		this.gameScreen = screen;
		this.world = world;
		egg = new Egg(new  Vector2(position.x + 55,position.y + region.getRegionHeight()),world);
		this.stage= stage;

		
		
		
		
		
		float posX = 30 *GameScreen.WORLD_TO_BOX;
		float posY =  - 27* GameScreen.WORLD_TO_BOX;
		  // Create a bodyShape shape and set its radius to 6
        PolygonShape bottombodyShape = new PolygonShape();

        bottombodyShape.setAsBox(2 * GameScreen.WORLD_TO_BOX , 55 * GameScreen.WORLD_TO_BOX , new Vector2(posX,posY),0); 
       

       

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
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        posX =  0;
		posY =  10 * GameScreen.WORLD_TO_BOX;
        
        
        // Create a bodyShape shape and set its radius to 6
        PolygonShape eggholderbodyShape = new PolygonShape();
        eggholderbodyShape.setAsBox( .1f * GameScreen.WORLD_TO_BOX, 20 * GameScreen.WORLD_TO_BOX , new Vector2(posX,posY),0); 
       

       

        // Create a fixture definition to apply our shape to
        FixtureDef eggholderfixtureDef = new FixtureDef();
        eggholderfixtureDef.shape = eggholderbodyShape;
        eggholderfixtureDef.density = 0f; 
        eggholderfixtureDef.friction = 0f;
        eggholderfixtureDef.restitution = 0.2f; // Make it bounce a little bit
        //bottomfixtureDef.isSensor = true;
        
        
   
        
        // Create our fixture and attach it to the body
        eggholderSensor = body.createFixture(eggholderfixtureDef);
       
        
        // Remember to dispose of any shapes after you're done with them!
        // BodyDef and FixtureDef don't need disposing, but shapes do.
        eggholderbodyShape.dispose();
        
        
        
        particals = new ParticalActor("data/death.p");
        stage.addActor(particals);
		
	}
	
	public void reset(){

		
		
		this.setVisible(true);
        rightDown = false;
        bottomSensor.setSensor(false);
        eggholderSensor.setSensor(false);
        bottomSensor.getBody().setLinearVelocity(new Vector2(0,0));
        
        
	    jumpDown =false;
	    rightDown = false;
	    leftDown = false;
	    leftbuttonpressed = false;
	    jumpbuttonpressed = false;
	    walking = true;
		frameCount = 0;
		direction = "walk";
		sad ="";
		directionmoving = "";
		started = false;
		resetJump = true;
		
		
		setY(Math.round((body.getPosition().y/GameScreen.WORLD_TO_BOX) - (getHeight())/2) - 5);
		setX(Math.round((body.getPosition().x * GameScreen.BOX_TO_WORLD) - (getWidth())/2));
		
		
		
		egg.body.setLinearVelocity(new Vector2(0,0));
		egg.body.setTransform((getX() + 105) * GameScreen.WORLD_TO_BOX, ( getY() + region.getRegionHeight()) * GameScreen.WORLD_TO_BOX, egg.body.getAngle());
		egg.reset();

	
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
		
		if(Gdx.input.isKeyPressed(Keys.A) || leftbuttonpressed){
			leftDown = true;
		}else{
			leftDown = false;
		}
		
		/*
		if(Gdx.input.isKeyPressed(Keys.D)){
			rightbuttonpressed = true;
		}else if(Gdx.app.getType() != ApplicationType.Android){
			rightbuttonpressed = false;
		}
		*/
		if(Gdx.input.isKeyPressed(Keys.SPACE) || jumpbuttonpressed){
			if(started){
				jumpDown = true;
			}
		}else{
			jumpDown = false;
			
		}
		
		
	   	if(leftDown && vel.x > 0) {
	   		
	   		if(!egg.broken){
		   		bottomSensor.getBody().applyLinearImpulse(-.002f, 0, pos.x, pos.y, true);
		   		//direction = "leftwalk";
		   		//walking = true;
		   		//directionmoving = "left";
	   		}
	   	} else if( rightDown && vel.x < MAX_VELOCITY) {
	   		bottomSensor.getBody().applyLinearImpulse(.02f, 0, pos.x, pos.y ,true);
	   		direction = "walk";
	   		walking = true;
	   		directionmoving = "";
	   	}else if(!rightDown && !leftDown ){
	   		walking = false;
	   	}
	   	
	   	/*
	   	if(walking && rightbuttonpressed && vel.x < MAX_VELOCITY + 3){
	   		bottomSensor.getBody().applyLinearImpulse(.005f, 0, pos.x, pos.y ,true);
	   	}
	   	*/
	   	if( jumpDown){
	   		jump();
	   	}
	   	
    	
	   	
	   	
	   	if(egg.broken){
	   		
	   		sad = "sad";
	   		rightDown = false;
	   		directionmoving = "left";
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
                if(contact.isTouching() && (contact.getFixtureA() == body.getFixtureList().first() || contact.getFixtureB() == body.getFixtureList().first())) {                            

                        Vector2 pos = body.getPosition();
                        
                
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
       if(isGrounded() && !egg.broken){
    	   Vector2 vel = body.getLinearVelocity();
    	   if(vel.y < MAX_JUMP  && resetJump){
    		   bottomSensor.getBody().applyForceToCenter(0f, 6f, true);
    	   }if(vel.y >= MAX_JUMP  && resetJump){
    		   resetJump = false;
    	   }else if (!resetJump && vel.y < MAX_JUMP/5){
    		   resetJump = true;
    	   }else{
    
    		   jumpDown = false;
    		   
    	   }
       }
       
    }
    
    
    /*************************************************************************************
	 * This plays the death animation and moves the player back to the start of the level
	 *************************************************************************************/
    public void die(){
    	this.setVisible(false);
    	particals.x = getX() + 100;
    	particals.y = getY();
        particals.start();
        deathPos = new Vector2(getX(), getY());
        rightDown = false;
        bottomSensor.setSensor(true);
        eggholderSensor.setSensor(true);
        bottomSensor.getBody().setLinearVelocity(new Vector2(0,0));
        
    }
   
}
