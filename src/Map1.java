
public class Map1 extends GameMap {

	public Map1(int width, int height) {
		super(width, height);
	}
	
	@Override
	protected void createMap() {
		Stone stone = new Stone();
    	stone.setPosition(200, 200);
    	Brick brick = new Brick();
    	brick.setPosition(250, 200);
    	Water water = new Water();
    	water.setPosition(200, 250);
    	Grass grass = new Grass();
    	grass.setPosition(250, 250);
    	elements.add(stone);
    	elements.add(brick);
    	elements.add(water);
    	elements.add(grass);
	}

}
