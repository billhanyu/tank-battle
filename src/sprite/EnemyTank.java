package sprite;
import java.util.ArrayList;

import game.Game;

/**
 * @author billyu
 * enemy tank actions
 * fire missile and change direction randomly
 */
public class EnemyTank extends Tank {
	
	private long lastChangeDirection = System.nanoTime();
	private static final long DIRECTION_DELAY = 500000000;
	private static final double DIRECTION_CHANGE_POS = 0.02;
	private static final double FIRE_MISSILE_POS = 0.02;
	
	public EnemyTank(ArrayList<Sprite> elements) {
		super(elements);
		SPEED = 100;
	}
	
	/* 
	 * change direction at random
	 * fire missile at random
	 */
	public void update(double time) {
		super.update(time);
		attemptChangeDirection();
		fireMissileRandom();
		missileDirection = getDirection();
	}
	
	public int getMissileMask() {
		return Game.ENEMY_MISSILE_MASK;
	}
	
	private void attemptChangeDirection() {
		if (Math.random() < DIRECTION_CHANGE_POS 
				&& System.nanoTime() - lastChangeDirection > DIRECTION_DELAY) {
			changeRandomDirection();
		}
	}
	
	private void changeRandomDirection() {
		int dir = (int)(Math.random() * 4);
		switch (dir) {
		case 0:
			setDirection(Direction.UP);
			break;
		case 1:
			setDirection(Direction.DOWN);
			break;
		case 2:
			setDirection(Direction.LEFT);
			break;
		case 3:
			setDirection(Direction.RIGHT);
			break;
		default:
			break;
		}
		lastChangeDirection = System.nanoTime();
	}
	
	private void fireMissileRandom() {
		if (Math.random() < FIRE_MISSILE_POS) {
			fireMissile();
		}
	}
	
	protected void dealWithCollision(Sprite s) {
		if (s.BITMASK == Game.PLAYER_MISSILE_MASK
				|| s.BITMASK == Game.PLAYER_TANK_MASK) {
			health--;
		}
	}
}
