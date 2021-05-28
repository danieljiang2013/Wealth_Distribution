import java.util.ArrayList;
import java.util.List;

/**
 * This class represents the entire grid, its entire population
 * and all the patches on the grid. It can be thought of as a 2D
 * grid of fixed patches around which person(s) can move.
 */
public class Grid {
	// A list of all the people
	private List<Person> population;
	// A collection of all patches in the grid
	private Patches patches;
	// total size of the grid
	private int size;

	private int mostWealth;
	private int totalWealth;
	TaxPopulation taxPopulation;

	/**
	 * The grid is initialised with a set size and initial population.
	 * The constructor initialises all the patches, set's up the grain
	 * and moves each person to a random location in the grid
	 * @param size
	 * @param numPeople
	 */
	Grid(int size, int numPeople){
		this.size = size;
		patches = new Patches(size);
		population = new ArrayList<Person>();
		for(int i = 0; i < numPeople; i ++) {
			population.add(new Person(this,
					patchAt(randomize(), randomize())));
		}
		taxPopulation = new TaxPopulation(population);
	}
	// returns a random number between 0 and size
	private int randomize(){
		var random = (int) (Math.random() * (size));
		if (random >= 0 && random <= size){
			return random;
		}
		return 0;
	}
	// Returns the patch at the specified coordinates
	public Patch patchAt(int x, int y) {
		int xx = x % size;
		if(xx < 0) {
			xx = size + xx;
		}
		int yy = y % size;
		if(yy < 0) {
			yy = size + yy;
		}
		return patches.getPatchAt(xx,yy);
	}

	/**
	 * At each tick, simulate the model. At the end of each tick,
	 * calculate and save the stats
	 */
	public void tick() {

		// Harvest the grain to the population
		for(int i = 0; i < population.size(); i++) {
			population.get(i).harvest();
		}
		// harvest the grain from the patches
		harvest();

		// life
		for(int i = 0; i < population.size(); i++) {
			population.get(i).move_eat_die();
		}
		// update the patches,
		patches.tick();

		calcMostWealth();
		calcTotalWealth();
		taxPopulation.tick();
	}
	// this method sets the grain at each patch to zero
	private void harvest() {
		for (int i = 0; i < population.size(); i++) {
			population.get(i).getLocation().setInitialGrain(0);
		}
	}
	
	public List<Person> getPopulation() {
		return population;
	}

	private void calcMostWealth() {
		mostWealth = 0;
		for (int i = 0; i < population.size(); i++) {
			if (population.get(i).getWealth() > mostWealth) {
				mostWealth = population.get(i).getWealth();
			}
		}
	}

	public int getMostWealth() {
		return mostWealth;
	}

	public void calcTotalWealth() {
		totalWealth = 0;
		for (int i = 0; i < population.size(); i++) {
			totalWealth += population.get(i).getWealth();
		}
	}

	public int getTotalWealth() {
		return totalWealth;
	}

}
