package stable;

import game.Game;
import sprite.Sprite;

/**
 * @author billyu
 * brick
 * can be destroyed by missile
 */
public final class Brick extends Stable {
	
	public Brick() {
		super();
	}

	protected void dealWithCollision(Sprite s) {
		switch (s.getBITMASK()) {
		case Game.PLAYER_MISSILE_MASK:
		case Game.ENEMY_MISSILE_MASK:
			health--;
			break;
		default:
			break;
		}
	}

	@Override
	protected void setImageFile() {
		imageFile = "brick.gif";
	}
}
