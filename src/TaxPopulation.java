import java.util.ArrayList;
import java.util.List;

public class TaxPopulation {
	private List<Person> population;
	
	public TaxPopulation(List<Person> population) {
		this.population = population;
	}
	
	void tick() {
		var poorPeople = getPoorPeople();
		
		if(poorPeople.size() == 0)
			return;
		
		int taxedAmount = taxRichPeople();		
		
		giveWealthToPoor(taxedAmount, poorPeople);
	}
	
	public List<Person> getPoorPeople() {
		List<Person> poorPeople = new ArrayList<Person>();
		
		for (Person person : population) {
			if(person.classRank() == 0) {
				poorPeople.add(person);
			}
		}
		
		return poorPeople;
	}
	
	public int taxRichPeople() {
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
	
	public void giveWealthToPoor(int taxedAmount, List<Person> poorPeople) {
		
		int numPoor = poorPeople.size();
		int amountPerPoor = taxedAmount / numPoor;
		int remaining = taxedAmount - amountPerPoor * numPoor;
		
		for (Person person : poorPeople) {
			person.setWealth(person.getWealth() + amountPerPoor);
		}
		
		int randomIndex = Math.min((int)(Math.random() * numPoor), numPoor - 1);
		
		var randomPoorPerson = poorPeople.get(randomIndex);
		
		randomPoorPerson.setWealth(randomPoorPerson.getWealth() + remaining);
	}
}
