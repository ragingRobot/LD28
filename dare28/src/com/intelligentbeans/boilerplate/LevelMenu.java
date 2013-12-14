package com.intelligentbeans.boilerplate;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;



public class LevelMenu implements Screen{
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//PRIVATE/PROTECTED STUFF
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////	
	private Stage background;
	private Stage staticStage;
	private Stage loading;
	private SpriteBatch batch;
    private static final float BUTTON_SPACING = 120f;
	private Game game;

	public LevelMenu(Game game){
		this.game = game;
		start();
		
		
	}
	
	private void start(){
		
		TextureAtlas atlas = new TextureAtlas(Gdx.files.internal("data/menu/z-menu-textures"));
		
		AtlasRegion emptyRegion = atlas.findRegion("empty");
		
		AtlasRegion largeRegion = atlas.findRegion("z");
		AtlasRegion optionsRegion = atlas.findRegion("options");
		
		TextureRegionDrawable z  = new TextureRegionDrawable(largeRegion);
		TextureRegionDrawable zdown  = new TextureRegionDrawable(atlas.findRegion("zdown"));
		TextureRegionDrawable empty  = new TextureRegionDrawable(emptyRegion);
		TextureRegionDrawable emptydown  = new TextureRegionDrawable(atlas.findRegion("emptydown"));
		TextureRegionDrawable jungle  = new TextureRegionDrawable(atlas.findRegion("jungle"));
		TextureRegionDrawable jungledown  = new TextureRegionDrawable(atlas.findRegion("jungledown"));
		
		
		TextureRegionDrawable options  = new TextureRegionDrawable(optionsRegion);
		TextureRegionDrawable optionsdown  = new TextureRegionDrawable(atlas.findRegion("optionsDown"));
		
		TextureRegionDrawable loadingImage  = new TextureRegionDrawable(atlas.findRegion("loading"));
		AtlasRegion logoRegion = atlas.findRegion("logo");
		TextureRegionDrawable logoImage = new TextureRegionDrawable(logoRegion);
		
		
		batch = new SpriteBatch();
		background = new Stage(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), true, batch);
		
		staticStage = new Stage();
		loading = new Stage();
		
		background.addActor(new MenuBackground());
		
		staticStage.addAction(Actions.alpha(0));
		background.addAction(Actions.alpha(0));
		loading.addAction(Actions.alpha(0));
		
		background.addAction(Actions.fadeIn(.5f));
		staticStage.addAction(Actions.fadeIn(.5f));
		
		Gdx.input.setInputProcessor(staticStage);
		
		
 
       
 
       
		ImageButton Level1 = new ImageButton(empty,emptydown);
        Level1.setX( (Gdx.graphics.getWidth()/2 ) - emptyRegion.packedWidth - (largeRegion.packedWidth ));
        Level1.setY( (Gdx.graphics.getHeight()/2 ) + BUTTON_SPACING);
    
        staticStage.addActor( Level1 );
 
       
        ImageButton Level2= new ImageButton(empty,emptydown);
        Level2.setX( (Gdx.graphics.getWidth()/2 ) - emptyRegion.packedWidth  -(largeRegion.packedWidth ));
        Level2.setY( (Gdx.graphics.getHeight()/2 ) - emptyRegion.packedHeight - BUTTON_SPACING);
     
        staticStage.addActor( Level2 );
 
        
        ImageButton Level3 = new ImageButton(jungle,jungledown);
        Level3.setX( (Gdx.graphics.getWidth()/2 ) + (largeRegion.packedWidth ));
        Level3.setY(  (Gdx.graphics.getHeight()/2 ) + BUTTON_SPACING);
   
        staticStage.addActor( Level3 );
        
        
        
      
        ImageButton Level4 = new ImageButton(empty,emptydown);
        Level4.setX( (Gdx.graphics.getWidth()/2 ) + (largeRegion.packedWidth ));
        Level4.setY(  (Gdx.graphics.getHeight()/2 ) - emptyRegion.packedHeight - BUTTON_SPACING );
     
        staticStage.addActor( Level4 );
        
        
        
