package stable;

import sprite.Sprite;

/**
 * @author billyu
 * missile fly over water
 * tanks cannot get into water
 */
public class Water extends Stable {
	
	public Water() {
		super();
	}

	@Override
	protected void setImageFile() {
		imageFile = "water.gif";
	}

	@Override
	protected void dealWithCollision(Sprite s) {
	}
}
