package edu.uab;

import edu.uab.simulation.Simulation;
import edu.uab.simulation.entities.Player;
import edu.uab.simulation.systems.Direction;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Timer;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;
    private Timer timer = new Timer();
    private Simulation simulation;

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) throws IOException {
        Pane root = new Pane();
        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.setTitle("A JavaFX Rectangle Example");
        stage.setHeight(720);
        stage.setWidth(1280);

        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                switch (event.getCode()) {
                    case UP:
                        simulation.world.input.setDirection(Direction.JUMP);
                        break;
                    case RIGHT:
                        simulation.world.input.setDirection(Direction.RIGHT);
                        break;
                    case DOWN:
                        simulation.world.input.setDirection(Direction.FALL);
                        break;
                    case LEFT:
                        simulation.world.input.setDirection(Direction.LEFT);
                        break;
                }
            }
        });

        scene.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                simulation.world.input.setIdleDirection();
            }
        });

        this.simulation = new Simulation(root.getChildren());
        this.simulation.world.entities.add(new Player());

        timer.scheduleAtFixedRate(this.simulation, 0, 16);

        stage.show();
    }

}