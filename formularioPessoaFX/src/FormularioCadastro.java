/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 *
 * @author Aluno
 */
public class FormularioCadastro extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        GridPane layout = FXMLLoader.load(getClass().getResource("FormularioCadastro.fxml"));

        Scene scene = new Scene(layout);
        scene.getStylesheets().add(getClass().getResource("estilo.css").toExternalForm());

        stage.setTitle("Formulário para cadastro de Pessoa");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
