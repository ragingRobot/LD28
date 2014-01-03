package com.intelligentbeans.dare;
import java.util.LinkedList;

import java.util.Iterator;
import java.util.List;
import java.util.Random;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Application.ApplicationType;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.FPSLogger;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.Timer.Task;
import com.intelligentbeans.boilerplate.*;

public class DareGameScreen extends GameScreen {
	Player player;
	private List<Platform> platformsl;
	private List<PhysicalImage> obstacles;
	private List<Block> blocksl;
	private List<Spikes> spikesl;
	private ImageButton resetbutton;
	private int meters = 0;
	int platformCount =0;
	boolean followPlayer = true;
	Game game;
	SpriteImage intro;
	boolean started = false;
	private String yourScoreName = "month 1 of 9";
	BitmapFont yourBitmapFontName;
	private double distanceApartObstacles = 1000;
	private ProgressBar progressBar;
	private float levellenght;
	private LevelMenu levelMenu;
	private boolean won = false;
	private int  currentLevel = 0;
	protected Preferences prefs;
	protected LoadingMenu loadingScreen;
	 
	public DareGameScreen(String level, Game game) {
		super(level, game);
		this.game = game;
		
		
		prefs = Gdx.app.getPreferences("my-preferences");
		currentLevel = prefs.getInteger("CurrentLevel");
		
		if (currentLevel == 0){
			currentLevel = 1;
			levelMenu.saveLevelProgress(1);
		}
		yourScoreName = "month "+currentLevel+" of 9";
		//yourBitmapFontName = new BitmapFont();
		yourBitmapFontName = new BitmapFont(Gdx.files.internal("data/fonts/meters.fnt"),Gdx.files.internal("data/fonts/meters.png"),false);
		

		if(Gdx.app.getType() == ApplicationType.Android) {
			intro = new SpriteImage(new Vector2((Gdx.graphics.getWidth()/2)-200, Gdx.graphics.getHeight() - 600),"intro-mobile");
			intro.addListener(new InputListener() {
				public boolean touchDown(InputEvent event, float x, float y,int pointer, int button) {
					start();
					return true;
				}

				public void touchUp(InputEvent event, float x, float y,int pointer, int button) {
					
				}

			});
		}else{
			intro = new SpriteImage(new Vector2((Gdx.graphics.getWidth()/2)-200, Gdx.graphics.getHeight() - 600),"intro");
		}
		intro.setX((Gdx.graphics.getWidth()/2)- (intro.getWidth()/2));
		intro.setY((Gdx.graphics.getHeight()/2) - (intro.getHeight()/2));
		staticStage.addActor(intro);
		
		
		Gdx.app.log("test", Gdx.graphics.getWidth() + "");
		
		if(Gdx.graphics.getWidth() < 850){
			((OrthographicCamera) staticStage.getCamera()).zoom = 1.3f;
			((OrthographicCamera) stage.getCamera()).zoom = 1.5f;
		
		}
		
		

		
	}
	
	
	@Override
	public void dispose() {
		super.dispose();
      

 }
	 
	 
	
