import java.nio.file.Paths;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class SoundManager {
	
	public void playVictory() {
		play("sounds/victory.mp3");
	}
	
	public void playDefeat() {
		play("sounds/defeat.mp3");
	}
	
	private void play(String filepath) {
		Media hit = new Media(Paths.get(filepath).toUri().toString());
		MediaPlayer mediaPlayer = new MediaPlayer(hit);
		mediaPlayer.play();
	}
	
}
