/*
 * Copyright (c) 2023. Gary F. Pollice
 *
 * This files was developed for personal or educational purposes. All rights reserved.
 *
 *  You may use this software for any purpose except as follows:
 *  1) You may not submit this file without modification for any educational assignment.
 *  2) You may not remove this copyright, even if you have modified this file.
 */

package counter;

import java.util.*;

/**
 * This is the interface that defines all of the public methods
 * that the universal counter recognizes. The implementation must
 * implement all of these methods.
 *
 * ## YOU MAY NOT MODIFY OR MOVE THIS FILE ##
 */
public interface IUniversalCounter {

    /**
     * Resets all counts to zero.
     */
    void resetCounters();

    /**
     * Reset a single counter to zero. The counter is specified
     * by the label
     * @param label a counter label
     * @throws CounterException (NOLABEL) if the label does not exist
     */
    void resetCounter(String label);

    /**
     * Clears the UniversalCounter by removing all labels and counts.
     */
    void clear();

    /**
     * @param label a counter label
     * @return the current count for the counter specified by the label
     * @throws CounterException (NOLABEL) if the label does not exist
     */
    int getCount(String label);

    /**
     * @return the sum of all current counters
     */
    int getTotalCount();

    /**
     * @return The current set of labels for currently active counters
     */
    Collection<String> getLabels();

    /**
     * Adds a counter with the specified name with an initial count of zero.
     * @param label the label associated with the counter
     * @throws CounterException (DUPLICATE_LABEL) if there already is a label
     *  with the specified name
     */
    void addCounter(String label);

    /**
     * Remove the label of the counter to remove
     * @param label of the counter
     * @throws CounterException (NO_LABEL) if there is no counter with
     * the specified label
     */
    void removeCounter(String label);

    /**
     * Increment the specified label's count by 1
     * @param label the label associated with the counter
     * @throws CounterException (NO_LABEL) if there is no counter with
     * the specified label
     */
    void increment(String label);

    /**
     * Increment the specified label's count by the value
     * @param label the lablel associated with the counter
     * @param value positive integer
     * @throws CounterException (NO_LABEL) if there is no counter with the specified label
     * @throws CounterException (BAD_VALUE) if the value is not a positive integer
     */
    void increment(String label, int value);

    /**
     * Decrement the specified label's count by 1.
     * @param label the lablel associated with the counter
     * @throws CounterException (NO_LABEL) if there is no counter with the specified label
     * @throws CounterException (BAD_VALUE) if the counter would be less than zero
     *  after being decremented by 1.
     */
    void decrement(String label);

    /**
     * Decrement the specified label's count by the value
*    * @param label the lablel associated with the counter
*    * @param value positive integer
*    * @throws CounterException (NO_LABEL) if there is no counter with the specified label
*    * @throws CounterException (BAD_VALUE) if the value is not a positive integer or if
     *  the counter would be less than zero after being decremented by 1.
     */
    void decrement(String label, int value);
}
