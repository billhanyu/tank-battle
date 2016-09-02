
public class Map1 extends GameMap {
	
	private static final int[][] brickPos = 
		{
			{280, 600}, {300, 600}, {320, 600}, {340, 600}, {360, 600}, {380, 600},
			{280, 620}, {300, 620}, {320, 620}, {340, 620}, {360, 620}, {380, 620},
			{280, 640}, {300, 640}, {360, 640}, {380, 640},
			{280, 660}, {300, 660}, {360, 660}, {380, 660}
		};
	private static final int[][] tankPos = 
		{
			{0, 0}, {320, 0}, {640, 0}
		};
	private static final int[] homePos = {320, 640};
	private static final int[] playerPos = {240, 640};

	public Map1(int width, int height) {
		super(width, height);
	}
	
	@Override
	protected void createMap() {
		for (int[] oneBrickPos: brickPos) {
			Brick brick = new Brick();
			brick.setPosition(oneBrickPos[0], oneBrickPos[1]);
			elements.add(brick);
		}
		
    	Home home = new Home();
    	home.setPosition(homePos[0], homePos[1]);
    	elements.add(home);
    	
    	playerTank = revivePlayerTank();
	}

	@Override
	protected void spawnTank() {
		
	}

	@Override
	protected PlayerTank revivePlayerTank() {
		playerTank = new PlayerTank();
		playerTank.setPosition(playerPos[0], playerPos[1]);
		elements.add(playerTank);
		return playerTank;
	}

}
