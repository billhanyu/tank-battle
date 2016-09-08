
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.*;
import javafx.scene.*;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * This is the main program, it is basically boilerplate to create
 * an animated scene.
 * 
 * @author Bill Yu
 */

public class Main extends Application {
    private static final int SIZE = 680;
    private static final int FRAMES_PER_SECOND = 60;
    private static final int MILLISECOND_DELAY = 1000 / FRAMES_PER_SECOND;
    private static final double SECOND_DELAY = 1.0 / FRAMES_PER_SECOND;

    private Game myGame;
    private Stage stage;
    private KeyFrame frame;
    private Timeline animation;
    
    private GameButtons btnManager;
    
    private SoundManager soundManager;
    
    class GameStart implements EventHandler<ActionEvent> {
    	public void handle(ActionEvent event) {
			gameStart();
		}
    }
    
    class ShowLeaders implements EventHandler<ActionEvent> {
    	public void handle(ActionEvent event) {
			showLeaders();
		}
    }
    
    class GameExit implements EventHandler<ActionEvent> {
    	public void handle(ActionEvent event) {
			stage.close();
		}
    }

    /**
     * Set things up at the beginning.
     */
    @Override
    public void start (Stage s) {
    	this.stage = s;
    	soundManager = new SoundManager();
    	btnManager = new GameButtons(new GameStart(), new ShowLeaders(), new GameExit());
    	Scene startScene = new StartScene(btnManager, SIZE).initScene();
    	configureStage(startScene);
    	stage.show();
    }
    
    private void gameStart() {
    	// create your own game here
        myGame = new Game();
        stage.setTitle(myGame.getTitle());
        btnManager.refreshGame();

        // attach game to the stage and display it
        Scene gameScene = myGame.init(SIZE, SIZE);
        stage.setScene(gameScene);

        // sets the game's loop
        frame = new KeyFrame(Duration.millis(MILLISECOND_DELAY),
                                      e -> step(SECOND_DELAY));
        animation = new Timeline();
        animation.setCycleCount(Timeline.INDEFINITE);
        animation.getKeyFrames().add(frame);
        animation.play();
    }
    
    public void gameWin() {
    	soundManager.playVictory();
    	Scene winScene = new WinScene(btnManager, SIZE, myGame).initScene();
    	stage.setScene(winScene);
    	clearGame();
    }
    
    public void gameOver() {
    	soundManager.playDefeat();
    	Scene overScene = new OverScene(btnManager, SIZE, myGame).initScene();
    	stage.setScene(overScene);
    	clearGame();
    }
    
    public void showLeaders() {
    	Scene leadersScene = new LeadersScene(btnManager, SIZE, myGame).initScene();
    	stage.setScene(leadersScene);
    }
    
    private void step(double elapsedTime) {
    	switch (myGame.getStatus()) {
    		case Lost:
    			gameOver();
    			return;
    		case Win:
    			gameWin();
    			return;
    		default:
    			break;
    	}
    	myGame.step(elapsedTime);
    }
    
    private void configureStage(Scene startScene) {
    	stage.setTitle("Tank Battle");
    	stage.setScene(startScene);
    	stage.setResizable(false);
    }
    
    private void clearGame() {
    	myGame = null;
    	frame = null;
    	if (animation != null)
    		animation.stop();
    	animation = null;
    }

    /**
     * Start the program.
     */
    public static void main (String[] args) {
        launch(args);
    }
}
