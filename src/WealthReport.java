import java.util.ArrayList;
import java.util.List;

public class WealthReport implements IStatistic {
	List<LineGraph<Integer>> classes;
	
	public WealthReport() {
		classes = new ArrayList<LineGraph<Integer>>();
		classes.add(new LineGraph<Integer>("Poor"));
		classes.add(new LineGraph<Integer>("Medium"));
		classes.add(new LineGraph<Integer>("Rich"));
	}
	
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
