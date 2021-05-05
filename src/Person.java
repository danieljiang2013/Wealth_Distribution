
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
	}
	
	public void tick() {
		
	}
	
	private void move(Cell newLocation) {
		this.location = newLocation;
	}
	
	private Vector2<Integer> determineDirection(){
		return null;
	}
	
	private void move(Vector2<Integer> direction) {
		
	}
}
