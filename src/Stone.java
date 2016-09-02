
public class Stone extends Stable {
	
	public Stone() {
		imageFile = "stone.gif";
		setImage(imageFile);
		BITMASK = Game.STONE_MASK;
	}
}