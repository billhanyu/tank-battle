import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class OverScene {
	private GameButtons btnManager;
	private final int SIZE;
	private Game myGame;
	
	public OverScene(GameButtons manager, int SIZE, Game game) {
		btnManager = manager;
		this.SIZE = SIZE;
		myGame = game;
	}
	
	public Scene initOverScene() {
    	Label indicator = new Label("Game Over\nScore: " + myGame.getScore());
    	indicator.setFont(new Font(20));
    	Button startButton = btnManager.initStartButton();
    	startButton.setText("Play Again");
    	Button leadersButton = btnManager.initLeadersButton();
    	Button exitButton = btnManager.initExitButton();
    	VBox root = new VBox();
    	root.setSpacing(60);
    	root.setAlignment(Pos.CENTER);
    	root.getChildren().add(indicator);
    	int score = myGame.getScore();
    	if (btnManager.getBoard().canGetOn(score)) {
    		root.getChildren().add(btnManager.initNameInput(score));
    	}
    	root.getChildren().addAll(startButton, leadersButton, exitButton);
    	Scene overScene = new Scene(root, SIZE, SIZE);
    	return overScene;
    }
}
