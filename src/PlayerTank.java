
public class PlayerTank extends Tank {
	
	private static final long IMMORTAL_DELAY = 5000*1000*100;
	private long immortalStartTime = System.nanoTime();
	
	public PlayerTank() {
		super();
		setGreen();
		BITMASK = Game.PLAYER_TANK_MASK;
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
}
