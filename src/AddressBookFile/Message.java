/*
Stduent: Mitchell Culligan
Workshop 7
id: 1616293170
email: mculligan@myseneca.ca
Professor: Mahboob Ali
Date: March 13th, 2020
 */
package AddressBookFile;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

public class Message {

    public static void showMessage(String message, Alert.AlertType type){
        Alert alert = new Alert(type,message, ButtonType.CLOSE);
        alert.showAndWait()
                .filter(response-> response==ButtonType.CLOSE)
                .ifPresent(response -> alert.close());

    }
}
