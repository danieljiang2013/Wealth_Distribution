import java.util.ArrayList;
import java.util.List;

public class ClassHistogram {
	private String name = "Class histogram";
	private List<LineGraph<Integer>> classHistogram = new ArrayList<LineGraph<Integer>>();

	
	public void update(Grid grid) {
		
		
	}

	public List<LineGraph<Integer>> getHistogram() {
		return classHistogram;
	}

}
