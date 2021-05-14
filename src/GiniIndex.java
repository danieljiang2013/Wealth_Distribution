import java.util.ArrayList;
import java.util.Collections;

public class GiniIndex {

	private LineGraph<Double> graph = new LineGraph<Double>("Gini Index");
	
	public void update(Grid grid) {	
		var totalWealth = grid.getTotalWealth();
		int wealthSoFar = 0;
		double giniIndexReserve = 0.0f;
		
		ArrayList<Integer> sortedWealths = new ArrayList<Integer>();
		for (int i = 0; i < grid.getPopulation().size(); i++) {
			sortedWealths.add(grid.getPopulation().get(i).getWealth());
		}
		Collections.sort(sortedWealths);
		
		for (int i = 0; i < sortedWealths.size(); i++) {
			wealthSoFar += sortedWealths.get(i);
			giniIndexReserve += ((float)i/grid.getPopulation().size()) 
					- ((float)wealthSoFar / totalWealth);
		}
		
		graph.add(giniIndexReserve);
	}

	public LineGraph<Double> getLineGraph() {
		return graph;
	}
}
