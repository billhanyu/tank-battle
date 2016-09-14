package ui;
import game.Game;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

/**
 * @author billyu
 * game over scene
 */
public class OverScene extends GameScene {
	
	public OverScene(GameUI manager, int SIZE, Game game) {
		super(manager, SIZE, game);
	}
	
	public Scene initScene() {
    	Label indicator = new Label("Game Over\nScore: " + myGame.getScore());
    	indicator.setFont(new Font(20));
    	Button startButton = uiManager.initStartButton();
    	startButton.setText("Play Again");
    	Button leadersButton = uiManager.initLeadersButton();
    	Button exitButton = uiManager.initExitButton();
    	VBox root = new VBox();
    	root.setSpacing(60);
    	root.setAlignment(Pos.CENTER);
    	root.getChildren().add(indicator);
    	int score = myGame.getScore();
    	if (uiManager.getBoard().canGetOn(score)) {
    		root.getChildren().add(uiManager.initNameInput(score));
    	}
    	root.getChildren().addAll(startButton, leadersButton, exitButton);
    	Scene overScene = new Scene(root, SIZE, SIZE);
    	return overScene;
    }
}
