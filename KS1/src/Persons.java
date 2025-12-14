import java.util.*;

public class Persons {
    private Person[] persons;

    public Persons(int n) {
        this.persons = new Person[n];
    }

    public Person[] getPersons() {
        return persons;
    }

    /**
     * The addPerson method adds person to a person array if the idx is within boundaries.
     * Rewrite of the person by another person is possible.
     * Indices range: [0 : len(n)-1]
     *
     * @param person person being added to the array
     * @param idx is the index of the array, where person should be inserted
     * @throws IllegalArgumentException  if the idx is out of range
     * */
    public void addPerson(Person person, int idx) {
        if (idx < 0 || idx >= this.persons.length) {
            throw new IllegalArgumentException("Index out of range");
        }
        persons[idx] = person;
    }

    /**
     * Sorts persons by age via insertions sort in O(nˆ2). Algorithm is in-situ (in place).
     * **/
    public void sortByAge() {
        for (int i=1; i < persons.length; i++) {

            Person personToSort = persons[i];

            int j = i-1;
            boolean inserted = false;

            while (j >= 0 && personToSort.getAge() < persons[j].getAge()) {
                persons[j+1] = persons[j];
                j--;
                inserted = true;
            }
            if (inserted) {
                persons[j+1] = personToSort;
            }
        }
    }

    /**
     * Sorts the array of Person objects by height. Internally it uses merge sort O(n*log(n)).
     *
     * Mergesort divides the array recursively until only one element is trivially sorted, then it merges
     * it into one array via comparing elements from both arrays.
     * T(n) = O(n*log(n))
     * **/
    public void sortByHeight(int left, int right) {

        if (left >= right) {
            return;
        }
        int pivot = (left + right) / 2;

        // 'Divide' the array until trivially sorted elements
        sortByHeight(left, pivot);
        sortByHeight(pivot + 1, right);

        // Merge two arrays into one
        merge(persons, left, pivot, right);
    }

    private void merge(Person[] arr, int left, int pivot, int right) {
        // Create 2 new sub arrays LEFT and RIGHT, that will be merged afterward
        int leftSize = pivot - left + 1;  // size of the left subarray
        int rightSize = right - pivot; // size of the right subarray

        Person[] leftArr = new Person[leftSize];
        Person[] rightArr = new Person[rightSize];

        // Fill in the subarrays
        fillInArray(arr, leftArr, leftSize, left);
        fillInArray(arr, rightArr, rightSize, pivot+1);

        // left and right indices of the subarrays
        int i = 0;
        int j = 0;

        // Merge 2 subarrays into the final array
        for (int l = left; l <= right; l++) {
            // left subarray is already inserted, continue with the right part
            if (i >= leftSize && j < rightSize) {
                arr[l] = rightArr[j];
                j++;
            }
            // right subarray is already inserted, continue with the left part
            else if (j >= rightSize && i < leftSize) {
                arr[l] = leftArr[i];
                i++;
            }
            // check if both arrays are not already inserted,
            // but in practice j >= rightSize cannot happen due tu the above condition evaluations,
            // if i >= leftsize  -> it means the right size is, too
            else if (i >= leftSize && j >= rightSize) {
                break;
            }
            // both arrays are being compared
            else if (leftArr[i].getHeight() < rightArr[j].getHeight()) {
                arr[l] = leftArr[i];
                i++;
            } else {
                arr[l] = rightArr[j];
                j++;
            }
        }
    }

    private void fillInArray(Person[] source, Person[] dest, int size, int left) {
        for (int i = 0; i < size; i++) {
            dest[i] = source[left + i];
        }
    }

    /**
     * Method matches person by name in the current Persons instance. It is case-insensitive.
     * It firstly sorts the array by name, and then it uses binary search.
     *
     * If the person name is not found, it returns NullPointerException.
     *
     * @param name: the name that is being found (case ignored)
     * @throws NullPointerException if the name was not found in the array
     * */
    public Person findPerson(String name) throws NullPointerException {
        System.out.println("\n=======Finding person: " + name + "=======");

        if (this.persons.length == 0) {
            return null;
        }
        sortByName();

        return findPersonByName(name, 0, persons.length - 1);
    }

