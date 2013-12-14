package com.intelligentbeans.boilerplate;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;

public class BodyFactory {
	/**************************************************************************************************************************
	 * This Class is used as a static interface between box 2d and my code.
	 **************************************************************************************************************************/
	
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//PUBLIC METHODS
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	/*************************************************************************************
	 * This returns a physics body for a square (Overload)
	 *************************************************************************************/
	static public Body CreateSquareBody(World world, BodyType bodyType, float posx,float posy, float width, float height, Boolean sensor ,float density, float restitution, float friction) {
		BodyDef bodyDef = new BodyDef();
		bodyDef.type = bodyType;
		bodyDef.position.set(posx * GameScreen.WORLD_TO_BOX, posy * GameScreen.WORLD_TO_BOX);
		bodyDef.fixedRotation = true;
		
		Body body = world.createBody(bodyDef);
		MakeSquareBody(body, width, height , bodyType, density, restitution, 0, friction, sensor);
		
		return body;
	}


	/*************************************************************************************
	 * This returns a physics body for a circle
	 *************************************************************************************/
	static public Body CreateCircleBody(World world, BodyType bodyType, float posx,float posy, float radius, float density) {
		BodyDef bodyDef = new BodyDef();
		bodyDef.type = bodyType;
		bodyDef.position.set(posx, posy);
		bodyDef.angle = 0;

		Body body = world.createBody(bodyDef);
		MakeCircleBody(body, radius, bodyType, density, 0, 0, 0);
		return body;
	}
	
	
	/*************************************************************************************
	 * This returns a physics body for a circle
	 *************************************************************************************/
	static public Body CreateCircleBody(World world, BodyType bodyType, float posx,float posy, float radius, boolean sensor) {
		
		//this is an override of the method above
		
		BodyDef bodyDef = new BodyDef();
		bodyDef.type = bodyType;
		bodyDef.position.set(posx, posy);
		bodyDef.angle = 0;

		Body body = world.createBody(bodyDef);
		MakeCircleBody(body, radius, bodyType, 1, 1, 0, 0, sensor);
		return body;
	}
	
	/*************************************************************************************
	 * This returns a physics body for a platform
	 *************************************************************************************/
	 static public void createPlatformBody(World world,float x, float y, float width, float height) {
			// Create our body definition
			BodyDef groundBodyDef = new BodyDef();
			// Set its world position
			groundBodyDef.position.set(x + width/2, y + height/2);

			// Create a body from the defintion and add it to the world
			Body platformBody = world.createBody(groundBodyDef);

			// Create a polygon shape
			PolygonShape groundBox = new PolygonShape();
			// Set the polygon shape as a box which is twice the size of our view
			// port and 10 high
			groundBox.setAsBox(width/2, height/2);
			// Create a fixture from our polygon shape and add it to our ground body
			platformBody.createFixture(groundBox, 0.0f);
			
			groundBox.dispose();
			
		}
	 
	 
	 
	 
	 /*************************************************************************************
		 * This returns a physics body for a platform
		 *************************************************************************************/
		 static public void createIcePlatformBody(World world,float x, float y, float width, float height) {
				// Create our body definition
				BodyDef groundBodyDef = new BodyDef();
				// Set its world position
				groundBodyDef.position.set(x + width/2, y + height/2);

				// Create a body from the defintion and add it to the world
				Body platformBody = world.createBody(groundBodyDef);
				
				// Create a polygon shape
				PolygonShape groundBox = new PolygonShape();
				// Set the polygon shape as a box which is twice the size of our view
				// port and 10 high
				groundBox.setAsBox(width/2, height/2);
				// Create a fixture from our polygon shape and add it to our ground body
				Fixture fix = platformBody.createFixture(groundBox, 0.0f);
				fix.setFriction(0.01f);
				groundBox.dispose();
				
			}

	
	
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//These are used to create the fixtures
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	static protected void MakeSquareBody(Body body, float width, float height,BodyDef.BodyType bodyType, float density, float restitution,float angle, float friction) {

		FixtureDef fixtureDef = new FixtureDef();
		fixtureDef.density = density;
		fixtureDef.restitution = restitution;
		fixtureDef.friction = friction;
		fixtureDef.shape = new PolygonShape();
	
		((PolygonShape) fixtureDef.shape).setAsBox((width * GameScreen.WORLD_TO_BOX) / 2,(height * GameScreen.WORLD_TO_BOX) / 2);

		body.createFixture(fixtureDef);
		fixtureDef.shape.dispose();
	}
	
	
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//These are used to create the fixtures
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	static protected void MakeSquareBody(Body body, float width, float height,BodyDef.BodyType bodyType, float density, float restitution,float angle, float friction, Boolean sensor) {

		FixtureDef fixtureDef = new FixtureDef();
		fixtureDef.density = density;
		fixtureDef.restitution = restitution;
		fixtureDef.friction = friction;
		fixtureDef.shape = new PolygonShape();
		fixtureDef.isSensor = sensor;
	
		((PolygonShape) fixtureDef.shape).setAsBox((width * GameScreen.WORLD_TO_BOX) / 2,(height * GameScreen.WORLD_TO_BOX) / 2);

		body.createFixture(fixtureDef);
		fixtureDef.shape.dispose();
	}

	static protected void MakeCircleBody(Body body, float radius, BodyDef.BodyType bodyType,float density, float restitution, float angle, float friction) {

		FixtureDef fixtureDef = new FixtureDef();
		fixtureDef.density = density;
		fixtureDef.restitution = restitution;
		fixtureDef.friction = friction;
		fixtureDef.shape = new CircleShape();
		fixtureDef.shape.setRadius(radius * GameScreen.WORLD_TO_BOX);

		body.createFixture(fixtureDef);
		fixtureDef.shape.dispose();
	}
	
	static void MakeCircleBody(Body body, float radius, BodyDef.BodyType bodyType, float density, float restitution, float angle, float friction, boolean sensor) {

		FixtureDef fixtureDef = new FixtureDef();
		fixtureDef.density = density;
		fixtureDef.restitution = restitution;
		fixtureDef.friction = friction;
		fixtureDef.shape = new CircleShape();
		fixtureDef.shape.setRadius(radius * GameScreen.WORLD_TO_BOX);
		fixtureDef.isSensor = sensor;

		body.createFixture(fixtureDef);
		fixtureDef.shape.dispose();
	}
}
