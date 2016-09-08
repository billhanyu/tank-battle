import javafx.scene.Scene;

public abstract class GameScene {
	protected GameButtons btnManager;
	protected final int SIZE;
	protected Game myGame;
	
	public GameScene(GameButtons manager, int SIZE) {
		btnManager = manager;
		this.SIZE = SIZE;
	}
	
	public GameScene(GameButtons manager, int SIZE, Game game) {
		this(manager, SIZE);
		myGame = game;
	}
	
	public abstract Scene initScene();
}
