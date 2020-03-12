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
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.control.Label;
import javafx.stage.Stage;


import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {


    @FXML
    private BorderPane root;

    private GridPane board;

    private Label gameText;


    public Controller(){
        this.root= new BorderPane();
        this.board = new GridPane();
        this.gameText = new Label("");
    }


    @Override
    public void initialize(URL url, ResourceBundle rb){
        this.drawBoard();
        this.setMessage();
        this.board.setBackground(new Background(new BackgroundFill(Color.BLUE, CornerRadii.EMPTY, Insets.EMPTY)));

        BorderPane.setMargin(this.gameText,new Insets(8,8,8,8));
        this.root.setTop(this.gameText);
        BorderPane.setMargin(this.board, new Insets(8,8,8,8));
        this.root.setCenter(this.board);
        this.root.setBackground(new Background(new BackgroundFill(Color.BLACK,CornerRadii.EMPTY,Insets.EMPTY)));
    }
    public void setMessage(){
        if(!ConnectGame.getGame().isDraw()) {
            this.gameText.setText(ConnectGame.getGame().gameMessage());
            this.gameText.setTextFill(ConnectGame.getGame().getCurrentColor());
        }
        else{
            this.gameText.setText("DRAW");
            this.gameText.setTextFill(Color.BLACK);
        }
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
                circle.setOnMouseClicked((e)->{
                    //  Node source = (Node)e.getSource();
                    Node source = (Node)e.getSource();
                    int row= GridPane.getRowIndex(source);
                    int column = GridPane.getColumnIndex(source);

                    Color tileColor = ConnectGame.getGame().getCurrentColor();
                    if(ConnectGame.getGame().move(row,column)){
                        this.setPlayerTile((Circle)source, tileColor);
                        //this.board.setColumnIndex(tile,column);
                        //this.board.setRowIndex(tile,row);
                        // this.board.getChildren().add(tile);
                        this.setMessage();
                        if(ConnectGame.getGame().isOver())
                            this.addWinScreen();


                    }
                });
                board.add(circle,j,i);
            }
        }
    }




    public void addWinScreen(){
        Stage stage = new Stage();
        Scene scene;
        Label lbl = new Label(this.gameText.getText());
        lbl.setTextFill(this.gameText.getTextFill());
        lbl.setStyle("-fx-font-size: 20px;");

        Group g = new Group();
        g.getChildren().add(lbl);
        scene = new Scene(g);
        scene.setFill(Color.BLACK);
        stage.setTitle("Winning screen");
        stage.setScene(scene);
        stage.show();

    }


}
