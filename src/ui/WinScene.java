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
 * scene to let the players know they won
 */
public class WinScene extends GameScene {
	
	public WinScene(GameUI manager, int SIZE, Game game) {
		super(manager, SIZE, game);
	}
	
	public Scene initScene() {
    	VBox root = new VBox();
    	root.setSpacing(60);
    	root.setAlignment(Pos.CENTER);
    	
    	int score = myGame.getScore();
    	Label indicator = new Label("You Won!\nScore: " + score);
    	root.getChildren().add(indicator);
    	if (uiManager.getBoard().canGetOn(score)) {
    		root.getChildren().add(uiManager.initNameInput(score));
    	}
    	indicator.setFont(new Font(20));
    	Button startButton = uiManager.initStartButton();
    	Button leadersButton = uiManager.initLeadersButton();
    	startButton.setText("Play Again");
    	Button exitButton = uiManager.initExitButton();
    	
    	root.getChildren().addAll(startButton, leadersButton, exitButton);
    	Scene winScene = new Scene(root, SIZE, SIZE);
    	return winScene;
    }
}
