import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import javax.swing.*;
import java.lang.reflect.Array;


public class Main extends Application {

    // window size parameters
    private final int windowHeight = 720;
    private final int windowWidth = 1530;

    // Number of colored bars to sort
    private int numElements = 1530;

    // Main root of the scene as a Vertical Box
    private VBox root;

    // View of the scene
    private Node view;

    // The array to be sorted
    private Element[] elementList;

    // Create a horizontal section (pane) for the sorting window (holds the elementList)
    private Pane sortSection;

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
        /* Shuffle */
        Button shuffleButton = new Button("Shuffle");
        // Set the shuffle button action
        shuffleButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                Sorter sorter = new Sorter(elementList, sortSection, 5);
                sorter.shuffle();
            }
        });

        /* Bubble Sort */
        Button bubbleSort = new Button("Bubble Sort");
        // Set the bubble sort action
        bubbleSort.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                Sorter sorter = new Sorter(elementList, sortSection, 300);
                sorter.bubbleSort();
            }
        });

        // Create root of the scene (returns as main parent scene)
        root = new VBox();

        // Create horizontal section for buttons
        HBox actionSection = new HBox(shuffleButton, bubbleSort);

        // Create a horizontal section (pane) for the sorting window
        // Note that panes allow for absolute positioning
        sortSection = new Pane();

        // update all children elements to sort section (colored bars)
        for(int i = 0; i < elementList.length; ++i) {
            sortSection.getChildren().add(elementList[i].getBody());
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

        // Set the view of the scene to be that created in root
        view = root;

        // Display screen
        stage.show();
    }
}