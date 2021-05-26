import java.util.ArrayList;
import java.util.List;

public class IncomeReport {
	private int[][] incomes = new int[3][50];
	
	public IncomeReport() {
		
	}
	
	public void update(Grid grid) {
		for (Person p : grid.getPopulation()) {
			incomes[p.classRank()][p.getIncome()] ++;
		}
	}

	public List<LineGraph<Integer>> getLineGraphs() {
		var classes = new ArrayList<LineGraph<Integer>>();
		classes.add(new LineGraph<Integer>("Poor"));
		classes.add(new LineGraph<Integer>("Medium"));
		classes.add(new LineGraph<Integer>("Rich"));
		
		for (int i = 0; i < incomes.length; i++) {
			for (int j = 0; j < incomes[i].length; j++) {
				classes.get(i).add(incomes[i][j]);
			}
		}
		
		return classes;		
	}
}