    private Person findPersonByName(String name, int left, int right) {
        // no match was found
        if (left > right) {
            return null;
        }

        // divide the array into 2 halves
        int mid = (left + right) / 2;

        if (name.equalsIgnoreCase(this.persons[mid].getName())) {
            return this.persons[mid];
        }
        // decide with which half of the array the binary search will continue
        if (name.compareToIgnoreCase(this.persons[mid].getName()) < 0) {
            // name is less than the mid-name -> go left
            return findPersonByName(name, left, mid - 1);
        }
        // otherwise go right
        return findPersonByName(name, mid+1, right);
    }

    /**
     * Sorts the array of Person objects by their names. Internally it uses Radix sort.
     *
     * 1. Determines the longest name in the array.
     * 2. Pads all names to the same max length by adding spaces at the end of each name.
     * 3. Performs radix sort from right to left - sorts array per character position.
     * **/
    public void sortByName() {
        // Get the longest name that will be used for padding
        int maxLength = getLongestName(persons);

        // Make all names equally large (with maxLength ), place empty spaces " " in front
        String[] paddedNames = padNames(persons, maxLength);

        // Sort from right to left
        for (int i = maxLength - 1; i >= 0; i--) {
            countingSortPerPosition(paddedNames, persons, i);
        }
    }

    /**
     * Sorts on a specific character position.
     *
     * @param paddedNames an array of names to be sorted
     * @param persons an array whose names are being sorted - but without padding, just switching the objects
     * @param position the index of the character in the padded names to be sorted on
     * **/
    private void countingSortPerPosition(String[] paddedNames, Person[] persons, int position) {

        Person[] sortedPersons = new Person[persons.length];
        String[] sortedNames = new String[persons.length];

        int maxKey = Integer.MIN_VALUE;
        int minKey = Integer.MAX_VALUE;

        // Get min and max key at given position
        for (String name : paddedNames) {
            char c = name.charAt(position);
            minKey = Math.min(minKey, c);
            maxKey = Math.max(maxKey, c);
        }

        // count number of occurrences of character at given position
        // count[0] = X stands for: idx = Character c - minKey = 0 and counts[idx] = number of occurrences of min key (cuz 0)
        int[] counts = new int[maxKey - minKey + 1];

        for (String name : paddedNames) {
            char c = name.charAt(position);
            int idx = c - minKey; // Start from the lowest character at 0 position
            counts[idx]++;
        }
        // convert to cumulative count
        for (int i = 1; i < counts.length; i++) {
            counts[i] += counts[i - 1];
        }

        // Add person at correct position according to counts array
        for (int i = paddedNames.length - 1; i >= 0; i--) {
            int letterIdx = paddedNames[i].charAt(position) - minKey;
            int personIdx = counts [letterIdx] - 1; // array starts from 0 not 1

            sortedPersons[personIdx] = persons[i];
            sortedNames[personIdx] = paddedNames[i];

            // decrement the position in counts arr of given letter that will be located in the output array
            counts[letterIdx]--;
        }
        // Copy back to the original array
        System.arraycopy(sortedPersons, 0, persons, 0, persons.length);
        System.arraycopy(sortedNames, 0, paddedNames, 0, paddedNames.length);
    }

    /**
     * getLongestName returns the maximum length of the names in persons array
     * @param persons array whose names are compared
     * @return the length of the longest name present in the array
     * */
    private int getLongestName(Person[] persons) {
        int maxLength = 0;
        // Fill in the frequency table and get the max length of the name
        for (Person person : persons) {
            int nameLength = person.getName().length();
            maxLength = Math.max(maxLength, nameLength);
        }
        return maxLength;
    }

    /**
     * Pads the names of all Person objects.The number of spaces added to each name is equal to the difference
     * between the maximum name's length and the current name's length. The spaces are added to the end.
     *
     * @param persons the array whose names will be padded
     * @param  maxLength the length that will all names have after padding
     * */
    private String[] padNames(Person[] persons, int maxLength) {
        String[] paddedNames = new String[persons.length];

        for (int i =0; i < paddedNames.length; i++) {
            int nameLength = persons[i].getName().length();

            int padding = maxLength - nameLength;
            String newName = persons[i].getName() + " ".repeat(padding);
            paddedNames[i] = newName;

        }
        return paddedNames;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Person person : this.persons) {
            sb.append(
                    String.format(
                            "Name: %-20s Height: %-10.2f Age: %-5d\n",
                            person.getName(), person.getHeight(), person.getAge()
                    ));
        }
        return sb.toString();
    }
}
