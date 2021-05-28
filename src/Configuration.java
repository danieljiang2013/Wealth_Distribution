
public class Configuration {
	
	public enum SimulationType {
		SingleRun,
		MultipleRuns,
		ChangeTaxFrom0To100
	}
	
	public enum TaxSystem {
		Wealth,
		Income
	}

	

	public static int numPeople = 250;
	public static int maxVision = 5;
	public static int metabolismMax = 15;
	public static int lifeExpectancyMin = 1;
	public static int lifeExpectancyMax = 83;
	public static double percentBestLand = 0.1;

	
	public static int gainGrowthInterval = 1;
	public static int numGrainGrown = 4;
	
	public static int gridSize = 51;
	public static int maxGrain = 50; // netlogo global
	
	
	public static double tax = 0;
	public static int numRuns = 3;
	public static SimulationType simulationType = SimulationType.SingleRun;

	public static TaxSystem taxSystem = TaxSystem.Wealth;
	
	
	
	

	public static int numRuns() {
		if (simulationType == SimulationType.MultipleRuns) {
			return numRuns;
		} else return 1;
	}
}


