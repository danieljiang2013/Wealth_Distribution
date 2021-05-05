import java.util.ArrayList;
import java.util.List;

public class LineGraph<T> {
	private String name = "";

	private List<T> counts = new ArrayList<T>();
	
	public LineGraph(String name) {
		this.name = name;
	}
	
	public List<T> getCounts() {
		return counts;
	}
	
	public void add(T value) {
		counts.add(value);
	}

	public String getName() {
		return name;
	}
}
