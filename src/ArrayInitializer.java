/**
 *  A Standalone free function helper to initialize the data array to be sorted
 */
class ArrayInitializer {
    /**
     * initialize a new array of Element class objects for sorting and displaying JFX rectangles
     * @param numberOfElements The total number of desired elements in the array
     * @param displayWidth The width in pixels of the containing display window
     * @param displayHeight The height in pixels of the containing display window
     * @return returns an array of Element objects with the proper color and weights
     */
    static Element[] initialize(int numberOfElements, int displayWidth, int displayHeight) {
        // Create the initial array
        Element[] elementList = new Element[numberOfElements];

        // Calculate the width for the elements
        int width = displayWidth / numberOfElements;

        // Helper class for generating the colors of the rainbow
        ColorGenerator generator = new ColorGenerator(numberOfElements);

        for(int i = 0; i < numberOfElements; ++i) {

            // Provide the weight to the element
            elementList[i] = new Element(i, width, displayHeight);

            // Set the element color
            elementList[i].setColor(generator.getColor());

            // Increment to the next color of the rainbow
            generator.incrementColor();
        }

        return elementList;
    }
}
