package sprite;

import stable.Grass;
import stable.Water;

/**
 * @author billyu
 * fired by tank, but exist even after tanks die
 */
public class Missile extends Sprite {
	private static final String MISSILE_UP = "missile-up.gif";
	private static final String MISSILE_DOWN = "missile-down.gif";
	private static final String MISSILE_LEFT = "missile-left.gif";
	private static final String MISSILE_RIGHT = "missile-right.gif";
	private static final int SPEED = 500;
	
	public Missile(Direction direction, int mask) {
		BITMASK = mask;
		switch (direction) {
			case UP:
				setImage(MISSILE_UP);
				velocityX = 0;
				velocityY = -SPEED;
				break;
			case DOWN:
				setImage(MISSILE_DOWN);
				velocityX = 0;
				velocityY = SPEED;
				break;
			case LEFT:
				setImage(MISSILE_LEFT);
				velocityX = -SPEED;
				velocityY = 0;
				break;
			case RIGHT:
				setImage(MISSILE_RIGHT);
				velocityX = SPEED;
				velocityY = 0;
				break;
			default:
				//error
				break;
		}
	}
	
	protected void dealWithCollision(Sprite s) {
		if (!(s instanceof Water || s instanceof Grass) || s instanceof Missile) {
			health = 0;
		}
	}
}
