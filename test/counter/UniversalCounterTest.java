package counter;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

public class UniversalCounterTest {
    @Test
    void createCounterWithNoLabels() {
        // Should not throw an exception on initialization
        assertDoesNotThrow(() -> new UniversalCounter());
    }

    @Test
    void createCounterWithStartingLabels() {
        // Should not throw an exception on initialization
        assertDoesNotThrow(() -> new UniversalCounter("test", "test1", "test2"));
    }

    @Test
    void createCounterWithNoLabelsInitializesEmptyHashMap() {
        // Make a UniversalCounter without any starting labels
        UniversalCounter ucWithoutLabels = new UniversalCounter();
        // Getting labels should return an empty list
        assert(ucWithoutLabels.getLabels().isEmpty());
        // This UniversalCounter should throw a CounterException when we try to get a value
        CounterException noLabelException = assertThrows(CounterException.class, () -> ucWithoutLabels.getCount("testLabel"));
        assert(noLabelException.getReason() == CounterException.ExceptionReason.NO_LABEL);
    }

    @Test
    void createCounterWithStartingLabelsInitializesHashMapWithKeysLoaded() {
        // Make a UniversalCounter without any starting labels
        UniversalCounter ucWithLabels = new UniversalCounter("test", "test1", "test2");
        // Getting labels should return a list containing all the starting labels from above
        assert(ucWithLabels.getLabels().contains("test"));
        assert(ucWithLabels.getLabels().contains("test1"));
        assert(ucWithLabels.getLabels().contains("test2"));
        // This UniversalCounter should return 0 when we try to get a value
        assert(ucWithLabels.getCount("test1") == 0);
    }

    @Test
    void getLabelsThrowsCounterException() {
        // Make a UniversalCounter without any starting labels
        UniversalCounter ucWithoutLabels = new UniversalCounter();
        // This UniversalCounter should throw a CounterException when we try to get a value
        CounterException noLabelException = assertThrows(CounterException.class, () -> ucWithoutLabels.getCount("testLabel"));
        assert(noLabelException.getReason() == CounterException.ExceptionReason.NO_LABEL);
    }

    @Test
    void incrementThrowsCounterException() {
        // Make a UniversalCounter without any starting labels
        UniversalCounter ucWithoutLabels = new UniversalCounter();
        // This UniversalCounter should throw a CounterException when we try to increment a value
        CounterException noLabelException1 = assertThrows(CounterException.class, () -> ucWithoutLabels.increment("testLabel"));
        CounterException noLabelException2 = assertThrows(CounterException.class, () -> ucWithoutLabels.increment("testLabel", 10));
        assert(noLabelException1.getReason() == CounterException.ExceptionReason.NO_LABEL);
        assert(noLabelException2.getReason() == CounterException.ExceptionReason.NO_LABEL);
    }

    @Test
    void incrementAddsToLabelCount() {
        // Make a UniversalCounter
        UniversalCounter uc = new UniversalCounter("label1", "label2");
        // Counters should start out as 0
        assert(uc.getCount("label1") == 0);
        assert(uc.getCount("label2") == 0);
        // Increment the counters
        uc.increment("label1");
        uc.increment("label2", 10);
        assert(uc.getCount("label1") == 1);
        assert(uc.getCount("label2") == 10);
    }

    @Test
    void clearResetsAllCountersAndLabels() {
        // Make a UniversalCounter and increment some labels
        UniversalCounter uc = new UniversalCounter("label1", "label2");
        uc.increment("label1");
        uc.increment("label2", 10);
        // Clear the UniversalCounter
        uc.clear();
        // Make sure that all traces of these labels are gone
        CounterException noLabelException1 = assertThrows(CounterException.class, () -> uc.getCount("label1"));
        CounterException noLabelException2 = assertThrows(CounterException.class, () -> uc.getCount("label2"));
        assert(uc.getLabels().isEmpty());
        assert(noLabelException1.getReason() == CounterException.ExceptionReason.NO_LABEL);
        assert(noLabelException2.getReason() == CounterException.ExceptionReason.NO_LABEL);
    }

    @Test
    void getTotalCountReturnsTheCorrectNumber() {
        // Make a UniversalCounter and increment some labels
        UniversalCounter uc = new UniversalCounter("label1", "label2");
        // Total count should be 0
        assert(uc.getTotalCount() == 0);
        // Increment some counters
        uc.increment("label1");
        uc.increment("label2", 10);
        // Total count should be 11
        assert(uc.getTotalCount() == 11);
    }

