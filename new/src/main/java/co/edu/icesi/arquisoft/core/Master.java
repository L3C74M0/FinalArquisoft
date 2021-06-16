package co.edu.icesi.arquisoft.core;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import co.edu.icesi.arquisoft.commons.PiCalculatorServices;
import co.edu.icesi.arquisoft.commons.Result;
import co.edu.icesi.arquisoft.commons.Workload;
import co.edu.icesi.arquisoft.ui.uiiPi;
import org.osoa.sca.annotations.Scope;

@Scope("COMPOSITE")
public class Master implements PiCalculatorServices {

	// Attributes -----------------------
	private int maxWorkers;

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
	
	private uiiPi ui;
	protected CSVManager csv;

	public Master() {
		
		try {
            ui = new uiiPi();
            ui.setLocationRelativeTo(null);
            ui.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            ui.setVisible(true);
	    } catch (Exception e) {
            e.printStackTrace();
	    }
        eventos();
	}

	public void setTotalPoints(int totalPoints) {
		this.totalPoints = totalPoints;
	}

	public void setMaxWorkers(int maxWorkers) {
		this.maxWorkers = maxWorkers;
	}

	/*
	 * Calcula la cantidad de puntos que le corresponden a cada nodo de
	 * procesamiento
	 */
	public void generateChuncks() {
		startTime = System.currentTimeMillis();
		int chuncksParts = (int) totalPoints / totalWorkers.size();
		int residue = (totalPoints % totalWorkers.size());

		//System.out.println("Partes: " + chuncksParts);
		//System.out.println("Residuo: " + residue);

		if (residue != 0) {
			int missingPart = residue * totalWorkers.size();
			Workload workload = new Workload();
			workload.setPointToGenerate(missingPart);
		}

		for (int i = 0; i < totalWorkers.size(); i++) {
			System.out.println("Generando workload");
			Workload workload = new Workload();
			workload.setPointToGenerate(chuncksParts);
			chuncks.add(workload);
		}
	}

	public void calculatePiByMonteCarlo() {
		System.out.println(
				"Calcular: Circulo: " + result.getCirclePoints() + " -- " + "Total: " + result.getTotalPoints());
		double pi = ((double) result.getCirclePoints() / result.getTotalPoints()) * 4;
		endTime = System.currentTimeMillis();
		System.out.println("Resultado: " + pi + " - Tiempo: " + (endTime - startTime) / 1000);
		// escribir resultado
	}

	@Override
	public void subscribe() {// Worker woker) {
		totalWorkers.add("worker");
	}

	@Override
	public Workload getWorkload() {
		synchronized (lockGetChunck) {
			Workload workload = chuncks.poll();
			return workload;
		}
	}

	@Override
	public void pushResult(Result workerResult) {
		synchronized (lockPushResult) {

			result.setTotalPoints(result.getTotalPoints() + workerResult.getTotalPoints());

			result.setCirclePoints(result.getCirclePoints() + workerResult.getCirclePoints());

			System.out.println("Entregar: " + result.getTotalPoints() + " -- Circulos: " + result.getCirclePoints());

			if (result.getTotalPoints() == totalPoints) {
				calculatePiByMonteCarlo();
			}

		}
	}
	
	protected void eventos() {
		ui.getCalculate().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!ui.getSeed().getText().isEmpty() && !ui.getPoints().getText().isEmpty()
						&& !ui.getNodes().getText().isEmpty()) {

					cleanState();
					
					//seed = Long.parseLong(ui.getSeed().getText());
					totalPoints = Integer.parseInt(ui.getPoints().getText());
					maxWorkers = Integer.parseInt(ui.getNodes().getText());
					
					generateChuncks();
					
					System.out.println("\tGenerado");
					
					// double pi = c.calcularPi(seed, points, servers);
					ui.getResult().setText("Pi es = ");
				}
			}
		});
	}
	
	protected void cleanState() {
		totalPoints = 0;
		result = new Result();
	}
}
