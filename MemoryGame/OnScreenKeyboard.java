import java.util.ArrayList;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
/**
 *
 * @author James
 * @Title OnScreenKeyboard.java
 * @StartDate Nov 23, 2017
 * @LastRevison Nov 27, 2017
 * @Class RSUProgramming2
 * @Description This class provides an on screen virtual keyboard that
 * provides input into a specific TextField allowing a user to input guesses
 * for the MemoryGame class.
 *
 *@Outline Create buttons for each letter and number key as well as a backspace
 *and enter button. Set event handlers for each button and add to javafx panes
 *that can be called by other programs.
 */
public class OnScreenKeyboard  {

    BorderPane keyPane = new BorderPane();
    private TextField textField = new TextField();
    private GridPane grid = new GridPane();
    private String tops = "qwertyuiop";
    private String[] topButtons = tops.split("");
    private String mids = "asdfghjkl";
    private String[] midButtons = mids.split("");
    private String bottoms = "zxcvbnm";
    private String[] bottomButtons = bottoms.split("");
    private String[] guess;
    private static StringBuffer sb;
    public OnScreenKeyboard(){

        ArrayList<Button> topRow = new ArrayList<>();
        ArrayList<Button> midRow = new ArrayList<>();
        ArrayList<Button> bottomRow = new ArrayList<>();
        textField.setPrefColumnCount(200);
        textField.setMaxWidth(200);

        //Adds the string arrays to the ArrayList to create keyboard buttons
        for(int i = 0; i < topButtons.length; i++){
            topRow.add(new Button (topButtons[i]));
        }
        for(int i = 0; i < midButtons.length; i++){
            midRow.add(new Button (midButtons[i]));
        }
        for(int i = 0; i < bottomButtons.length; i++){
            bottomRow.add(new Button (bottomButtons[i]));
        }

    //  maps each key to the corresponding letter to be displayed
        midRow.get(0).setOnAction(e -> {textField.appendText("a");});
        bottomRow.get(4).setOnAction(e -> {textField.appendText("b");});
        bottomRow.get(2).setOnAction(e -> {textField.appendText("c");});
        midRow.get(2).setOnAction(e -> {textField.appendText("d");});
        topRow.get(2).setOnAction(e -> {textField.appendText("e");});
        midRow.get(3).setOnAction(e -> {textField.appendText("f");});
        midRow.get(4).setOnAction(e -> {textField.appendText("g");});
        midRow.get(5).setOnAction(e -> {textField.appendText("h");});
        topRow.get(7).setOnAction(e -> {textField.appendText("i");});
        midRow.get(6).setOnAction(e -> {textField.appendText("j");});
        midRow.get(7).setOnAction(e -> {textField.appendText("k");});
        midRow.get(8).setOnAction(e -> {textField.appendText("l");});
        bottomRow.get(6).setOnAction(e -> {textField.appendText("m");});
        bottomRow.get(5).setOnAction(e -> {textField.appendText("n");});
        topRow.get(8).setOnAction(e -> {textField.appendText("o");});
        topRow.get(9).setOnAction(e -> {textField.appendText("p");});
        topRow.get(0).setOnAction(e -> {textField.appendText("q");});
        topRow.get(3).setOnAction(e -> {textField.appendText("r");});
        midRow.get(1).setOnAction(e -> {textField.appendText("s");});
        topRow.get(4).setOnAction(e -> {textField.appendText("t");});
        topRow.get(6).setOnAction(e -> {textField.appendText("u");});
        bottomRow.get(3).setOnAction(e -> {textField.appendText("v");});
        topRow.get(1).setOnAction(e -> {textField.appendText("w");});
        bottomRow.get(1).setOnAction(e -> {textField.appendText("x");});
        topRow.get(5).setOnAction(e -> {textField.appendText("y");});
        bottomRow.get(0).setOnAction(e -> {textField.appendText("z");});

        //Add each button to HBox to keep alignment correct in final pane
        HBox topBox = new HBox();
        HBox midBox = new HBox();
        HBox bottomBox = new HBox();

        for(Button i : topRow)
            topBox.getChildren().add(i);

        for(Button i : midRow)
            midBox.getChildren().add(i);

        for(Button i : bottomRow)
            bottomBox.getChildren().add(i);

        /**
         * Enter button: Stores and sends the guess from the text field to the
         * checkAnswer() in the MemoryGame class and then clears textField
         * so it is ready for the next guess.
         */
        Button btnEnter = new Button("Enter");
        midBox.getChildren().add(btnEnter);
        btnEnter.setOnAction(e -> {guess = textField.getText().split("");

            for(String i : guess)
                MemoryGame.guess.add(i);

            MemoryGame.checkAnswer();
            textField.clear();
        });

        /**
         * Backspace button: Sets the current textField to a stringbuffer and deletes the last
         * char of the string if any are available. Returns the modified string
         * to the textField
         */
        Button bckSpace = new Button("Backspace");
        bckSpace.setOnAction(e -> { if (textField.getText().length() > 0){
            sb = new StringBuffer(textField.getText());
            sb = sb.deleteCharAt(textField.getText().length()-1);  // delete char at end of texField
            textField.setText(sb.toString());}                                        // display edited string in textField
        else {
            return;
        }

        });
        topBox.getChildren().add(bckSpace);

        topBox.setPadding(new Insets(5, 0, 0, 2));
        midBox.setPadding(new Insets(0, 0, 0,5));
        bottomBox.setPadding(new Insets(0, 0, 0, 15));

        /** Create the Number pad for the keyboard**/
        Button btn1 = new Button("1");
        btn1.setOnAction(e -> {textField.appendText("1");});
        Button btn2 = new Button("2");
        btn2.setOnAction(e -> {textField.appendText("2");});
        Button btn3 = new Button("3");
        btn3.setOnAction(e -> {textField.appendText("3");});
        Button btn4 = new Button("4");
        btn4.setOnAction(e -> {textField.appendText("4");});
        Button btn5 = new Button("5");
        btn5.setOnAction(e -> {textField.appendText("5");});
        Button btn6 = new Button("6");
        btn6.setOnAction(e -> {textField.appendText("6");});
        Button btn7 = new Button("7");
        btn7.setOnAction(e -> {textField.appendText("7");});
        Button btn8 = new Button("8");
        btn8.setOnAction(e -> {textField.appendText("8");});
        Button btn9 = new Button("9");
        btn9.setOnAction(e -> {textField.appendText("9");});
        Button btn0 = new Button("0");
        btn0.setOnAction(e -> {textField.appendText("0");});

        //Set and align the number buttons
        HBox topNums = new HBox();
        HBox midNums = new HBox();
        HBox bottomNums = new HBox();
        HBox zeroField = new HBox();

        topNums.getChildren().addAll(btn7, btn8, btn9);
        midNums.getChildren().addAll(btn4, btn5, btn6);
        bottomNums.getChildren().addAll(btn1, btn2, btn3);
        zeroField.getChildren().add(btn0);
        topNums.setPadding(new Insets(5, 0, 0, 2));
        midNums.setPadding(new Insets(0, 0, 0,2));
        bottomNums.setPadding(new Insets(0, 0, 0,2));
        zeroField.setPadding(new Insets(0, 0, 20, 2));

        grid.add(topBox, 0, 0);
        grid.add(midBox, 0, 1);
        grid.add(bottomBox, 0, 2);
        grid.add(topNums,1, 0);
        grid.add(midNums, 1, 1);
        grid.add(bottomNums, 1, 2);
        grid.add(zeroField, 1, 3);
        grid.setStyle("-fx-border-color : black");


        keyPane.setTop(textField);
        keyPane.setCenter(grid);
    }






}






