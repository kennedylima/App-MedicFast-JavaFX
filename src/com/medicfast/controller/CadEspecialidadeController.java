
package com.medicfast.controller;

import com.medicfast.model.Especialidade;
import com.medicfast.service.EspecialidadeService;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;


public class CadEspecialidadeController implements Initializable {
    
    @FXML
    private TextField txtEspecialidade;
    
    @FXML
    private Button btnLimpar;
    
    @FXML
    private Button btnConsultar;
    
    @FXML
    private Button btnSalvar;
    
   
    private static Boolean valida = false;
    private static Especialidade esp;

    public static Boolean getValida() {
        return valida;
    }

    public static void setValida(Boolean valida) {
        CadEspecialidadeController.valida = valida;
    }
    
    
    
    @FXML
    private void Limpar(ActionEvent event){
        limpar();
    }
    public void limpar (){
        txtEspecialidade.setText(null);
    }
    @FXML
    private void Consultar(ActionEvent event){
        TelaController tela = new TelaController();
        tela.inserirTela("ConsEspecialidade");
    }
    
    @FXML
    private void Salvar (ActionEvent event){
        
        Especialidade e = new Especialidade();
        e.setNome(txtEspecialidade.getText());
        
        EspecialidadeService es = new EspecialidadeService();
        String msg = es.Salvar(e);
        
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(msg);
        alert.showAndWait();
        limpar();
    }
    
        
    public void Editar (){
        EspecialidadeService es = new EspecialidadeService();
        esp.setNome(txtEspecialidade.getText());
        String msg = es.Salvar(esp);
        
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(msg);
        alert.showAndWait();
        valida = false;
        esp = new Especialidade();
        TelaController tc = new TelaController();
        tc.inserirTela("ConsEspecialidade");
        
    }
    
    public void TelaEditar(Especialidade e){
        valida = true;
        esp=e;
        TelaController tc = new TelaController();
        tc.inserirTela("CadEspecialidade");
    }
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if(valida==true){
            txtEspecialidade.setText(esp.getNome());
            btnSalvar.setOnAction(event -> Editar());
        }
    }

    
}


