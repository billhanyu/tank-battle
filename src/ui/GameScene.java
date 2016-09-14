package ui;
import game.Game;
import javafx.scene.Scene;

/**
 * @author billyu
 * generate scene needed
 */
public abstract class GameScene {
	protected GameUI uiManager;
	protected final int SIZE;
	protected Game myGame;
	
	public GameScene(GameUI manager, int SIZE) {
		uiManager = manager;
		this.SIZE = SIZE;
	}
	
	public GameScene(GameUI manager, int SIZE, Game game) {
		this(manager, SIZE);
		myGame = game;
	}
	
	/**
	 * @return the scene of the class
	 */
	public abstract Scene initScene();
}
