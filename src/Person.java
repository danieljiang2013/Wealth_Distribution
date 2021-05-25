import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

public class Person {
	private int age;
	private int wealth;
	private int lifeExpectancy;
	private int metabolism;
	private int vision = Configuration.maxVision;
	private Cell location;
	private Grid grid;
	
	public Person(Grid grid, Cell cell) {
		this.grid = grid;
		this.location = cell;
		rebirth();
		this.age = randomize(this.lifeExpectancy);
	}

	public void move_eat_die() {
	
		var direction = determineDirection();
		

		move(direction);
		
		//eat
		wealth -= metabolism;
		
		age++;
		
		if (wealth < 0 || age >= lifeExpectancy) {
			rebirth();
		}

		
	}

	// Returns number of persons that have the same cell location as this object
	public int numPeopleOnSamePatch(){
		var persons = grid.getPopulation();
		var ret = 0;
		for (int x = 0; x < persons.size(); x++) {
			var position = persons.get(x).location;
			if (position == this.location){
				ret ++;
			}
		}
		return ret;
	}
	// Grain at a patch gets divided equally amongst all people at that patch
	public void harvest() {
		var num = numPeopleOnSamePatch();
		var grainChanged = location.getGrain();
		if (num != 0){
			grainChanged /= num;
		}
		this.wealth += grainChanged; // increase
	}

	private void move(Cell newLocation) {
		this.location = newLocation;
	}

	private Vector2<Integer> determineDirection() {
		var directions = new ArrayList<Vector2<Integer>>();
		directions.add(new Vector2<Integer>(1, 0));
		directions.add(new Vector2<Integer>(-1, 0));
		directions.add(new Vector2<Integer>(0, 1));
		directions.add(new Vector2<Integer>(0, -1));

		int bestTotal = -1;
		Vector2<Integer> bestDirection = new Vector2<Integer>(0, 0);

		for (Vector2<Integer> direction : directions) {
			int totalGrain = 0;
			for (int i = 1; i <= vision; i++) {
				var newPos = location.getPosition().add(direction.multiply(i));
				totalGrain += grid.cellAt(newPos.x, newPos.y).getGrain();
			}
			if (totalGrain > bestTotal) {
				bestDirection = direction;
			}
		}

		return bestDirection;

	}

	private void move(Vector2<Integer> direction) {
		var newPosition = location.getPosition().add(direction);
		var newCell = grid.cellAt(newPosition.x, newPosition.y);
		move(newCell);
	}
	private int randomize(int limit){
		var random = (int) (Math.random() * (limit));
		if (random >= 0 && random <= limit){
			return random;
		}
		return 0;
	}
	private void rebirth() {
		this.lifeExpectancy = Configuration.lifeExpectancyMin
				+ randomize(Configuration.lifeExpectancyMax - Configuration.lifeExpectancyMin + 1);
		this.metabolism = 1 + randomize(Configuration.metabolismMax);
		this.wealth = this.metabolism + randomize(50);
		this.vision = 1 + randomize(Configuration.maxVision);
		this.age = 0;
	}

	// 0 is low class
	// 2 is high class
	public int classRank() {
		var mostWealth = grid.getMostWealth();
		if (wealth <= mostWealth / 3.0f) {
			return 0;
		}
		if (wealth <= mostWealth * (2.0f / 3.0f)) {
			return 1;
		}
		return 2;
	}

	public int getWealth() {
		return wealth;
	}

	public Cell getLocation() {
		return location;
	}

	public void setWealth(int wealth) {
		this.wealth = wealth;		
	}
}
