package AddressBookFile;

import javafx.application.Platform;
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

public class AddressController implements Initializable {
    private static final double BUTTON_HEIGHT=40.0;
    private static final double BUTTON_WIDTH = 120.0;

    @FXML
    private BorderPane root;

    private TextField firstName;

    private TextField lastName;

    private TextField city;

    private TextField postalCode;

    private ChoiceBox provinceChoice;

    private PersonalAddressLogger logger;
    private Alert alertMessage;

    public AddressController(){
        this.firstName = new TextField();
        this.lastName = new TextField();
        this.city = new TextField();
        this.postalCode = new TextField();
        this.root = new BorderPane();
        this.logger = new PersonalAddressLogger();

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
        provinceChoice = new ChoiceBox();
        provinceChoice.getItems().addAll("Ontario","Quebec","Alberta","Newfoundland and labrador","Prince Edward Island");
        provinceLabel.setLabelFor(provinceChoice);
        pane.getChildren().add(this.labelTextField(this.city,"City: ",TextField.DEFAULT_PREF_COLUMN_COUNT));
        pane.getChildren().addAll(provinceLabel,provinceChoice);
        pane.getChildren().add(this.labelTextField(this.postalCode,"Postal Code: ",TextField.DEFAULT_PREF_COLUMN_COUNT));
    }

    public HBox labelTextField(TextField tField, String labelText, int columnCount){
        HBox hb = new HBox(5);
        VBox vb = new VBox(5);
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

        addButton.setOnMouseClicked((e)-> Platform.runLater(()-> {
            String first = this.firstName.getText();
            String last = this.lastName.getText();
            Address add = new Address(this.city.getText(), (String) this.provinceChoice.getValue(), this.postalCode.getText());
            try{
                this.logger.add(new PersonalAddress(first,last,add));
            }catch (IOException ioe){
                ioe.printStackTrace();
            }

        }));
        pane.getChildren().addAll(addButton,firstButton,nextButton,prevButton,lastButton,updateButton);

    }

    public void closeResource(){
            this.logger.close();
    }
}
