/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.medicfast.controller;

import com.medicfast.model.TipoOcorrencia;
import com.medicfast.service.TipoOcorrenciaService;
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
 * @author Kennedy
 */
public class ConsTipoOcorrenciaController implements Initializable{
    @FXML
    private TableColumn colNome;

    @FXML
    private TextField txtNome;

    @FXML
    private Button btnExcluir;

    @FXML
    private TableColumn colId;

    @FXML
    private Button btnPesquisar;

    @FXML
    private Button btnNovo;

    @FXML
    private Button btnEditar;

    @FXML
    private TableView<TipoOcorrencia> table;
    
    private List<TipoOcorrencia> dados;
    private ObservableList<TipoOcorrencia> ocorrencias ;

    @FXML
    void novo(ActionEvent event) {
        CadTipoOcorrenciaController.setValida(Boolean.FALSE);
        TelaController tc = new TelaController();
        tc.inserirTela("CadTipoOcorrencia");
    }

    @FXML
    void editar(ActionEvent event) {
        TipoOcorrencia t;
        t = table.getSelectionModel().getSelectedItem();
        CadTipoOcorrenciaController ctc = new CadTipoOcorrenciaController();
        ctc.TelaEditar(t);
    }

    @FXML
    void excluir(ActionEvent event) {
        
        TipoOcorrencia t;
        TipoOcorrenciaService ts = new TipoOcorrenciaService();
        t = table.getSelectionModel().getSelectedItem();
        String msg = ts.excluir(t);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(msg);
        alert.showAndWait();
        inserirTabela();
    }
    
    @FXML
    void pesquisar(ActionEvent event){
        colNome.setCellValueFactory(new PropertyValueFactory<>("descricao"));
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        
        TipoOcorrenciaService ts = new TipoOcorrenciaService();
        dados = ts.buscarOcorrenciasPorNome(txtNome.getText());
        ocorrencias = FXCollections.observableArrayList(dados);
        table.setItems(ocorrencias);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        inserirTabela();
    }

    private void inserirTabela() {
        colNome.setCellValueFactory(new PropertyValueFactory<>("descricao"));
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        
        TipoOcorrenciaService ts = new TipoOcorrenciaService();
        dados = ts.buscarOcorrencias();
        ocorrencias = FXCollections.observableArrayList(dados);
        table.setItems(ocorrencias);
    }
}
