package AddressBookFile;

import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.fxml.FXML;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.function.Consumer;

public class AddressController implements Initializable {
    private static final double BUTTON_HEIGHT=40.0;
    private static final double BUTTON_WIDTH = 120.0;

    @FXML
    private BorderPane root;

    private TextField firstName;

    private TextField lastName;

    private TextField city;

    private TextField postalCode;

    private ChoiceBox<String> provinceChoice;

    private PersonalAddressLogger logger;


    private class Validation {

        private String errMssge;


        private boolean validDataSize(String data, int size){
            return data.length()>0 && data.length()<=size
                    && data.indexOf(PersonalAddressLogger.DELIM)==-1;
        }


      public String getErrMssge(){
            return this.errMssge;
      }



        public boolean validatePerson(){
            boolean valid=false;

            if(this.validDataSize(firstName.getText(),PersonalAddressLogger.NAME_SIZE)&&
                    this.validDataSize(lastName.getText(),PersonalAddressLogger.NAME_SIZE)) {
                valid = this.validateAddress();
            }
            else this.errMssge="Invalid Name data";

            return valid;
        }

        public boolean validateAddress(){
            boolean valid = false;
             if(this.validDataSize(city.getText(),PersonalAddressLogger.CITY_SIZE)) {
                 if(this.validDataSize(provinceChoice.getValue(), PersonalAddressLogger.PROV_SIZE))
                         if(postalCode.getText().matches("[A-Za-z][0-9][A-Za-z][0-9][A-Za-z][0-9]"))
                             valid=true;
                         else
                             this.errMssge="Invalid Postal code";
                 else
                     this.errMssge="Invalid province data";
             }
             else
                 this.errMssge= "Invalid City data";

             return valid;
        }


    }
    private Validation validator;
    public AddressController(){
        this.firstName = new TextField();
        this.lastName = new TextField();
        this.city = new TextField();
        this.postalCode = new TextField();
        this.root = new BorderPane();
        this.logger = new PersonalAddressLogger();
        this.validator = new Validation();

    }

    @Override
    public void initialize(URL url, ResourceBundle resources){


        VBox vb = new VBox(10);
        HBox hb = new HBox(5);

        this.addNameFields(vb);
        BorderPane.setMargin(vb, new Insets(8, 8, 8, 8));
        this.root.setTop(vb);
        this.addLocateFields(hb);
        BorderPane.setMargin(hb, new Insets(8, 8, 8, 8));
        this.root.setCenter(hb);
        hb = new HBox();
        this.addButtons(hb);
        BorderPane.setMargin(hb, new Insets(20, 8, 8, 8));
        this.root.setBottom(hb);
    }



    public void addNameFields(Pane pane){

        pane.getChildren().add( this.labelTextField(this.firstName,"First Name: ",50));
        pane.getChildren().add(this.labelTextField(this.lastName,"Last Name: ",50));

    }

    public void addLocateFields(Pane pane){
        Label provinceLabel = new Label("Province: ");
        provinceChoice = new ChoiceBox<String>();
        provinceChoice.getItems().addAll("Ontario","Quebec","Alberta","Newfoundland and labrador","Prince Edward Island");
        provinceLabel.setLabelFor(provinceChoice);
        pane.getChildren().add(this.labelTextField(this.city,"City: ",TextField.DEFAULT_PREF_COLUMN_COUNT));
        pane.getChildren().addAll(provinceLabel,provinceChoice);
        pane.getChildren().add(this.labelTextField(this.postalCode,"Postal Code: ",TextField.DEFAULT_PREF_COLUMN_COUNT));
    }

    public HBox labelTextField(TextField tField, String labelText, int columnCount){
        HBox hb = new HBox(5);
        Label label = new Label(labelText);
        label.setLabelFor(tField);
        tField.setPrefColumnCount(columnCount);
        hb.getChildren().addAll(label,tField);
        return hb;

    }

    public void addButtons(Pane pane){
        Button addButton= new Button("Add"), firstButton = new Button("First"), nextButton = new Button("Next"),
                prevButton = new Button("Previous"), lastButton = new Button("Last"), updateButton = new Button("Update");
        addButton.setPrefSize(BUTTON_WIDTH,BUTTON_HEIGHT);
        firstButton.setPrefSize(BUTTON_WIDTH,BUTTON_HEIGHT);
        nextButton.setPrefSize(BUTTON_WIDTH,BUTTON_HEIGHT);
        prevButton.setPrefSize(BUTTON_WIDTH,BUTTON_HEIGHT);
        lastButton.setPrefSize(BUTTON_WIDTH,BUTTON_HEIGHT);
        updateButton.setPrefSize(BUTTON_WIDTH,BUTTON_HEIGHT);

        addButton.setOnMouseClicked((e)-> this.persistData((pa)-> {
            try {
                this.logger.add(pa);
                AddressController.showMessage("Record Added", Alert.AlertType.INFORMATION);
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }
        }));
        firstButton.setOnMouseClicked((e)-> this.setData(this.logger.getFirst()));

        nextButton.setOnMouseClicked((e)-> this.setData(this.logger.getNext()));

        prevButton.setOnMouseClicked((e)-> this.setData(this.logger.getPrevious()));
        lastButton.setOnMouseClicked((e)-> this.setData(this.logger.getLast()));

        updateButton.setOnMouseClicked((e)-> this.persistData((pa)-> {
            try {
                this.logger.update(pa);
                AddressController.showMessage("Record Updated", Alert.AlertType.INFORMATION);
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }
        }));
        pane.getChildren().addAll(addButton,firstButton,nextButton,prevButton,lastButton,updateButton);

    }

    public void closeResource(){
            this.logger.close();
    }


public void persistData(Consumer<PersonalAddress> operation){
    if(this.validator.validatePerson()) {
        String first = this.firstName.getText();
        String last = this.lastName.getText();
        Address add = new Address(this.city.getText(), this.provinceChoice.getValue(), this.postalCode.getText());
        PersonalAddress pa = new PersonalAddress(first, last, add);
        operation.accept(pa);
    }else
       AddressController.showMessage( this.validator.getErrMssge(), Alert.AlertType.ERROR);
}
public void setData(PersonalAddress personAddress){
    if(personAddress!=null){
        this.firstName.setText(personAddress.getFirstName());
        this.lastName.setText(personAddress.getLastName());
        this.city.setText(personAddress.getAddress().getCity());
        this.provinceChoice.setValue(personAddress.getAddress().getProv());
        this.postalCode.setText(personAddress.getAddress().getPostalCode());
    }
    else AddressController.showMessage("Could not retrieve Address", Alert.AlertType.ERROR);
}


public static void showMessage(String message, Alert.AlertType type){
    Alert alert = new Alert(type,message, ButtonType.CLOSE);
    alert.showAndWait()
            .filter(response-> response==ButtonType.CLOSE)
            .ifPresent(response -> alert.close());

}

}
