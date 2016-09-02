
public final class Brick extends Stable {
	public Brick() {
		imageFile = "brick.gif";
		setImage(imageFile);
		BITMASK = Game.STABLE_MASK;
	}

	public void handleCollision(Sprite s) {
		switch (s.BITMASK) {
		case Game.PLAYER_MISSILE_MASK:
		case Game.ENEMY_MISSILE_MASK:
			health--;
			break;
		default:
			break;
		}
		super.handleCollision(s);
	}
}
