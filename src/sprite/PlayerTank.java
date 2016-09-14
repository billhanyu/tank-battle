package sprite;
import java.nio.file.Paths;
import java.util.ArrayList;

import game.Game;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

/**
 * @author billyu
 * controlled by the player
 * fire missile, move around and benefit from buffs
 */
public class PlayerTank extends Tank {
	
	private static final long IMMORTAL_DELAY = 5000*1000*100;
	private long immortalStartTime = System.nanoTime();
	
	public PlayerTank(ArrayList<Sprite> elements) {
		super(elements);
		setGreen();
		BITMASK = Game.PLAYER_TANK_MASK;
		buffImmortal();
	}
	
	public void update(double time) {
		super.update(time);
		missileDirection = getDirection() != Direction.NONE ? getDirection() : missileDirection;
		setDirection(Direction.NONE);
		checkImmortalOut();
	}
	
	private void checkImmortalOut() {
		if (System.nanoTime() - immortalStartTime > IMMORTAL_DELAY * 10) {
			debuffImmortal();
		}
	}
	
	public int getMissileMask() {
		return Game.PLAYER_MISSILE_MASK;
	}
	
	public void buffImmortal() {
		setRed();
		health = Integer.MAX_VALUE;
		immortalStartTime = System.nanoTime();
	}
	
	public void debuffImmortal() {
		health = 1;
		setGreen();
	}
	
	protected void dealWithCollision(Sprite s) {
		if (s.BITMASK == Game.ENEMY_MISSILE_MASK
				|| s.BITMASK == Game.ENEMY_TANK_MASK) {
			health--;
		}
	}
	
	public void playDeadSound() {
		System.out.println("slain");
		String bip = "sounds/slain.mp3";
		Media hit = new Media(Paths.get(bip).toUri().toString());
		MediaPlayer mediaPlayer = new MediaPlayer(hit);
		mediaPlayer.play();
	}
}
