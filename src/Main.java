import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {

    // window size parameters
    private final int windowHeight = 720;
    private final int windowWidth = 1530;

    // The array to be sorted
    private Element[] elementList;

    // Create a horizontal section (pane) for the sorting window (holds the elementList)
    private Pane sortSection;

    // Time in milliseconds to delay between swaps
    private int swapTime = 10;

    // Initial set up as main
    public static void main(String[] args) {
        // Connect to StyleSheet
        setUserAgentStylesheet("Main.css");

        // Output Version Information to the console
        String javaVersion = System.getProperty("java.version");
        String javafxVersion = System.getProperty("javafx.version");
        System.out.println("Java: " + javaVersion);
        System.out.println("JavaFX: " + javafxVersion);

        // Program Start
        launch();
    }

    // Parent of all nodes with children in the scene
    private Parent createLayout() {
        /* Shuffle Button */
        Button shuffleButton = new Button("Shuffle");

        // Set the shuffle button action
        shuffleButton.setOnAction(e -> {
            Sorter sorter = new Sorter(elementList, sortSection, 5);
            sorter.shuffle();
        });

        /* Bubble Sort Button */
        Button bubbleSort = new Button("Bubble Sort");

        // Set the bubble sort action
        bubbleSort.setOnAction(e -> {
            Sorter sorter = new Sorter(elementList, sortSection, swapTime);
            sorter.bubbleSort();
        });

        /* Insertion Sort Button */
        Button insertionSort = new Button("Insertion Sort");

        // Set the insertion sort action
        insertionSort.setOnAction(e -> {
            Sorter sorter = new Sorter(elementList, sortSection, swapTime);
            sorter.insertionSort();
        });

        /* Merge Sort Button */
        Button mergeSort = new Button("Merge Sort");

        // Set the merge sort action
        mergeSort.setOnAction(e -> {
            Sorter sorter = new Sorter(elementList, sortSection, swapTime);
            sorter.mergeSort();
        });

        /* Quick Sort Button */
        Button quickSort = new Button("Quick Sort");

        // Set the Quick sort action
        quickSort.setOnAction(e -> {
            Sorter sorter = new Sorter(elementList, sortSection, swapTime);
            sorter.quickSort();
        });

        /* Heap Sort Button */
        Button heapSort = new Button("Heap Sort");

        // Set the Quick sort action
        heapSort.setOnAction(e -> {
            Sorter sorter = new Sorter(elementList, sortSection, swapTime);
            sorter.heapSort();
        });

        // Array Size Combo Box
        final ComboBox<String> arraySize = new ComboBox<>();
        arraySize.getItems().addAll(
                "Very Small (10)",
                "Small (30)",
                "Medium (90)",
                "Large (300)",
                "Very Large (765)",
                "Max (1530)"
        );

        // Add listener to update array size
        arraySize.valueProperty().addListener((ov, t, t1) -> {
            // Set actions for each option
            switch (t1) {
                case "Very Small (10)":
                    elementList = ArrayInitializer.initialize(10, windowWidth, windowHeight);
                    break;
                case "Small (30)":
                    elementList = ArrayInitializer.initialize(30, windowWidth, windowHeight);
                    break;
                case "Medium (90)":
                    elementList = ArrayInitializer.initialize(90, windowWidth, windowHeight);
                    break;
                case "Large (300)":
                    elementList = ArrayInitializer.initialize(306, windowWidth, windowHeight);
                    break;
                case "Very Large (765)":
                    elementList = ArrayInitializer.initialize(765, windowWidth, windowHeight);
                    break;
                case "Max (1530)":
                    elementList = ArrayInitializer.initialize(1530, windowWidth, windowHeight);
                    break;
            }

            // Clear the current elements from the GUI
            sortSection.getChildren().clear();

            // Update the gui with the current elements
            for (Element element : elementList) {
                sortSection.getChildren().add(element.getBody());
            }
        });
        arraySize.setValue("Array Size");

        // Swap Speed Combo Box
        final ComboBox<String> swapSpeed = new ComboBox<>();
        swapSpeed.getItems().addAll(
                "Very Fast (7ms)",
                "Fast (10ms)",
                "Medium (25ms)",
                "Slow (50ms)",
                "Very Slow (100ms)",
                "Slowest (300ms)"
        );

        // Add listener to update array size
        swapSpeed.valueProperty().addListener((ov, t, t1) -> {
            // Set actions for each option
            switch (t1) {
                case "Very Fast (7ms)":
                    swapTime = 7;
                    break;
                case "Fast (10ms)":
                    swapTime = 10;
                    break;
                case "Medium (25ms)":
                    swapTime = 25;
                    break;
                case "Slow (50ms)":
                    swapTime = 50;
                    break;
                case "Very Slow (100ms)":
                    swapTime = 100;
                    break;
                case "Slowest (300ms)":
                    swapTime = 300;
                    break;
            }
        });

        swapSpeed.setValue("Swap Time");

        // Create root of the scene (returns as main parent scene)
        // Main root of the scene as a Vertical Box
        VBox root = new VBox();

        // Create horizontal section for buttons
        HBox actionSection = new HBox(shuffleButton, bubbleSort,
                insertionSort, mergeSort, quickSort, heapSort, arraySize, swapSpeed);

        // Create a horizontal section (pane) for the sorting window
        // Note that panes allow for absolute positioning
        sortSection = new Pane();

        // update all children elements to sort section (colored bars)
        for (Element element : elementList) {
            sortSection.getChildren().add(element.getBody());
        }

        // Combine both HBox's into one Vertical box
        root.getChildren().add(actionSection);
        root.getChildren().add(sortSection);

        // Return the completed scene
        return root;
    }

    // Start the JavaFX Application
    @Override
    public void start(Stage stage) {
        // Name of the window
        stage.setTitle("Capstone FunSort");

        // Number of elements to start with
        int numElements = 306;

        // Create list of elements to sort
        elementList = ArrayInitializer.initialize(numElements, windowWidth, windowHeight);

        // Draw the scene to the stage
        stage.setScene(new Scene(createLayout()));
        stage.setResizable(false);

        // Display screen
        stage.show();
    }
}