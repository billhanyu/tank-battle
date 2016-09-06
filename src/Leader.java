import java.io.Serializable;

public class Leader implements Comparable<Leader>, Serializable {
	private static final long serialVersionUID = 1L;
	
	String name;
	int score;
	
	public Leader(String name, int score) {
		this.name = name;
		this.score = score;
	}
	
	@Override
	public int compareTo(Leader o) {
		if (score > o.score) return -1;
		else if (score < o.score) return 1;
		return 0;
	}
}
