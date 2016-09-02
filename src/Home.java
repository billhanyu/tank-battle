
public class Home extends Stable {
	public Home() {
		imageFile = "home.gif";
		setImage(imageFile);
		BITMASK = Game.STABLE_MASK;
	}
	
	@Override
	public void handleCollision(Sprite s) {
		if (s instanceof Missile) {
			Game.status = Status.ToLose;
			Game.toLoseTime = System.nanoTime();
			imageFile = "nohome.gif";
			setImage(imageFile);
		}
	}
}