        ImageButton Level5 = new ImageButton(z,zdown);
        Level5.setX( (Gdx.graphics.getWidth()/2 ) - (largeRegion.packedWidth/2));
        Level5.setY(  (Gdx.graphics.getHeight()/2 ) - (largeRegion.packedHeight/2) );
       
        staticStage.addActor( Level5 );
        
        
        
        
        

        ImageButton LevelLoader = new ImageButton(loadingImage);
        LevelLoader.setX( (Gdx.graphics.getWidth()/2 ) - (emptyRegion.packedWidth/2));
        LevelLoader.setY(  (Gdx.graphics.getHeight()/2 ) - (emptyRegion.packedHeight/2) );
      
        loading.addActor( LevelLoader );
        
        
        
        
        
        
        ImageButton logo = new ImageButton(logoImage);
        logo.setX( (Gdx.graphics.getWidth()/2 ) - (logoRegion.packedWidth/2));
        logo.setY(  (Gdx.graphics.getHeight()/2 ) - (logoRegion.packedHeight/2) - 110);
        staticStage.addActor( logo );
        
        
        ImageButton optionsButton = new ImageButton(options,optionsdown);
        optionsButton.setX( (Gdx.graphics.getWidth()/2 ) - (optionsRegion.packedWidth/2));
        optionsButton.setY(  (Gdx.graphics.getHeight()/2 ) - (optionsRegion.packedHeight/2) - 250);
        staticStage.addActor( optionsButton );
        
        
        
        Level1.addListener(new InputListener() {
			public boolean touchDown(InputEvent event, float x, float y,int pointer, int button) {
				
				return true;	
			}
			public void touchUp(InputEvent event, float x, float y,int pointer, int button) {
				fadeOut("data/levels/level1.json");
			
			}
		});
        
        
        
        Level2.addListener(new InputListener() {
			public boolean touchDown(InputEvent event, float x, float y,int pointer, int button) {
			
				
				return true;	
			}
			public void touchUp(InputEvent event, float x, float y,int pointer, int button) {
				fadeOut("data/levels/ice1.json");
			
			}
		});
        
        
        Level3.addListener(new InputListener() {
			public boolean touchDown(InputEvent event, float x, float y,int pointer, int button) {
				
				
				return true;	
			}
			public void touchUp(InputEvent event, float x, float y,int pointer, int button) {
				fadeOut("data/levels/jungle1.json");
				
			}
		});
        
        
        Level4.addListener(new InputListener() {
			public boolean touchDown(InputEvent event, float x, float y,int pointer, int button) {
				
				return true;	
			}
			public void touchUp(InputEvent event, float x, float y,int pointer, int button) {
				fadeOut("data/levels/factory1.json");
			}
		});
        
        
        Level5.addListener(new InputListener() {
			public boolean touchDown(InputEvent event, float x, float y,int pointer, int button) {
				
				
				return true;
			}
			public void touchUp(InputEvent event, float x, float y,int pointer, int button) {
				fadeOut("data/levels/final1.json");
				
			}
		});
	}
	
	
	private void fadeOut(String level){
		
		final String levelToLoad = level;
		loading.addAction(Actions.fadeIn(.2f));
		staticStage.addAction(Actions.sequence(Actions.fadeOut(.5f), Actions.run(new Runnable(){

			@Override
			public void run() {
			
				game.setScreen(new GameScreen(levelToLoad,game));
				
			}
			
		})));
	}
	
	
	
	
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//GAME LOOP STUFF
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	/*************************************************************************************
	 * This renders stuff on the screen
	 *************************************************************************************/
	@Override
	public void render(float delta) {
		

		
		//this clears the canvas so we can start drawing a clean frame
		Gdx.gl.glClearColor(0.1f, 0.1f, 0.1f, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);

    	

     	    
		
		background.act(delta);
		background.draw();

		
        
		
		//this animated then draws the layer with stationary controls on it
		staticStage.act(delta);
		staticStage.draw();
		
		
		
		loading.act(delta);
		loading.draw();

		
	}
	
	

	@Override
	public void resize(int width, int height) {
	
		start();
	}

	@Override
	public void show() {
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
		
	}

}
