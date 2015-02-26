/**
 * 
 */
package snake;

import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 * @author Aaron
 *
 */
public class Sound {
	private Clip clip;
	private String fileName;

	/**
	 * 
	 */
	public Sound(String fileName) {
		this.fileName = fileName;
		loadSound();
	}
	
	/**
	 * This method plays the sound
	 */
	public void play(){
		if(clip.getFramePosition() == clip.getFrameLength()){
			clip.stop();
			clip.close();
			System.out.println("Clip closed");
		}
		
		loadSound();
		clip.setFramePosition(0);
		clip.start();
	}
	
	/**
	 * This method loops the sound
	 */
	public void loop(){
		loadSound();
		clip.setFramePosition(0);
		clip.loop(Clip.LOOP_CONTINUOUSLY);
	}
	
	/**
	 * This method stops the sound
	 */
	public void stop(){
		clip.stop();
	}
	
	/**
	 * This method closes the sound and releases the resource from memory
	 */
	public void close(){
		clip.close();
	}
	
	/**
	 * This method loads the sound into memory
	 */
	private void loadSound(){
		try {
			AudioInputStream audio = AudioSystem.getAudioInputStream(this.getClass().getResource(fileName));
			clip = AudioSystem.getClip();
			clip.open(audio);
		} catch(UnsupportedAudioFileException e){
			e.printStackTrace();
		} catch(LineUnavailableException e){
			e.printStackTrace();
		} catch(IOException e){
			e.printStackTrace();
		}
	}

}
