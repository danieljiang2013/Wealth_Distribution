import java.util.ArrayList;
import java.util.List;

public class ClassHistogram {
	private String name = "Class histogram";
	private List<LineGraph<Integer>> classHistogram = new ArrayList<LineGraph<Integer>>();

	
	public void update(Grid grid) {
		int counts[] = {0,0,0};
		for (Person p : grid.getPopulation()) {
			counts[p.classRank()] ++;
		} 
		var t = new LineGraph<Integer>("");
		for (int i = 0; i < counts.length; i++) {
			t.add(counts[i]);
		}
		classHistogram.add(t);
	
	}

	public List<LineGraph<Integer>> getHistogram() {
		return classHistogram;
	}

}
