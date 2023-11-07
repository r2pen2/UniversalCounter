package counter;

import java.util.Collection;
import java.util.HashMap;

public class UniversalCounter implements IUniversalCounter {

    /**  HashMap containing counts for each "label" string */
    private HashMap<String, Integer> counters;

    /**
     * Create a UniversalCounter without any starting labels.
     */
    public UniversalCounter() {
        // No starting labels, so just init counters
        counters = new HashMap<String, Integer>();
    }

    /**
     * Create a UniversalCounter with specified starting labels.
     * @param startingLabels - a list of strings to use as starting labels
     */
    public UniversalCounter(String... startingLabels) {
        // We're given starting labels, so init hashmap with some keys
        counters = new HashMap<String, Integer>();
        for (String startingLabel : startingLabels) {
            throwIfDuplicateLabel(startingLabel); // Throw an exception if this is a duplicate label
            counters.put(startingLabel, 0);
        }
    }

    /** Throw a CounterException if the given label is not found in the counters HashMap */
    private void throwIfLabelNotFound(String label) throws CounterException {
        // Make sure that this label exists in the counter
        if (!counters.keySet().contains(label)) {
            // This key is invalid
            throw new CounterException(String.format("The label '%s' does not exist on this counter.", label), CounterException.ExceptionReason.NO_LABEL);
        }
    }

    /** Throw a CounterException if the given label appears more than once */
    private void throwIfDuplicateLabel(String label) throws CounterException {
        if (counters.containsKey(label)) {
            // This key is already used
            throw new CounterException(String.format("The label '%s' already exists on this counter.", label), CounterException.ExceptionReason.DUPLICATE_LABEL);
        }
    }

    /** Throw a CounterException if the given value is <= 0 */
    private void throwIfNegativeValue(int value) throws CounterException {
        if (value <= 0) {
            throw new CounterException("Increment and Decrement values must be integers greater than or equal to 1", CounterException.ExceptionReason.BAD_VALUE);
        }
    }

    @Override
    public void resetCounters() {
        // Iterate through all key strings in the counter
        for (String label : counters.keySet()) {
            // Put a 0 in this slot
            counters.put(label, 0);
        }
    }

    @Override
    public void resetCounter(String label) {
        throwIfLabelNotFound(label);
        // Place a 0 into this counter's bucket
        counters.put(label, 0);
    }

    @Override
    public void clear() {
        // Set counters to a fresh HashMap
        counters = new HashMap<String, Integer>();
    }

    @Override
    public int getCount(String label) {
        throwIfLabelNotFound(label);
        // This label exists— return the correct count
        return counters.get(label);
    }

    @Override
    public int getTotalCount() {
        // Iterate through all counters and return the total
        int total = 0;
        for (int count : counters.values()) {
            total += count;
        }
        return total;
    }

    @Override
    public Collection<String> getLabels() {
        // Simply return the counters key set
        return counters.keySet();
    }

    @Override
    public void addCounter(String label) {
        throwIfDuplicateLabel(label);
        // Add a 0 to the HashHap at this label
        counters.put(label, 0);
    }

    @Override
    public void removeCounter(String label) {
        throwIfLabelNotFound(label);
        // Delete the key
        counters.remove(label);
    }

    @Override
    public void increment(String label) {
        throwIfLabelNotFound(label);
        // Add 1 to the counter
        counters.put(label, counters.get(label) + 1);
    }

    @Override
    public void increment(String label, int value) {
        throwIfLabelNotFound(label);
        // We should also enforce that "value" is positive for ease of use
        throwIfNegativeValue(value);
        // Add "value" to the counter
        counters.put(label, counters.get(label) + value);
    }

    @Override
    public void decrement(String label) {
        throwIfLabelNotFound(label);
        // Subtract -1 from the counter
        counters.put(label, counters.get(label) - 1);
    }

    @Override
    public void decrement(String label, int value) {
        throwIfLabelNotFound(label);
        // We should also enforce that "value" is positive for ease of use
        throwIfNegativeValue(value);
        // Subtract "value" from the counter
        counters.put(label, counters.get(label) - value);
    }
}
