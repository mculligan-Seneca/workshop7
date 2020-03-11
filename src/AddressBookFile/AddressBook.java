/*
Student: Mitchell Culligan
email: mculligan@myseneca.ca
id: 161293170
professor: Mahboob Ali
date: Feb 29th 2020
 */

package AddressBookFile;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.BorderPane;
import javafx.scene.Parent;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
public class AddressBook extends Application {


    //private  BorderPane bp;


    //@Override
    //public void init(){


    //}
    private FXMLLoader loader;
    @Override
    public void start(Stage  priorityStage)throws Exception{
        Scene scene;


           // Thread.setDefaultUncaughtExceptionHandler(AddressBook::showError);
            this.loader = new FXMLLoader(getClass().getResource("AddressBookFile.fxml"));
            Parent root = this.loader.load();
        //        this.logger = new PersonalAddressLogger();

        //BorderPane bp = new BorderPane();

            priorityStage.setResizable(false);
            priorityStage.setTitle("Address Book");

            scene = new Scene(root);
            priorityStage.setScene(scene);
            priorityStage.show();



    }


    @Override
    public void stop(){
        ((AddressController)this.loader.getController()).closeResource();
    }


    public static void main(String... args){
        launch(args);
    }



}
