/**
 * Configuration class defines the set of configuration/setup parameters
 * that are used to initialize and then simulate the configuration.
 *
 * A simulation can be configured to run in one of 3 ways -
 * 1. SingleRun 			-> runs the simulation once for `numTicks`
 * 2. MultipleRuns 			-> runs the simulation `numRuns` times,
 *    each for `numTicks`
 * 3. ChangeTaxFrom0To100	-> runs the simulation 100 times with tax
 *    increasing from 0 - 99 after each run. Tax can be selected to be on
 *    either 'wealth' or on 'income'
 */
public class Configuration {

	// Set Simulation Run Type
	public static SimulationType simulationType = SimulationType.SingleRun;

	// Experimentation parameters
	public static final TaxSystem taxSystem = TaxSystem.Wealth; // or TaxSystem.Income
	public static double tax = 0; // automatically varied from 0 - 100
	private static int numRuns = 3;

	public enum SimulationType {
		SingleRun, // Default
		MultipleRuns, // Experimentation
		ChangeTaxFrom0To100 // Experimentation
	}
	// If simulationType is ChangeTaxFrom0To100, then one of the following can
	// be chosen
	public enum TaxSystem {
		Wealth,
		Income
	}

	// configuration parameters
	public static final int numPeople = 250;
	public static final int maxVision = 5;
	public static final int metabolismMax = 15;
	public static final int lifeExpectancyMin = 1;
	public static final int lifeExpectancyMax = 83;
	public static final double percentBestLand = 0.1;
	public static final int gainGrowthInterval = 1;
	public static final int numGrainGrown = 4;

	// netlogo globals
	public static final int gridSize = 51;
	public static final int maxGrain = 50;

	// This method returns either 1 or `numRuns` based on value `simulationType`
	public static final int numRuns() {
		if (simulationType == SimulationType.MultipleRuns) {
			return numRuns;
		} else return 1;
	}
 	// The number of ticks for a single run of the simulation
	public static final int numTicks = 1000;
}


