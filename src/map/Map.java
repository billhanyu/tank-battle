package map;

/**
 * @author billyu
 * hold data for map
 */
public class Map {
	public int[][] tankPos, brickPos, stonePos, waterPos, grassPos;
	public int[] homePos, playerPos;

	public Map(int[][] tankPos, int[][] brickPos, int[][] stonePos, int[][] waterPos, int[][] grassPos,
			int[] homePos, int[] playerPos) {
		this.tankPos = tankPos;
		this.brickPos = brickPos;
		this.stonePos = stonePos;
		this.waterPos = waterPos;
		this.grassPos = grassPos;
		this.homePos = homePos;
		this.playerPos = playerPos;
	}
}