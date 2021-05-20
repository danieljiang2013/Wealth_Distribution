import java.util.ArrayList;
import java.util.List;

public class Stats {
	private ClassHistogram classHistogram = new ClassHistogram();
	private LorenzCurve lorenzCurve = new LorenzCurve();
	private GiniIndex giniIndex = new GiniIndex();
	private ClassPopulation population = new ClassPopulation(); 
	private List<LineGraph<Double>> lorenzCurves = new ArrayList<LineGraph<Double>>(); 
	
	public void update(Grid grid) {
		//classHistogram.update(grid);
		giniIndex.update(grid);
		population.update(grid);
		//lorenzCurves.add(lorenzCurve.curve(grid));
	}
	
	public List<LineGraph<Integer>> getClassHistogram() {
		return classHistogram.getHistogram();
	}
	
	public List<LineGraph<Double>> getLorenzCurves(){
		return lorenzCurves;
	}
	
	public LineGraph<Double> getGiniIndex() {
		return giniIndex.getLineGraph();
	}
	
	public List<LineGraph<Integer>> getClassPopulation() {
		return population.getLineGraphs();
	}
}
