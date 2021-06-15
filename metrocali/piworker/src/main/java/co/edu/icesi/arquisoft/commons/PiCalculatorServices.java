package co.edu.icesi.arquisoft.commons;

import org.osoa.sca.annotations.Service;

@Service
public interface PiCalculatorServices {
	
	public void subscribe();
	
	public Workload getWorkload();
	
	public void pushResult(Result result);

}
