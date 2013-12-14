package com.intelligentbeans.boilerplate;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class ParticalActor extends SpriteImage{

	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//PUBLIC STUFF / should probably be changed to getters and setters
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public ParticleEffect particleEffect;
	public float x = 0;
	public float y = 0;
	
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//SETUP
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	/*************************************************************************************
	 * This creates a new particle system with the default fire particles
	 *************************************************************************************/
	public ParticalActor() {
		particleEffect = new ParticleEffect();
		particleEffect.load(Gdx.files.internal("data/test2.p"),Gdx.files.internal("data"));

		float pScale = 0.01f;

		float scaling = particleEffect.getEmitters().get(0).getScale().getHighMax();
		particleEffect.getEmitters().get(0).getScale().setHigh(scaling * pScale);

		scaling = particleEffect.getEmitters().get(0).getScale().getLowMax();
		particleEffect.getEmitters().get(0).getScale().setLow(scaling * pScale);

		scaling = particleEffect.getEmitters().get(0).getVelocity().getHighMax();
		particleEffect.getEmitters().get(0).getVelocity().setHigh(scaling * pScale);

		scaling = particleEffect.getEmitters().get(0).getVelocity().getLowMax();
		particleEffect.getEmitters().get(0).getVelocity().setLow(scaling * pScale);

	}
	
	/*************************************************************************************
	 * This is an override of the method above and allows you to input a specific particle
	 * file to use
	 *************************************************************************************/
	public ParticalActor(String particles) {
		particleEffect = new ParticleEffect();
		particleEffect.load(Gdx.files.internal(particles),Gdx.files.internal("data"));

		float pScale = 0.01f;

		float scaling = particleEffect.getEmitters().get(0).getScale().getHighMax();
		particleEffect.getEmitters().get(0).getScale().setHigh(scaling * pScale);

		scaling = particleEffect.getEmitters().get(0).getScale().getLowMax();
		particleEffect.getEmitters().get(0).getScale().setLow(scaling * pScale);

		scaling = particleEffect.getEmitters().get(0).getVelocity().getHighMax();
		particleEffect.getEmitters().get(0).getVelocity().setHigh(scaling * pScale);

		scaling = particleEffect.getEmitters().get(0).getVelocity().getLowMax();
		particleEffect.getEmitters().get(0).getVelocity().setLow(scaling * pScale);

	}
	
	/*************************************************************************************
	 * This starts the effect if it isn't continuous
	 *************************************************************************************/
	public void start(){
		particleEffect.start();
	}

	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//GAME LOOP STUFF
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	/*************************************************************************************
	 * This handles physics and that kind of stuff
	 *************************************************************************************/
	@Override
	public void act(float delta) {
		super.act(delta);
		particleEffect.setPosition(x, y);
		particleEffect.update(delta);

	}

	/*************************************************************************************
	 * This renders stuff
	 *************************************************************************************/
	@Override
	public void draw(SpriteBatch batch, float parentAlpha) {
		super.draw(batch, parentAlpha);
		particleEffect.draw(batch);
	}

}
