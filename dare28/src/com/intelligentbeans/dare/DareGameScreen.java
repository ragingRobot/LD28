package com.intelligentbeans.dare;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Application.ApplicationType;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.Touchpad;
import com.badlogic.gdx.scenes.scene2d.ui.Touchpad.TouchpadStyle;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Array;
import com.intelligentbeans.boilerplate.*;
public class DareGameScreen extends GameScreen {
	Player player;
	private List<Platform> platformsl;
	int platformCount =0;
	public DareGameScreen(String level, Game game) {
		super(level, game);
	
	}
	
	@Override
	protected void itemCreationLoop(JSONGameItem item){
		
		
		if (item.getItemType().equals("Player")) {
			player = new Player(new Vector2(item.getX(),item.getY()), world,stage);
			stage.addActor(player);
			player.addEgg();
		}else if(item.getItemType().equals("Platform")){
			Platform platform = new Platform(new Vector2(item.getX(),item.getY()), world);
			
			if(this.platformsl == null){
				this.platformsl = new LinkedList<Platform>();
			}
			stage.addActor(platform);
			platformsl.add(platform);
			//Gdx.app.log("test","" + platforms.size);
		}

		
		
		
		
	}
	
	
	/*************************************************************************************
	 * This adds the user interface needed based on the type of device
	 *************************************************************************************/
	@Override
	public void addInterface() {


		// Create a table that fills the screen. Everything else will go inside
		// this table.
		Table table = new Table();
		table.setFillParent(true);

		TextureAtlas atlas = new TextureAtlas(Gdx.files.internal("data/z/dare-textures"));
	

		TextureRegionDrawable jumpup = new TextureRegionDrawable(atlas.findRegion("jumpButton"));
		TextureRegionDrawable jumpdown = new TextureRegionDrawable(atlas.findRegion("jumpButtonDown"));

		TextureRegionDrawable leftup = new TextureRegionDrawable(atlas.findRegion("leftButton"));
		TextureRegionDrawable leftdown = new TextureRegionDrawable(atlas.findRegion("leftButtonDown"));

		TextureRegionDrawable rightup = new TextureRegionDrawable(atlas.findRegion("rightButton"));
		TextureRegionDrawable rightdown = new TextureRegionDrawable(atlas.findRegion("rightButtonDown"));

		// Create a button with the "default" TextButtonStyle. A 3rd parameter
		// can be used to specify a name other than "default".
		final ImageButton button = new ImageButton(jumpup, jumpdown);
		final ImageButton leftbutton = new ImageButton(leftup, leftdown);
		final ImageButton rightbutton = new ImageButton(rightup, rightdown);

		button.setBounds(Gdx.graphics.getWidth() - 128 - 20, 15, 128, 128);
		leftbutton.setBounds(20, 15, 128, 128);
		rightbutton.setBounds(200, 15, 128, 128);
		// table.add(button);

		button.addListener(new InputListener() {
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				player.jumpDown = true;
				return true;
			}

			public void touchUp(InputEvent event, float x, float y,
					int pointer, int button) {
				player.jumpDown = false;
			}

		});

		leftbutton.addListener(new InputListener() {
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				player.leftDown = true;
				return true;
			}

			public void touchUp(InputEvent event, float x, float y,
					int pointer, int button) {
				player.leftDown = false;
			}

		});

		rightbutton.addListener(new InputListener() {
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				player.rightDown = true;
				return true;
			}

			public void touchUp(InputEvent event, float x, float y,
					int pointer, int button) {
				player.rightDown = false;
			}

		});

		

		// this ads on screen controls for mobile devices
		if (Gdx.app.getType() == ApplicationType.Android) {
			// android specific code
				staticStage.addActor(leftbutton);
				staticStage.addActor(rightbutton);
		
			staticStage.addActor(button);
	
		}


	}
	
	
	
	@Override
	public void render(float delta) {
		super.render(delta);
		
		
		if(platformsl.size() > 4){
			
			if(player.getX() > platformsl.get(4).getX()){
				Platform first = platformsl.remove(0);
				first.body.setTransform(platformsl.get(platformsl.size()-1).body.getPosition().x + (422 * GameScreen.WORLD_TO_BOX), first.body.getPosition().y, first.body.getAngle());
				platformsl.add(first);
			}
		}
		
		
		if(platformsl.size() > 4){
			
			if(player.getX() < platformsl.get(3).getX()){
				Platform last= platformsl.remove(platformsl.size()-1);
				last.body.setTransform(platformsl.get(0).body.getPosition().x - (422 * GameScreen.WORLD_TO_BOX), last.body.getPosition().y, last.body.getAngle());
				platformsl.add(0,last);
			}
		}
		Camera camera = stage.getCamera();
		// pan the camera to the player
		if (player != null) {
			camera.position.y += (player.getY() - camera.position.y + 2f) * .08f   + 10;
			camera.position.x += (player.getX() - camera.position.x) * .08f;


		}
		camera.update();
	}


	
	

}
