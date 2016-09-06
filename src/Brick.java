
public final class Brick extends Stable {
	
	public Brick() {
		super();
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

	@Override
	protected void setImageFile() {
		imageFile = "brick.gif";
	}
}
