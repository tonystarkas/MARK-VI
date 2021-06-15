package GUI;

import Duombaze.CategoryUtils;
import Duombaze.FinanceUtils;
import SubKlases.CRUDAI;
import SubKlases.Kategorijos;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class KategorijosController implements Initializable {
    @FXML
    private Button pridKategorija;
    @FXML
    private Button istrKategorija;
    @FXML
    private Button redKategorija;
    @FXML
    private Button perzKategorijas;
    @FXML
    private Button back;
    @FXML
    private AnchorPane inkaras;
    @FXML
    private TextField tekstas;
    @FXML
    private Text balance;
    private CRUDAI crud;
    @FXML
    private ListView<Kategorijos> kategoriju_sarasas = new ListView<>();
    private Kategorijos kategorija;

    public CRUDAI getCrud() {
        return crud;
    }

    public void setCrud(CRUDAI crud) {
        this.crud = crud;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            reload();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void grizti(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Meniu.fxml"));
        Parent root = fxmlLoader.load();
        MeniuController meniuController = fxmlLoader.getController();
        meniuController.setCrud(crud);
        Stage stage = (Stage)  back.getScene().getWindow();
        stage.setTitle("Finance Management System");
        stage.setScene(new Scene(root));
        stage.show();
    }

    public void perziureti(ActionEvent actionEvent) {
        crud.Pamatyti_kategorijas();
    }

    public void prideti(ActionEvent actionEvent) throws IOException, SQLException, ClassNotFoundException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Kat_edit.fxml"));
        Stage stage = new Stage();
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        Kat_edit editor = fxmlLoader.getController();
        editor.setCrud(crud);
        editor.setActionType("Prideti");
        editor.setKategorija(kategorija);
        stage.setTitle("Pridejimas");
        stage.setScene(scene);
        stage.showAndWait();
        reload();
        kategoriju_sarasas.refresh();
    }

    public void istrinti(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        CategoryUtils.delete(kategorija.getId());
        reload();
        kategoriju_sarasas.refresh();
    }

    public void redaguoti(ActionEvent actionEvent) throws IOException, SQLException, ClassNotFoundException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Kat_edit.fxml"));
        Stage stage = new Stage();
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        Kat_edit editor = fxmlLoader.getController();
        editor.setCrud(crud);
        editor.setKategorija(kategorija);
        editor.setActionType("redaguoti");
        stage.setTitle("Redagavimas");
        stage.setScene(scene);
        stage.showAndWait();
        reload();
        kategoriju_sarasas.refresh();
    }
    public void reload() throws SQLException, ClassNotFoundException {
        kategoriju_sarasas.getItems().clear();
        List<Kategorijos> kat = null;
        try {
            kat = CategoryUtils.getAllCategories();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        kat.forEach(kateg -> kategoriju_sarasas.getItems().addAll(kateg));
        kategoriju_sarasas.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent click) {
                kategorija = kategoriju_sarasas.getSelectionModel().getSelectedItem();
                if(click.getClickCount() == 2){
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Finansai.fxml"));
                    Stage stage = new Stage();
                    Parent root = null;
                    try {
                        root = fxmlLoader.load();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    Scene scene = new Scene(root);
                    stage.setScene(scene);
                    FinansuController finansuController= fxmlLoader.getController();
                    try {
                        finansuController.setKategorijos_id(kategorija.getId());
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                    stage.setScene(scene);
                    stage.show();
                }
            }
        });
        double amount = 0;
        amount = FinanceUtils.getBalance();
        if(amount < 0)
            balance.setFill(Color.RED);
        else balance.setFill(Color.GREEN);
        balance.setText("Sistemos balansas: " + amount);
    }
}
