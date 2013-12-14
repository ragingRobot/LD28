package com.intelligentbeans.boilerplate;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
public class SoundManager {
	private static SoundManager _instance;
	private Music music;
	private Music sound;
	private Music gunsound;
	private String currentSound = "";
	private String currentSong = "";
	public SoundManager(){
		
	}
	
	public void loadSong(String fileHandle){
		
		if(!currentSong.equals(fileHandle)){
			if(music != null || fileHandle == "" ){
				music.stop(); 
				music.dispose();
			}
			
			if(fileHandle != ""){
				music = Gdx.audio.newMusic(Gdx.files.internal(fileHandle));
				music.play(); 
				music.setLooping(true);
				music.setVolume(.3f);
			}
			currentSong = fileHandle;
		}
		
	}
	
public void playSound(String fileHandle){
		if(fileHandle == "gun.mp3"){
			if(gunsound == null){
				gunsound = Gdx.audio.newMusic(Gdx.files.internal(fileHandle));
			}
			gunsound.stop();
			gunsound.play(); 
		}else
		if(fileHandle != currentSound){
			if(sound != null || fileHandle == ""){
				sound.stop(); 
				sound.dispose();
				currentSound = "";
			}
			
			if(fileHandle != ""){
				sound = Gdx.audio.newMusic(Gdx.files.internal(fileHandle));
				currentSound = fileHandle;
				sound.play(); 
			 
			}
		}else{
			if(sound != null){
				sound.stop();
				sound.play(); 
			}
		}
			
		
		
	}
	
	public static SoundManager getInstance(){
		if(_instance == null){
			_instance = new SoundManager();
		}
		return _instance;
	}

}
