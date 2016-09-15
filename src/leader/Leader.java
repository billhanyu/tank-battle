// This entire file is part of my masterpiece.
// Bill Yu

package leader;
import java.io.Serializable;

/**
 * @author billyu
 * player on the leader board
 */
public class Leader implements Comparable<Leader>, Serializable {
	private static final long serialVersionUID = 1L;
	
	private String name;
	private int score;
	
	public Leader(String name, int score) {
		this.setName(name.trim());
		this.setScore(score);
	}
	
	@Override
	public int compareTo(Leader o) {
		if (getScore() > o.getScore()) return -1;
		else if (getScore() < o.getScore()) return 1;
		return 0;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}
}
