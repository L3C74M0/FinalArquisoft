package co.edu.icesi.arquisoft.core;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class CSVManager {
	
	public CSVManager() {
		
	}
	
	
	
	
	protected String[] loadCSV() {
		String[] query = new String[5];
		
		String line = "";
		String splitBy = ",";
		try {
			// parsing a CSV file into BufferedReader class constructor
			BufferedReader br = new BufferedReader(new FileReader("CSVDemo.csv"));
			while ((line = br.readLine()) != null) // returns a Boolean value
			{
				String[] employee = line.split(splitBy); // use comma as separator
				System.out.println("Employee [First Name=" + employee[0] + ", Last Name=" + employee[1]
						+ ", Designation=" + employee[2] + ", Contact=" + employee[3] + ", Salary= " + employee[4]
						+ ", City= " + employee[5] + "]");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return query;
		
	}

}
