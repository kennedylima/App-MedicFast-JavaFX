
package com.medicfast.controller;

import com.medicfast.DAO.MedicoDAO;
import com.medicfast.entity.SessionEntity;
import com.medicfast.model.Especialidade;
import com.medicfast.model.Medicamento;
import com.medicfast.model.Medico;
import com.medicfast.model.PontoAtendimento;
import com.medicfast.model.Usuario;
import com.medicfast.service.EspecialidadeService;
import com.medicfast.service.MedicamentoService;
import com.medicfast.service.MedicoService;
import com.medicfast.service.PontoAtendimentoService;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;


public class ConsMedicoController implements Initializable {
    @FXML
    private TableColumn colTelefone;

    @FXML
    private TableColumn colEspecialidade;

    @FXML
    private TableColumn colEndereco;
    
    @FXML
    private TableColumn colAtendendo;
    
    @FXML
    private TextField txtNome;

    @FXML
    private Button btnPesquisar;

    @FXML
    private TableColumn colEmail;

    @FXML
    private ComboBox slcConsulta;

    @FXML
    private TableColumn colNome;

    @FXML
    private Button btnEditar;

    @FXML
    private ComboBox slcPonto;

    @FXML
    private ComboBox slcEspecialidade;

    @FXML
    private Button btnExcluir;

    @FXML
    private TableColumn colID;

    @FXML
    private TableView<Medico> table;
    
    @FXML
    private Button btnNovo;
    
    @FXML
    private Label lbPonto;
     
    @FXML
    private Label lbEspecialidade;
    
    @FXML
    private Label lblConsultarPor;
    
    @FXML
    private Button btnEspecialidade;
    
    @FXML
    private Button btnRemoverPlantao;
    
    @FXML
    private Button btnPlantao;
    
    private List<Medico> dados;
    private List<Medico> ListaMedicoPlantao;
    private ArrayList<List<String>> especialidadeList = new ArrayList<List<String>>();
    private ObservableList<Medico> medicos ;
    public Set<Medico> setMedicoPlantao;
    private ObservableList<Especialidade> especialidadeMedica ;
    private ObservableList<Medico> medicoDePlantao;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Usuario usuarioLogado = UsuarioLogado.getUsuarioLogado();
        
        if(usuarioLogado.getColaborador()== true  ){
            slcConsulta.setVisible(false) ;
            lbPonto.setVisible(false);
            slcPonto.setVisible(false);
            slcEspecialidade.setVisible(false);
            lbEspecialidade.setVisible(false);
            txtNome.setVisible(false);
            btnPesquisar.setVisible(false);
            lblConsultarPor.setVisible(false);
            btnEditar.setVisible(false);
            btnNovo.setVisible(false);
            btnExcluir.setVisible(false);
            btnPlantao.setVisible(true);
            btnRemoverPlantao.setVisible(true);
            MedicoService ms = new MedicoService();
            dados = ms.buscarMedico(UsuarioLogado.getUsuarioLogado());
        }
        else if(usuarioLogado.getAdministradorLocal()== true  ){
            slcConsulta.setVisible(false) ;
            lbPonto.setVisible(false);
            slcPonto.setVisible(false);
            slcEspecialidade.setVisible(false);
            lbEspecialidade.setVisible(false);
            txtNome.setVisible(false);
            btnPesquisar.setVisible(false);
            lblConsultarPor.setVisible(false);
            btnEditar.setVisible(false);
            btnNovo.setVisible(true);
            btnExcluir.setVisible(true);
            MedicoService ms = new MedicoService();
            dados = ms.buscarMedico(UsuarioLogado.getUsuarioLogado());
        }
        else{
            lbPonto.setVisible(false);
            slcPonto.setVisible(false);
            slcEspecialidade.setVisible(false);
            lbEspecialidade.setVisible(false);
            txtNome.setVisible(false);
            btnPesquisar.setVisible(false);            
            btnPlantao.setVisible(false);
            btnRemoverPlantao.setVisible(false);
            MedicoService ms = new MedicoService();
            dados = ms.buscarTodosOsMedicos();
            slcConsulta.setItems(FXCollections.observableArrayList("Nome","Ponto de Atendimento","Especialidade"));

            PontoAtendimentoService pas = new PontoAtendimentoService();
            ObservableList<PontoAtendimento> ponto = FXCollections.observableArrayList(pas.buscarPontos());
            slcPonto.setItems(ponto);

            EspecialidadeService es = new EspecialidadeService();
            ObservableList<Especialidade> e = FXCollections.observableArrayList(es.buscarEspecialidades());
            slcEspecialidade.setItems(e);
        }
        
