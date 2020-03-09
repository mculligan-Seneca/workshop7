package AddressBookFile;
/*
Student: Mitchell Culligan
email: mculligan@myseneca.ca
id: 161293170
professor: Mahboob Ali
date: March 13th 2020

 */
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.geometry.Insets;

import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.BorderPane;
import javafx.scene.control.ChoiceBox;

public class AddressBookController implements Initializable {
    private static final double BUTTON_HEIGHT=40.0;
    private static final double BUTTON_WIDTH = 120.0;
    private BorderPane root;
    private Button addButton;
    private TextField firstName;
    private TextField lastName;
    private TextField city;
    private ChoiceBox provinceChoice;
    private TextField postalCode;


    AddressBookController(){
        this.root = new BorderPane();
        this.addButton = new Button("Add");
        this.firstName = new TextField();

    }
    @Override
    public void initialize(URL url, ResourceBundle rb){
        BorderPane bp = new BorderPane();
        VBox vb = new VBox(10);
        HBox hb = new HBox(5);

        this.addNameFields(vb);
        BorderPane.setMargin(vb,new Insets(8,8,8,8));
        bp.setTop(vb);
        this.addLocateFields(hb);
        BorderPane.setMargin(hb,new Insets(8,8,8,8));
        bp.setCenter(hb);
        hb = new HBox();
        this.addButtons(hb);
        BorderPane.setMargin(hb,new Insets(20,8,8,8));
        bp.setBottom(hb);
    }


    public void addNameFields(Pane pane){

        this.labelTextField(pane,"First Name: ",50);
        this.labelTextField(pane,"Last Name: ",50);

    }

    public void addLocateFields(Pane pane){
        Label provinceLabel = new Label("Province: ");
        ChoiceBox provinceChoice = new ChoiceBox();
        provinceChoice.getItems().addAll("Ontario","Quebec","Alberta","Newfoundland and labrador","Prince Edward Island");
        provinceLabel.setLabelFor(provinceChoice);
        this.labelTextField(pane,"City: ",TextField.DEFAULT_PREF_COLUMN_COUNT);
        pane.getChildren().addAll(provinceLabel,provinceChoice);
        this.labelTextField(pane,"Postal Code: ",TextField.DEFAULT_PREF_COLUMN_COUNT);
    }

    public void labelTextField(Pane pane, String labelText, int columnCount){
        HBox hb = new HBox(5);
        //   VBox vb = new VBox(5);
        Label label = new Label(labelText);
        TextField tField = new TextField();
        label.setLabelFor(tField);
        tField.setPrefColumnCount(columnCount);
        hb.getChildren().addAll(label,tField);
        pane.getChildren().add(hb);

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
        pane.getChildren().addAll(addButton,firstButton,nextButton,prevButton,lastButton,updateButton);

    }
}
