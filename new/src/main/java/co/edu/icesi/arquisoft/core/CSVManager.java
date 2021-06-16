package co.edu.icesi.arquisoft.core;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class CSVManager {

	private ArrayList<String[]> inputs = new ArrayList<String[]>();

	public ArrayList<String[]> getInputs() {
		return inputs;
	}

	public CSVManager() {
	}

	public boolean loadCSV() {
		boolean loaded = false;

		String line = "";
		String splitBy = ",";
		try {
			// TODO Toca cambiar la ruta
			BufferedReader br = new BufferedReader(new FileReader("CSVDemo.csv"));
			String[] query = new String[3];

			while ((line = br.readLine()) != null) {
				query = line.split(splitBy);

				// The CSV reading order is:
				/*
				 * 0. configuration name 
				 * 1. number of random points to consider 
				 * 2. seed of the random generator to use 
				 * 3. number of processing nodes to use
				 */

				inputs.add(query);

				loaded = true;
			}
			br.close();
		} catch (IOException e) {
			System.out.println("The file could not be loaded");
		}
		return loaded;
	}

	public void saveAsCSV(ArrayList<String[]> output) throws FileNotFoundException {
		String line = "";
		/*
		 * 1. Name
		 * 2. PI
		 */
		
		for (int i = 0; i < output.size(); i++) {
			line += output.get(i)[0] + "," + output.get(i)[1];
		}

		PrintWriter pw = new PrintWriter(new File("output.csv"));
		pw.print(line);
		pw.close();
	}
}