	@Override
	protected void itemCreationLoop(JSONGameItem item){
		
		
		
		
		if (item.getItemType().equals("LevelDetail")) {

			this.levellenght =  item.levellenght;
			

		} else
		if (item.getItemType().equals("Player")) {
			player = new Player(new Vector2(item.getX(),item.getY()), world,stage, this);
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
		}else if(item.getItemType().equals("Block")){
			Block block = new Block(new Vector2(item.getX(),item.getY()), world);
			
			if(this.obstacles == null){
				this.obstacles = new LinkedList<PhysicalImage>();
			}
			
			if(this.blocksl == null){
				this.blocksl = new LinkedList<Block>();
			}
			
			Random random = new Random();
			block.setMoving(random.nextBoolean());
			
			stage.addActor(block);
			obstacles.add(block);
			blocksl.add(block);
			//Gdx.app.log("test","" + platforms.size);
		}else if(item.getItemType().equals("Spikes")){
			Spikes spikes = new Spikes(new Vector2(item.getX(),item.getY()), world);
			
			if(this.obstacles == null){
				this.obstacles = new LinkedList<PhysicalImage>();
			}
			
			if(this.spikesl == null){
				this.spikesl = new LinkedList<Spikes>();
			}
			stage.addActor(spikes);
			obstacles.add(spikes);
			spikesl.add(spikes);
			//Gdx.app.log("test","" + platforms.size);
		}

		
		
		
		
	}
	
	
	/*************************************************************************************
	 * This handles when the window is resized
	 *************************************************************************************/
	@Override
	public void resize(int width, int height) {
		super.resize(width, height);
		resetbutton.setBounds(Gdx.graphics.getWidth() - 177 - 30, Gdx.graphics.getHeight() - 60 - 30, 177, 60);
		intro.setX((Gdx.graphics.getWidth()/2)- (intro.getWidth()/2));
		intro.setY((Gdx.graphics.getHeight()/2) - (intro.getHeight()/2));
		
		
		int offsetTop = 90;	
		if(Gdx.graphics.getWidth() < 850){
			offsetTop = 0;
			resetbutton.setBounds(Gdx.graphics.getWidth() - 70, Gdx.graphics.getHeight(), 177, 60);
		}
		
		progressBar.setX((Gdx.graphics.getWidth()/2) - 263);
		progressBar.setY(Gdx.graphics.getHeight() - offsetTop);
		
		
		levelMenu.setX(Gdx.graphics.getWidth()/2 - levelMenu.getWidth()/2);
		levelMenu.setY(Gdx.graphics.getHeight()/2 - levelMenu.getHeight()/2);
		loadingScreen.setX(Gdx.graphics.getWidth()/2 - levelMenu.getWidth()/2);
		loadingScreen.setY(Gdx.graphics.getHeight()/2 - levelMenu.getHeight()/2);
		
		loadingScreen.updatePosition();

	}
	
	@Override
	public void loadComplete(){
		SoundManager.getInstance().loadSong("data/sounds/test.mp3");
		loadingScreen.setVisible(false);
	}
	/*************************************************************************************
	 * This adds the user interface needed based on the type of device
	 *************************************************************************************/
	@Override
	public void addInterface() {


		// Create a table that fills the screen. Everything else will go inside
		// this table.
		Table table = new Table();
		if(Gdx.app.getType() == ApplicationType.Android) {
			table.setFillParent(true);
		}else{
			table.setFillParent(false);
		}

		TextureAtlas atlas = new TextureAtlas(Gdx.files.internal("data/z/dare-textures"));
	

		TextureRegionDrawable jumpup = new TextureRegionDrawable(atlas.findRegion("jumpButton"));
		TextureRegionDrawable jumpdown = new TextureRegionDrawable(atlas.findRegion("jumpButtonDown"));

		TextureRegionDrawable leftup = new TextureRegionDrawable(atlas.findRegion("leftButton"));
		TextureRegionDrawable leftdown = new TextureRegionDrawable(atlas.findRegion("leftButtonDown"));

		TextureRegionDrawable rightup = new TextureRegionDrawable(atlas.findRegion("rightButton"));
		TextureRegionDrawable rightdown = new TextureRegionDrawable(atlas.findRegion("rightButtonDown"));
		
		TextureRegionDrawable resetup = new TextureRegionDrawable(atlas.findRegion("resetButton"));
		TextureRegionDrawable resetdown = new TextureRegionDrawable(atlas.findRegion("resetButtonDown"));
		TextureRegionDrawable levelup = new TextureRegionDrawable(atlas.findRegion("levelButton"));
		TextureRegionDrawable leveldown = new TextureRegionDrawable(atlas.findRegion("levelButtonDown"));

		// Create a button with the "default" TextButtonStyle. A 3rd parameter
		// can be used to specify a name other than "default".
		final ImageButton button = new ImageButton(jumpup, jumpdown);
		final ImageButton leftbutton = new ImageButton(leftup, leftdown);
		final ImageButton rightbutton = new ImageButton(rightup, rightdown);
		final ImageButton levelbutton = new ImageButton(levelup, leveldown);
		loadingScreen = new LoadingMenu(new Vector2(0,0), this);
		resetbutton = new ImageButton(resetup, resetdown);
		addLevelIndicator();
		
		
		button.setBounds(Gdx.graphics.getWidth() - 128 - 20, 15, 128, 128);
		leftbutton.setBounds(20, 15, 226, 226);
		rightbutton.setBounds(300, 5, 128, 128);
		resetbutton.setBounds(Gdx.graphics.getWidth() - 177 - 30, Gdx.graphics.getHeight() - 60 - 30, 177, 60);
		levelbutton.setX(20);
		levelbutton.setY(Gdx.graphics.getHeight() - 60 - 30);
		
		if(Gdx.graphics.getWidth() < 850){
			button.setBounds(Gdx.graphics.getWidth() - 30, -40, 128, 128);
			leftbutton.setBounds(-95, -40, 226, 226);
			
			resetbutton.setBounds(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), 177, 60);
			
			levelbutton.setX(-95);
			levelbutton.setY(Gdx.graphics.getHeight());
		}
		// table.add(button);

