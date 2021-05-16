import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CSV {
	String content = "";
	String name = "";

	public <T> CSV(LineGraph<T> lineGraph) {
		this.name = lineGraph.getName();
		content += lineGraph.getName() + "\n";

		for (Object value : lineGraph.getCounts()) {
			content += value.toString() + "\n";
		}
	}

	public <T> CSV(List<LineGraph<T>> lineGraphs, String name) {
		this.name = name;
		int length = 0;
		for (LineGraph<T> lineGraph : lineGraphs) {
			content += lineGraph.getName() + ",";
			length = lineGraph.getCounts().size();
		}
		content += "\n";

		for (int i = 0; i < length; i++) {
			for (int j = 0; j < lineGraphs.size(); j++) {
				content += lineGraphs.get(j).getCounts().get(i);
				if (j - 1 != lineGraphs.size())
					content += ",";
			}
			content += "/n";
		}

	}

	public void saveTo(String path) {
		BufferedWriter writer = null;
		try {
			writer = new BufferedWriter(new FileWriter(path + name + ".csv"));
			writer.write(content);

		} catch (IOException e) {
		} finally {
			try {
				if (writer != null)
					writer.close();
			} catch (IOException e) {
			}
		}

	}
}
