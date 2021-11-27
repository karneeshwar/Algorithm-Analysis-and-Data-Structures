/*Program to implement hashing while inserting 20 words
Author
Name    : Karneeshwar, Sendilkumar Vijaya
NetID   : KXS200001
*/


public class HashTable {
    // Initialize the list of words to be added into the hash table
    private String[] words = new String[] {"Friends", "himym", "Office", "Dexter", "Prison", "Break", "Brooklyn",
                    "Pokemon", "Marvel", "Comics", "Scarlet", "Flash", "Canary", "Legends", "Hiking",
                    "wander", "gym", "food", "fitness", "sing"};

    // Function to add words into the table
    public void addToTable(Table tb) {
        // Display the total number of words that are being added into the table
        System.out.println("\nTotal number of words = " + words.length);

        // For all the elements perform the contents of the loop
        for (String word : words) {
            // Function to add words into the table including rehash and collision checks
            tb.add(word);
            // Print the word that was added and the count of words added so far
            System.out.println("Word added = \"" + word + "\", Total number of words added = " + tb.entries);
        }
    }

    class Table {
        private int table_size = 31;                            // Initial size of table
        private String[] contents = new String[table_size];     // The actual table where we are adding the words
        private int entries = 0;                                // Variable to count the number of words added in the table
        private int collision = 0;                              // Variable to keep track of collisions
        private static final float LF = 0.5f;                   // Load factor defined, table size should be at least 2 times larger than elements

        // Function to add words into the table including rehash and collision checks
        public void add(String word) {
            // Function used to check if rehash is required or not, if rehash is required perform the contents of the if statement
            if (isReHashRequired()) {
                rehashing();                                    // Function to perform rehashing
                System.out.print("\nIncrease table size by "
                        + this.table_size + "\n");              // Prompt the user that the table size is increasing to new value
                System.out.print("Rehashed");                   // Prompt the user that rehash is completed
                printContents();                                // Print the contents of new table

            }
            int index = hashFunction(word);                     // Function to get the index for the new word to be added into
            entries++;                                          // Increment the count of words added in the table
            if (this.contents[index] != null)                   // If the index provided is not null, then there is a collision
            {
                // Print that there is a collision at the given index
                System.out.print("\nCollision while inserting word: \"" + word + "\" with word: \"" + this.contents[index] + "\"");
                // Increment collision variable to keep track of total number of collisons
                collision++;
                // Function, quadProb, is used to perform quadratic probing to find the new index that doesn't have any entries
                index = quadProb(index, 0, this.contents);
            }
            this.contents[index] = word;                        // Word added to the index that doesn't have any entries
            printContents();                                    // Print the contents of the table
        }

        // Function to find an index for a given word
        private int hashFunction(String key) {
            int hash = 0;                                       // Variable for storing ASCII value of the word
            for (int i = 0; i < key.length(); i++)              // Iterate for all characters in the word
                hash += key.charAt(i);                          // Add the ASCII values of all characters
            return hash % table_size;                           // Return index, which is the modulo of ASCII value and table size
        }

        // Function to perform quadratic probing to find an index that doesn't have any entries
        private int quadProb(int index, int offset, String content[]) {
            // If the new index found is null, then return the new index
            if (content[(index + (offset * offset)) % table_size] == null)
                return (index + (offset * offset)) % table_size;
            // Else recursively call quadProb function with an incremented value of offset
            return quadProb(index, offset + 1, content);
        }

        // Function to determine is rehash is required or not
        private boolean isReHashRequired() {
            // Table size should be at least 2 times larger than elements in the table, if not return true for rehashing
            return table_size * Table.LF <= entries;
        }

        // Function to perform rehashing
        private void rehashing() {
            table_size = nextPrime(2 * table_size);             // Double the table size to the next prime number
            String temp[] = new String[table_size];             // Temporary variable for table with new table size
            for (String word : this.contents)                   // For all the contents of initial table
            {
                if (word == null)                               // Skip null indices
                    continue;
                int index = hashFunction(word);                 // Use hashFunction to find new index for the existing words in table
                if (temp[index] != null)                        // Perform Quadratic probing if collision occurs
                    index = quadProb(index, 0, temp);
                temp[index] = word;                             // Add word to the temporary table
            }
            this.contents = temp;                               // Copy temporary table to the main table
        }

        // Function to find the next prime number closest to the given number
        private int nextPrime(int n) {
            while (!isPrime(n))                                 // If given number is not prime continue finding next prime
                n++;
            return n;                                           // Return the prime number which is the new table size
        }

        // Function to check is given number is prime or not
        private boolean isPrime(int n) {
            for (int i = 2; i < Math.sqrt(n); i++)
                if (n % i == 0)
                    return false;                               // Return false if not prime
            return true;                                        // Return true is prime
        }

        // Function to print the contents of the table
        public void printContents() {
            System.out.print("\nCurrent entries of table are,\n");
            for (String word : this.contents)
                System.out.print(word + " | ");                 // Contents of table. each seperated by vertical bar
            System.out.print("\n");
        }
    }

    public static void main(String[] args) {
        System.out.print("\nCS5343.002 Assignment 6: Hashing\n\n");
        HashTable hm = new HashTable();
        Table tb = hm.new Table();
        // Add words to the table
        hm.addToTable(tb);
        // Print the total number of collisions occurred in the process
        System.out.println("\nNumber of Collisions = " + tb.collision);
        System.out.print("\n\nEnd of Results!!\n");
    }
}