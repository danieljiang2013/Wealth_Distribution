import java.util.ArrayList;
import java.util.Iterator;

public class Person {
	private int age;
	private int wealth;
	private int lifeExpectancy;
	private int metabolism;
	private int vision;
	private Cell location;
	private Grid grid;
	
	public Person(Grid grid) {
		this.grid = grid;
		rebirth();
	}
	
	public void tick() {
		harvest();
		
		var direction = determineDirection();
		move(direction);
		
		wealth -= metabolism;
		
		if(wealth <= 0 || age >= lifeExpectancy) {
			rebirth();
		}
		
		age ++;
	}
	
	public void harvest() {
		
	}
	
	private void move(Cell newLocation) {
		this.location = newLocation;
	}
	
	private Vector2<Integer> determineDirection(){
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
				var newPos = location.getPoistion().add(direction.multiply(i));
				totalGrain += grid.cellAt(newPos.x, newPos.y).getGrain();
			}
			if(totalGrain > bestTotal) {
				bestDirection = direction;
			}
		}
		
		return bestDirection;
		
	}
	
	private void move(Vector2<Integer> direction) {
		var newPosition = location.getPoistion().add(direction);
		var newCell = grid.cellAt(newPosition.x, newPosition.y);
		move(newCell);
	}
	
	private void rebirth() {
		
	}
	
	//0 is low class
	//2 is high class
	public int classRank() {
		  var mostWealth = grid.getMostWealth();
		  if (wealth <= mostWealth/3) {
			  return 0;
		  }
		  if (wealth <= mostWealth * 2.0f/3.0f) {
			  return 1;
		  }
		  return 2;
	}

	public int getWealth() {
		return wealth;
	}
}
