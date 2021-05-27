import java.util.ArrayList;
import java.util.Collections;

/**
 * This class is used to calculate the gini index for a distribution
 * of wealth amongst the 3 classes.
 */
public class GiniIndex {

	private LineGraph<Double> graph = new LineGraph<>("Gini_Index");
	/**
	 * On each tick, this method is called and it calculates the
	 * gini index.
	 * @param grid for which the gini index is calculated
	 */
	public void update(Grid grid) {
		int wealthSoFar = 0;
		double giniIndexReserve = 0.0f;
		var totalWealth = grid.getTotalWealth();
		ArrayList<Integer> sortedWealths = new ArrayList<>();

		for (int i = 0; i < grid.getPopulation().size(); i++) {
			sortedWealths.add(grid.getPopulation().get(i).getWealth());
		}
		Collections.sort(sortedWealths);
		float populationSize = grid.getPopulation().size();
		for (int i = 0; i < sortedWealths.size(); i++) {
			wealthSoFar += sortedWealths.get(i);
			
			double p = (i+1)/populationSize;
			double w = (float)wealthSoFar / totalWealth;
			giniIndexReserve = giniIndexReserve + p - w;
		}
		
		graph.add((giniIndexReserve/populationSize) * 2);
	}

	public LineGraph<Double> getLineGraph() {
		return graph;
	}
}
