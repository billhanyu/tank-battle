
public class Map1 extends GameMap {
	
	//x, y, width, height
	private static final int[][] tankPos = 
		{
			{0, 0}, {320, 0}, {640, 0}
		};
	private static final int[][] brickPos = 
		{
			{280, 600, 120, 40}, {280, 640, 40, 40}, {360, 640, 40, 40},//home
			{60, 320, 40, 180}, {160, 320, 40, 180}, {580, 320, 40, 180}, {480, 320, 40, 180}
		};
	private static final int[][] stonePos = 
		{
			{200, 400, 40, 20}, {440, 400, 40, 20}
		};
	private static final int[][] waterPos = 
		{
			{160, 160, 40, 120}, {480, 160, 40, 120}
		};
	private static final int[][] grassPos =
		{
			{200, 160, 280, 60}
		};
	private static final double SPAWN_POS = 0.005;
	private static final int[] homePos = {320, 640};
	private static final int[] playerPos = {240, 640};

	public Map1(int width, int height) {
		super(width, height);
	}
	
	@Override
	protected void createMap() {
		for (int[] chunk: brickPos) {
			int x = chunk[0];
			int y = chunk[1];
			int chunkWidth = chunk[2];
			int chunkHeight = chunk[3];
			for (int p = x; p <= x + chunkWidth - unitSize; p += unitSize) {
				for (int q = y; q <= y + chunkHeight - unitSize; q += unitSize) {
					Brick brick = new Brick();
					brick.setPosition(p, q);
					elements.add(brick);
				}
			}
		}
		
		for (int[] chunk: stonePos) {
			int x = chunk[0];
			int y = chunk[1];
			int chunkWidth = chunk[2];
			int chunkHeight = chunk[3];
			for (int p = x; p <= x + chunkWidth - unitSize; p += unitSize) {
				for (int q = y; q <= y + chunkHeight - unitSize; q += unitSize) {
					Stone st = new Stone();
					st.setPosition(p, q);
					elements.add(st);
				}
			}
		}
		
		for (int[] chunk: waterPos) {
			int x = chunk[0];
			int y = chunk[1];
			int chunkWidth = chunk[2];
			int chunkHeight = chunk[3];
			for (int p = x; p <= x + chunkWidth - unitSize; p += unitSize) {
				for (int q = y; q <= y + chunkHeight - unitSize; q += unitSize) {
					Water brick = new Water();
					brick.setPosition(p, q);
					elements.add(brick);
				}
			}
		}
		
		for (int[] chunk: grassPos) {
			int x = chunk[0];
			int y = chunk[1];
			int chunkWidth = chunk[2];
			int chunkHeight = chunk[3];
			for (int p = x; p <= x + chunkWidth - unitSize; p += unitSize) {
				for (int q = y; q <= y + chunkHeight - unitSize; q += unitSize) {
					Grass brick = new Grass();
					brick.setPosition(p, q);
					elements.add(brick);
				}
			}
		}
		
    	Home home = new Home();
    	home.setPosition(homePos[0], homePos[1]);
    	elements.add(home);
    	
    	playerTank = revivePlayerTank();
	}

	@Override
	protected void spawnTank() {
		for (int[] pos: tankPos) {
			if (Math.random() < SPAWN_POS) {
				EnemyTank tank = new EnemyTank();
				tank.setPosition(pos[0], pos[1]);
				boolean valid = true;
				for (Sprite e: elements) {
					if (tank.intersects(e)) {
						valid = false;
					}
				}
				if (valid) {
					tank.setDirection(Direction.DOWN);
					elements.add(tank);
				}
			}
		}
	}

	@Override
	protected PlayerTank revivePlayerTank() {
		playerTank = new PlayerTank();
		playerTank.setPosition(playerPos[0], playerPos[1]);
		elements.add(playerTank);
		return playerTank;
	}

}
