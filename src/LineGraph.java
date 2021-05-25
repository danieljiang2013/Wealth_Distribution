import java.util.ArrayList;
import java.util.List;

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
		counts.add(value);
		sum += value.doubleValue();
	}

	public String getName() {
		return name;
	}
	
	public double average() {
		return sum.doubleValue() / counts.size();
	}
	
	public static Number addNumbers(Number a, Number b) {
	    if(a instanceof Double || b instanceof Double) {
	        return a.doubleValue() + b.doubleValue();
	    } else if(a instanceof Float || b instanceof Float) {
	        return a.floatValue() + b.floatValue();
	    } else if(a instanceof Long || b instanceof Long) {
	        return a.longValue() + b.longValue();
	    } else {
	        return a.intValue() + b.intValue();
	    }
	}
	
}
