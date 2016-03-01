package com.medicfast.controller;

import com.medicfast.model.PontoAtendimento;
import com.medicfast.model.Usuario;
import com.medicfast.service.PontoAtendimentoService;
import com.medicfast.service.UsuarioService;
import java.net.URL;
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
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import net.sercris.util.ValidationFields;

public class CadUsuarioController implements Initializable {

    @FXML
    private TextField txUsuario;

    @FXML
    private PasswordField txSenha;

    @FXML
    private TextField txNome;
    
    @FXML
    private ComboBox<String> ComboBoxTipoUsuario;
            
    @FXML
    private ComboBox<PontoAtendimento> ComboBoxPonto;
    
    @FXML
    private Label labelPonto;
    
    @FXML
    private Button btnLimpar;
    @FXML
    private Button btnConsultar;
    
    @FXML
    private Button btnSalvar;

    private static boolean valida;
    private static Usuario usr;
    private static int tipoUsuario;
    
    @FXML
    public void limpar(ActionEvent event){
        limpar();
    }
    
    @FXML
    public void consultar(ActionEvent event){
        TelaController tela = new TelaController();
        tela.inserirTela("ConsUsuario");
    }
    
    @FXML
    void salvar(ActionEvent event) {
        if (valida == false) {
            if (ValidationFields.checkEmptyFields(txUsuario, txSenha, txNome,ComboBoxTipoUsuario)) {
                
                Usuario user = new Usuario();
                user.setNome(txNome.getText());
                user.setUsuario(txUsuario.getText());
                user.setSenha(txSenha.getText());
                
                    if(tipoUsuario == 1){
                        user.setAdministradorGeral(true);
                        user.setAdministradorLocal(false);
                        user.setColaborador(false);
                        user.setDescricaoTipoUsuario("Administrador Geral");
                    }else if(tipoUsuario == 2){
                        user.setAdministradorGeral(false);
                        user.setAdministradorLocal(true);
                        user.setColaborador(false);
                        user.setDescricaoTipoUsuario("Administrador Local");
                    
                    }else if(tipoUsuario == 3){
                        user.setAdministradorGeral(false);
                        user.setAdministradorLocal(false);
                        user.setColaborador(true);
                        user.setDescricaoTipoUsuario("Colaborador");
                    }
                
                user.setPontoAtendimento(ComboBoxPonto.getSelectionModel().getSelectedItem());
                UsuarioService userService = new UsuarioService();
                String msg;
                msg = userService.salvar(user);

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText(msg);
                alert.showAndWait();
                
                limpar(event);
            }
        } else {
            Usuario user = usr;
            user.setNome(txNome.getText());
            user.setUsuario(txUsuario.getText());
            user.setSenha(txSenha.getText());

            UsuarioService userService = new UsuarioService();
            String msg;
            msg = userService.salvar(user);

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(msg);
            alert.showAndWait();

            usr = new Usuario();
            limpar(event);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if (valida == true) {
            txNome.setText(usr.getNome());
            txSenha.setText(usr.getSenha());
            txUsuario.setText(usr.getUsuario());
         
        }
        
         PontoAtendimentoService pas = new PontoAtendimentoService();
         ObservableList<PontoAtendimento> ponto = FXCollections.observableArrayList(pas.buscarPontos());
         ComboBoxPonto.setItems(ponto);
         ComboBoxPonto.setVisible(false);
         labelPonto.setVisible(false);
         ComboBoxTipoUsuario.setItems(FXCollections.observableArrayList("Administrador Geral","Administrador Local","Colaborador"));

    }
    
    @FXML
    public void acao(ActionEvent event){
        String opcao = ComboBoxTipoUsuario.getSelectionModel().getSelectedItem();
        
        if(opcao=="Administrador Geral"){
            ComboBoxPonto.setVisible(false);
            labelPonto.setVisible(false);
            tipoUsuario =1;
            
        }else if(opcao=="Administrador Local"){
            tipoUsuario = 2;
            ComboBoxPonto.setVisible(true);
            labelPonto.setVisible(true);
            
        }else if(opcao=="Colaborador"){
            tipoUsuario = 3;
            ComboBoxPonto.setVisible(true);
            labelPonto.setVisible(true);
        }
    }

    void TelaEditar(Usuario u) {
        valida = true;
        usr = u;
        TelaController tc = new TelaController();
        tc.inserirTela("CadUsuario");
    }

    private void limpar() {
        txUsuario.setText(null);
        txSenha.setText(null);
        txNome.setText(null);
        ComboBoxTipoUsuario.setItems(FXCollections.observableArrayList("Administrador Geral","Administrador Local","Colaborador"));
        ComboBoxTipoUsuario.getSelectionModel().clearSelection();
        ComboBoxPonto.setVisible(false);
        labelPonto.setVisible(false);
    }

}
