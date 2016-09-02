import java.util.ArrayList;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.*;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

/**
 * Separate the game code from some of the boilerplate code.
 * 
 * @author Bill Yu
 */

enum Status {
	Wait, Play, Lost, Win;
}

class Game {
    public static final String TITLE = "Fight for Your Home";
    public static Status status = Status.Wait;

    private Scene myScene;
    private GraphicsContext gc;
    private PlayerTank playerTank;
    private Label info;
    private int width, height;
    
    public static ArrayList<Sprite> elements = new ArrayList<Sprite>();
    
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
    	
    	BorderPane root = new BorderPane();
    	root.setStyle("-fx-background-color: black;");
    	
    	info = new Label("This is fun.");
    	info.setFont(new Font(20));
    	info.setTextFill(Color.WHITE);
    	BorderPane.setAlignment(info, Pos.CENTER);
    	
    	playerTank = new PlayerTank();
    	ArrayList<Tank> enemyTanks = new ArrayList<Tank>();
    	for (int i = 0; i < 5; i++) {
    		double x = Math.random() * width;
    		double y = Math.random() * height;
    		EnemyTank enemy = new EnemyTank();
    		enemy.setPosition(x, y);
    		
    		boolean valid = true;
    		if (enemy.intersects(playerTank)) {
    			valid = false;
    		}
    		for (Tank tank: enemyTanks) {
    			if (enemy.intersects(tank)) {
    				valid = false;
    			}
    		}
    		if (!valid) {
    			i--;
    			continue;
    		}
    		enemyTanks.add(enemy);
    	}
    	
    	elements.add(playerTank);
    	elements.addAll(enemyTanks);
    	
    	Stone stone = new Stone();
    	stone.setPosition(200, 200);
    	Brick brick = new Brick();
    	brick.setPosition(250, 200);
    	elements.add(stone);
    	elements.add(brick);
    	
        root.setTop(info);
        // Create a place to see the shapes
        Canvas canvas = new Canvas(width, height);
        canvas.setStyle("-fx-background-color: black;");
        root.setCenter(canvas);
        gc = canvas.getGraphicsContext2D();
        
        myScene = new Scene(root, width, height, Color.BLACK);
        // Respond to input
        myScene.setOnKeyPressed(e -> handleKeyInput(e.getCode()));
        myScene.setOnMouseClicked(e -> handleMouseInput(e.getX(), e.getY()));
        return myScene;
    }

    /**
     * Change properties of shapes to animate them
     * 
     * Note, there are more sophisticated ways to animate shapes,
     * but these simple ways work too.
     */
    public void step (double elapsedTime) {
        // check for collisions
        // with shapes, can check precisely
    	gc.clearRect(0, 0, width, height);
    	
    	int i = 0;
    	if (elements.size() == 1) {
    		status = Status.Win;
    	}
    	while (i < elements.size()) {
    		Sprite e = elements.get(i);
    		if (e.alive) {
    			e.update(elapsedTime);
    			i++;
    		}
    		else if (e.BITMASK == playerTank.BITMASK) {
    			status = Status.Lost;
    			return;
    		}
    		else {
    			elements.remove(i);
    		}
    	}
    	
    	detectCollisions();
    	
    	for (Sprite e: elements) {
    		e.render(gc);
    	}
    	updateHud();
    }

    private void handleKeyInput (KeyCode code) {
    	if (code == KeyCode.SPACE) {
    		playerTank.fireMissile();
    		return;
    	}
    	switch (code) {
        	case RIGHT:
        	case D:
        		playerTank.direction = Direction.RIGHT;
        		break;
        	case LEFT:
        	case A:
        		playerTank.direction = Direction.LEFT;
        		break;
        	case UP:
        	case W:
        		playerTank.direction = Direction.UP;
        		break;
        	case DOWN:
        	case S:
        		playerTank.direction = Direction.DOWN;
        		break;
        	case C:
        		clearEnemies();
        		break;
        	case B:
        		playerTank.buffImmortal();
        	default:
        		break;
    	}
    }

    private void handleMouseInput (double x, double y) {
        
    }
    
    private void detectCollisions() {
    	for (int i = 0; i < elements.size(); i++) {
    		for (int j = i + 1; j < elements.size(); j++) {
    			Sprite e1 = elements.get(i);
    			Sprite e2 = elements.get(j);
    			if (!e1.intersects(e2)) continue;
    			if ((e1.BITMASK & e2.BITMASK) != 0) {
    				e1.handleCollision(e2);;
    				e2.handleCollision(e1);;
    			}
    		}
    	}
    }
    
    public void clearEnemies() {
    	for (int i = 0; i < elements.size(); i++) {
    		Sprite e = elements.get(i);
    		if (e.BITMASK == ENEMY_TANK_MASK) {
    			e.alive = false;
    			elements.remove(i);
    			i--;
    		}
    	}
    }
    
    private void updateHud() {
    	int count = 0;
    	for (Sprite e: elements) {
    		if (e.BITMASK == ENEMY_TANK_MASK) count++;
    	}
    	info.setText(String.format("%d enemies left.", count));
    }
    
    public static final int PLAYER_TANK_MASK = 1;
	public static final int ENEMY_TANK_MASK = 3;
	public static final int PLAYER_MISSILE_MASK = 6;
	public static final int ENEMY_MISSILE_MASK = 9;
	public static final int STABLE_MASK = 15;
}