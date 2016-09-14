package ui;
import java.nio.file.Paths;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

/**
 * @author billyu
 * manager to play sound in the game
 */
public class SoundManager {
	
	/**
	 * play victory audio
	 */
	public void playVictory() {
		play("sounds/victory.mp3");
	}
	
	/**
	 * play defeat audio
	 */
	public void playDefeat() {
		play("sounds/defeat.mp3");
	}
	
	/**
	 * @param filepath song to play's path
	 * play a song
	 */
	private void play(String filepath) {
		Media hit = new Media(Paths.get(filepath).toUri().toString());
		MediaPlayer mediaPlayer = new MediaPlayer(hit);
		mediaPlayer.play();
	}
	
}
