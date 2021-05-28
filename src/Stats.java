import java.util.List;
import java.util.ArrayList;

/**
 * This class is the sole entry point to all statistics determined by the system
 * It contains instances for multiple type of property objects and provides a
 * common point to access all of them.
 */
public class Stats {
	private GiniIndex giniIndex = new GiniIndex();
	private ClassPopulation population = new ClassPopulation(); 
	private IncomeReport incomeReport = new IncomeReport();
	private WealthReport wealthReport = new WealthReport();
	private RecordClassProperties classProperties = new RecordClassProperties();
	

	/**
	 * This method is called on each tick of the simulation.
	 * It calculate the statistic for each property type at the end of each tick.
	 * @param grid - for which the statistics are to be calculated
	 */
	public void update(Grid grid) {
		giniIndex.update(grid);
		population.update(grid);
		incomeReport.update(grid);
		wealthReport.update(grid);
		classProperties.update(grid);
	}
	

	/**
	 * Public getters, that returns the requested object property instance
	 */

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
	
	/**
	 * This method generates a CSV file for each of the statistic
	 * calculated
	 */
	public void saveStats(String prefix){
		new CSV(incomeReport.getLineGraphs(), "Income Histograms").saveTo(prefix);				
		new CSV(wealthReport.getLineGraphs(), "Wealth Graph").saveTo(prefix);
		new CSV(classProperties.getLineGraphs(), "Properties").saveTo(prefix);
		new CSV(population.getLineGraphs(), "Populations").saveTo(prefix);
		new CSV(giniIndex.getLineGraph()).saveTo(prefix);
	}
	
	/**
	 * This method calculates the average sizes for each wealth class
	 * along with the average of the gini index based on the number os
	 * runs completed. It then prints it out on the terminal
	 */
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
