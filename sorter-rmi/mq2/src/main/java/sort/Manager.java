package sort;

import org.osoa.sca.annotations.Service;
import org.osoa.sca.annotations.Reference;


@Service(Runnable.class)
public class Manager implements Runnable{

    @Reference
    private Runnable merger;
    @Reference
    private Runnable sorter;

    @Override
    public void run() {
        sorter.run();
        merger.run();        
    }
    
}
