import java.util.ArrayList;
import java.util.List;

public class Histogram {
	private String name;
	private ArrayList<Integer> counts;
	
	public Histogram(String name) {
		this.name = name;
	}
	
	public List<Integer> getCounts() {
		return counts;
	}
	
	public void add(int value) {
		counts.add(value);
	}

	public String getName() {
		return name;
	}
}
