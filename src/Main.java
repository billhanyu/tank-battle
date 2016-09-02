
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.*;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.*;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * This is the main program, it is basically boilerplate to create
 * an animated scene.
 * 
 * @author Bill Yu
 */

public class Main extends Application {
    public static final int SIZE = 680;
    public static final int FRAMES_PER_SECOND = 60;
    private static final int MILLISECOND_DELAY = 1000 / FRAMES_PER_SECOND;
    private static final double SECOND_DELAY = 1.0 / FRAMES_PER_SECOND;

    private Game myGame;
    private Stage stage;
    private Button startButton;
    private Button exitButton;
    private KeyFrame frame;
    private Timeline animation;

    /**
     * Set things up at the beginning.
     */
    @Override
    public void start (Stage s) {
    	this.stage = s;
    	BorderPane root = new BorderPane();
    	VBox buttons = new VBox();
    	
    	buttons.setPadding(new Insets(15, 12, 15, 12));
        buttons.setSpacing(100);
        
    	startButton = new Button("Start Game");
    	startButton.setOnAction(new EventHandler<ActionEvent>() {
    		public void handle(ActionEvent event) {
    			startGame();
    		}
    	});
    	startButton.setMaxWidth(100);
    	
    	exitButton = new Button("Exit");
    	exitButton.setOnAction(new EventHandler<ActionEvent>() {
    		public void handle(ActionEvent event) {
    			stage.close();
    		}
    	});
    	
    	Text text = new Text();
		text.setFont(new Font(16));
		text.setWrappingWidth(400);
		text.setTextAlignment(TextAlignment.CENTER);
		text.setText("WASD or arrow keys to move around\n\nSpace to shoot\n\nProtect your home");
		root.getChildren().add(text);
		
    	buttons.getChildren().addAll(text, startButton);
    	buttons.setAlignment(Pos.CENTER);
    	
    	Label title = new Label("Tank Battle");
    	title.setFont(new Font(20));
    	title.setPadding(new Insets(15, 15, 15, 15));
    	title.setTextAlignment(TextAlignment.CENTER);
    	
    	root.setTop(title);
    	root.setCenter(buttons);
    	BorderPane.setAlignment(title, Pos.CENTER);
    	Scene startScene = new Scene(root, SIZE, SIZE);
    	stage.setTitle("Tank Battle");
    	stage.setScene(startScene);
    	stage.setResizable(false);
    	stage.show();
    }
    
    private void startGame() {
    	// create your own game here
        myGame = new Game();
        stage.setTitle(myGame.getTitle());

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
    	myGame = null;
    	frame = null;
    	animation.stop();
    	animation = null;
    	
    	Label indicator = new Label("You Won!");
    	indicator.setFont(new Font(20));
    	startButton.setText("Play Again");
    	
    	VBox root = new VBox();
    	root.setSpacing(60);
    	root.setAlignment(Pos.CENTER);
    	root.getChildren().addAll(indicator, startButton, exitButton);
    	Scene overScene = new Scene(root, SIZE, SIZE);
    	stage.setScene(overScene);
    }
    
    public void gameOver() {
    	myGame = null;
    	frame = null;
    	animation.stop();
    	animation = null;
    	Label indicator = new Label("Game Over");
    	indicator.setFont(new Font(20));
    	startButton.setText("Play Again");
    	VBox root = new VBox();
    	root.setSpacing(60);
    	root.setAlignment(Pos.CENTER);
    	root.getChildren().addAll(indicator, startButton, exitButton);
    	Scene overScene = new Scene(root, SIZE, SIZE);
    	stage.setScene(overScene);
    }
    
    private void step(double elapsedTime) {
    	switch (Game.status) {
    		case Wait:
    			break;
    		case Play:
    			break;
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

    /**
     * Start the program.
     */
    public static void main (String[] args) {
        launch(args);
    }
}
