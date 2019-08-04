import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.stream.IntStream;

public class Controller implements Initializable {
//    Creating game board.
    private char [][] gameBoard = new char [3][3];

    private int turn = 1;

    private int numberOfTimesPlayerXWon, numberOfTimesPlayerYWon = 0;

    @FXML
    private Label systemMsg;

    @FXML
    private Label playerXScore;

    @FXML
    private Label playerYScore;

    @FXML
    private Button button1;

    @FXML
    private Button button2;

    @FXML
    private Button button3;

    @FXML
    private Button button4;

    @FXML
    private Button button5;

    @FXML
    private Button button6;

    @FXML
    private Button button7;

    @FXML
    private Button button8;

    @FXML
    private Button button9;

    @FXML
    void onbuttonClicked(ActionEvent event) {
        //IF turn is = EVEN, then it's player X's time to play, else if turn is = ODD, then it's player O's time to play.
        Button btn  = (Button) event.getSource();
        if (btn.getText().equals("")){
            if (this.getTurn() % 2 == 0){

                move(event, 'X', gameBoard);
                systemMsg.setText("Player O's Turn To Play.");
                this.setTurn();
            }else{
                move(event, 'O', gameBoard);
                systemMsg.setText("Player X's Turn To Play.");
                this.setTurn();
            }
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("Initializing Game Board.");
        initializeGameBoard(gameBoard);
    }

// Custom methods.
    private int getTurn() {
        return turn;
    }

    private void setTurn() {
        this.turn = this.turn + 1;
    }


    private void move(ActionEvent event, char move, char [][] gameBoard){
        Button btn  = (Button) event.getSource();

        if(btn.getText().equals("")) {
            writeMoveToGameBoard(btn, move, gameBoard);
        }
        else {systemMsg.setText("Player O's Turn To Play.\nInvalid Move.");}

        if(checkIfBoardIsFull()) {
            /*RESTART GAME.
            * 1.Record Draw.
            * 2.Send message that game resulted in a draw.
            * 3.Reset matrix game board and fxml game board.
            * 4.*/
//            Print Congratulatory Message.
            printCongratulatoryMessage("draw");

            initializeGameBoard(gameBoard);

//            Clear Fxml Game Board.
            clearGameBoard();
        }
    }

    private void writeMoveToGameBoard(Button btn, char move, char [][] gameBoard){
        btn.setText(String.valueOf(move));
        String buttonId = btn.getId();
        switch (buttonId){
            case ("button1"):
                gameBoard[0][0] = move;
                break;
            case ("button2"):
                gameBoard[0][1] = move;
                break;
            case ("button3"):
                gameBoard[0][2] = move;
                break;
            case ("button4"):
                gameBoard[1][0] = move;
                break;
            case ("button5"):
                gameBoard[1][1] = move;
                break;
            case ("button6"):
                gameBoard[1][2] = move;
                break;
            case ("button7"):
                gameBoard[2][0] = move;
                break;
            case ("button8"):
                gameBoard[2][1] = move;
                break;
            case ("button9"):
                gameBoard[2][2] = move;
                break;
        }
        displayGameBoardMatrix(gameBoard);
        if (checkGameResult()){
            updateScore();
            initializeGameBoard(gameBoard);
            clearGameBoard();
            systemMsg.setText("Game over");
        }
    }

    private void initializeGameBoard(char [][] gameBoard) {
        for (int i = 0; i < gameBoard[0].length; i++) {
//            Iterating through the columns.
            for (int j = 0; j < gameBoard.length; j++) {
//                Iterating through the rows.
                gameBoard[i][j] = ' ';
            }
        }
    }

    private void displayGameBoardMatrix(char [][] gameBoard){
        for (int i = 0; i < gameBoard[0].length; i++) {
//            Iterating through the columns.
            for (int j = 0; j < gameBoard.length; j++) {
//                Iterating through the rows.
                System.out.print(gameBoard[i][j] + " | ");
            }
            System.out.println();
        }
    }

    private boolean checkRow(){
        for (int i = 0; i < gameBoard[0].length; i++) {
            if (String.valueOf(gameBoard[i][0]).equals(String.valueOf(gameBoard[i][1])) && String.valueOf(gameBoard[i][2]).equals(String.valueOf(gameBoard[i][0])) && !String.valueOf(gameBoard[i][0]).equals(" ")){
                if (gameBoard[i][0] == 'X'){
                    numberOfTimesPlayerXWon++;
                }else{
                    numberOfTimesPlayerYWon++;
                }
                return true;
            }
        }
        return false;
    }

    private boolean checkCol(){
        for (int i = 0; i < gameBoard[0].length; i++) {
            if (String.valueOf(gameBoard[0][i]).equals(String.valueOf(gameBoard[1][i])) && String.valueOf( gameBoard[2][i]).equals(String.valueOf(gameBoard[0][i]))  && !String.valueOf(gameBoard[0][i]).equals(" ")){
                if (gameBoard[0][i] == 'X'){
                    numberOfTimesPlayerXWon++;
                }else{
                    numberOfTimesPlayerYWon++;
                }
                return true;
            }
        }
        return false;
    }

    private boolean checkDiagonal(){
        if (String.valueOf(gameBoard[0][0]).equals(String.valueOf(gameBoard[1][1])) && String.valueOf( gameBoard[0][0]).equals(String.valueOf(gameBoard[2][2])) && !String.valueOf(gameBoard[1][1]).equals(" ")){
            if (gameBoard[0][0] == 'X'){
                numberOfTimesPlayerXWon++;
            }else{
                numberOfTimesPlayerYWon++;
            }
            return true;
        }
        if (String.valueOf(gameBoard[0][2]).equals(String.valueOf(gameBoard[1][1])) && String.valueOf(gameBoard[0][2]).equals(String.valueOf(gameBoard[2][0])) && !String.valueOf(gameBoard[1][1]).equals(" ")){
            if (gameBoard[0][2] == 'X'){
                numberOfTimesPlayerXWon++;
            }else{
                numberOfTimesPlayerYWon++;
            }
            return true;
        }

        return false;
    }

    private boolean checkGameResult(){
        return (checkCol() || checkRow() || checkDiagonal());
    }

    private void updateScore(){
        playerXScore.setText(String.valueOf(numberOfTimesPlayerXWon));
        playerYScore.setText(String.valueOf(numberOfTimesPlayerYWon));
    }

    private void clearGameBoard(){
        button1.setText("");
        button2.setText("");
        button3.setText("");
        button4.setText("");
        button5.setText("");
        button6.setText("");
        button7.setText("");
        button8.setText("");
        button9.setText("");
    }

    private void printCongratulatoryMessage(String condition){
        if (condition.equals("win")){
            System.out.println("Congratulations you won. \uD83D\uDE4C\uD83D\uDE4C\uD83D\uDE4C\uD83D\uDE4C\uD83D\uDE4C");
        }else if (condition.equals("draw")){
            System.out.println("You drew, try again.");
        }else{
            System.out.println("You lost, try again.");
        }
    }

    private boolean checkIfBoardIsFull(){
        for (int i = 0; i < gameBoard.length; i++) {
            for (int j = 0; j < gameBoard[0].length; j++) {
                if (String.valueOf(gameBoard[i][j]).equals(" ")){
                    return false;
                }
            }
        }
        return true;
    }
}
