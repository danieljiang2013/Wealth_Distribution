import java.util.ArrayList;
import java.util.List;

/**
 * This class is a generic representation of a stat that can
 * be calculated in the system.
 * @param <T>
 */
public class LineGraph<T extends Number > {
	private String name = "";
	private Double sum = 0.0;

	private List<T> counts = new ArrayList<T>();
	
	public LineGraph(String name) {
		this.name = name;
	}
	
	public List<T> getCounts() {
		return counts;
	}
	
	public void add(T value) {
		if(value != null) {
			counts.add(value);
			sum += value.doubleValue();
		}
	}

	public String getName() {
		return name;
	}
	public double average() {
		return sum.doubleValue() / counts.size();
	}

	
}
