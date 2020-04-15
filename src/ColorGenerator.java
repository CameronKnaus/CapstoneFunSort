import javafx.scene.paint.Color;

/**
 *  ColorGenerator keeps track of producing an even combination of colors to form a rainbow.
 *  The maximum possible RGB combinations produceable by this class is 1530
 *  The total number of elements to split the rainbow into will be calculated as
 *  1530 / numberOfElements
 */
class ColorGenerator
{
    // RGB Values
    private int red = 255;
    private int green = 0;
    private int blue = 0;

    // state of the current color (for incrementing / decrementing R, G, and B)
    private int state = 1;

    // Maximum number of possible colors (possible standard RGB combinations)
    private final int MAX_COLORS = 1530;

    // The size of an increment (amount of change per color)
    private int increment;

    // Constructor - sets the amount to increment colors based on number of elements
    ColorGenerator(int numberOfElements) {
        increment = MAX_COLORS / numberOfElements;
    }

    // Get the resulting rgb as a JavaFX Color variable
    Color getColor() {
        return Color.rgb(red, green, blue);
    }

    // Increments up by a factor of 'increment' to the next color in the rainbow
    void incrementColor() {
        // Change is used for the amount to change between RGB values, also handles overflow
        double change = increment;

        // Red to Yellow changes
        if(state == 1) {
            green += change;
            if(green > 255) {
                // Handle overflow
                change = (green - 255);
                green = 255;
                state = 2;
            }
        }

        // Yellow to Green Change
        if(state == 2) {
            red -= change;
            if(red < 0) {
                // Handle overflow
                change = Math.abs(red);
                red = 0;
                state = 3;
            }
        }

        // Green to Cyan change
        if(state == 3)
        {
            blue += change;
            if(blue > 255)
            {
                // Handle overflow
                change = (blue - 255);
                blue = 255;
                state = 4;
            }
        }

        // Cyan to Blue change
        if(state == 4)
        {
            green -= change;
            if(green < 0)
            {
                // Handle overflow
                change = Math.abs(green);
                green = 0;
                state = 5;
            }
        }

        // Blue to Purple change
        if(state == 5)
        {
            red += change;
            if(red > 255)
            {
                // Handle overflow
                change = (red - 255);
                red = 255;
                state = 6;
            }
        }

        // Purple back to Red change
        if(state == 6)
        {
            // Handle overflow
            blue -= change;
            if(blue < 0)
            {
                blue = 0;
            }
        }
    }
}
