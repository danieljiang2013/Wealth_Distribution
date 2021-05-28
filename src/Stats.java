import java.util.ArrayList;
import java.util.List;

public class Stats {
	private GiniIndex giniIndex = new GiniIndex();
	private ClassPopulation population = new ClassPopulation(); 
	private IncomeReport incomeReport = new IncomeReport();
	private WealthReport wealthReport = new WealthReport();
	private RecordClassProperties classProperties = new RecordClassProperties();
	private List<IStatistic> stats = new ArrayList<IStatistic>();
	
	public void update(Grid grid) {
		for (IStatistic iStatistic : stats) {
			iStatistic.update(grid);
		}
	}
	
	public LineGraph<Double> getGiniIndex() {
		return giniIndex.getLineGraph();
	}
	public List<LineGraph<Integer>> getClassPopulation() {

		return population.getLineGraphs();
	}
	public List<LineGraph<Integer>> getIncomeReport(){

		return incomeReport.getLineGraphs();
	}
	public List<LineGraph<Integer>> getWealthReport(){

		return wealthReport.getLineGraphs();
	}
	public List<LineGraph<Integer>> getClassProperties(){

		return classProperties.getLineGraphs();
	}
	
	public void saveStats(String prefix){
		new CSV(incomeReport.getLineGraphs(), "Income Histograms").saveTo(prefix);				
		new CSV(wealthReport.getLineGraphs(), "Wealth Graph").saveTo(prefix);
		new CSV(classProperties.getLineGraphs(), "Properties").saveTo(prefix);
		new CSV(population.getLineGraphs(), "Populations").saveTo(prefix);
		new CSV(giniIndex.getLineGraph()).saveTo(prefix);
	}
	
	public static void printAverages(List<Stats> statsRuns) {
		double[] averages = new double[3];
		double giniAverage = 0;
		
		for (int i = 0; i < statsRuns.size(); i++) {
			var stats = statsRuns.get(i);
			giniAverage += stats.getGiniIndex().average();	
			var population = stats.getClassPopulation();
			for(int j = 0; j < population.size(); j++) {
				averages[j] += population.get(j).average();
			}
		}
		
		for (int i = 0; i < averages.length; i++) {
			averages[i] /= statsRuns.size();
		}
		giniAverage /= statsRuns.size();
		
		for (int i = 0; i < averages.length; i++) {
			System.out.println("Average class population " + (i + 1) + " : " + averages[i]);
		}
		System.out.println("Average gini : " + giniAverage);
	}
	
}