        inserirTabela();
        
    }
    
    
    @FXML 
    public void acao(ActionEvent e){
        String opcao = slcConsulta.getSelectionModel().getSelectedItem().toString();
        
        if(opcao=="Nome"){
            txtNome.setVisible(true);
            lbPonto.setVisible(false);
            slcPonto.setVisible(false);
            slcEspecialidade.setVisible(false);
            lbEspecialidade.setVisible(false);
            btnPesquisar.setVisible(true);
            
        }else if(opcao=="Ponto de Atendimento"){
            txtNome.setVisible(false);
            slcPonto.setVisible(true);
            lbPonto.setVisible(true);            
            slcEspecialidade.setVisible(false);
            lbEspecialidade.setVisible(false);
            btnPesquisar.setVisible(true);
            
        }else if (opcao=="Especialidade"){
            txtNome.setVisible(false);
            lbPonto.setVisible(false);
            slcPonto.setVisible(false);
            slcEspecialidade.setVisible(true);
            lbEspecialidade.setVisible(true);
            btnPesquisar.setVisible(true);
        }
        
    }
    
    @FXML
    public void excluir(ActionEvent event) {
        Medico m ;
        MedicoService ms = new MedicoService();
        m = table.getSelectionModel().getSelectedItem();
        String msg = ms.excluir(m);
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setHeaderText(msg);
        alert.showAndWait();
        
        TelaController tc = new TelaController();
        tc.inserirTela("ConsMedico");
    }
    
    @FXML
    void novo(ActionEvent event) {
        CadMedicoController.setValida(Boolean.FALSE);
        TelaController tc = new TelaController();
        tc.inserirTela("CadMedico");
    }
    
    @FXML
    void pesquisar(ActionEvent event){
        String opcao = slcConsulta.getSelectionModel().getSelectedItem().toString();
        MedicoService ms = new MedicoService();
        
        if(opcao=="Nome"){
            dados = ms.buscarMedicoPorNome(txtNome.getText());
            inserirTabela();
            
        }else if(opcao=="Ponto de Atendimento"){
            txtNome.getText();
            dados = ms.buscarMedicoPorPonto((PontoAtendimento)slcPonto.getSelectionModel().getSelectedItem());
            inserirTabela();
            
        }else if (opcao=="Especialidade"){
            dados = ms.buscarMedicoPorEspecialidade((Especialidade)slcEspecialidade.getSelectionModel().getSelectedItem());
            inserirTabela();
        }
    }
    
    @FXML
    void inserirNoPlantao(ActionEvent event) {
        setMedicoPlantao = new HashSet(table.getSelectionModel().getSelectedItems());
        medicoDePlantao = FXCollections.observableArrayList(setMedicoPlantao);
        MedicoService ms = new MedicoService();
        boolean especialidadeDisponivelParaExercerDuranteOPlantao = false;
        
        for (Medico m : medicoDePlantao) {
            
            for(int i =0; i<m.getEspecialidade().size(); i++){
                if(m.getAtendendoComo().getNome().equals(m.getEspecialidade().get(i).getNome())){
                    especialidadeDisponivelParaExercerDuranteOPlantao =true;
                }
            }
            if(especialidadeDisponivelParaExercerDuranteOPlantao == true ){
                    especialidadeDisponivelParaExercerDuranteOPlantao=false;
                    m.setLocalAtendimento(UsuarioLogado.getUsuarioLogado().getPontoAtendimento().getId());
                    String msg = ms.salvar(m);
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setHeaderText(msg);
                    alert.showAndWait();
            }else{
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setHeaderText("O Dr(a) "+m.getNome()+" Não é "+m.getAtendendoComo());
                    alert.showAndWait();
            }
        }
    }
    
    @FXML
    public void removerDoPlantao(){
        setMedicoPlantao = new HashSet(table.getSelectionModel().getSelectedItems());
        medicoDePlantao = FXCollections.observableArrayList(setMedicoPlantao);
        String msg = "";
        
        for(Medico m: medicoDePlantao){
            m.setLocalAtendimento(null);
            m.setAtendendoComo(null);
            
            MedicoService ms = new MedicoService();
             msg = ms.salvar(m);
            
        }
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(msg);
            alert.showAndWait();
            inserirTabela();
    }
    
    @FXML
    public void editar(){
        Medico m;
        m = table.getSelectionModel().getSelectedItem();
        CadMedicoController cmc = new CadMedicoController();
        cmc.TelaEditar(m);
    }
    
    public void inserirTabela(){
        
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colEndereco.setCellValueFactory(new PropertyValueFactory<>("endereco"));
        colEspecialidade.setCellValueFactory(new PropertyValueFactory<>("especialidade"));
        colNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        colTelefone.setCellValueFactory(new PropertyValueFactory<>("telefone"));
        colID.setCellValueFactory(new PropertyValueFactory<>("id"));
        colAtendendo.setCellValueFactory(new PropertyValueFactory<>("atendendoComo"));
        
        table.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
             
        EspecialidadeService es = new EspecialidadeService();
        especialidadeMedica = FXCollections.observableArrayList(es.buscarEspecialidades());
        colAtendendo.setCellFactory(ComboBoxTableCell.forTableColumn(especialidadeMedica));
        //Obtendo valor inserido na coluna de Atedendimento como.. referente a especialidade que está sendo exercida naquele plantao
        colAtendendo.setOnEditCommit(
            new EventHandler<CellEditEvent<Medico, Especialidade>>() {
                @Override
                public void handle(CellEditEvent<Medico, Especialidade> t) {
                    ((Medico) t.getTableView().getItems().get(
                        t.getTablePosition().getRow())
                        ).setAtendendoComo(t.getNewValue());
                }
            }
        );
        
        PontoAtendimentoService ps = new PontoAtendimentoService();
        ObservableList<PontoAtendimento> pt = FXCollections.observableArrayList(ps.buscarPontos());
        slcPonto.setItems(pt);
        
        
        ObservableList<Especialidade> e = FXCollections.observableArrayList(es.buscarEspecialidades());
        slcEspecialidade.setItems(e);
         
        medicos = FXCollections.observableArrayList(dados);
        table.setItems(medicos);
    }
    
    @FXML
    void excluirEspecialidade(ActionEvent event) {

    }
    
    
}
