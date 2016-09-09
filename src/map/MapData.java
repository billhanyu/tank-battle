package map;
import java.util.ArrayList;

/**
 * @author billyu
 * stores the map data in code
 * in the future could read from file
 */
public class MapData {
	
	private ArrayList<Map> maps;

	public MapData() {
		maps = new ArrayList<Map>();
		initMapData();
	}
	
	/**
	 * @return the number of levels according to map data
	 */
	public int numLevels() {
		return maps.size();
	}
	
	/**
	 * @param level the level of the map requested
	 * @return a map corresponding to the level
	 */
	public Map mapWithLevel(int level) {
		return maps.get(level);
	}

	/**
	 * read map data from code
	 */
	private void initMapData() {
		Map map1 = new Map(tankPos1, brickPos1, stonePos1, waterPos1, grassPos1, homePos1, playerPos1);
		Map map2 = new Map(tankPos2, brickPos2, stonePos2, waterPos2, grassPos2, homePos2, playerPos2);
		maps.add(map1);
		maps.add(map2);
	}

	//x, y, width, height
	private static final int[][] tankPos1 = 
		{
				{0, 0}, {320, 0}, {640, 0}
		};
	private static final int[][] brickPos1 = 
		{
				{280, 600, 120, 40}, {280, 640, 40, 40}, {360, 640, 40, 40},//home
				{60, 320, 40, 180}, {160, 360, 40, 140}, {580, 320, 40, 180}, {480, 360, 40, 140}
		};
	private static final int[][] stonePos1 = 
		{
				{200, 400, 40, 20}, {440, 400, 40, 20}
		};
	private static final int[][] waterPos1 = 
		{
				{160, 160, 40, 120}, {480, 160, 40, 120}
		};
	private static final int[][] grassPos1 =
		{
				{200, 160, 280, 60}
		};
	private static final int[] homePos1 = {320, 640};
	private static final int[] playerPos1 = {240, 640};
	
	private static final int[][] tankPos2 = 
		{
				{0, 0}, {320, 0}, {640, 0}
		};
	private static final int[][] brickPos2 = 
		{
				{280, 600, 120, 40}, {280, 640, 40, 40}, {360, 640, 40, 40},//home
				{60, 320, 40, 180}, {160, 360, 40, 140}, {580, 320, 40, 180}, {480, 360, 40, 140}
		};
	private static final int[][] stonePos2 = 
		{
				{200, 400, 40, 20}, {440, 400, 40, 20}
		};
	private static final int[][] waterPos2 = 
		{
				{160, 160, 40, 120}, {480, 160, 40, 120}
		};
	private static final int[][] grassPos2 =
		{
				{200, 160, 280, 60}
		};
	private static final int[] homePos2 = {320, 640};
	private static final int[] playerPos2 = {240, 600};
}
