/*
Student: Mitchell Culligan
email: mculligan@myseneca.ca
id: 161293170
professor: Mahboob Ali
date: Feb 29th 2020
 */

package AddressBookFile;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Parent;

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
