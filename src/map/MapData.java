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
		Map map3 = new Map(tankPos3, brickPos3, stonePos3, waterPos3, grassPos3, homePos3, playerPos3);
		maps.add(map1);
		maps.add(map2);
		maps.add(map3);
	}

	//x, y, width, height
	private static final int[][] tankPos1 = 
		{
				{0, 0}, {320, 0}, {640, 0}
		};
	private static final int[][] brickPos1 = 
		{
				{280, 600, 120, 40}, {280, 640, 40, 40}, {360, 640, 40, 40},//home
				{280, 360, 120, 40}, {60, 320, 40, 180}, {160, 360, 40, 140}, {580, 320, 40, 180}, 
				{480, 360, 40, 140}
		};
	private static final int[][] stonePos1 = 
		{
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
				{0, 580, 100, 40}, {60, 620, 40, 60},
				{200, 360, 280, 40}, {160, 240, 40, 440}, {480, 240, 40, 440}
		};
	private static final int[][] stonePos2 = 
		{
				{0, 620, 60, 60}, {300, 120, 80, 40}
		};
	private static final int[][] waterPos2 = 
		{
				{560, 120, 40, 120}
		};
	private static final int[][] grassPos2 =
		{
				{80, 80, 80, 120}, {520, 560, 120, 80}, {480, 120, 80, 100}
		};
	private static final int[] homePos2 = {320, 640};
	private static final int[] playerPos2 = {240, 640};
	
	private static final int[][] tankPos3 = 
		{
				{0, 0}, {640, 0}, {0, 640}, {640, 640}
		};
	private static final int[][] brickPos3 = 
		{
				{280, 280, 120, 40}, {280, 320, 40, 40}, {280, 360, 120, 40}, {360, 320, 40, 40},//home
				{160, 180, 320, 40}, {160, 460, 320, 40}, {160, 220, 40, 240}, {480, 180, 40, 320},
				{560, 460, 120, 40}, {560, 500, 40, 80}, {360, 580, 240, 40}, {400, 0, 40, 180},
				{0, 220, 160, 40}, {280, 500, 40, 180}
		};
	private static final int[][] stonePos3 = 
		{
				{560, 240, 80, 40}, {0, 380, 120, 40}
		};
	private static final int[][] waterPos3 = 
		{
				{140, 0, 200, 60}
		};
	private static final int[][] grassPos3 =
		{
				{160, 500, 120, 80}, {400, 220, 80, 60}
		};
	private static final int[] homePos3 = {320, 320};
	private static final int[] playerPos3 = {320, 240};
}
