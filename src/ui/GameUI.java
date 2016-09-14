package ui;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import leader.Leader;
import leader.LeaderBoard;

/**
 * @author billyu
 * common buttons reused across scenes
 */
public class GameUI {
	private EventHandler<ActionEvent> gameStart;
	private EventHandler<ActionEvent> showLeaders;
	private EventHandler<ActionEvent> gameExit;
	private boolean didInputName;
	private LeaderBoard board;
	
	public GameUI(EventHandler<ActionEvent> start,
			EventHandler<ActionEvent> leaders,
			EventHandler<ActionEvent> exit) {
		gameStart = start;
		showLeaders = leaders;
		gameExit = exit;
		refreshGame();
		setBoard(new LeaderBoard());
	}
	
	/**
	 * refresh the states
	 */
	public void refreshGame() {
		didInputName = false;
	}

	/**
	 * @return button for game start
	 */
	public Button initStartButton() {
		Button startButton = new Button("Start Game");
		startButton.setPrefWidth(120);
		startButton.setOnAction(gameStart);
		return startButton;
	}

	/**
	 * @return button for displaying leader board
	 */
	public Button initLeadersButton() {
		Button leadersButton = new Button("Leader Board");
		leadersButton.setPrefWidth(120);
		leadersButton.setOnAction(showLeaders);
		return leadersButton;
	}

	/**
	 * @return button to quit game
	 */
	public Button initExitButton() {
		Button exitButton = new Button("Exit");
		exitButton.setPrefWidth(120);
		exitButton.setOnAction(gameExit);
		return exitButton;
	}
	
	/**
	 * @param score player's score after the game finishes
	 * @return a UI element that let the player input the name
	 */
	public VBox initNameInput(int score) {
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
    			getBoard().putOn(l);
    			getBoard().save();
    			indicator.setText("Succeeded!");
    		}
    	});
    	box.getChildren().addAll(prompt, input, confirm);
    	whole.getChildren().addAll(indicator, box);
    	return whole;
    }

	/**
	 * @return the leader board object
	 */
	public LeaderBoard getBoard() {
		return board;
	}

	/**
	 * @param board leader board object
	 */
	private void setBoard(LeaderBoard board) {
		this.board = board;
	}
}
