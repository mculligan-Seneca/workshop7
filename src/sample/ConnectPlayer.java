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


public enum ConnectPlayer {
    RED(Color.RED,"Red Player"),
    YELLOW(Color.YELLOW,"Yellow Player");

    private final Color PLAYER_COLOR;
    private final String PLAYER;
    ConnectPlayer(Color color,String player){
        this.PLAYER_COLOR= color;
        this.PLAYER= player;
    }

    public final Color getPlayerColor(){
        return this.PLAYER_COLOR;
    }
    @Override
    public String toString(){
        return this.PLAYER;
    }

}
