package co.edu.icesi.arquisoft.commons;

import java.io.Serializable;

public class Result implements Serializable {
	
	private static final long serialVersionUID = 3039854036267011151L;

	private int circlePoints;
	
	private int totalPoints;
	
	public int getCirclePoints() {
		return circlePoints;
	}

	public void setCirclePoints(int circlePoints) {
		this.circlePoints = circlePoints;
	}

	public int getTotalPoints() {
		return totalPoints;
	}

	public void setTotalPoints(int totalPoints) {
		this.totalPoints = totalPoints;
	}
	
	public void addCirclePoint() {
		this.circlePoints += 1;
	}

}
