import java.util.ArrayList;
import java.util.Collections;

public class GiniIndex implements IStatistic {

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
