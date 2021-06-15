package GUI;

import SubKlases.CRUDAI;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {
    public Button toRegister;
    public Button toPrisijungti;
    private CRUDAI crud;

    public CRUDAI getCrud() {
        return crud;
    }

    public void setCrud(CRUDAI crud) {
        this.crud = crud;
    }

    public void openRegisterWindow(ActionEvent actionEvent) throws IOException {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Registracija.fxml"));
            Parent root = fxmlLoader.load();
            RegisterController registerController = fxmlLoader.getController();
            registerController.setCrud(crud);
            Stage stage = (Stage)  toRegister.getScene().getWindow();
            stage.setTitle("Finance Management System");
            stage.setScene(new Scene(root));
            stage.show();
    }

    public void openLoginWindow(ActionEvent actionEvent) throws IOException {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Prisijungimas.fxml"));
            Parent root = fxmlLoader.load();
            LoginController loginController = fxmlLoader.getController();
            loginController.setCrud(crud);
            Stage stage = (Stage)  toPrisijungti.getScene().getWindow();
            stage.setTitle("Finance Management System");
            stage.setScene(new Scene(root));
            stage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
