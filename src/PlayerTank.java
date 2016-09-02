
public class PlayerTank extends Tank {
	
	private static final long IMMORTAL_DELAY = 5000*1000*100;
	private long immortalStartTime = System.nanoTime();
	
	public PlayerTank() {
		super();
		TANK_UP = GREEN_TANK_UP;
		TANK_DOWN = GREEN_TANK_DOWN;
		TANK_LEFT = GREEN_TANK_LEFT;
		TANK_RIGHT = GREEN_TANK_RIGHT;
		BITMASK = Tank.PLAYER_TANK_MASK;
	}
	
	public void update(double time) {
		super.update(time);
		missileDirection = direction != Direction.NONE ? direction : missileDirection;
		direction = Direction.NONE;
		checkImmortalOut();
	}
	
	private void checkImmortalOut() {
		if (System.nanoTime() - immortalStartTime > IMMORTAL_DELAY * 10) {
			debuffImmortal();
		}
	}
	
	public int getMissileMask() {
		return Tank.PLAYER_MISSILE_MASK;
	}
	
	public void buffImmortal() {
		health = Integer.MAX_VALUE;
		TANK_UP = RED_TANK_UP;
		TANK_DOWN = RED_TANK_DOWN;
		TANK_LEFT = RED_TANK_LEFT;
		TANK_RIGHT = RED_TANK_RIGHT;
		immortalStartTime = System.nanoTime();
	}
	
	public void debuffImmortal() {
		health = 1;
		TANK_UP = GREEN_TANK_UP;
		TANK_DOWN = GREEN_TANK_DOWN;
		TANK_LEFT = GREEN_TANK_LEFT;
		TANK_RIGHT = GREEN_TANK_RIGHT;
	}
}
