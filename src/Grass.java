
public class Grass extends Stable {
	
	public Grass() {
		super();
	}

	@Override
	protected void setImageFile() {
		imageFile = "grass.gif";
	}

	@Override
	protected void dealWithCollision(Sprite s) {
	}
}
