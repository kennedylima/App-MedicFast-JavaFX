
package com.medicfast.controller;

import com.medicfast.model.TipoOcorrencia;
import com.medicfast.service.TipoOcorrenciaService;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;


public class CadTipoOcorrenciaController implements Initializable {
    
    @FXML
    private Button btnSalvar;

    @FXML
    private TextField txtNomeOcorrencia;

    @FXML
    private Button btnConsultar;

    @FXML
    private Button btnLimpar;

    private static Boolean valida = false;
    private static TipoOcorrencia tOcorrencia;

    public static Boolean getValida() {
        return valida;
    }

    public static void setValida(Boolean valida) {
        CadTipoOcorrenciaController.valida = valida;
    }
    
    
    
    @FXML
    void limpar(ActionEvent event) {
        txtNomeOcorrencia.setText(null);
    }

    @FXML
    void consultar(ActionEvent event) {
        TelaController tela = new TelaController();
        tela.inserirTela("ConsTipoOcorrencia");
    }

    @FXML
    void salvar(ActionEvent event) {
        TipoOcorrencia t = new TipoOcorrencia();
        t.setDescricao(txtNomeOcorrencia.getText());
        
        TipoOcorrenciaService ts = new TipoOcorrenciaService();
        String msg = ts.salvar(t);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(msg);
        alert.showAndWait();
        txtNomeOcorrencia.setText(null);
    }
    
    public void Editar (){
        TipoOcorrenciaService ts = new TipoOcorrenciaService();
        tOcorrencia.setDescricao(txtNomeOcorrencia.getText());
        String msg = ts.salvar(tOcorrencia);
        
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(msg);
        alert.showAndWait();
        valida = false;
        tOcorrencia = new TipoOcorrencia();
        TelaController tc = new TelaController();
        tc.inserirTela("ConsTipoOcorrencia");
        
    }
    public void TelaEditar(TipoOcorrencia t){
        valida = true;
        tOcorrencia=t;
        TelaController tc = new TelaController();
        tc.inserirTela("CadTipoOcorrencia");
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if(valida==true){
            txtNomeOcorrencia.setText(tOcorrencia.getDescricao());
            btnSalvar.setOnAction(event -> Editar());
        }
    }
}
