
public class Home extends Stable {
	public Home() {
		imageFile = "home.gif";
		setImage(imageFile);
		BITMASK = Game.STABLE_MASK;
	}
	
	@Override
	public void handleCollision(Sprite s) {
		if (s instanceof Missile) {
			Game.setToLose();
			imageFile = "nohome.gif";
			setImage(imageFile);
		}
	}
}
