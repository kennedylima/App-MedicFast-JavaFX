
package com.medicfast.controller;

import com.medicfast.model.Especialidade;
import com.medicfast.model.Medico;
import com.medicfast.service.EspecialidadeService;
import com.medicfast.service.MedicoService;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;


public class CadMedicoController implements Initializable{
    
     @FXML
    private Button btnAdicionar;

    @FXML
    private TextField txtEndereco;

    @FXML
    private TextArea txtInfo;

    @FXML
    private TextField txtNome;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtTelefone;

    @FXML
    private Button btnConsultar;

    @FXML
    private TextField txtCRM;

    @FXML
    private TableColumn<?, ?> colNome;

    @FXML
    private Button btnSalvar;

    @FXML
    private ComboBox<Especialidade> slcEspecialidade;

    @FXML
    private TableColumn<?, ?> colId;

    @FXML
    private Button btnLimpar;

    @FXML
    private TableView table;
    
    private ObservableList<Especialidade> dados;
    
    private List<Especialidade> especialidades = new ArrayList<>();
    
    private static Medico medico;
    private static Boolean valida = false;
    public Set<Especialidade> setExcluirEspecialidade;
    public static ObservableList<Especialidade> especialidadeSelecionada;

    public static Boolean getValida() {
        return valida;
    }

    public static void setValida(Boolean valida) {
        CadMedicoController.valida = valida;
    }


    @FXML
    void Limpar(ActionEvent event) {        
        limpar();
         
    }

    @FXML
    void Salvar(ActionEvent event) {
        if(valida==false){
            Medico m = new Medico();
            m.setNome(txtNome.getText());
            m.setCRM(txtCRM.getText());
            m.setEmail(txtEmail.getText());
            m.setEndereco(txtEndereco.getText());           
            m.setTelefone(txtTelefone.getText());
            m.setInformacao(txtInfo.getText());
            
            especialidades = table.getItems();
            m.setEspecialidade(especialidades);

            MedicoService ms = new MedicoService();
            String msg = ms.salvar(m);

            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setHeaderText(msg);
            alert.showAndWait();
            
            limpar();
        
        }else {
            Medico m = medico;
            m.setNome(txtNome.getText());
            m.setCRM(txtCRM.getText());
            m.setEmail(txtEmail.getText());
            m.setEndereco(txtEndereco.getText());           
            m.setTelefone(txtTelefone.getText());
            m.setInformacao(txtInfo.getText());
            
            especialidades = table.getItems();
            m.setEspecialidade(especialidades);

            MedicoService ms = new MedicoService();
            String msg = ms.salvar(m);

            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setHeaderText(msg);
            alert.showAndWait();
            
            limpar();
        }
    
    }

    @FXML
    void Adicionar(ActionEvent event) {
        Especialidade e = new Especialidade();
        e = slcEspecialidade.getValue();
        especialidades.add(e);
        inserirDadosNaTabela();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if(valida == true){
            txtNome.setText(medico.getNome());
            txtCRM.setText(medico.getCRM());
            txtEmail.setText(medico.getEmail());
            txtEndereco.setText(medico.getEndereco());
            txtInfo.setText(medico.getInformacao());
            txtTelefone.setText(medico.getTelefone());
            especialidades = medico.getEspecialidade();
            
            
            
            TelaController tc = new TelaController();
            btnConsultar.setOnAction(event -> tc.inserirTela("ConsMedico"));
            slcEspecialidade.setItems(EspecialidadeService.listarTodos());
            
            inserirDadosNaTabela();
        
        }else{
            TelaController tc = new TelaController();
            btnConsultar.setOnAction(event -> tc.inserirTela("ConsMedico"));
            slcEspecialidade.setItems(EspecialidadeService.listarTodos());
        }
    }
    
    
    void TelaEditar(Medico m) {
        medico = m;
        valida = true;
        TelaController tc = new TelaController();
        tc.inserirTela("CadMedico");
    }
    
    public void limpar(){
        txtCRM.setText(null);
        txtEmail.setText(null);
        txtEndereco.setText(null);
        txtInfo.setText(null);
        txtNome.setText(null);
        txtTelefone.setText(null);
        slcEspecialidade.getSelectionModel().clearSelection();
        
        especialidades.removeAll(especialidades);
        especialidadeSelecionada = FXCollections.observableArrayList(especialidades);
        table.getItems().clear();
        table.getItems().addAll(especialidadeSelecionada);
    }
    public void inserirDadosNaTabela(){
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        dados = FXCollections.observableArrayList(especialidades);

        table.setItems(dados);
    }

    @FXML
    public void excluirEspecialidade(ActionEvent event){
        // instanciando uma nova lista para copiar as informações 
        especialidadeSelecionada = FXCollections.observableArrayList(especialidades);

        // pegando as linhas selecionadas na tela
        setExcluirEspecialidade = new HashSet(table.getSelectionModel().getSelectedItems());
        
        // removendo as linhas que foram selecionadas na tela da lista especialidadeSelecionada
        especialidadeSelecionada.removeAll(setExcluirEspecialidade);

        // limpando as informações da tableview
        table.getItems().clear();
        
        // sobrescrevendo as informações da lista prod com as informaçoes da itensSelecioados
        especialidades = FXCollections.observableArrayList(especialidadeSelecionada);
        
        //Inserindo a lista prod dentro da table view
        table.getItems().addAll(especialidades);
    }
    
    @FXML
    public void consultar(ActionEvent e){
        TelaController tela = new TelaController();
        tela.inserirTela("ConsMedico");
    }
    
}
