import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 *
 * @author James
 * @Title PlayMemoryGame.java
 * @StartDate Nov 24, 2017
 * @LastRevison Nov 27, 2017
 * @Class RSUProgramming2
 * @Description This file launches the playable memory game by constructing the
 *  OnScreenKeyboard  and MemoryGame classes and displaying them in a window.
 *  The GUI allows a player to Start a game, Stop a game/exit the program, and
 *  enter guesses to number sequences using the virtual keyboard. If a player
 *  makes an incorrect guess, the program gives the player the choice to start
 *  over or exit the program. When the player makes an incorrect choice, the
 *  highest number of correctly guessed numbers is displayed for the round
 *  along with the record high for the session.
 *
 *@Outline Create a GUI to display the keyboard and game module and launch
 *the program from the main().
 */
public class PlayMemoryGame extends Application {

    static BorderPane borderPane = new BorderPane();
    @Override
    /**
     * Set the layout for the program to display the MemoryGame
     * content and the interactive virtual keyboard.
     */
    public void start(Stage primaryStage) {
        new MemoryGame();
        Pane root = new Pane();
        OnScreenKeyboard keyboard = new OnScreenKeyboard();
        borderPane.setTop(MemoryGame.pane);
        borderPane.setPadding(new Insets( 10, 10, 10, 10));
        borderPane.setCenter(keyboard.keyPane);
        root.getChildren().add(borderPane);
        Scene scene = new Scene(root);
        primaryStage.setTitle("Memory Game");
        primaryStage.setScene(scene);
        primaryStage.show();

    }
    /**
     * 	*****************************************************************
     main
     @purpose	Launches the program in the IDE if needed.

     @param args
     *******************************************************************
     */
    public static void main(String[] args){
        launch(args);
    }





}

