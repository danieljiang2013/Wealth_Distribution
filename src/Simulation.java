import java.util.ArrayList;
import java.util.List;

/**
 * This is the main class that runs the entire simulation for the wealth
 * distribution model. The simulation is run according to the configured
 * parameters
 */
public class Simulation {
	public static void main(String[] args) {
		switch (Configuration.simulationType) {
			case SingleRun, MultipleRuns -> {
				runSim();
				break;
			}
			case ChangeTaxFrom0To100 -> {
				runSimTax();
				break;
			}
			default -> {
				Configuration.numRuns = 1; // run once as default
				runSim();
				break;saveStat();
			}
		}
	}

	/**
	 * This method represents a single run of the simulation for the configured
	 * number of ticks. It first set's up the grid with a population randomly
	 * distributed and the grain appropriately diffused. Then at each tick,
	 * relevant statistics from the grid are updated.
	 */
	private static Stats run() {
		Grid grid = new Grid(Configuration.gridSize, Configuration.numPeople);
		Stats stats = new Stats();

		for(int i = 0; i < Configuration.numTicks; i++) {
			grid.tick();
			stats.update(grid);
		}
		return stats;
	}
	/**
	 * This method is called in case the simulation is to be run one or more
	 * times, but without any extension/experimentation changes to the default
	 * system. (i.e no tax).
	 * - At the end of each run, the stats are collected.
	 * - At the end of all runs, the stats are then printed to their respective
	 *   CSV files
	 */
	private static void runSim() {
		List<Stats> statsRuns = new ArrayList<>();
		for (int r = 0; r < Configuration.numRuns(); r++) {
			System.out.println("Run - "+ (r + 1));
			var stat = run();
			statsRuns.add(stat);
		}
		for (int i = 0; i < statsRuns.size(); i++) {
			saveStat(statsRuns.get(i),  (i + 1) + "");
		}
		printAverages(statsRuns);
	}
	/**
	 * This method is called in case the simulation is to be run with a tax
	 * imposed on the population. The tax can either be on their income or
	 * on their wealth
	 * - At each run, the tax is incremented by 1%.
	 * - At the end of each run, the stats are collected.
	 * - At the end of all runs, the stats are then printed to their
	 *   respective CSV files
	 */
	private static void runSimTax() {
		List<Stats> statsRuns = new ArrayList<>();
		for (int i = 0; i < 100; i++) {
			System.out.println("Run - "+ (i+1));
			Configuration.tax = i/100.0f;
			statsRuns.add( run());
		}

		LineGraph<Double> gini = new LineGraph<>("Gini_VaryingTax");
		for (Stats stats : statsRuns) {
			gini.add(stats.getGiniIndex().average());
		}
		new CSV(gini).saveTo("");
	}
	/**
	 * This method calculates the average sizes for each wealth class
	 * along with the average of the gini index based on the number os
	 * runs completed. It then prints it out on the terminal
	 */
	private static void printAverages(List<Stats> statsRuns) {
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
			System.out.println("Average class population " + (i + 1)
					+ " : " + averages[i]);
		}
		System.out.println("Average gini : " + giniAverage);
		
	}
	/**
	 * This method generates a CSV file for each of the statistic
	 * calculated
	 */
	private static void saveStat(Stats stats, String suffix) {
		System.out.println("Generated Files - ");
		new CSV(stats.getIncomeReport(), "Income_Histograms").saveTo(suffix);
		new CSV(stats.getWealthReport(), "Wealth_Graphs").saveTo(suffix);
		new CSV(stats.getClassProperties(), "Properties").saveTo(suffix);
		new CSV(stats.getClassPopulation(), "Populations").saveTo(suffix);
		new CSV(stats.getGiniIndex()).saveTo("");
	}
}
