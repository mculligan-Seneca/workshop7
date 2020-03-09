package AddressBookFile;/*
Student: Mitchell Culligan
email: mculligan@myseneca.ca
id: 161293170
professor: Mahboob Ali
date: March 13th 2020
 */
import javafx.application.Application;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class AddressBook extends Application {


    public void start(Stage  priorityStage){
        Scene scene;

        try {
            Parent root = FXMLLoader.load(getClass().getResource("AddressBookFile.fxml"));

            priorityStage.setResizable(false);
            priorityStage.setTitle("Address Book");

            scene = new Scene(root);
            priorityStage.setScene(scene);
            priorityStage.show();
        }catch(IOException ioe){
            ioe.printStackTrace();
        }


    }














}
