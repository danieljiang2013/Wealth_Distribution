import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.nio.file.Paths;

/**
	This class implements the functionality for writing output to csv files.
	It is able to write either a single `LineGraph` object's values to a file, or
	aggregate the values from a list of `LineGraph` objects to a c single file
 */
public class CSV {
	String content = "";
	String name;

	/**
	 * For a single `LineGraph` object, the constructor concatenates all values
	 * to a a single variable.
	*/
	public <T extends Number> CSV(LineGraph<T> lineGraph) {
		this.name = lineGraph.getName();
		content += lineGraph.getName() + "\n";
		for (Object value : lineGraph.getCounts()) {
			content += value.toString() + "\n";
		}
	}
	/**
	 * For a list of `LineGraph` objects, the constructor concatenates the values
	 * for each element one by one to a single variable.
	 */
	public <T extends Number> CSV(List<LineGraph<T>> lineGraphs, String name) {
		this.name = name;
		int length = 0;
		for (LineGraph<T> lineGraph : lineGraphs) {
			content += lineGraph.getName() + ",";
			length = lineGraph.getCounts().size();
		}
		content += "\n";
		for(int i = 0; i < length; i++) {
			for(int j = 0; j < lineGraphs.size(); j++) {
				content += lineGraphs.get(j).getCounts().get(i);
				if(j -1 != lineGraphs.size())
					content += ",";
			}
			content += "\n";
		}
	}
	/**
	 * This method saves the contents of this object to the file name provided
	 * as the param to this method call.
	 * @param path to the file
	 */
	public void saveTo(String path) {
	    BufferedWriter writer = null;
		String saveName = "";
		try {
			saveName = Paths.get("./").toAbsolutePath().normalize().toString()
					+ "/" + name + path + ".csv";
			writer = new BufferedWriter(new FileWriter(saveName));
			writer.write(content);
		} catch (IOException e) {
			System.out.println(e);
		} finally {
			try {
				if(writer != null) {
					writer.close();
					System.out.println(saveName);
				}
			} catch (IOException e) {
				System.out.println(e);
			}
		}
	}
}
