import java.util.ArrayList;
import java.util.List;

public class Simulation {
	
	public static void main(String[] args) {
		switch (Configuration.simulationType) {
			case MultipleRuns: {
				multipleRunSim();
				break;
			}
			case ChangeTaxFrom0To100:{
				increasingTax();
				break;
			}
		}
	}
	
	public static void increasingTax() {
		List<Stats> statsRuns = new ArrayList<Stats>();
		for (int i = 0; i < 100; i++) {
			Configuration.tax = i/100.0f;
			var stat = run();
			statsRuns.add(stat);	
		}		
		LineGraph<Double> gini = new LineGraph<>("Gini Index varying tax");		
		
		for (Stats stats : statsRuns) {
			gini.add(stats.getGiniIndex().average());
		}
		var giniCSV = new CSV(gini);
		giniCSV.saveTo("");
	}
	
	public static void multipleRunSim() {
		List<Stats> statsRuns = new ArrayList<Stats>();
		for (int r = 0; r < Configuration.numRuns; r++) {
			var stat = run();
			statsRuns.add(stat);			
		}
		
		printAverages(statsRuns);
		
		for (int i = 0; i < statsRuns.size(); i++) {
			saveStat(statsRuns.get(i),  i + "-");
		}
		
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
			averages[i] /= statsRuns.size();
			System.out.println("Average class population " + (i + 1) + " : " + averages[i]);
		}
		System.out.println("Average gini : " + giniAverage);
		
		
	}
	
	
	
	public static Stats run() {
		Grid grid = new Grid(Configuration.gridSize, Configuration.numPeople);
		Stats stats = new Stats();
		
		int ticks = 1000;
		
		
		for(int i = 0; i < ticks; i++) {
			grid.tick();
			stats.update(grid);
		}
		return stats;
	}
	
	public static void saveStat(Stats stats, String prefix) {
		
		 var classes = new CSV(stats.getClassHistogram(), "Classes");
		 classes.saveTo(prefix);
		 var lorenzCurver = new CSV(stats.getLorenzCurves(), "LorenzCurves");
		 lorenzCurver.saveTo(prefix);
		 var population = new CSV(stats.getClassPopulation(), "Populations");
		 population.saveTo(prefix);
		 var giniIndex = new CSV(stats.getGiniIndex());
		 giniIndex.saveTo(prefix);
	}

}
