
package com.medicfast.controller;

import com.medicfast.model.Medicamento;
import com.medicfast.model.Medico;
import com.medicfast.model.PontoAtendimento;
import com.medicfast.service.MedicamentoService;
import com.medicfast.service.MedicoService;
import com.medicfast.service.PontoAtendimentoService;
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
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;


public class CadPontoAtendimentoController implements Initializable {
    
     @FXML
    private TableColumn<?, ?> colEspecialidade;

    @FXML
    private TableColumn<?, ?> colNomeMedicamento;

    @FXML
    private TextField txtNome;

    @FXML
    private TextField txtTelefone;

    @FXML
    private TableView<Medicamento> tblMedicamento;

    @FXML
    private Button btnConsultar;

    @FXML
    private TableColumn<?, ?> colIdMedicamento;

    @FXML
    private TextField txtNumero;

    @FXML
    private TextField txtRua;

    @FXML
    private Button btnAdicionarMedicamento;

    @FXML
    private Button btnSalvar;

    @FXML
    private TableColumn<?, ?> colNomeMedico;

    @FXML
    private TableColumn<?, ?> colIdMedico;

    @FXML
    private TextField txtComplemento;

    @FXML
    private ComboBox<Medicamento> slcMedicamento;

    @FXML
    private Button btnAdicionarMedico;

    @FXML
    private ComboBox<Medico> slcMedico;

    @FXML
    private TableColumn<?, ?> colQuantidade;

    @FXML
    private Button btnLimpar;

    @FXML
    private TableView<Medico> tblMedico;

    @FXML
    private TextField txtBairro;
    
    private  List<Medico> medicos = new ArrayList<>();
    private  List<Medicamento> medicamentoList = new ArrayList<>();
    
    private ObservableList<Medicamento> medicamentoOb;
    private ObservableList<Medico> medicoOb;
    private static boolean valida = false;
    private static PontoAtendimento pa;
    private static ObservableList<Medico> medicosSelecionados;
    private static ObservableList<Medicamento> medicamentosSelecionados;
    public Set<Medicamento> setExcluirMedicamento;
    public Set<Medico> setExcluirMedico;

    public static boolean isValida() {
        return valida;
    }

    public static void setValida(boolean valida) {
        CadPontoAtendimentoController.valida = valida;
    }
    
    
    
    @FXML
    void limpar(ActionEvent event) {
        txtBairro.setText(null);
        txtComplemento.setText(null);
        txtNome.setText(null);
        txtNumero.setText(null);
        txtRua.setText(null);
        txtTelefone.setText(null);
        
        // ------- Limpando tabela medico ----
        medicos.removeAll(medicos);
        medicoOb = FXCollections.observableArrayList(medicos);
        tblMedico.getItems().clear();
        tblMedico.getItems().addAll(medicoOb);
        
        // ------- Limpando tabela medicamento ----
        medicamentoList.removeAll(medicamentoList);
        medicamentoOb = FXCollections.observableArrayList(medicamentoList);
        tblMedicamento.getItems().clear();
        tblMedicamento.getItems().addAll(medicamentoOb);
        
        // ------- Limpando Combobox ----
        slcMedicamento.getSelectionModel().clearSelection();
        slcMedico.getSelectionModel().clearSelection();
        
        valida = false;
        
    }

    @FXML
    void consultar(ActionEvent event) {
        TelaController tc = new TelaController();
        tc.inserirTela("ConsPonto");
    }
    @FXML
    void AdicionarMedico(ActionEvent event) {
        
        Medico m;
        m = slcMedico.getValue();
        
        medicos.add(m);
        
        colIdMedico.setCellValueFactory(new PropertyValueFactory<>("id"));
        colNomeMedico.setCellValueFactory(new PropertyValueFactory<>("nome"));
        colEspecialidade.setCellValueFactory(new PropertyValueFactory<>("especialidade"));
        medicoOb = FXCollections.observableArrayList(medicos);

        tblMedico.setItems(medicoOb);
        
    }

    @FXML
    void AdicionarMedicamento(ActionEvent event) {
        Medicamento m;
        m = slcMedicamento.getValue();
        
        medicamentoList.add(m);
        
        colIdMedicamento.setCellValueFactory(new PropertyValueFactory<>("id"));
        colNomeMedicamento.setCellValueFactory(new PropertyValueFactory<>("nome"));
        colQuantidade.setCellValueFactory(new PropertyValueFactory<>("quantidade"));
        medicamentoOb = FXCollections.observableArrayList(medicamentoList);

        tblMedicamento.setItems(medicamentoOb);
    }

