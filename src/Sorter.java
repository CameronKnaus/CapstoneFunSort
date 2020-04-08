import javafx.application.Platform;
import javafx.scene.layout.Pane;

import java.util.Random;

/**
 * Sorter is a class that is responsible for handling all of the free functions involving
 * sorting operations / shuffles
 */
public class Sorter {

    // Array of elements used for temporary access while sorting
    private Element[] elementList;

    // Holds the pane in the application to update with new elements
    private Pane sortSection;

    // Constructor
    Sorter(Element[] givenList, Pane givenPane) {
        elementList = givenList;
        sortSection = givenPane;
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

    // Shuffle an array of elements
    public void runShuffle() {
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

                Thread.sleep(5);
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
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
