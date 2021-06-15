package sort;

import org.osoa.sca.annotations.Service;


@Service
public interface WorkersService {

    Sorter getSorter();
    Merger getMerger();
    boolean isSorter();
    boolean isMerger();
    int getSorters();
    int getMergers();
}
