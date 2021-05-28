import java.util.ArrayList;
import java.util.List;

/**
 * This class calculates the number of people that are present
 * in each of the 3 wealth classes.
 */
public class ClassPopulation {
	private List<LineGraph<Integer>> classes;
	
	public ClassPopulation() {
		classes = new ArrayList<>();
		classes.add(new LineGraph<>("Poor"));
		classes.add(new LineGraph<>("Medium"));
		classes.add(new LineGraph<>("Rich"));
	}

	/**
	 * On each tick, this method is called and it calculates the
	 * counts for each class at the end of that tick
	 * @param grid for which the class counts are calculated
	 */
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
