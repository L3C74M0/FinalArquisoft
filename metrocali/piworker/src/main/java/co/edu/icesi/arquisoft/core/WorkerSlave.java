package co.edu.icesi.arquisoft.core;

import java.util.Random;

import org.osoa.sca.annotations.Property;
import org.osoa.sca.annotations.Reference;

import co.edu.icesi.arquisoft.commons.PiCalculatorServices;
import co.edu.icesi.arquisoft.commons.Result;
import co.edu.icesi.arquisoft.commons.Worker;
import co.edu.icesi.arquisoft.commons.Workload;

public class WorkerSlave implements Runnable {

	@Property
	private int randomSeed;
	
	@Reference
	private PiCalculatorServices piCalculatorServices;
	
	public Result calculateWorkload(Workload workload) {
		
		Random random = new Random(randomSeed);
		int totalPoints = workload.getPointToGenerate();
		
		Result result = new Result();
		result.setTotalPoints(totalPoints);
		
		for (int i = 0; i < totalPoints; i++) {
			
			double x = random.nextDouble();
			double y = random.nextDouble();
			
			double d = x*x + y*y;
			boolean inCircle = d <= 1;
			
			if(inCircle)
				result.addCirclePoint();
		}
		
		return result;
		
	}

	@Override
	public void run() {
		
		Worker worker = new Worker();
		worker.setSeed(randomSeed);
		
		piCalculatorServices.subscribe();
		
		while(true) {
			
			Workload workload = piCalculatorServices.getWorkload();
			
			if(workload != null) {
				System.out.println("Procesando");
				Result result = calculateWorkload(workload);
				piCalculatorServices.pushResult(result);
			}
			
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
		}
		
	}
	
}
