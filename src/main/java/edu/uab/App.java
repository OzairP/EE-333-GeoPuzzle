package edu.uab;

import edu.uab.simulation.Simulation;
import edu.uab.simulation.World;
import edu.uab.simulation.entities.Spikes;
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
        stage.setScene(scene);
        stage.setTitle("GeoPuzzle");
        stage.setHeight(720);
        stage.setWidth(1280);

        stage.setScene(new Scene(App.loadFXML("primary")));
        stage.show();

//        Pane root = new Pane();
//        Scene scene = new Scene(root);
//
//        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
//            @Override
//            public void handle(KeyEvent event) {
//                switch (event.getCode()) {
//                    case UP:
//                        simulation.world.input.setDirection(Direction.JUMP);
//                        break;
//                    case RIGHT:
//                        simulation.world.input.setDirection(Direction.RIGHT);
//                        break;
//                    case DOWN:
//                        simulation.world.input.setDirection(Direction.FALL);
//                        break;
//                    case LEFT:
//                        simulation.world.input.setDirection(Direction.LEFT);
//                        break;
//                }
//            }
//        });
//
//        scene.setOnKeyReleased(new EventHandler<KeyEvent>() {
//            @Override
//            public void handle(KeyEvent event) {
//                simulation.world.input.setIdleDirection();
//            }
//        });
//
//        World world = new World(root.getChildren(), 720, 1280, 1250, 700, 25);
//        world.setKeyLocation(250, 670, 50);
//        world.setExitLocation(150, 620, 50);
//        world.entities.add(new Spikes(world.nextEntityId(), 350, 670, 50));
//        this.simulation = new Simulation(world);
//
//        timer.scheduleAtFixedRate(this.simulation, 0, 16);
//
//        stage.show();
    }

}