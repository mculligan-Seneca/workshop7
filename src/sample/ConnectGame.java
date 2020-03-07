/*
Stduent: Mitchell Culligan
Workshop 7
id: 1616293170
email: mculligan@myseneca.ca
Professor: Mahboob Ali
Date: March 13th, 2020
 */
package sample;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;


public class ConnectGame {

    private ConnectPlayer currentPlayer;
    private ConnectPlayer nonActivePlayer;
    private ConnectBoard gameBoard;
    private boolean isWon;
    private boolean draw;

    public ConnectGame(){
        this.currentPlayer=ConnectPlayer.RED;
        this.nonActivePlayer= ConnectPlayer.YELLOW;
        this.gameBoard = new ConnectBoard();
        this.isWon = false;
        this.draw = false;
    }



    public boolean isOver(){
        return this.isWon || this.draw;
    }

    public void playerSwap(){
        ConnectPlayer hold = this.currentPlayer;
        this.currentPlayer= this.nonActivePlayer;
        this.nonActivePlayer= hold;
    }
    public boolean move(int r, int c){
        boolean completed = false;
        if(!this.isOver()){
            if(this.gameBoard.isPlayable(r,c)){
                this.gameBoard.addTile(r, c, this.currentPlayer);
                if(!this.checkWon(r,c) && !this.checkDraw()) {
                    this.playerSwap();
                }
                completed = true;

            }
        }
        return completed;
    }
    public boolean isDraw(){
        return this.gameBoard.isFull();
    }
    public String gameMessage(){
        String message=this.getCurrentPlayer();
        if(this.isOver() && !this.draw)
            message += "wins!";
        else
            message += "'s Turn";
        return message;
    }
    public String getCurrentPlayer(){
        return this.currentPlayer.toString();
    }
    public Color getCurrentColor(){
        return this.currentPlayer.getPlayerColor();
    }
    public Color getNonActiveColor(){ return this.nonActivePlayer.getPlayerColor();}

    private boolean checkDraw(){
        if(this.gameBoard.isFull() && !isWon)
            this.draw=true;
        return this.draw;
    }
    private boolean checkWon(int r, int c){

        this.horizontalCheck(r,c);
        if(!this.isWon) {
            this.verticalCheck(r,c);
            if(!this.isWon){
                this.diagonalCheck(r,c);
            }
        }
        return this.isWon;

    }

    private void horizontalCheck(int r,int c){
            int count = 0;
            for(int i=0;i<4&&i+c<ConnectBoard.BOARD_COLUMN_NUM && count<=4;i++){
                if(this.gameBoard.getTileAt(r,i+c)==this.currentPlayer)
                    count++;
            }
        for(int i=1;i<4 && c-i>=0 && count<=4;i++){
            if(this.gameBoard.getTileAt(r,c-i)==this.currentPlayer)
                count++;
            else
                break;
        }
            if(count==4)this.isWon=true;

    }

   private void verticalCheck(int r, int c){
        int count=0;
        for(int i=0;i<4&&i+r<ConnectBoard.BOARD_ROW_NUM && count<=4;i++){
           if(this.gameBoard.getTileAt(i+r,c)!=this.currentPlayer)break;
           else
               count++;
       }
       for(int i=1;i<4 && r-i>=0 && count<=4;i++){
           if(this.gameBoard.getTileAt(r-i,c)!=this.currentPlayer)break;
           else
               count++;
       }
       if(count==4)this.isWon=true;

   }

    private void diagonalCheck(int r,int c){
        int count=0;
        for(int i=0;i<4 &&i+r<ConnectBoard.BOARD_ROW_NUM && c+i<ConnectBoard.BOARD_COLUMN_NUM && count<=4;i++){
            if(this.gameBoard.getTileAt(r+i,i+c)!=this.currentPlayer)break;
            else
                count++;
        }
        for(int i=1;i<4 && r-i>=0&& c-i >=0 && count<=4;i++){
            if(this.gameBoard.getTileAt(r-i,c-i)!=this.currentPlayer)break;
            else
                count++;
        }

        if(count==4)this.isWon=true;

    }

}
