import java.util.Random;

/**
 * Sorter is a class that is responsible for handling all of the free functions involving
 * sorting operations / shuffles
 */
public class Sorter {

    // Shuffle an array of elements
    public static void shuffle(Element[] elementList) {
        // Shuffle based on a random number
        Random rand = new Random();

        // Randomly swap around elements
        for(int i = 0; i < elementList.length; ++i) {
            swap(elementList[i], elementList[rand.nextInt(elementList.length)]);
        }
    }

    // The Swap function exchanges the positions of two given elements
    private static void swap(Element givenOne, Element givenTwo) {
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
