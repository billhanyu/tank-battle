import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;

public class GameButtons {
	private EventHandler<ActionEvent> gameStart;
	private EventHandler<ActionEvent> showLeaders;
	private EventHandler<ActionEvent> gameExit;
	
	public GameButtons(EventHandler<ActionEvent> start,
			EventHandler<ActionEvent> leaders,
			EventHandler<ActionEvent> exit) {
		gameStart = start;
		showLeaders = leaders;
		gameExit = exit;
	}

	public Button initStartButton() {
		Button startButton = new Button("Start Game");
		startButton.setPrefWidth(120);
		startButton.setOnAction(gameStart);
		return startButton;
	}

	public Button initLeadersButton() {
		Button leadersButton = new Button("Leader Board");
		leadersButton.setPrefWidth(120);
		leadersButton.setOnAction(showLeaders);
		return leadersButton;
	}

	public Button initExitButton() {
		Button exitButton = new Button("Exit");
		exitButton.setPrefWidth(120);
		exitButton.setOnAction(gameExit);
		return exitButton;
	}
}
