import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 * The Element class refers to each colored sliver found in the sorted rainbow.
 * Each element of the class will contain an integer for index weighting, as well
 * as a corresponding color as created by the ColorGenerator class
 */

public class Element {
    // Weight for sorting
    private int weight;

    // Rectangle body to draw on screen
    private Rectangle body = new Rectangle();

    /**
     * Element constructor
     * @param givenWeight the index weight for sorting purposes
     */
    Element(int givenWeight, int bodyWidth, int bodyHeight) {
        weight = givenWeight;

        // Set rectangle body proportions
        body.setHeight(bodyHeight);
        body.setWidth(bodyWidth);

        // Set the X-Axis offset (position of the element)
        body.setLayoutX(givenWeight * bodyWidth);
    }

    // Set the color of the body of the element based on a given RGB value
    public void setColor(Color givenColor) {
        body.setFill(givenColor);
    }

    // Getters
    public Rectangle getBody() {return body;}

}
