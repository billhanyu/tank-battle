import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class LeaderBoard {
	private ArrayList<Leader> leaders;
	private static final int SIZE = 10;

	public LeaderBoard() {
		ArrayList<Leader> lds = read();
		if (lds != null) {
			leaders = lds;
		}
		else {
			leaders = new ArrayList<Leader>();
		}
	}

	public boolean canGetOn(int score) {
		if (leaders.size() < SIZE) return true;
		return score > leaders.get(leaders.size() - 1).score;
	}

	public void putOn(Leader l) {
		leaders.add(l);
		leaders.sort(null);
		for (int i = SIZE; i < leaders.size(); i++) {
			leaders.remove(i);
		}
	}
	
	public ArrayList<Leader> getLeaders() {
		return leaders;
	}

	public void save() {
		try {
			FileOutputStream fout = new FileOutputStream("leaders.ser");
			ObjectOutputStream oos = new ObjectOutputStream(fout);
			oos.writeObject(leaders);
			oos.close();

		} catch(Exception ex) {
			ex.printStackTrace();
		}
	}

	private ArrayList<Leader> read() {
		try{
			FileInputStream fin = new FileInputStream("leaders.ser");
			ObjectInputStream ois = new ObjectInputStream(fin);
			@SuppressWarnings("unchecked")
			ArrayList<Leader> lds = (ArrayList<Leader>) ois.readObject();
			ois.close();
			return lds;
		} catch(Exception ex) {
			return null;
		}
	}
}
