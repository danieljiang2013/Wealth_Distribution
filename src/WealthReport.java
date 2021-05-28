import java.util.ArrayList;
import java.util.List;
/**
 * This class calculates the wealth of people in each of the 3
 * wealth classes.
 */
public class WealthReport implements IStatistic {
	List<LineGraph<Integer>> classes;
	
	public WealthReport() {
		classes = new ArrayList<LineGraph<Integer>>();
		classes.add(new LineGraph<Integer>("Poor"));
		classes.add(new LineGraph<Integer>("Medium"));
		classes.add(new LineGraph<Integer>("Rich"));
	}
	/**
	 * On each tick, this method is called and it calculates the
	 * total wealth for each class
	 * @param grid for which the class wealth are calculated
	 */
	public void update(Grid grid) {
		int[] wealths = new int[3];
		for (Person p : grid.getPopulation()) {
			wealths[p.classRank()] += p.getWealth();
		}
		for (int i = 0; i < wealths.length; i++) {
			classes.get(i).add(wealths[i]);
		}
	}

	public List<LineGraph<Integer>> getLineGraphs() {
		return classes;		
	}
}
