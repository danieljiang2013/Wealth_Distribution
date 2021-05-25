import java.util.ArrayList;
import java.util.List;

public class Grid {
	
	private List<Person> population;
	private Cells cells;
	private int size;
	private int mostWealth;
	private int totalWealth;
	TaxPopulation taxPopulation;
	
	Grid(int size, int numPeople){
		this.size = size;

		cells = new Cells(size);
		population = new ArrayList<Person>();
		for(int i = 0; i < numPeople; i ++) {
			population.add(new Person(this, cellAt(randomize(), randomize())));
		}
		
		taxPopulation = new TaxPopulation(population);

	}
	// random number between 0 and size
	private int randomize(){
		var random = (int) (Math.random() * (size));
		if (random >= 0 && random <= size){
			return random;
		}
		return 0;
	}

	public Cell cellAt(int x, int y) {
		int xx = x % size;
		if(xx < 0) {
			xx = size + xx;
		}
		int yy = y % size;
		int tyy = yy;
		if(yy < 0) {
			yy = size + yy;
		}
		return cells.getCellAt(xx,yy);
	}

	public void tick() {
		
		
		for(int i = 0; i < population.size(); i++) {
			population.get(i).harvest();
		}
		
		harvest();
		
		for(int i = 0; i < population.size(); i++) {
			population.get(i).move_eat_die();
		}
		
		cells.tick();
		
		
		
		calcMostWealth();
		calcTotalWealth();
		
		taxPopulation.tick();
//		System.out.println(this.mostWealth);
	}
	
	private void harvest() {
		for (int i = 0; i < population.size(); i++) {
			population.get(i).getLocation().setInitialGrain(0);
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
