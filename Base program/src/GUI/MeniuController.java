package GUI;

import SubKlases.CRUDAI;
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

public class MeniuController implements Initializable {
    public Button kategorijos;
    public Button pajamos;
    public Button islaidos;
    public Button logout;
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

    public void toKategorijos(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Kategorijos.fxml"));
        Parent root = fxmlLoader.load();
        KategorijosController kategorijosController = fxmlLoader.getController();
        kategorijosController.setCrud(crud);
        Stage stage = (Stage)  kategorijos.getScene().getWindow();
        stage.setTitle("Finance Management System");
        stage.setScene(new Scene(root));
        stage.show();
    }

    public void logOut(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Main.fxml"));
        Parent root = fxmlLoader.load();
        MainController mainController = fxmlLoader.getController();
        mainController.setCrud(crud);
        Stage stage = (Stage)  logout.getScene().getWindow();
        stage.setTitle("Finance Management System");
        stage.setScene(new Scene(root));
        stage.show();
    }
}
