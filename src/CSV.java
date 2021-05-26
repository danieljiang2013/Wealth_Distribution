import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.nio.file.Paths;

public class CSV {
	String content = "";
	String name = "";
	

	
	public <T extends Number> CSV(LineGraph<T> lineGraph) {
		this.name = lineGraph.getName();
		content += lineGraph.getName() + "\n";
		
		for (Object value : lineGraph.getCounts()) {
			content += value.toString() + "\n";
		}		
	}
	
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
	
	public void saveTo(String path) {
	    BufferedWriter writer = null;
		try {
			String saveName = Paths.get("../").toAbsolutePath().normalize().toString() + "/" + name;
			writer = new BufferedWriter(new FileWriter( saveName + path + ".csv"));
			writer.write(content);
		} catch (IOException e) {
			System.out.println(e);
		} finally {
			try {
				if(writer != null)
					writer.close();
			} catch (IOException e) {
				System.out.println(e);
			}
		}
	}
}
