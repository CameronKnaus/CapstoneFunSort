import javafx.application.Platform;
import javafx.scene.layout.Pane;

import java.util.Random;

/**
 * Sorter is a class that is responsible for handling all of the  functions involving
 * sorting operations / shuffles
 */
public class Sorter {

    // Array of elements used for temporary access while sorting
    private Element[] elementList;

    // Holds the pane in the application to update with new elements
    private Pane sortSection;

    // Holds the time between swaps in milliseconds
    private int swapTime;

    /**
     * Constructor for the Sorter helping class
     * @param givenList The list of elements to sort (holds objects of type Element)
     * @param givenPane The Node from the JavaFX Application thread to update elements in
     * @param time The desired time, in milliseconds, to stall between each swap
     */
    Sorter(Element[] givenList, Pane givenPane, int time) {
        elementList = givenList;
        sortSection = givenPane;
        swapTime = time;
    }

    /**
     * User facing function that opens a new thread to shuffle the element list
     */
    public void shuffle() {
        // Create a runnable for shuffle (allows for multithreaded running)
        Runnable task = new Runnable() {
            public void run() {
                runShuffle();
            }
        };

        // Run the shuffle in the background thread
        Thread backgroundThread = new Thread(task);

        // Terminate the running thread if the application exits
        backgroundThread.setDaemon(true);

        // Start the thread
        backgroundThread.start();
    }

    /**
     * User facing function to open a new thread for bubble sort operations
     */
    public void bubbleSort() {
        // Create a runnable for shuffle (allows for multithreaded running)
        Runnable task = new Runnable() {
            public void run() {
                runBubbleSort();
            }
        };

        // Run the shuffle in the background thread
        Thread backgroundThread = new Thread(task);

        // Terminate the running thread if the application exits
        backgroundThread.setDaemon(true);

        // Start the thread
        backgroundThread.start();
    }

    /**
     * User facing function to open a new thread for insertion sort operations
     */
    public void insertionSort() {
        // Create a runnable for shuffle (allows for multithreaded running)
        Runnable task = new Runnable() {
            public void run() {
                runInsertionSort();
            }
        };

        // Run the shuffle in the background thread
        Thread backgroundThread = new Thread(task);

        // Terminate the running thread if the application exits
        backgroundThread.setDaemon(true);

        // Start the thread
        backgroundThread.start();
    }

    /**
     * User facing function to open a new thread for merge sort operations
     */
    public void mergeSort() {
        // Create a runnable for shuffle (allows for multithreaded running)
        Runnable task = new Runnable() {
            public void run() {
                runMergeSort(0, elementList.length - 1);
            }
        };

        // Run the shuffle in the background thread
        Thread backgroundThread = new Thread(task);

        // Terminate the running thread if the application exits
        backgroundThread.setDaemon(true);

        // Start the thread
        backgroundThread.start();
    }

    /**
     * User facing function to open a new thread for Quick sort operations
     */
    public void quickSort() {
        // Create a runnable for shuffle (allows for multithreaded running)
        Runnable task = new Runnable() {
            public void run() {
                runQuickSort(0, elementList.length - 1);
            }
        };

        // Run the shuffle in the background thread
        Thread backgroundThread = new Thread(task);

        // Terminate the running thread if the application exits
        backgroundThread.setDaemon(true);

        // Start the thread
        backgroundThread.start();
    }

    /**
     * Updates pane containing all elements
     */
    private void updateList() {
        // Clear current elements
        sortSection.getChildren().clear();

        // update all children elements to sort section (colored bars)
        for (int i = 0; i < elementList.length; ++i) {
            sortSection.getChildren().add(elementList[i].getBody());
        }
    }

