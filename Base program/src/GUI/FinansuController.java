package GUI;

import Duombaze.CategoryUtils;
import Duombaze.FinanceUtils;
import SubKlases.CRUDAI;
import SubKlases.Finansai;
import SubKlases.Kategorijos;
import com.sun.prism.paint.Paint;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;


public class FinansuController{
    private int kategorijos_id;
    private int finansu_id;
    private CRUDAI crud;
    @FXML
    private Button grizti;
    @FXML
    private Text balance;
    @FXML
    private ListView<Finansai> finansu_sarasas = new ListView<>();
    private Finansai finansai;

    public CRUDAI getCrud() {
        return crud;
    }

    public void setCrud(CRUDAI crud) {
        this.crud = crud;
    }

    private void reload() throws SQLException, ClassNotFoundException {
        finansu_sarasas.getItems().clear();
        List<Finansai> fin = null;
        try {
            fin = FinanceUtils.getFinances(kategorijos_id);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        fin.forEach(f -> finansu_sarasas.getItems().addAll(f));
        finansu_sarasas.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent click) {
                finansai = finansu_sarasas.getSelectionModel().getSelectedItem();
            }
        });
        double amount = FinanceUtils.getBalanceById(kategorijos_id);
        if(amount < 0)
            balance.setFill(Color.RED);
        else balance.setFill(Color.GREEN);
        balance.setText("Sistemos balansas: " + amount);

    }
    public void setKategorijos_id(int kategorijos_id) throws SQLException, ClassNotFoundException {
        this.kategorijos_id = kategorijos_id;
        reload();
    }

    public void setFinansu_id(int finansu_id) {
        this.finansu_id = finansu_id;
    }

    public void prideti(ActionEvent actionEvent) throws IOException, SQLException, ClassNotFoundException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("FinanceEditor.fxml"));
        Stage stage = new Stage();
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        Fin_edit editor = fxmlLoader.getController();
        editor.setFinansas(finansai);
        editor.setActionType("Prideti");
        editor.setKategorijos_id(kategorijos_id);
        stage.setTitle("Pridejimas");
        stage.setScene(scene);
        stage.showAndWait();
        reload();
        finansu_sarasas.refresh();
    }

    public void istrinti(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        FinanceUtils.delete(finansai.getId());
        reload();
        finansu_sarasas.refresh();
    }

    public void redaguoti(ActionEvent actionEvent) throws IOException, SQLException, ClassNotFoundException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("FinanceEditor.fxml"));
        Stage stage = new Stage();
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        Fin_edit editor = fxmlLoader.getController();
        editor.setFinansas(finansai);
        editor.setActionType("Redaguoti");
        editor.setKategorijos_id(kategorijos_id);
        stage.setTitle("Redagavimas");
        stage.setScene(scene);
        stage.showAndWait();
        reload();
        finansu_sarasas.refresh();
    }

    public void tokategorijos(ActionEvent actionEvent){
        Stage stage = (Stage) grizti.getScene().getWindow();
        stage.close();
    }
}
