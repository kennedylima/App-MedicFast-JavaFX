/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.medicfast.controller;

import com.medicfast.model.Medicamento;
import com.medicfast.model.Usuario;
import com.medicfast.service.MedicamentoService;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 *
 * @author Samsung Ultra
 */
public class ConsMedicamentoController implements Initializable{
    
    @FXML
    private ComboBox<?> slcFabricante;

    @FXML
    private TextField txtNome;

    @FXML
    private Button btnPesquisar;

    @FXML
    private ComboBox slcConsulta;

    @FXML
    private TableColumn<?, ?> colNome;

    @FXML
    private Label lbFabricante;
    
    @FXML
    private Label lblConsultaPor;

    @FXML
    private Button btnEditar;

    @FXML
    private TableColumn<?, ?> colFabricante;

    @FXML
    private TableColumn<?, ?> colPeso;

    @FXML
    private TableColumn<?, ?> colID;

    @FXML
    private Button btnExcluir;

    @FXML
    private TableColumn<?, ?> colQuantidade;

    @FXML
    private TableView<Medicamento> table;

    @FXML
    private Button btnNovo;

    private List<Medicamento> dados;
    private List<Medicamento> FiltroDados;
    private ObservableList<Medicamento> usuarios ;
    private boolean pesq;
    
    @FXML
    public void acao(ActionEvent event) {
        String acao = slcConsulta.getSelectionModel().getSelectedItem().toString();
        
        if(acao=="Nome"){
            txtNome.setVisible(true);
            btnPesquisar.setVisible(true);
            btnNovo.setVisible(true);
            btnEditar.setVisible(true);
            btnExcluir.setVisible(true);
            txtNome.setPromptText("Digite o Nome do Medicamento ...");
            pesq = true;
            lbFabricante.setVisible(false);
            slcFabricante.setVisible(false);
        
        }else if(acao=="Fabricante"){
            pesq = false;
            lbFabricante.setVisible(false);
            slcFabricante.setVisible(false);
            btnPesquisar.setVisible(true);
            txtNome.setPromptText("Digite o Nome do Fabricante...");
            btnNovo.setVisible(true);
            btnEditar.setVisible(true);
            btnExcluir.setVisible(true);
            txtNome.setVisible(true);
        }
    }
    
    @FXML
    void pesquisar(ActionEvent event) {
        MedicamentoService ms = new MedicamentoService();
        Usuario usuarioLogado = UsuarioLogado.getUsuarioLogado();
        if(pesq == true){
            String txt = txtNome.getText();
            if(usuarioLogado.getAdministradorLocal() == true || usuarioLogado.getColaborador()== true){
                dados = ms.buscarPorNome(txt,usuarioLogado);
            }else{
                dados = ms.buscarPorNome(txt);
            }
        }else {
            String txt = txtNome.getText();
           
            if(usuarioLogado.getAdministradorLocal() == true || usuarioLogado.getColaborador()== true){
                dados = ms.buscarPorFabricante(txt,usuarioLogado);
                System.out.println("Fabricante" + txt);
            }else{
                dados = ms.buscarPorFabricante(txt);
            }
        }
        inserirTabela();
    }
    
     @FXML
    void novo(ActionEvent event) {
        CadMedicamentoController.setValida(Boolean.FALSE);
        TelaController tc = new TelaController();
        tc.inserirTela("CadMedicamento");
    }

    @FXML
    void excluir(ActionEvent event) {
        Medicamento m ;
        MedicamentoService ms = new MedicamentoService();
        m = table.getSelectionModel().getSelectedItem();
        String msg = ms.excluir(m);
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setHeaderText(msg);
        alert.showAndWait();
        
        dados = ms.buscarMedicamentos();
        inserirTabela();
    }

    @FXML
    void editar(ActionEvent event) {
        Medicamento m;
        m = table.getSelectionModel().getSelectedItem();
        CadMedicamentoController cmc = new CadMedicamentoController();
        cmc.TelaEditar(m);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Usuario usuarioLogado = UsuarioLogado.getUsuarioLogado();
        
        if(usuarioLogado.getColaborador()== true){
            slcConsulta.setVisible(false);
            lbFabricante.setVisible(false);
            lblConsultaPor.setVisible(false);
            slcFabricante.setVisible(false);
            txtNome.setVisible(false);
            btnPesquisar.setVisible(false);
            btnNovo.setVisible(false);
            btnEditar.setVisible(false);
            btnExcluir.setVisible(false);
            
            MedicamentoService ms = new MedicamentoService();
            dados = ms.buscarMedicamentos(usuarioLogado);
        
        }else if(usuarioLogado.getAdministradorLocal()== true){
            slcConsulta.setItems(FXCollections.observableArrayList("Nome","Fabricante"));

            lbFabricante.setVisible(false);
            slcFabricante.setVisible(false);
            txtNome.setVisible(false);
            btnPesquisar.setVisible(false);
            btnNovo.setVisible(true);
            btnEditar.setVisible(true);
            btnExcluir.setVisible(true);

            MedicamentoService ms = new MedicamentoService();
            dados = ms.buscarMedicamentos(usuarioLogado);
        
        }
        else if (usuarioLogado.getAdministradorGeral()== true){
            slcConsulta.setItems(FXCollections.observableArrayList("Nome","Fabricante"));

            lbFabricante.setVisible(false);
            slcFabricante.setVisible(false);
            txtNome.setVisible(false);
            btnPesquisar.setVisible(false);
            btnNovo.setVisible(true);
            btnEditar.setVisible(true);
            btnExcluir.setVisible(true);

            MedicamentoService ms = new MedicamentoService();
            dados = ms.buscarMedicamentos();
        }
        
        inserirTabela();
        
    }
    
    public void inserirTabela(){
        colNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        colFabricante.setCellValueFactory(new PropertyValueFactory<>("fabricante"));
        colPeso.setCellValueFactory(new PropertyValueFactory<>("peso"));
        colQuantidade.setCellValueFactory(new PropertyValueFactory<>("quantidade"));
        colID.setCellValueFactory(new PropertyValueFactory<>("id"));
        
        usuarios = FXCollections.observableArrayList(dados);
        table.setItems(usuarios);
        txtNome.setText(null);
    }
    
    
    
}
