package co.edu.icesi.arquisoft.core;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class CSVManager {

	private String configurationName;
	private long pointsToGenerate;
	private int seed;
	private int nodesToUse;

	public CSVManager() {
	}

	public String getConfigurationName() {
		return configurationName;
	}

	public void setConfigurationName(String configurationName) {
		this.configurationName = configurationName;
	}

	public long getPointsToGenerate() {
		return pointsToGenerate;
	}

	public void setPointsToGenerate(long pointsToGenerate) {
		this.pointsToGenerate = pointsToGenerate;
	}

	public int getSeed() {
		return seed;
	}

	public void setSeed(int seed) {
		this.seed = seed;
	}

	public int getNodesToUse() {
		return nodesToUse;
	}

	public void setNodesToUse(int nodesToUse) {
		this.nodesToUse = nodesToUse;
	}

	protected boolean loadCSV() {
		boolean loaded = false;

		String line = "";
		String splitBy = ",";
		try {
			BufferedReader br = new BufferedReader(new FileReader("CSVDemo.csv"));
			String[] query = new String[3];
			while ((line = br.readLine()) != null) {
				query = line.split(splitBy);

				// The CSV reading order is:
				/*
				 * 0. configuration name 1. number of random points to consider 2. seed of the
				 * random generator to use 3. number of processing nodes to use
				 */

				// Set configuration name
				setConfigurationName(query[0]);

				// Set points to generate
				long points = Long.parseLong(query[1]);
				setPointsToGenerate(points);

				// Set seed
				int seedR = Integer.parseInt(query[2]);
				setSeed(seedR);

				// Set nodes to use
				int nNodes = Integer.parseInt(query[3]);
				setNodesToUse(nNodes);

				loaded = true;
			}
			br.close();
		} catch (IOException e) {
			System.out.println("The file could not be loaded");
		}
		return loaded;
	}
}