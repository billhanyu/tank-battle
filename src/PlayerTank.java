
public class PlayerTank extends Tank {
	
	public PlayerTank() {
		super();
		BITMASK = Tank.PLAYER_TANK_MASK;
	}
	
	public void update(double time) {
		super.update(time);
		missileDirection = direction != Direction.NONE ? direction : missileDirection;
		direction = Direction.NONE;
	}
	
	public int getMissileMask() {
		return Tank.PLAYER_MISSILE_MASK;
	}
}
