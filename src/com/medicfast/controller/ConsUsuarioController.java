
package com.medicfast.controller;

import com.medicfast.model.Usuario;
import com.medicfast.service.UsuarioService;
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


public class ConsUsuarioController implements Initializable{
    
    @FXML
    private TableColumn colNome;
    
    @FXML
    private TableColumn colClassificacao;
    
    @FXML
    private Button btnSalvar;

    @FXML
    private TableColumn colUsuario;

    @FXML
    private TextField txtNome;

    @FXML
    private Button btnExcluir;

    @FXML
    private TableColumn colID;

    @FXML
    private Button btnPesquisar;
    
    @FXML
    private Button btnNovo;
    @FXML
    private TableView<Usuario> table;

    private List<Usuario> dados;
    private ObservableList<Usuario> usuarios ;
    
    @FXML
    void novo(ActionEvent event){
        TelaController tela = new TelaController();
        tela.inserirTela("CadUsuario");
    }
    @FXML
    void excluir(ActionEvent event) {
        Usuario u;
        u = table.getSelectionModel().getSelectedItem();
        UsuarioService us = new UsuarioService();
        String msg =us.excluir(u);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(msg);
        alert.showAndWait();
        inserirTabela();
    }

    @FXML
    void editar(ActionEvent event) {
        Usuario u;
        u = table.getSelectionModel().getSelectedItem();
        CadUsuarioController cua = new CadUsuarioController();
        cua.TelaEditar(u);
    }
    
     @FXML
    void pesquisar(ActionEvent event) {
        UsuarioService us = new UsuarioService();
        colUsuario.setCellValueFactory(new PropertyValueFactory<>("usuario"));
        colID.setCellValueFactory(new PropertyValueFactory<>("id"));
        dados = us.buscarNome(txtNome.getText());
        
        usuarios = FXCollections.observableArrayList(dados);
        table.setItems(usuarios);
    }
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        inserirTabela();
    }
    
    public void inserirTabela(){
        colNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        colUsuario.setCellValueFactory(new PropertyValueFactory<>("usuario"));
        colID.setCellValueFactory(new PropertyValueFactory<>("id"));
        colClassificacao.setCellValueFactory(new PropertyValueFactory<>("descricaoTipoUsuario"));
        
        
        UsuarioService us = new UsuarioService();
        dados = us.buscarUsuarios();
        
        usuarios = FXCollections.observableArrayList(dados);
        table.setItems(usuarios);
    }
    
}
