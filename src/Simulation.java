import java.util.ArrayList;
import java.util.List;

public class Simulation {
	
	public static void main(String[] args) {
		switch (Configuration.simulationType) {
			case SingleRun:
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
			System.out.println("Run - "+ i);
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
		for (int r = 0; r < Configuration.numRuns(); r++) {
			System.out.println("Run - "+ r);
			var stat = run();
			statsRuns.add(stat);
		}
		
		Stats.printAverages(statsRuns);
		
		for (int i = 0; i < statsRuns.size(); i++) {
			statsRuns.get(i).saveStats(i + "");
		}
		
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
	

}
