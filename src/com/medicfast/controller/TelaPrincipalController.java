/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.medicfast.controller;

import com.medicfast.model.Usuario;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;


public class TelaPrincipalController implements Initializable {
   
    
    @FXML
    private MenuItem novoEspecialidade;
    
    @FXML
    private MenuItem novoMedico;
    
    @FXML
    private MenuItem novoMedicamento;
    
    @FXML
    private MenuItem novoPonto;
    
    @FXML
    private MenuItem novoUsuario;
     
    @FXML
    private MenuItem novoOcorrencia;
    
    @FXML
    private MenuItem conEspecialidade;
    
    @FXML
    private MenuItem conMedico;
    
    @FXML
    private MenuItem conMedicamento;
    
    @FXML
    private MenuItem conPonto;
    
    @FXML
    private MenuItem conUsuario;     
     
    @FXML
    private MenuItem conOcorrencia;
    
    @FXML
    private MenuItem remEspecialidade;
    
    @FXML
    private MenuItem remMedicamento;
    
    @FXML
    private MenuItem remMedico;
    
    @FXML
    private MenuItem remPonto;

    
    
    @FXML
    private Button senha;

    @FXML
    private Button medico;
    
    
    @FXML
    private Button medicamento;
    
    @FXML
    private Menu menuNovo;
    
     @FXML
    private MenuItem btnSair;

    
    @FXML
    private AnchorPane centro;

    TelaController tc = new TelaController();
    
    public static  AnchorPane conteudo;
    
    @FXML
    public void sair(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Sair do Sistema");
        alert.setHeaderText("Tem Certeza que deseja sair ?");
        
        
        Optional<ButtonType> confirm;
        
        //recebe o resultado da tela (Sim ou Nao )
        confirm = alert.showAndWait();
        
        //verifica se o usuario pressionou o botão sim para sair
        if(confirm.get() == ButtonType.OK){
            //Fecha a Aplicação
            System.exit(0);
        }
    }
    
    @FXML
    public void medicamento(ActionEvent event){
        
    }
    
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
        conteudo=centro;
        
        //---------------------Novo-----------------------------------------
        novoEspecialidade.setOnAction(event -> tc.inserirTela("CadEspecialidade"));
        novoMedicamento.setOnAction(event -> tc.inserirTela("CadMedicamento"));
        novoMedico.setOnAction(event -> tc.inserirTela("CadMedico"));
        novoPonto.setOnAction(event -> tc.inserirTela("CadPontoAtendimento"));
        novoUsuario.setOnAction(event -> tc.inserirTela("CadUsuario"));
        novoOcorrencia.setOnAction(event -> tc.inserirTela("CadTipoOcorrencia"));
        
        //---------------------Consulta--------------------------------------
        conMedico.setOnAction(event -> tc.inserirTela("ConsMedico"));
        conMedicamento.setOnAction(event -> tc.inserirTela("ConsMedicamento"));
        conPonto.setOnAction(event-> tc.inserirTela("ConsPonto"));
        conEspecialidade.setOnAction(event-> tc.inserirTela("ConsEspecialidade"));
        conUsuario.setOnAction(event -> tc.inserirTela("ConsUsuario"));
        conOcorrencia.setOnAction(event -> tc.inserirTela("ConsTipoOcorrencia"));
        
        // --------------------Button----------------------------------------
        medico.setOnAction(event -> tc.inserirTela("ConsMedico"));
        medicamento.setOnAction(event -> tc.inserirTela("ConsMedicamento"));
        senha.setOnAction(event  -> tc.inserirTela("Senha"));
        
        Usuario usuario = UsuarioLogado.getUsuarioLogado();
        
        if(usuario.getAdministradorLocal() == true){
            novoPonto.setVisible(false);
            novoMedicamento.setVisible(false);
            
            
        }else if(usuario.getColaborador()== true){
            menuNovo.setVisible(false);
            conEspecialidade.setVisible(false);
            conOcorrencia.setVisible(false);
            conUsuario.setVisible(false);
        }
        
       
    }
}
