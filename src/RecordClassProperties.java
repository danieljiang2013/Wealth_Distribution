import java.util.ArrayList;
import java.util.List;

public class RecordClassProperties  {
	private ArrayList<ArrayList<LineGraph<Integer>>> classes = new ArrayList<ArrayList<LineGraph<Integer>>>();
	
	public RecordClassProperties() {
		classes = new ArrayList<ArrayList<LineGraph<Integer>>>();
		classes.add(propertyGraphs("Poor"));
		classes.add(propertyGraphs("Medium"));
		classes.add(propertyGraphs("Rich"));
	}
	
	private ArrayList<LineGraph<Integer>> propertyGraphs(String prefix){
		var properties = new ArrayList<LineGraph<Integer>>();
		properties.add(new LineGraph<Integer>(prefix + " Age"));
		properties.add(new LineGraph<Integer>(prefix + " LifeExptancy"));
		properties.add(new LineGraph<Integer>(prefix + " Metabolism"));
		properties.add(new LineGraph<Integer>(prefix + " Vision"));		
		return properties;
	}
	
	public void update(Grid grid) {
		int[][] classProperties = new int[3][4];
		int[] classCounts = new int[3];
		for (Person p : grid.getPopulation()) {
			int rank = p.classRank();
			classProperties[rank][0] += p.getAge();
			classProperties[rank][1] += p.getLifeExptancy();
			classProperties[rank][2] += p.getMetabolism();
			classProperties[rank][3] += p.getVision();
			classCounts[rank] ++;
		}
		
		for (int i = 0; i < classProperties.length; i++) {
			for (int j = 0; j < classProperties[i].length; j++) {
				if(classCounts[i] != 0) {
					classProperties[i][j] /= classCounts[i];
					classes.get(i).get(j).add(classProperties[i][j]);
				}else {
					classes.get(i).get(j).add(null);
				}
				
			}
		}
	}

	public List<LineGraph<Integer>> getLineGraphs() {
		var singleClasses = new ArrayList<LineGraph<Integer>>();
		for (int i = 0; i < classes.size(); i++) {
			for (int j = 0; j < classes.get(i).size(); j++) {
				singleClasses.add(classes.get(i).get(j));
			}
		}		
		return singleClasses;		
	}
}
