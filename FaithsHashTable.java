// File/Class Name: FaithsHashTable.java
// Author: Faith Reeves
// Class Purpose: a Hashtable class that stores data by mapping keys to values.
//                Linear and Quadratic probing are supported to solve collisions

package openaddressing;

import static java.lang.Math.*;

public class FaithsHashTable {

    // start load datafield at 50%
    double load = .5;

    // set the target table size to 2x the number of words
    int targetTableSize = 90806;

    // declare datafield for the section of the table in which words can be inserted
    int availableTableSize;

    // declare array for items inserted with linear probe
    String[] linearKeys;

    // declare array for items inserted with quadratic probe
    String[] quadraticKeys;

    // declare variable for indexes that will be calculated with probes
    int index = 0;

    // set counter datafields to 0
    int linearCollisions = 0;

    int linearNoCollisions = 0;

    int quadraticCollisions = 0;

    int quadraticNoCollisions = 0;

    // set noCollisions occured boolean to true
    boolean noCollisions = true;

    // create constant for the actual total table (array) size
    final int TOTAL_ARRAY_SIZE = 90821;

    // no arg constructor for FaithsHashTable
    FaithsHashTable() {

        // assign the available table size to be the next greatest prime of the
        // target table size
        availableTableSize = nextPrime(targetTableSize);

        // create arrays for the keys of size TOTAL_ARRAY_SIZE
        linearKeys = new String[TOTAL_ARRAY_SIZE];

        quadraticKeys = new String[TOTAL_ARRAY_SIZE];
    }

    // method to insert a word into the table using linear probing
    public void linearProbeInsert(String item) {

        // set a counter to 0
        int counter = 0;

        // calculate the word's index
        int indexL = calculateLinearIndex(item, counter);

        // set the noCollisions bool to true
        noCollisions = true;

        // execute while indexL in the table is not empty
        while (linearKeys[indexL] != null) {

            // increment counter
            counter++;

            // recalculate the index
            indexL = calculateLinearIndex(item, counter);

            // increment the linear collisions counter
            linearCollisions++;

            // change noCollisions boolean to false
            noCollisions = false;
        }

        // insert item at linearKeys[indexL]
        linearKeys[indexL] = item;

        // if there were no collisions, increment counter
        if (noCollisions) {

            linearNoCollisions++;
        }

    }

    // method to insert a word into the table using quadratic probing
    public void quadraticProbeInsert(String item) {

        // set counter to 0
        int j = 0;

        // calculate the word's index
        int indexQ = calculateQuadraticIndex(item, j);

        // set the no collisions bool to true
        noCollisions = true;

        // execute while indexQ in the table is not empty
        while (quadraticKeys[indexQ] != null) {

            // increment the counter j
            j++;

            // recalculate index
            indexQ = calculateQuadraticIndex(item, j);

            // increment the quadratic collision counter
            quadraticCollisions++;

            // change noCollisions boolean to false
            noCollisions = false;
        }

        // insert item at quadraticKays[indexQ]
        quadraticKeys[indexQ] = item;

        // if there were no collisions, increment counter
        if (noCollisions) {

            quadraticNoCollisions++;
        }
    }

    // method to calculate the next index for linear probing
    public int calculateLinearIndex(String word, int count) {

        // calculate new index
        index = abs((word.hashCode() + count) % availableTableSize);

        // return the index
        return index;
    }

    // method to calculate the next index for quadratic probing
    public int calculateQuadraticIndex(String word, int counter) {

        // calculate new index
        index = (int) (abs(word.hashCode() + Math.pow(counter, 2)) % availableTableSize);

        // return the index
        return index;
    }

    // method to calculate the next greatest prime number
    private int nextPrime(int num) {

        // iterate through the for loop starting with the int passed in
        for (int j = num;; j++) {

            // set the count equal to 0
            int count = 0;

            // iterate through, checking if numbers are not prime
            for (int i = 2; i <= j / 2; i++) {

                // if NOT prime, increment count
                if (j % i == 0) {

                    count++;
                }
            }

            // if count == 0, return the next prime number
            if (count == 0) {

                return j;
            }
        }
    }

    // method to calculate the load factor
    public double calculateLoadFactor(int elements) {

        // return calculated load factor
        return (elements * 1.0) / targetTableSize;
    }

    // method to change the target table size and available table size
    public int setTableSize(int items) {

        // increase load 
        load += 0.05;

        // calculate the new target table size
        targetTableSize = (int) ((items * 1.0) / load);

        // set the available table size to the next greatest prime of targetTableSize
        availableTableSize = nextPrime(targetTableSize);

        // return the available table size
        return availableTableSize;
    }

    // method to return the number of words inserted without a linear probing collision
    public int getLinearNoCollisions() {

        return linearNoCollisions;
    }

    // method to return the number of words inserted without a quadratic probing collision
    public int getQuadraticNoCollisions() {

        return quadraticNoCollisions;
    }

    // method to return the number of linear probing collisions
    public int getLinearCollisions() {

        return linearCollisions;
    }

    // method to return the number of quadratic probing collisions
    public int getQuadraticCollisions() {

        return quadraticCollisions;
    }

    // method to clear/reset the hashtable
    public void clearHashTable() {

        // set all counters back to default values of 0  
        linearCollisions = 0;

        linearNoCollisions = 0;

        quadraticCollisions = 0;

        quadraticNoCollisions = 0;

        // set all items in the arrays to null
        for (int i = 0; i < 90821; i++) {

            linearKeys[i] = null;
            quadraticKeys[i] = null;
        }
    }
}