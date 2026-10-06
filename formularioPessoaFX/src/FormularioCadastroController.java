/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

/**
 *
 * @author Aluno
 */
public class FormularioCadastroController implements Initializable {

    @FXML
    private TextField txtCpf;
    @FXML
    private TextField txtNome;
    @FXML
    private TextField txtEndereco;
    @FXML
    private ComboBox<String> cmbEstado;
    @FXML
    private ComboBox<String> cmbCargo;
    @FXML
    private Button btnImprimir;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObservableList<String> estados = FXCollections.observableArrayList(
                "Bahia", "Minas Gerais", "São Paulo", "Rio de Janeiro");
        ObservableList<String> cargos = FXCollections.observableArrayList(
                "Gerente de Marketing", "Programador", "Analista", "Desenvolvedor");

        cmbEstado.setItems(estados);
        cmbEstado.getSelectionModel().selectFirst();

        cmbCargo.setItems(cargos);
        cmbCargo.getSelectionModel().selectFirst();

        btnImprimir.setOnAction(evento -> {
            Alert alerta = new Alert(Alert.AlertType.INFORMATION);
            alerta.setTitle("Mensagem");
            alerta.setHeaderText(null);
            alerta.setContentText(
                    "Cpf: " + txtCpf.getText()
                    + "\nNome: " + txtNome.getText()
                    + "\nEndereço: " + txtEndereco.getText()
                    + "\nEstado: " + cmbEstado.getValue()
                    + "\nCargo: " + cmbCargo.getValue());
            alerta.showAndWait();
        });
    }
}
