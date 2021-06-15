package co.edu.icesi.arquisoft.commons;

import java.io.Serializable;

public class Workload implements Serializable {
	
	private static final long serialVersionUID = 7832074124419417832L;
	
	private int pointToGenerate;

	public int getPointToGenerate() {
		return pointToGenerate;
	}

	public void setPointToGenerate(int pointToGenerate) {
		this.pointToGenerate = pointToGenerate;
	}
	
}
