
public class Cell {
	private int maxGrain;
	private int grain;
	private int x;
	private int y;
	private int tickInterval = Configuration.gainGrowthInterval;
	private int currentInterval = 0;
	private int grainGrowth = Configuration.numGrainGrown;
	
	public Cell(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public void tick() {
		if(tickInterval == currentInterval) {
			grain = Math.min(grain+grainGrowth, maxGrain);
		}
		currentInterval ++;
		currentInterval = currentInterval % tickInterval;
	}
	
	public int getX() { return x; }
	public int getY() { return y; }
	public Vector2<Integer> getPoistion(){ 
		return new Vector2<Integer>(getX(), getY());
	}

	public int getGrain() {
		return grain;
	}
}
