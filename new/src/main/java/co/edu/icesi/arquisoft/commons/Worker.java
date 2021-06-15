package co.edu.icesi.arquisoft.commons;

import java.io.Serializable;

public class Worker implements Serializable {
	
	private static final long serialVersionUID = -481119804409504955L;

	private int seed;
	
	private String ip;

	public int getSeed() {
		return seed;
	}

	public void setSeed(int seed) {
		this.seed = seed;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

}
