
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
    private Scene startScene;
    private KeyFrame frame;
    private Timeline animation;

    /**
     * Set things up at the beginning.
     */
    @Override
    public void start (Stage s) {
    	this.stage = s;
    	startScene = initStartScene();
    	configureStage();
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
    	clearGame();
    	
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
    	clearGame();
    	
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
    
    private Button initStartButton() {
    	startButton = new Button("Start Game");
    	startButton.setPrefWidth(100);
    	startButton.setOnAction(new EventHandler<ActionEvent>() {
    		public void handle(ActionEvent event) {
    			startGame();
    		}
    	});
    	return startButton;
    }
    
    private Button initExitButton() {
    	exitButton = new Button("Exit");
    	exitButton.setPrefWidth(100);
    	exitButton.setOnAction(new EventHandler<ActionEvent>() {
    		public void handle(ActionEvent event) {
    			stage.close();
    		}
    	});
    	return exitButton;
    }
    
    private VBox initStartViewButtons() {
    	VBox buttons = new VBox();
    	
    	buttons.setPadding(new Insets(15, 12, 15, 12));
        buttons.setSpacing(100);
        
        startButton = initStartButton();
    	exitButton = initExitButton();
    	
    	Text text = new Text();
		text.setFont(new Font(16));
		text.setWrappingWidth(400);
		text.setTextAlignment(TextAlignment.CENTER);
		text.setText("WASD or arrow keys to move around\n\nSpace to shoot\n\nProtect your home");
		
    	buttons.getChildren().addAll(text, startButton);
    	buttons.setAlignment(Pos.CENTER);
    	
    	return buttons;
    }
    
    private Label initTitle() {
    	Label title = new Label("Tank Battle");
    	title.setFont(new Font(20));
    	title.setPadding(new Insets(15, 15, 15, 15));
    	title.setTextAlignment(TextAlignment.CENTER);
    	return title;
    }
    
    private Scene initStartScene() {
    	BorderPane root = new BorderPane();
    	
    	VBox startViewButtons = initStartViewButtons();
    	Label title = initTitle();
    	
    	root.setTop(title);
    	root.setCenter(startViewButtons);
    	BorderPane.setAlignment(title, Pos.CENTER);
    	Scene scn = new Scene(root, SIZE, SIZE);
    	return scn;
    }
    
    private void configureStage() {
    	stage.setTitle("Tank Battle");
    	stage.setScene(startScene);
    	stage.setResizable(false);
    }
    
    private void clearGame() {
    	myGame = null;
    	frame = null;
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
