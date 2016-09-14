package stable;

import game.Game;
import sprite.Sprite;

/**
 * @author billyu
 * elements that do not move during the game
 */
public abstract class Stable extends Sprite {
	protected static String imageFile;
	
	public Stable() {
		setImageFile();
		setImage(imageFile);
		BITMASK = Game.STABLE_MASK;
	}
	
	protected abstract void setImageFile();
}