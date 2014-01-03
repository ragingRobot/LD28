package com.intelligentbeans.boilerplate;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.FPSLogger;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;

public class GameScreen implements Screen, ContactListener {

	// //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	// STATIC PROPERTIES
	// //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public static final float WORLD_TO_BOX = 0.01f;
	public static final float BOX_TO_WORLD = 100f;

	// //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	// PRIVATE/PROTECTED STUFF
	// //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	protected World world;
	protected Stage background;
	protected Stage stage;
	protected Stage staticStage;
	protected Box2DDebugRenderer debugRenderer;
	protected FPSLogger logger;
	protected String level;
	protected SpriteBatch batch;
	protected String backgroundImage = "data/backgrounds/background.jpg";
	protected boolean lightsOn = false;
	protected String music = "";
	protected Stage loading;
	protected Array<JSONGameItem> items;
	protected boolean paused = false;
	protected boolean distroyed = false;
	AssetManager manager;
	// //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	// SETUP AND SCREEN RESIZE STUFF
	// //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public GameScreen(String level, Game game) {

		start(level, game, null, null);

	}

	public GameScreen(String level, Game game, Float startX, Float startY) {

		start(level, game, startX, startY);

	}

	protected void start(String level, Game game, Float startX, Float startY) {
		this.setLevel(level);

		batch = new SpriteBatch();
		background = new Stage(Gdx.graphics.getWidth(),Gdx.graphics.getHeight(), true, batch);
		stage = new Stage(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(),true, batch);
		loading = new Stage(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(),true, batch);
		staticStage = new Stage(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(),true, batch);
		logger = new FPSLogger();
		world = new World(new Vector2(0, -10f), true);
		debugRenderer = new Box2DDebugRenderer();
		manager = new AssetManager();
		
		//TextureAtlasLoader
		manager.load("data/z/dare-textures", TextureAtlas.class);
		
		
		

		Gdx.input.setInputProcessor(staticStage);
		world.setContactListener(this);

		addInterface();
		

		stage.addAction(Actions.alpha(0));
		background.addAction(Actions.alpha(0));
		loading.addAction(Actions.alpha(0));
		staticStage.addAction(Actions.alpha(0));
		
		staticStage.addAction(Actions.fadeIn(.5f));
		background.addAction(Actions.fadeIn(.5f));
		stage.addAction(Actions.fadeIn(.5f));
		
		
		
	}

	/*************************************************************************************
	 * This handles when the window is resized
	 *************************************************************************************/
	@Override
	public void resize(int width, int height) {
		stage.setViewport(width , height , false);
		staticStage.setViewport(width , height , false);

	}

	@Override
	public void show() {
		loadLevel(level);
	}
	
	


	/*************************************************************************************
	 * This takes the name of a JSON file then creates the level based on that
	 * file
	 *************************************************************************************/
	@SuppressWarnings("unchecked")
	protected void loadLevel(String levelToLoad) {

		// this loads the json file with our level
		FileHandle file = Gdx.files.internal(levelToLoad);
		Json json = new Json();

		// this converts the json objects into java objects that we can read
		// easily
	
		items = json.fromJson(Array.class, JSONGameItem.class, file);

		// loop through the contents of the level file and create the objects
		// spcified
		for (JSONGameItem item : items) {

			if (item.getItemType().equals("LevelDetail")) {

				/************************************************************************************************
				 * This sets the details of the level
				 * 
				 */
				backgroundImage = item.background;
				lightsOn = item.lights;
				music = item.music;

			} 
			
			itemCreationLoop(item);

		}

		background.addActor(new Background(backgroundImage));
		SoundManager.getInstance().loadSong(music);
		
		loadComplete();
		

	}
	
	public void loadComplete(){
		
	}
	protected void itemCreationLoop(JSONGameItem item){
		
	}
	/*************************************************************************************
	 * This adds the user interface needed based on the type of device
	 *************************************************************************************/
	protected void addInterface() {

		

	}

	// //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	// GAME LOOP STUFF
	// //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	/*************************************************************************************
	 * This renders stuff on the screen
	 *************************************************************************************/
	@Override
	public void render(float delta) {
		
		 if(manager.update()) {
	         // we are done loading, let's move to another screen!
	      }
		 
		 
		if(!distroyed){
				Camera camera = stage.getCamera();
			
				// pan the camera to the player
		
				camera.update();
		
				// this clears the canvas so we can start drawing a clean frame
				Gdx.gl.glClearColor(0.1f, 0.1f, 0.1f, 1);
				Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
				if(!paused){
				
					background.act(delta);
				}
					background.draw();
		
		
				
					if(!paused){	// this animates then draws the main game layer
					stage.act(delta);
					}
					
					
					stage.draw();
				
				// this animated then draws the layer with stationary controlls on it
				staticStage.act(delta);
				staticStage.draw();
				
				
				loading.act(delta);
				loading.draw();
		
				
			
				// this renders the physics debugger.
				//debugRenderer.render(world, camera.combined.scale(100,100,100));
		
				if(!paused){
				// this steps the physics engine
				world.step(1 / 60f, 6, 2);
				}
		
				// this logs our FPS to the console. We can romove this in production
				logger.log();
		}

	}

	/*************************************************************************************
	 * This handles collisions detected by Box2d
	 *************************************************************************************/
	
	public void showLoader(){
		TextureAtlas atlas = new TextureAtlas(Gdx.files.internal("data/menu/z-menu-textures"));
		AtlasRegion loadingRegion = atlas.findRegion("loading");
		TextureRegionDrawable loadingImage  = new TextureRegionDrawable(loadingRegion);
		

		loading.addActor(new MenuBackground());
		ImageButton LevelLoader = new ImageButton(loadingImage);
	    LevelLoader.setX( (Gdx.graphics.getWidth()/2 ) - (loadingRegion.packedWidth/2));
	    LevelLoader.setY(  (Gdx.graphics.getHeight()/2 ) - (loadingRegion.packedHeight/2) );
	      
	    loading.addActor( LevelLoader );
	            
		loading.addAction(Actions.fadeIn(.2f));
	}

	
	// /////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	// INTERFACE IMPLEMENTATION GRAVEYARD
	// //////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	@Override
	public void preSolve(Contact contact, Manifold oldManifold) {
		// TODO Auto-generated method stub
	}

	@Override
	public void postSolve(Contact contact, ContactImpulse impulse) {
		// TODO Auto-generated method stub
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		distroyed = true;
		batch.dispose();
		background.dispose();
		stage.dispose();
		loading.dispose();
		staticStage.dispose();
		world.dispose();
		debugRenderer.dispose();
		

	}

	public void setLevel(String level) {
		this.level = level;
	}

	public String getLevel() {
		return level;
	}

	@Override
	public void beginContact(Contact contact) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void endContact(Contact contact) {
		// TODO Auto-generated method stub
		
	}

}
