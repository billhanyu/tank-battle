package game;
import java.util.ArrayList;

import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.canvas.*;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import map.GameMap;
import sprite.Direction;
import sprite.PlayerTank;
import sprite.Sprite;
import stable.Home;

/**
 * Separate the game code from some of the boilerplate code.
 * 
 * @author Bill Yu
 */

enum Status {
	Wait, Play, Lost, Win, ToLose, Between;
}

public class Game {
	public static final String TITLE = "Fight for Your Home";
	private Status status = Status.Wait;
	private int currentLevel = 0;

	private static long toLoseTime = System.nanoTime();
	private static final long LOSE_DELAY = 500 * 1000000;

	public long deadTime = System.nanoTime();
	private static final long DIE_DELAY = 1 * 1000000000L;

	private long passLevelTime = System.nanoTime();
	private static final long LEVEL_DELAY = 3 * 1000000000L;

	private int lives;
	private int score;
	private static final int INITIAL_LIVES = 3;
	private static final int SCORE_UNIT = 100;

	private Scene myScene;
	private GraphicsContext gc;
	private PlayerTank playerTank;
	private Text livesHud;
	private Text timeHud;
	private Text levelHud;
	private int width, height;

	private GameMap map;
	private int numLevels;

	private static final long GAME_TIME_SECONDS = 3;
	private static final long GAME_TIME = GAME_TIME_SECONDS * 1000000000L;
	private long startTime = System.nanoTime();

	private ArrayList<Sprite> elements = new ArrayList<Sprite>();

	/**
	 * Returns name of the game.
	 */
	public String getTitle () {
		return TITLE;
	}

	/**
	 * Create the game's scene
	 */
	public Scene init (int width, int height) {
		status = Status.Play;
		this.width = width;
		this.height = height;
		elements = new ArrayList<Sprite>();
		startTime = System.nanoTime();
		lives = INITIAL_LIVES;
		score = 0;
		currentLevel = 0;

		BorderPane root = new BorderPane();
		root.setStyle("-fx-background-color: black;");
		root.setTop(initHud());

		map = new GameMap(width, height);
		map.init(elements);
		numLevels = map.numLevels();
		nextLevel();
		
		gc = initGraphicsContext(root);

		myScene = new Scene(root, width, height + livesHud.getLayoutBounds().getHeight(), Color.BLACK);
		// Respond to input
		myScene.setOnKeyPressed(e -> handleKeyInput(e.getCode()));
		return myScene;
	}

	/**
	 * game step
	 */
	public void step (double elapsedTime) {
		if (lives <= 0 && status != Status.ToLose) {
			setToLose();
			return;
		}
		if (status == Status.Play && System.nanoTime() - startTime > GAME_TIME) {
			if (currentLevel >= numLevels) {
				status = Status.Win;
				return;
			}
			gc.clearRect(0, 0, width, height);
			showScore();
			setBetween();
		}
		if (status == Status.Between) {
			if (System.nanoTime() - passLevelTime > LEVEL_DELAY) {
				nextLevel();
			}
			return;
		}
		if (status == Status.ToLose 
				&& System.nanoTime() - toLoseTime > LOSE_DELAY) {
			status = Status.Lost;
			return;
		}
		
		updateElements(elapsedTime);
		detectCollisions();
		renderElements();
		updateHud();
		map.spawnTank();
	}
	
	/**
	 * set the game status to ToLose
	 */
	public void setToLose() {
		status = Status.ToLose;
		toLoseTime = System.nanoTime();
	}
	
	/**
	 * @return current status of the game
	 */
	public Status getStatus() {
		return status;
	}

	/**
	 * @return player's score of the game
	 */
	public int getScore() {
		return score;
	}

	private void nextLevel() {
		if (currentLevel >= numLevels) {
			status = Status.Win;
			return;
		}
		elements.removeAll(elements);
		startTime = System.nanoTime();
		map.buildMap(currentLevel);
		playerTank = map.getPlayerTank();
		status = Status.Play;
		currentLevel++;
	}

