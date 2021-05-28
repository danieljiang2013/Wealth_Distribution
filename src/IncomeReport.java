import java.util.ArrayList;
import java.util.List;
/**
 * This class calculates the income of people in each of the 3
 * wealth classes.
 */
public class IncomeReport  implements IStatistic  {
	private static final int numClasses = 3;
	private int[][] incomes = new int[numClasses][Configuration.gridSize - 1];
	
	public IncomeReport() {}
	/**
	 * On each tick, this method is called and it calculates the
	 * incomes for each class
	 * @param grid for which the class incomes are calculated
	 */
	public void update(Grid grid) {
		for (Person p : grid.getPopulation()) {
			incomes[p.classRank()][p.getIncome()] ++;
		}
	}

	public List<LineGraph<Integer>> getLineGraphs() {
		var classes = new ArrayList<LineGraph<Integer>>();
		classes.add(new LineGraph<>("Poor"));
		classes.add(new LineGraph<>("Medium"));
		classes.add(new LineGraph<>("Rich"));
		
		for (int i = 0; i < incomes.length; i++) {
			for (int j = 0; j < incomes[i].length; j++) {
				classes.get(i).add(incomes[i][j]);
			}
		}
		
		return classes;		
	}
}
