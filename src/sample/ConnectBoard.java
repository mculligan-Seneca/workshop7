/*
Stduent: Mitchell Culligan
Workshop 7
id: 1616293170
email: mculligan@myseneca.ca
Professor: Mahboob Ali
Date: March 13th, 2020
 */
package sample;

public class ConnectBoard {
    public static final int BOARD_COLUMN_NUM=7;
    public static final int BOARD_ROW_NUM = 6;
    public static final int BOARD_SIZE= BOARD_COLUMN_NUM*BOARD_ROW_NUM;
    private ConnectPlayer[][] board;
    private int fullTiles;
    public ConnectBoard(){
        this.board = new ConnectPlayer[BOARD_ROW_NUM][BOARD_COLUMN_NUM];
        this.fullTiles=0;
    }

    public boolean isPlayable(int r,int c){

        return this.board[r][c]==null && (r + 1 < BOARD_ROW_NUM?this.board[r+1][c]!=null: true);
    }

    public void addTile(int r, int c, ConnectPlayer tile){
        if(this.isPlayable(r,c)) {
            this.board[r][c] = tile;
            this.fullTiles++;
        }
    }

    public boolean isFull(){
        return this.fullTiles==BOARD_SIZE;
    }

    public ConnectPlayer getTileAt(int r, int c){
        return this.board[r][c];
    }
}
