package org.ow2.frascati.examples.helloworld.annotated;

import org.osoa.sca.annotations.Service;

@Service
public interface PrintService {
    void print(String msg);

    public void subscribe();
	
	public Workload getWorkload();
	
	public void pushResult(Result result);
}
