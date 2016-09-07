
public class Water extends Stable {
	
	public Water() {
		super();
	}

	@Override
	protected void setImageFile() {
		imageFile = "water.gif";
	}

	@Override
	protected void dealWithCollision(Sprite s) {
	}
}
