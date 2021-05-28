import java.util.ArrayList;
import java.util.List;

/**]
 * Handles taxing the population
 *
 */
public class TaxPopulation {
	private List<Person> population;
	
	/*
	 * Creates the tax calculator effecting a population
	 */
	public TaxPopulation(List<Person> population) {
		this.population = population;
	}
	
	/**
	 * At each tick, tax is deducted from the wealthy
	 * and then equally distributed to the poor
	 */
	void tick() {
		if(getPoorPeople().size() == 0)
			return;

		var poorPeople = getPoorPeople();
		int taxedAmount = taxRichPeople();		
		
		giveWealthToPoor(taxedAmount, poorPeople);
	}
	
	/**
	 * Gets all the poor people
	 */
	private List<Person> getPoorPeople() {
		List<Person> poorPeople = new ArrayList<Person>();
		
		for (Person person : population) {
			if(person.classRank() == 0) {
				poorPeople.add(person);
			}
		}
		
		return poorPeople;
	}
	
	/**
	 * Taxes the rich population via income or wealth 
	 * depending upon Configuration
	 */
	private int taxRichPeople() {
		int taxedAmount = 0;
		for (Person person : population) {
			if(person.classRank() >= 1) {
				int taxeable = 0;
				if(Configuration.taxSystem == Configuration.TaxSystem.Income) {
					taxeable = person.getIncome();
				}
				
				if(Configuration.taxSystem == Configuration.TaxSystem.Wealth) {
					taxeable = person.getWealth();
				}
				
				int amountToTax = (int) (taxeable * Configuration.tax);
				
				person.setWealth(person.getWealth() - amountToTax);
				taxedAmount += amountToTax;
			}
		}
		return taxedAmount;
	}
	
	/**
	 * Gives an amount of tax to a set of people
	 */
	private void giveWealthToPoor(int taxedAmount, List<Person> people) {
		
		int numPoor = people.size();
		int amountPerPoor = taxedAmount / numPoor;
		int remaining = taxedAmount - amountPerPoor * numPoor;
		
		for (Person person : people) {
			person.setWealth(person.getWealth() + amountPerPoor);
		}
		
		int randomIndex = Math.min((int)(Math.random() * numPoor), numPoor - 1);
		
		var randomPoorPerson = people.get(randomIndex);
		
		randomPoorPerson.setWealth(randomPoorPerson.getWealth() + remaining);
	}
}
