/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.medicfast.controller;

import com.medicfast.model.Especialidade;
import com.medicfast.service.EspecialidadeService;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 *
 * @author Samsung Ultra
 */
public class ConsEspecialidadeController implements Initializable{
    
    @FXML
    private TableColumn colNome;

    @FXML
    private TextField txtNome;

    @FXML
    private Button btnExcluir;

    @FXML
    private TableColumn colID;

    @FXML
    private Button btnPesquisar;

    @FXML
    private Button btnEditar;

    @FXML
    private Button btnNovo;

    @FXML
    private TableView<Especialidade> table;

    private List<Especialidade> dados;
    private ObservableList<Especialidade> especialidades ;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        inserirTabela();
        
    }
    
     @FXML
    void editar(ActionEvent event) {
        Especialidade e;
        e = table.getSelectionModel().getSelectedItem();
        CadEspecialidadeController cec = new CadEspecialidadeController();
        cec.TelaEditar(e);
    }

    @FXML
    void excluir(ActionEvent event) {

        Especialidade e ;
        EspecialidadeService es = new EspecialidadeService();
        e = table.getSelectionModel().getSelectedItem();
        String msg = es.excluir(e);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(msg);
        alert.showAndWait();
        inserirTabela();
    }

    @FXML
    void novo(ActionEvent event) {
        CadEspecialidadeController.setValida(Boolean.FALSE);
        TelaController tc = new TelaController();
        tc.inserirTela("CadEspecialidade");
    }
    
    @FXML
    void pesquisar(ActionEvent event) {
        EspecialidadeService es = new EspecialidadeService();
        colNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        colID.setCellValueFactory(new PropertyValueFactory<>("id"));
        
        dados = es.buscarPorNome(txtNome.getText());
        especialidades = FXCollections.observableArrayList(dados);
        table.setItems(especialidades);
    }
    
    private void inserirTabela(){
        colNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        colID.setCellValueFactory(new PropertyValueFactory<>("id"));
        
        EspecialidadeService es = new EspecialidadeService();
        dados = es.buscarEspecialidades();
        especialidades = FXCollections.observableArrayList(dados);
        table.setItems(especialidades);
    }
}
