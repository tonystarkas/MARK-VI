package GUI;

import Duombaze.UserUtils;
import SubKlases.CRUDAI;
import com.company.Main;
import com.sun.javafx.scene.control.behavior.PasswordFieldBehavior;
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

public class RegisterController implements Initializable {
    public TextField regName;
    public PasswordField regPass;
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

    public void contReg(ActionEvent actionEvent) throws IOException, SQLException, ClassNotFoundException {
        UserUtils.createUser(regName.getText(), regPass.getText());
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Informacija!");
        alert.setHeaderText(null);
        alert.setContentText("Sėkmingai sukurta!");
        alert.showAndWait();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Main.fxml"));
        Parent root = fxmlLoader.load();
        MainController mainController = fxmlLoader.getController();
        mainController.setCrud(crud);
        Stage stage = (Stage)  cont.getScene().getWindow();
        stage.setTitle("Finance Management System");
        stage.setScene(new Scene(root));
        stage.show();
    }
}
