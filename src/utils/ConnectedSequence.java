package utils;

import java.util.HashMap;
import java.util.Map;

/**
 * Class representing a sequence of (unique, non-null) elements where each element is connected to the next one in
 * the sequence, making functionality where asking for the next element by giving the previous as argument possible.
 * @param <E> The type of elements stored in the sequence
 */
public class ConnectedSequence<E> {

    /**
     * Every element makes up one key and one value in the map with the exception of the first and latest added element
     * An added element is treated as a value and paired up with the latest key,
     * then that elements becomes the key for the next element.
     */
    private final Map<E, E> map;
    private E firstKey;
    private E lastValue;

    public ConnectedSequence() {
        this.map = new HashMap<>();
    }

    /**
     * Adds the element at the end of the sequence.
     * Note: The order at which elements are added cannot change after insertion (unless the reset method is called)
     * @param element The element to be added
     */
    public void add(E element) {
        if (element == null) {
            throw new IllegalSequenceException("Cannot handle null as element in sequence");
        }
        if (map.containsValue(element) || element.equals(firstKey)) {
            throw new IllegalSequenceException("The sequence already contains element " + element + ".");
        }

        if (firstKey == null) {
            firstKey = element;
        } else {
            // Put the previous added element as key together with parameter element as value
            map.put(lastValue, element);
        }
        // lastValue is always the latest added element
        lastValue = element;
    }

    /**
     * Retrieves the first element in the sequence
     * @return The first element of the sequence or null if none has been added yet
     */
    public E first() {
        return firstKey;
    }

    /**
     * Retrieves the last element in the sequence
     * @return The last element of the sequence or null if none has been added yet
     */
    public E last() {
        return lastValue;
    }

    /**
     * By giving an element as parameter this method returns the next element in the sequence or null if either
     * the element given does not exist in the sequence or it is the last element in the sequence
     * @param element The element that you want the consequent element of
     * @return The consequent element in the sequence or null if there is not one
     */
    public E next(E element) {
        return map.get(element);
    }

    /**
     * Calculates the size of the sequence
     * @return The size of the sequence
     */
    public int size() {
        // + 1 because the lastValue has not been added as a key yet
        return firstKey == null ? 0 : map.size() + 1;
    }

    /**
     * Determines if the sequence is empty or not
     * @return True if no elements are in the sequence, false otherwise
     */
    public boolean isEmpty() {
        return this.size() == 0;
    }

    /**
     * Resets the sequence by removing all elements in it
     * (This is the only way to change the order of elements)
     */
    public void reset() {
        map.clear();
        firstKey = null;
        lastValue = null;
    }


    static class IllegalSequenceException extends RuntimeException {
        public IllegalSequenceException(String message) {
            super(message);
        }
    }
}
