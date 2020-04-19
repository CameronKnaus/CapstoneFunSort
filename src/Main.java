import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {

    // window size parameters
    private final int windowHeight = 720;
    private final int windowWidth = 1530;

    // Number of colored bars to sort
    private int numElements = 306;

    // Main root of the scene as a Vertical Box
    private VBox root;

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
        shuffleButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                Sorter sorter = new Sorter(elementList, sortSection, 5);
                sorter.shuffle();
            }
        });

        /* Bubble Sort Button */
        Button bubbleSort = new Button("Bubble Sort");

        // Set the bubble sort action
        bubbleSort.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                Sorter sorter = new Sorter(elementList, sortSection, swapTime);
                sorter.bubbleSort();
            }
        });

        /* Insertion Sort Button */
        Button insertionSort = new Button("Insertion Sort");

        // Set the insertion sort action
        insertionSort.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                Sorter sorter = new Sorter(elementList, sortSection, swapTime);
                sorter.insertionSort();
            }
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
        heapSort.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                Sorter sorter = new Sorter(elementList, sortSection, swapTime);
                sorter.heapSort();
            }
        });

        // Array Size Combo Box
        final ComboBox arraySize = new ComboBox();
        arraySize.getItems().addAll(
                "Very Small (10)",
                "Small (30)",
                "Medium (90)",
                "Large (300)",
                "Very Large (765)",
                "Max (1530)"
        );

        // Add listener to update array size
        arraySize.valueProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue ov, String t, String t1) {
                // Set actions for each option
                if(t1.equals("Very Small (10)"))
                    elementList = ArrayInitializer.initialize(10, windowWidth, windowHeight);
                else if(t1.equals("Small (30)"))
                    elementList = ArrayInitializer.initialize(30, windowWidth, windowHeight);
                else if(t1.equals("Medium (90)"))
                    elementList = ArrayInitializer.initialize(90, windowWidth, windowHeight);
                else if(t1.equals("Large (300)"))
                    elementList = ArrayInitializer.initialize(300, windowWidth, windowHeight);
                else if(t1.equals("Very Large (765)"))
                    elementList = ArrayInitializer.initialize(765, windowWidth, windowHeight);
                else if(t1.equals("Max (1530)"))
                    elementList = ArrayInitializer.initialize(1530, windowWidth, windowHeight);

                // Clear the current elements from the GUI
                sortSection.getChildren().clear();

                // Update the gui with the current elements
                for (Element element : elementList) {
                    sortSection.getChildren().add(element.getBody());
                }
            }
        });
        arraySize.setValue("Array Size");

        // Swap Speed Combo Box
        final ComboBox swapSpeed = new ComboBox();
        swapSpeed.getItems().addAll(
                "Very Fast (5ms)",
                "Fast (10ms)",
                "Medium (25ms)",
                "Slow (50ms)",
                "Very Slow (100ms)",
                "Slowest (300ms)"
        );

        // Add listener to update array size
        swapSpeed.valueProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue ov, String t, String t1) {
                // Set actions for each option
                if(t1.equals("Very Fast (5ms)"))
                    swapTime = 5;
                else if(t1.equals("Fast (10ms)"))
                    swapTime = 10;
                else if(t1.equals("Medium (25ms)"))
                    swapTime = 25;
                else if(t1.equals("Slow (50ms)"))
                    swapTime = 50;
                else if(t1.equals("Very Slow (100ms)"))
                    swapTime = 100;
                else if(t1.equals("Slowest (300ms)"))
                    swapTime = 300;

            }
        });

        swapSpeed.setValue("Swap Time");

        // Create root of the scene (returns as main parent scene)
        root = new VBox();

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

        // Create list of elements to sort
        elementList = ArrayInitializer.initialize(numElements, windowWidth, windowHeight);

        // Draw the scene to the stage
        stage.setScene(new Scene(createLayout()));
        stage.setResizable(false);

        // Display screen
        stage.show();
    }
}