    @FXML
    void excluirMedico(ActionEvent event) {
        // instaniando uma noval lista para copiar as informações da lista prod
        medicosSelecionados = FXCollections.observableArrayList(medicos);

        // pegando as linhas selecionadas na tela
        setExcluirMedico = new HashSet(tblMedico.getSelectionModel().getSelectedItems());
        
        // removendo as linhas que foram selecionadas na tela da lista itensSelecionados
        medicosSelecionados.removeAll(setExcluirMedico);

        // limpando as informações da tableview
        tblMedico.getItems().clear();
        
        // sobrescrevendo as informações da lista prod com as informaçoes da itensSelecioados
        medicos = FXCollections.observableArrayList(medicosSelecionados);
        
        //Inserindo a lista prod dentro da table view
        tblMedico.getItems().addAll(medicos);
    }

    @FXML
    void excluirMedicamento(ActionEvent event) {
        // instaniando uma noval lista para copiar as informações da lista prod
        medicamentosSelecionados = FXCollections.observableArrayList(medicamentoList);

        // pegando as linhas selecionadas na tela
        setExcluirMedicamento = new HashSet(tblMedicamento.getSelectionModel().getSelectedItems());
        
        // removendo as linhas que foram selecionadas na tela da lista itensSelecionados
        medicamentosSelecionados.removeAll(setExcluirMedicamento);

        // limpando as informações da tableview
        tblMedicamento.getItems().clear();
        
        // sobrescrevendo as informações da lista prod com as informaçoes da itensSelecioados
        medicamentoList = FXCollections.observableArrayList(medicamentosSelecionados);
        
        //Inserindo a lista prod dentro da table view
        tblMedicamento.getItems().addAll(medicamentoList);
    }
    
    @FXML
    public void Salvar (ActionEvent event){
        if(valida==false){
            PontoAtendimento pt = new PontoAtendimento();
            pt.setNome(txtNome.getText());
            pt.setBairro(txtBairro.getText());
            pt.setComplemento(txtComplemento.getText());
            pt.setNumero(txtNumero.getText());
            pt.setTelefone(txtTelefone.getText());
            pt.setRua(txtRua.getText());
            pt.setMedico(medicos);
            pt.setMedicamento(medicamentoList);

            PontoAtendimentoService pontoService = new PontoAtendimentoService() ;
            String msg;
            msg = pontoService.salvar(pt);

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(msg);
            alert.showAndWait();
            
            TelaController tc = new TelaController();
            tc.inserirTela("CadPontoAtendimento");
        }else{
            pa.setNome(txtNome.getText());
            pa.setBairro(txtBairro.getText());
            pa.setComplemento(txtComplemento.getText());
            pa.setNumero(txtNumero.getText());
            pa.setTelefone(txtTelefone.getText());
            pa.setRua(txtRua.getText());
            pa.setMedico(medicos);
            pa.setMedicamento(medicamentoList);

            PontoAtendimentoService pontoService = new PontoAtendimentoService() ;
            String msg;
            msg = pontoService.salvar(pa);

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(msg);
            alert.showAndWait();
        
            pa = new PontoAtendimento();
            
            TelaController tc = new TelaController();
            tc.inserirTela("ConsPonto");
        }
    }
    
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if(valida==false){
            alimentarSelect();
        
        }else{
            txtBairro.setText(pa.getBairro());
            txtComplemento.setText(pa.getComplemento());
            txtNome.setText(pa.getNome());
            txtNumero.setText(pa.getNumero());
            txtRua.setText(pa.getRua());
            txtTelefone.setText(pa.getTelefone());
                    
            medicamentoList = pa.getMedicamento();
            System.out.println("Medicamento ---"+pa.getMedicamento().size());
            
            colIdMedicamento.setCellValueFactory(new PropertyValueFactory<>("id"));
            colNomeMedicamento.setCellValueFactory(new PropertyValueFactory<>("nome"));
            colQuantidade.setCellValueFactory(new PropertyValueFactory<>("quantidade"));
            
            medicamentoOb = FXCollections.observableArrayList(medicamentoList);
            tblMedicamento.setItems(medicamentoOb);
            
            medicos = pa.getMedico();
            colIdMedico.setCellValueFactory(new PropertyValueFactory<>("id"));
            colNomeMedico.setCellValueFactory(new PropertyValueFactory<>("nome"));
            colEspecialidade.setCellValueFactory(new PropertyValueFactory<>("especialidade"));
            System.out.println("Medico ---"+pa.getMedico().size());
            medicoOb = FXCollections.observableArrayList(medicos);
            tblMedico.setItems(medicoOb);
            
            alimentarSelect();
            
        }
    }
    
    public void Editar(PontoAtendimento p){
        valida=true;
        pa = p;
        System.out.println("Editar Cad");
        TelaController tc = new TelaController();
        tc.inserirTela("CadPontoAtendimento");
    }
    
    public void alimentarSelect(){
         MedicamentoService mts = new MedicamentoService();
         ObservableList<Medicamento> mdt = FXCollections.observableArrayList(mts.buscarMedicamentos());
         slcMedicamento.setItems(mdt);

         MedicoService ms = new MedicoService();
         ObservableList<Medico> mdo = FXCollections.observableArrayList(ms.buscarTodosOsMedicos());
         slcMedico.setItems(mdo);
    }
}