	private void handleKeyInput (KeyCode code) {
		if (System.nanoTime() - deadTime < DIE_DELAY) {
			return;
		}
		if (code == KeyCode.SPACE) {
			playerTank.fireMissile();
			return;
		}
		switch (code) {
		case RIGHT:
		case D:
			playerTank.setDirection(Direction.RIGHT);
			break;
		case LEFT:
		case A:
			playerTank.setDirection(Direction.LEFT);
			break;
		case UP:
		case W:
			playerTank.setDirection(Direction.UP);
			break;
		case DOWN:
		case S:
			playerTank.setDirection(Direction.DOWN);
			break;
			//cheats
		case C:
			clearEnemies();
			break;
		case B:
			playerTank.buffImmortal();
			break;
		case L:
			lives++;
		case N:
			nextLevel();
		default:
			break;
		}
	}

	private void detectCollisions() {
		for (int i = 0; i < elements.size(); i++) {
			for (int j = i + 1; j < elements.size(); j++) {
				Sprite e1 = elements.get(i);
				Sprite e2 = elements.get(j);
				if (!e1.intersects(e2)) continue;
				if ((e1.getBITMASK() & e2.getBITMASK()) != 0) {
					e1.handleCollision(e2);;
					e2.handleCollision(e1);;
				}
			}
		}
	}

	public void clearEnemies() {
		for (int i = 0; i < elements.size(); i++) {
			Sprite e = elements.get(i);
			if (e.getBITMASK() == ENEMY_TANK_MASK) {
				e.setAlive(false);
				elements.remove(i);
				i--;
			}
		}
	}

	private void updateHud() {
		updateLivesHud();
		updateTimeHud();
		updateLevelHud();
	}
	
	private void updateLivesHud() {
		livesHud.setText(String.format("Lives: %d", lives));
	}
	
	private void updateTimeHud() {
		timeHud.setText("Time: " + (GAME_TIME_SECONDS - (System.nanoTime() - startTime) / 1000000000L));
	}
	
	private void updateLevelHud() {
		levelHud.setText("Level: " + currentLevel);
	}

	private GraphicsContext initGraphicsContext(BorderPane root) {
		Canvas canvas = new Canvas(width, height);
		canvas.setStyle("-fx-background-color: black;");
		root.setCenter(canvas);
		return canvas.getGraphicsContext2D();
	}

	private void renderElements() {
		elements.sort(null);
		for (Sprite e: elements) {
			e.render(gc);
		}
	}

	private void updateElements(double elapsedTime) {
		gc.clearRect(0, 0, width, height);
		int i = 0;
		while (i < elements.size()) {
			Sprite e = elements.get(i);
			if (e.isAlive()) {
				e.update(elapsedTime);
				i++;
			}
			else {
				if (e instanceof Home) {
					if (status == Status.Play) {
						setToLose();
					}
					i++;
					continue;
				}
				elements.remove(i);
				if (e.getBITMASK() == playerTank.getBITMASK()) {
					playerTank = map.revivePlayerTank();
					lives--;
					deadTime = System.nanoTime();
				}
				else if (e.getBITMASK() == Game.ENEMY_TANK_MASK) {
					score += SCORE_UNIT;
				}
			}
		}
	}
	
	private Node initHud() {
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
	
	private void configureGameHud(Text hud) {
		hud.setFont(new Font(20));
		hud.setFill(Color.WHITE);
	}

	private void showScore() {
		gc.setFill(Color.WHITE);
		gc.setFont(new Font(20));
		gc.fillText("Current Score: " + getScore(), width / 2 - 80, height / 2);
	}

	private void setBetween() {
		passLevelTime = System.nanoTime();
		status = Status.Between;
	}

	public static final int PLAYER_TANK_MASK = 1;
	public static final int ENEMY_TANK_MASK = 3;
	public static final int PLAYER_MISSILE_MASK = 6;
	public static final int ENEMY_MISSILE_MASK = 9;
	public static final int STABLE_MASK = 15;
}