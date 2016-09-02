import java.util.ArrayList;

public abstract class GameMap {
	private int width, height;
	protected ArrayList<Sprite> elements;
	protected PlayerTank playerTank;
	
	public GameMap(int width, int height) {
		this.width = width;
		this.height = height;
	}
	
	public void init(ArrayList<Sprite> elements) {
		this.elements = elements;
		pad();
		createMap();
	}
	
	private void pad() {
		Stone stone = new Stone();
		double size = stone.getWidth();
		double x = 0, y = 0;
		x = -size;
		for (y = 0; y <= height; y += size) {
			addPadding(x, y);
		}
		y = height;
		for (x = 0; x <= width; x += size) {
			addPadding(x, y);
		}
		x = width;
		for (y = 0; y <= height; y+= size) {
			addPadding(x, y);
		}
		y = -size;
		for (x = 0; x <= width; x += size) {
			addPadding(x, y);
		}
	}
	
	private void addPadding(double x, double y) {
		Stone padding = new Stone();
		padding.setPosition(x, y);
		elements.add(padding);
	}
	
	protected abstract void createMap();
	
	protected abstract void spawnTank();
	
	protected PlayerTank getPlayerTank() {
		return playerTank;
	}
	
	protected abstract PlayerTank revivePlayerTank();
}