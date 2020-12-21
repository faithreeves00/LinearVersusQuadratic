// File/Class Name: OpenAddressing.java
// Author: Faith Reeves
// Class Purpose: To test FaithsHashTable and collect data on the efficiency of
//                linear probing vs quadratic probing in a hashtable

package openaddressing;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import javax.swing.JFileChooser;

public class OpenAddressing {

    public static void main(String[] args) {

        // create constant for number of dictionary items
        final int NUM_ITEMS = 45403;

        // create a FaithsHashTable Object
        FaithsHashTable hashTable = new FaithsHashTable();

        // create a String array to store the data from the "dictionary.txt" file
        String[] dictionaryWords = new String[NUM_ITEMS];

        // prompt the user to select the "dictionary.txt" file
        System.out.println("Select the \"dictionary.txt\" file.");

        // create a JFileChooser object
        JFileChooser chooser = new JFileChooser();

        // open the file dialog box
        chooser.showOpenDialog(null);

        // get the selected file
        File f = chooser.getSelectedFile();

        // store the file path
        String filename = f.getAbsolutePath();

        // declare file object
        File myDictionary;

        // declare Scanner object
        Scanner input = null;

        // beggining of try block
        try {

            // instantiate File object
            myDictionary = new File(filename);

            // instantiate Scanner object
            input = new Scanner(myDictionary);

            // check if the file does not exist or does not contain "dictionary.txt"
            if (!myDictionary.exists() || !filename.contains("dictionary.txt")) {

                // throw file not found exception
                throw new FileNotFoundException();

            }

        } // end of try block
        
        // handles exception if user's file does not exist
        catch (FileNotFoundException ex) {

            // tell the user that the file name is invalid and that you are exiting the program
            System.out.print("Sorry, the file \"dictionary.txt\" was not found. Exiting Program.");

            // exit the program
            System.exit(1);

        }

        // create a counter for the while loop
        int j = 0;

        // execute the loop for each word in the "dictionary.txt" file
        while (input.hasNextLine()) {

            // store each word from the file in the dictionaryWords array
            dictionaryWords[j] = input.nextLine();

            // increment the counter variable
            j++;
        }

        // close the file reading Scanner
        input.close();

        // display header for data that will be found
        System.out.printf("%-16s%-38s%-23s%-38s%-23s\n", "load factor", "LP inserations with no collisions",
                "LP collision count", "QP inserations with no collisions", "QP collision count");

        // loop through for each load factor from 50%-100%
        for (int i = 0; i < 11; i++) {

            // display the load factor being used
            System.out.printf("%-16.2f", hashTable.calculateLoadFactor(NUM_ITEMS));

            // insert all the dictionary words into the hashtable
            for (int k = 0; k < NUM_ITEMS; k++) {

                // insert using linear probe
                hashTable.linearProbeInsert(dictionaryWords[k]);

                // insert using quadratic probe
                hashTable.quadraticProbeInsert(dictionaryWords[k]);
            }

            // print out number of items inserted without collisions and number of collisions
            System.out.printf("%-38d%-23d%-38d%-23d\n", hashTable.getLinearNoCollisions(),
                    hashTable.getLinearCollisions(), hashTable.getQuadraticNoCollisions(),
                    hashTable.getQuadraticCollisions());

            // clear the hashTable
            hashTable.clearHashTable();

            // decrease the table size to increase the load factor
            hashTable.setTableSize(NUM_ITEMS);
        }
    }
}
