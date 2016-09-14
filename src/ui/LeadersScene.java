package ui;
import java.util.ArrayList;

import game.Game;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import leader.Leader;

/**
 * @author billyu
 * scene to display the leader board
 */
public class LeadersScene extends GameScene {
	
	public LeadersScene(GameUI manager, int SIZE, Game game) {
		super(manager, SIZE, game);
	}
	
	public Scene initScene() {
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
    
    /**
     * @return leader board ui element
     */
    private Node initLeadersView() {
    	VBox box = new VBox();
    	box.setPadding(new Insets(15, 12, 15, 12));
        box.setSpacing(40);
    	
        ArrayList<Leader> leaders = uiManager.getBoard().getLeaders();
        Button startButton = uiManager.initStartButton();
        startButton.setText("Play Again");
        Button exitButton = uiManager.initExitButton();
        
        Node leadersBox = initLeadersBox(leaders);
    	box.getChildren().addAll(leadersBox, startButton, exitButton);
    	box.setAlignment(Pos.CENTER);
    	return box;
    }

	/**
	 * @param leaders arraylist of leaders from LeaderBoard
	 * @return the leader board content
	 */
	private HBox initLeadersBox(ArrayList<Leader> leaders) {
		HBox leadersBox = new HBox();
        leadersBox.setPadding(new Insets(15, 12, 15, 12));
        leadersBox.setSpacing(60);
        leadersBox.setAlignment(Pos.CENTER);
        VBox names = new VBox();
        names.setSpacing(20);
        VBox scores = new VBox();
        scores.setSpacing(20);
        for (Leader l: leaders) {
        	Text text = new Text();
        	text.setFont(new Font(16));
        	text.setWrappingWidth(100);
        	text.setTextAlignment(TextAlignment.JUSTIFY);
        	text.setText(l.getName());
        	names.getChildren().add(text);
        	Text s = new Text();
        	s.setFont(new Font(16));
        	s.setWrappingWidth(100);
        	s.setTextAlignment(TextAlignment.RIGHT);
        	s.setText(""+l.getScore());
        	scores.getChildren().add(s);
        }
        leadersBox.getChildren().addAll(names, scores);
		return leadersBox;
	}
}
