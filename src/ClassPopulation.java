import java.util.ArrayList;
import java.util.Iterator;
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
		int counts[] = {0,0,0};
		for (Person p : grid.getPopulation()) {
			counts[p.classRank()] ++;
		} 
		for (int i = 0; i < counts.length; i++) {
			classes.get(i).add(counts[i]);
		}
	}

	public List<LineGraph<Integer>> getLineGraphs() {
		return classes;
	}
}