		button.addListener(new InputListener() {
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				player.jumpbuttonpressed  = true;
				return true;
			}

			public void touchUp(InputEvent event, float x, float y,
					int pointer, int button) {
				player.jumpbuttonpressed = false;
			}

		});

		leftbutton.addListener(new InputListener() {
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				player.leftbuttonpressed = true;
				return true;
			}

			public void touchUp(InputEvent event, float x, float y,
					int pointer, int button) {
				player.leftbuttonpressed = false;
			}

		});

		rightbutton.addListener(new InputListener() {
			public boolean touchDown(InputEvent event, float x, float y,int pointer, int button) {
				player.rightbuttonpressed = true;
				return true;
			}

			public void touchUp(InputEvent event, float x, float y,
					int pointer, int button) {
				player.rightbuttonpressed = false;
			}

		});
		
		
		
		resetbutton.addListener(new InputListener() {
			
			public boolean touchDown(InputEvent event, float x, float y,int pointer, int button) {
				//////////////RESET HERE
				reset();
				return true;
			}

			public void touchUp(InputEvent event, float x, float y,int pointer, int button) {
				
			}

		});
		
		
		
		
		
		final DareGameScreen proxy = this;
		
	levelbutton.addListener(new InputListener() {
			
			public boolean touchDown(InputEvent event, float x, float y,int pointer, int button) {
			
				return true;
			}

			public void touchUp(InputEvent event, float x, float y,int pointer, int button) {
				proxy.toggleLevelMenu();
			}

		});

		

		// this ads on screen controls for mobile devices
		if (Gdx.app.getType() == ApplicationType.Android) {
			// android specific code
			staticStage.addActor(leftbutton);
			//staticStage.addActor(rightbutton);
			staticStage.addActor(button);
	
		}
		
		staticStage.addActor(resetbutton);
		staticStage.addActor(levelbutton);
		staticStage.addActor(loadingScreen);
		loadingScreen.setVisible(true);
		resetbutton.setVisible(false);
		
		
		levelMenu = new LevelMenu(new Vector2(0,0), staticStage, this);
		levelMenu.setX(Gdx.graphics.getWidth()/2 - levelMenu.getWidth()/2);
		levelMenu.setY(Gdx.graphics.getHeight()/2 - levelMenu.getHeight()/2);
		loadingScreen.setX(Gdx.graphics.getWidth()/2 - levelMenu.getWidth()/2);
		loadingScreen.setY(Gdx.graphics.getHeight()/2 - levelMenu.getHeight()/2);
		levelMenu.setVisible(false);
		
		
	
	}
	public void toggleLevelMenu(){
		if(levelMenu.isVisible()){
			levelMenu.setVisible(false);
		}else{
			levelMenu.setVisible(true);
		}
	}
	
	void win(){
		///////////////////////////////////////////////////////////////////////////////////////WIN CODE HERE
		if(!won){
			won = true;
			paused = true;
			
			if(currentLevel == 0){
				currentLevel = 1;
			}
			if(currentLevel < 9){
				int nextlevel = currentLevel + 1;
				
				loadNewLevel("data/levels/"+ nextlevel +".json", nextlevel );
			
			}
		}
	}
	
	
	
	
	public void loadNewLevel(final String level, final int levelVal) {
		
		loadingScreen.setVisible(true);
		loadingScreen.toFront();
		
		
		
		
		float delay = .3f; // seconds

		Timer.schedule(new Task(){
		    @Override
		    public void run() {
		        // Do your work
		    	
		    	
		    	SoundManager.getInstance().stopSong();
				levelMenu.saveLevelProgress(levelVal);
				////////////////////////////////////////////////////////////////////////////////////////////////////////////////HERE
				Array<Body> iter = new Array<Body>();
				world.getBodies(iter);
				   for (int i =0 ; i < iter.size; i++) {
				     Body body = iter.get(i);
				     if(body!=null) {
				       
				          	world.destroyBody(body);
				            body.setUserData(null);
				            body = null;
				         
				     }
				   }
				   
				   
					player.remove();
					Array<Actor> list = stage.getActors();
				
					removeActorsFromArray(list.toArray());
					//removeActorsFromArray(obstacles.toArray(new Actor[obstacles.size()]));
					stage.getActors();
					platformsl.clear();
					obstacles.clear();
					blocksl.clear();
					spikesl.clear();
					
					currentLevel = levelVal;
					loadLevel(level);
					yourScoreName = "month " + currentLevel+" of 9";
					reset();
		    }
		}, delay);
		
		
		
		
	}
	
	protected void removeActorsFromArray(Actor[] actors){
		for(int i = 0 ; i < actors.length ; i++){
			actors[i].remove();
			actors[i].clear();
			actors[i].clearListeners();
			actors[i].setVisible(false);
			actors[i] = null;
		}
	}
	void addLevelIndicator(){
		int offsetTop = 90;
		if(Gdx.graphics.getWidth() < 850){
			offsetTop = 0;
		}
		progressBar = new ProgressBar(new Vector2((Gdx.graphics.getWidth()/2) - 263,Gdx.graphics.getHeight() - offsetTop),staticStage);

	//intro.setX((Gdx.graphics.getWidth()/2)- (intro.getWidth()/2));
	//intro.setY((Gdx.graphics.getHeight()/2) - (intro.getHeight()/2));
	
	}
	
	public void reset(){
		SoundManager.getInstance().stopSong();
		//game.setScreen(new DareGameScreen("data/levels/level.json",game));
		progressBar.setPercentage(0);
		
		SoundManager.getInstance().loadSong("data/sounds/test.mp3");
		
		int platformCounter = 0;
		int blockCounter = 0;
		int spikeCounter = 0;
		followPlayer = true;
		started = false;
		meters = 0;
		won = false;
		this.paused = false;
		
		intro.setVisible(true);
		resetbutton.setVisible(false);
		    	
		    	
		for (JSONGameItem item : items) {

			if (item.getItemType().equals("Player")) {
				
				
				player.body.setTransform(item.getX() * GameScreen.WORLD_TO_BOX, item.getY() * GameScreen.WORLD_TO_BOX, player.body.getAngle());
				player.reset();
				
			}else if(item.getItemType().equals("Platform")){
				
				
				Platform plat = platformsl.get(platformCounter);
				
				plat.body.setTransform(item.getX() * GameScreen.WORLD_TO_BOX, item.getY() * GameScreen.WORLD_TO_BOX, plat.body.getAngle());
				platformCounter ++;
				//Gdx.app.log("test","" + platforms.size);
			}else if(item.getItemType().equals("Block")){
				
				Block block = blocksl.get(blockCounter);
				
				block.body.setTransform(item.getX() * GameScreen.WORLD_TO_BOX, item.getY() * GameScreen.WORLD_TO_BOX, block.body.getAngle());
				
				Random random = new Random();
				block.setMoving(random.nextBoolean());
				
				blockCounter ++;
				
			}else if(item.getItemType().equals("Spikes")){
				Spikes spikes = spikesl.get(spikeCounter);
				
				spikes.body.setTransform(item.getX() * GameScreen.WORLD_TO_BOX, item.getY() * GameScreen.WORLD_TO_BOX, spikes.body.getAngle());
				spikeCounter ++;
			}
			
			

		}
		
	}
	public void start(){
		intro.setVisible(false);
		player.rightDown = true;
		started = true;
		
		float delay = .5f; // seconds

		Timer.schedule(new Task(){
		    @Override
		    public void run() {
		    	player.started = true;
		    	resetbutton.setVisible(true);
		    }
		}, delay);
	}
	@Override
	public void render(float delta) {
		if(!distroyed){
		super.render(delta);
		
		batch.begin(); 
		yourBitmapFontName.setColor(58.0f, 0.0f, 0.0f, 1.0f);
		yourBitmapFontName.draw(batch, yourScoreName,100, Gdx.graphics.getHeight() - 50); 
		batch.end(); 
		
		loadingScreen.toFront();
		
		if(levelMenu.isVisible()){
			paused = true;
		}else{
			paused = false;
		}
		
		if(!paused && !levelMenu.isVisible()){

		if(!started && Gdx.input.isKeyPressed(Keys.SPACE)){
			start();
			
		}
		
		Camera camera = stage.getCamera();
		
		if(player != null){
		
			if(player.getX() > obstacles.get( obstacles.size() - 3).getX()){
				
				PhysicalImage firstObstacle = obstacles.remove(0);
				firstObstacle.body.setTransform((float) ((obstacles.get( obstacles.size() - 1).getX() + distanceApartObstacles ) * GameScreen.WORLD_TO_BOX), firstObstacle.body.getPosition().y, firstObstacle.body.getAngle());
				obstacles.add(firstObstacle);
				
				if(firstObstacle instanceof Block){
					Random random = new Random();
					((Block) firstObstacle).setMoving(random.nextBoolean());
				}
			}
	
		
		if(platformsl.size() > 4){
			//right trigger
			if(player.getX() > platformsl.get(4).getX()){
				Platform first = platformsl.remove(0);
				first.body.setTransform(platformsl.get(platformsl.size()-1).body.getPosition().x + (422 * GameScreen.WORLD_TO_BOX), first.body.getPosition().y, first.body.getAngle());
				platformsl.add(first);
				meters ++;
			
				//Gdx.app.log( meters+ "/" + levellenght + "=", (meters/levellenght) * 100 + "" );
				progressBar.setPercentage((meters/levellenght) * 100);
			
				if(meters >= levellenght){
					
					win();
				}
			
			}
		}
		
		
		if(platformsl.size() > 4){
			
			if(player.getX() < platformsl.get(3).getX()){
				Platform last= platformsl.remove(platformsl.size()-1);
				last.body.setTransform(platformsl.get(0).body.getPosition().x - (422 * GameScreen.WORLD_TO_BOX), last.body.getPosition().y, last.body.getAngle());
				platformsl.add(0,last);
			}
		}
		
		// pan the camera to the player
		if (player != null && followPlayer) {
			camera.position.y += (player.getY() - camera.position.y + 2f) * .08f   + 10;
			camera.position.x += (player.getX() - camera.position.x) * .08f;


		}
		
		}
		
		camera.update();
		
		}
		
		
		}
	}
	
	
	
	
	
	/*************************************************************************************
	 * This handles collisions detected by Box2d
	 *************************************************************************************/
	@Override
	public void beginContact(Contact contact) {

		// this tells us what 2 objects collided
		Body a = contact.getFixtureA().getBody();
		Body b = contact.getFixtureB().getBody();

		// this is where we take action to address the collision
		if ((a.getUserData() instanceof Spikes && b.getUserData() instanceof Player)
				|| (a.getUserData() instanceof Player && b.getUserData() instanceof Spikes)) {

			final Spikes obstacle;

			if (a.getUserData() instanceof Spikes) {
				obstacle = (Spikes) a.getUserData();
			} else {
				obstacle = (Spikes) b.getUserData();
			}

			
				// if the player has hit an obstacle they must die :(
				player.die();
				followPlayer = false;
			
		} 
	}


	
	

}
