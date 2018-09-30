package sm.nse.util;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.opencsv.CSVReader;


public class CsvReader {
	String contextId;

	public CsvReader() {
	
	}

	public List<String[]> makeFileList(String csvFile) {
		List<String[]> data = new ArrayList<String[]>();

	try {
			CSVReader reader = new CSVReader(new FileReader(csvFile), ',');
			String[] record = null;
			int i = 0;
			while ((record = reader.readNext()) != null) {
				i = i + 1;

				/*
				 * for (String col : record) {
				 * 
				 * System.out.print(col + "=="); }
				 */
				data.add(record);

			}

			reader.close();

			/*
			 * ============================================== try (BufferedReader br = new
			 * BufferedReader(new FileReader(csvFile))) {
			 * 
			 * while ((line = br.readLine()) != null) {
			 * 
			 * // use comma as separator String[] row1 = line.split(cvsSplitBy);
			 * data.add(row1); }
			 */
		} catch (IOException e) {
			e.printStackTrace();
		}
		return data;
	}
}