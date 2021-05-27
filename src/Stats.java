import java.util.List;

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
}
