/*
Stduent: Mitchell Culligan
Workshop 7
id: 1616293170
email: mculligan@myseneca.ca
Professor: Mahboob Ali
Date: March 13th, 2020
 */
package sample;

import javafx.application.Application;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.Parent;
import javafx.stage.Stage;


public class Connect4UI extends Application {
    public static final double TILE_RADIUS=50.0;


    @Override
    public void start(Stage primaryStage) throws Exception{
        Scene scene;
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));

        primaryStage.setTitle("Connect Four");
        primaryStage.setResizable(false);


        scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
    }





    public static void main(String[] args) {
        launch(args);
    }
}
