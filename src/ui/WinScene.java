package ui;
import game.Game;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class WinScene extends GameScene {
	
	public WinScene(GameButtons manager, int SIZE, Game game) {
		super(manager, SIZE, game);
	}
	
	public Scene initScene() {
    	VBox root = new VBox();
    	root.setSpacing(60);
    	root.setAlignment(Pos.CENTER);
    	
    	int score = myGame.getScore();
    	Label indicator = new Label("You Won!\nScore: " + score);
    	root.getChildren().add(indicator);
    	if (btnManager.getBoard().canGetOn(score)) {
    		root.getChildren().add(btnManager.initNameInput(score));
    	}
    	indicator.setFont(new Font(20));
    	Button startButton = btnManager.initStartButton();
    	Button leadersButton = btnManager.initLeadersButton();
    	startButton.setText("Play Again");
    	Button exitButton = btnManager.initExitButton();
    	
    	root.getChildren().addAll(startButton, leadersButton, exitButton);
    	Scene winScene = new Scene(root, SIZE, SIZE);
    	return winScene;
    }
}
