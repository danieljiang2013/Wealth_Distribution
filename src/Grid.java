import java.util.ArrayList;
import java.util.List;

public class Grid {

	private List<Person> population;
	private List<List<Cell>> cells;
	private int size;
	private int mostWealth;
	private int totalWealth;

	Grid(int size, int numPeople) {
		this.size = size;
		cells = new ArrayList<List<Cell>>();
		for (int x = 0; x < size; x++) {
			var column = new ArrayList<Cell>();
			for (int y = 0; y < size; y++) {
				column.add(new Cell(x, y));
			}
			cells.add(column);
		}

		population = new ArrayList<Person>();
		for (int i = 0; i < numPeople; i++) {
			population.add(new Person(this));
		}
	}

	public Cell cellAt(int x, int y) {
		return cells.get(x).get(y);
	}

	public void tick() {
		calcMostWealth();
		calcTotalWealth();

		for (int x = 0; x < size; x++) {
			var column = new ArrayList<Cell>();
			for (int y = 0; y < size; y++) {
				cells.get(x).get(y).tick();
			}
		}

		for (int i = 0; i < population.size(); i++) {
			population.get(i).tick();
		}
	}

	public List<Person> getPopulation() {
		return population;
	}

	public void calcMostWealth() {
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

	public int getNumPeopleAt(int x, int y) {
		int count = 0;

		for (int i = 0; i < population.size(); i++) {
			if (population.get(i).getLocation().getX() == x && population.get(i).getLocation().getY() == y) {
				count++;
			}
		}
		return count;
	}
}
