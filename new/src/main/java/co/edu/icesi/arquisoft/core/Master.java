package co.edu.icesi.arquisoft.core;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

import co.edu.icesi.arquisoft.commons.PiCalculatorServices;
import co.edu.icesi.arquisoft.commons.Result;
import co.edu.icesi.arquisoft.commons.Workload;

import org.osoa.sca.annotations.Scope;

@Scope("COMPOSITE")
public class Master implements PiCalculatorServices {
	
	// Attributes -----------------------
	private int maxWorkers = 2;
	
	private int totalPoints;
	private List<String> totalWorkers = new ArrayList<String>();
	
	private Result result = new Result();
	
	private double startTime;
	private double endTime;
	// ----------------------------------
	
	// Locks ----------------------------
	private Object lockGetChunck = new Object();
	private Object lockPushResult = new Object();
	// ----------------------------------
	
	private Queue<Workload> chuncks = new LinkedBlockingQueue<Workload>();
	
	public Master() {
		//Llamar la interfaz
		this.totalPoints = 1000000000;
		
	}
	
	public void setTotalPoints(int totalPoints) {
		this.totalPoints = totalPoints;
	}
	
	public void setMaxWorkers(int maxWorkers) {
		this.maxWorkers = maxWorkers;
	}
	
	public void generateChuncks() {
		int chuncksParts = (int) totalPoints / totalWorkers.size();
		int residue = (totalPoints % totalWorkers.size());
		
		System.out.println("Partes: " + chuncksParts);
		System.out.println("Residuo: " + residue);
		
		if(residue != 0) {
			int missingPart = residue * totalWorkers.size();
			Workload workload = new Workload();
			workload.setPointToGenerate(missingPart);
		}
		
		for(int i=0; i < totalWorkers.size(); i++) {
			System.out.println("Generando workload");
			Workload workload = new Workload();
			workload.setPointToGenerate(chuncksParts);
			chuncks.add(workload);
		}
		
	}
	
	public void calculatePiByMonteCarlo() {
		System.out.println("Calcular: Circulo: " + result.getCirclePoints() + " -- " + "Total: " + result.getTotalPoints());
		double pi = ((double) result.getCirclePoints() / result.getTotalPoints()) * 4;
		endTime = System.currentTimeMillis();
		System.out.println("Resultado: " + pi + " - Tiempo: " + (endTime - startTime)/1000);
		// escribir resultado
	}
	
	@Override
	public void subscribe() {//Worker woker) {
		totalWorkers.add("worker");
		if(totalWorkers.size() == maxWorkers) {
			System.out.println("Inicia...");
			startTime = System.currentTimeMillis();
			generateChuncks();
		}
		System.out.println("Entra un worker... " + totalWorkers.size());
	}
	
	@Override
	public Workload getWorkload() {
		synchronized (lockGetChunck) {
			Workload workload = chuncks.poll();
			System.out.println("Entregar workload: " + workload.getPointToGenerate());
			return workload;
		}
	}
	
	@Override
	public void pushResult(Result workerResult) {
		synchronized (lockPushResult) {
			
			result.setTotalPoints(
					result.getTotalPoints() + workerResult.getTotalPoints()
			);
			
			result.setCirclePoints(
					result.getCirclePoints() + workerResult.getCirclePoints()
			);
			
			System.out.println("Entregar: " + result.getTotalPoints() +
					" -- Circulos: " + result.getCirclePoints());
			
			if(result.getTotalPoints() == totalPoints) {
				calculatePiByMonteCarlo();
			}
			
		}
	}
	
}
