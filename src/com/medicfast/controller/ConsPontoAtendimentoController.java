/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.medicfast.controller;

import com.medicfast.model.PontoAtendimento;
import com.medicfast.model.Usuario;
import com.medicfast.service.PontoAtendimentoService;
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
public class ConsPontoAtendimentoController implements Initializable {
    
    @FXML
    private TableColumn<?, ?> colNome;

    @FXML
    private TableColumn<?, ?> colRua;

    @FXML
    private TableColumn<?, ?> colTelefone;

    @FXML
    private Button btnNovo;
    
    @FXML
    private Button btnEditar;
    
    @FXML
    private Button btnExcluirMedicamento;
    
    @FXML
    private Button btnExcluirMedico;
    
    @FXML
    private TableColumn<?, ?> colComplemento;

    @FXML
    private TableColumn<?, ?> colBairro;

    @FXML
    private TextField txtNome;

    @FXML
    private TableColumn<?, ?> colNumero;

    @FXML
    private Button btnExcluir;

    @FXML
    private TableColumn<?, ?> colID;

    @FXML
    private Button btnPesquisar;
    
    
    @FXML
    private TableView<PontoAtendimento> table;
    
    private List<PontoAtendimento> dados;
    private ObservableList<PontoAtendimento> pontos ;
    PontoAtendimentoService pas = new PontoAtendimentoService();
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Usuario usuarioLogado = UsuarioLogado.getUsuarioLogado();
       
        if(usuarioLogado.getColaborador() == true || usuarioLogado.getAdministradorLocal() == true){
            btnEditar.setVisible(false);
            btnExcluir.setVisible(false);
            btnNovo.setVisible(false);
        }
        
        dados = pas.buscarPontos();
        inserirTabela();
    }
    
    @FXML
    public void editar(ActionEvent event) {
        PontoAtendimento pa;
        pa = table.getSelectionModel().getSelectedItem();
        CadPontoAtendimentoController cpa = new CadPontoAtendimentoController();
        cpa.Editar(pa);
    }
    
    @FXML
    public void excluir(ActionEvent event) {
        PontoAtendimento pa;
        pa = table.getSelectionModel().getSelectedItem();
        
        String msg =pas.excluir(pa);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(msg);
        alert.showAndWait();
        dados = pas.buscarPontos();
        inserirTabela();
    }


    @FXML
    public void novo(ActionEvent event) {
        CadPontoAtendimentoController.setValida(Boolean.FALSE);
        TelaController tc = new TelaController();
        tc.inserirTela("CadPontoAtendimento");
    }
    
    @FXML
    public void pesquisar(ActionEvent event) {
        
        dados = pas.buscarPontoPorNome(txtNome.getText());
        inserirTabela();
        txtNome.setText(null);
    }
    
    public void inserirTabela(){
        
        colID.setCellValueFactory(new PropertyValueFactory<>("id"));
        colNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        colRua.setCellValueFactory(new PropertyValueFactory<>("rua"));
        colBairro.setCellValueFactory(new PropertyValueFactory<>("bairro"));
        colNumero.setCellValueFactory(new PropertyValueFactory<>("numero"));
        colComplemento.setCellValueFactory(new PropertyValueFactory<>("complemento"));
        colTelefone.setCellValueFactory(new PropertyValueFactory<>("telefone"));
        
        pontos = FXCollections.observableArrayList(dados);
        table.setItems(pontos);
        
    }
    
}
