import java.util.ArrayList;

import javafx.animation.PauseTransition;
import javafx.animation.SequentialTransition;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.util.Duration;
/**
 * 
 * @author James
 * @Title MemoryGame.java 
 * @StartDate Nov 25, 2017
 * @LastRevison Aug 26, 2019
 * @Class RSUProgramming2
 * @Description Memory Game Displays a sequence of numbers to a player and 
 * the player must enter the correct sequence into a text field. Each character
 * is displayed for 1000ms and then the player is to use the virtual keyboard
 * to enter their guess. If correct, the sequence increases by 1. If incorrect
 * the game ends and displays the current score of correct numbers entered in
 * sequence and the record highest number for the current game. A player
 * may then choose to start over, or exit the program.  
 *
 *@Outline
 */
public class MemoryGame {
	static Button btnStart = new Button("Start");
	private static StackPane stackPane = new StackPane();
	private static BorderPane borderPane = new BorderPane();
	public static Pane pane = new Pane();
	private static int counter = 0;
	private static int record = 0;
	public static ArrayList<String> guess = new ArrayList<>();
	public static ArrayList<String> answer = new ArrayList<>();
	private static ArrayList<String> sequenceNums = new ArrayList<>();
	static Label label = new Label();
	private static Text startText = new Text();
	private static TilePane tilePane = new TilePane();
	private String instructions =
	  ("WELCOME TO THE MEMORY TESTER!\n"
		+ "In this game, a series of numbers will be displayed \non the "
		+ "screen. You must memorize each number in\norder and use"
		+ " the virtual keyboard below to enter your \nanswer. When you"
		+ " have typed in your guess click the\n\"Enter\" key. Click "
		+ "\"start\"when you are ready to begin.");
	
	public MemoryGame(){
		
		borderPane.prefWidth(250);
		 startText.setText(instructions);
		stackPane.getChildren().add(startText);
		 createSequence(); // creates the first random sequence of numbers
		
		 Button btnStop = new Button ("Stop");	 
	 		
		 VBox vBox = new VBox();
		 vBox.getChildren().addAll(btnStart, btnStop); 
		//Add label and set Height and width values to keep it visible
		 label.setText(instructions);		 
		 borderPane.setCenter(label);		
		 borderPane.setRight(vBox);
		 borderPane.setMinHeight(200);
		 borderPane.setMinWidth(200);
		 borderPane.setPrefHeight(200);
		 borderPane.setPrefWidth(350);
		 
		 /**
		  * Clicking the start button begins a new game and displays the first
		  * sequence to try. After it is clicked the button is hidden so a 
		  * player can not click it again to view the sequence a second time.
		  */

		 btnStart.setOnAction(e -> { 			 
			label.setText("Get Ready");
			SequentialTransition sequence = newSequence();
			sequence.play();
	       btnStart.setVisible(false);
	        
		 });

		 // StopButton exits the program
			btnStop.setOnAction(e -> { System.exit(0);});


			/**Set the attributes for the display area of the MemoryGame
			   *  when called by another class.*/
			pane.setPrefWidth(350);
			pane.setPrefHeight(250);
			pane.setPadding(new Insets(50, 10, 20, 10));
		    pane.setStyle("-fx-background-color: cornsilk;");
		   pane.getChildren().add(borderPane);
		
		
	} 
		

	/**
	 * *****************************************************************
	 										createSequence								
	  																	
	  @purpose	 Generates a list of random integers from 0 to 9												
	 and stores the results as a string. Assigns the list to 
	 "answer" and adds the string"GO!" to be displayed
	 after the list is displayed.
	 *******************************************************************
	 */
	private static void createSequence(){	
	
		for(int i = 0;  i < counter + 4;  i++){	 // start with four numbers

			sequenceNums.add(String.valueOf((int)(Math.random() * 10)));
		}
		answer = sequenceNums;
		sequenceNums.add("GO!");
		
		 }
	/**
	 * *****************************************************************
	 									checkAnswer								
	  																	
	  @purpose	Checks the list "answer" and "guess" to see if they 
	  are equal. If equal, displays a message, stores the size of "answer"
	  to "record". If the answer size is greater than the record, increases
	  counter, and clears the lists in order to accept new input and 										
	  begins the next round. If not equal, displays stats for current 
	  round and asks if the player would like to start over. Clears 
	  all variables and panes if the player says yes, and exits the 
	  program if the player selects no.
	 *******************************************************************
	 */
	public static void checkAnswer(){

		answer.remove(answer.size() -1);

		if(guess.equals(answer)){		
			
			if(record < answer.size())
				record = answer.size();
					
			label.setText("Correct, get Ready for the next round");
			counter++;

			//clear all lists for new sequences and input.
			sequenceNums.clear();
			answer.clear();
			guess.clear();
			createSequence();
					

		       SequentialTransition sequence2 = newSequence();
		       sequence2.play();
		
		}
				else {
					Button yes = new Button("Yes");
					Button no = new Button("No");

				//Show Record and last correct guess length.
				if(counter != 0){
					label.setText("I'm sorry that is incorrect. Your\nhighest"
					+ " sequence entered correctly\nwas: " +  (counter + 3)
					+ "\n\nThe record sequence is: " + record+ "\nWould you like to play again?");
			}
				else{ label.setText("I'm sorry that is incorrect. You\nhave"
					+ " not entered a sequence\ncorrectly yet.\nThe record sequence is:\n "
					 + record+ "\nWould you like to play again?");
			}
						counter = 0;
						tilePane.getChildren().addAll(yes, no);
						pane.getChildren().add(tilePane);

						//Clear data and fire the start button Action Event
						yes.setOnAction( e->{ stackPane.getChildren().removeAll();
						borderPane.getChildren().removeAll();
						pane.getChildren().removeAll();
						answer.clear();
						guess.clear();
						pane.getChildren().removeAll(tilePane);
						tilePane.getChildren().removeAll(yes, no);
						createSequence();
						MemoryGame.btnStart.fire();
													});
						no.setOnAction(e ->{ System.exit(1);});
				}
		
	}

	private static SequentialTransition newSequence(){
		SequentialTransition sequence = new SequentialTransition();

		for (String nums : sequenceNums) {
			PauseTransition transition = new PauseTransition (Duration.millis(1000));
			transition.setOnFinished(evt -> label.setText(nums.toString()));
			label.setFont(Font.font("Verdana", 16));
			label.setTextFill(Color.NAVY);
			sequence.getChildren().add(transition);

		}
		return sequence;
	}
 
	}

