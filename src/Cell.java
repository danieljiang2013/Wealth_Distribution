
// The world is two dimensional and is divided up into a grid of patches (cells).
// Each patch is a square piece of â€œgroundâ€� over which people can move.

import java.util.List;

// rename to Patch
public class Cell {

	private int x;
	private int y;
	private double maxGrain;
	private double grain;
	private int currentInterval = 0;
	private int tickInterval = Configuration.gainGrowthInterval;
	private int grainGrowth = Configuration.numGrainGrown;

	public Cell(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public void tick() {
		if (currentInterval == 0) {
			grain = Math.min(grain + grainGrowth, maxGrain);
		}
		currentInterval++;
		currentInterval = currentInterval % tickInterval;
	}

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
	public void harvestGrain(double value) {
		this.grain -= value;
	}


}
