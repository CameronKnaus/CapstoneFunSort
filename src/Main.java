import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class Main extends Application {

    // member variables
    private final int windowHeight = 720;
    private final int windowWidth = 1530;
    private int numElements = 1300; // Number of colored bars to sort
    Button button;

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

    // Start the JavaFX Application
    @Override
    public void start(Stage stage) throws Exception {
        // Name of the window
        stage.setTitle("Capstone FunSort");

        button = new Button("My Button");
    }
}