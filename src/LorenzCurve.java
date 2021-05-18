import java.util.ArrayList;
import java.util.Collections;

public class LorenzCurve {
	
	public LineGraph<Double> curve(Grid grid) {
		
		var lorenzGraph = new LineGraph<Double>("Lorenz Curver");
		var totalWealth = grid.getTotalWealth();
		int wealthSoFar = 0;
		
		ArrayList<Integer> sortedWealths = new ArrayList<Integer>();
		for (int i = 0; i < grid.getPopulation().size(); i++) {
			sortedWealths.add((int)grid.getPopulation().get(i).getWealth());
		}
		Collections.sort(sortedWealths);
		
		for (int i = 0; i < sortedWealths.size(); i++) {
			wealthSoFar += sortedWealths.get(i);
			lorenzGraph.add((double)wealthSoFar/totalWealth);
		}
		
		return lorenzGraph;
	}
	
}
