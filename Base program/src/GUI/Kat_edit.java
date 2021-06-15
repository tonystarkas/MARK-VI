package GUI;

import Duombaze.CategoryUtils;
import SubKlases.CRUDAI;
import SubKlases.Kategorijos;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.SQLException;

public class Kat_edit {
    @FXML
    private Button cancelBtn;
    @FXML
    private Button doneBtn;
    @FXML
    private TextField katName;
    private String actionType;
    private CRUDAI crud;
    private Kategorijos kategorija;

    public void setKategorija(Kategorijos kategorija) {
        this.kategorija = kategorija;
    }

    public void setCrud(CRUDAI crud) {
        this.crud = crud;
    }

    public void setActionType(String actionType) {
        this.actionType = actionType;
        if(actionType.equals("redaguoti")){
            katName.setText(kategorija.getPavadinimas());
        }
    }

    public void complete(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        if(actionType.equals("Prideti")){
            CategoryUtils.add(katName.getText(), crud.getPrisijunges_asmuo());
        }
        else{
            CategoryUtils.update(katName.getText(), kategorija.getId());
        }
        Stage currentStage = (Stage) doneBtn.getScene().getWindow();
        currentStage.close();
    }

    public void cancelAction(ActionEvent actionEvent) {
        Stage currentStage = (Stage) cancelBtn.getScene().getWindow();
        currentStage.close();
    }
}