    @Test
    void decrementThrowsCounterExceptionWhenBadLabel() {
        // Make a UniversalCounter without any starting labels
        UniversalCounter ucWithoutLabels = new UniversalCounter();
        // This UniversalCounter should throw a CounterException when we try to decrement a value
        CounterException noLabelException1 = assertThrows(CounterException.class, () -> ucWithoutLabels.decrement("testLabel"));
        CounterException noLabelException2 = assertThrows(CounterException.class, () -> ucWithoutLabels.decrement("testLabel", 10));
        assert(noLabelException1.getReason() == CounterException.ExceptionReason.NO_LABEL);
        assert(noLabelException2.getReason() == CounterException.ExceptionReason.NO_LABEL);
    }

    @Test
    void decrementSubtractsFromLabelCount() {
        // Make a UniversalCounter
        UniversalCounter uc = new UniversalCounter("label1", "label2");
        // Counters should start out as 0
        assert(uc.getCount("label1") == 0);
        assert(uc.getCount("label2") == 0);
        // Increment the counters
        uc.decrement("label1");
        uc.decrement("label2", 10);
        assert(uc.getCount("label1") == -1);
        assert(uc.getCount("label2") == -10);
    }

    @Test
    void createCounterWithDuplicateLabelsThrowsCounterException() {
        // Should throw an exception on initialization
        CounterException duplicateException = assertThrows(CounterException.class, () -> new UniversalCounter("test", "test"));
        assert(duplicateException.getReason() == CounterException.ExceptionReason.DUPLICATE_LABEL);
    }

    @Test
    void addCounterThrowsCounterExceptionOnDuplicateLabel() {
        UniversalCounter uc = new UniversalCounter("test");
        // Should throw an exception on addCounter
        CounterException duplicateException = assertThrows(CounterException.class, () -> uc.addCounter("test"));
        // Make sure we're throwing the right kind of error
        assert(duplicateException.getReason() == CounterException.ExceptionReason.DUPLICATE_LABEL);
    }

    @Test
    void addCounterAddsCounterWithValueZero() {
        UniversalCounter uc = new UniversalCounter();
        uc.addCounter("test");
        // Check that the label was added as a key
        assert(uc.getLabels().contains("test"));
        // Check that the label's value is 0
        assert(uc.getCount("test") == 0);
    }

    @Test
    void removeCounterThrowsCounterExceptionIfLabelDoesNotExist() {
        UniversalCounter uc = new UniversalCounter();
        CounterException noLabelException = assertThrows(CounterException.class, () -> uc.removeCounter("test"));
        // Make sure we're throwing the right kind of error
        assert(noLabelException.getReason() == CounterException.ExceptionReason.NO_LABEL);
    }

    @Test
    void removeCounterDeletesTargetCounter() {
        UniversalCounter uc = new UniversalCounter("test", "test1");
        uc.removeCounter("test");
        assert(!uc.getLabels().contains("test")); // This should be gone
        assert(uc.getLabels().contains("test1")); // This should still be here
        // And getting the value of the deleted label throws
        CounterException noLabelException = assertThrows(CounterException.class, () -> uc.getCount("test"));
        // Make sure we're throwing the right kind of error
        assert(noLabelException.getReason() == CounterException.ExceptionReason.NO_LABEL);
    }

    @Test
    void incrementThrowsCounterExceptionIfValueIsNegative() {
        UniversalCounter uc = new UniversalCounter("test");
        CounterException badValueException = assertThrows(CounterException.class, () -> uc.increment("test", -1));
        // Make sure we're throwing the right kind of error
        assert(badValueException.getReason() == CounterException.ExceptionReason.BAD_VALUE);
    }

    @Test
    void decrementThrowsCounterExceptionIfValueIsNegative() {
        UniversalCounter uc = new UniversalCounter("test");
        CounterException badValueException = assertThrows(CounterException.class, () -> uc.decrement("test", -1));
        // Make sure we're throwing the right kind of error
        assert(badValueException.getReason() == CounterException.ExceptionReason.BAD_VALUE);
    }

    @Test
    void incrementThrowsCounterExceptionIfValueIsZero() {
        UniversalCounter uc = new UniversalCounter("test");
        CounterException badValueException = assertThrows(CounterException.class, () -> uc.increment("test", 0));
        // Make sure we're throwing the right kind of error
        assert(badValueException.getReason() == CounterException.ExceptionReason.BAD_VALUE);
    }

    @Test
    void decrementThrowsCounterExceptionIfValueIsZero() {
        UniversalCounter uc = new UniversalCounter("test");
        CounterException badValueException = assertThrows(CounterException.class, () -> new UniversalCounter("test").decrement("test", 0));
        // Make sure we're throwing the right kind of error
        assert(badValueException.getReason() == CounterException.ExceptionReason.BAD_VALUE);
    }
}
