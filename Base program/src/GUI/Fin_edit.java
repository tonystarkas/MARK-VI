package GUI;

import Duombaze.CategoryUtils;
import Duombaze.FinanceUtils;
import SubKlases.CRUDAI;
import SubKlases.Finansai;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class Fin_edit implements Initializable {
    public TextField finName;
    public ChoiceBox finBox;
    public TextField finAmount;
    public Button doneBtn;
    public Button cancelBtn;
    private Finansai finansas;
    private String actionType;
    private int kategorijos_id;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<String> fin_type = FXCollections.observableArrayList("islaidos", "pajamos");
        finBox.setItems(fin_type);
        finBox.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override public void changed(ObservableValue<? extends String> selected, String old_type, String new_type) {
            }
        });
        finBox.setValue("islaidos");
        finBox.setValue("pajamos");

    }

    public void setKategorijos_id(int kategorijos_id) {
        this.kategorijos_id = kategorijos_id;
    }

    public void setFinansas(Finansai finansas) {
        this.finansas = finansas;
    }
    public void setActionType(String actionType) {
        this.actionType = actionType;
        if(actionType.equals("Redaguoti")){
            finName.setText(finansas.getPavadinimas());
            finAmount.setText(String.valueOf(finansas.getSuma()));
        }
    }
    public void complete(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        if(actionType.equals("Prideti")){
            double amount = 0;
            try{
                amount = Double.parseDouble(finAmount.getText());
            }
            catch(Exception exc){
                exc.printStackTrace();
            }
            if(finBox.getValue().equals("pajamos")) {
                FinanceUtils.add(finName.getText(), amount, kategorijos_id);
            }
            else{
                FinanceUtils.add(finName.getText(), amount*(-1), kategorijos_id);
            }
        }
        else{
            double amount = 0;
            try{
                amount = Double.parseDouble(finAmount.getText());
            }
            catch(Exception exc){
                exc.printStackTrace();
            }
            if(finBox.getValue().equals("pajamos")) {
                if(amount < 0){
                    amount *= -1;
                }
                FinanceUtils.update(finName.getText(), amount, finansas.getId());
            }
            else{
                if(amount < 0){
                    FinanceUtils.update(finName.getText(), amount, finansas.getId());
                }
                else{
                    FinanceUtils.update(finName.getText(), amount*(-1), finansas.getId());
                }
            }
        }
        Stage currentStage = (Stage) doneBtn.getScene().getWindow();
        currentStage.close();
    }

    public void cancelAction(ActionEvent actionEvent) throws IOException {
        Stage currentStage = (Stage) doneBtn.getScene().getWindow();
        currentStage.close();
    }
}