    /**
     * Updates pane containing all elements
     */
    private void updateGUI() {
        try {
            Platform.runLater(new Runnable()
            {
                @Override
                public void run()
                {
                    // Clear current elements
                    sortSection.getChildren().clear();

                    // update all children elements to sort section (colored bars)
                    for (int i = 0; i < elementList.length; ++i) {
                        sortSection.getChildren().add(elementList[i].getBody());
                    }
                }
            });

            // Cause the thread to sleep if a swap was executed
            Thread.sleep(swapTime);
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    /**
     * Randomly shuffles the element list. GUI is updated on the main thread through runLater()
     */
    private void runShuffle() {
        // Shuffle based on a random number
        Random rand = new Random();

        // Randomly swap around elements
        for(int i = 0; i < elementList.length; ++i) {
            try {
                swap(i, rand.nextInt(elementList.length));
                Platform.runLater(new Runnable()
                {
                    @Override
                    public void run()
                    {
                        updateList();
                    }
                });

                Thread.sleep(swapTime);
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Performs bubble sort on the element list. GUI is updated on the main thread through runLater()
     */
    private void runBubbleSort() {
        // Mark if the elements are sorted
        boolean unsorted = true;

        // Commence bubble sort
        try {
            while(unsorted) {
                // Consider the list sorted until proven otherwise
                unsorted = false;

                // Loop through all elements (one 'bubble')
                for(int i = 0; i < elementList.length - 1; ++i) {
                    if(elementList[i].getWeight() > elementList[i + 1].getWeight()) {
                        swap(i, i+1);
                        // Update Application thread after swap
                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                updateList();
                            }
                        });

                        // Cause the thread to sleep if a swap was executed
                        Thread.sleep(swapTime);

                        unsorted = true;
                    }
                }
            }
            // The element list is now sorted. Refresh one more time to ensure all elements are properly displayed
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    updateList();
                }
            });
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Performs insertion sort on the element list. GUI is updated on the main thread through runLater()
     */
    private void runInsertionSort() {
        // Commence insertion sort
        try {
            for (int i = 1; i < elementList.length; ++i) {
                int j = i;
                while (j > 0 && elementList[j].getWeight() < elementList[j - 1].getWeight()) {
                    swap(j, j - 1);
                    j -= 1;
                }
                // Update the GUI
                Platform.runLater(new Runnable()
                {
                    @Override
                    public void run()
                    {
                        updateList();
                    }
                });

                // Cause the thread to sleep if a swap was executed
                Thread.sleep(swapTime);
            }
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }

        // One last GUI update to ensure all elements are accurately in place
        Platform.runLater(new Runnable()
        {
            @Override
            public void run()
            {
                updateList();
            }
        });
    }

    /**
     * Performs merge sort on the element list. GUI is updated on the main thread through runLater()
     * Note that this is the in-place representation of Merge Sort
     * @param left The left index of the sub array to be sorted
     * @param right The right index of the sub array to be sorted
     */
    private void runMergeSort(int left, int right) {
        // Commence merge sort
        if (left < right) {
            int middle = left + (right - left) / 2;

            // Sort first and second halves
            runMergeSort(left, middle);
            runMergeSort(middle + 1, right);

            merge(left, middle, right);
        }
    }

    /**
     * Performs merge sort on the element list. The GUI is updated on the main thread through runLater() calls
     * Note that this is the recursive implementation of Quicksort as found here: https://www.geeksforgeeks.org/quick-sort/
     * @param start The starting index of the current array set
     * @param end The ending index of the current array set
     */
    private void runQuickSort(int start, int end) {
        // Commence quick sort
        if(start < end) {
            // Set the partitioning index
            int pi = partition(start, end);

            // Seperately sort the elements before and after the partition
            runQuickSort(start, pi - 1);
            runQuickSort(pi + 1, end);
        }
    }

    /**
     * Helper function for quicksort. Returns a partitioning point between the two given indexes
     * @param start The starting index of the current array section
     * @param end The ending index of the current array section
     * @return The index of the partitioning point
     */
    private int partition(int start, int end) {
        int pivot = elementList[end].getWeight();

        // Index of the smaller element
        int i = (start - 1);

        for(int j = start; j <= end - 1; ++j) {
            // If the current element is smaller than the pivot
            if(elementList[j].getWeight() < pivot) {
                // Increment the index of the smaller pivot
                ++i;

                // Perform a swap
                swap(i, j);

                // Update the GUI
                updateGUI();
            }
        }
        // Perform last swap
        swap(i + 1, end);

        // Update the GUI
        updateGUI();

        // Return the pivot point
        return (i + 1);
    }

    /**
     * Helper function that merges the pieces of merge sort
     * @param start The starting point of the sub array
     * @param mid The mid point of the sub array
     * @param end The end point of the sub array
     */
    private void merge(int start, int mid, int end) {
        try {
            int start2 = mid + 1;

            // If the direct merge is already sorted
            if (elementList[mid].getWeight() <= elementList[start2].getWeight()) {
                return;
            }

            // Two pointers to maintain start of both arrays to merge
            while (start <= mid && start2 <= end) {
                // If element 1 is in the right place
                if (elementList[start].getWeight() <= elementList[start2].getWeight()) {
                    ++start;
                } else {
                    int index = start2;

                    // Shift all the elements between element 1 and 2 right by one
                    while (index != start) {
                        swap(index, index - 1);
                        --index;

                        // Update the GUI
                        Platform.runLater(new Runnable()
                        {
                            @Override
                            public void run()
                            {
                                updateList();
                            }
                        });

                        // Cause the thread to sleep if a swap was executed
                        Thread.sleep(swapTime);
                    }

                    // Update the GUI
                    Platform.runLater(new Runnable()
                    {
                        @Override
                        public void run()
                        {
                            updateList();
                        }
                    });

                    // Update all the pointers
                    ++start;
                    ++mid;
                    ++start2;
                }
            }
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    /**
     * Swap performs a standard swap between two elements and handles gui shape objects' positions as well
     * @param indexOne, the index of one element to swap
     * @param indexTwo, the index of the other element to swap
     */
    private void swap(int indexOne, int indexTwo) {
        // Swap rectangle locations
        double tempPosition = elementList[indexOne].getBody().getLayoutX();
        elementList[indexOne].getBody().setLayoutX(elementList[indexTwo].getBody().getLayoutX());
        elementList[indexTwo].getBody().setLayoutX(tempPosition);

        // Swap the elements in the array
        Element tempElement = elementList[indexOne];
        elementList[indexOne] = elementList[indexTwo];
        elementList[indexTwo] = tempElement;
    }
}
