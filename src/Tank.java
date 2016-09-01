import java.util.Timer;
import java.util.TimerTask;

public abstract class Tank extends Sprite {
	
	private static final long MISSILE_DELAY = 500000000;
	private static final int IMMORTAL_DELAY = 5;
	
	protected int SPEED = 250;
	protected String TANK_UP = WHITE_TANK_UP;
	protected String TANK_DOWN = WHITE_TANK_DOWN;
	protected String TANK_LEFT = WHITE_TANK_LEFT;
	protected String TANK_RIGHT = WHITE_TANK_RIGHT;
	
	protected Direction direction = Direction.UP;
	protected Direction missileDirection = direction;
	protected Direction collisionDirection = Direction.NONE;
	private long fireTime = System.nanoTime();
	
	private Timer immortalTimer;
	
	public Tank() {
		immortalTimer = new Timer();
		setImage(TANK_UP);
		BITMASK = ENEMY_TANK_MASK; //default, enemy tanks
	}
	
	public Tank(int mask) {
		this();
		BITMASK = mask;
	}
	
	public void update(double time) {
		switch (direction) {
			case RIGHT:
				velocityX = SPEED;
				velocityY = 0;
				break;
			case LEFT:
				velocityX = -SPEED;
				velocityY = 0;
				break;
			case UP:
				velocityX = 0;
				velocityY = -SPEED;
				break;
			case DOWN:
				velocityX = 0;
				velocityY = SPEED;
				break;
			default:
				velocityX = 0;
				velocityY = 0;
				break;
		}
		switch (missileDirection) {
		case RIGHT:
			setImage(TANK_RIGHT);
			break;
		case LEFT:
			setImage(TANK_LEFT);
			break;
		case UP:
			setImage(TANK_UP);
			break;
		case DOWN:
			setImage(TANK_DOWN);
			break;
		default:
			break;
		}
		super.update(time);
	}
	
	public Missile fireMissile() {
		long time = System.nanoTime();
		if (time - fireTime < MISSILE_DELAY) return null;
		Missile missile = new Missile(missileDirection, getMissileMask());
		switch (missileDirection) {
			case UP:
				missile.setPosition(positionX + 0.5 * width - 0.5 * missile.width, positionY - missile.height);
				break;
			case DOWN:
				missile.setPosition(positionX + 0.5 * width - 0.5 * missile.width, positionY + height);
				break;
			case LEFT:
				missile.setPosition(positionX - missile.width, positionY + 0.5 * height - 0.5 * missile.height);
				break;
			case RIGHT:
				missile.setPosition(positionX + width, positionY + 0.5 * height - 0.5 * missile.height);
				break;
			default:
				//error
				break;
		}
		fireTime = time;
		return missile;
	}
	
	public void handleCollision(Sprite s) {
//		if (BITMASK == s.BITMASK) {
//			lastPosition();
//			return;
//		}
		switch (s.BITMASK) {
		case Tank.PLAYER_MISSILE_MASK:
		case Tank.PLAYER_TANK_MASK:
		case Tank.ENEMY_MISSILE_MASK:
		case Tank.ENEMY_TANK_MASK:
			health--;
			break;
		case 15:
			lastPosition();
			break;
		default:
			break;
		}
		super.handleCollision(s);
	}
	
	public void buffImmortal() {
		cancelBuff();
		immortalTimer.cancel();
		immortalTimer.purge();
		immortalTimer = new Timer();
		health = Integer.MAX_VALUE;
		TANK_UP = RED_TANK_UP;
		TANK_DOWN = RED_TANK_DOWN;
		TANK_LEFT = RED_TANK_LEFT;
		TANK_RIGHT = RED_TANK_RIGHT;
		TimerTask unbuff = new TimerTask() {
			public void run() {
				cancelBuff();
			}
		};
		immortalTimer.schedule(unbuff, IMMORTAL_DELAY * 1000);
	}
	
	public void cancelBuff() {
		health = 1;
		TANK_UP = WHITE_TANK_UP;
		TANK_DOWN = WHITE_TANK_DOWN;
		TANK_LEFT = WHITE_TANK_LEFT;
		TANK_RIGHT = WHITE_TANK_RIGHT;
	}
	
	public boolean isCollisionDirection(Direction dir) {
		return (collisionDirection != Direction.NONE && collisionDirection == dir);
	}
	
	public abstract int getMissileMask();
	
	//constants
	protected static final String WHITE_TANK_UP = "white-tank-up.gif";
	protected static final String WHITE_TANK_DOWN = "white-tank-down.gif";
	protected static final String WHITE_TANK_LEFT = "white-tank-left.gif";
	protected static final String WHITE_TANK_RIGHT = "white-tank-right.gif";
	protected static final String GREEN_TANK_UP = "green-tank-up.gif";
	protected static final String GREEN_TANK_DOWN = "green-tank-down.gif";
	protected static final String GREEN_TANK_LEFT = "green-tank-left.gif";
	protected static final String GREEN_TANK_RIGHT = "green-tank-right.gif";
	protected static final String RED_TANK_UP = "red-tank-up.gif";
	protected static final String RED_TANK_DOWN = "red-tank-down.gif";
	protected static final String RED_TANK_LEFT = "red-tank-left.gif";
	protected static final String RED_TANK_RIGHT = "red-tank-right.gif";
	protected static final String YELLOW_TANK_UP = "yellow-tank-up.gif";
	protected static final String YELLOW_TANK_DOWN = "yellow-tank-down.gif";
	protected static final String YELLOW_TANK_LEFT = "yellow-tank-left.gif";
	protected static final String YELLOW_TANK_RIGHT = "yellow-tank-right.gif";
	
	protected static final int PLAYER_TANK_MASK = 1;
	protected static final int ENEMY_TANK_MASK = 3;
	protected static final int PLAYER_MISSILE_MASK = 6;
	protected static final int ENEMY_MISSILE_MASK = 9;
}
