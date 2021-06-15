package sort;
/**
 * WrapRunnable
 */
public class WrapRunnable implements Runnable {

    private Object service;
    private Comparable[][] params;
    private Comparable[] result;

    public WrapRunnable(Object service,Comparable[] ... params){
        this.service=service;
        this.params=params;
    }

    @Override
    public void run() {
        try {
            if(service instanceof Sorter){
                result=((Sorter)service).sort(params[0]);

            }else{
                result=((Merger)service).merge(params[0],params[1]);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * @return the result
     */
    public Comparable[] getResult() {
        return result;
    }
}