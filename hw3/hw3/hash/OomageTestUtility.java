package hw3.hash;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OomageTestUtility {
    public static boolean haveNiceHashCodeSpread(List<Oomage> oomages, int M) {
        /* TODO:
         * Write a utility function that returns true if the given oomages
         * have hashCodes that would distribute them fairly evenly across
         * M buckets. To do this, convert each oomage's hashcode in the
         * same way as in the visualizer, i.e. (& 0x7FFFFFFF) % M.
         * and ensure that no bucket has fewer than N / 50
         * Oomages and no bucket has more than N / 2.5 Oomages.
         */
        // a hashmap mapping the bucketNum and number of oomages per bucket
        Map<Integer, Integer> numPerBucket = new HashMap<>();
        double LOWERBOUND = oomages.size() / (50 * 1.0);
        double UPPERBOUND = oomages.size() / 2.5;
        for (Oomage oomage : oomages) {
            int bucketNum = (oomage.hashCode() & 0x7FFFFFFF) % M;
            if (numPerBucket.containsKey(bucketNum)) { // if the bucketNum has already existed in the map
                numPerBucket.replace(bucketNum, numPerBucket.get(bucketNum) + 1);
            } else {
                numPerBucket.put(bucketNum, 1);
            }
        }
        Collection<Integer> allNum = numPerBucket.values();
        for (int num : allNum) {
            if (num < LOWERBOUND || num > UPPERBOUND) {
                return false;
            }
        }
        return true;
    }
}
