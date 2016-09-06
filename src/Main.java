
import java.util.ArrayList;

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
    private KeyFrame frame;
    private Timeline animation;
    private LeaderBoard board;
    private boolean didInputName;

    /**
     * Set things up at the beginning.
     */
    @Override
    public void start (Stage s) {
    	this.stage = s;
    	board = new LeaderBoard();
    	Scene startScene = initStartScene();
    	configureStage(startScene);
    	stage.show();
    }
    
    private void gameStart() {
    	// create your own game here
        myGame = new Game();
        stage.setTitle(myGame.getTitle());
        didInputName = false;

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
    	Scene winScene = initWinScene();
    	stage.setScene(winScene);
    	clearGame();
    }
    
    public void gameOver() {
    	Scene overScene = initOverScene();
    	stage.setScene(overScene);
    	clearGame();
    }
    
    public void showLeaders() {
    	Scene leadersScene = initLeadersScene();
    	stage.setScene(leadersScene);
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
    	Button startButton = new Button("Start Game");
    	startButton.setPrefWidth(120);
    	startButton.setOnAction(new EventHandler<ActionEvent>() {
    		public void handle(ActionEvent event) {
    			gameStart();
    		}
    	});
    	return startButton;
    }
    
    private Button initLeadersButton() {
    	Button leadersButton = new Button("Leader Board");
    	leadersButton.setPrefWidth(120);
    	leadersButton.setOnAction(new EventHandler<ActionEvent>() {
    		public void handle(ActionEvent event) {
    			showLeaders();
    		}
    	});
    	return leadersButton;
    }
    
    private Button initExitButton() {
    	Button exitButton = new Button("Exit");
    	exitButton.setPrefWidth(120);
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
        
        Button startButton = initStartButton();
    	
    	Text text = new Text();
		text.setFont(new Font(16));
		text.setWrappingWidth(400);
		text.setTextAlignment(TextAlignment.CENTER);
		text.setText("WASD or arrow keys to move around\n\nSpace to shoot\n\nProtect your home and destroy enemies");
		
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
    
    private Scene initOverScene() {
    	Label indicator = new Label("Game Over\nScore: " + myGame.getScore());
    	indicator.setFont(new Font(20));
    	Button startButton = initStartButton();
    	startButton.setText("Play Again");
    	Button exitButton = initExitButton();
    	VBox root = new VBox();
    	root.setSpacing(60);
    	root.setAlignment(Pos.CENTER);
    	root.getChildren().addAll(indicator, startButton, exitButton);
    	Scene overScene = new Scene(root, SIZE, SIZE);
    	return overScene;
    }
    
    private Scene initWinScene() {
    	VBox root = new VBox();
    	root.setSpacing(60);
    	root.setAlignment(Pos.CENTER);
    	
    	int score = myGame.getScore();
    	Label indicator = new Label("You Won!\nScore: " + score);
    	root.getChildren().add(indicator);
    	if (board.canGetOn(score)) {
    		root.getChildren().add(initNameInput(score));
    	}
    	indicator.setFont(new Font(20));
    	Button startButton = initStartButton();
    	Button leadersButton = initLeadersButton();
    	startButton.setText("Play Again");
    	Button exitButton = initExitButton();
    	
    	root.getChildren().addAll(startButton, leadersButton, exitButton);
    	Scene winScene = new Scene(root, SIZE, SIZE);
    	return winScene;
    }
    
    private Scene initLeadersScene() {
    	BorderPane root = new BorderPane();
    	
    	Node leaders = initLeadersView();
    	
    	Label title = new Label("Leader Board");
    	title.setFont(new Font(20));
    	title.setPadding(new Insets(15, 15, 15, 15));
    	title.setTextAlignment(TextAlignment.CENTER);
    	
    	root.setTop(title);
    	root.setCenter(leaders);
    	BorderPane.setAlignment(title, Pos.CENTER);
    	Scene scn = new Scene(root, SIZE, SIZE);
    	return scn;
    }
    
    private Node initLeadersView() {
    	VBox box = new VBox();
    	box.setPadding(new Insets(15, 12, 15, 12));
        box.setSpacing(60);
        
        HBox leadersBox = new HBox();
        box.setPadding(new Insets(15, 12, 15, 12));
        leadersBox.setSpacing(60);
        leadersBox.setAlignment(Pos.CENTER);
    	
        ArrayList<Leader> leaders = board.getLeaders();
        Button startButton = initStartButton();
        startButton.setText("Play Again");
        Button exitButton = initExitButton();
        
        VBox names = new VBox();
        names.setSpacing(20);
        VBox scores = new VBox();
        scores.setSpacing(20);
        for (Leader l: leaders) {
        	Text text = new Text();
        	text.setFont(new Font(16));
        	text.setWrappingWidth(100);
        	text.setTextAlignment(TextAlignment.JUSTIFY);
        	text.setText(l.name);
        	names.getChildren().add(text);
        	Text s = new Text();
        	s.setFont(new Font(16));
        	s.setWrappingWidth(100);
        	s.setTextAlignment(TextAlignment.RIGHT);
        	s.setText(""+l.score);
        	scores.getChildren().add(s);
        }
        leadersBox.getChildren().addAll(names, scores);
    	box.getChildren().addAll(leadersBox, startButton, exitButton);
    	box.setAlignment(Pos.CENTER);
    	return box;
    }
    
    private VBox initNameInput(int score) {
    	VBox whole = new VBox();
    	whole.setAlignment(Pos.CENTER);
    	Label indicator = new Label("You just got on the leader board!");
    	whole.setSpacing(20);
    	HBox box = new HBox();
    	box.setAlignment(Pos.CENTER);
    	box.setPadding(new Insets(15, 12, 15, 12));
    	box.setSpacing(20);
    	Label prompt = new Label("Name:");
    	TextField input = new TextField();
    	Button confirm = new Button("OK");
    	confirm.setOnAction(new EventHandler<ActionEvent>() {
    		public void handle(ActionEvent event) {
    			if (didInputName) {
    				indicator.setText("You've already put your name in!");
    				return;
    			}
    			didInputName = true;
    			Leader l = new Leader(input.getText(), score);
    			board.putOn(l);
    			board.save();
    			indicator.setText("Succeeded!");
    		}
    	});
    	box.getChildren().addAll(prompt, input, confirm);
    	whole.getChildren().addAll(indicator, box);
    	return whole;
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
