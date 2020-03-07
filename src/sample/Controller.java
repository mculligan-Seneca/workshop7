/*
Stduent: Mitchell Culligan
Workshop 7
id: 1616293170
email: mculligan@myseneca.ca
Professor: Mahboob Ali
Date: March 13th, 2020
 */
package sample;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    private ConnectGame game;
    @FXML
    private BorderPane root;
    @FXML
    private GridPane board;
    @FXML
    private Label gameText;


    public Controller(){
        this.game = new ConnectGame();
        this.root= new BorderPane();
        this.board = new GridPane();
        this.gameText = new Label("");


    }


    @Override
    public void initialize(URL url, ResourceBundle rb){
        this.drawBoard();
        this.setMessage();
        this.addBoardEvent();
        this.board.setBackground(new Background(new BackgroundFill(Color.BLUE, CornerRadii.EMPTY, Insets.EMPTY)));

        BorderPane.setMargin(this.gameText,new Insets(8,8,8,8));
        this.root.setTop(this.gameText);
        BorderPane.setMargin(this.board, new Insets(8,8,8,8));
        this.root.setCenter(this.board);
    }
    public void setMessage(){
        if(!this.game.isDraw()) {
            this.gameText.setText(this.game.gameMessage());
            this.gameText.setTextFill(this.game.getCurrentColor());
        }
        else{
            this.gameText.setText("DRAW");
            this.gameText.setTextFill(Color.BLACK);
        }
    }
    public boolean gameMove(int r, int c){

       return this.game.move(r,c);

    }

    public void setPlayerTile(Circle tile,Color color){
        tile.setFill(color);
    }

    
    public void drawBoard(){
        Circle circle;
        this.board.setHgap(5.0);
        this.board.setVgap(5.0);
        for(int i=0; i<ConnectBoard.BOARD_ROW_NUM;i++){
            for(int j=0;j<ConnectBoard.BOARD_COLUMN_NUM;j++){
                circle = new Circle(Connect4UI.TILE_RADIUS);
                circle.setStyle("-fx-fill: white; -fx-stroke: black;");
                board.add(circle,j,i);
            }
        }
    }
    public void addBoardEvent(){
        this.board.getChildren().forEach((tile)->{
         tile.setOnMouseClicked((e)->{
              //  Node source = (Node)e.getSource();
                Node source = (Node)e.getSource();
                int row= GridPane.getRowIndex(source);
                int column = GridPane.getColumnIndex(source);
                Color tileColor = this.game.getCurrentColor();
                if(this.gameMove(row,column)){
                    this.setPlayerTile((Circle)source, tileColor);
                    //this.board.setColumnIndex(tile,column);
                    //this.board.setRowIndex(tile,row);
                    // this.board.getChildren().add(tile);
                    this.setMessage();

                }
          });
        });
    }
}
