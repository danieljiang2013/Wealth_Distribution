import java.util.ArrayList;
import java.util.List;

public class ClassPopulation {
	private List<LineGraph<Integer>> classes = new ArrayList<LineGraph<Integer>>();
	
	public ClassPopulation() {
		classes = new ArrayList<LineGraph<Integer>>();
		classes.add(new LineGraph<Integer>("Poor"));
		classes.add(new LineGraph<Integer>("Medium"));
		classes.add(new LineGraph<Integer>("Rich"));
		
	}
	
	public void update(Grid grid) {
		
	}

	public List<LineGraph<Integer>> getLineGraphs() {
		return classes;
	}
}
