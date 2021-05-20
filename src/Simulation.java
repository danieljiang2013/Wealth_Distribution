
public class Simulation {
	
	public static void main(String[] args) {
		Grid grid = new Grid(Configuration.gridSize, Configuration.numPeople);
		Stats stats = new Stats();
		int ticks = 1000;
		
		
		for(int i = 0; i < ticks; i++) {
			grid.tick();
			stats.update(grid);
		}
		
		 String path = "";
		 var classes = new CSV(stats.getClassHistogram(), "Classes");
		 classes.saveTo(path);
		 var lorenzCurver = new CSV(stats.getLorenzCurves(), "LorenzCurves");
		 lorenzCurver.saveTo(path);
		 var population = new CSV(stats.getClassPopulation(), "Populations");
		 population.saveTo(path);
		 var giniIndex = new CSV(stats.getGiniIndex());
		 giniIndex.saveTo(path);
		
	}

}
