package stable;

import sprite.Missile;
import sprite.Sprite;

/**
 * @author billyu
 * home of the player, if this dies, game over
 */
public class Home extends Stable {
	
	public Home() {
		super();
	}

	protected void dealWithCollision(Sprite s) {
		if (s instanceof Missile) {
			health--;
			imageFile = "nohome.gif";
			setImage(imageFile);
		}
	}

	@Override
	protected void setImageFile() {
		imageFile = "home.gif";
	}
}
