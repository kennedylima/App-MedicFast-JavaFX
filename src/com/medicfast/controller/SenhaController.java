
package com.medicfast.controller;

import com.medicfast.DAO.SenhaDAO;
import com.medicfast.model.Senha;
import com.medicfast.model.Usuario;
import com.medicfast.service.SenhaService;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import net.sercris.util.ValidationFields;


public class SenhaController implements Initializable {
    
    @FXML
    private Button btnSalvar;

    @FXML
    private Label labelNumSenha;

    @FXML
    private TextField txEspecialidade;

    @FXML
    private TextField txSintomas;

    @FXML
    private CheckBox combSim;

    @FXML
    private TextArea txObs;

    @FXML
    private Button btnProxSenha;

    @FXML
    private CheckBox combNao;

    @FXML
    private TextField txOcorrencia;

    @FXML
    private FlowPane flowSenha;
    
    public static Senha s ;
    public static SenhaService se = new SenhaService();
    public static Usuario usuarioLogado = UsuarioLogado.getUsuarioLogado();
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        txSintomas.setEditable(false);
        s= se.buscarUltimoAtendido(usuarioLogado);
        
        if(s ==null){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("Não há novas Senhas");
            alert.showAndWait();
            labelNumSenha.setVisible(false);
        }       
        
        inserirInformacaoNaTela();        
    }
    
    @FXML
    public void salvar (ActionEvent event){
        if(combSim.isSelected() && combNao.isSelected()){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("Por Favor selecione apenas uma opção");
            alert.showAndWait();
            combNao.setSelected(false);
            combSim.setSelected(false);
        }
        else if(combSim.isSelected()){
            s.setChamado(Boolean.TRUE);
            s.setAtendido(Boolean.TRUE);
            String msg = se.salvar(s);
            
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(msg);
            alert.showAndWait();
            
            combNao.setSelected(false);
            combSim.setSelected(false);
            
            proximaSenha();
        
        }else if(combNao.isSelected()){
            if(ValidationFields.checkEmptyFields(txObs)){
                s.setChamado(Boolean.TRUE);
                s.setAtendido(Boolean.FALSE);
                String msg = se.salvar(s);
                
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText(msg);
                alert.showAndWait();
                
                combNao.setSelected(false);
                combSim.setSelected(false);
                proximaSenha();
            }
        }
    }
    
    @FXML
    public void proximaSenha(){
        s= se.buscarUltimoAtendido(usuarioLogado);
        if(s==null){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("Não há novas Senhas !");
            alert.showAndWait();
        
        }else{
            inserirInformacaoNaTela();
        }
    }
    
    public void inserirInformacaoNaTela(){
        labelNumSenha.setVisible(true);
        txObs.setText(s.getObservacao());
        txOcorrencia.setText(s.getOcorrencia().getDescricao());
        txSintomas.setText(s.getSintoma());
        txEspecialidade.setText(s.getEspecialidade().getNome());
        labelNumSenha.setText(String.valueOf(s.getNumero()));
    }
}
