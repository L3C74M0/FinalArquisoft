package sort;

import org.osoa.sca.annotations.Service;


@Service
public interface Subject {
    void attachSorter(String uri);
    void detachSorter(String uri);    
    void attachMerger(String uri);
    void detachMerger(String uri); 
}
