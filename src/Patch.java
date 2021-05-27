
/**
 * This Class represents a single patch on the grid.
 * Each patch is initialized to a unique location on the grid
 * and are set up with an initial grain and max grain.
 */
public class Patch {

	// x,y coordinates on the grid
	private int x;
	private int y;
	private double maxGrain;
	private double grain;
	private int grainGrowth = Configuration.numGrainGrown;
	private int currentInterval = 0;
	private int tickInterval = Configuration.gainGrowthInterval;

	// Each patch is initialized to a unique location
	public Patch(int x, int y) {
		this.x = x;
		this.y = y;
	}
	// Represent the change in grain based on the grain growth interval
	public void tick() {
		if (currentInterval == 0) {
			grain = Math.min(grain + grainGrowth, maxGrain);
		}
		currentInterval++;
		currentInterval = currentInterval % tickInterval;
	}

	/**
	 * Default getters and setters for class variables
	 */

	public int getX() { return x; }
	public int getY() { return y; }
	public Vector2<Integer> getPosition(){
		return new Vector2<Integer>(getX(), getY());
	}

	public double getGrain() {
		return this.grain;
	}
	public void setInitialGrain(double value) {
		this.grain = value;
	}
	public void setMaxGrain(double value) {
		this.maxGrain = value;
	}
	public double getMaxGrain() {
		return this.maxGrain;
	}


}
