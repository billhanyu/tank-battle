
public class EnemyTank extends Tank {
	
	private long lastChangeDirection = System.nanoTime();
	private static final long DIRECTION_DELAY = 500000000;
	private static final double DIRECTION_CHANGE_POS = 0.01;
	private static final double FIRE_MISSILE_POS = 0.01;
	
	public EnemyTank() {
		super();
		SPEED = 50;
	}
	
	public void update(double time) {
		super.update(time);
		attemptChangeDirection();
		fireMissileRandom();
		missileDirection = direction;
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
			direction = Direction.UP;
			break;
		case 1:
			direction = Direction.DOWN;
			break;
		case 2:
			direction = Direction.LEFT;
			break;
		case 3:
			direction = Direction.RIGHT;
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
	
	public void handleCollision(Sprite s) {
		if (s.BITMASK == Game.PLAYER_MISSILE_MASK
				|| s.BITMASK == Game.PLAYER_TANK_MASK) {
			health--;
		}
		super.handleCollision(s);
	}
}
