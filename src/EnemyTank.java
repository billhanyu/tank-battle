import java.util.Timer;
import java.util.TimerTask;

public class EnemyTank extends Tank {
	private FireMissileTask fireMissileTask;
	private ChangeDirectionTask changeDirectionTask;
	private Timer t;
	
	public EnemyTank() {
		super();
		SPEED = 50;
		t = new Timer();
		
		changeDirectionTask = new ChangeDirectionTask();
		t.schedule(changeDirectionTask, 0);
		fireMissileTask = new FireMissileTask();
		t.schedule(fireMissileTask, 0);
	}
	
	class FireMissileTask extends TimerTask {
        @Override
        public void run() {
        	if (!alive) return;
            int delay_millis = (int)((2 + Math.random()) * 1000);
            t.schedule(new FireMissileTask(), delay_millis);
            Missile m = fireMissile();
            if (m != null) {
            	Game.elements.add(m);
            }
        }
    }
	
	class ChangeDirectionTask extends TimerTask {
		@Override
		public void run() {
			int delay_millis = (int)((2 + 2 * Math.random()) * 1000);
			t.schedule(new ChangeDirectionTask(), delay_millis);
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
		}
	}
	
	public void update(double time) {
		if (!alive) {
			deinit();
			return;
		}
		super.update(time);
		missileDirection = direction;
	}
	
	public void deinit() {
		t.cancel();
		t.purge();
	}
	
	public int getMissileMask() {
		return Tank.ENEMY_MISSILE_MASK;
	}
}
