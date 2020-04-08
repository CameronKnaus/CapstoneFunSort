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

    // Shuffle an array of elements
    private void runShuffle() {
        // Shuffle based on a random number
        Random rand = new Random();

        // Randomly swap around elements
        for(int i = 0; i < elementList.length; ++i) {
            try {
                swap(elementList[i], elementList[rand.nextInt(elementList.length)]);
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

                Thread.sleep(swapTime);
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

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
                        swap(elementList[i], elementList[i+1]);
                        // Update Application thread after swap
                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
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

                        unsorted = true;
                    }
                }
            }
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    // The Swap function exchanges the positions of two given elements
    private void swap(Element givenOne, Element givenTwo) {
        // swap the two weight values
        int temp = givenOne.getWeight();
        givenOne.setWeight(givenTwo.getWeight());
        givenTwo.setWeight(temp);

        // Swap rectangle locations
        double tempPosition = givenOne.getBody().getLayoutX();
        givenOne.getBody().setLayoutX(givenTwo.getBody().getLayoutX());
        givenTwo.getBody().setLayoutX(tempPosition);
    }
}
