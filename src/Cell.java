
// The world is two dimensional and is divided up into a grid of patches (cells).
// Each patch is a square piece of “ground” over which people can move.

import java.util.List;

// rename to Patch
public class Cell {

	private int x;
	private int y;
	private int maxGrain;
	private int grain;
	private int currentInterval = 0;
	private int tickInterval = Configuration.gainGrowthInterval;
	private int grainGrowth = Configuration.numGrainGrown;


	public Cell(int x, int y) {
		this.x = x;
		this.y = y;
	}
	public void tick() {
		if(tickInterval == currentInterval) {
			grain = Math.min(grain + grainGrowth, maxGrain);
		}
		currentInterval ++;
		currentInterval = currentInterval % tickInterval;
	}

	public int getX() { return x; }
	public int getY() { return y; }
	public Vector2<Integer> getPosition(){
		return new Vector2<Integer>(getX(), getY());
	}

	public int getGrain() {
		return this.grain;
	}
	public void setInitialGrain(int value) {
		this.grain = value;
	}
	public void setMaxGrain(int value) {
		this.maxGrain = value;
	}
	public int getMaxGrain() {
		return this.maxGrain;
	}
	public void harvestGrain(int value) {
		this.grain -= value;
	}


}
