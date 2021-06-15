package GUI;

import Duombaze.UserUtils;
import SubKlases.CRUDAI;
import SubKlases.Klientas;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class LoginController implements Initializable {
    public TextField loginName;
    public PasswordField logPass;
    public Button cont;
    private CRUDAI crud;

    public CRUDAI getCrud() {
        return crud;
    }

    public void setCrud(CRUDAI crud) {
        this.crud = crud;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void log(ActionEvent actionEvent) throws IOException, SQLException {
        Klientas tempuser = UserUtils.empLogin(loginName.getText(), logPass.getText());
        if(tempuser != null){
            crud.setPrisijunges_asmuo(tempuser.getVartotojo_vardas());
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Meniu.fxml"));
            Parent root = fxmlLoader.load();
            MeniuController meniuController = fxmlLoader.getController();
            meniuController.setCrud(crud);
            Stage stage = (Stage)  cont.getScene().getWindow();
            stage.setTitle("Finance Management System");
            stage.setScene(new Scene(root));
            stage.show();
        }
        else{
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Informacija!");
            alert.setHeaderText(null);
            alert.setContentText("Tokio vartotojo nėra.");
            alert.showAndWait();
        }
    }
}
