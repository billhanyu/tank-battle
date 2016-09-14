package ui;

import game.Game;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

/**
 * @author billyu
 * in game info display
 */
public class GameHud {
	private Text timeHud;
	private Text livesHud;
	private Text levelHud;
	private Game myGame;
	
	public GameHud(Game game) {
		myGame = game;
	}
	
	public Node initHud() {
		livesHud = new Text();
		configureGameHud(livesHud);
		timeHud = new Text();
		configureGameHud(timeHud);
		levelHud = new Text();
		configureGameHud(levelHud);
		HBox box = new HBox();
		box.getChildren().addAll(livesHud, timeHud, levelHud);
		box.setSpacing(200);
		BorderPane.setAlignment(box, Pos.CENTER);
		return box;
	}
	
	public void updateHud() {
		updateLivesHud();
		updateTimeHud();
		updateLevelHud();
	}
	
	public double getHeight() {
		return livesHud.getLayoutBounds().getHeight();
	}
	
	private void configureGameHud(Text hud) {
		hud.setFont(new Font(20));
		hud.setFill(Color.WHITE);
	}
	
	public void updateLivesHud() {
		livesHud.setText(String.format("Lives: %d", myGame.getLives()));
	}
	
	public void updateTimeHud() {
		timeHud.setText("Time: " + (Game.GAME_TIME_SECONDS - (System.nanoTime() - myGame.getStartTime()) / 1000000000L));
	}
	
	public void updateLevelHud() {
		levelHud.setText("Level: " + myGame.getCurrentLevel());
	}
}
